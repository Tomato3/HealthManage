package com.example.healthmanage.ui.activity.healthrecord;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityHealthRecordBinding;
import com.example.healthmanage.ui.activity.healthrecord.fragment.HealthDataFragment;
import com.example.healthmanage.ui.activity.healthrecord.fragment.HealthHistoryFragment;
import com.example.healthmanage.ui.activity.healthrecord.fragment.HealthMeansFragment;
import com.example.healthmanage.ui.activity.healthrecord.fragment.InformationFragment;
import com.example.healthmanage.ui.activity.healthrecord.fragment.LifeCustomFragment;
import com.example.healthmanage.ui.activity.healthrecord.response.HealthRecordResponse;
import com.example.healthmanage.widget.TitleToolBar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康档案
 */
public class HealthRecordActivity extends BaseActivity<ActivityHealthRecordBinding,HealthRecordViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<Fragment> fragmentList;
    private InformationFragment informationFragment;
    private HealthDataFragment healthDataFragment;
    private LifeCustomFragment lifeCustomFragment;
    private HealthHistoryFragment healthHistoryFragment;
    private HealthMeansFragment healthMeansFragment;
    private int currentIndex = 0, beforeIndex = 0;
    private int userId;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {
        userId = getIntent().getIntExtra("userId",0);
        titleToolBar.setTitle("健康档案");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        informationFragment = new InformationFragment();
        healthDataFragment = new HealthDataFragment();
        lifeCustomFragment = new LifeCustomFragment();
        healthHistoryFragment = new HealthHistoryFragment();
        healthMeansFragment = new HealthMeansFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(informationFragment);
        fragmentList.add(healthDataFragment);
        fragmentList.add(lifeCustomFragment);
        fragmentList.add(healthHistoryFragment);
        fragmentList.add(healthMeansFragment);

        final FragmentTransaction mTransation = getSupportFragmentManager().beginTransaction();
        mTransation.add(R.id.fragmentView, informationFragment);
        mTransation.add(R.id.fragmentView, healthDataFragment);
        mTransation.add(R.id.fragmentView, lifeCustomFragment);
        mTransation.add(R.id.fragmentView, healthHistoryFragment);
        mTransation.add(R.id.fragmentView, healthMeansFragment);
        mTransation.hide(informationFragment);
        mTransation.hide(healthDataFragment);
        mTransation.hide(lifeCustomFragment);
        mTransation.hide(healthHistoryFragment);
        mTransation.hide(healthMeansFragment);
        mTransation.commitAllowingStateLoss();

        mTransation.show(fragmentList.get(currentIndex));

        viewModel.getUserInfo(userId);

    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.dataBeanMutableLiveData.observe(this, new Observer<HealthRecordResponse.DataBean>() {
            @Override
            public void onChanged(HealthRecordResponse.DataBean dataBean) {
                if (dataBean != null){
                    informationFragment.setHealthData(dataBean);
                    healthDataFragment.setHealthData(dataBean);
                    lifeCustomFragment.setHealthData(dataBean);
                    healthHistoryFragment.setHealthData(dataBean);
                    healthMeansFragment.setUserId(userId);
                }else {
                    informationFragment.setHealthData(null);
                    healthDataFragment.setHealthData(null);
                    lifeCustomFragment.setHealthData(null);
                    healthHistoryFragment.setHealthData(null);
                    healthMeansFragment.setUserId(0);
                }
            }
        });
        dataBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setShowFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setShowFragment(int position) {
        beforeIndex = currentIndex;
        if (currentIndex != position){
            currentIndex = position;
            FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
            mTransaction.hide(fragmentList.get(beforeIndex));
            mTransaction.show(fragmentList.get(currentIndex));
            mTransaction.commitAllowingStateLoss();
        }
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
        return R.layout.activity_health_record;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
