package com.example.healthmanage.ui.fragment.qualification;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;

/**
 * Description:
 * Author:bwang
 * Date:2020/12/2 17:01
 */
public class SecondStepViewModel extends BaseViewModel {

    public MutableLiveData<String> personalProfile = new MutableLiveData<>();
    public MutableLiveData<String> field = new MutableLiveData<>();

    public SecondStepViewModel() {
    }
}
