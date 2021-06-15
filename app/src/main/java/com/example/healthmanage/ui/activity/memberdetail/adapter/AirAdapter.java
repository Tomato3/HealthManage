package com.example.healthmanage.ui.activity.memberdetail.adapter;

import android.text.Html;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.bean.network.response.AirResponse;

import java.util.List;

public class AirAdapter extends BaseQuickAdapter<AirResponse.DataBean, BaseViewHolder> {
    public AirAdapter(@Nullable List<AirResponse.DataBean> data) {
        super(R.layout.item_vip_detail,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AirResponse.DataBean item) {

        helper.setText(R.id.tv_device_scene,"设备场景:"+item.getDeviceInfo().getSceneName());
        helper.setText(R.id.tv_temperature,Html.fromHtml("温度:<font color=\"#000000\">\t"+item.getTemperature()+"\u2103" +"</font>"));
        helper.setText(R.id.tv_humidity,Html.fromHtml("湿度:<font color=\"#000000\">\t"+item.getHumidity()+"%"+"</font>"));
        helper.setText(R.id.tv_formaldehyde,Html.fromHtml("甲醛:<font color=\"#000000\">\t"+item.getHcho()+"</font>"));
        helper.setText(R.id.tv_anion,Html.fromHtml("负离子:<font color=\"#000000\">\t"+item.getNegOxygenIons()+"</font>"));
        helper.setText(R.id.tv_o2,Html.fromHtml("O2:<font color=\"#000000\">\t"+item.getO2()+"%"+"</font>"));
        helper.setText(R.id.tv_co2,Html.fromHtml("CO2:<font color=\"#000000\">\t"+item.getCo2()+"</font>"));
        helper.setText(R.id.tv_pm25,Html.fromHtml("PM2.5:<font color=\"#000000\">\t"+item.getPm25()+"</font>"));
        helper.setText(R.id.tv_pm3,Html.fromHtml("PM0.3:<font color=\"#000000\">\t"+item.getPm03()+"</font>"));
        helper.setText(R.id.tv_co,Html.fromHtml("CO:<font color=\"#000000\">\t"+item.getCo()+"</font>"));
        helper.setText(R.id.tv_voc,Html.fromHtml("VOC:<font color=\"#000000\">\t"+item.getTvoc()+"</font>"));
    }
}
