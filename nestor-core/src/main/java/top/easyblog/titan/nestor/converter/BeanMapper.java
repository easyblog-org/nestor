package top.easyblog.titan.nestor.converter;

import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import top.easyblog.titan.nestor.bean.MessageConfigBean;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfig;
import top.easyblog.titan.nestor.dao.auto.model.TemplateValueConfig;
import top.easyblog.titan.nestor.request.CreateMessageConfigRequest;
import top.easyblog.titan.nestor.request.CreateTemplateValueConfigRequest;

/**
 * @author: frank.huang
 * @date: 2023-02-04 20:13
 */
@Mapper(componentModel = "spring")
public interface BeanMapper {

    @Mapping(target = "templateValueConfigId", source = "templateValueConfigId")
    MessageConfig buildMessageConfig(CreateMessageConfigRequest request, Long templateValueConfigId);

    TemplateValueConfig buildTemplateConfig(CreateTemplateValueConfigRequest request);

    @Mappings({
            @Mapping(target = "templateValueConfigType", source = "templateValueConfig.type"),
            @Mapping(target = "expression", source = "templateValueConfig.expression"),
            @Mapping(target = "url", source = "templateValueConfig.url"),
            @Mapping(target = "type", source = "messageConfig.type"),
            @Mapping(target = "deleted", source = "messageConfig.deleted"),
            @Mapping(target = "createTime", expression = "java(messageConfig.getCreateTime().getTime())"),
            @Mapping(target = "updateTime", expression = "java(messageConfig.getUpdateTime().getTime())")
    })
    MessageConfigBean buildMessageConfigBean(MessageConfig messageConfig, TemplateValueConfig templateValueConfig);
}