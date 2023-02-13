package top.easyblog.titan.nestor.strategy.assemble;

import top.easyblog.titan.nestor.processor.MessageProcessorContext;

/**
 * @author: frank.huang
 * @date: 2023-02-13 21:27
 */
public interface MessageAssembleStrategy {
    /**
     * 获取发送策略
     *
     * @return
     */
    byte getPushType();

    /**
     * 组装消息
     *
     * @param context
     */
    void assemble(MessageProcessorContext context);
}
