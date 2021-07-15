package com.example.healthmanage.ui.fragment.educationchild.response;

import java.util.List;

/**
 * desc:
 * date:2021/7/14 10:32
 * author:bWang
 */
public class BookListResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 200
     * message : 成功
     * data : [{"id":1,"bookName":"测试书籍","bookCover":"http://119.23.187.176:8886/profile/avatar/2021/04/28/e80e671f-f09d-4614-b6af-714d8e2cf891.png","bookType":1,"bookNumber":"2021年1期","bookYear":"2018","createTime":"2021-03-15 17:24:51","updateTime":"2021-04-28 17:01:16","isUsed":0,"articleCategoryId":null,"status":0}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 1
     * bookName : 测试书籍
     * bookCover : http://119.23.187.176:8886/profile/avatar/2021/04/28/e80e671f-f09d-4614-b6af-714d8e2cf891.png
     * bookType : 1
     * bookNumber : 2021年1期
     * bookYear : 2018
     * createTime : 2021-03-15 17:24:51
     * updateTime : 2021-04-28 17:01:16
     * isUsed : 0
     * articleCategoryId : null
     * status : 0
     */

    private List<DataBean> data;

    public Object getRequestId() {
        return requestId;
    }

    public void setRequestId(Object requestId) {
        this.requestId = requestId;
    }

    public Object getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(Object errorLog) {
        this.errorLog = errorLog;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String bookName;
        private String bookCover;
        private int bookType;
        private String bookNumber;
        private String bookYear;
        private String createTime;
        private String updateTime;
        private int isUsed;
        private Object articleCategoryId;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getBookCover() {
            return bookCover;
        }

        public void setBookCover(String bookCover) {
            this.bookCover = bookCover;
        }

        public int getBookType() {
            return bookType;
        }

        public void setBookType(int bookType) {
            this.bookType = bookType;
        }

        public String getBookNumber() {
            return bookNumber;
        }

        public void setBookNumber(String bookNumber) {
            this.bookNumber = bookNumber;
        }

        public String getBookYear() {
            return bookYear;
        }

        public void setBookYear(String bookYear) {
            this.bookYear = bookYear;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getIsUsed() {
            return isUsed;
        }

        public void setIsUsed(int isUsed) {
            this.isUsed = isUsed;
        }

        public Object getArticleCategoryId() {
            return articleCategoryId;
        }

        public void setArticleCategoryId(Object articleCategoryId) {
            this.articleCategoryId = articleCategoryId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
