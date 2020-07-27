package com.example.healthmanage.ui.activity.searchMember;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.MyMemberResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.view.MyMemberRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchMemberViewModel extends BaseViewModel {

    public MutableLiveData<String> search = new MutableLiveData<>("");
    UsersRemoteSource usersRemoteSource;
    public MutableLiveData<List<MyMemberRecyclerView>>
            myMemberMutableLiveData = new MutableLiveData<>();

    public List<MyMemberRecyclerView>
            myMemberRecyclerViewList;

    private String memberRank = "";

    public SearchMemberViewModel(){
        usersRemoteSource = new UsersRemoteSource();
    }

public void search(){
    usersRemoteSource.searchMemberByName(search.getValue(),
            String.valueOf(BaseApplication.getUserInfoBean().getSysId()), new UsersInterface.LoadMyMembersCallback() {
                @Override
                public void loadSucceed(MyMemberResponse myMemberResponse) {
                    myMemberRecyclerViewList = new ArrayList<>();
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
                                myMemberResponse.getData().get(i).getUserName(),
                                memberRank,
                                myMemberResponse.getData().get(i).getFollowStatus() == 0 ?
                                        false :
                                        true,
                                myMemberResponse.getData().get(i).getId()));
                    }
                    myMemberMutableLiveData.setValue(myMemberRecyclerViewList);
                }

                @Override
                public void loadFailed(String msg) {

                }

                @Override
                public void error(ExceptionHandle.ResponseException e) {

                }
            });
}
}
