package top.easyblog.titan.nestor.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author: frank.huang
 * @date: 2023-02-04 19:58
 */
@Data
public class MessageConfigBean {
    private String code;

    private String type;

    private String name;

    private Byte templateValueConfigType;

    private String expression;

    private String url;

    private Boolean deleted;

    private Long createTime;

    private Long updateTime;
}
