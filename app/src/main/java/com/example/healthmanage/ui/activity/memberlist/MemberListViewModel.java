package com.example.healthmanage.ui.activity.memberlist;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.searchMember.SearchMemberActivity;
import com.example.healthmanage.bean.recyclerview.MyMemberRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MemberListViewModel extends BaseViewModel {

    public MutableLiveData<String> searchPhone = new MutableLiveData<>("");
    public MutableLiveData<String> marginInTop = new MutableLiveData<>("110dp");
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<List<MyMemberRecyclerView>>
            myMemberMutableLiveData = new MutableLiveData<>();

    public List<MyMemberRecyclerView>
            myMemberRecyclerViewList;

    private String memberRank = "";

    public MemberListViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void searchMember() {
        startActivity(SearchMemberActivity.class);
    }

    public void loadDifferentLevelMember(int type, int rank) {
        usersRemoteSource.selectMember(String.valueOf(BaseApplication.getUserInfoBean().getSysId()), rank, new UsersInterface.LoadMyMembersCallback() {
            @Override
            public void loadSucceed(MyMemberResponse myMemberResponse) {
                if (type == 0) {
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
                    } else {
                        myMemberMutableLiveData.setValue(null);
                        getUiChangeEvent().getToastTxt().setValue("暂无会员！");
                    }
                } else {
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
                            if (myMemberResponse.getData().get(i).getFollowStatus() == 1) {
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
                    } else {
                        myMemberMutableLiveData.setValue(null);
                        getUiChangeEvent().getToastTxt().setValue("暂无会员！");
                    }
                }
            }

            @Override
            public void loadFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                getUiChangeEvent().getToastTxt().setValue(e.getMessage());
            }
        });
    }
}
