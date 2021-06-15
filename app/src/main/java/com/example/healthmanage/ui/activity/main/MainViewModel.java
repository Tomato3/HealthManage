package com.example.healthmanage.ui.activity.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.qualification.response.DoctorInfoResponse;
import com.example.healthmanage.ui.activity.team.response.DoctorTeamResponse;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MainViewModel extends BaseViewModel {


    UsersRemoteSource usersRemoteSource;

    public MainViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void setUserInfo(String token) {
        if (token != null) {
            Log.d(HTAG, "setUserInfo: ==============>");
        } else {
            Log.d(HTAG, "setUserInfo: ==============>null");

        }
    }


}
