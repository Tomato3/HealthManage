package com.example.healthmanage.ui.activity.splash;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.lifecycle.Observer;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivitySplashBinding;
import com.example.healthmanage.ui.activity.login.LoginActivity;
import com.example.healthmanage.utils.ToolUtil;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    @Override
    protected void initData() {
        viewModel.setCountDownSkip();
    }

    @Override
    public void initViewListener() {
        super.initViewListener();

        viewModel.countdown.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("0s")) {
                    ImageView imageView = findViewById(R.id.iv_logo);
                    ToolUtil.startActivityWithTransition(SplashActivity.this, LoginActivity.class
                            , imageView, null);
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
        return R.layout.activity_splash;
    }


}
