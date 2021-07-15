package com.example.healthmanage.ui.fragment.educationchild.response;

import java.util.List;

/**
 * desc:
 * date:2021/7/15 9:56
 * author:bWang
 */
public class BookMenuResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 200
     * message : 成功
     * data : [{"id":45,"name":"卷首语","bookId":15,"parentId":0,"createTime":"2021-06-18 16:12:48","updateTime":null,"isUsed":0,"sort":0,"list":[{"id":18,"bookId":15,"bookCatalogId":45,"name":"金秋时光","content":null,"createTime":"2021-06-18 16:13:41","updateTime":null},{"id":19,"bookId":15,"bookCatalogId":45,"name":"文章标题测试1","content":null,"createTime":"2021-06-22 11:21:30","updateTime":null}]}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 45
     * name : 卷首语
     * bookId : 15
     * parentId : 0
     * createTime : 2021-06-18 16:12:48
     * updateTime : null
     * isUsed : 0
     * sort : 0
     * list : [{"id":18,"bookId":15,"bookCatalogId":45,"name":"金秋时光","content":null,"createTime":"2021-06-18 16:13:41","updateTime":null},{"id":19,"bookId":15,"bookCatalogId":45,"name":"文章标题测试1","content":null,"createTime":"2021-06-22 11:21:30","updateTime":null}]
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
        private String name;
        private int bookId;
        private int parentId;
        private String createTime;
        private Object updateTime;
        private int isUsed;
        private int sort;
        /**
         * id : 18
         * bookId : 15
         * bookCatalogId : 45
         * name : 金秋时光
         * content : null
         * createTime : 2021-06-18 16:13:41
         * updateTime : null
         */

        private List<ListBean> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public int getIsUsed() {
            return isUsed;
        }

        public void setIsUsed(int isUsed) {
            this.isUsed = isUsed;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private int bookId;
            private int bookCatalogId;
            private String name;
            private Object content;
            private String createTime;
            private Object updateTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getBookId() {
                return bookId;
            }

            public void setBookId(int bookId) {
                this.bookId = bookId;
            }

            public int getBookCatalogId() {
                return bookCatalogId;
            }

            public void setBookCatalogId(int bookCatalogId) {
                this.bookCatalogId = bookCatalogId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
