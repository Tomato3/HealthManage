package com.example.healthmanage.ui.activity.famousDoctorHall.bean;

import java.util.List;

/**
 * desc:
 * date:2021/7/1 10:50
 * author:bWang
 */
public class RankBean {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean{
        public DataBean(String name, List<ListBean> list) {
            this.name = name;
            this.list = list;
        }

        private String name;
        private List<ListBean> list;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static class ListBean{
            private String name;
            private boolean isSelect;

            public ListBean(String name) {
                this.name = name;
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
        }
    }
}
