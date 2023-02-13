package top.easyblog.titan.nestor.strategy.push;

import top.easyblog.titan.nestor.context.MessageSendContext;

/**
 * 消息发送策略
 *
 * @author: frank.huang
 * @date: 2023-02-11 20:06
 */
public interface MessagePushStrategy {

    /**
     * 获取发送策略
     *
     * @return
     */
    byte getPushType();

    /**
     * 发送消息
     *
     * @param context
     */
    void push(MessageSendContext context);

}
