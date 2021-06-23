package com.example.healthmanage.ui.activity.vipmanager;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.bean.network.response.BaseResponse;
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.bean.network.response.SearchMemberResponse;
import com.example.healthmanage.bean.network.response.VipUserBean;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.team.response.DoctorTeamResponse;

import java.util.List;

public class VipTeamViewModel extends BaseViewModel {
    private UsersRemoteSource mUsersRemoteSource = new UsersRemoteSource();
    public MutableLiveData<List<MyMemberResponse.DataBean>> mListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<MyMemberResponse.DataBean> mListSearchMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> mBooleanMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> mBooleanDeleteMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> userId = new MutableLiveData<>();

    /**
     * 获取所有会员
     */
    public void getMemberss(String userId) {
//        String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId())
        mUsersRemoteSource.loadMyMembers(userId, new UsersInterface.LoadMyMembersCallback() {
            @Override
            public void loadSucceed(MyMemberResponse myMemberResponse) {
                if (myMemberResponse.getData() != null && myMemberResponse.getData().size()>0) {
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

    public void getDoctorTeam(){
        mUsersRemoteSource.getDoctorTeam(BaseApplication.getToken(), new UsersInterface.GetDoctorTeamCallback() {
            @Override
            public void getSucceed(DoctorTeamResponse doctorTeamResponse) {
                if (doctorTeamResponse.getData()!=null){
                    userId.setValue(String.valueOf(doctorTeamResponse.getData().getSystemUserId()));
                }else {
                    userId.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                userId.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                userId.setValue(null);
            }
        });
    }

    /**
     * 通过会员等级来获取筛选数据
     *
     * @param rank
     */
    public void getMembersByRank(String rank,String userId) {
        mUsersRemoteSource.selectMember(userId, rank, new UsersInterface.LoadMyMembersCallback() {
            @Override
            public void loadSucceed(MyMemberResponse myMemberResponse) {
                if (myMemberResponse.getData() != null && myMemberResponse.getData().size()>0) {
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
                e.printStackTrace();
            }
        });
    }

    /**
     * 获取我的关注数据
     */
    public void getFocus(String userId) {
        mUsersRemoteSource.loadMyFocus(userId, new UsersInterface.LoadMyMembersCallback() {
            @Override
            public void loadSucceed(MyMemberResponse myMemberResponse) {
                if (myMemberResponse.getData() != null && myMemberResponse.getData().size()>0) {
                    mListMutableLiveData.postValue(myMemberResponse.getData());
                } else {
                    mListMutableLiveData.postValue(null);
                }
            }

            @Override
            public void loadFailed(String msg) {
                mListMutableLiveData.postValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    /**添加关注*/
    public void setFocus(String userId) {
        mUsersRemoteSource.addFocus(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()), userId, new UsersInterface.AddFocusCallback() {
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

    /**取消关注*/
    public void cancelFocus(String userId){
        mUsersRemoteSource.deleteFocus(userId, new UsersInterface.DeleteFocusCallback() {
            @Override
            public void deleteSucceed() {
                mBooleanDeleteMutableLiveData.postValue(true);
            }

            @Override
            public void deleteFailed(String msg) {
                mBooleanDeleteMutableLiveData.postValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mBooleanDeleteMutableLiveData.postValue(false);
            }
        });
    }

    /**按手机号搜索会员*/
//    public void getVipByPhone(String phone){
//        mUsersRemoteSource.searchMembers(BaseApplication.getToken(),phone, new UsersInterface.SearchMembersCallback() {
//            @Override
//            public void searchSucceed(BaseResponse<MyMemberResponse.DataBean> searchMemberResponse) {
//                if (searchMemberResponse.getData() != null) {
//                    mListSearchMutableLiveData.postValue(searchMemberResponse.getData());
//                } else {
//                    mListSearchMutableLiveData.postValue(null);
//                }
//            }
//
//            @Override
//            public void searchFailed(String msg) {
//
//            }
//
//            @Override
//            public void error(ExceptionHandle.ResponseException e) {
//
//            }
//        });
//    }
}
