package com.example.healthmanage.ui.activity.signmember;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.signmember.response.SignMemberResponse;
import com.example.healthmanage.ui.activity.signmember.response.SignMemberSuccessResponse;

import java.util.ArrayList;
import java.util.List;

public class SignMemberViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<List<SignMemberResponse.DataBean>> signList = new MutableLiveData<>();
    public MutableLiveData<List<SignMemberResponse.DataBean>> notSignList = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSignSuccess = new MutableLiveData<>();
    public MutableLiveData<Boolean> isNotSignSuccess = new MutableLiveData<>();

    public SignMemberViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getNotSignMember(int doctorId){
        usersRemoteSource.getNotSignMemberData(doctorId, BaseApplication.getToken(), new UsersInterface.GetNotSignMemberCallback() {
            @Override
            public void sendSucceed(SignMemberResponse signMemberResponse) {
                if (signMemberResponse.getData()!=null && signMemberResponse.getData().size()>0){
                    signList.postValue(signMemberResponse.getData());
                }else {
                    signList.postValue(null);
                }
            }

            @Override
            public void sendFailed(String msg) {

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    public void getSignMember(int doctorId){
        usersRemoteSource.getSignMemberData(doctorId, BaseApplication.getToken(), new UsersInterface.GetSignMemberCallback() {
            @Override
            public void sendSucceed(SignMemberResponse signMemberResponse) {
                if (signMemberResponse.getData()!=null && signMemberResponse.getData().size()>0){
                    notSignList.postValue(signMemberResponse.getData());
                }else {
                    notSignList.postValue(null);
                }
            }

            @Override
            public void sendFailed(String msg) {

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    public void addSignMember(int doctorSignStatus,long doctorId,long id){
        usersRemoteSource.addSignMember(doctorSignStatus, doctorId, id, BaseApplication.getToken(), new UsersInterface.AddSignMemberCallback() {
            @Override
            public void sendSucceed(SignMemberSuccessResponse signMemberSuccessResponse) {
                isSignSuccess.postValue(true);
            }

            @Override
            public void sendFailed(String msg) {
                isSignSuccess.postValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isSignSuccess.postValue(false);
            }
        });
    }

    public void addNotSignMember(int doctorSignStatus,long doctorId,long id){
        usersRemoteSource.addSignMember(doctorSignStatus, doctorId, id, BaseApplication.getToken(), new UsersInterface.AddSignMemberCallback() {
            @Override
            public void sendSucceed(SignMemberSuccessResponse signMemberSuccessResponse) {
                isNotSignSuccess.postValue(true);
            }

            @Override
            public void sendFailed(String msg) {
                isNotSignSuccess.postValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isNotSignSuccess.postValue(false);
            }
        });
    }

}
