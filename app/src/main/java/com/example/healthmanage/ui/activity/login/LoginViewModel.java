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
import com.example.healthmanage.ui.activity.qualification.response.DoctorInfoResponse;
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
    /**判断是否发送验证码成功*/
    public MutableLiveData<Boolean> isGetCodeSuccess = new MutableLiveData<>(false);
    /**判断是否关闭提示tip*/
    public MutableLiveData<Boolean> isClose = new MutableLiveData<>(false);

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
//        if (TextUtils.isEmpty(code.getValue())){
//            tipImg.setValue(R.drawable.tip_fail);
//            tip.setValue("请输入验证码");
//            tipColor.setValue(false);
//            isClose.setValue(true);
//        }else {
            usersRemoteSource.loginByPassword(phone.getValue(), password.getValue(), new UsersInterface.LoginResponseCallback() {
                        @Override
                        public void loginSucceed(LoginResponse loginResponse) {
                            if (loginCallback != null) {
                                loginCallback.loginSucceed(phone.getValue(), password.getValue(),
                                        autoLogin.getValue(), loginResponse);
                            }
//                            Bundle bundle = new Bundle();
//////                            boolean isRenzheng =false;
//                            if (isSuccess.getValue()!=null){
//                                bundle.putBoolean("isTrue",isSuccess.getValue());
//                            }
//                            startActivity(MainActivity.class,bundle);
                        }

                        @Override
                        public void loginFailed(String msg) {
                            getUiChangeEvent().getToastTxt().setValue(msg);
                        }

                        @Override
                        public void error(ExceptionHandle.ResponseException e) {
                        }
                    });
//        }

    }

    public void getSmsCode() {
        if (TextUtils.isEmpty(phone.getValue())) {
            tipImg.setValue(R.drawable.tip_fail);
            tip.setValue("手机号码不能为空");
            tipColor.setValue(false);
            isClose.setValue(true);
        } else if (!ToolUtil.isMobileNO(phone.getValue())) {
            tipImg.setValue(R.drawable.tip_fail);
            tip.setValue("请输入正确的手机号码");
            tipColor.setValue(false);
            isClose.setValue(true);
        } else {
            usersRemoteSource.getSmsCode(phone.getValue(), new UsersInterface.getSmsCodeCallback() {
                @Override
                public void sendSucceed(SmsCodeResponse smsCodeResponse) {
                    isGetCodeSuccess.setValue(true);
                    tipImg.setValue(R.drawable.tip_success);
                    tip.setValue("验证码已发送，请耐心等待");
                    tipColor.setValue(true);
                    isClose.setValue(true);
                }

                @Override
                public void sendFailed(String msg) {
                    isGetCodeSuccess.setValue(false);
                    tipImg.setValue(R.drawable.tip_fail);
                    tip.setValue(msg);
                    tipColor.setValue(false);
                    isClose.setValue(true);
                }

                @Override
                public void error(ExceptionHandle.ResponseException e) {
                    isGetCodeSuccess.setValue(false);
                    tipImg.setValue(R.drawable.tip_fail);
                    tip.setValue(e.getMessage());
                    tipColor.setValue(false);
                    isClose.setValue(true);
                }
            });

        }
    }


    /**
     * 跳转注册页面
     */
    public void jumpToRegisterOrForgetActivity() {
        startActivity(RegisterOrForgetActivity.class);
    }

    public void setCallback(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }

}
