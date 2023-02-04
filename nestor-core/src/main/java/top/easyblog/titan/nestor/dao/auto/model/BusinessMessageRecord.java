package top.easyblog.titan.nestor.dao.auto.model;

import java.util.Date;

public class BusinessMessageRecord {
    private Long id;

    private String businessId;

    private String businessModule;

    private String businessEvent;

    private Byte status;

    private Integer retryTimes;

    private String failReason;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    private String businessMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getBusinessModule() {
        return businessModule;
    }

    public void setBusinessModule(String businessModule) {
        this.businessModule = businessModule == null ? null : businessModule.trim();
    }

    public String getBusinessEvent() {
        return businessEvent;
    }

    public void setBusinessEvent(String businessEvent) {
        this.businessEvent = businessEvent == null ? null : businessEvent.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
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

    public String getBusinessMessage() {
        return businessMessage;
    }

    public void setBusinessMessage(String businessMessage) {
        this.businessMessage = businessMessage == null ? null : businessMessage.trim();
    }
}