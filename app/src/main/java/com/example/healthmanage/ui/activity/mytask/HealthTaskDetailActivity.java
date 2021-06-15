package com.example.healthmanage.ui.activity.mytask;

import android.os.Bundle;
import android.text.Html;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.bean.network.response.AirResponse;
import com.example.healthmanage.bean.network.response.NursingResponse;
import com.example.healthmanage.databinding.ActivityTaskDataBinding;
import com.example.healthmanage.ui.activity.memberdetail.adapter.AirAdapter;
import com.example.healthmanage.ui.activity.memberdetail.adapter.SpiritAdapter;
import com.example.healthmanage.ui.activity.memberdetail.response.HealthDataResponse;
import com.example.healthmanage.ui.activity.memberdetail.response.SpiritHealthResponse;
import com.example.healthmanage.ui.activity.mytask.adapter.AirTaskAdapter;
import com.example.healthmanage.ui.activity.mytask.adapter.SpiritTaskAdapter;
import com.example.healthmanage.ui.activity.mytask.response.HealthTaskDetailResponse;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.DropdownNewBar;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 异常数据查询
 */
public class HealthTaskDetailActivity extends BaseActivity<ActivityTaskDataBinding,MyNewTaskViewModel> implements TitleToolBar.OnTitleIconClickCallBack{
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<HealthTaskDetailResponse.DataBean.SongARIListBean> tEnvironmentBeanList;
    private List<HealthTaskDetailResponse.DataBean.SleepDataBean> spiritListData;
    private HealthTaskDetailResponse.DataBean.SongEnDataBean todayHealthData;
    private HealthTaskDetailResponse.DataBean.SongHuLiYiDataBean bodyHealthData;
    private AirTaskAdapter airAdapter;
    private SpiritTaskAdapter spiritAdapter;
    private int userId;

    @Override
    protected void initData() {
        userId = getIntent().getIntExtra("userId",0);
        titleToolBar.setTitle("异常数据");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.includeTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        tEnvironmentBeanList = new ArrayList<>();
        airAdapter = new AirTaskAdapter(tEnvironmentBeanList);
        dataBinding.recycleviewTodayenvironment.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recycleviewTodayenvironment.setAdapter(airAdapter);

        spiritListData = new ArrayList<>();
        spiritAdapter = new SpiritTaskAdapter(spiritListData);
        dataBinding.rlSpiritHealth.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.rlSpiritHealth.setAdapter(spiritAdapter);

        todayHealthData = new HealthTaskDetailResponse.DataBean.SongEnDataBean();
        bodyHealthData = new HealthTaskDetailResponse.DataBean.SongHuLiYiDataBean();

        viewModel.getExceptionData(userId);

        DropdownNewBar dropdownBarTodayEnvironment = new DropdownNewBar("今日环境", false, false);
        DropdownNewBar dropdownBarTodayHealth = new DropdownNewBar("今日健康", false, false);
        DropdownNewBar dropdownBarBodyHealth = new DropdownNewBar("身体健康",  false, false);
        DropdownNewBar dropdownBarSpiritHealth = new DropdownNewBar("精神健康", false, false);
        List<DropdownNewBar> dropdownNewBarList = new ArrayList<>();
        dropdownNewBarList = Arrays.asList(dropdownBarTodayEnvironment,dropdownBarTodayHealth,dropdownBarBodyHealth,dropdownBarSpiritHealth);
        viewModel.dropdownBarLists.setValue(dropdownNewBarList);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        if (tEnvironmentBeanList!=null && tEnvironmentBeanList.size()>0){
            dataBinding.includeTodayEnvironmentNull.setVisibility(View.GONE);
        }
        viewModel.dataItemAir.observe(this, new Observer<List<HealthTaskDetailResponse.DataBean.SongARIListBean>>() {
            @Override
            public void onChanged(List<HealthTaskDetailResponse.DataBean.SongARIListBean> songARIListBeans) {
                if (songARIListBeans!=null && songARIListBeans.size()>0){
                    if (tEnvironmentBeanList!=null && tEnvironmentBeanList.size()>0){
                        tEnvironmentBeanList.clear();
                    }
                    tEnvironmentBeanList.addAll(songARIListBeans);
                    airAdapter.notifyDataSetChanged();
                }
            }
        });
        viewModel.todayAirVisible.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    dataBinding.includeTodayEnvironment.tvCollapse.setVisibility(View.VISIBLE);
                }else {
                    dataBinding.includeTodayEnvironment.tvCollapse.setVisibility(View.GONE);
                }
            }
        });
        viewModel.dataItemSpiritHealth.observe(this, new Observer<List<HealthTaskDetailResponse.DataBean.SleepDataBean>>() {
            @Override
            public void onChanged(List<HealthTaskDetailResponse.DataBean.SleepDataBean> sleepDataBeans) {
                if (sleepDataBeans!=null && sleepDataBeans.size()>0){
                    if (spiritListData != null && spiritListData.size()>0){
                        spiritListData.clear();
                    }
                    spiritListData.addAll(sleepDataBeans);
                    spiritAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.rlSpiritHealth.setVisibility(View.GONE);
                }
            }
        });
        viewModel.spiritVisiable.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    dataBinding.includeSpiritHealth.tvCollapse.setVisibility(View.VISIBLE);
                }else {
                    dataBinding.includeSpiritHealth.tvCollapse.setVisibility(View.GONE);
                }
            }
        });
        viewModel.dataItemTodayHealth.observe(this, new Observer<HealthTaskDetailResponse.DataBean.SongEnDataBean>() {
            @Override
            public void onChanged(HealthTaskDetailResponse.DataBean.SongEnDataBean songEnDataBean) {
                if (songEnDataBean != null){
                    todayHealthData = songEnDataBean;
                    dataBinding.layoutTodayHealth.tvHealth1.setText(Html.fromHtml("步数:<font color=\"#000000\">"+todayHealthData.getSteps()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth2.setText(Html.fromHtml("消耗:<font color=\"#000000\">"+todayHealthData.getCalorie()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth3.setText(Html.fromHtml("高压:<font color=\"#000000\">"+todayHealthData.getHighPressure()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth4.setText(Html.fromHtml("低压:<font color=\"#000000\">"+todayHealthData.getLowPressure()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth5.setText(Html.fromHtml("血氧:<font color=\"#000000\">"+todayHealthData.getSaturation()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth6.setText(Html.fromHtml("血糖:<font color=\"#000000\">"+todayHealthData.getPPG()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth7.setText(Html.fromHtml("体温:<font color=\"#000000\">"+todayHealthData.getTemperature()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth8.setText(Html.fromHtml("心率:<font color=\"#000000\">"+todayHealthData.getHeartRate()+"</font>"));
                    dataBinding.layoutTodayHealth.divisionLine4.setVisibility(View.GONE);
                    dataBinding.layoutTodayHealth.layout5.setVisibility(View.GONE);
                }
            }
        });
        viewModel.todayHealthVisible.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    dataBinding.includeTodayHealth.tvCollapse.setVisibility(View.VISIBLE);
                    dataBinding.layoutTodayHealth.getRoot().setVisibility(View.VISIBLE);
                }else {
                    dataBinding.includeTodayHealth.tvCollapse.setVisibility(View.GONE);
                    dataBinding.layoutTodayHealth.getRoot().setVisibility(View.GONE);
                }
            }
        });

        viewModel.dataItemBodyHealth.observe(this, new Observer<HealthTaskDetailResponse.DataBean.SongHuLiYiDataBean>() {
            @Override
            public void onChanged(HealthTaskDetailResponse.DataBean.SongHuLiYiDataBean dataBean) {
                if (dataBean!=null){
                    bodyHealthData = dataBean;
                    dataBinding.layoutBodyHealth.tvDeviceName.setText("护理仪");
                    dataBinding.layoutBodyHealth.tvHealth1.setText(Html.fromHtml("ID:<font color=\"#000000\">"+ ToolUtil.isNull(bodyHealthData.getDeviceId())+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth2.setText(Html.fromHtml("设定风温:<font color=\"#000000\">"+stateToString(bodyHealthData.getSetWindT())+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth3.setText(Html.fromHtml("设定水压:<font color=\"#000000\">"+stateToString(bodyHealthData.getSetWaterP())+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth4.setText(Html.fromHtml("设定水温:<font color=\"#000000\">"+stateToString(bodyHealthData.getSetWaterT())+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth5.setText(Html.fromHtml("当前风温:<font color=\"#000000\">"+ToolUtil.isNull(bodyHealthData.getCurrentWindT())+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth6.setText(Html.fromHtml("清洁空气状态:<font color=\"#000000\">"+ToolUtil.isNull(bodyHealthData.getCleanAirStatus() == 0 ? "等待中" : "进行中")+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth7.setText(Html.fromHtml("当前空气状态:<font color=\"#000000\">"+ToolUtil.isNull(bodyHealthData.getCurrentWorkStatus())+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth8.setText(Html.fromHtml("当前工作状态:<font color=\"#000000\">"+ToolUtil.isNull(bodyHealthData.getCurrentWorkStatus())+"</font>"));
                    dataBinding.layoutBodyHealth.divisionLine4.setVisibility(View.GONE);
                    dataBinding.layoutBodyHealth.layout5.setVisibility(View.GONE);
                }
            }
        });

        viewModel.bodyHealthVisible.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    dataBinding.layoutBodyHealth.getRoot().setVisibility(View.VISIBLE);
                    dataBinding.includeBodyHealth.tvCollapse.setVisibility(View.VISIBLE);
                }else {
                    dataBinding.includeBodyHealth.tvCollapse.setVisibility(View.GONE);
                    dataBinding.layoutBodyHealth.getRoot().setVisibility(View.GONE);
                }
            }
        });

        dataBinding.includeTodayEnvironment.tvCollapse.setOnClickListener(v -> {
            dataBinding.includeTodayEnvironment.tvExpand.setVisibility(View.VISIBLE);
            dataBinding.includeTodayEnvironment.tvCollapse.setVisibility(View.GONE);
            dataBinding.recycleviewTodayenvironment.setVisibility(View.GONE);
        });

        dataBinding.includeTodayEnvironment.tvExpand.setOnClickListener(v -> {
            dataBinding.includeTodayEnvironment.tvExpand.setVisibility(View.GONE);
            dataBinding.includeTodayEnvironment.tvCollapse.setVisibility(View.VISIBLE);
            dataBinding.recycleviewTodayenvironment.setVisibility(View.VISIBLE);
        });

        dataBinding.includeTodayHealth.tvCollapse.setOnClickListener(v -> {
            dataBinding.includeTodayHealth.tvExpand.setVisibility(View.VISIBLE);
            dataBinding.includeTodayHealth.tvCollapse.setVisibility(View.GONE);
            dataBinding.layoutTodayHealth.getRoot().setVisibility(View.GONE);
        });

        dataBinding.includeTodayHealth.tvExpand.setOnClickListener(v -> {
            dataBinding.includeTodayHealth.tvExpand.setVisibility(View.GONE);
            dataBinding.includeTodayHealth.tvCollapse.setVisibility(View.VISIBLE);
            dataBinding.layoutTodayHealth.getRoot().setVisibility(View.VISIBLE);
        });

        dataBinding.includeBodyHealth.tvCollapse.setOnClickListener(v -> {
            dataBinding.includeBodyHealth.tvExpand.setVisibility(View.VISIBLE);
            dataBinding.includeBodyHealth.tvCollapse.setVisibility(View.GONE);
            dataBinding.layoutBodyHealth.getRoot().setVisibility(View.GONE);
        });

        dataBinding.includeBodyHealth.tvExpand.setOnClickListener(v -> {
            dataBinding.includeBodyHealth.tvExpand.setVisibility(View.GONE);
            dataBinding.includeBodyHealth.tvCollapse.setVisibility(View.VISIBLE);
            dataBinding.layoutBodyHealth.getRoot().setVisibility(View.VISIBLE);
        });

        dataBinding.includeSpiritHealth.tvCollapse.setOnClickListener(v -> {
            dataBinding.includeSpiritHealth.tvExpand.setVisibility(View.VISIBLE);
            dataBinding.includeSpiritHealth.tvCollapse.setVisibility(View.GONE);
            dataBinding.rlSpiritHealth.setVisibility(View.GONE);
        });

        dataBinding.includeSpiritHealth.tvExpand.setOnClickListener(v -> {
            dataBinding.includeSpiritHealth.tvExpand.setVisibility(View.GONE);
            dataBinding.includeSpiritHealth.tvCollapse.setVisibility(View.VISIBLE);
            dataBinding.rlSpiritHealth.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_task_data;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    private String stateToString(int state) {
        String string = "";
        switch (state) {
            case 0:
                string = "低挡";
                break;
            case 1:
                string = "中档";
                break;
            case 2:
                string = "高挡";
                break;
        }
        return string;
    }
}
