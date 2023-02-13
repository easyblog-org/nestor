package top.easyblog.titan.nestor.strategy.push.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import top.easyblog.titan.nestor.annotation.MessageSendStatusManage;
import top.easyblog.titan.nestor.context.MessageSendContext;
import top.easyblog.titan.nestor.dao.auto.model.MessageSendRecord;
import top.easyblog.titan.nestor.enums.MessageSendChannel;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.request.QueryMessageSendRecordRequest;
import top.easyblog.titan.nestor.response.NestorResultCode;
import top.easyblog.titan.nestor.service.atomic.AtomicMessageSendRecordService;
import top.easyblog.titan.nestor.strategy.push.MessagePushStrategy;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * 纯文本邮件
 *
 * @author: frank.huang
 * @date: 2023-02-11 20:10
 */
@Slf4j
@Component
@AllArgsConstructor
public class AttachmentEmailPushStrategy implements MessagePushStrategy {

    private final JavaMailSender mailSender;

    private final AtomicMessageSendRecordService atomicMessageSendRecordService;

    @Override
    public byte getPushType() {
        return MessageSendChannel.ATTACHMENT_EMAIL.getCode();
    }

    @Override
    @MessageSendStatusManage
    public void push(MessageSendContext context) {
        MessageSendRecord messageSendRecord = atomicMessageSendRecordService.details(QueryMessageSendRecordRequest.builder()
                .id(context.getSendRecordId()).build());
        if (Objects.isNull(messageSendRecord)) {
            throw new BusinessException(NestorResultCode.SEND_RECORD_NOT_FOUND);
        }
        MimeMessage mimeMessage = mailSender.createMimeMessage();
       try{
           MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
           helper.setFrom(messageSendRecord.getSender());
           helper.setTo(messageSendRecord.getReceiver());
           helper.setSubject(messageSendRecord.getTitle());
           helper.setText(messageSendRecord.getContent());
           List<File> attachments = context.getAttachments();
           // 添加附件（多个）
           if (CollectionUtils.isNotEmpty(attachments)) {
               for (File attachment : attachments) {
                   helper.addAttachment(attachment.getName(), attachment);
               }
           }
           // 发送邮件
           mailSender.send(mimeMessage);
       }catch (Exception e){
           log.info(e.getMessage());
           throw new BusinessException(NestorResultCode.SEND_MESSAGE_FAILED);
       }
    }
}
