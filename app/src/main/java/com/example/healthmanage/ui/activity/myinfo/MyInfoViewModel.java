package com.example.healthmanage.ui.activity.myinfo;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;

public class MyInfoViewModel extends BaseViewModel {

    public MutableLiveData<String> avatar = new MutableLiveData<>("http://b-ssl.duitang.com/uploads/item/201803/02/20180302222228_v3JdH.jpeg");

    public MutableLiveData<String> nickName = new MutableLiveData<>("");

    public MutableLiveData<String> phone = new MutableLiveData<>("");

    public MyInfoViewModel() {
        nickName.setValue(BaseApplication.getUserInfoBean().getSysNickName());
        phone.setValue(BaseApplication.getUserInfoBean().getPhone());
    }


}
