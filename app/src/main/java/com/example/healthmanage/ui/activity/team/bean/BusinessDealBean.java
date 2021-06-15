package com.example.healthmanage.ui.activity.team.bean;

import java.util.List;

public class BusinessDealBean {

    /**
     * id : 1
     * status : 1
     * details : 完成描述
     * list : [{"url":"图片地址"}]
     */

    private int id;
    private int status;
    private String details;
    /**
     * url : 图片地址
     */

    private List<ListBean> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
