package com.example.healthmanage.ui.activity.myinfo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.GeneralResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.jeremyliao.liveeventbus.LiveEventBus;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MyInfoViewModel extends BaseViewModel {

    public MutableLiveData<String> avatar = new MutableLiveData<>("http://b-ssl.duitang.com/uploads/item/201803/02/20180302222228_v3JdH.jpeg");

    public MutableLiveData<String> nickName = new MutableLiveData<>("");

    public MutableLiveData<String> phone = new MutableLiveData<>("");

    public MutableLiveData<String> license_registration_number = new MutableLiveData<>("88888888");

    public MutableLiveData<String> inviteCode = new MutableLiveData<>("");

    public MutableLiveData<String> inviteCodeTip = new MutableLiveData<>("生成邀请码");

    UsersRemoteSource usersRemoteSource = new UsersRemoteSource();

    public MyInfoViewModel() {
        nickName.setValue(BaseApplication.getUserInfoBean().getSysNickName());
        phone.setValue(BaseApplication.getUserInfoBean().getPhone());
    }

    public void getInviteCode() {
        usersRemoteSource.getInviteCode(BaseApplication.getToken(), new UsersInterface.GetInviteCodeCallback() {
            @Override
            public void getSucceed(GeneralResponse generalResponse) {
                showToast(generalResponse.getMessage(), 0);
                inviteCodeTip.setValue("复制邀请码");
                inviteCode.setValue(generalResponse.getData());
            }

            @Override
            public void getFailed(String msg) {

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    public void writeInviteCode(String inviteCode) {
        usersRemoteSource.writeInviteCode(BaseApplication.getToken(), inviteCode, new UsersInterface.WriteInviteCodeCallback() {
            @Override
            public void writeSucceed(GeneralResponse generalResponse) {
                showToast(generalResponse.getMessage(), 0);
                LiveEventBus.get("close").post(true);
            }

            @Override
            public void writeFailed(String msg) {
                showToast(msg, 1);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                Log.d(HTAG, "error==========>: " + e.getMessage());
            }
        });
    }


}
