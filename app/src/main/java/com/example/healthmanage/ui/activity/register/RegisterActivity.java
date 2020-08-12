package com.example.healthmanage.ui.activity.register;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import androidx.lifecycle.Observer;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityRegisterBinding;
import com.example.healthmanage.widget.TitleToolBar;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();

    @Override
    protected void initData() {
        titleToolBar.setTitle("注册");
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
    }


    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.eyeState.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (viewModel.eyeState.getValue()) {
                    dataBinding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    dataBinding.etPassword.setSelection(dataBinding.etPassword.getText().length());
                    dataBinding.ivEye.setImageResource(R.drawable.activity_login_password_open);
                } else {
                    dataBinding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    dataBinding.etPassword.setSelection(dataBinding.etPassword.getText().length());
                    dataBinding.ivEye.setImageResource(R.drawable.activity_login_password_closed);
                }
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_register;
    }

}
