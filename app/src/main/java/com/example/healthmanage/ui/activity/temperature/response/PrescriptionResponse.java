package com.example.healthmanage.ui.activity.temperature.response;

import java.io.Serializable;
import java.util.List;

public class PrescriptionResponse implements Serializable {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"modelName":"中暑","modelType":null,"drugId":null,"id":null,"drugList":[{"id":18,"name":"名称","number":22,"unit":"药品单位","useMode":"服用方式","useTime":"2021-05-07 08:00:00","useFrequency":"服用频率","healthConsultId":null},{"id":19,"name":"名称","number":22,"unit":"药品单位","useMode":"服用方式","useTime":"2021-05-07 08:00:00","useFrequency":"服用频率","healthConsultId":null}]}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * modelName : 中暑
     * modelType : null
     * drugId : null
     * id : null
     * drugList : [{"id":18,"name":"名称","number":22,"unit":"药品单位","useMode":"服用方式","useTime":"2021-05-07 08:00:00","useFrequency":"服用频率","healthConsultId":null},{"id":19,"name":"名称","number":22,"unit":"药品单位","useMode":"服用方式","useTime":"2021-05-07 08:00:00","useFrequency":"服用频率","healthConsultId":null}]
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

    public static class DataBean implements Serializable{
        private String modelName;
        private int modelType;
        private int drugId;
        private int id;
        /**
         * id : 18
         * name : 名称
         * number : 22
         * unit : 药品单位
         * useMode : 服用方式
         * useTime : 2021-05-07 08:00:00
         * useFrequency : 服用频率
         * healthConsultId : null
         */

        private List<DrugListBean> drugList;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public int getModelType() {
            return modelType;
        }

        public void setModelType(int modelType) {
            this.modelType = modelType;
        }

        public int getDrugId() {
            return drugId;
        }

        public void setDrugId(int drugId) {
            this.drugId = drugId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<DrugListBean> getDrugList() {
            return drugList;
        }

        public void setDrugList(List<DrugListBean> drugList) {
            this.drugList = drugList;
        }

        public static class DrugListBean implements Serializable{
            private int id;
            private String name;
            private int number;
            private String unit;
            private String useMode;
            private String useTime;
            private String useFrequency;
            private int healthConsultId;

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

            public int getHealthConsultId() {
                return healthConsultId;
            }

            public void setHealthConsultId(int healthConsultId) {
                this.healthConsultId = healthConsultId;
            }
        }
    }
}
