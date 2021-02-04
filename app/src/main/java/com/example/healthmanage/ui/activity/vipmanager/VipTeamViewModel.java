package com.example.healthmanage.ui.activity.vipmanager;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.data.network.exception.ExceptionHandle;

import java.util.List;

public class VipTeamViewModel extends BaseViewModel {
    private UsersRemoteSource mUsersRemoteSource = new UsersRemoteSource();
    public MutableLiveData<List<MyMemberResponse.DataBean>> mListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> mBooleanMutableLiveData = new MutableLiveData<>();

    /**
     * 获取所有会员
     */
    public void getMemberss() {
        mUsersRemoteSource.loadMyMembers(String.valueOf(BaseApplication.getUserInfoBean().getSysId()), new UsersInterface.LoadMyMembersCallback() {
            @Override
            public void loadSucceed(MyMemberResponse myMemberResponse) {
                if (myMemberResponse.getData() != null) {
                    mListMutableLiveData.postValue(myMemberResponse.getData());
                } else {
                    mListMutableLiveData.postValue(null);
                }
            }


            @Override
            public void loadFailed(String msg) {

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    /**
     * 通过会员等级来获取筛选数据
     *
     * @param rank
     */
    public void getMembersByRank(int rank) {
        mUsersRemoteSource.selectMember(String.valueOf(BaseApplication.getUserInfoBean().getSysId()), rank, new UsersInterface.LoadMyMembersCallback() {
            @Override
            public void loadSucceed(MyMemberResponse myMemberResponse) {
                if (myMemberResponse.getData() != null) {
                    mListMutableLiveData.postValue(myMemberResponse.getData());
                } else {
                    mListMutableLiveData.postValue(null);
                }
            }

            @Override
            public void loadFailed(String msg) {

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    /**
     * 获取我的关注数据
     */
    public void getFocus() {
        mUsersRemoteSource.loadMyFocus(String.valueOf(BaseApplication.getUserInfoBean().getSysId()), new UsersInterface.LoadMyMembersCallback() {
            @Override
            public void loadSucceed(MyMemberResponse myMemberResponse) {
                if (myMemberResponse.getData() != null) {
                    mListMutableLiveData.postValue(myMemberResponse.getData());
                } else {
                    mListMutableLiveData.postValue(null);
                }
            }

            @Override
            public void loadFailed(String msg) {

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    public void setFocus(String userId) {
        mUsersRemoteSource.addFocus(String.valueOf(BaseApplication.getUserInfoBean().getSysId()), userId, new UsersInterface.AddFocusCallback() {
            @Override
            public void addSucceed() {
                mBooleanMutableLiveData.postValue(true);
            }

            @Override
            public void addFailed(String msg) {
                mBooleanMutableLiveData.postValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mBooleanMutableLiveData.postValue(false);
            }
        });
    }
}
