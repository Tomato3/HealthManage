package com.example.healthmanage.ui.activity.invitingmembers;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.LoginResponse;
import com.example.healthmanage.bean.SearchMemberResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.widget.TitleToolBar;

public class InvitingMembersViewModel extends BaseViewModel {

    public MutableLiveData<String> phone = new MutableLiveData<>("");
    public MutableLiveData<String> name = new MutableLiveData<>("");
    public MutableLiveData<String> rank = new MutableLiveData<>("");
    public MutableLiveData<Boolean> inviteBtnVisible = new MutableLiveData<>(false);
    public MutableLiveData<LoginResponse.DataBean.UserInfoBean> userInfoBean =
            new MutableLiveData<>();
    public MutableLiveData<SearchMemberResponse.DataBean> searchMemberResponse = new MutableLiveData<>();
    UsersRemoteSource usersRemoteSource;
    int a;

    public InvitingMembersViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void searchMember() {
        usersRemoteSource.searchMembers(phone.getValue(),
                new UsersInterface.SearchMembersCallback() {
            @Override
            public void searchSucceed(SearchMemberResponse searchMemberResponse) {
                if (searchMemberResponse.getData() == null) {
                    getUiChangeEvent().getToastTxt().setValue(searchMemberResponse.getMessage());
                } else {
                    getUiChangeEvent().getToastTxt().setValue(searchMemberResponse.getMessage());
                    inviteBtnVisible.setValue(true);
                    Log.d("TAG",
                            "searchSucceed: ===>" + searchMemberResponse.getData().getUserName());
                    Log.d("TAG",
                            "searchSucceed: ===>" + searchMemberResponse.getData().getId());
                    a = searchMemberResponse.getData().getId();
                    name.setValue(searchMemberResponse.getData().getUserName());
                    switch (searchMemberResponse.getData().getRank()) {
                        case 0:
                            rank.setValue("普通会员");
                            break;
                        case 1:
                            rank.setValue("贵宾会员");
                            break;
                        case 2:
                            rank.setValue("SVIP会员");
                            break;
                    }
                }
            }

            @Override
            public void searchFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue("检查会员手机号是否输入正确");
            }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {

                    }
        });
    }

    public void inviteMember() {

        usersRemoteSource.inviteMembers(String.valueOf(BaseApplication.getUserInfoBean().getSysId()),
                String.valueOf(a),
                new UsersInterface.InvitingMembersCallback() {
                    @Override
                    public void inviteSucceed(String msg) {
                        getUiChangeEvent().getToastTxt().setValue(msg);
                    }

                    @Override
                    public void inviteFailed(String msg) {
                        getUiChangeEvent().getToastTxt().setValue(msg);
                    }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {
                        Log.d("TAG", "Error: ===>" + e.getMessage());

                    }
                }
        );
    }
}
