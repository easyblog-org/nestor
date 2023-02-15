package top.easyblog.titan.nestor.parser;

import java.util.Map;

/**
 * @author: frank.huang
 * @date: 2023-02-15 20:51
 */
public interface TemplateParser {

    /**
     * 解析模板
     *
     * @param input     要解析的字符串，其中包含{@code ${name}}占位符。
     * @param variables 一个Map对象，其中包含每个占位符名称对应的值。
     * @return
     */
    String parse(String input, Map<String, String> variables);

}
