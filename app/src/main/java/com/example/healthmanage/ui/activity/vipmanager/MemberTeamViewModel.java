package com.example.healthmanage.ui.activity.vipmanager;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.vipmanager.response.DeleteMemberResponse;
import com.example.healthmanage.ui.activity.vipmanager.response.IsFocusResponse;
import com.example.healthmanage.ui.activity.vipmanager.response.MemberTeamListResponse;

import java.util.List;

/**
 * desc:
 * date:2021/6/22 13:52
 * author:bWang
 */
public class MemberTeamViewModel extends BaseViewModel {
    private UsersRemoteSource mUsersRemoteSource;
    public MutableLiveData<List<MemberTeamListResponse.DataBean>> memberTeamListLiveData = new MutableLiveData<>();
    public MutableLiveData<List<MemberTeamListResponse.DataBean>> getMemberTeamByNameListLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isAddFocusSucceed = new MutableLiveData<>();
    public MutableLiveData<Boolean> isCancelFocusSucceed = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRemoveSucceed = new MutableLiveData<>();

    public MemberTeamViewModel() {
        mUsersRemoteSource = new UsersRemoteSource();
    }

    public void getMemberTeamList(String ranks,int status){
        mUsersRemoteSource.getMemberTeamList(BaseApplication.getToken(), ranks,status, new UsersInterface.GetMemberTeamListCallback() {
            @Override
            public void getSucceed(MemberTeamListResponse memberTeamListResponse) {
                if (memberTeamListResponse.getData()!=null && memberTeamListResponse.getData().size()>0){
                    memberTeamListLiveData.setValue(memberTeamListResponse.getData());
                }else {
                    memberTeamListLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                memberTeamListLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                memberTeamListLiveData.setValue(null);
            }
        });
    }

    public void addFocusMemberTeam(int id,int status){
        mUsersRemoteSource.editMemberTeam(BaseApplication.getToken(), id, status, new UsersInterface.EditMemberTeamCallback() {
            @Override
            public void editSucceed(IsFocusResponse isFocusResponse) {
                isAddFocusSucceed.setValue(true);
            }

            @Override
            public void editFailed(String msg) {
                isAddFocusSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isAddFocusSucceed.setValue(false);
            }
        });
    }
    public void cancelFocusMemberTeam(int id,int status){
        mUsersRemoteSource.editMemberTeam(BaseApplication.getToken(), id, status, new UsersInterface.EditMemberTeamCallback() {
            @Override
            public void editSucceed(IsFocusResponse isFocusResponse) {
                isCancelFocusSucceed.setValue(true);
            }

            @Override
            public void editFailed(String msg) {
                isCancelFocusSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isCancelFocusSucceed.setValue(false);
            }
        });
    }

    public void deleteMemberTeam(int id){
        mUsersRemoteSource.deleteMemberTeam(BaseApplication.getToken(), id, new UsersInterface.DeleteMemberTeamCallback() {
            @Override
            public void deleteSucceed(DeleteMemberResponse deleteMemberResponse) {
                isRemoveSucceed.setValue(true);
            }

            @Override
            public void deleteFailed(String msg) {
                isRemoveSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isRemoveSucceed.setValue(false);
            }
        });
    }

    public void getMemberTeamByName(String nameOrPhone,int status){
        mUsersRemoteSource.getMemberTeamByName(BaseApplication.getToken(), nameOrPhone, status, new UsersInterface.GetMemberTeamByNameCallback() {
            @Override
            public void getSucceed(MemberTeamListResponse memberTeamListResponse) {
                if (memberTeamListResponse.getData()!=null && memberTeamListResponse.getData().size()>0){
                    getMemberTeamByNameListLiveData.setValue(memberTeamListResponse.getData());
                }else {
                    getMemberTeamByNameListLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                getMemberTeamByNameListLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                getMemberTeamByNameListLiveData.setValue(null);
            }
        });
    }

}
