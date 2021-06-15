package com.example.healthmanage.ui.activity.qualification;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.qualification.response.DepartmentResponse;
import com.example.healthmanage.ui.activity.qualification.response.HospitalResponse;

import java.util.List;

public class DepartMentViewModel extends BaseViewModel {
    public String title = "选择科室";
    public String hospitalTitle = "选择医院";
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<List<DepartmentResponse.DataBean>> departmentMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<DepartmentResponse.DataBean>> departmentRightMutableData = new MutableLiveData<>();
    public MutableLiveData<List<HospitalResponse.DataBean>> hospitalMutableLiveData = new MutableLiveData<>();

    public DepartMentViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getDepartmentList(){
        usersRemoteSource.getDepartmentListData(new UsersInterface.GetDepartmentDataCallback() {
            @Override
            public void sendSucceed(DepartmentResponse departmentResponse) {
                if (departmentResponse.getData() != null){
                    departmentMutableLiveData.postValue(departmentResponse.getData());
                }else {
                    departmentMutableLiveData.postValue(null);
                }
            }

            @Override
            public void sendFailed(String msg) {

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    public void getDepartMentByName(String name){
        usersRemoteSource.getDepartmentByName(name, new UsersInterface.GetDepartmentByNameCallback() {
            @Override
            public void sendSucceed(DepartmentResponse departmentItemResponse) {
                if (departmentItemResponse.getData() != null){
                    departmentRightMutableData.postValue(departmentItemResponse.getData());
                }else {
                    departmentRightMutableData.postValue(null);
                }
            }

            @Override
            public void sendFailed(String msg) {

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    public void getHospitalByName(String name){
        usersRemoteSource.getHospitalByName(name, new UsersInterface.GetHospitalByNameCallback() {
            @Override
            public void sendSucceed(HospitalResponse hospitalResponse) {
                if (hospitalResponse.getData() != null){
                    hospitalMutableLiveData.postValue(hospitalResponse.getData());
                }else {
                    hospitalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void sendFailed(String msg) {

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

}
