package top.easyblog.titan.nestor.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import top.easyblog.titan.nestor.bean.MessageConfigBean;
import top.easyblog.titan.nestor.bean.MessageConfigRuleBean;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfig;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfigRule;
import top.easyblog.titan.nestor.dao.auto.model.TemplateValueConfig;
import top.easyblog.titan.nestor.request.*;

/**
 * @author: frank.huang
 * @date: 2023-02-04 20:13
 */
@Mapper(componentModel = "spring")
public interface BeanMapper {

    int MESSAGE_CONFIG_CODE_LEN = 8;

    @Mapping(target = "code", expression = "java(top.easyblog.titan.nestor.util.IdGenerator.getUUID(MESSAGE_CONFIG_CODE_LEN))")
    @Mapping(target = "templateValueConfigId", source = "templateValueConfigId")
    MessageConfig buildMessageConfig(CreateMessageConfigRequest request, Long templateValueConfigId);

    @Mapping(target = "id", source = "id")
    MessageConfig buildMessageConfig(UpdateMessageConfigRequest request, Long id);

    TemplateValueConfig buildTemplateConfig(CreateTemplateValueConfigRequest request);

    @Mapping(target = "id", source = "id")
    TemplateValueConfig buildTemplateConfig(UpdateTemplateValueConfigRequest request, Long id);

    @Mappings({
            @Mapping(target = "templateValueConfigType", source = "templateValueConfig.type"),
            @Mapping(target = "expression", source = "templateValueConfig.expression"),
            @Mapping(target = "url", source = "templateValueConfig.url"),
            @Mapping(target = "type", source = "messageConfig.type"),
            @Mapping(target = "deleted", source = "messageConfig.deleted"),
            @Mapping(target = "createTime", expression = "java(messageConfig.getCreateTime().getTime()/1000)"),
            @Mapping(target = "updateTime", expression = "java(messageConfig.getUpdateTime().getTime()/1000)")
    })
    MessageConfigBean buildMessageConfigBean(MessageConfig messageConfig, TemplateValueConfig templateValueConfig);

    @Mapping(target = "code", expression = "java(top.easyblog.titan.nestor.util.IdGenerator.getUUID(MESSAGE_CONFIG_CODE_LEN))")
    MessageConfigRule buildMessageConfigRule(CreateMessageConfigRuleRequest request);

    @Mapping(target = "id", source = "id")
    MessageConfigRule buildMessageConfigRule(UpdateMessageConfigRuleRequest request, Long id);

    @Mapping(target = "createTime", expression = "java(messageConfigRule.getCreateTime().getTime()/1000)")
    @Mapping(target = "updateTime", expression = "java(messageConfigRule.getUpdateTime().getTime()/1000)")
    MessageConfigRuleBean buildMessageConfigRuleBean(MessageConfigRule messageConfigRule);
}