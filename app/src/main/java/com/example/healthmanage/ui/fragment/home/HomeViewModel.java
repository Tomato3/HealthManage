package com.example.healthmanage.ui.fragment.home;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.MyMemberResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.view.MyFocusRecyclerView;
import com.example.healthmanage.view.MyMemberRecyclerView;
import com.example.healthmanage.widget.DropdownBar;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewModel {
    private static String TAG = "HomeViewModel";

    public MutableLiveData<ArrayList<DropdownBar>>
            dropdownBarLists = new MutableLiveData<>();

    public MutableLiveData<List<MyMemberRecyclerView>>
            myMemberMutableLiveData = new MutableLiveData<>();

    public List<MyMemberRecyclerView>
            myMemberRecyclerViewList;

    public MutableLiveData<List<MyFocusRecyclerView>>
            myFocusMutableLiveData = new MutableLiveData<>();

    private List<MyFocusRecyclerView>
            myFocusRecyclerViewList;


    String memberRank = "";

    UsersRemoteSource usersRemoteSource;

    public HomeViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void loadMyFocus() {
        usersRemoteSource.loadMyFocus(String.valueOf(BaseApplication.getUserInfoBean().getSysId()),
                new UsersInterface.LoadMyMembersCallback() {
                    @Override
                    public void loadSucceed(MyMemberResponse myMemberResponse) {
                        myFocusRecyclerViewList = new ArrayList<>();
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
                            myFocusRecyclerViewList.add(new MyFocusRecyclerView(
                                    myMemberResponse.getData().get(i).getUserName(),
                                    memberRank));
                        }
                        myFocusMutableLiveData.setValue(myFocusRecyclerViewList);
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


    public void loadMyMembers() {
        usersRemoteSource.loadMyMembers(String.valueOf(BaseApplication.getUserInfoBean().getSysId()), new UsersInterface.LoadMyMembersCallback() {
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

    @Override
    public void setDropdownBar(DropdownBar dropdownBar) {
        super.setDropdownBar(dropdownBar);
    }
}
