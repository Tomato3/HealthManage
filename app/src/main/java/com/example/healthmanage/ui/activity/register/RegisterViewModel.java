package com.example.healthmanage.ui.activity.register;

import android.os.CountDownTimer;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.RegisterResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.login.LoginActivity;
import com.example.healthmanage.widget.TitleToolBar;

public class RegisterViewModel extends BaseViewModel {
    private String defaultString = "获取验证码";
    public MutableLiveData<String> phone = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");
    public MutableLiveData<Boolean> eyeState = new MutableLiveData<>(false);
    public MutableLiveData<String> verificationCode = new MutableLiveData<>("");
    public MutableLiveData<String> countdown = new MutableLiveData<>(defaultString);
    private String smsIdentity = "";//发送短信时返回的标识
    private UsersRemoteSource usersRemoteSource;


    public RegisterViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    @Override
    public void setTitleToolBar(TitleToolBar titleToolBar) {
        super.setTitleToolBar(titleToolBar);

    }


    public void register() {
        usersRemoteSource.register(phone.getValue(), password.getValue(),
                verificationCode.getValue(), smsIdentity, new UsersInterface.RegisterCallback() {
                    @Override
                    public void registerSucceed() {
                        startActivity(LoginActivity.class);
                    }

                    @Override
                    public void registerFailed(String msg) {
                        getUiChangeEvent().getToastTxt().setValue(msg);
                    }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {
                        getUiChangeEvent().getToastTxt().setValue(e.getMessage());
                    }
                });
    }

    public void receiverVerificationCode() {
        usersRemoteSource.sendSmsMessage(phone.getValue(), new UsersInterface.SendSmsMessageCallback() {
            @Override
            public void sendSucceed(RegisterResponse registerResponse) {
                smsIdentity = registerResponse.getData();
                getUiChangeEvent().getToastTxt().setValue(registerResponse.getMessage());
                new CountDownTimer(60 * 1000, 1 * 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        countdown.setValue(String.valueOf(millisUntilFinished / 1000) + "s");
                    }

                    @Override
                    public void onFinish() {
                        countdown.setValue(defaultString);
                    }
                }.start();
            }

            @Override
            public void sendFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                getUiChangeEvent().getToastTxt().setValue(e.getMessage());
            }
        });

    }

    public void changePasswordEye() {
        if (eyeState.getValue()) {
            eyeState.setValue(false);
        } else {
            eyeState.setValue(true);
        }
    }
}
