package top.easyblog.titan.nestor.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * System unified response code
 *
 * @author frank.huang
 * @since 2021/8/21 22:13
 */
@Getter
@AllArgsConstructor
public enum NestorResultCode {
    //sever internal
    SUCCESS,
    INVALID_PARAMS,
    NOT_FOUND,
    INTERNAL_ERROR,
    DATA_ACCESS_FAIL,
    PARAMETER_VALIDATE_FAILED,

    SIGN_FAIL,
    SIGN_ERROR,
    SIGN_NOT_FOUND,
    SING_HAS_EXPIRE,

    REMOTE_INVOKE_FAIL, NULL_RECORD_NOT_ALLOW;


    public String getCode() {
        return this.name().toLowerCase();
    }
}
