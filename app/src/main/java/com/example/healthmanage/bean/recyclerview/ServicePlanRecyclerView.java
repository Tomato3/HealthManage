package com.example.healthmanage.bean.recyclerview;

import com.example.healthmanage.R;

import java.io.Serializable;


public class ServicePlanRecyclerView implements Serializable {

    public int statusImg, state, servicePlanId;
    public String serviceMember, serviceItem, serviceTime, serviceLocation;

    public ServicePlanRecyclerView(String serviceMember, String serviceItem, String serviceTime
            , String serviceLocation, int state, int servicePlanId) {
        this.serviceMember = "服务对象：" + serviceMember;
        this.serviceItem = "服务项目：" + serviceItem;
        this.serviceTime = "服务时间：" + serviceTime;
        this.serviceLocation = "服务地点：" + serviceLocation;
        this.servicePlanId = servicePlanId;
        this.state = state;
        switch (state) {
            case 0:
                statusImg = R.drawable.recycler_view_my_task_red;
                break;
            case 1:
                statusImg = R.drawable.recycler_view_my_task_blue;
                break;
            case 2:
                statusImg = R.drawable.recycler_view_my_task_green;
                break;
        }
    }
}
