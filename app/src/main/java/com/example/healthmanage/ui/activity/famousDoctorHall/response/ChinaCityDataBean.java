package com.example.healthmanage.ui.activity.famousDoctorHall.response;

import java.util.List;

/**
 * desc:
 * date:2021/7/2 14:20
 * author:bWang
 */
public class ChinaCityDataBean {

    /**
     * id : 110000
     * name : 北京市
     * cityList : [{"id":"110000","name":"北京市","cityList":[{"id":"110101","name":"东城区"},{"id":"110102","name":"西城区"},{"id":"110105","name":"朝阳区"},{"id":"110106","name":"丰台区"},{"id":"110107","name":"石景山区"},{"id":"110108","name":"海淀区"},{"id":"110109","name":"门头沟区"},{"id":"110111","name":"房山区"},{"id":"110112","name":"通州区"},{"id":"110113","name":"顺义区"},{"id":"110114","name":"昌平区"},{"id":"110115","name":"大兴区"},{"id":"110116","name":"怀柔区"},{"id":"110117","name":"平谷区"},{"id":"110118","name":"密云区"},{"id":"110119","name":"延庆区"}]}]
     */

    private String id;
    private String name;
    private boolean isSelect;

    public ChinaCityDataBean(String name) {
        this.name = name;
    }

    /**
     * id : 110000
     * name : 北京市
     * cityList : [{"id":"110101","name":"东城区"},{"id":"110102","name":"西城区"},{"id":"110105","name":"朝阳区"},{"id":"110106","name":"丰台区"},{"id":"110107","name":"石景山区"},{"id":"110108","name":"海淀区"},{"id":"110109","name":"门头沟区"},{"id":"110111","name":"房山区"},{"id":"110112","name":"通州区"},{"id":"110113","name":"顺义区"},{"id":"110114","name":"昌平区"},{"id":"110115","name":"大兴区"},{"id":"110116","name":"怀柔区"},{"id":"110117","name":"平谷区"},{"id":"110118","name":"密云区"},{"id":"110119","name":"延庆区"}]
     */



    private List<CityListBean> cityList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public List<CityListBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityListBean> cityList) {
        this.cityList = cityList;
    }

    public static class CityListBean {
        private String id;
        private String name;
        private boolean isSelect;
        /**
         * id : 110101
         * name : 东城区
         */

        private List<AreaListBean> cityList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public List<AreaListBean> getCityList() {
            return cityList;
        }

        public void setCityList(List<AreaListBean> cityList) {
            this.cityList = cityList;
        }

        public static class AreaListBean {
            private String id;
            private String name;
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
