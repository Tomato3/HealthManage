package com.example.healthmanage.ui.activity.temperature;

import java.util.List;

public class ReceivePatientBean {

    /**
     * id : 54
     * replyContent : 回复内容
     * drugList : [{"id":null,"name":"名称","number":22,"unit":"药品单位","useMode":"服用方式","useTime":"2021-05-07 08:00:00","useFrequency":"服用频率"},{"id":null,"name":"名称","number":22,"unit":"药品单位","useMode":"服用方式","useTime":"2021-05-07 08:00:00","useFrequency":"服用频率"}]
     */

    private String id;
    private String replyContent;
    private String diagnosis;
    private String token;
    //签名
    private String physician;
    /**
     * id : null
     * name : 名称
     * number : 22
     * unit : 药品单位
     * useMode : 服用方式
     * useTime : 2021-05-07 08:00:00
     * useFrequency : 服用频率
     */

    private List<DrugListBean> drugList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhysician() {
        return physician;
    }

    public void setPhysician(String physician) {
        this.physician = physician;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public List<DrugListBean> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<DrugListBean> drugList) {
        this.drugList = drugList;
    }

    public static class DrugListBean {
        private int id;
        private String name;
        private int number;
        private String unit;
        private String useMode;
        private String useTime;
        private String useFrequency;

        public DrugListBean(String unit, String useMode, String useTime, String useFrequency) {
            this.unit = unit;
            this.useMode = useMode;
            this.useTime = useTime;
            this.useFrequency = useFrequency;
        }

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

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getUseMode() {
            return useMode;
        }

        public void setUseMode(String useMode) {
            this.useMode = useMode;
        }

        public String getUseTime() {
            return useTime;
        }

        public void setUseTime(String useTime) {
            this.useTime = useTime;
        }

        public String getUseFrequency() {
            return useFrequency;
        }

        public void setUseFrequency(String useFrequency) {
            this.useFrequency = useFrequency;
        }
    }
}
