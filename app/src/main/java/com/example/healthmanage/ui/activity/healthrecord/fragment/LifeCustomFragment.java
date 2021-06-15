package com.example.healthmanage.ui.activity.healthrecord.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blankj.utilcode.util.StringUtils;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentInformationBinding;
import com.example.healthmanage.databinding.FragmentLifeCustomBinding;
import com.example.healthmanage.ui.activity.healthrecord.HealthRecordViewModel;
import com.example.healthmanage.ui.activity.healthrecord.response.HealthRecordResponse;

/**
 * 生活习惯
 */
public class LifeCustomFragment  extends BaseFragment<FragmentLifeCustomBinding, HealthRecordViewModel> {
    private String alcohol;
    @Override
    protected void initData() {

    }
    public void setHealthData(HealthRecordResponse.DataBean dataBean){
        if (dataBean!=null){
            if (!StringUtils.isEmpty(dataBean.getSmoking())){
                if (dataBean.getSmoking().contains("戒烟")){
                    dataBinding.tvSmooking.setText(dataBean.getSmoking()+"/"+dataBean.getQuitSmokingYears()+"年");
                }
                dataBinding.tvSmooking.setText(dataBean.getSmoking()+"/吸烟"+dataBean.getSmokingYears()+"年/日均"+dataBean.getSmokingNumber()+"根");
            }
            if (!StringUtils.isEmpty(dataBean.getAlcohol())){
                if (dataBean.getAlcohol().contains("戒酒")){
                    dataBinding.tvDrink.setText(dataBean.getAlcohol()+"/"+dataBean.getQuitDrinkingYears()+"年");
                }else if (dataBean.getAlcohol().contains("不饮酒")){
                    dataBinding.tvDrink.setText(dataBean.getAlcohol());
                }else {
                    alcohol = dataBean.getAlcohol()+"/"+dataBean.getDrinkingYears()+"年";
                    if (!StringUtils.isEmpty(dataBean.getBeer()) || !StringUtils.isEmpty(dataBean.getWhiteAlcohol()) || !StringUtils.isEmpty(dataBean.getRedAlcohol()) || !StringUtils.isEmpty(dataBean.getOtherAlcohol())) {
                        if (!StringUtils.isEmpty(dataBean.getBeer())) {
                            alcohol = alcohol + "/啤酒" + dataBean.getBeer() + "ml";
                        }
                        dataBinding.tvDrink.setText(alcohol);
                        if (!StringUtils.isEmpty(dataBean.getWhiteAlcohol())) {
                            alcohol = alcohol + "/白酒" + dataBean.getWhiteAlcohol() + "ml";
                        }
                        dataBinding.tvDrink.setText(alcohol);
                        if (!StringUtils.isEmpty(dataBean.getRedAlcohol())) {
                            alcohol = alcohol + "/红酒" + dataBean.getRedAlcohol() + "ml";
                        }
                        dataBinding.tvDrink.setText(alcohol);
                        if (!StringUtils.isEmpty(dataBean.getOtherAlcohol())) {
                            alcohol = alcohol + "/其他" + dataBean.getOtherAlcohol() + "ml";
                        }
                    }
                    dataBinding.tvDrink.setText(alcohol);
                }
            }
            if (!StringUtils.isEmpty(dataBean.getDiet())){
                dataBinding.tvDiet.setText(dataBean.getDiet());
            }
            if (!StringUtils.isEmpty(dataBean.getSport())){
                if (dataBean.getSport().contains("不运动")){
                    dataBinding.tvSport.setText(dataBean.getSport());
                }
                dataBinding.tvSport.setText(dataBean.getSport()+"/"+dataBean.getSportHour()+"小时/次");
            }
            if (!StringUtils.isEmpty(dataBean.getSleep())){
                dataBinding.tvSleepTime.setText(dataBean.getSleep());
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
        return R.layout.fragment_life_custom;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}
