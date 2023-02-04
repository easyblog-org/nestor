package top.easyblog.titan.nestor.feign.internal;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import top.easyblog.titan.nestor.response.NestorResultCode;

/**
 * @author: frank.huang
 * @date: 2021-11-14 20:32
 */
@Data
public class BaseClientResponse<T> implements Response<T>{

    private T data;
    private String message;
    private String path;
    private String code;
    private String serverTime;
    private String txId;

    @Override
    public boolean isSuccess() {
        return StringUtils.equalsIgnoreCase(code, NestorResultCode.SUCCESS.getCode());
    }

    @Override
    public T data() {
        return this.data;
    }

    @Override
    public String message() {
        return this.message;
    }

    @Override
    public String displayMessage() {
        return message;
    }

    @Override
    public String resultCode() {
        return this.code;
    }
}
