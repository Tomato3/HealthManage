package com.example.healthmanage.ui.activity.healthrecord.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.StringUtils;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentHealthHistoryBinding;
import com.example.healthmanage.databinding.FragmentInformationBinding;
import com.example.healthmanage.ui.activity.healthrecord.HealthRecordActivity;
import com.example.healthmanage.ui.activity.healthrecord.HealthRecordViewModel;
import com.example.healthmanage.ui.activity.healthrecord.response.HealthRecordResponse;


/**
 * 健康史
 */
public class HealthHistoryFragment  extends BaseFragment<FragmentHealthHistoryBinding, HealthRecordViewModel> {
    @Override
    protected void initData() {
    }

    public void setHealthData(HealthRecordResponse.DataBean dataBean){
        if (dataBean != null){
            if (!StringUtils.isEmpty(dataBean.getHistoryDisease())){
                dataBinding.tvHistoryDisease.setText(dataBean.getHistoryDisease());
            }
            if (!StringUtils.isEmpty(dataBean.getFamilyHistoryDisease())){
                dataBinding.tvHistoryFamily.setText(dataBean.getFamilyHistoryDisease());
            }
            if (!StringUtils.isEmpty(dataBean.getAllergy()) || !StringUtils.isEmpty(dataBean.getDrugAllergy()) ||!StringUtils.isEmpty(dataBean.getFoodAllergy()) ||!StringUtils.isEmpty(dataBean.getContactAllergy())){
                dataBinding.layoutAllergy.setVisibility(View.VISIBLE);
                dataBinding.tvAllAllergy.setVisibility(View.GONE);
                if (!StringUtils.isEmpty(dataBean.getDrugAllergy())){
                    dataBinding.tvHistoryDrugAllergy.setVisibility(View.VISIBLE);
                    dataBinding.tvHistoryDrugAllergy.setText("药物过敏:"+dataBean.getDrugAllergy());
                }else {
                    dataBinding.tvHistoryDrugAllergy.setVisibility(View.GONE);
                }
                if (!StringUtils.isEmpty(dataBean.getFoodAllergy())){
                    dataBinding.tvHistoryFoodAllergy.setVisibility(View.VISIBLE);
                    dataBinding.tvHistoryFoodAllergy.setText("食物过敏:"+dataBean.getFoodAllergy());
                }else {
                    dataBinding.tvHistoryFoodAllergy.setVisibility(View.GONE);
                }
                if (!StringUtils.isEmpty(dataBean.getContactAllergy())){
                    dataBinding.tvHistoryContactAllergy.setVisibility(View.VISIBLE);
                    dataBinding.tvHistoryContactAllergy.setText("药物过敏:"+dataBean.getContactAllergy());
                }else {
                    dataBinding.tvHistoryContactAllergy.setVisibility(View.GONE);
                }
                if (!StringUtils.isEmpty(dataBean.getAllergy())){
                    dataBinding.tvHistoryAddAllergy.setVisibility(View.VISIBLE);
                    dataBinding.tvHistoryAddAllergy.setText("药物过敏:"+dataBean.getAllergy());
                }else {
                    dataBinding.tvHistoryAddAllergy.setVisibility(View.GONE);
                }
            }else {
                dataBinding.layoutAllergy.setVisibility(View.GONE);
                dataBinding.tvAllAllergy.setVisibility(View.VISIBLE);
            }
            if (!StringUtils.isEmpty(dataBean.getHistoryOperation())){
                dataBinding.tvHistoryOpera.setText(dataBean.getHistoryOperation());
            }
            if (!StringUtils.isEmpty(dataBean.getHistoryMedication())){
                dataBinding.tvHistoryMedicine.setText(dataBean.getHistoryMedication());
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
    protected void registerUIChangeLiveDataCallBack() {
        super.registerUIChangeLiveDataCallBack();
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_health_history;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}
