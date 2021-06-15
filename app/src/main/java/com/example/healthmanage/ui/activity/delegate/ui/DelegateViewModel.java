package com.example.healthmanage.ui.activity.delegate.ui;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.delegate.response.CreateDelegateResponse;
import com.example.healthmanage.ui.activity.delegate.response.DelegateBean;
import com.example.healthmanage.ui.activity.delegate.response.DelegateListResponse;
import com.example.healthmanage.ui.activity.team.response.TeamMemberResponse;

import java.util.List;

public class DelegateViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<String> dateChoose = new MutableLiveData<>();
    public MutableLiveData<List<DelegateListResponse.DataBean>> delegateLiveData = new MutableLiveData<>();
    public MutableLiveData<String> delegateDetail = new MutableLiveData<>("");
    public MutableLiveData<String> delegateSpecificTime = new MutableLiveData<>("");
    public MutableLiveData<String> delegateAddress = new MutableLiveData<>("");
    public MutableLiveData<String> delegateTime = new MutableLiveData<>("");
    public MutableLiveData<List<TeamMemberResponse.DataBean>> doctorMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> addSucceed = new MutableLiveData<>();

    public DelegateViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getBusineService(String date){
        usersRemoteSource.getBusineService(BaseApplication.getToken(), date, new UsersInterface.GetBusineServiceCallback() {
            @Override
            public void getSucceed(DelegateListResponse delegateListResponse) {
                if (delegateListResponse.getData()!=null && delegateListResponse.getData().size()>0){
                    delegateLiveData.setValue(delegateListResponse.getData());
                }else {
                    delegateLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                delegateLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                delegateLiveData.setValue(null);
            }
        });
    }

    public void getDoctorList(int roleId){
        usersRemoteSource.getDoctorTeamList(BaseApplication.getToken(), roleId,new UsersInterface.GetDoctorTeamListCallback() {
            @Override
            public void getSucceed(TeamMemberResponse teamMemberResponse) {
                if (teamMemberResponse.getData()!=null && teamMemberResponse.getData().size()>0){
                    doctorMutableLiveData.postValue(teamMemberResponse.getData());
                }else {
                    doctorMutableLiveData.postValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                doctorMutableLiveData.postValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    public void addBusineService(DelegateBean delegateBean){
        usersRemoteSource.addBusineService(delegateBean, new UsersInterface.AddBusineServiceCallback() {
            @Override
            public void addSucceed(CreateDelegateResponse createDelegateResponse) {
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
}
