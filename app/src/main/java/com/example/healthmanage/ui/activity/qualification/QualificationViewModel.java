package com.example.healthmanage.ui.activity.qualification;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.QualificationInputItem;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.bean.network.response.LoginResponse;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.main.MainActivity;

import java.util.List;

public class QualificationViewModel extends BaseViewModel {

    public MutableLiveData<String> title = new MutableLiveData<>("实名资质认证");
    public MutableLiveData<String> operationText = new MutableLiveData<>("下一步");
    public MutableLiveData<String> profession = new MutableLiveData<>("请选择您的职业");
    public MutableLiveData<List<QualificationInputItem>> qualificationInputItemListMutableLiveData = new MutableLiveData<>();
    private UsersRemoteSource usersRemoteSource;

    public QualificationViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void toMain(String phone, String password, String roleId) {
        usersRemoteSource.loginByPassword(phone, password, "9",
                new UsersInterface.LoginResponseCallback() {
            @Override
            public void loginSucceed(LoginResponse loginResponse) {
                startActivity(MainActivity.class);
            }

            @Override
            public void loginFailed(String msg) {

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }


}
