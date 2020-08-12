package com.example.healthmanage.ui.activity.login;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import androidx.lifecycle.Observer;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.bean.LoginResponse;
import com.example.healthmanage.databinding.ActivityLoginBinding;
import com.example.healthmanage.utils.Constants;
import com.example.healthmanage.utils.SPUtil;
import com.example.healthmanage.utils.ToolUtil;

import static com.example.healthmanage.utils.Constants.HTAG;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginCallback {


    @Override
    protected void initData() {
        checkAutoLogin();
    }

    @Override
    public void initViewParameters() {
        super.initViewParameters();
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

        //密码可见切换
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

        if (!SPUtil.getAutoLogin(this)) {
            //职业下拉框选择监听
            dataBinding.spinnerProfession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Constants.ROLE_ID = String.valueOf(position + 9);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }

        dataBinding.spinnerProfession.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ToolUtil.hideKeyboard(dataBinding.etPassword);
                ToolUtil.hideKeyboard(dataBinding.etPhone);
                return false;
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        viewModel.setCallback(this);
    }

    @Override
    public void loginSucceed(String phone, String password, boolean autoLogin, LoginResponse loginResponse) {
        if (!SPUtil.getAutoLogin(this)) {
            saveLoginInfo(phone, password, Constants.ROLE_ID, autoLogin);
        }
        //保存数据
        ((BaseApplication) getApplication()).setToken(loginResponse.getData().getToken());
        //保存服务器返回用户信息
        ((BaseApplication) getApplication()).setUserInfoBean(loginResponse.getData().getUserInfo());
    }


    /**
     * 保存登录信息
     *
     * @param phone
     * @param password
     * @param roleId
     * @param autoLogin
     */
    public void saveLoginInfo(String phone, String password, String roleId, boolean autoLogin) {
        Log.d(HTAG, "saveLoginInfo==========>: " + autoLogin);
        SPUtil.savePhone(phone, this);
        SPUtil.savePassword(password, this);
        SPUtil.saveAutoLogin(autoLogin, this);
        SPUtil.saveRoleId(roleId, this);
    }

    /**
     * 检查自动登录
     */
    public void checkAutoLogin() {
        String phone = SPUtil.getPhone(this);
        String password = SPUtil.getPassword(this);
        Constants.ROLE_ID = SPUtil.getRoleId(this);
        boolean autoLogin = SPUtil.getAutoLogin(this);
        viewModel.phone.setValue(phone);
        viewModel.password.setValue(password);
        viewModel.autoLogin.setValue(autoLogin);
        if (autoLogin) {
            viewModel.jumpToMainActivity();
        }
    }


}
