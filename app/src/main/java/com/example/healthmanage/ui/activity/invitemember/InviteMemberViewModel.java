package com.example.healthmanage.ui.activity.invitemember;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.LoginResponse;
import com.example.healthmanage.bean.network.response.SearchMemberResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.jeremyliao.liveeventbus.LiveEventBus;

public class InviteMemberViewModel extends BaseViewModel {

    public MutableLiveData<String> name = new MutableLiveData<>("");
    public MutableLiveData<String> rank = new MutableLiveData<>("");
    public MutableLiveData<Boolean> inviteBtnVisible = new MutableLiveData<>(false);
    public MutableLiveData<LoginResponse.DataBean.UserInfoBean> userInfoBean =
            new MutableLiveData<>();
    public MutableLiveData<SearchMemberResponse.DataBean> searchMemberResponse = new MutableLiveData<>();
    private UsersRemoteSource usersRemoteSource;
    private int a;

    public InviteMemberViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    /**
     * 搜索会员
     *
     * @param phone
     */
    public void searchMember(String phone) {
        inviteBtnVisible.setValue(false);
        if (!TextUtils.isEmpty(phone)) {
            usersRemoteSource.searchMembers(phone,
                    new UsersInterface.SearchMembersCallback() {
                        @Override
                        public void searchSucceed(SearchMemberResponse searchMemberResponse) {
                            if (searchMemberResponse.getData() == null) {
                                getUiChangeEvent().getToastTxt().setValue(searchMemberResponse.getMessage());
                            } else {
                                getUiChangeEvent().getToastTxt().setValue(searchMemberResponse.getMessage());
                                LiveEventBus.get("CloseKeyboard").post(true);
                                inviteBtnVisible.setValue(true);
                                a = searchMemberResponse.getData().getId();
                                name.setValue(searchMemberResponse.getData().getNickName());
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
                            name.setValue(null);
                            rank.setValue(null);
                            getUiChangeEvent().getToastTxt().setValue("检查会员手机号是否输入正确");
                        }

                        @Override
                        public void error(ExceptionHandle.ResponseException e) {
                            name.setValue(null);
                            rank.setValue(null);
                        }
                    });
        } else {
            name.setValue(null);
            rank.setValue(null);
        }
    }

    /**
     * 邀请会员
     */
    public void inviteMember() {
        usersRemoteSource.inviteMembers(String.valueOf(BaseApplication.getUserInfoBean().getSysId()),
                String.valueOf(a),
                new UsersInterface.InvitingMembersCallback() {
                    @Override
                    public void inviteSucceed(String msg) {
                        getUiChangeEvent().getToastTxt().setValue(msg);
                        LiveEventBus.get("Refresh").post(true);
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
