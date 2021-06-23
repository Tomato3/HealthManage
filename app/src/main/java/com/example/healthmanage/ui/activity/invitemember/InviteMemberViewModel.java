package com.example.healthmanage.ui.activity.invitemember;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.invitemember.response.InviteSucceedResponse;
import com.example.healthmanage.ui.activity.vipmanager.response.InviteMemberResponse;

public class InviteMemberViewModel extends BaseViewModel {

    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> rank = new MutableLiveData<>();
    public MutableLiveData<String> phone = new MutableLiveData<>();
    public MutableLiveData<Boolean> inviteSuccess = new MutableLiveData<>();
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<InviteMemberResponse.DataBean> mInviteMemberResponseMutableLiveData = new MutableLiveData<>();

    public InviteMemberViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void inviteSearchMember(String phone){
        usersRemoteSource.searchMembers(BaseApplication.getToken(), phone, new UsersInterface.SearchMembersCallback() {
            @Override
            public void searchSucceed(InviteMemberResponse searchMemberResponse) {
                if (searchMemberResponse.getData()!=null){
                    mInviteMemberResponseMutableLiveData.setValue(searchMemberResponse.getData());
                }else {
                    mInviteMemberResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void searchFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                mInviteMemberResponseMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mInviteMemberResponseMutableLiveData.setValue(null);
            }
        });
    }

    public void inviteMember(int userId){
        usersRemoteSource.inviteMember(BaseApplication.getToken(), userId, new UsersInterface.InviteUserMemberCallback() {
            @Override
            public void inviteSucceed(InviteSucceedResponse inviteSucceedResponse) {
                inviteSuccess.setValue(true);
            }

            @Override
            public void inviteFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                inviteSuccess.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                inviteSuccess.setValue(false);
            }
        });
    }


}
