package top.easyblog.titan.nestor.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @author: frank.huang
 * @date: 2021-11-01 19:12
 */
public class IdGenerator {


    public static final int MAX_UUID_LEN = 32;

    private IdGenerator() {
    }

    public static String getRequestId() {
        return getUUID(MAX_UUID_LEN);
    }

    public static String getTraceId() {
        return getUUID(MAX_UUID_LEN) + ((int) ((Math.random() * 9 + 1) * 100000));
    }


    public static String getUUID(int len) {
        if (len <= 0 || len > MAX_UUID_LEN) {
            return StringUtils.EMPTY;
        }
        return UUID.randomUUID().toString().replace("-", "").substring(0, len);
    }


}
