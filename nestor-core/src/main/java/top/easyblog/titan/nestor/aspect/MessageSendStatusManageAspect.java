package top.easyblog.titan.nestor.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.easyblog.titan.nestor.context.MessageSendContext;
import top.easyblog.titan.nestor.dao.auto.model.MessageSendRecord;
import top.easyblog.titan.nestor.enums.MessageSendStatus;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.request.BaseRequest;
import top.easyblog.titan.nestor.response.NestorResultCode;
import top.easyblog.titan.nestor.service.atomic.AtomicMessageSendRecordService;

import java.util.Arrays;
import java.util.Objects;

/**
 * 消息发送状态更新切面
 *
 * @author: frank.huang
 * @date: 2023-02-11 21:24
 */
@Slf4j
@Aspect
@Component
public class MessageSendStatusManageAspect {

    @Autowired
    private AtomicMessageSendRecordService atomicMessageSendRecordService;

    @Pointcut("@annotation(top.easyblog.titan.nestor.annotation.MessageSendStatusManage)")
    public void sendStatusManage() {

    }

    @After(value = "sendStatusManage()")
    public void beforeSend(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).filter(arg -> Objects.nonNull(arg) && arg instanceof MessageSendContext).forEach(arg -> {
            MessageSendContext messageSendContext = (MessageSendContext) arg;
            MessageSendRecord newMessageSendRecord = new MessageSendRecord();
            newMessageSendRecord.setId(messageSendContext.getSendRecordId());
            newMessageSendRecord.setStatus(MessageSendStatus.SENDING.getCode());
            atomicMessageSendRecordService.update(newMessageSendRecord);
        });
    }


    @After(value = "sendStatusManage()")
    public void afterSendSuccess(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).filter(arg -> Objects.nonNull(arg) && arg instanceof MessageSendContext).forEach(arg -> {
            MessageSendContext messageSendContext = (MessageSendContext) arg;
            MessageSendRecord newMessageSendRecord = new MessageSendRecord();
            newMessageSendRecord.setId(messageSendContext.getSendRecordId());
            newMessageSendRecord.setStatus(MessageSendStatus.SUCCESS.getCode());
            atomicMessageSendRecordService.update(newMessageSendRecord);
        });
    }

    @AfterThrowing(value = "sendStatusManage()")
    public void afterSendFailed(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String strategyName = joinPoint.getClass().getSimpleName();
        Arrays.stream(args).filter(arg -> Objects.nonNull(arg) && arg instanceof MessageSendContext).forEach(arg -> {
            MessageSendContext messageSendContext = (MessageSendContext) arg;
            log.info("[{}]Send attachment email failed,record_code:{}", strategyName, messageSendContext.getSendRecordId());

            MessageSendRecord newMessageSendRecord = new MessageSendRecord();
            newMessageSendRecord.setId(messageSendContext.getSendRecordId());
            newMessageSendRecord.setStatus(MessageSendStatus.FAILED.getCode());
            atomicMessageSendRecordService.update(newMessageSendRecord);
        });
    }


}
