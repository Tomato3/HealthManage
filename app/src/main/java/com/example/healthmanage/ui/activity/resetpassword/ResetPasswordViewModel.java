package com.example.healthmanage.ui.activity.resetpassword;

import android.os.CountDownTimer;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.GeneralResponse;
import com.example.healthmanage.bean.RegisterResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.login.LoginActivity;
import com.example.healthmanage.widget.TitleToolBar;


public class ResetPasswordViewModel extends BaseViewModel {
    private String defaultString = "获取验证码";
    public MutableLiveData<String> phone = new MutableLiveData<>("");
    public MutableLiveData<String> verificationCode = new MutableLiveData<>("");
    public MutableLiveData<String> newPassword = new MutableLiveData<>("");
    public MutableLiveData<Boolean> eyeState = new MutableLiveData<>(false);
    public MutableLiveData<String> countdown = new MutableLiveData<>(defaultString);
    private UsersRemoteSource usersRemoteSource;
    private String smsIdentity = "";//发送短信时返回的标识


    public ResetPasswordViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    @Override
    public void setTitleToolBar(TitleToolBar titleToolBar) {
        super.setTitleToolBar(titleToolBar);

    }

    public void receiverVerificationCode() {
        usersRemoteSource.sendSmsMessage(phone.getValue(), new UsersInterface.SendSmsMessageCallback() {
            @Override
            public void sendSucceed(RegisterResponse registerResponse) {
                smsIdentity = registerResponse.getData();
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

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

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

    public void resetPassword() {
        usersRemoteSource.updatePassword(phone.getValue(), newPassword.getValue(),
                verificationCode.getValue(), smsIdentity, new UsersInterface.UpdatePasswordCallback() {
                    @Override
                    public void updateSucceed(GeneralResponse generalResponse) {
                        getUiChangeEvent().getToastTxt().setValue(generalResponse.getMessage());
                        finish();
                    }

                    @Override
                    public void updateFailed(String msg) {
                        getUiChangeEvent().getToastTxt().setValue(msg);
                    }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {

                    }
                });

    }
}
