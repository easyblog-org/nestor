package top.easyblog.titan.nestor.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;
import top.easyblog.titan.nestor.response.NestorResultCode;

/**
 * @author: frank.huang
 * @date: 2021-11-01 17:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private NestorResultCode code;

    public BusinessException(@NonNull NestorResultCode code) {
        this.code = code;
    }

    public BusinessException(@NonNull NestorResultCode code, String message){
        super(message);
        this.code=code;
    }

    public BusinessException(@NonNull NestorResultCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }


    public String getCode(){
        return this.code.getCode();
    }

}
