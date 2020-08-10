package com.example.healthmanage.ui.activity.memberinfo;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;

public class MemberInfoViewModel extends BaseViewModel {

    public MutableLiveData<String> memberName = new MutableLiveData<>("");
    public MutableLiveData<String> memberPhone = new MutableLiveData<>("");
    public MutableLiveData<String> memberAvatar = new MutableLiveData<>("");


}
