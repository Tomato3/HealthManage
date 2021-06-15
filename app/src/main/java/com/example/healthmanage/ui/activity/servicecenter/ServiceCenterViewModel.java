package com.example.healthmanage.ui.activity.servicecenter;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersRemoteSource;

public class ServiceCenterViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;

    public ServiceCenterViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }
}
