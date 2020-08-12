package com.example.healthmanage.ui.activity.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Observer;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivitySplashBinding;
import com.example.healthmanage.ui.activity.login.LoginActivity;

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
                    start(SplashActivity.this, imageView);
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

    public static void start(Activity context, ImageView imageView) {
        Intent intent = new Intent(context, LoginActivity.class);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(context, imageView,
                        context.getString(R.string.transition_logo_img));
        //与xml文件对应
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }


}
