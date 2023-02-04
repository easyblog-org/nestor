package top.easyblog.titan.nestor.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.nestor.bean.UserDetailsBean;
import top.easyblog.titan.nestor.constant.Constants;
import top.easyblog.titan.nestor.feign.client.DemoClient;
import top.easyblog.titan.nestor.request.QueryUserRequest;
import top.easyblog.titan.nestor.service.IDemoService;
import top.easyblog.titan.nestor.util.JsonUtils;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Example service
 *
 * @author: frank.huang
 * @date: 2021-11-01 21:21
 */
@Slf4j
@Service
public class DemoService implements IDemoService {

    @Autowired
    private DemoClient demoClient;

    @Override
    public Integer demo1() {
        return new Random().nextInt();
    }

    @Override
    public List<UserDetailsBean> demo3() {
        return Lists.newArrayList(UserDetailsBean.builder()
                        .name("法外狂徒1")
                        .age(100)
                        .address("本宇宙-拉尼亚凯亚超星系团-室女座星系团-本星系群-银河系-猎户座旋臂-太阳系-地球")
                        .build(),
                UserDetailsBean.builder()
                        .name("法外狂徒2")
                        .age(200)
                        .address("本宇宙-拉尼亚凯亚超星系团-室女座星系团-本星系群-银河系-猎户座旋臂-太阳系-地球")
                        .build(),
                UserDetailsBean.builder()
                        .name("法外狂徒3")
                        .age(300)
                        .address("本宇宙-拉尼亚凯亚超星系团-室女座星系团-本星系群-银河系-猎户座旋臂-太阳系-地球")
                        .build());
    }

    @Override
    public Object demo4(QueryUserRequest request){
        return demoClient.request(() -> demoClient.getUserDetailBean(request));
    }

}
