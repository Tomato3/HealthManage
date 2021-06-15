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
import com.example.healthmanage.ui.activity.qualification.QualificationActivity;
import com.example.healthmanage.utils.CountDownTimerUtils;
import com.example.healthmanage.utils.SPUtil;

/**
 * 注册界面
 */
public class RegisterOrForgetActivity extends BaseActivity<ActivityRegisterOrForgetBinding, RegisterOrForgetViewModel> {
    private CountDownTimerUtils mCountDownTimerUtils;

    @Override
    protected void initData() {
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
//                viewModel.countdown.observe(this,countdown->{
//                    dataBinding.btnGetRegistercode.setText(countdown);
//                    viewModel.isGetCodeStart.setValue(false);
//                    viewModel.isGetFinish.observe(this,isFinish->{
//                        if (isFinish){
//                            dataBinding.btnGetRegistercode.setClickable(true);
//                        }else {
//                            dataBinding.btnGetRegistercode.setClickable(false);
//                        }
//                    });
//                });

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
//                SPUtil.savePhone(viewModel.phone.getValue(),RegisterOrForgetActivity.this);
//                Intent intent = new Intent(RegisterOrForgetActivity.this, QualificationActivity.class);
//                intent.putExtra("phone",viewModel.phone.getValue());
//                startActivity(intent);
                if (StringUtils.isEmpty(viewModel.phone.getValue()) || StringUtils.isEmpty(viewModel.password.getValue()) || StringUtils.isEmpty(viewModel.verificationCode.getValue())){
                    return;
                }else {
                    viewModel.register();
                }
            }
        });
        viewModel.isRegisterSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Intent intent = new Intent(RegisterOrForgetActivity.this, QualificationActivity.class);
                    intent.putExtra("phone",viewModel.phone.getValue());
                    SPUtil.savePhone(viewModel.phone.getValue(),RegisterOrForgetActivity.this);
                    SPUtil.savePassword(viewModel.password.getValue(),RegisterOrForgetActivity.this);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
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
