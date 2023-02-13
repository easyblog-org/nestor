package top.easyblog.titan.nestor.processor.chian.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.processor.MessageProcessorContext;
import top.easyblog.titan.nestor.processor.chian.MessageProcessChain;
import top.easyblog.titan.nestor.response.NestorResultCode;
import top.easyblog.titan.nestor.strategy.verify.MessageVerifyStrategy;
import top.easyblog.titan.nestor.strategy.verify.MessageVerifyStrategyContext;

import java.util.Objects;

/**
 * 消息参数检查
 *
 * @author: frank.huang
 * @date: 2023-02-11 17:48
 */
@Slf4j
@Component
public class MessageParameterCheckProcessChain implements MessageProcessChain {


    @Override
    public int priority() {
        return 1;
    }

    @Override
    public MessageProcessorContext process(MessageProcessorContext context) {
        MessageVerifyStrategy verifyStrategy = MessageVerifyStrategyContext.getMessageVerifyStrategy(context.getChannel());
        if (Objects.isNull(verifyStrategy)) {
            throw new BusinessException(NestorResultCode.ILLEGAL_MESSAGE_SEND_CHANNEL);
        }
        verifyStrategy.verify(context);
        return context;
    }

}
