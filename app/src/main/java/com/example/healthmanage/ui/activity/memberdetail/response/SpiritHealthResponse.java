package com.example.healthmanage.ui.activity.memberdetail.response;

import java.util.List;

public class SpiritHealthResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : [{"deepSleep":0,"highBreathRate":18,"lightSleep":0,"averageBreathRate":15.566666666666666,"outOfBed":0,"bodyMovement":17,"snore":0,"highHeartRate":83,"lowHeartRate":65,"heartRate":[{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":76,"breathRate":9,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:45:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":78,"breathRate":13,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:46:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":70,"breathRate":14,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:47:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":69,"breathRate":15,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:48:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":73,"breathRate":15,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:49:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":70,"breathRate":15,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:50:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":73,"breathRate":16,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:51:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":72,"breathRate":16,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:52:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":83,"breathRate":16,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:53:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":69,"breathRate":15,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:54:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":70,"breathRate":15,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:55:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":70,"breathRate":15,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:56:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":71,"breathRate":15,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:57:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":81,"breathRate":16,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:58:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":83,"breathRate":18,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:59:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":81,"breathRate":17,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 16:00:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":74,"breathRate":16,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:01:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":69,"breathRate":15,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 16:02:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":70,"breathRate":15,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:03:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":66,"breathRate":15,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:04:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":65,"breathRate":15,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:05:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":79,"breathRate":17,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:06:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":81,"breathRate":18,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:07:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":77,"breathRate":17,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 16:08:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":80,"breathRate":18,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 16:09:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":67,"breathRate":17,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:10:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":70,"breathRate":16,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:11:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":71,"breathRate":16,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 16:12:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":73,"breathRate":16,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:13:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":79,"breathRate":16,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:14:52","cycleTime":"2021-01-12 14:45:52"}],"sleepLength ":30,"sleepTime":"2021-01-12 14:45:52","averageHeartRate":73.66666666666667,"sober":30,"sleepScore":85,"lowBreathRate":9}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * deepSleep : 0
     * highBreathRate : 18
     * lightSleep : 0
     * averageBreathRate : 15.566666666666666
     * outOfBed : 0
     * bodyMovement : 17
     * snore : 0
     * highHeartRate : 83
     * lowHeartRate : 65
     * heartRate : [{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":76,"breathRate":9,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:45:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":78,"breathRate":13,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:46:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":70,"breathRate":14,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:47:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":69,"breathRate":15,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:48:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":73,"breathRate":15,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:49:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":70,"breathRate":15,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:50:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":73,"breathRate":16,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:51:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":72,"breathRate":16,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:52:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":83,"breathRate":16,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:53:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":69,"breathRate":15,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:54:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":70,"breathRate":15,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:55:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":70,"breathRate":15,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:56:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":71,"breathRate":15,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:57:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":81,"breathRate":16,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 15:58:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":83,"breathRate":18,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 15:59:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":81,"breathRate":17,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 16:00:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":74,"breathRate":16,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:01:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":69,"breathRate":15,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 16:02:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":70,"breathRate":15,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:03:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":66,"breathRate":15,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:04:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":65,"breathRate":15,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:05:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":79,"breathRate":17,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:06:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":81,"breathRate":18,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:07:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":77,"breathRate":17,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 16:08:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":80,"breathRate":18,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 16:09:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":67,"breathRate":17,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:10:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":70,"breathRate":16,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:11:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":71,"breathRate":16,"snoring":0,"turnover":0,"sleepState":2,"createTime":"2021-01-12 16:12:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":73,"breathRate":16,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:13:52","cycleTime":"2021-01-12 14:45:52"},{"id":null,"deviceId":"SM01-48:87:2D:12:95:E8","heartRate":79,"breathRate":16,"snoring":0,"turnover":1,"sleepState":2,"createTime":"2021-01-12 16:14:52","cycleTime":"2021-01-12 14:45:52"}]
     * sleepLength  : 30
     * sleepTime : 2021-01-12 14:45:52
     * averageHeartRate : 73.66666666666667
     * sober : 30
     * sleepScore : 85.0
     * lowBreathRate : 9
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
        private int deepSleep;
        private int highBreathRate;
        private int lightSleep;
        private double averageBreathRate;
        private int outOfBed;
        private int bodyMovement;
        private int snore;
        private int highHeartRate;
        private int lowHeartRate;
        private int sleepLength;
        private String sleepTime;
        private double averageHeartRate;
        private int sober;
        private double sleepScore;
        private int lowBreathRate;
        /**
         * id : null
         * deviceId : SM01-48:87:2D:12:95:E8
         * heartRate : 76
         * breathRate : 9
         * snoring : 0
         * turnover : 1
         * sleepState : 2
         * createTime : 2021-01-12 15:45:52
         * cycleTime : 2021-01-12 14:45:52
         */

        private List<HeartRateBean> heartRate;

        public int getDeepSleep() {
            return deepSleep;
        }

        public void setDeepSleep(int deepSleep) {
            this.deepSleep = deepSleep;
        }

        public int getHighBreathRate() {
            return highBreathRate;
        }

        public void setHighBreathRate(int highBreathRate) {
            this.highBreathRate = highBreathRate;
        }

        public int getLightSleep() {
            return lightSleep;
        }

        public void setLightSleep(int lightSleep) {
            this.lightSleep = lightSleep;
        }

        public double getAverageBreathRate() {
            return averageBreathRate;
        }

        public void setAverageBreathRate(double averageBreathRate) {
            this.averageBreathRate = averageBreathRate;
        }

        public int getOutOfBed() {
            return outOfBed;
        }

        public void setOutOfBed(int outOfBed) {
            this.outOfBed = outOfBed;
        }

        public int getBodyMovement() {
            return bodyMovement;
        }

        public void setBodyMovement(int bodyMovement) {
            this.bodyMovement = bodyMovement;
        }

        public int getSnore() {
            return snore;
        }

        public void setSnore(int snore) {
            this.snore = snore;
        }

        public int getHighHeartRate() {
            return highHeartRate;
        }

        public void setHighHeartRate(int highHeartRate) {
            this.highHeartRate = highHeartRate;
        }

        public int getLowHeartRate() {
            return lowHeartRate;
        }

        public void setLowHeartRate(int lowHeartRate) {
            this.lowHeartRate = lowHeartRate;
        }

        public int getSleepLength() {
            return sleepLength;
        }

        public void setSleepLength(int sleepLength) {
            this.sleepLength = sleepLength;
        }

        public String getSleepTime() {
            return sleepTime;
        }

        public void setSleepTime(String sleepTime) {
            this.sleepTime = sleepTime;
        }

        public double getAverageHeartRate() {
            return averageHeartRate;
        }

        public void setAverageHeartRate(double averageHeartRate) {
            this.averageHeartRate = averageHeartRate;
        }

        public int getSober() {
            return sober;
        }

        public void setSober(int sober) {
            this.sober = sober;
        }

        public double getSleepScore() {
            return sleepScore;
        }

        public void setSleepScore(double sleepScore) {
            this.sleepScore = sleepScore;
        }

        public int getLowBreathRate() {
            return lowBreathRate;
        }

        public void setLowBreathRate(int lowBreathRate) {
            this.lowBreathRate = lowBreathRate;
        }

        public List<HeartRateBean> getHeartRate() {
            return heartRate;
        }

        public void setHeartRate(List<HeartRateBean> heartRate) {
            this.heartRate = heartRate;
        }

        public static class HeartRateBean {
            private Object id;
            private String deviceId;
            private int heartRate;
            private int breathRate;
            private int snoring;
            private int turnover;
            private int sleepState;
            private String createTime;
            private String cycleTime;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public int getHeartRate() {
                return heartRate;
            }

            public void setHeartRate(int heartRate) {
                this.heartRate = heartRate;
            }

            public int getBreathRate() {
                return breathRate;
            }

            public void setBreathRate(int breathRate) {
                this.breathRate = breathRate;
            }

            public int getSnoring() {
                return snoring;
            }

            public void setSnoring(int snoring) {
                this.snoring = snoring;
            }

            public int getTurnover() {
                return turnover;
            }

            public void setTurnover(int turnover) {
                this.turnover = turnover;
            }

            public int getSleepState() {
                return sleepState;
            }

            public void setSleepState(int sleepState) {
                this.sleepState = sleepState;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCycleTime() {
                return cycleTime;
            }

            public void setCycleTime(String cycleTime) {
                this.cycleTime = cycleTime;
            }
        }
    }
}
