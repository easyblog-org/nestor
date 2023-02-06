package top.easyblog.titan.nestor.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author: frank.huang
 * @date: 2023-02-06 19:29
 */
@Data
public class MessageConfigRuleBean {
    private Long id;

    private String code;

    private String businessModule;

    private String businessEvent;

    private String templateCode;

    private String group;

    private Integer priority;

    private Byte channel;

    private String configIds;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;
}
