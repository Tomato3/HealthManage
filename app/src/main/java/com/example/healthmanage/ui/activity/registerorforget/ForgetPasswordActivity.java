package com.example.healthmanage.ui.activity.registerorforget;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;

import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.StringUtils;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityRegisterOrForgetBinding;
import com.example.healthmanage.ui.activity.login.LoginNewActivity;
import com.example.healthmanage.utils.CountDownTimerUtils;

/**
 * 忘记密码
 */
public class ForgetPasswordActivity extends BaseActivity<ActivityRegisterOrForgetBinding, RegisterOrForgetViewModel> {
    private CountDownTimerUtils mCountDownTimerUtils;

    @Override
    protected void initData() {
        viewModel.title = "设置新密码";
        viewModel.operationTxt = "确认重置";
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        /**监听是否发送验证码成功*/
        viewModel.isGetCodeStart.observe(this,isCodeStart->{
            if (isCodeStart) {
                viewModel.isGetCodeStart.setValue(false);
                mCountDownTimerUtils = new CountDownTimerUtils(dataBinding.btnGetRegistercode, 60000, 1000);
                mCountDownTimerUtils.start();

            }
        });
        /**定时三秒关闭提示*/
        viewModel.isClose.observe(this,isClose->{
            if (isClose){
                dataBinding.ivTips.setVisibility(View.VISIBLE);
                dataBinding.tvTips.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataBinding.ivTips.setVisibility(View.INVISIBLE);
                        dataBinding.tvTips.setVisibility(View.INVISIBLE);
                        viewModel.isClose.setValue(false);
                    }
                },3000);
            }

        });
        //密码可见切换
        dataBinding.cbEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dataBinding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    dataBinding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                dataBinding.etPassword.setSelection(dataBinding.etPassword.getText().length());
            }
        });

        dataBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dataBinding.btnRegisterOrForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(viewModel.phone.getValue()) || StringUtils.isEmpty(viewModel.password.getValue()) || StringUtils.isEmpty(viewModel.verificationCode.getValue())){
                    return;
                }else {
                    viewModel.forgetPwd();
                }
            }
        });
        viewModel.isForgetSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Intent intent = new Intent();
                    intent.putExtra("phone",viewModel.phone.getValue());
                    setResult(RESULT_OK,intent);
                    finish();
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
        return R.layout.activity_register_or_forget;
    }
}
