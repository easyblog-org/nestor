package top.easyblog.titan.nestor.strategy.verify.impl.verifiers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.easyblog.titan.nestor.context.VerifyContext;
import top.easyblog.titan.nestor.dao.auto.model.MessageTemplate;
import top.easyblog.titan.nestor.request.QueryMessageTemplateRequest;
import top.easyblog.titan.nestor.service.atomic.AtomicMessageTemplateService;
import top.easyblog.titan.nestor.strategy.verify.VerifyStrategy;

import java.util.Objects;

/**
 * @author: frank.huang
 * @date: 2023-02-12 14:29
 */
@Slf4j
@Component
public class TemplateValidVerifier implements VerifyStrategy {


    @Autowired
    private AtomicMessageTemplateService atomicMessageTemplateService;

    @Override
    public boolean verify(VerifyContext request) {
        if (StringUtils.isBlank(request.getTemplateCode())) {
            return false;
        }
        MessageTemplate messageTemplate = atomicMessageTemplateService.queryByRequest(QueryMessageTemplateRequest.builder()
                .templateCode(request.getTemplateCode())
                .build());
        return Objects.nonNull(messageTemplate) &&
                Objects.equals(Boolean.FALSE, messageTemplate.getDeleted()) &&
                StringUtils.isNotBlank(messageTemplate.getMsgContent()) &&
                Objects.isNull(messageTemplate.getSendChannel());
    }
}
