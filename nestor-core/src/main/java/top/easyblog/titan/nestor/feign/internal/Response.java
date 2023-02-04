package top.easyblog.titan.nestor.feign.internal;

/**
 * @author: frank.huang
 * @date: 2021-11-14 21:23
 */
public interface Response<T> {

    boolean isSuccess();

    T data();

    String message();

    String displayMessage();

    String resultCode();

}
