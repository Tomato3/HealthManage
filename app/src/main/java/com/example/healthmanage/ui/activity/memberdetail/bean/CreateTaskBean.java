package com.example.healthmanage.ui.activity.memberdetail.bean;

import java.util.List;

public class CreateTaskBean {

    /**
     * content : string
     * systemUserId : 0
     * userId : 0
     * list : [{"url":"string"}]
     */

    private String content;
    private int systemUserId;
    private int userId;
    /**
     * url : string
     */

    private List<ListBean> list;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String url;

        public ListBean(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
