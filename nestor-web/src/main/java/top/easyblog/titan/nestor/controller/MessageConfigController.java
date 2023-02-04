package top.easyblog.titan.nestor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.easyblog.titan.nestor.annotation.ResponseWrapper;
import top.easyblog.titan.nestor.request.CreateMessageConfigRequest;
import top.easyblog.titan.nestor.service.MessageConfigService;

import java.util.Objects;

/**
 * @author: frank.huang
 * @date: 2023-02-04 19:49
 */
@RestController
@RequestMapping("/v1/message-config")
public class MessageConfigController {

    @Autowired
    private MessageConfigService messageConfigService;

    /**
     * 创建通知配置参数
     *
     * @return
     */
    @ResponseWrapper
    @PostMapping("/")
    public Object createConfig(@RequestBody CreateMessageConfigRequest request) {
        return messageConfigService.createConfig(request);
    }


}
