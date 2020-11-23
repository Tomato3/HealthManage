package com.example.healthmanage.ui.activity.referral;

import android.os.Bundle;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityReferralBinding;


public class ReferralActivity extends BaseActivity<ActivityReferralBinding, ReferralViewModel> {
    @Override
    protected void initData() {

    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_referral;
    }
}
