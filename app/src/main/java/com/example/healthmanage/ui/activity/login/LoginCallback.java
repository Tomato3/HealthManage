package com.example.healthmanage.ui.activity.login;

import com.example.healthmanage.bean.LoginResponse;

public interface LoginCallback {

    /**
     * 登录成功回调
     *
     * @param phone
     * @param password
     * @param autoLogin
     * @param loginResponse
     */
    void loginSucceed(String phone, String password, boolean autoLogin,
                      LoginResponse loginResponse);

}
