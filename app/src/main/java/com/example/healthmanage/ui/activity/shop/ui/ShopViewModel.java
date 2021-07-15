package com.example.healthmanage.ui.activity.shop.ui;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersRemoteSource;

/**
 * desc:
 * date:2021/6/25 15:28
 * author:bWang
 */
public class ShopViewModel extends BaseViewModel {
    private UsersRemoteSource mUsersRemoteSource;
    public MutableLiveData<String> mCity = new MutableLiveData<>();
    public MutableLiveData<String> mAddress = new MutableLiveData<>();
    public MutableLiveData<String> mPhone = new MutableLiveData<>();
    public MutableLiveData<String> mType = new MutableLiveData<>();

    public ShopViewModel() {
        mUsersRemoteSource = new UsersRemoteSource();
    }
}
