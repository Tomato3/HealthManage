package com.example.healthmanage.ui.activity.searchMember;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.bean.recyclerview.MyMemberRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchMemberViewModel extends BaseViewModel {

    UsersRemoteSource usersRemoteSource;
    public MutableLiveData<List<MyMemberRecyclerView>>
            myMemberMutableLiveData = new MutableLiveData<>();

    public List<MyMemberRecyclerView>
            myMemberRecyclerViewList;

    private String memberRank = "";

    public SearchMemberViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void search(String searchTxt) {
        if (!TextUtils.isEmpty(searchTxt)) {
            usersRemoteSource.searchMemberByName(searchTxt,
                    String.valueOf(BaseApplication.getUserInfoBean().getSysId()), new UsersInterface.LoadMyMembersCallback() {
                        @Override
                        public void loadSucceed(MyMemberResponse myMemberResponse) {
                            myMemberRecyclerViewList = new ArrayList<>();
                            if (myMemberResponse.getData() != null) {
                                for (int i = 0; i < myMemberResponse.getData().size(); i++) {
                                    switch (myMemberResponse.getData().get(i).getRank()) {
                                        case 0:
                                            memberRank = "普通会员";
                                            break;
                                        case 1:
                                            memberRank = "贵宾会员";
                                            break;
                                        case 2:
                                            memberRank = "SVIP会员";
                                            break;
                                    }
                                    myMemberRecyclerViewList.add(new MyMemberRecyclerView(
                                            myMemberResponse.getData().get(i).getNickName(),
                                            memberRank,
                                            myMemberResponse.getData().get(i).getFollowStatus() == 0 ?
                                                    false :
                                                    true,
                                            myMemberResponse.getData().get(i).getId()));
                                }
                                myMemberMutableLiveData.setValue(myMemberRecyclerViewList);
                            }
                        }

                        @Override
                        public void loadFailed(String msg) {
                            showToast(msg, 1);
                        }

                        @Override
                        public void error(ExceptionHandle.ResponseException e) {
                        }
                    });
        } else {
            myMemberMutableLiveData.setValue(null);
        }
    }
}
