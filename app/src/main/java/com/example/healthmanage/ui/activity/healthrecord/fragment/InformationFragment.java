package com.example.healthmanage.ui.activity.healthrecord.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blankj.utilcode.util.StringUtils;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentInformationBinding;
import com.example.healthmanage.ui.activity.healthrecord.HealthRecordViewModel;
import com.example.healthmanage.ui.activity.healthrecord.response.HealthRecordResponse;

/**
 * 基本信息Fragment
 */
public class InformationFragment extends BaseFragment<FragmentInformationBinding, HealthRecordViewModel> {

    @Override
    protected void initData() {
    }

    public void setHealthData(HealthRecordResponse.DataBean dataBean){
        if (dataBean!=null){
            if (!StringUtils.isEmpty(dataBean.getName())){
                dataBinding.tvName.setText(dataBean.getName());
            }
            if (!StringUtils.isEmpty(dataBean.getIdType())){
                dataBinding.tvIdType.setText(dataBean.getIdType());
            }
            if (!StringUtils.isEmpty(dataBean.getIdNumber())) {
                dataBinding.tvIdNumber.setText(dataBean.getIdNumber());
            }
            if (!StringUtils.isEmpty(dataBean.getSex())) {
                dataBinding.tvSex.setText(dataBean.getSex());
            }
            if (!StringUtils.isEmpty(dataBean.getBirthday())) {
                dataBinding.tvBirthday.setText(dataBean.getBirthday());
            }
            if (!StringUtils.isEmpty(dataBean.getPhone())){
                dataBinding.tvPhone.setText(dataBean.getPhone());
            }
            if (!StringUtils.isEmpty(dataBean.getBloodType())){
                dataBinding.tvBloodType.setText(dataBean.getBloodType());
            }
            if (!StringUtils.isEmpty(dataBean.getMarital())){
                dataBinding.tvMarital.setText(dataBean.getMarital());
            }
            if (!StringUtils.isEmpty(dataBean.getRegion())){
                dataBinding.tvRegion.setText(dataBean.getRegion());
            }
            if (!StringUtils.isEmpty(dataBean.getAddress())){
                dataBinding.tvAddress.setText(dataBean.getAddress());
            }
            if (!StringUtils.isEmpty(dataBean.getOccupation())){
                dataBinding.tvOccupation.setText(dataBean.getOccupation());
            }
            if (!StringUtils.isEmpty(dataBean.getWork())){
                dataBinding.tvWork.setText(dataBean.getWork());
            }
            if (!StringUtils.isEmpty(dataBean.getSocialSecurity())){
                dataBinding.tvSocialSecurity.setText(dataBean.getSocialSecurity());
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
        return R.layout.fragment_information;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}
