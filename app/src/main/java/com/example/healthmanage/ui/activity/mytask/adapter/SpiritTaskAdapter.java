package com.example.healthmanage.ui.activity.mytask.adapter;

import android.graphics.Color;
import android.text.Html;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.memberdetail.bean.BreathRateBean;
import com.example.healthmanage.ui.activity.memberdetail.bean.HeartRateBean;
import com.example.healthmanage.ui.activity.memberdetail.response.SpiritHealthResponse;
import com.example.healthmanage.ui.activity.mytask.response.HealthTaskDetailResponse;
import com.example.healthmanage.utils.MPChartUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SpiritTaskAdapter extends BaseQuickAdapter<HealthTaskDetailResponse.DataBean.SleepDataBean, BaseViewHolder> {
    private List<HeartRateBean> heartRateBeanList;
    private List<BreathRateBean> breathRateBeanList;
    public SpiritTaskAdapter(@Nullable List<HealthTaskDetailResponse.DataBean.SleepDataBean> data) {
        super(R.layout.item_spirit_health, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthTaskDetailResponse.DataBean.SleepDataBean item) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM", Locale.forLanguageTag(item.getSleepTime()));
        Date date = new Date();
        String sleepTime = sdf.format(date);
        helper.setText(R.id.tv_sleep_time, Html.fromHtml("入睡时间:<font color=\"#000000\">"+sleepTime+"</font>"));
        helper.setText(R.id.tv_sleep_duration,Html.fromHtml("入睡时长:<font color=\"#000000\">"+item.getSleepLength()+"</font>"));
        helper.setText(R.id.tv_leave_num,Html.fromHtml("离床次数:<font color=\"#000000\">"+item.getOutOfBed()+"</font>"));
        helper.setText(R.id.tv_sober,Html.fromHtml("清醒:<font color=\"#000000\">"+item.getSober()+"</font>"));
        helper.setText(R.id.tv_deep_sleep,Html.fromHtml("深睡:<font color=\"#000000\">"+item.getDeepSleep()+"</font>"));
        helper.setText(R.id.tv_light_sleep,Html.fromHtml("浅睡:<font color=\"#000000\">"+item.getLightSleep()+"</font>"));
        helper.setText(R.id.tv_snore_num,Html.fromHtml("打鼾次数:<font color=\"#000000\">"+item.getSnore()+"</font>"));
        helper.setText(R.id.tv_move_num,Html.fromHtml("体动次数:<font color=\"#000000\">"+item.getBodyMovement()+"</font>"));

        heartRateBeanList = new ArrayList<>();
        breathRateBeanList = new ArrayList<>();

        List<HealthTaskDetailResponse.DataBean.SleepDataBean.HeartRateBean> list = item.getHeartRate();
        List<String> times = new ArrayList<>();
        for (HealthTaskDetailResponse.DataBean.SleepDataBean.HeartRateBean heartBean : list) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:MM", Locale.forLanguageTag(heartBean.getCreateTime()));
            Date date1 = new Date();
            String createTime = sdf.format(date1);
            heartRateBeanList.add(new HeartRateBean(heartBean.getHeartRate(),createTime));
            breathRateBeanList.add(new BreathRateBean(heartBean.getBreathRate(),createTime));
            times.add(createTime);
        }
        MPChartUtils.initChart(helper.getView(R.id.lc_heart_rate),times,String.valueOf(item.getHighHeartRate()),String.valueOf(item.getLowHeartRate()),String.valueOf(Math.round(item.getAverageHeartRate())));
        MPChartUtils.initChart(helper.getView(R.id.lc_breath_rate),times,String.valueOf(item.getHighBreathRate()),String.valueOf(item.getLowBreathRate()),String.valueOf(Math.round(item.getAverageBreathRate())));
        MPChartUtils.showHeartRateLineChart(helper.getView(R.id.lc_heart_rate),heartRateBeanList,"心率(bmp)", Color.BLUE);
        MPChartUtils.showBreathRateLineChart(helper.getView(R.id.lc_breath_rate),breathRateBeanList,"呼吸率(次)", Color.BLUE);
    }
}
