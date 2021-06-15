package com.example.healthmanage.ui.activity.healthrecord.response;

public class HealthRecordResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : {"id":92,"name":"王斌","idType":"身份证","idNumber":"320724199999999999","sex":"男","birthday":"1999-01-01","phone":"13584869132","bloodType":"A型","marital":"已婚","region":"北京市北京市东城区","address":"长安街","occupation":"企业职工","work":"苏州集云云","socialSecurity":"123456123","height":"","weight":"","bust":"","waistline":"","hipline":"","bloodPressureRecordTime":"2020-11-11 10:33:00.0","highPressure":"","lowPressure":"","bloodSugarRecordTime":"2020-11-11 10:33:00.0","checkInterval":"","bloodSugar":"","smoking":"已戒烟","smokingYears":"1","smokingNumber":"","quitSmokingYears":"1","alcohol":"已戒酒","alcoholType":null,"beer":"","whiteAlcohol":"","redAlcohol":"","otherAlcohol":"","drinkingYears":"","quitDrinkingYears":"","diet":"偏甜","sport":"大于7次","sportHour":"11","sleep":">8小时","historyDisease":"心脑血管疾病/糖尿病/痛风/高血压/","historyDiseaseInput":"","familyHistoryDisease":"0,1,15","familyHistoryDiseaseInput":"ffffffff-d999-5a00-50c4-3aa70535f7cb","allergy":"","drugAllergy":"0,1","foodAllergy":"0,3,1,2","contactAllergy":"0,5,4","historyOperation":"1,16","historyOperationInput":"ffffffff-d999-5a00-50c4-3aa70535f7cb","historyMedication":"0,4","historyMedicationInput":"ffffffff-d999-5a00-50c4-3aa70535f7cb","createTime":null,"updateTime":null,"token":null}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 92
     * name : 王斌
     * idType : 身份证
     * idNumber : 320724199999999999
     * sex : 男
     * birthday : 1999-01-01
     * phone : 13584869132
     * bloodType : A型
     * marital : 已婚
     * region : 北京市北京市东城区
     * address : 长安街
     * occupation : 企业职工
     * work : 苏州集云云
     * socialSecurity : 123456123
     * height :
     * weight :
     * bust :
     * waistline :
     * hipline :
     * bloodPressureRecordTime : 2020-11-11 10:33:00.0
     * highPressure :
     * lowPressure :
     * bloodSugarRecordTime : 2020-11-11 10:33:00.0
     * checkInterval :
     * bloodSugar :
     * smoking : 已戒烟
     * smokingYears : 1
     * smokingNumber :
     * quitSmokingYears : 1
     * alcohol : 已戒酒
     * alcoholType : null
     * beer :
     * whiteAlcohol :
     * redAlcohol :
     * otherAlcohol :
     * drinkingYears :
     * quitDrinkingYears :
     * diet : 偏甜
     * sport : 大于7次
     * sportHour : 11
     * sleep : >8小时
     * historyDisease : 心脑血管疾病/糖尿病/痛风/高血压/
     * historyDiseaseInput :
     * familyHistoryDisease : 0,1,15
     * familyHistoryDiseaseInput : ffffffff-d999-5a00-50c4-3aa70535f7cb
     * allergy :
     * drugAllergy : 0,1
     * foodAllergy : 0,3,1,2
     * contactAllergy : 0,5,4
     * historyOperation : 1,16
     * historyOperationInput : ffffffff-d999-5a00-50c4-3aa70535f7cb
     * historyMedication : 0,4
     * historyMedicationInput : ffffffff-d999-5a00-50c4-3aa70535f7cb
     * createTime : null
     * updateTime : null
     * token : null
     */

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
        private int id;
        private String name;
        private String idType;
        private String idNumber;
        private String sex;
        private String birthday;
        private String phone;
        private String bloodType;
        private String marital;
        private String region;
        private String address;
        private String occupation;
        private String work;
        private String socialSecurity;
        private String height;
        private String weight;
        private String bust;
        private String waistline;
        private String hipline;
        private String bloodPressureRecordTime;
        private String highPressure;
        private String lowPressure;
        private String bloodSugarRecordTime;
        private String checkInterval;
        private String bloodSugar;
        private String smoking;
        private String smokingYears;
        private String smokingNumber;
        private String quitSmokingYears;
        private String alcohol;
        private Object alcoholType;
        private String beer;
        private String whiteAlcohol;
        private String redAlcohol;
        private String otherAlcohol;
        private String drinkingYears;
        private String quitDrinkingYears;
        private String diet;
        private String sport;
        private String sportHour;
        private String sleep;
        private String historyDisease;
        private String historyDiseaseInput;
        private String familyHistoryDisease;
        private String familyHistoryDiseaseInput;
        private String allergy;
        private String drugAllergy;
        private String foodAllergy;
        private String contactAllergy;
        private String historyOperation;
        private String historyOperationInput;
        private String historyMedication;
        private String historyMedicationInput;
        private Object createTime;
        private Object updateTime;
        private Object token;

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

        public String getIdType() {
            return idType;
        }

        public void setIdType(String idType) {
            this.idType = idType;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBloodType() {
            return bloodType;
        }

        public void setBloodType(String bloodType) {
            this.bloodType = bloodType;
        }

        public String getMarital() {
            return marital;
        }

        public void setMarital(String marital) {
            this.marital = marital;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public String getSocialSecurity() {
            return socialSecurity;
        }

        public void setSocialSecurity(String socialSecurity) {
            this.socialSecurity = socialSecurity;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getBust() {
            return bust;
        }

        public void setBust(String bust) {
            this.bust = bust;
        }

        public String getWaistline() {
            return waistline;
        }

        public void setWaistline(String waistline) {
            this.waistline = waistline;
        }

        public String getHipline() {
            return hipline;
        }

        public void setHipline(String hipline) {
            this.hipline = hipline;
        }

        public String getBloodPressureRecordTime() {
            return bloodPressureRecordTime;
        }

        public void setBloodPressureRecordTime(String bloodPressureRecordTime) {
            this.bloodPressureRecordTime = bloodPressureRecordTime;
        }

        public String getHighPressure() {
            return highPressure;
        }

        public void setHighPressure(String highPressure) {
            this.highPressure = highPressure;
        }

        public String getLowPressure() {
            return lowPressure;
        }

        public void setLowPressure(String lowPressure) {
            this.lowPressure = lowPressure;
        }

        public String getBloodSugarRecordTime() {
            return bloodSugarRecordTime;
        }

        public void setBloodSugarRecordTime(String bloodSugarRecordTime) {
            this.bloodSugarRecordTime = bloodSugarRecordTime;
        }

        public String getCheckInterval() {
            return checkInterval;
        }

        public void setCheckInterval(String checkInterval) {
            this.checkInterval = checkInterval;
        }

        public String getBloodSugar() {
            return bloodSugar;
        }

        public void setBloodSugar(String bloodSugar) {
            this.bloodSugar = bloodSugar;
        }

        public String getSmoking() {
            return smoking;
        }

        public void setSmoking(String smoking) {
            this.smoking = smoking;
        }

        public String getSmokingYears() {
            return smokingYears;
        }

        public void setSmokingYears(String smokingYears) {
            this.smokingYears = smokingYears;
        }

        public String getSmokingNumber() {
            return smokingNumber;
        }

        public void setSmokingNumber(String smokingNumber) {
            this.smokingNumber = smokingNumber;
        }

        public String getQuitSmokingYears() {
            return quitSmokingYears;
        }

        public void setQuitSmokingYears(String quitSmokingYears) {
            this.quitSmokingYears = quitSmokingYears;
        }

        public String getAlcohol() {
            return alcohol;
        }

        public void setAlcohol(String alcohol) {
            this.alcohol = alcohol;
        }

        public Object getAlcoholType() {
            return alcoholType;
        }

        public void setAlcoholType(Object alcoholType) {
            this.alcoholType = alcoholType;
        }

        public String getBeer() {
            return beer;
        }

        public void setBeer(String beer) {
            this.beer = beer;
        }

        public String getWhiteAlcohol() {
            return whiteAlcohol;
        }

        public void setWhiteAlcohol(String whiteAlcohol) {
            this.whiteAlcohol = whiteAlcohol;
        }

        public String getRedAlcohol() {
            return redAlcohol;
        }

        public void setRedAlcohol(String redAlcohol) {
            this.redAlcohol = redAlcohol;
        }

        public String getOtherAlcohol() {
            return otherAlcohol;
        }

        public void setOtherAlcohol(String otherAlcohol) {
            this.otherAlcohol = otherAlcohol;
        }

        public String getDrinkingYears() {
            return drinkingYears;
        }

        public void setDrinkingYears(String drinkingYears) {
            this.drinkingYears = drinkingYears;
        }

        public String getQuitDrinkingYears() {
            return quitDrinkingYears;
        }

        public void setQuitDrinkingYears(String quitDrinkingYears) {
            this.quitDrinkingYears = quitDrinkingYears;
        }

        public String getDiet() {
            return diet;
        }

        public void setDiet(String diet) {
            this.diet = diet;
        }

        public String getSport() {
            return sport;
        }

        public void setSport(String sport) {
            this.sport = sport;
        }

        public String getSportHour() {
            return sportHour;
        }

        public void setSportHour(String sportHour) {
            this.sportHour = sportHour;
        }

        public String getSleep() {
            return sleep;
        }

        public void setSleep(String sleep) {
            this.sleep = sleep;
        }

        public String getHistoryDisease() {
            return historyDisease;
        }

        public void setHistoryDisease(String historyDisease) {
            this.historyDisease = historyDisease;
        }

        public String getHistoryDiseaseInput() {
            return historyDiseaseInput;
        }

        public void setHistoryDiseaseInput(String historyDiseaseInput) {
            this.historyDiseaseInput = historyDiseaseInput;
        }

        public String getFamilyHistoryDisease() {
            return familyHistoryDisease;
        }

        public void setFamilyHistoryDisease(String familyHistoryDisease) {
            this.familyHistoryDisease = familyHistoryDisease;
        }

        public String getFamilyHistoryDiseaseInput() {
            return familyHistoryDiseaseInput;
        }

        public void setFamilyHistoryDiseaseInput(String familyHistoryDiseaseInput) {
            this.familyHistoryDiseaseInput = familyHistoryDiseaseInput;
        }

        public String getAllergy() {
            return allergy;
        }

        public void setAllergy(String allergy) {
            this.allergy = allergy;
        }

        public String getDrugAllergy() {
            return drugAllergy;
        }

        public void setDrugAllergy(String drugAllergy) {
            this.drugAllergy = drugAllergy;
        }

        public String getFoodAllergy() {
            return foodAllergy;
        }

        public void setFoodAllergy(String foodAllergy) {
            this.foodAllergy = foodAllergy;
        }

        public String getContactAllergy() {
            return contactAllergy;
        }

        public void setContactAllergy(String contactAllergy) {
            this.contactAllergy = contactAllergy;
        }

        public String getHistoryOperation() {
            return historyOperation;
        }

        public void setHistoryOperation(String historyOperation) {
            this.historyOperation = historyOperation;
        }

        public String getHistoryOperationInput() {
            return historyOperationInput;
        }

        public void setHistoryOperationInput(String historyOperationInput) {
            this.historyOperationInput = historyOperationInput;
        }

        public String getHistoryMedication() {
            return historyMedication;
        }

        public void setHistoryMedication(String historyMedication) {
            this.historyMedication = historyMedication;
        }

        public String getHistoryMedicationInput() {
            return historyMedicationInput;
        }

        public void setHistoryMedicationInput(String historyMedicationInput) {
            this.historyMedicationInput = historyMedicationInput;
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

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }
    }
}
