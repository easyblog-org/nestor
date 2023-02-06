package top.easyblog.titan.nestor.converter;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import top.easyblog.titan.nestor.bean.MessageConfigBean;
import top.easyblog.titan.nestor.bean.MessageConfigRuleBean;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfig;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfigRule;
import top.easyblog.titan.nestor.dao.auto.model.TemplateValueConfig;
import top.easyblog.titan.nestor.request.CreateMessageConfigRequest;
import top.easyblog.titan.nestor.request.CreateMessageConfigRuleRequest;
import top.easyblog.titan.nestor.request.CreateTemplateValueConfigRequest;
import top.easyblog.titan.nestor.request.UpdateMessageConfigRequest;
import top.easyblog.titan.nestor.request.UpdateMessageConfigRuleRequest;
import top.easyblog.titan.nestor.request.UpdateTemplateValueConfigRequest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-06T21:30:39+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
@Component
public class BeanMapperImpl implements BeanMapper {

    @Override
    public MessageConfig buildMessageConfig(CreateMessageConfigRequest request, Long templateValueConfigId) {
        if ( request == null && templateValueConfigId == null ) {
            return null;
        }

        MessageConfig messageConfig = new MessageConfig();

        if ( request != null ) {
            messageConfig.setType( request.getType() );
            messageConfig.setName( request.getName() );
        }
        messageConfig.setTemplateValueConfigId( templateValueConfigId );
        messageConfig.setCode( top.easyblog.titan.nestor.util.IdGenerator.getUUID(MESSAGE_CONFIG_CODE_LEN) );

        return messageConfig;
    }

    @Override
    public MessageConfig buildMessageConfig(UpdateMessageConfigRequest request, Long id) {
        if ( request == null && id == null ) {
            return null;
        }

        MessageConfig messageConfig = new MessageConfig();

        if ( request != null ) {
            messageConfig.setName( request.getName() );
            messageConfig.setDeleted( request.getDeleted() );
        }
        messageConfig.setId( id );

        return messageConfig;
    }

    @Override
    public TemplateValueConfig buildTemplateConfig(CreateTemplateValueConfigRequest request) {
        if ( request == null ) {
            return null;
        }

        TemplateValueConfig templateValueConfig = new TemplateValueConfig();

        templateValueConfig.setType( request.getType() );
        templateValueConfig.setExpression( request.getExpression() );
        templateValueConfig.setUrl( request.getUrl() );

        return templateValueConfig;
    }

    @Override
    public TemplateValueConfig buildTemplateConfig(UpdateTemplateValueConfigRequest request, Long id) {
        if ( request == null && id == null ) {
            return null;
        }

        TemplateValueConfig templateValueConfig = new TemplateValueConfig();

        if ( request != null ) {
            templateValueConfig.setType( request.getType() );
            templateValueConfig.setExpression( request.getExpression() );
            templateValueConfig.setUrl( request.getUrl() );
            templateValueConfig.setDeleted( request.getDeleted() );
        }
        templateValueConfig.setId( id );

        return templateValueConfig;
    }

    @Override
    public MessageConfigBean buildMessageConfigBean(MessageConfig messageConfig, TemplateValueConfig templateValueConfig) {
        if ( messageConfig == null && templateValueConfig == null ) {
            return null;
        }

        MessageConfigBean messageConfigBean = new MessageConfigBean();

        if ( messageConfig != null ) {
            messageConfigBean.setType( messageConfig.getType() );
            messageConfigBean.setDeleted( messageConfig.getDeleted() );
            messageConfigBean.setCode( messageConfig.getCode() );
            messageConfigBean.setName( messageConfig.getName() );
        }
        if ( templateValueConfig != null ) {
            messageConfigBean.setTemplateValueConfigType( templateValueConfig.getType() );
            messageConfigBean.setExpression( templateValueConfig.getExpression() );
            messageConfigBean.setUrl( templateValueConfig.getUrl() );
        }
        messageConfigBean.setCreateTime( messageConfig.getCreateTime().getTime() );
        messageConfigBean.setUpdateTime( messageConfig.getUpdateTime().getTime() );

        return messageConfigBean;
    }

    @Override
    public MessageConfigRule buildMessageConfigRule(CreateMessageConfigRuleRequest request) {
        if ( request == null ) {
            return null;
        }

        MessageConfigRule messageConfigRule = new MessageConfigRule();

        messageConfigRule.setBusinessModule( request.getBusinessModule() );
        messageConfigRule.setBusinessEvent( request.getBusinessEvent() );
        messageConfigRule.setTemplateCode( request.getTemplateCode() );
        messageConfigRule.setGroup( request.getGroup() );
        messageConfigRule.setPriority( request.getPriority() );
        messageConfigRule.setChannel( request.getChannel() );
        messageConfigRule.setConfigIds( request.getConfigIds() );

        messageConfigRule.setCode( top.easyblog.titan.nestor.util.IdGenerator.getUUID(MESSAGE_CONFIG_CODE_LEN) );

        return messageConfigRule;
    }

    @Override
    public MessageConfigRule buildMessageConfigRule(UpdateMessageConfigRuleRequest request, Long id) {
        if ( request == null && id == null ) {
            return null;
        }

        MessageConfigRule messageConfigRule = new MessageConfigRule();

        if ( request != null ) {
            messageConfigRule.setBusinessModule( request.getBusinessModule() );
            messageConfigRule.setBusinessEvent( request.getBusinessEvent() );
            messageConfigRule.setTemplateCode( request.getTemplateCode() );
            messageConfigRule.setGroup( request.getGroup() );
            messageConfigRule.setPriority( request.getPriority() );
            messageConfigRule.setChannel( request.getChannel() );
            messageConfigRule.setConfigIds( request.getConfigIds() );
        }
        messageConfigRule.setId( id );

        return messageConfigRule;
    }

    @Override
    public MessageConfigRuleBean buildMessageConfigRuleBean(MessageConfigRule messageConfigRule) {
        if ( messageConfigRule == null ) {
            return null;
        }

        MessageConfigRuleBean messageConfigRuleBean = new MessageConfigRuleBean();

        messageConfigRuleBean.setId( messageConfigRule.getId() );
        messageConfigRuleBean.setCode( messageConfigRule.getCode() );
        messageConfigRuleBean.setBusinessModule( messageConfigRule.getBusinessModule() );
        messageConfigRuleBean.setBusinessEvent( messageConfigRule.getBusinessEvent() );
        messageConfigRuleBean.setTemplateCode( messageConfigRule.getTemplateCode() );
        messageConfigRuleBean.setGroup( messageConfigRule.getGroup() );
        messageConfigRuleBean.setPriority( messageConfigRule.getPriority() );
        messageConfigRuleBean.setChannel( messageConfigRule.getChannel() );
        messageConfigRuleBean.setConfigIds( messageConfigRule.getConfigIds() );
        messageConfigRuleBean.setDeleted( messageConfigRule.getDeleted() );
        messageConfigRuleBean.setCreateTime( messageConfigRule.getCreateTime() );
        messageConfigRuleBean.setUpdateTime( messageConfigRule.getUpdateTime() );

        return messageConfigRuleBean;
    }
}
