package top.easyblog.titan.nestor.request;

/**
 * @author frank.huang
 * @date 2022/01/29 14:42
 */
public abstract  class BaseRequest {
    /**
     * 请求参校验
     */
    public boolean validate() {
        return true;
    }

}
