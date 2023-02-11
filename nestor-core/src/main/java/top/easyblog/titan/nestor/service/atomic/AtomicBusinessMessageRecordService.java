package top.easyblog.titan.nestor.service.atomic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.nestor.dao.auto.mapper.BusinessMessageRecordMapper;

/**
 * @author: frank.huang
 * @date: 2023-02-04 19:45
 */
@Slf4j
@Service
public class AtomicBusinessMessageRecordService {

    @Autowired
    private BusinessMessageRecordMapper mapper;

}
