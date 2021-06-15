package com.example.healthmanage.bean.network.response;

import java.util.List;

public class NursingResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":1652608,"deviceId":"11102435CC1399B5","setWindT":0,"setWaterP":1,"setWaterT":1,"sewagePailStatus":1,"purePailStatus":1,"heatwaterboxWaterLevel":0,"heatwaterboxWaterT":"29","vacuum":"914","stoolSensor0Status":0,"stoolSensor1Status":0,"stoolSensor2Status":0,"stoolSensor3Status":0,"stoolSensor4Status":0,"urineSensor0Status":0,"urineSensor1Status":0,"urineSensor2Status":0,"urineSensor3Status":0,"workheadSlope":"0","currentWindT":"0","currentWorkStatus":0,"cleanAirStatus":1,"fault0":0,"fault1":0,"fault2":0,"fault3":1,"fault4":0,"fault5":0,"fault6":0,"fault7":0,"fault8":0,"fault9":0,"fault10":0,"fault11":0,"fault12":0,"sourceData":"/11102435CC1399B5/S00/1/0,1,1,1,1,0,29,914,0,0,0,0,0,1,8/1598341861602","createTime":"2020-08-25 15:51:01","deviceType":"1"}]
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
         * id : 1652608
         * deviceId : 11102435CC1399B5
         * setWindT : 0
         * setWaterP : 1
         * setWaterT : 1
         * sewagePailStatus : 1
         * purePailStatus : 1
         * heatwaterboxWaterLevel : 0
         * heatwaterboxWaterT : 29
         * vacuum : 914
         * stoolSensor0Status : 0
         * stoolSensor1Status : 0
         * stoolSensor2Status : 0
         * stoolSensor3Status : 0
         * stoolSensor4Status : 0
         * urineSensor0Status : 0
         * urineSensor1Status : 0
         * urineSensor2Status : 0
         * urineSensor3Status : 0
         * workheadSlope : 0
         * currentWindT : 0
         * currentWorkStatus : 0
         * cleanAirStatus : 1
         * fault0 : 0
         * fault1 : 0
         * fault2 : 0
         * fault3 : 1
         * fault4 : 0
         * fault5 : 0
         * fault6 : 0
         * fault7 : 0
         * fault8 : 0
         * fault9 : 0
         * fault10 : 0
         * fault11 : 0
         * fault12 : 0
         * sourceData : /11102435CC1399B5/S00/1/0,1,1,1,1,0,29,914,0,0,0,0,0,1,8/1598341861602
         * createTime : 2020-08-25 15:51:01
         * deviceType : 1
         */

        private int id;
        private String deviceId;
        private int setWindT;
        private int setWaterP;
        private int setWaterT;
        private int sewagePailStatus;
        private int purePailStatus;
        private int heatwaterboxWaterLevel;
        private String heatwaterboxWaterT;
        private String vacuum;
        private int stoolSensor0Status;
        private int stoolSensor1Status;
        private int stoolSensor2Status;
        private int stoolSensor3Status;
        private int stoolSensor4Status;
        private int urineSensor0Status;
        private int urineSensor1Status;
        private int urineSensor2Status;
        private int urineSensor3Status;
        private String workheadSlope;
        private String currentWindT;
        private int currentWorkStatus;
        private int cleanAirStatus;
        private int fault0;
        private int fault1;
        private int fault2;
        private int fault3;
        private int fault4;
        private int fault5;
        private int fault6;
        private int fault7;
        private int fault8;
        private int fault9;
        private int fault10;
        private int fault11;
        private int fault12;
        private String sourceData;
        private String createTime;
        private String deviceType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public int getSetWindT() {
            return setWindT;
        }

        public void setSetWindT(int setWindT) {
            this.setWindT = setWindT;
        }

        public int getSetWaterP() {
            return setWaterP;
        }

        public void setSetWaterP(int setWaterP) {
            this.setWaterP = setWaterP;
        }

        public int getSetWaterT() {
            return setWaterT;
        }

        public void setSetWaterT(int setWaterT) {
            this.setWaterT = setWaterT;
        }

        public int getSewagePailStatus() {
            return sewagePailStatus;
        }

        public void setSewagePailStatus(int sewagePailStatus) {
            this.sewagePailStatus = sewagePailStatus;
        }

        public int getPurePailStatus() {
            return purePailStatus;
        }

        public void setPurePailStatus(int purePailStatus) {
            this.purePailStatus = purePailStatus;
        }

        public int getHeatwaterboxWaterLevel() {
            return heatwaterboxWaterLevel;
        }

        public void setHeatwaterboxWaterLevel(int heatwaterboxWaterLevel) {
            this.heatwaterboxWaterLevel = heatwaterboxWaterLevel;
        }

        public String getHeatwaterboxWaterT() {
            return heatwaterboxWaterT;
        }

        public void setHeatwaterboxWaterT(String heatwaterboxWaterT) {
            this.heatwaterboxWaterT = heatwaterboxWaterT;
        }

        public String getVacuum() {
            return vacuum;
        }

        public void setVacuum(String vacuum) {
            this.vacuum = vacuum;
        }

        public int getStoolSensor0Status() {
            return stoolSensor0Status;
        }

        public void setStoolSensor0Status(int stoolSensor0Status) {
            this.stoolSensor0Status = stoolSensor0Status;
        }

        public int getStoolSensor1Status() {
            return stoolSensor1Status;
        }

        public void setStoolSensor1Status(int stoolSensor1Status) {
            this.stoolSensor1Status = stoolSensor1Status;
        }

        public int getStoolSensor2Status() {
            return stoolSensor2Status;
        }

        public void setStoolSensor2Status(int stoolSensor2Status) {
            this.stoolSensor2Status = stoolSensor2Status;
        }

        public int getStoolSensor3Status() {
            return stoolSensor3Status;
        }

        public void setStoolSensor3Status(int stoolSensor3Status) {
            this.stoolSensor3Status = stoolSensor3Status;
        }

        public int getStoolSensor4Status() {
            return stoolSensor4Status;
        }

        public void setStoolSensor4Status(int stoolSensor4Status) {
            this.stoolSensor4Status = stoolSensor4Status;
        }

        public int getUrineSensor0Status() {
            return urineSensor0Status;
        }

        public void setUrineSensor0Status(int urineSensor0Status) {
            this.urineSensor0Status = urineSensor0Status;
        }

        public int getUrineSensor1Status() {
            return urineSensor1Status;
        }

        public void setUrineSensor1Status(int urineSensor1Status) {
            this.urineSensor1Status = urineSensor1Status;
        }

        public int getUrineSensor2Status() {
            return urineSensor2Status;
        }

        public void setUrineSensor2Status(int urineSensor2Status) {
            this.urineSensor2Status = urineSensor2Status;
        }

        public int getUrineSensor3Status() {
            return urineSensor3Status;
        }

        public void setUrineSensor3Status(int urineSensor3Status) {
            this.urineSensor3Status = urineSensor3Status;
        }

        public String getWorkheadSlope() {
            return workheadSlope;
        }

        public void setWorkheadSlope(String workheadSlope) {
            this.workheadSlope = workheadSlope;
        }

        public String getCurrentWindT() {
            return currentWindT;
        }

        public void setCurrentWindT(String currentWindT) {
            this.currentWindT = currentWindT;
        }

        public int getCurrentWorkStatus() {
            return currentWorkStatus;
        }

        public void setCurrentWorkStatus(int currentWorkStatus) {
            this.currentWorkStatus = currentWorkStatus;
        }

        public int getCleanAirStatus() {
            return cleanAirStatus;
        }

        public void setCleanAirStatus(int cleanAirStatus) {
            this.cleanAirStatus = cleanAirStatus;
        }

        public int getFault0() {
            return fault0;
        }

        public void setFault0(int fault0) {
            this.fault0 = fault0;
        }

        public int getFault1() {
            return fault1;
        }

        public void setFault1(int fault1) {
            this.fault1 = fault1;
        }

        public int getFault2() {
            return fault2;
        }

        public void setFault2(int fault2) {
            this.fault2 = fault2;
        }

        public int getFault3() {
            return fault3;
        }

        public void setFault3(int fault3) {
            this.fault3 = fault3;
        }

        public int getFault4() {
            return fault4;
        }

        public void setFault4(int fault4) {
            this.fault4 = fault4;
        }

        public int getFault5() {
            return fault5;
        }

        public void setFault5(int fault5) {
            this.fault5 = fault5;
        }

        public int getFault6() {
            return fault6;
        }

        public void setFault6(int fault6) {
            this.fault6 = fault6;
        }

        public int getFault7() {
            return fault7;
        }

        public void setFault7(int fault7) {
            this.fault7 = fault7;
        }

        public int getFault8() {
            return fault8;
        }

        public void setFault8(int fault8) {
            this.fault8 = fault8;
        }

        public int getFault9() {
            return fault9;
        }

        public void setFault9(int fault9) {
            this.fault9 = fault9;
        }

        public int getFault10() {
            return fault10;
        }

        public void setFault10(int fault10) {
            this.fault10 = fault10;
        }

        public int getFault11() {
            return fault11;
        }

        public void setFault11(int fault11) {
            this.fault11 = fault11;
        }

        public int getFault12() {
            return fault12;
        }

        public void setFault12(int fault12) {
            this.fault12 = fault12;
        }

        public String getSourceData() {
            return sourceData;
        }

        public void setSourceData(String sourceData) {
            this.sourceData = sourceData;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }
    }
}
