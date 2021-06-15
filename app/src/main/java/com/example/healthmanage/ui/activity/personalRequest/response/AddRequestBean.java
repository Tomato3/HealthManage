package com.example.healthmanage.ui.activity.personalRequest.response;

public class AddRequestBean {

    /**
     * content : 请求内容
     * systemUserId : 78
     * type : 法律
     */

    private String content;
    private int systemUserId;
    private String type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(int systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
