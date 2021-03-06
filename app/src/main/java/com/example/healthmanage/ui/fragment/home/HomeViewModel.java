package com.example.healthmanage.ui.fragment.home;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.bean.recyclerview.MyFocusRecyclerView;
import com.example.healthmanage.bean.recyclerview.MyMemberRecyclerView;
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
        usersRemoteSource.loadMyFocus(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()),
                new UsersInterface.LoadMyMembersCallback() {
                    @Override
                    public void loadSucceed(MyMemberResponse myMemberResponse) {
                        if (myMemberResponse.getData() != null) {
                            myFocusRecyclerViewList = new ArrayList<>();
                            for (int i = 0; i < myMemberResponse.getData().size(); i++) {
                                switch (myMemberResponse.getData().get(i).getRank()) {
                                    case 0:
                                        memberRank = "????????????";
                                        break;
                                    case 1:
                                        memberRank = "????????????";
                                        break;
                                    case 2:
                                        memberRank = "SVIP??????";
                                        break;
                                }
                                myFocusRecyclerViewList.add(new MyFocusRecyclerView(
                                        myMemberResponse.getData().get(i).getNickName(),
                                        memberRank, myMemberResponse.getData().get(i).getId()));
                            }
                            myFocusMutableLiveData.setValue(myFocusRecyclerViewList);
                        }
                    }

                    @Override
                    public void loadFailed(String msg) {
                        getUiChangeEvent().getToastTxt().setValue(msg);
                    }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {
                    }
                });
    }


    public void loadMyMembers() {
        usersRemoteSource.loadMyMembers(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()), new UsersInterface.LoadMyMembersCallback() {
            @Override
            public void loadSucceed(MyMemberResponse myMemberResponse) {
                myMemberRecyclerViewList = new ArrayList<>();
                if (myMemberResponse.getData() != null) {
                    for (int i = 0; i < myMemberResponse.getData().size(); i++) {
                        switch (myMemberResponse.getData().get(i).getRank()) {
                            case 0:
                                memberRank = "????????????";
                                break;
                            case 1:
                                memberRank = "????????????";
                                break;
                            case 2:
                                memberRank = "SVIP??????";
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
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                getUiChangeEvent().getToastTxt().setValue(e.getMessage());
            }
        });
    }

    @Override
    public void setDropdownBar(DropdownBar dropdownBar) {
        super.setDropdownBar(dropdownBar);
    }
}
