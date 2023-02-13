package top.easyblog.titan.nestor.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.easyblog.titan.nestor.annotation.ResponseWrapper;
import top.easyblog.titan.nestor.context.MessageSendContext;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.processor.MessageProcessorContext;
import top.easyblog.titan.nestor.request.CreateMessageSendRecordRequest;
import top.easyblog.titan.nestor.response.NestorResultCode;
import top.easyblog.titan.nestor.service.MessageSendRecordService;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: frank.huang
 * @date: 2023-02-12 13:47
 */
@RequestMapping("/v1/message/send")
@RestController
public class MessageSendRecordController {

    @Autowired
    private MessageSendRecordService messageSendRecordService;


    @ResponseWrapper
    @PostMapping("/email")
    public MessageProcessorContext sendPlainEmail(@RequestBody @Valid CreateMessageSendRecordRequest request) {
        return messageSendRecordService.sendPlainEmail(request);
    }

    @ResponseWrapper
    @PostMapping("/attachment-email")
    public MessageProcessorContext sendAttachmentEmail(@RequestBody @Valid CreateMessageSendRecordRequest request,
                                                       @RequestParam("attachments") MultipartFile[] attachments) {
        List<InputStream> attachmentsList = Arrays.stream(attachments).map(multipartFile -> {
            try {
                return multipartFile.getInputStream();
            } catch (IOException e) {
                throw new BusinessException(NestorResultCode.SEND_MESSAGE_FAILED);
            }
        }).collect(Collectors.toList());
        request.setAttachments(attachmentsList);
        return messageSendRecordService.sendAttachmentEmail(request);
    }


}
