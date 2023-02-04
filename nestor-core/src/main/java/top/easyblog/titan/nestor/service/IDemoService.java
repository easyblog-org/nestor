package top.easyblog.titan.nestor.service;

import top.easyblog.titan.nestor.bean.UserDetailsBean;
import top.easyblog.titan.nestor.request.QueryUserRequest;

import java.util.List;

/**
 * @author: frank.huang
 * @date: 2021-11-20 10:09
 */
public interface IDemoService {

    Integer demo1();

    List<UserDetailsBean> demo3();

    Object demo4(QueryUserRequest request);

}
