package top.easyblog.titan.nestor.converter;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import top.easyblog.titan.nestor.bean.MessageConfigBean;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfig;
import top.easyblog.titan.nestor.dao.auto.model.TemplateValueConfig;
import top.easyblog.titan.nestor.request.CreateMessageConfigRequest;
import top.easyblog.titan.nestor.request.CreateTemplateValueConfigRequest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-04T22:21:39+0800",
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
            messageConfig.setCode( request.getCode() );
            messageConfig.setType( request.getType() );
            messageConfig.setName( request.getName() );
        }
        messageConfig.setTemplateValueConfigId( templateValueConfigId );

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
}
