package top.easyblog.titan.nestor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.nestor.annotation.Transaction;
import top.easyblog.titan.nestor.bean.MessageConfigBean;
import top.easyblog.titan.nestor.converter.BeanMapper;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfig;
import top.easyblog.titan.nestor.dao.auto.model.TemplateValueConfig;
import top.easyblog.titan.nestor.request.CreateMessageConfigRequest;
import top.easyblog.titan.nestor.request.CreateTemplateValueConfigRequest;
import top.easyblog.titan.nestor.service.atomic.AtomicMessageConfigService;
import top.easyblog.titan.nestor.service.atomic.AtomicTemplateValueConfigService;

/**
 * 通知消息参数配置
 *
 * @author: frank.huang
 * @date: 2023-02-04 19:47
 */
@Slf4j
@Service
public class MessageConfigService {

    @Autowired
    private AtomicMessageConfigService atomicMessageConfigService;

    @Autowired
    private AtomicTemplateValueConfigService atomicTemplateValueConfigService;

    @Autowired
    private BeanMapper beanMapper;

    @Transaction
    public MessageConfigBean createConfig(CreateMessageConfigRequest request) {
        TemplateValueConfig templateValueConfig = beanMapper.buildTemplateConfig(request.getTemplateValueConfig());
        atomicTemplateValueConfigService.insertOne(templateValueConfig);
        MessageConfig messageConfig = beanMapper.buildMessageConfig(request, templateValueConfig.getId());
        atomicMessageConfigService.insertOne(messageConfig);
        return beanMapper.buildMessageConfigBean(messageConfig,templateValueConfig);
    }
}
