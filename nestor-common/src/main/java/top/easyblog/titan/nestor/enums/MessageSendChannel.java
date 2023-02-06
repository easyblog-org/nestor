package top.easyblog.titan.nestor.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author: frank.huang
 * @date: 2023-02-06 19:44
 */
@Getter
@AllArgsConstructor
public enum MessageSendChannel {

    /**
     * 邮箱
     */
    EMAIL((byte) 1),
    /**
     * 短信
     */
    SMS((byte) 2),
    /**
     * 微信通知
     */
    WX((byte) 3),

    ;

    private final byte code;


    public static MessageSendChannel codeOf(Byte code) {
        if (Objects.isNull(code)) {
            return null;
        }
        return Arrays.stream(MessageSendChannel.values())
                .filter(channel -> Objects.equals(channel.getCode(), code))
                .findAny().orElse(null);
    }
}
