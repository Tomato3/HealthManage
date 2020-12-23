package com.example.healthmanage.ui.activity.login;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.bean.network.response.LoginResponse;
import com.example.healthmanage.databinding.ActivityLoginBinding;
import com.example.healthmanage.utils.Constants;
import com.example.healthmanage.utils.SPUtil;
import com.example.healthmanage.utils.ToastUtil;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginCallback, View.OnClickListener {


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

    int state = 0;

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

//        dataBinding.rgLogin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.rb_code:
//                        if (state != 0) {
//                            state = 0;
//                            dataBinding.linearLayoutCode.setVisibility(View.VISIBLE);
//                            dataBinding.linearLayoutPassword.setVisibility(View.GONE);
//                        }
//                        break;
//                    case R.id.rb_password:
//                        if (state != 1) {
//                            state = 1;
//                            dataBinding.linearLayoutCode.setVisibility(View.GONE);
//                            dataBinding.linearLayoutPassword.setVisibility(View.VISIBLE);
//                        }
//                        break;
//                }
//            }
//        });

        dataBinding.cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

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

        viewModel.setCallback(this);
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
    }

    @Override
    public void loginSucceed(String phone, String password, boolean autoLogin, LoginResponse loginResponse) {
        if (!SPUtil.getAutoLogin(this)) {
            saveLoginInfo(phone, password, autoLogin);
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
     * @param autoLogin
     */
    public void saveLoginInfo(String phone, String password, boolean autoLogin) {
        SPUtil.savePhone(phone, this);
        SPUtil.savePassword(password, this);
        SPUtil.saveAutoLogin(autoLogin, this);
//        SPUtil.saveRoleId(roleId, this);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_wechat:
                ToastUtil.showShort("正在开发中...");
                break;
        }
    }
}
