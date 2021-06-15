package com.example.healthmanage.ui.activity.registerorforget;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.StringUtils;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.bean.network.response.GeneralResponse;
import com.example.healthmanage.bean.network.response.SmsCodeResponse;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.qualification.QualificationActivity;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.TitleToolBar;

import static com.example.healthmanage.utils.Constants.HTAG;

public class RegisterOrForgetViewModel extends BaseViewModel {
    private String defaultString = "获取验证码";
    public MutableLiveData<String> phone = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");
    public MutableLiveData<String> verificationCode = new MutableLiveData<>("");
    public MutableLiveData<String> countdown = new MutableLiveData<>(defaultString);
    public String title = "注册账号", operationTxt = "确认注册";
    public MutableLiveData<String> tip = new MutableLiveData<>("");
    public MutableLiveData<Integer> tipImg = new MutableLiveData<>();
    public MutableLiveData<Boolean> tipColor = new MutableLiveData<>();
    public MutableLiveData<Boolean> isGetCodeStart = new MutableLiveData<>();
    public MutableLiveData<Boolean> isClose = new MutableLiveData<>(false);
//    public MutableLiveData<Boolean> isGetFinish = new MutableLiveData<>();
    private String smsIdentity = "";//发送短信时返回的标识
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<Boolean> isRegisterSuccess = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> isForgetSuccess = new MutableLiveData<Boolean>();


    public RegisterOrForgetViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    @Override
    public void setTitleToolBar(TitleToolBar titleToolBar) {
        super.setTitleToolBar(titleToolBar);

    }


    public void register() {
//            startActivity(QualificationActivity.class);
            usersRemoteSource.register(phone.getValue(), password.getValue(),
                    verificationCode.getValue(), smsIdentity, new UsersInterface.RegisterCallback() {
                        @Override
                        public void registerSucceed() {
                            isRegisterSuccess.postValue(true);
                        }

                        @Override
                        public void registerFailed(String msg) {
                            showToast(msg, 1);
                        }

                        @Override
                        public void error(ExceptionHandle.ResponseException e) {
                            isRegisterSuccess.postValue(false);
                        }
                    });
    }

    public void forgetPwd(){
        usersRemoteSource.forgetPassword(phone.getValue(), password.getValue(),
                verificationCode.getValue(), smsIdentity, new UsersInterface.ForgetPasswordCallback() {
                    @Override
                    public void forgetSucceed(GeneralResponse generalResponse) {
                        isForgetSuccess.postValue(true);
                        Log.d(HTAG, "forgetSucceed==========>: ");
                    }

                    @Override
                    public void forgetFailed(String msg) {
                        showToast(msg, 1);
                    }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {
                        isForgetSuccess.postValue(false);
                    }
                });
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
                    tipImg.setValue(R.drawable.tip_success);
                    tip.setValue("验证码已发送，请耐心等待");
                    tipColor.setValue(true);
                    smsIdentity = smsCodeResponse.getData();
                    isGetCodeStart.setValue(true);
                    isClose.setValue(true);
//                    new CountDownTimer(60 * 1000, 1 * 1000) {
//
//                        @Override
//                        public void onTick(long millisUntilFinished) {
//                            countdown.setValue(String.valueOf(millisUntilFinished / 1000) + "s");
//                            isGetFinish.setValue(false);
//                        }
//
//                        @Override
//                        public void onFinish() {
//                            countdown.setValue(defaultString);
//                            isGetFinish.setValue(true);
//                        }
//                    }.start();
                }

                @Override
                public void sendFailed(String msg) {
                    isGetCodeStart.setValue(false);
//                    isGetFinish.setValue(true);
                    tipImg.setValue(R.drawable.tip_fail);
                    tip.setValue(msg);
                    tipColor.setValue(false);
                    isClose.setValue(true);
                }

                @Override
                public void error(ExceptionHandle.ResponseException e) {
//                    isGetFinish.setValue(true);
                    isGetCodeStart.setValue(false);
                    tipImg.setValue(R.drawable.tip_fail);
                    tip.setValue(e.getMessage());
                    tipColor.setValue(false);
                    isClose.setValue(true);
                }
            });

        }
    }


}
