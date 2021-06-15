package com.example.healthmanage.ui.activity.delegate.response;

public class DelegateBean {

    /**
     * addr : 任务地点
     * content : 委派内容
     * createUserId : 当前登录的用户ID
     * date : 服务日期
     * systemUserId : 委派给谁的ID
     * time : 具体时间
     * userId : 服务对象的ID
     */

    private String addr;
    private String content;
    private String createUserId;
    private String date;
    private String systemUserId;
    private String time;
    private String userId;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(String systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
