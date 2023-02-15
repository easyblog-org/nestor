package top.easyblog.titan.nestor.parser.impl;


import top.easyblog.titan.nestor.parser.TemplateParser;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析${name}样式参数并替换为对应参数
 *
 * @author: frank.huang
 * @date: 2023-02-15 21:21
 */
public class StringTemplateParser implements TemplateParser {
    private final String pattern;

    public StringTemplateParser() {
        this("\\$\\{(.+?)\\}");
    }

    public StringTemplateParser(String pattern) {
        this.pattern = pattern;
    }

    public String parse(String input, Map<String, String> variables) {
        Pattern expr = Pattern.compile(pattern);
        Matcher matcher = expr.matcher(input);
        StringBuffer output = new StringBuffer();

        while (matcher.find()) {
            String variableName = matcher.group(1);
            String variableValue = variables.get(variableName);
            if (variableValue == null) {
                variableValue = "";
            }
            matcher.appendReplacement(output, variableValue);
        }

        matcher.appendTail(output);
        return output.toString();
    }
}

