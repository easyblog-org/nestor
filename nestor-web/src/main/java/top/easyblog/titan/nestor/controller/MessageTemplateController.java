package top.easyblog.titan.nestor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.easyblog.titan.nestor.annotation.ResponseWrapper;
import top.easyblog.titan.nestor.request.CreateMessageTemplateRequest;
import top.easyblog.titan.nestor.service.MessageTemplateService;

/**
 * @author: frank.huang
 * @date: 2023-02-11 14:51
 */
@RequestMapping("/v1")
@RestController
public class MessageTemplateController {

    @Autowired
    private MessageTemplateService messageTemplateService;

    @ResponseWrapper
    @GetMapping("/template")
    public void  createMessageTemplate(@RequestBody CreateMessageTemplateRequest request){
        messageTemplateService.createMessageTemplate(request);
    }

}
