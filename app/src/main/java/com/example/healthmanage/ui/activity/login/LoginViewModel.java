package com.example.healthmanage.ui.activity.login;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.LoginResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.main.MainActivity;
import com.example.healthmanage.ui.activity.register.RegisterActivity;
import com.example.healthmanage.ui.activity.resetpassword.ResetPasswordActivity;
import com.example.healthmanage.utils.Constants;


public class LoginViewModel extends BaseViewModel {

    public MutableLiveData<String> phone = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");
    public MutableLiveData<Boolean> eyeState = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> autoLogin = new MutableLiveData<>(false);

    private UsersRemoteSource usersRemoteSource;
    private LoginCallback loginCallback;

    public LoginViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    /**
     * 跳转首页
     */
    public void jumpToMainActivity() {
        usersRemoteSource.loginByPassword(phone.getValue(), password.getValue()
                , Constants.ROLE_ID, new UsersInterface.LoginResponseCallback() {
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

    /**
     * 跳转注册页面
     */
    public void jumpToRegisterActivity() {
        startActivity(RegisterActivity.class);
    }

    /**
     * 跳转忘记密码页面
     */
    public void jumpToResentPasswordActivity() {
        startActivity(ResetPasswordActivity.class);
    }

    /**
     * 切换密码可见
     */
    public void changePasswordEye() {
        if (eyeState.getValue()) {
            eyeState.setValue(false);
        } else {
            eyeState.setValue(true);
        }
    }

    /**
     * 切换自动登录
     */
    public void changeAutoLogin() {
        if (autoLogin.getValue()) {
            autoLogin.setValue(false);
        } else {
            autoLogin.setValue(true);
        }
    }

    public void setCallback(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }
}
