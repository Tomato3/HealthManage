package com.example.healthmanage.bean.network.response;

public class WeatherResponse {


    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message :
     * data : {"city_id":null,"city_code":"苏州","city_name":"苏州","aqi":"19","pm25":"7","pm10":"16","so2":"3","no2":"27","co":null,"o3":null,"quality":"优","update_time":"2020-06-22 12:00:00","temperature_current":"23","temperature_max":null,"temperature_min":null,"weather":"小雨","wind":"东南风2级","humidity":"90%"}
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
         * city_id : null
         * city_code : 苏州
         * city_name : 苏州
         * aqi : 19
         * pm25 : 7
         * pm10 : 16
         * so2 : 3
         * no2 : 27
         * co : null
         * o3 : null
         * quality : 优
         * update_time : 2020-06-22 12:00:00
         * temperature_current : 23
         * temperature_max : null
         * temperature_min : null
         * weather : 小雨
         * wind : 东南风2级
         * humidity : 90%
         */

        private Object city_id;
        private String city_code;
        private String city_name;
        private String aqi;
        private String pm25;
        private String pm10;
        private String so2;
        private String no2;
        private Object co;
        private Object o3;
        private String quality;
        private String update_time;
        private String temperature_current;
        private Object temperature_max;
        private Object temperature_min;
        private String weather;
        private String wind;
        private String humidity;

        public Object getCity_id() {
            return city_id;
        }

        public void setCity_id(Object city_id) {
            this.city_id = city_id;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public Object getCo() {
            return co;
        }

        public void setCo(Object co) {
            this.co = co;
        }

        public Object getO3() {
            return o3;
        }

        public void setO3(Object o3) {
            this.o3 = o3;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getTemperature_current() {
            return temperature_current;
        }

        public void setTemperature_current(String temperature_current) {
            this.temperature_current = temperature_current;
        }

        public Object getTemperature_max() {
            return temperature_max;
        }

        public void setTemperature_max(Object temperature_max) {
            this.temperature_max = temperature_max;
        }

        public Object getTemperature_min() {
            return temperature_min;
        }

        public void setTemperature_min(Object temperature_min) {
            this.temperature_min = temperature_min;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }
    }
}
