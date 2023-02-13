package top.easyblog.titan.nestor.strategy.verify;

import top.easyblog.titan.nestor.context.VerifyContext;

/**
 * @author: frank.huang
 * @date: 2023-02-12 14:41
 */
public interface VerifyStrategy {

    /**
     * 校验
     * @return 是否校验通过，true 通过；false 不通过
     * @param request
     */
    boolean verify(VerifyContext request);

}
