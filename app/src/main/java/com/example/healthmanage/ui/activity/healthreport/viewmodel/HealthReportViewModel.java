package com.example.healthmanage.ui.activity.healthreport.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.healthreport.HealthReportInfo;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportConfirmResponse;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportDetailResponse;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportResponse;

import java.util.List;

public class HealthReportViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<String> dateChoose = new MutableLiveData<>();
    public MutableLiveData<List<HealthReportResponse.DataBean>> healthReportMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> saveSuccess = new MutableLiveData<>();
    public MutableLiveData<String> bloodPressure = new MutableLiveData<>("");
    public MutableLiveData<String> bloodSugar = new MutableLiveData<>("");
    public MutableLiveData<String> bloodOxygen= new MutableLiveData<>("");
    public MutableLiveData<String> temprature = new MutableLiveData<>("");
    public MutableLiveData<String> sportStatus = new MutableLiveData<>("");
    public MutableLiveData<String> sleepStatus = new MutableLiveData<>("");
    public MutableLiveData<String> reportName = new MutableLiveData<>("");
    public MutableLiveData<String> reportTime = new MutableLiveData<>("");
    public MutableLiveData<String> reportStartTime = new MutableLiveData<>("");
    public MutableLiveData<String> reportEndTime = new MutableLiveData<>("");
    public MutableLiveData<String> reportCreateTime = new MutableLiveData<>("");
    public MutableLiveData<HealthReportDetailResponse.DataBean> healthReportDetailData = new MutableLiveData<>();


    public HealthReportViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getHealthReportAll(int userId,String date){
        usersRemoteSource.getHealthReportList(userId, date,BaseApplication.getToken(), new UsersInterface.GetHealthReportAllCallback() {
            @Override
            public void sendSucceed(HealthReportResponse healthReportResponse) {
                if (healthReportResponse.getData()!=null && healthReportResponse.getData().size()>0){
                    healthReportMutableLiveData.postValue(healthReportResponse.getData());
                }else {
                    healthReportMutableLiveData.postValue(null);
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

    public void saveHealthReport(HealthReportInfo healthReportInfo){
        usersRemoteSource.saveHealthReport(healthReportInfo, new UsersInterface.SaveHealthReportCallback() {
            @Override
            public void sendSucceed(HealthReportConfirmResponse healthReportConfirmResponse) {
                saveSuccess.postValue(true);
            }

            @Override
            public void sendFailed(String msg) {
                saveSuccess.postValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    public void getHealthReport(int id){
        usersRemoteSource.getHealthReport(id, BaseApplication.getToken(), new UsersInterface.GetHealthReportCallback() {
            @Override
            public void sendSucceed(HealthReportDetailResponse healthReportDetailResponse) {
                if (healthReportDetailResponse.getData()!=null){
                    healthReportDetailData.postValue(healthReportDetailResponse.getData());
                }else {
                    healthReportDetailData.postValue(null);
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
