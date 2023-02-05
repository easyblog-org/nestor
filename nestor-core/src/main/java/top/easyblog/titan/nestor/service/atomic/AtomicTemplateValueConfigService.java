package top.easyblog.titan.nestor.service.atomic;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.nestor.dao.auto.mapper.TemplateValueConfigMapper;
import top.easyblog.titan.nestor.dao.auto.model.TemplateValueConfig;
import top.easyblog.titan.nestor.dao.auto.model.TemplateValueConfigExample;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.request.QueryTemplateValueConfigRequest;
import top.easyblog.titan.nestor.response.NestorResultCode;
import top.easyblog.titan.nestor.util.JsonUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @author: frank.huang
 * @date: 2023-02-04 19:42
 */
@Slf4j
@Service
public class AtomicTemplateValueConfigService {

    @Autowired
    private TemplateValueConfigMapper templateValueConfigMapper;

    public void insertOne(TemplateValueConfig record) {
        if (Objects.isNull(record)) {
            throw new BusinessException(NestorResultCode.DB_OPERATE_RECORD_NOT_ALLOW_NULL);
        }
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        templateValueConfigMapper.insertSelective(record);
        log.info("[DB]Create template value config successfully!Details==>{}",JsonUtils.toJSONString(record));
    }

    public TemplateValueConfig queryByRequest(QueryTemplateValueConfigRequest request) {
        if (Objects.isNull(request.getId())) {
            return null;
        }
        TemplateValueConfigExample example = new TemplateValueConfigExample();
        TemplateValueConfigExample.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(request.getId())) {
            criteria.andIdEqualTo(request.getId());
        }
        return Iterables.getFirst(templateValueConfigMapper.selectByExample(example), null);
    }

    public void updateByPKSelective(TemplateValueConfig record) {
        if (Objects.isNull(record)) {
            throw new BusinessException(NestorResultCode.DB_OPERATE_RECORD_NOT_ALLOW_NULL);
        }
        record.setUpdateTime(new Date());
        templateValueConfigMapper.updateByPrimaryKey(record);
        log.info("[DB]Update template value config successfully!Details==>{}", JsonUtils.toJSONString(record));
    }
}
