package com.example.healthmanage.ui.activity.famousDoctorHall.response;

import java.util.List;

/**
 * desc:
 * date:2021/6/28 15:45
 * author:bWang
 */
public class DepartMentResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":3,"parentId":0,"name":"儿科","isUsed":0,"sort":20,"createTime":null,"updateTime":null,"list":[{"id":2,"parentId":3,"name":"儿童内科","isUsed":1,"sort":20,"createTime":null,"updateTime":1617346583000,"list":null},{"id":4,"parentId":3,"name":"儿童保健科","isUsed":0,"sort":10,"createTime":null,"updateTime":1617346591000,"list":null}]},{"id":1,"parentId":0,"name":"内科","isUsed":0,"sort":1,"createTime":null,"updateTime":1617344185000,"list":[{"id":5,"parentId":1,"name":"内科","isUsed":0,"sort":1,"createTime":1617344270000,"updateTime":null,"list":null},{"id":6,"parentId":1,"name":"神经内科","isUsed":0,"sort":4,"createTime":1617344554000,"updateTime":1617344810000,"list":null},{"id":7,"parentId":1,"name":"消化内科","isUsed":0,"sort":2,"createTime":1617344788000,"updateTime":null,"list":null},{"id":8,"parentId":1,"name":"普通内科","isUsed":0,"sort":3,"createTime":1617344802000,"updateTime":null,"list":null},{"id":9,"parentId":1,"name":"内分泌科","isUsed":0,"sort":5,"createTime":1617344823000,"updateTime":null,"list":null},{"id":10,"parentId":1,"name":"心血管科","isUsed":0,"sort":6,"createTime":1617344846000,"updateTime":null,"list":null}]},{"id":12,"parentId":0,"name":"骨科","isUsed":0,"sort":20,"createTime":1617345446000,"updateTime":null,"list":[{"id":22,"parentId":12,"name":"骨外科","isUsed":0,"sort":32,"createTime":1617346303000,"updateTime":null,"list":null},{"id":23,"parentId":12,"name":"脊柱外科","isUsed":0,"sort":221,"createTime":1617346317000,"updateTime":null,"list":null},{"id":24,"parentId":12,"name":"骨关节科","isUsed":0,"sort":2,"createTime":1617346327000,"updateTime":null,"list":null}]},{"id":11,"parentId":0,"name":"外科","isUsed":0,"sort":1,"createTime":1617345129000,"updateTime":null,"list":[{"id":25,"parentId":11,"name":"泌尿外科","isUsed":0,"sort":12,"createTime":1617346338000,"updateTime":null,"list":null},{"id":26,"parentId":11,"name":"普通外科","isUsed":0,"sort":22,"createTime":1617346348000,"updateTime":null,"list":null},{"id":27,"parentId":11,"name":"神经外科","isUsed":0,"sort":12,"createTime":1617346358000,"updateTime":null,"list":null},{"id":28,"parentId":11,"name":"肛肠科","isUsed":0,"sort":2,"createTime":1617346369000,"updateTime":null,"list":null},{"id":29,"parentId":11,"name":"血管瘤专科","isUsed":0,"sort":12,"createTime":1617346386000,"updateTime":null,"list":null},{"id":30,"parentId":11,"name":"血管外科","isUsed":0,"sort":12,"createTime":1617346395000,"updateTime":null,"list":null},{"id":31,"parentId":11,"name":"疼痛/麻醉科","isUsed":0,"sort":2,"createTime":1617346411000,"updateTime":null,"list":null}]},{"id":13,"parentId":0,"name":"妇产科","isUsed":0,"sort":3,"createTime":1617346203000,"updateTime":null,"list":[{"id":32,"parentId":13,"name":"妇科","isUsed":0,"sort":12,"createTime":1617346424000,"updateTime":null,"list":null},{"id":33,"parentId":13,"name":"产科","isUsed":0,"sort":12,"createTime":1617346434000,"updateTime":null,"list":null}]},{"id":14,"parentId":0,"name":"肿瘤科","isUsed":0,"sort":7,"createTime":1617346214000,"updateTime":null,"list":[{"id":34,"parentId":14,"name":"中医肿瘤科","isUsed":0,"sort":12,"createTime":1617346464000,"updateTime":null,"list":null}]},{"id":20,"parentId":0,"name":"眼科","isUsed":0,"sort":2,"createTime":1617346278000,"updateTime":null,"list":[{"id":35,"parentId":20,"name":"眼科综合","isUsed":0,"sort":12,"createTime":1617346474000,"updateTime":null,"list":null}]},{"id":15,"parentId":0,"name":"康复医学","isUsed":0,"sort":33,"createTime":1617346223000,"updateTime":null,"list":[{"id":36,"parentId":15,"name":"运动医学科","isUsed":0,"sort":12,"createTime":1617346486000,"updateTime":null,"list":null}]},{"id":16,"parentId":0,"name":"男科","isUsed":0,"sort":23,"createTime":1617346231000,"updateTime":null,"list":[{"id":37,"parentId":16,"name":"男科","isUsed":0,"sort":12,"createTime":1617346500000,"updateTime":null,"list":null}]},{"id":17,"parentId":0,"name":"医技科","isUsed":0,"sort":2,"createTime":1617346243000,"updateTime":null,"list":[{"id":38,"parentId":17,"name":"检验科","isUsed":0,"sort":12,"createTime":1617346514000,"updateTime":null,"list":null}]},{"id":18,"parentId":0,"name":"重症医学科","isUsed":0,"sort":2,"createTime":1617346256000,"updateTime":null,"list":[{"id":39,"parentId":18,"name":"重症科","isUsed":0,"sort":12,"createTime":1617346535000,"updateTime":null,"list":null}]},{"id":19,"parentId":0,"name":"口腔科","isUsed":0,"sort":22,"createTime":1617346265000,"updateTime":null,"list":[{"id":40,"parentId":19,"name":"口腔科综合","isUsed":0,"sort":12,"createTime":1617346547000,"updateTime":null,"list":null}]},{"id":21,"parentId":0,"name":"耳鼻喉科","isUsed":0,"sort":4,"createTime":1617346284000,"updateTime":null,"list":[{"id":41,"parentId":21,"name":"耳鼻喉头颈外科","isUsed":0,"sort":12,"createTime":1617346563000,"updateTime":null,"list":null}]}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 3
     * parentId : 0
     * name : 儿科
     * isUsed : 0
     * sort : 20
     * createTime : null
     * updateTime : null
     * list : [{"id":2,"parentId":3,"name":"儿童内科","isUsed":1,"sort":20,"createTime":null,"updateTime":1617346583000,"list":null},{"id":4,"parentId":3,"name":"儿童保健科","isUsed":0,"sort":10,"createTime":null,"updateTime":1617346591000,"list":null}]
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
        private int parentId;
        private String name;
        private int isUsed;
        private int sort;
        private Object createTime;
        private Object updateTime;

        public DataBean(String name) {
            this.name = name;
        }

        /**
         * id : 2
         * parentId : 3
         * name : 儿童内科
         * isUsed : 1
         * sort : 20
         * createTime : null
         * updateTime : 1617346583000
         * list : null
         */



        private List<ListBean> list;
        private boolean isSelect;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public static class ListBean {
            private int id;
            private int parentId;
            private String name;
            private int isUsed;
            private int sort;
            private Object createTime;
            private long updateTime;
            private Object list;
            private boolean isSelect;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public Object getList() {
                return list;
            }

            public void setList(Object list) {
                this.list = list;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }
        }
    }
}
