package top.easyblog.titan.nestor.service.atomic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.nestor.dao.auto.mapper.MessageConfigMapper;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfig;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.response.NestorResultCode;

import java.util.Date;
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


    public void insertOne(MessageConfig record) {
        if(Objects.isNull(record)){
            throw new BusinessException(NestorResultCode.NULL_RECORD_NOT_ALLOW);
        }
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        messageConfigMapper.insertSelective(record);
    }
}
