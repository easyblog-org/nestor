package top.easyblog.titan.nestor.processor.chian.impl;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import top.easyblog.titan.nestor.dao.auto.model.MessageSendRecord;
import top.easyblog.titan.nestor.dao.auto.model.MessageTemplate;
import top.easyblog.titan.nestor.enums.MessageSendStatus;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.parser.TemplateParser;
import top.easyblog.titan.nestor.processor.MessageProcessorContext;
import top.easyblog.titan.nestor.processor.chian.MessageProcessChain;
import top.easyblog.titan.nestor.request.QueryMessageTemplateRequest;
import top.easyblog.titan.nestor.response.NestorResultCode;
import top.easyblog.titan.nestor.service.atomic.AtomicMessageSendRecordService;
import top.easyblog.titan.nestor.service.atomic.AtomicMessageTemplateService;
import top.easyblog.titan.nestor.util.IdGenerator;
import top.easyblog.titan.nestor.util.JsonUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
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

    @Autowired
    private AtomicMessageTemplateService atomicMessageTemplateService;

    @Autowired
    private TemplateParser stringTemplateParser;

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public MessageProcessorContext process(MessageProcessorContext context) {
        MessageTemplate messageTemplate = atomicMessageTemplateService.queryByRequest(QueryMessageTemplateRequest.builder()
                .templateCode(context.getTemplateCode())
                .build());
        if (Objects.isNull(messageTemplate)) {
            throw new BusinessException(NestorResultCode.TEMPLATE_NOT_FOUND);
        }

        Map<String, String> variables = JsonUtils.jsonToMap(context.getReplaceValues());
        String parsedContent = stringTemplateParser.parse(messageTemplate.getMsgContent(), variables);
        context.setContent(parsedContent);
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
