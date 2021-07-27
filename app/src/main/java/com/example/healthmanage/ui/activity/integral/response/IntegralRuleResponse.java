package com.example.healthmanage.ui.activity.integral.response;

import java.util.List;

/**
 * desc:
 * date:2021/7/15 15:39
 * author:bWang
 */
public class IntegralRuleResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":6,"name":"行为积分","type":2,"state":0,"appRuleList":[{"id":22,"ruleTypeId":6,"articleCategoryId":null,"name":"登录APP","points":10,"timeLimit":null,"dailyLimit":1,"behaviorCode":null,"createTime":"2021-07-09 16:08:29","updateTime":null,"completeNumber":1},{"id":23,"ruleTypeId":6,"articleCategoryId":null,"name":"预约参加论坛会议","points":10,"timeLimit":null,"dailyLimit":2,"behaviorCode":null,"createTime":"2021-07-09 16:09:22","updateTime":null,"completeNumber":0},{"id":24,"ruleTypeId":6,"articleCategoryId":null,"name":"购买专家讲座视频","points":10,"timeLimit":null,"dailyLimit":2,"behaviorCode":null,"createTime":"2021-07-09 16:10:06","updateTime":null,"completeNumber":0},{"id":25,"ruleTypeId":6,"articleCategoryId":null,"name":"购买在职教育视频","points":10,"timeLimit":null,"dailyLimit":2,"behaviorCode":null,"createTime":"2021-07-09 16:14:57","updateTime":null,"completeNumber":0},{"id":26,"ruleTypeId":6,"articleCategoryId":null,"name":"购买学历提升视频","points":10,"timeLimit":null,"dailyLimit":2,"behaviorCode":null,"createTime":"2021-07-09 16:15:17","updateTime":null,"completeNumber":0},{"id":27,"ruleTypeId":6,"articleCategoryId":null,"name":"回复患者咨询","points":10,"timeLimit":null,"dailyLimit":2,"behaviorCode":null,"createTime":"2021-07-09 16:15:33","updateTime":null,"completeNumber":0}]},{"id":5,"name":"学习积分","type":2,"state":0,"appRuleList":[{"id":18,"ruleTypeId":5,"articleCategoryId":113,"name":"阅读期刊杂志","points":10,"timeLimit":10,"dailyLimit":3,"behaviorCode":null,"createTime":"2021-07-09 16:05:42","updateTime":null,"completeNumber":0},{"id":19,"ruleTypeId":5,"articleCategoryId":null,"name":"观看专家讲座视频","points":10,"timeLimit":10,"dailyLimit":3,"behaviorCode":null,"createTime":"2021-07-09 16:06:38","updateTime":null,"completeNumber":0},{"id":20,"ruleTypeId":5,"articleCategoryId":null,"name":"观看在职教育视频","points":10,"timeLimit":10,"dailyLimit":3,"behaviorCode":null,"createTime":"2021-07-09 16:07:14","updateTime":null,"completeNumber":0},{"id":21,"ruleTypeId":5,"articleCategoryId":null,"name":"观看学历提升视频","points":10,"timeLimit":10,"dailyLimit":3,"behaviorCode":null,"createTime":"2021-07-09 16:07:39","updateTime":null,"completeNumber":0}]},{"id":4,"name":"新人任务","type":2,"state":0,"appRuleList":[{"id":16,"ruleTypeId":4,"articleCategoryId":null,"name":"完成资格认证","points":10,"timeLimit":null,"dailyLimit":1,"behaviorCode":null,"createTime":"2021-07-09 16:04:49","updateTime":null,"completeNumber":0},{"id":17,"ruleTypeId":4,"articleCategoryId":null,"name":"注册使用APP","points":10,"timeLimit":null,"dailyLimit":1,"behaviorCode":null,"createTime":"2021-07-09 16:05:04","updateTime":null,"completeNumber":1}]}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 6
     * name : 行为积分
     * type : 2
     * state : 0
     * appRuleList : [{"id":22,"ruleTypeId":6,"articleCategoryId":null,"name":"登录APP","points":10,"timeLimit":null,"dailyLimit":1,"behaviorCode":null,"createTime":"2021-07-09 16:08:29","updateTime":null,"completeNumber":1},{"id":23,"ruleTypeId":6,"articleCategoryId":null,"name":"预约参加论坛会议","points":10,"timeLimit":null,"dailyLimit":2,"behaviorCode":null,"createTime":"2021-07-09 16:09:22","updateTime":null,"completeNumber":0},{"id":24,"ruleTypeId":6,"articleCategoryId":null,"name":"购买专家讲座视频","points":10,"timeLimit":null,"dailyLimit":2,"behaviorCode":null,"createTime":"2021-07-09 16:10:06","updateTime":null,"completeNumber":0},{"id":25,"ruleTypeId":6,"articleCategoryId":null,"name":"购买在职教育视频","points":10,"timeLimit":null,"dailyLimit":2,"behaviorCode":null,"createTime":"2021-07-09 16:14:57","updateTime":null,"completeNumber":0},{"id":26,"ruleTypeId":6,"articleCategoryId":null,"name":"购买学历提升视频","points":10,"timeLimit":null,"dailyLimit":2,"behaviorCode":null,"createTime":"2021-07-09 16:15:17","updateTime":null,"completeNumber":0},{"id":27,"ruleTypeId":6,"articleCategoryId":null,"name":"回复患者咨询","points":10,"timeLimit":null,"dailyLimit":2,"behaviorCode":null,"createTime":"2021-07-09 16:15:33","updateTime":null,"completeNumber":0}]
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
        private int type;
        private int state;
        /**
         * id : 22
         * ruleTypeId : 6
         * articleCategoryId : null
         * name : 登录APP
         * points : 10
         * timeLimit : null
         * dailyLimit : 1
         * behaviorCode : null
         * createTime : 2021-07-09 16:08:29
         * updateTime : null
         * completeNumber : 1
         */

        private List<AppRuleListBean> appRuleList;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public List<AppRuleListBean> getAppRuleList() {
            return appRuleList;
        }

        public void setAppRuleList(List<AppRuleListBean> appRuleList) {
            this.appRuleList = appRuleList;
        }

        public static class AppRuleListBean {
            private int id;
            private int ruleTypeId;
            private Object articleCategoryId;
            private String name;
            private int points;
            private String timeLimit;
            private int dailyLimit;
            private Object behaviorCode;
            private String createTime;
            private Object updateTime;
            private int completeNumber;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRuleTypeId() {
                return ruleTypeId;
            }

            public void setRuleTypeId(int ruleTypeId) {
                this.ruleTypeId = ruleTypeId;
            }

            public Object getArticleCategoryId() {
                return articleCategoryId;
            }

            public void setArticleCategoryId(Object articleCategoryId) {
                this.articleCategoryId = articleCategoryId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPoints() {
                return points;
            }

            public void setPoints(int points) {
                this.points = points;
            }

            public String getTimeLimit() {
                return timeLimit;
            }

            public void setTimeLimit(String timeLimit) {
                this.timeLimit = timeLimit;
            }

            public int getDailyLimit() {
                return dailyLimit;
            }

            public void setDailyLimit(int dailyLimit) {
                this.dailyLimit = dailyLimit;
            }

            public Object getBehaviorCode() {
                return behaviorCode;
            }

            public void setBehaviorCode(Object behaviorCode) {
                this.behaviorCode = behaviorCode;
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

            public int getCompleteNumber() {
                return completeNumber;
            }

            public void setCompleteNumber(int completeNumber) {
                this.completeNumber = completeNumber;
            }
        }
    }
}
