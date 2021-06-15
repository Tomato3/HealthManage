package com.example.healthmanage.ui.activity.healthreport.ui;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.StringUtils;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityReportDetailBinding;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportDetailResponse;
import com.example.healthmanage.ui.activity.healthreport.viewmodel.HealthReportViewModel;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.TitleToolBar;

/**
 * 健康报告详情
 */
public class HealthReportDetailActivity extends BaseActivity<ActivityReportDetailBinding, HealthReportViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private int id;
    private String name;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id",0);
        name = getIntent().getStringExtra("name");
        if (StringUtils.isEmpty(name)){
            titleToolBar.setTitle("健康报告");
        }else {
            titleToolBar.setTitle(name);
        }
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.getHealthReport(id);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.healthReportDetailData.observe(this, new Observer<HealthReportDetailResponse.DataBean>() {
            @Override
            public void onChanged(HealthReportDetailResponse.DataBean dataBean) {
                if (dataBean!=null){
                    viewModel.bloodPressure.postValue(dataBean.getBloodPressure());
                    viewModel.bloodSugar.postValue(dataBean.getBloodSugar());
                    viewModel.bloodOxygen.postValue(dataBean.getBloodOxygen());
                    viewModel.temprature.postValue(dataBean.getTemperature());
                    viewModel.sportStatus.postValue(dataBean.getSport());
                    viewModel.sleepStatus.postValue(dataBean.getSleep());
                    viewModel.reportStartTime.postValue(dataBean.getStartTime());
                    viewModel.reportEndTime.postValue(dataBean.getEndTime());
                    viewModel.reportCreateTime.postValue(ToolUtil.timeStampToDate(String.valueOf(dataBean.getCreateTime()),null));
                }
            }
        });
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_report_detail;
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
