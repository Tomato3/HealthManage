package com.example.healthmanage.ui.activity.notice.ui;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.notice.response.NoticeResponse;
import com.example.healthmanage.ui.activity.team.response.SignTeamResponse;

import java.util.List;

public class NoticeViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<List<NoticeResponse.DataBean>> invitationLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSignSucceed = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRefuseSucceed = new MutableLiveData<>();

    public NoticeViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getDoctorTeamApplyNoticeList(){
        usersRemoteSource.getDoctorTeamApplyNoticeList(BaseApplication.getToken(),new UsersInterface.GetDoctorTeamApplyListCallback() {
            @Override
            public void getSucceed(NoticeResponse noticeResponse) {
                if (noticeResponse.getData()!=null&&noticeResponse.getData().size()>0){
                    invitationLiveData.setValue(noticeResponse.getData());
                }else {
                    invitationLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                invitationLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                invitationLiveData.setValue(null);
            }
        });
    }

    public void signTeam(int id,int status){
        usersRemoteSource.signOrNot(id, status, BaseApplication.getToken(), new UsersInterface.SignOrNotCallback() {
            @Override
            public void getSucceed(SignTeamResponse signTeamResponse) {
                isSignSucceed.setValue(true);
            }

            @Override
            public void getFailed(String msg) {
                isSignSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isSignSucceed.setValue(false);
            }
        });
    }

    public void refuseSignTeam(int id,int status){
        usersRemoteSource.signOrNot(id, status, BaseApplication.getToken(), new UsersInterface.SignOrNotCallback() {
            @Override
            public void getSucceed(SignTeamResponse signTeamResponse) {
                isRefuseSucceed.setValue(true);
            }

            @Override
            public void getFailed(String msg) {
                isRefuseSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isRefuseSucceed.setValue(false);
            }
        });
    }
}
