package com.example.healthmanage.ui.activity.healthrecord.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blankj.utilcode.util.StringUtils;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentHealthDataBinding;
import com.example.healthmanage.databinding.FragmentInformationBinding;
import com.example.healthmanage.ui.activity.healthrecord.HealthRecordViewModel;
import com.example.healthmanage.ui.activity.healthrecord.response.HealthRecordResponse;

/**
 * 健康数据Fragment
 */
public class HealthDataFragment extends BaseFragment<FragmentHealthDataBinding, HealthRecordViewModel> {
    @Override
    protected void initData() {

    }

    public void setHealthData(HealthRecordResponse.DataBean dataBean){
        if (dataBean !=null){
            if (!StringUtils.isEmpty(dataBean.getHeight())){
                dataBinding.tvHeight.setText(dataBean.getHeight()+"cm");
            }
            if (!StringUtils.isEmpty(dataBean.getWeight())){
                dataBinding.tvWeight.setText(dataBean.getWeight()+"kg");
            }
            if (!StringUtils.isEmpty(dataBean.getBust())){
                dataBinding.tvBust.setText(dataBean.getBust()+"cm");
            }
            if (!StringUtils.isEmpty(dataBean.getWaistline())){
                dataBinding.tvWaistline.setText(dataBean.getWaistline()+"cm");
            }
            if (!StringUtils.isEmpty(dataBean.getHipline())){
                dataBinding.tvHipLine.setText(dataBean.getHipline()+"cm");
            }
            if (!StringUtils.isEmpty(dataBean.getHighPressure())){
                dataBinding.tvHighPressure.setText(dataBean.getHighPressure()+"mmHg");
            }
            if (!StringUtils.isEmpty(dataBean.getLowPressure())){
                dataBinding.tvLowPressure.setText(dataBean.getLowPressure()+"mmHg");
            }
            if (StringUtils.isEmpty(dataBean.getCheckInterval())&&StringUtils.isEmpty(dataBean.getBloodSugar())){
                return;
            }else if (!StringUtils.isEmpty(dataBean.getBloodSugar())){
                if (!StringUtils.isEmpty(dataBean.getBloodSugar())){
                    dataBinding.tvBloodSugar.setText(dataBean.getBloodSugar()+"mmol/L");
                }
            }else {
                dataBinding.tvBloodSugar.setText(dataBean.getCheckInterval()+"/"+dataBean.getBloodSugar()+"mmol/L");
            }
        }

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void initObserver() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_health_data;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}
