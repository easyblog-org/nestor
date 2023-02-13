package top.easyblog.titan.nestor.dao.auto.model;

import java.util.Date;

public class MessageTemplate {
    private Long id;

    private String templateCode;

    private String name;

    private Byte msgStatus;

    private String expectPushTime;

    private Byte idType;

    private Byte sendChannel;

    private Byte msgType;

    private Byte shieldType;

    private String msgContent;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode == null ? null : templateCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Byte msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getExpectPushTime() {
        return expectPushTime;
    }

    public void setExpectPushTime(String expectPushTime) {
        this.expectPushTime = expectPushTime == null ? null : expectPushTime.trim();
    }

    public Byte getIdType() {
        return idType;
    }

    public void setIdType(Byte idType) {
        this.idType = idType;
    }

    public Byte getSendChannel() {
        return sendChannel;
    }

    public void setSendChannel(Byte sendChannel) {
        this.sendChannel = sendChannel;
    }

    public Byte getMsgType() {
        return msgType;
    }

    public void setMsgType(Byte msgType) {
        this.msgType = msgType;
    }

    public Byte getShieldType() {
        return shieldType;
    }

    public void setShieldType(Byte shieldType) {
        this.shieldType = shieldType;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}