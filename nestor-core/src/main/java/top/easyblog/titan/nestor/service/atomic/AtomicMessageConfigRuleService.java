package top.easyblog.titan.nestor.service.atomic;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.nestor.dao.auto.mapper.MessageConfigRuleMapper;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfigRule;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfigRuleExample;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.request.QueryMessageConfigRuleRequest;
import top.easyblog.titan.nestor.request.QueryMessageConfigRulesRequest;
import top.easyblog.titan.nestor.response.NestorResultCode;
import top.easyblog.titan.nestor.util.JsonUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author: frank.huang
 * @date: 2023-02-04 19:44
 */
@Slf4j
@Service
public class AtomicMessageConfigRuleService {


    @Autowired
    private MessageConfigRuleMapper messageConfigRuleMapper;

    public void insertOne(MessageConfigRule record) {
        if (Objects.isNull(record)) {
            throw new BusinessException(NestorResultCode.DB_OPERATE_RECORD_NOT_ALLOW_NULL);
        }
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        messageConfigRuleMapper.insertSelective(record);
        log.info("[DB]Insert new message config rule successfully!Details==>{}", JsonUtils.toJSONString(record));
    }

    public MessageConfigRule queryByRequest(QueryMessageConfigRuleRequest request) {
        if (queryParamAllEmpty(request)) {
            return null;
        }
        MessageConfigRuleExample example = new MessageConfigRuleExample();
        MessageConfigRuleExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(request.getCode())) {
            criteria.andTemplateCodeEqualTo(request.getCode());
        }
        return Iterables.getFirst(messageConfigRuleMapper.selectByExample(example), null);
    }

    private boolean queryParamAllEmpty(QueryMessageConfigRuleRequest request) {
        return StringUtils.isBlank(request.getCode());
    }

    public void updateByPKSelective(MessageConfigRule record) {
        if (Objects.isNull(record)) {
            throw new BusinessException(NestorResultCode.DB_OPERATE_RECORD_NOT_ALLOW_NULL);
        }
        record.setUpdateTime(new Date());
        messageConfigRuleMapper.updateByPrimaryKeySelective(record);
        log.info("[DB]Update new message config rule successfully.Details==>{}", JsonUtils.toJSONString(record));
    }

    public long countByRequest(QueryMessageConfigRulesRequest request) {
        return 0;
    }

    public List<MessageConfigRule> queryListByRequest(QueryMessageConfigRulesRequest request) {
        return null;
    }
}
