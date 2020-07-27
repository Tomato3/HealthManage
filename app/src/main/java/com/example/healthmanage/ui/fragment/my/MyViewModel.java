package com.example.healthmanage.ui.fragment.my;


import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersRemoteSource;

public class MyViewModel extends BaseViewModel {

    public MutableLiveData<String> phone = new MutableLiveData<>("");
    public MutableLiveData<String> name = new MutableLiveData<>("");
    public MutableLiveData<String> rank = new MutableLiveData<>("");
    public MutableLiveData<String> avatar = new MutableLiveData<>("http://b-ssl.duitang.com/uploads/item/201803/02/20180302222228_v3JdH.jpeg");

    UsersRemoteSource usersRemoteSource;

    public MyViewModel() {
        usersRemoteSource = new UsersRemoteSource();
        name.setValue(BaseApplication.getUserInfoBean().getSysNickName());
    }

    public void empty() {
        getUiChangeEvent().getToastTxt().setValue("开发中");
    }
}
