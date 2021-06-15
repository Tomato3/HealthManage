package com.example.healthmanage.ui.activity.consultation.response;

import java.io.Serializable;
import java.util.List;

/**
 * 问诊列表response
 */
public class ConsultationListResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":59,"briefHistory":"对局","createUserId":"34","examineTime":null,"examineId":"19","patientInitialDecide":"炖白菜","examineGoalRequest":"超可能","startTime":"Sat Apr 17 11:10:00 CST 2021","endTime":"Sat Apr 17 12:10:00 CST 2021","roomId":"145993918119937","startTimeQuery":1618629000000,"endTimeQuery":1618632600000,"status":0,"examinePlan":null,"appPatientPictures":null,"insertPic":null,"appNinePictures":[{"patientPic":"http://192.168.199.245:8080/picture/6d405a6a516c4f86739441fb497b5516.png","patientId":59,"id":null}],"createUserName":{"phone":"18852407703","sysNickName":"178556","departmentName":"外科","rank":"健康管理师","sysId":34},"exmineName":[{"phone":"13584869132","sysNickName":"测试健康管理师19","departmentName":"骨科","rank":"健康管理师","sysId":19}]}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 59
     * briefHistory : 对局
     * createUserId : 34
     * examineTime : null
     * examineId : 19
     * patientInitialDecide : 炖白菜
     * examineGoalRequest : 超可能
     * startTime : Sat Apr 17 11:10:00 CST 2021
     * endTime : Sat Apr 17 12:10:00 CST 2021
     * roomId : 145993918119937
     * startTimeQuery : 1618629000000
     * endTimeQuery : 1618632600000
     * status : 0
     * examinePlan : null
     * appPatientPictures : null
     * insertPic : null
     * appNinePictures : [{"patientPic":"http://192.168.199.245:8080/picture/6d405a6a516c4f86739441fb497b5516.png","patientId":59,"id":null}]
     * createUserName : {"phone":"18852407703","sysNickName":"178556","departmentName":"外科","rank":"健康管理师","sysId":34}
     * exmineName : [{"phone":"13584869132","sysNickName":"测试健康管理师19","departmentName":"骨科","rank":"健康管理师","sysId":19}]
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

    public static class DataBean implements Serializable {
        private int id;
        private String briefHistory;
        private String createUserId;
        private Object examineTime;
        private String examineId;
        private String patientInitialDecide;
        private String examineGoalRequest;
        private String startTime;
        private String endTime;
        private String roomId;
        private long startTimeQuery;
        private long endTimeQuery;
        private int status;
        private String examinePlan;
        private Object appPatientPictures;
        private Object insertPic;
        /**
         * phone : 18852407703
         * sysNickName : 178556
         * departmentName : 外科
         * rank : 健康管理师
         * sysId : 34
         */

        private CreateUserNameBean createUserName;
        /**
         * patientPic : http://192.168.199.245:8080/picture/6d405a6a516c4f86739441fb497b5516.png
         * patientId : 59
         * id : null
         */

        private List<AppNinePicturesBean> appNinePictures;
        /**
         * phone : 13584869132
         * sysNickName : 测试健康管理师19
         * departmentName : 骨科
         * rank : 健康管理师
         * sysId : 19
         */

        private List<ExmineNameBean> exmineName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBriefHistory() {
            return briefHistory;
        }

        public void setBriefHistory(String briefHistory) {
            this.briefHistory = briefHistory;
        }

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public Object getExamineTime() {
            return examineTime;
        }

        public void setExamineTime(Object examineTime) {
            this.examineTime = examineTime;
        }

        public String getExamineId() {
            return examineId;
        }

        public void setExamineId(String examineId) {
            this.examineId = examineId;
        }

        public String getPatientInitialDecide() {
            return patientInitialDecide;
        }

        public void setPatientInitialDecide(String patientInitialDecide) {
            this.patientInitialDecide = patientInitialDecide;
        }

        public String getExamineGoalRequest() {
            return examineGoalRequest;
        }

        public void setExamineGoalRequest(String examineGoalRequest) {
            this.examineGoalRequest = examineGoalRequest;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public long getStartTimeQuery() {
            return startTimeQuery;
        }

        public void setStartTimeQuery(long startTimeQuery) {
            this.startTimeQuery = startTimeQuery;
        }

        public long getEndTimeQuery() {
            return endTimeQuery;
        }

        public void setEndTimeQuery(long endTimeQuery) {
            this.endTimeQuery = endTimeQuery;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getExaminePlan() {
            return examinePlan;
        }

        public void setExaminePlan(String examinePlan) {
            this.examinePlan = examinePlan;
        }

        public Object getAppPatientPictures() {
            return appPatientPictures;
        }

        public void setAppPatientPictures(Object appPatientPictures) {
            this.appPatientPictures = appPatientPictures;
        }

        public Object getInsertPic() {
            return insertPic;
        }

        public void setInsertPic(Object insertPic) {
            this.insertPic = insertPic;
        }

        public CreateUserNameBean getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(CreateUserNameBean createUserName) {
            this.createUserName = createUserName;
        }

        public List<AppNinePicturesBean> getAppNinePictures() {
            return appNinePictures;
        }

        public void setAppNinePictures(List<AppNinePicturesBean> appNinePictures) {
            this.appNinePictures = appNinePictures;
        }

        public List<ExmineNameBean> getExmineName() {
            return exmineName;
        }

        public void setExmineName(List<ExmineNameBean> exmineName) {
            this.exmineName = exmineName;
        }

        public static class CreateUserNameBean implements Serializable{
            private String phone;
            private String sysNickName;
            private String departmentName;
            private String name;
            private String rank;
            private int sysId;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getSysNickName() {
                return sysNickName;
            }

            public void setSysNickName(String sysNickName) {
                this.sysNickName = sysNickName;
            }

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }

            public int getSysId() {
                return sysId;
            }

            public void setSysId(int sysId) {
                this.sysId = sysId;
            }
        }

        public static class AppNinePicturesBean implements Serializable{
            private String patientPic;
            private int patientId;
            private int id;

            public String getPatientPic() {
                return patientPic;
            }

            public void setPatientPic(String patientPic) {
                this.patientPic = patientPic;
            }

            public int getPatientId() {
                return patientId;
            }

            public void setPatientId(int patientId) {
                this.patientId = patientId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class ExmineNameBean implements Serializable{
            private String phone;
            private String sysNickName;
            private String departmentName;
            private String name;
            private String rank;
            private int sysId;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getSysNickName() {
                return sysNickName;
            }

            public void setSysNickName(String sysNickName) {
                this.sysNickName = sysNickName;
            }

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }

            public int getSysId() {
                return sysId;
            }

            public void setSysId(int sysId) {
                this.sysId = sysId;
            }
        }
    }
}
