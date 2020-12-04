package com.example.healthmanage.ui.activity.login;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.bean.network.response.LoginResponse;
import com.example.healthmanage.bean.network.response.SmsCodeResponse;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.main.MainActivity;
import com.example.healthmanage.ui.activity.registerorforget.RegisterOrForgetActivity;
import com.example.healthmanage.utils.ToolUtil;


public class LoginViewModel extends BaseViewModel {

    public MutableLiveData<String> phone = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");
    public MutableLiveData<String> code = new MutableLiveData<>("");
    public MutableLiveData<String> tip = new MutableLiveData<>("");
    public MutableLiveData<Integer> tipImg = new MutableLiveData<>();
    public MutableLiveData<Boolean> tipColor = new MutableLiveData<>();
    public MutableLiveData<Boolean> autoLogin = new MutableLiveData<>(false);

    private UsersRemoteSource usersRemoteSource;
    private LoginCallback loginCallback;
    private Bundle bundle;

    public LoginViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    /**
     * 跳转首页
     */
    public void jumpToMainActivity() {
        usersRemoteSource.loginByPassword(phone.getValue(), password.getValue()
                , "9", new UsersInterface.LoginResponseCallback() {
                    @Override
                    public void loginSucceed(LoginResponse loginResponse) {
                        if (loginCallback != null) {
                            loginCallback.loginSucceed(phone.getValue(), password.getValue(),
                                    autoLogin.getValue(), loginResponse);
                        }
                        startActivity(MainActivity.class);
                    }

                    @Override
                    public void loginFailed(String msg) {
                        getUiChangeEvent().getToastTxt().setValue(msg);
                    }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {
                    }
                });
    }

    public void getSmsCode() {
        if (TextUtils.isEmpty(phone.getValue())) {
            tipImg.setValue(R.drawable.tip_fail);
            tip.setValue("手机号码不能为空");
            tipColor.setValue(false);
        } else if (!ToolUtil.isMobileNO(phone.getValue())) {
            tipImg.setValue(R.drawable.tip_fail);
            tip.setValue("请输入正确的手机号码");
            tipColor.setValue(false);
        } else {
            usersRemoteSource.getSmsCode(phone.getValue(), new UsersInterface.getSmsCodeCallback() {
                @Override
                public void sendSucceed(SmsCodeResponse smsCodeResponse) {
                    tipImg.setValue(R.drawable.tip_success);
                    tip.setValue("验证码已发送，请耐心等待");
                    tipColor.setValue(true);
                }

                @Override
                public void sendFailed(String msg) {
                    tipImg.setValue(R.drawable.tip_fail);
                    tip.setValue(msg);
                    tipColor.setValue(false);
                }

                @Override
                public void error(ExceptionHandle.ResponseException e) {
                    tipImg.setValue(R.drawable.tip_fail);
                    tip.setValue(e.getMessage());
                    tipColor.setValue(false);
                }
            });

        }
    }

    /**
     * 跳转注册页面
     */
    public void jumpToRegisterOrForgetActivity(int type) {
        bundle = new Bundle();
        bundle.putInt("Type", type);
        startActivity(RegisterOrForgetActivity.class, bundle);

    }

    public void setCallback(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }
}
