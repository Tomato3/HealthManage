package com.example.healthmanage.bean.network.response;

import java.util.List;

public class HistoryDataResponse {


    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : {"total":13,"rows":[{"id":176,"imei":"866118040106539","bloodSugar":"5.66","bloodHighPressure":124,"bloodLowPressure":70,"bloodStick":"6.74","bloodLoop":"暂无风险","fatiguePredict":"数据分析显示：您今日精神疲劳，不适合开车，建议放松，请注意休息，合理安排作息时间","microLoopPredict":"微循环良好","riskPredict":"暂无风险","arteryPredict":"数据分析显示：您今日动脉硬化发展趋势微快，请注意饮食和作息","metabolizePredict":"数据分析显示：您今日身体代谢良好，请继续保持","diseasePredict":"您可能存在体环境异常，请连续监测;您可能存在营养不良异常，请连续监测","heartRate":74,"temperature":"36","cvo2":"98","svo2":"77","pao2":82,"pvo2":32,"paco2":35,"aado2":18,"hgb":"11.5","oi":428,"createTime":"2020-08-06 12:40:05"},{"id":175,"imei":"866118040106539","bloodSugar":"5.66","bloodHighPressure":124,"bloodLowPressure":70,"bloodStick":"6.74","bloodLoop":"暂无风险","fatiguePredict":"数据分析显示：您今日精神疲劳，不适合开车，建议放松，请注意休息，合理安排作息时间","microLoopPredict":"微循环良好","riskPredict":"暂无风险","arteryPredict":"数据分析显示：您今日动脉硬化发展趋势微快，请注意饮食和作息","metabolizePredict":"数据分析显示：您今日身体代谢良好，请继续保持","diseasePredict":"您可能存在体环境异常，请连续监测;您可能存在营养不良异常，请连续监测","heartRate":74,"temperature":"36","cvo2":"98","svo2":"77","pao2":82,"pvo2":32,"paco2":35,"aado2":18,"hgb":"11.5","oi":428,"createTime":"2020-08-06 08:40:05"},{"id":177,"imei":"866118040106539","bloodSugar":"5.66","bloodHighPressure":124,"bloodLowPressure":70,"bloodStick":"6.74","bloodLoop":"暂无风险","fatiguePredict":"数据分析显示：您今日精神疲劳，不适合开车，建议放松，请注意休息，合理安排作息时间","microLoopPredict":"微循环良好","riskPredict":"暂无风险","arteryPredict":"数据分析显示：您今日动脉硬化发展趋势微快，请注意饮食和作息","metabolizePredict":"数据分析显示：您今日身体代谢良好，请继续保持","diseasePredict":"您可能存在体环境异常，请连续监测;您可能存在营养不良异常，请连续监测","heartRate":74,"temperature":"36","cvo2":"98","svo2":"77","pao2":82,"pvo2":32,"paco2":35,"aado2":18,"hgb":"11.5","oi":428,"createTime":"2020-08-06 06:40:05"},{"id":178,"imei":"866118040106539","bloodSugar":"5.66","bloodHighPressure":124,"bloodLowPressure":70,"bloodStick":"6.74","bloodLoop":"暂无风险","fatiguePredict":"数据分析显示：您今日精神疲劳，不适合开车，建议放松，请注意休息，合理安排作息时间","microLoopPredict":"微循环良好","riskPredict":"暂无风险","arteryPredict":"数据分析显示：您今日动脉硬化发展趋势微快，请注意饮食和作息","metabolizePredict":"数据分析显示：您今日身体代谢良好，请继续保持","diseasePredict":"您可能存在体环境异常，请连续监测;您可能存在营养不良异常，请连续监测","heartRate":74,"temperature":"36","cvo2":"98","svo2":"77","pao2":82,"pvo2":32,"paco2":35,"aado2":18,"hgb":"11.5","oi":428,"createTime":"2020-08-06 05:40:05"},{"id":174,"imei":"866118040106539","bloodSugar":"5.66","bloodHighPressure":124,"bloodLowPressure":70,"bloodStick":"6.74","bloodLoop":"暂无风险","fatiguePredict":"数据分析显示：您今日精神疲劳，不适合开车，建议放松，请注意休息，合理安排作息时间","microLoopPredict":"微循环良好","riskPredict":"暂无风险","arteryPredict":"数据分析显示：您今日动脉硬化发展趋势微快，请注意饮食和作息","metabolizePredict":"数据分析显示：您今日身体代谢良好，请继续保持","diseasePredict":"您可能存在体环境异常，请连续监测;您可能存在营养不良异常，请连续监测","heartRate":74,"temperature":"36","cvo2":"98","svo2":"77","pao2":82,"pvo2":32,"paco2":35,"aado2":18,"hgb":"11.5","oi":428,"createTime":"2020-08-05 11:40:05"}]}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * total : 13
         * rows : [{"id":176,"imei":"866118040106539","bloodSugar":"5.66","bloodHighPressure":124,"bloodLowPressure":70,"bloodStick":"6.74","bloodLoop":"暂无风险","fatiguePredict":"数据分析显示：您今日精神疲劳，不适合开车，建议放松，请注意休息，合理安排作息时间","microLoopPredict":"微循环良好","riskPredict":"暂无风险","arteryPredict":"数据分析显示：您今日动脉硬化发展趋势微快，请注意饮食和作息","metabolizePredict":"数据分析显示：您今日身体代谢良好，请继续保持","diseasePredict":"您可能存在体环境异常，请连续监测;您可能存在营养不良异常，请连续监测","heartRate":74,"temperature":"36","cvo2":"98","svo2":"77","pao2":82,"pvo2":32,"paco2":35,"aado2":18,"hgb":"11.5","oi":428,"createTime":"2020-08-06 12:40:05"},{"id":175,"imei":"866118040106539","bloodSugar":"5.66","bloodHighPressure":124,"bloodLowPressure":70,"bloodStick":"6.74","bloodLoop":"暂无风险","fatiguePredict":"数据分析显示：您今日精神疲劳，不适合开车，建议放松，请注意休息，合理安排作息时间","microLoopPredict":"微循环良好","riskPredict":"暂无风险","arteryPredict":"数据分析显示：您今日动脉硬化发展趋势微快，请注意饮食和作息","metabolizePredict":"数据分析显示：您今日身体代谢良好，请继续保持","diseasePredict":"您可能存在体环境异常，请连续监测;您可能存在营养不良异常，请连续监测","heartRate":74,"temperature":"36","cvo2":"98","svo2":"77","pao2":82,"pvo2":32,"paco2":35,"aado2":18,"hgb":"11.5","oi":428,"createTime":"2020-08-06 08:40:05"},{"id":177,"imei":"866118040106539","bloodSugar":"5.66","bloodHighPressure":124,"bloodLowPressure":70,"bloodStick":"6.74","bloodLoop":"暂无风险","fatiguePredict":"数据分析显示：您今日精神疲劳，不适合开车，建议放松，请注意休息，合理安排作息时间","microLoopPredict":"微循环良好","riskPredict":"暂无风险","arteryPredict":"数据分析显示：您今日动脉硬化发展趋势微快，请注意饮食和作息","metabolizePredict":"数据分析显示：您今日身体代谢良好，请继续保持","diseasePredict":"您可能存在体环境异常，请连续监测;您可能存在营养不良异常，请连续监测","heartRate":74,"temperature":"36","cvo2":"98","svo2":"77","pao2":82,"pvo2":32,"paco2":35,"aado2":18,"hgb":"11.5","oi":428,"createTime":"2020-08-06 06:40:05"},{"id":178,"imei":"866118040106539","bloodSugar":"5.66","bloodHighPressure":124,"bloodLowPressure":70,"bloodStick":"6.74","bloodLoop":"暂无风险","fatiguePredict":"数据分析显示：您今日精神疲劳，不适合开车，建议放松，请注意休息，合理安排作息时间","microLoopPredict":"微循环良好","riskPredict":"暂无风险","arteryPredict":"数据分析显示：您今日动脉硬化发展趋势微快，请注意饮食和作息","metabolizePredict":"数据分析显示：您今日身体代谢良好，请继续保持","diseasePredict":"您可能存在体环境异常，请连续监测;您可能存在营养不良异常，请连续监测","heartRate":74,"temperature":"36","cvo2":"98","svo2":"77","pao2":82,"pvo2":32,"paco2":35,"aado2":18,"hgb":"11.5","oi":428,"createTime":"2020-08-06 05:40:05"},{"id":174,"imei":"866118040106539","bloodSugar":"5.66","bloodHighPressure":124,"bloodLowPressure":70,"bloodStick":"6.74","bloodLoop":"暂无风险","fatiguePredict":"数据分析显示：您今日精神疲劳，不适合开车，建议放松，请注意休息，合理安排作息时间","microLoopPredict":"微循环良好","riskPredict":"暂无风险","arteryPredict":"数据分析显示：您今日动脉硬化发展趋势微快，请注意饮食和作息","metabolizePredict":"数据分析显示：您今日身体代谢良好，请继续保持","diseasePredict":"您可能存在体环境异常，请连续监测;您可能存在营养不良异常，请连续监测","heartRate":74,"temperature":"36","cvo2":"98","svo2":"77","pao2":82,"pvo2":32,"paco2":35,"aado2":18,"hgb":"11.5","oi":428,"createTime":"2020-08-05 11:40:05"}]
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 176
             * imei : 866118040106539
             * bloodSugar : 5.66
             * bloodHighPressure : 124
             * bloodLowPressure : 70
             * bloodStick : 6.74
             * bloodLoop : 暂无风险
             * fatiguePredict : 数据分析显示：您今日精神疲劳，不适合开车，建议放松，请注意休息，合理安排作息时间
             * microLoopPredict : 微循环良好
             * riskPredict : 暂无风险
             * arteryPredict : 数据分析显示：您今日动脉硬化发展趋势微快，请注意饮食和作息
             * metabolizePredict : 数据分析显示：您今日身体代谢良好，请继续保持
             * diseasePredict : 您可能存在体环境异常，请连续监测;您可能存在营养不良异常，请连续监测
             * heartRate : 74
             * temperature : 36
             * cvo2 : 98
             * svo2 : 77
             * pao2 : 82
             * pvo2 : 32
             * paco2 : 35
             * aado2 : 18
             * hgb : 11.5
             * oi : 428
             * createTime : 2020-08-06 12:40:05
             */

            private int id;
            private String imei;
            private String bloodSugar;
            private int bloodHighPressure;
            private int bloodLowPressure;
            private String bloodStick;
            private String bloodLoop;
            private String fatiguePredict;
            private String microLoopPredict;
            private String riskPredict;
            private String arteryPredict;
            private String metabolizePredict;
            private String diseasePredict;
            private int heartRate;
            private String temperature;
            private String cvo2;
            private String svo2;
            private int pao2;
            private int pvo2;
            private int paco2;
            private int aado2;
            private String hgb;
            private int oi;
            private String createTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImei() {
                return imei;
            }

            public void setImei(String imei) {
                this.imei = imei;
            }

            public String getBloodSugar() {
                return bloodSugar;
            }

            public void setBloodSugar(String bloodSugar) {
                this.bloodSugar = bloodSugar;
            }

            public int getBloodHighPressure() {
                return bloodHighPressure;
            }

            public void setBloodHighPressure(int bloodHighPressure) {
                this.bloodHighPressure = bloodHighPressure;
            }

            public int getBloodLowPressure() {
                return bloodLowPressure;
            }

            public void setBloodLowPressure(int bloodLowPressure) {
                this.bloodLowPressure = bloodLowPressure;
            }

            public String getBloodStick() {
                return bloodStick;
            }

            public void setBloodStick(String bloodStick) {
                this.bloodStick = bloodStick;
            }

            public String getBloodLoop() {
                return bloodLoop;
            }

            public void setBloodLoop(String bloodLoop) {
                this.bloodLoop = bloodLoop;
            }

            public String getFatiguePredict() {
                return fatiguePredict;
            }

            public void setFatiguePredict(String fatiguePredict) {
                this.fatiguePredict = fatiguePredict;
            }

            public String getMicroLoopPredict() {
                return microLoopPredict;
            }

            public void setMicroLoopPredict(String microLoopPredict) {
                this.microLoopPredict = microLoopPredict;
            }

            public String getRiskPredict() {
                return riskPredict;
            }

            public void setRiskPredict(String riskPredict) {
                this.riskPredict = riskPredict;
            }

            public String getArteryPredict() {
                return arteryPredict;
            }

            public void setArteryPredict(String arteryPredict) {
                this.arteryPredict = arteryPredict;
            }

            public String getMetabolizePredict() {
                return metabolizePredict;
            }

            public void setMetabolizePredict(String metabolizePredict) {
                this.metabolizePredict = metabolizePredict;
            }

            public String getDiseasePredict() {
                return diseasePredict;
            }

            public void setDiseasePredict(String diseasePredict) {
                this.diseasePredict = diseasePredict;
            }

            public int getHeartRate() {
                return heartRate;
            }

            public void setHeartRate(int heartRate) {
                this.heartRate = heartRate;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getCvo2() {
                return cvo2;
            }

            public void setCvo2(String cvo2) {
                this.cvo2 = cvo2;
            }

            public String getSvo2() {
                return svo2;
            }

            public void setSvo2(String svo2) {
                this.svo2 = svo2;
            }

            public int getPao2() {
                return pao2;
            }

            public void setPao2(int pao2) {
                this.pao2 = pao2;
            }

            public int getPvo2() {
                return pvo2;
            }

            public void setPvo2(int pvo2) {
                this.pvo2 = pvo2;
            }

            public int getPaco2() {
                return paco2;
            }

            public void setPaco2(int paco2) {
                this.paco2 = paco2;
            }

            public int getAado2() {
                return aado2;
            }

            public void setAado2(int aado2) {
                this.aado2 = aado2;
            }

            public String getHgb() {
                return hgb;
            }

            public void setHgb(String hgb) {
                this.hgb = hgb;
            }

            public int getOi() {
                return oi;
            }

            public void setOi(int oi) {
                this.oi = oi;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
