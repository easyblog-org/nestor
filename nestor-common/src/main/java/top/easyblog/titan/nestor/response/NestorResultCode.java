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
    FAIL,
    INVALID_PARAMS,
    NOT_FOUND,
    INTERNAL_ERROR,
    DATA_ACCESS_FAIL,
    PARAMETER_VALIDATE_FAILED,

    SIGN_FAIL,
    SIGN_ERROR,
    SIGN_NOT_FOUND,
    SING_HAS_EXPIRE,

    REMOTE_INVOKE_FAIL,
    DB_OPERATE_RECORD_NOT_ALLOW_NULL,   // 数据库操作实体对象不允许为null
    ILLEGAL_CONFIG_TYPE,   // 非法的消息参数配置类型
    ILLEGAL_TEMPLATE_VALUE_TYPE, MESSAGE_CONFIG_NOT_FOUND;   // 非法的消息参数模板取值配置类型


    public String getCode() {
        return this.name().toLowerCase();
    }
}
