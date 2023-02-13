package top.easyblog.titan.nestor.strategy.assemble.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.easyblog.titan.nestor.enums.MessageSendChannel;
import top.easyblog.titan.nestor.processor.MessageProcessorContext;
import top.easyblog.titan.nestor.strategy.assemble.MessageAssembleStrategy;

/**
 * @author: frank.huang
 * @date: 2023-02-13 21:32
 */
@Slf4j
@Component
public class PlainEmailAssembleStrategy implements MessageAssembleStrategy {

    @Override
    public byte getPushType() {
        return MessageSendChannel.PLAIN_EMAIL.getCode();
    }

    @Override
    public void assemble(MessageProcessorContext context) {

    }
}
