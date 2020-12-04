package com.example.healthmanage.ui.activity.mycomment;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.bean.recyclerview.MyCommentRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyCommentViewModel extends BaseViewModel {

    public MutableLiveData<List<MyCommentRecyclerView>> myCommentMutableLiveData =
            new MutableLiveData<>();

    private List<MyCommentRecyclerView> myCommentRecyclerViewList;

    UsersRemoteSource usersRemoteSource;

    public MyCommentViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getMyComment() {
        myCommentRecyclerViewList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            myCommentRecyclerViewList.add(new MyCommentRecyclerView("http://b-ssl.duitang" +
                    ".com/uploads/item/201803/02/20180302222228_v3JdH.jpeg",
                    "测试昵称", "测试评价内容", "2020-7-30 19:55:55"));
        }
        myCommentMutableLiveData.setValue(myCommentRecyclerViewList);

    }
}
