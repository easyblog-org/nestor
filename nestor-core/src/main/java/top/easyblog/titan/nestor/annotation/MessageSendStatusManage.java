package top.easyblog.titan.nestor.annotation;

import java.lang.annotation.*;

/**
 * @author: frank.huang
 * @date: 2023-02-11 21:28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MessageSendStatusManage {
}
