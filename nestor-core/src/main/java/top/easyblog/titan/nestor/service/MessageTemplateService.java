package top.easyblog.titan.nestor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.nestor.request.CreateMessageTemplateRequest;
import top.easyblog.titan.nestor.service.atomic.AtomicMessageTemplateService;

/**
 * @author: frank.huang
 * @date: 2023-02-11 14:50
 */
@Slf4j
@Service
public class MessageTemplateService {

    @Autowired
    private AtomicMessageTemplateService atomicMessageTemplateService;


    public void createMessageTemplate(CreateMessageTemplateRequest request) {

    }
}
