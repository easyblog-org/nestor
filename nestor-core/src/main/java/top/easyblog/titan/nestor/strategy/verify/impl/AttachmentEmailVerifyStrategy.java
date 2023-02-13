package top.easyblog.titan.nestor.strategy.verify.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.easyblog.titan.nestor.context.VerifyContext;
import top.easyblog.titan.nestor.enums.MessageSendChannel;
import top.easyblog.titan.nestor.processor.MessageProcessorContext;
import top.easyblog.titan.nestor.strategy.verify.MessageVerifyService;
import top.easyblog.titan.nestor.strategy.verify.MessageVerifyStrategy;
import top.easyblog.titan.nestor.strategy.verify.impl.verifiers.AttachmentValidVerifier;
import top.easyblog.titan.nestor.strategy.verify.impl.verifiers.TemplateValidVerifier;

/**
 * 带附件的邮件参数校验
 *
 * @author: frank.huang
 * @date: 2023-02-12 14:30
 */
@Slf4j
@Component
public class AttachmentEmailVerifyStrategy implements MessageVerifyStrategy {

    @Autowired
    private MessageVerifyService messageVerifyService;

    @Override
    public byte getPushType() {
        return MessageSendChannel.PLAIN_EMAIL.getCode();
    }

    @Override
    public void verify(MessageProcessorContext context) {
        messageVerifyService.verify(VerifyContext.builder()
                .templateCode(context.getTemplateCode())
                .attachments(context.getAttachments())
                .checkOptions(Lists.newArrayList(
                        TemplateValidVerifier.class,
                        AttachmentValidVerifier.class
                ))
                .build());
    }
}
