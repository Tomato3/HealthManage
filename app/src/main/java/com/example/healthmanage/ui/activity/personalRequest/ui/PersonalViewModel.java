package com.example.healthmanage.ui.activity.personalRequest.ui;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.personalRequest.response.AddRequestBean;
import com.example.healthmanage.ui.activity.personalRequest.response.AddRequestResponse;
import com.example.healthmanage.ui.activity.personalRequest.response.CancelResponse;
import com.example.healthmanage.ui.activity.personalRequest.response.ReplyResponse;
import com.example.healthmanage.ui.activity.personalRequest.response.RequestResponse;

import java.util.List;

public class PersonalViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<String> describeContent = new MutableLiveData<>();
    public MutableLiveData<List<RequestResponse.DataBean>> personRequestLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> addSucceed = new MutableLiveData<>();
    public MutableLiveData<Boolean> cancelSucceed = new MutableLiveData<>();
    public MutableLiveData<ReplyResponse.DataBean> replyData = new MutableLiveData<>();

    public PersonalViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getPersonalRequestList(int status){
        usersRemoteSource.getPersonalRequestList(BaseApplication.getToken(), status, new UsersInterface.GetPersonalRequestListCallback() {
            @Override
            public void getSucceed(RequestResponse requestResponse) {
                if (requestResponse.getData()!=null && requestResponse.getData().size()>0){
                    personRequestLiveData.setValue(requestResponse.getData());
                }else {
                    personRequestLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                personRequestLiveData.setValue(null);
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                personRequestLiveData.setValue(null);
            }
        });
    }

    public void addPersonalRequest(AddRequestBean addRequestBean){
        usersRemoteSource.addPersonalRequest(addRequestBean, new UsersInterface.AddPersonalRequestCallback() {
            @Override
            public void addSucceed(AddRequestResponse addRequestResponse) {
                addSucceed.setValue(true);
            }

            @Override
            public void addFailed(String msg) {
                addSucceed.setValue(false);
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                addSucceed.setValue(false);
            }
        });
    }

    public void getPersonalRequest(int id){
        usersRemoteSource.getPersonalRequest(BaseApplication.getToken(), id, new UsersInterface.GetPersonalRequestCallback() {
            @Override
            public void getSucceed(ReplyResponse replyResponse) {
                if (replyResponse.getData()!=null){
                    replyData.setValue(replyResponse.getData());
                }else {
                    replyData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                replyData.setValue(null);
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                replyData.setValue(null);
            }
        });
    }

    public void cancelPersonalRequest(int id){
        usersRemoteSource.cancelPersonalRequest(BaseApplication.getToken(), id, new UsersInterface.CancelPersonalRequestCallback() {
            @Override
            public void cancelSucceed(CancelResponse cancelResponse) {
                cancelSucceed.setValue(true);
            }

            @Override
            public void cancelFailed(String msg) {
                cancelSucceed.setValue(false);
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                cancelSucceed.setValue(false);
            }
        });
    }
}
