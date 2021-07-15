package com.example.healthmanage.ui.activity.famousDoctorHall.ui;

import android.content.Context;
import android.os.Bundle;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityHospitalSurveyBinding;
import com.example.healthmanage.ui.activity.famousDoctorHall.DoctorHallViewModel;
import com.example.healthmanage.widget.TitleToolBar;

/**
 * desc:医院概况
 * date:2021/7/8 16:01
 * author:bWang
 */
public class HospitalSurveyActivity extends BaseActivity<ActivityHospitalSurveyBinding, DoctorHallViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context mContext;
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private String hospitalSurvey;
    private String hospitalAddress;
    private String hospitalPhone;

    @Override
    protected void initData() {
        mContext = HospitalSurveyActivity.this;
        mTitleToolBar.setTitle("医院概况");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutHospitalSurveyTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);

        hospitalSurvey = getIntent().getStringExtra("survey");
        hospitalAddress = getIntent().getStringExtra("address");
        hospitalPhone = getIntent().getStringExtra("phone");

    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.tvHospitalSurvey.setText(hospitalSurvey);
        dataBinding.tvHospitalAddress.setText(hospitalAddress);
        dataBinding.tvHospitalPhone.setText(hospitalPhone);
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        mTitleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_hospital_survey;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
