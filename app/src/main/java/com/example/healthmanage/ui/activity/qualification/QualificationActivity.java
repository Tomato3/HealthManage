package com.example.healthmanage.ui.activity.qualification;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.StringUtils;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityQualificationBinding;
import com.example.healthmanage.ui.fragment.qualification.FirstStepFragment;
import com.example.healthmanage.ui.fragment.qualification.SecondStepFragment;
import com.example.healthmanage.ui.fragment.qualification.ThirdStepFragment;
import com.example.healthmanage.ui.fragment.qualification.bean.DoctorInfo;
import com.example.healthmanage.ui.fragment.qualification.bean.UpdateDoctorInfo;
import com.example.healthmanage.utils.Constants;
import com.example.healthmanage.utils.SPUtil;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 资质认证
 */
public class QualificationActivity extends BaseActivity<ActivityQualificationBinding,
        QualificationViewModel> implements View.OnClickListener {

    private List<Fragment> mFragments;
    private DoctorInfo doctorInfo;
    private UpdateDoctorInfo updateDoctorInfo;
    private String phone;
    private String type;
    private String id;
    private int currentPosition;

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
        }
    }

    @Override
    protected void initData() {
        phone = getIntent().getStringExtra("phone");
        type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        initFragment();
        doctorInfo = new DoctorInfo();
        updateDoctorInfo = new UpdateDoctorInfo();
        if (!StringUtils.isEmpty(phone)){
            doctorInfo.setPhone(phone);
        }
        if (!StringUtils.isEmpty(id)){
            updateDoctorInfo.setId(Integer.valueOf(id));
        }
    }

    public String getType() {
        return type;
    }

    public DoctorInfo getDoctorInfo() {
        return doctorInfo;
    }

    public UpdateDoctorInfo getUpdateDoctorInfo() {
        return updateDoctorInfo;
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        if (type==null){
            currentPosition = 0;
        }else if (type.equals("wait")){
            currentPosition = 2;
        }else if (type.equals("failed")){
            currentPosition = 2;
        }
        mFragments = new ArrayList<>();
        mFragments.add(new FirstStepFragment());
        mFragments.add(new SecondStepFragment());
        mFragments.add(new ThirdStepFragment());

        changeFragment(currentPosition);
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
                LiveEventBus.get("type",String.class).observe(QualificationActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if (s.equals("wait")){
                            LiveEventBus.get("type1").postAcrossProcess("wait");
                            Log.e("type1==========",type);
                        }
                    }
                });
                changeFragment(integer);
                dataBinding.stepView.setPosition(integer);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_to_main:
                String phone = SPUtil.getPhone(this);
                String password = SPUtil.getPassword(this);
                Constants.ROLE_ID = SPUtil.getRoleId(this);
                viewModel.toMain(phone, password, Constants.ROLE_ID);
                break;
            case R.id.btn_commit:
//                LiveEventBus.get("ChangeFragment").post(2);
                break;
        }
    }

}
