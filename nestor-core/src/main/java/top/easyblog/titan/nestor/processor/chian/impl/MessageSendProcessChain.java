package top.easyblog.titan.nestor.processor.chian.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.easyblog.titan.nestor.context.MessageSendContext;
import top.easyblog.titan.nestor.dao.auto.model.MessageSendRecord;
import top.easyblog.titan.nestor.enums.MessageSendStatus;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.processor.MessageProcessorContext;
import top.easyblog.titan.nestor.processor.chian.MessageProcessChain;
import top.easyblog.titan.nestor.request.QueryMessageSendRecordRequest;
import top.easyblog.titan.nestor.response.NestorResultCode;
import top.easyblog.titan.nestor.service.atomic.AtomicMessageSendRecordService;
import top.easyblog.titan.nestor.strategy.push.MessagePushStrategy;
import top.easyblog.titan.nestor.strategy.push.MessagePushStrategyContext;
import top.easyblog.titan.nestor.util.IdGenerator;

import java.util.Objects;

/**
 * @author: frank.huang
 * @date: 2023-02-11 18:28
 */
@Slf4j
@Component
public class MessageSendProcessChain implements MessageProcessChain {

    @Autowired
    private AtomicMessageSendRecordService atomicMessageSendRecordService;

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public MessageProcessorContext process(MessageProcessorContext context) {
        MessageSendRecord messageSendRecord = atomicMessageSendRecordService.details(QueryMessageSendRecordRequest.builder()
                .id(context.getMessageRecordId())
                .build());
        if (Objects.nonNull(messageSendRecord)) {
            throw new BusinessException(NestorResultCode.INTERNAL_ERROR,
                    String.format("Not found message send record by id:%s", context.getMessageRecordId()));
        }
        MessagePushStrategy sendStrategy = MessagePushStrategyContext.getMessageSendStrategy(context.getChannel());
        if (Objects.isNull(sendStrategy)) {
            throw new BusinessException(NestorResultCode.ILLEGAL_MESSAGE_SEND_CHANNEL);
        }
        MessageSendContext sendContext = MessageSendContext.builder()
                .sendRecordId(context.getMessageRecordId())
                .attachments(Lists.newArrayList())
                .build();
        sendStrategy.push(sendContext);
        return context;
    }

}
