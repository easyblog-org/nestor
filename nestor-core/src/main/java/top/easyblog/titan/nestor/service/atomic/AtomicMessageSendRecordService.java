package top.easyblog.titan.nestor.service.atomic;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.nestor.dao.auto.mapper.MessageSendRecordMapper;
import top.easyblog.titan.nestor.dao.auto.model.MessageSendRecord;
import top.easyblog.titan.nestor.dao.auto.model.MessageSendRecordExample;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.request.QueryMessageSendRecordRequest;
import top.easyblog.titan.nestor.response.NestorResultCode;
import top.easyblog.titan.nestor.util.JsonUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @author: frank.huang
 * @date: 2023-02-11 16:53
 */
@Slf4j
@Service
public class AtomicMessageSendRecordService {

    @Autowired
    private MessageSendRecordMapper mapper;

    public void create(MessageSendRecord record) {
        if (Objects.isNull(record)) {
            throw new BusinessException(NestorResultCode.DB_OPERATE_RECORD_NOT_ALLOW_NULL);
        }
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        mapper.insertSelective(record);
        log.info("[DB]Insert new message send record successfully!Details==>{}", JsonUtils.toJSONString(record));
    }

    public void update(MessageSendRecord record) {
        if (Objects.isNull(record)) {
            throw new BusinessException(NestorResultCode.DB_OPERATE_RECORD_NOT_ALLOW_NULL);
        }
        record.setUpdateTime(new Date());
        mapper.updateByPrimaryKeySelective(record);
        log.info("[DB]Update message send record successfully!Details==>{}", JsonUtils.toJSONString(record));
    }

    public MessageSendRecord details(QueryMessageSendRecordRequest request) {
        if (StringUtils.isBlank(request.getCode())) {
            return null;
        }
        MessageSendRecordExample example = new MessageSendRecordExample();
        MessageSendRecordExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(request.getCode())) {
            criteria.andCodeEqualTo(request.getCode());
        }
        if (Objects.nonNull(request.getId())) {
            criteria.andIdEqualTo(request.getId());
        }
        return Iterables.getFirst(mapper.selectByExample(example), null);
    }

}
