package top.easyblog.titan.nestor.parser.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.easyblog.titan.nestor.parser.TemplateParser;
import top.easyblog.titan.nestor.parser.impl.StringTemplateParser;

/**
 * @author: frank.huang
 * @date: 2023-02-15 21:35
 */
@Configuration
public class TemplateParserAutoConfig {


    @Bean
    @ConditionalOnClass(value = TemplateParser.class)
    @ConditionalOnMissingBean(value = TemplateParser.class)
    public StringTemplateParser templateParser() {
        return new StringTemplateParser();
    }

}
