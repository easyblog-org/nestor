package top.easyblog.titan.nestor.service.atomic;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.nestor.bean.MessageConfigBean;
import top.easyblog.titan.nestor.dao.auto.mapper.MessageConfigMapper;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfig;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfigExample;
import top.easyblog.titan.nestor.dao.custom.mapper.MyMessageConfigMapper;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.request.QueryMessageConfigRequest;
import top.easyblog.titan.nestor.request.QueryMessageConfigsRequest;
import top.easyblog.titan.nestor.response.NestorResultCode;
import top.easyblog.titan.nestor.util.JsonUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author: frank.huang
 * @date: 2023-02-04 19:44
 */
@Slf4j
@Service
public class AtomicMessageConfigService {

    @Autowired
    private MessageConfigMapper messageConfigMapper;

    @Autowired
    private MyMessageConfigMapper myMessageConfigMapper;

    public void insertOne(MessageConfig record) {
        if (Objects.isNull(record)) {
            throw new BusinessException(NestorResultCode.DB_OPERATE_RECORD_NOT_ALLOW_NULL);
        }
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        messageConfigMapper.insertSelective(record);
        log.info("[DB]Create message config successfully!Details==>{}", JsonUtils.toJSONString(record));
    }

    public MessageConfig queryByRequest(QueryMessageConfigRequest request) {
        if (StringUtils.isEmpty(request.getCode())) {
            return null;
        }
        MessageConfigExample example = new MessageConfigExample();
        MessageConfigExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isEmpty(request.getCode())) {
            criteria.andCodeEqualTo(request.getCode());
        }
        return Iterables.getFirst(messageConfigMapper.selectByExample(example), null);
    }

    public long countByRequest(QueryMessageConfigsRequest request){
        if(Objects.isNull(request)){
            return NumberUtils.LONG_ZERO;
        }
        return myMessageConfigMapper.countByRequest(request);
    }

    public List<MessageConfigBean> queryListByRequest(QueryMessageConfigsRequest request){
        if(Objects.isNull(request)){
            return Collections.emptyList();
        }
        return myMessageConfigMapper.selectListByRequest(request);
    }

    public void updateByPKSelective(MessageConfig record) {
        if (Objects.isNull(record)) {
            throw new BusinessException(NestorResultCode.DB_OPERATE_RECORD_NOT_ALLOW_NULL);
        }
        record.setUpdateTime(new Date());
        messageConfigMapper.updateByPrimaryKey(record);
        log.info("[DB]Update message config successfully!Details==>{}", JsonUtils.toJSONString(record));
    }
}
