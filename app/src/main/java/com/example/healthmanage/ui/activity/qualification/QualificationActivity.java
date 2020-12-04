package com.example.healthmanage.ui.activity.qualification;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityQualificationBinding;
import com.example.healthmanage.ui.fragment.qualification.FirstStepFragment;
import com.example.healthmanage.ui.fragment.qualification.SecondStepFragment;
import com.example.healthmanage.ui.fragment.qualification.ThirdStepFragment;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;
import java.util.List;


public class QualificationActivity extends BaseActivity<ActivityQualificationBinding, QualificationViewModel> {

    private List<Fragment> mFragments;

    @Override
    protected void initData() {
        initFragment();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new FirstStepFragment());
        mFragments.add(new SecondStepFragment());
        mFragments.add(new ThirdStepFragment());

        changeFragment(0);
    }

    /**
     * 切换fragment
     *
     * @param position
     */
    private void changeFragment(int position) {
        hideAllFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(position + "");
        if (currentFragment != null) {
            transaction.show(currentFragment);
        } else {
            currentFragment = mFragments.get(position);
            transaction.add(R.id.frameLayout, currentFragment, position + "");
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏所有Fragment
     */
    private void hideAllFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(i + "");
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void initViewListener() {
        super.initViewListener();

    }


    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

        LiveEventBus.get("ChangeFragment", Integer.class).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                changeFragment(integer);
            }
        });
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_qualification;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
