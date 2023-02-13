package top.easyblog.titan.nestor.processor.chian.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.easyblog.titan.nestor.dao.auto.model.MessageSendRecord;
import top.easyblog.titan.nestor.enums.MessageSendStatus;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.processor.MessageProcessorContext;
import top.easyblog.titan.nestor.processor.chian.MessageProcessChain;
import top.easyblog.titan.nestor.request.CreateMessageSendRecordRequest;
import top.easyblog.titan.nestor.response.NestorResultCode;
import top.easyblog.titan.nestor.service.atomic.AtomicMessageSendRecordService;
import top.easyblog.titan.nestor.strategy.assemble.MessageAssembleStrategy;
import top.easyblog.titan.nestor.strategy.assemble.MessageAssembleStrategyContext;
import top.easyblog.titan.nestor.util.IdGenerator;

import java.util.Objects;

/**
 * 消息组装处理器，把消息和模板组装起来
 *
 * @author: frank.huang
 * @date: 2023-02-11 17:50
 */
@Slf4j
@Component
public class MessageAssembleProcessChain implements MessageProcessChain {

    @Autowired
    private AtomicMessageSendRecordService atomicMessageSendRecordService;

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public MessageProcessorContext process(MessageProcessorContext context) {
        MessageAssembleStrategy assembleStrategy = MessageAssembleStrategyContext.getMessageAssembleStrategy(context.getChannel());
        if (Objects.isNull(assembleStrategy)) {
            throw new BusinessException(NestorResultCode.ILLEGAL_MESSAGE_SEND_CHANNEL);
        }
        assembleStrategy.assemble(context);
        saveAssembledRecord(context);
        return context;
    }


    private void saveAssembledRecord(MessageProcessorContext context) {
        MessageSendRecord messageSendRecord = new MessageSendRecord();
        messageSendRecord.setCode(IdGenerator.getUUID(IdGenerator.MAX_UUID_LEN));
        messageSendRecord.setChannel(context.getChannel());
        messageSendRecord.setReceiver(context.getReceiver());
        messageSendRecord.setSender(context.getSender());
        messageSendRecord.setContent(context.getContent());
        messageSendRecord.setTitle(context.getTitle());
        messageSendRecord.setStatus(MessageSendStatus.INITIALIZED.getCode());
        atomicMessageSendRecordService.create(messageSendRecord);
    }
}
