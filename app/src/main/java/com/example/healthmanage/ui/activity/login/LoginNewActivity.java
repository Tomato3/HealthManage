package com.example.healthmanage.ui.activity.login;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.aries.ui.widget.progress.UIProgressDialog;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.bean.network.response.LoginResponse;
import com.example.healthmanage.databinding.ActivityLoginNewBinding;
import com.example.healthmanage.ui.activity.main.MainActivity;
import com.example.healthmanage.ui.activity.qualification.QualificationActivity;
import com.example.healthmanage.ui.activity.registerorforget.ForgetPasswordActivity;
import com.example.healthmanage.ui.activity.registerorforget.RegisterOrForgetActivity;
import com.example.healthmanage.utils.Constants;
import com.example.healthmanage.utils.CountDownTimerUtils;
import com.example.healthmanage.utils.SPUtil;
import com.example.healthmanage.utils.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import static android.os.Build.VERSION_CODES.M;


public class LoginNewActivity extends BaseActivity<ActivityLoginNewBinding,LoginViewModel> implements LoginCallback, View.OnClickListener {

    private int state = 0;
    private CountDownTimerUtils mCountDownTimerUtils;
    private UIProgressDialog uiProgressDialog;


    @Override
    public void initDataBindingAndViewModel(Bundle savedInstanceState) {
        super.initDataBindingAndViewModel(savedInstanceState);
    }

    @Override
    protected void initData() {
        requestPermission();
        SharedPreferences sp = getSharedPreferences("isFirst",0);
        Boolean isFirst = sp.getBoolean("first",true);
        if (isFirst){
            sp.edit().putBoolean("first",false).commit();
        }else {
            String phone = SPUtil.getPhone(this);
            if (TextUtils.isEmpty(phone)){
                viewModel.phone.setValue(null);
            }else {
                viewModel.phone.setValue(phone);
            }
            dataBinding.rbPassword.setChecked(true);
            dataBinding.linearLayoutCode.setVisibility(View.GONE);
            dataBinding.linearLayoutPassword.setVisibility(View.VISIBLE);
            state=1;
        }
        if (SPUtil.getAutoLogin(this)){
            viewModel.password.setValue(SPUtil.getPassword(this));
//            checkAutoLogin();
        }else {
            viewModel.password.setValue(null);
        }

        dataBinding.rgLogin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_code:
                        if (state != 0){
                            state = 0;
                            dataBinding.linearLayoutCode.setVisibility(View.VISIBLE);
                            dataBinding.linearLayoutPassword.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.rb_password:
                        if (state != 1){
                            state = 1;
                            dataBinding.linearLayoutCode.setVisibility(View.GONE);
                            dataBinding.linearLayoutPassword.setVisibility(View.VISIBLE);
                        }
                }
            }
        });
    }

    /**必须写，不然不能通话*/
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA}, 1000);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i : grantResults) {
            if (i != PackageManager.PERMISSION_GRANTED) {
                System.exit(1);
            }
        }
    }

    @Override
    public void initViewParameters() {
        super.initViewParameters();
    }

    /**
     * 检查自动登录
     */
    private void checkAutoLogin() {
        String phone = SPUtil.getPhone(this);
        String password = SPUtil.getPassword(this);
        Constants.ROLE_ID = SPUtil.getRoleId(this);
        boolean autoLogin = SPUtil.getAutoLogin(this);
        viewModel.phone.setValue(phone);
        viewModel.password.setValue(password);
        viewModel.autoLogin.setValue(autoLogin);
        if (autoLogin){
            viewModel.jumpToMainActivity();
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_login_new;
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.jumpToMainActivity();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            EMClient.getInstance().createAccount(viewModel.phone.getValue(),viewModel.password.getValue());
//                            Log.e("success=========","注册成功");
//                        } catch (HyphenateException e) {
//                            e.printStackTrace();
//                            Log.e("failed========","注册失败"+e.getErrorCode()+","+e.getMessage());
//                        }
//                    }
//                }).start();
            }
        });
        /**监听是否发送验证码成功*/
        viewModel.isGetCodeSuccess.observe(this,isCodeStart -> {
            if (isCodeStart){
                viewModel.isGetCodeSuccess.setValue(false);
                mCountDownTimerUtils = new CountDownTimerUtils(dataBinding.btnGetCode, 60000, 1000);
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
        if (SPUtil.getAutoLogin(this)){
            dataBinding.cbRemember.setChecked(true);
        }else {
            dataBinding.cbRemember.setChecked(false);
        }

        dataBinding.cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SPUtil.saveAutoLogin(true,LoginNewActivity.this);
                }else {
                    SPUtil.saveAutoLogin(false,LoginNewActivity.this);
                    viewModel.autoLogin.setValue(false);
                }
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

        dataBinding.tvForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginNewActivity.this, ForgetPasswordActivity.class);
                startActivityForResult(intent,2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            if (requestCode==2){
                if (resultCode==RESULT_OK){
                    viewModel.phone.setValue(data.getStringExtra("phone"));
                    SPUtil.savePhone(data.getStringExtra("phone"),LoginNewActivity.this);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void loginSucceed(String phone, String password, boolean autoLogin, LoginResponse loginResponse) {
        if (!SPUtil.getAutoLogin(this)){
            SPUtil.savePhone(phone, this);
            SPUtil.savePassword(null, this);
        }else {
            SPUtil.savePhone(phone, this);
            SPUtil.savePassword(password, this);
        }
        //保存数据
        ((BaseApplication) getApplication()).setToken(loginResponse.getData().getToken());
        //保存服务器返回用户信息
        ((BaseApplication) getApplication()).setUserInfoBean(loginResponse.getData().getUserInfo());
        if (loginResponse.getData().getUserInfo().getAppDoctorInfo()!=null){
            if (loginResponse.getData().getUserInfo().getAppDoctorInfo().getAuditStatus()==0){
                Intent intent = new Intent(LoginNewActivity.this, QualificationActivity.class);
                intent.putExtra("type","wait");
                startActivity(intent);
            }else if (loginResponse.getData().getUserInfo().getAppDoctorInfo().getAuditStatus()==1){
                Intent intent = new Intent(LoginNewActivity.this,MainActivity.class);
                intent.putExtra("isSuccess",true);
                intent.putExtra("phone",viewModel.phone.getValue());
                intent.putExtra("pwd",viewModel.password.getValue());
                SPUtil.savePhone(viewModel.phone.getValue(),LoginNewActivity.this);
                SPUtil.savePassword(viewModel.password.getValue(),LoginNewActivity.this);
                startActivity(intent);
            }else if (loginResponse.getData().getUserInfo().getAppDoctorInfo().getAuditStatus()==2){
                Intent intent = new Intent(LoginNewActivity.this, QualificationActivity.class);
                intent.putExtra("type","failed");
                intent.putExtra("id",String.valueOf(loginResponse.getData().getUserInfo().getAppDoctorInfo().getId()));
                startActivity(intent);
            }
        }else {
            Intent intent = new Intent(LoginNewActivity.this, QualificationActivity.class);
            intent.putExtra("phone",viewModel.phone.getValue());
            SPUtil.savePhone(viewModel.phone.getValue(),LoginNewActivity.this);
            SPUtil.savePassword(viewModel.password.getValue(),LoginNewActivity.this);
            startActivity(intent);
        }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mCountDownTimerUtils.onFinish();
    }
}
