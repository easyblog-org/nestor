package top.easyblog.titan.nestor.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.easyblog.titan.nestor.bean.MessageConfigBean;
import top.easyblog.titan.nestor.bean.MessageConfigRuleBean;
import top.easyblog.titan.nestor.constant.Constants;
import top.easyblog.titan.nestor.context.MessageConfigRuleContext;
import top.easyblog.titan.nestor.converter.BeanMapper;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfigRule;
import top.easyblog.titan.nestor.dao.auto.model.MessageTemplate;
import top.easyblog.titan.nestor.enums.MessageSendChannel;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.request.*;
import top.easyblog.titan.nestor.response.NestorResultCode;
import top.easyblog.titan.nestor.response.PageResponse;
import top.easyblog.titan.nestor.service.atomic.AtomicMessageConfigRuleService;
import top.easyblog.titan.nestor.service.atomic.AtomicMessageConfigService;
import top.easyblog.titan.nestor.service.atomic.AtomicMessageTemplateService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: frank.huang
 * @date: 2023-02-06 19:05
 */
@Slf4j
@Service
public class MessageConfigRuleService {

    @Autowired
    private AtomicMessageConfigRuleService messageConfigRuleService;

    @Autowired
    private AtomicMessageConfigService messageConfigService;

    @Autowired
    private AtomicMessageTemplateService messageTemplateService;

    @Autowired
    private BeanMapper beanMapper;


    public void createConfigRule(CreateMessageConfigRuleRequest request) {
        checkRequestParmaValid(MessageConfigRuleContext.builder()
                .configIds(Arrays.stream(StringUtils.split(request.getConfigIds(), Constants.COMMA)).collect(Collectors.toList()))
                .channel(request.getChannel())
                .templateCode(request.getTemplateCode())
                .checkIfNonNull(Boolean.FALSE)
                .build());
        MessageConfigRule messageConfigRule = beanMapper.buildMessageConfigRule(request);
        messageConfigRuleService.insertOne(messageConfigRule);
    }

    private void checkRequestParmaValid(MessageConfigRuleContext context) {
        if (!(Objects.isNull(context.getChannel()) && context.isCheckIfNonNull())) {
            MessageSendChannel sendChannel = MessageSendChannel.codeOf(context.getChannel());
            if (Objects.isNull(sendChannel)) {
                throw new BusinessException(NestorResultCode.ILLEGAL_MESSAGE_SEND_CHANNEL);
            }
        }

        if (!(CollectionUtils.isEmpty(context.getConfigIds()) && context.isCheckIfNonNull())) {
            List<MessageConfigBean> messageConfigBeans = messageConfigService.queryListByRequest(QueryMessageConfigsRequest.builder()
                    .codes(context.getConfigIds()).deleted(Boolean.FALSE).build());
            if (CollectionUtils.isEmpty(messageConfigBeans) || !Objects.equals(messageConfigBeans.size(), context.getConfigIds().size())) {
                throw new BusinessException(NestorResultCode.ILLEGAL_CONFIG_IDS);
            }
        }
        if (!(StringUtils.isBlank(context.getTemplateCode()) && context.isCheckIfNonNull())) {
            MessageTemplate messageTemplate = messageTemplateService.queryByRequest(QueryMessageTemplateRequest.builder()
                    .templateCode(context.getTemplateCode()).build());
            if (Objects.isNull(messageTemplate)) {
                throw new BusinessException(NestorResultCode.ILLEGAL_TEMPLATE_CODE);
            }
        }
    }

    public void updateConfigRule(String code, UpdateMessageConfigRuleRequest request) {
        MessageConfigRule messageConfigRule = messageConfigRuleService.queryByRequest(QueryMessageConfigRuleRequest.builder()
                .code(code).build());
        if (Objects.isNull(messageConfigRule)) {
            throw new BusinessException(NestorResultCode.MESSAGE_CONFIG_RULE_NOT_FOUND);
        }
        checkRequestParmaValid(MessageConfigRuleContext.builder()
                .configIds(Arrays.stream(StringUtils.split(request.getConfigIds(), Constants.COMMA)).collect(Collectors.toList()))
                .channel(request.getChannel())
                .templateCode(request.getTemplateCode())
                .checkIfNonNull(Boolean.TRUE)
                .build());
        MessageConfigRule newMessageConfigRule = beanMapper.buildMessageConfigRule(request, messageConfigRule.getId());
        messageConfigRuleService.updateByPKSelective(newMessageConfigRule);
    }

    public MessageConfigRuleBean details(QueryMessageConfigRuleRequest request) {
        MessageConfigRule messageConfigRule = messageConfigRuleService.queryByRequest(request);
        if (Objects.isNull(messageConfigRule)) {
            return null;
        }
        return beanMapper.buildMessageConfigRuleBean(messageConfigRule);
    }

    public PageResponse<MessageConfigRuleBean> list(QueryMessageConfigRulesRequest request) {
        long total = messageConfigRuleService.countByRequest(request);
        if (Objects.equals(NumberUtils.LONG_ZERO, total)) {
            return PageResponse.<MessageConfigRuleBean>builder().total(total).offset(request.getOffset()).limit(request.getLimit()).data(Collections.emptyList()).build();
        }
        List<MessageConfigRule> messageConfigRules = messageConfigRuleService.queryListByRequest(request);
        List<MessageConfigRuleBean> messageConfigRuleBeans = messageConfigRules.stream().map(config -> beanMapper.buildMessageConfigRuleBean(config)).collect(Collectors.toList());
        return PageResponse.<MessageConfigRuleBean>builder().total(total).offset(request.getOffset()).limit(request.getLimit()).data(messageConfigRuleBeans).build();
    }
}
