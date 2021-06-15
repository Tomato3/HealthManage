package com.example.healthmanage.ui.activity.healthrecord;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.healthrecord.response.CheckReportResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.HealthRecordResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.HistoryAssessListResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.MedicineBookResponse;

import java.util.List;

public class HealthRecordViewModel extends BaseViewModel {
    public MutableLiveData<HealthRecordResponse.DataBean> dataBeanMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<CheckReportResponse.DataBean>> checkReportListLiveData = new MutableLiveData<>();
    public MutableLiveData<List<MedicineBookResponse.DataBean>> medicineListLiveData = new MutableLiveData<>();
    public MutableLiveData<List<HistoryAssessListResponse.DataBean>> historyAssessListLiveData = new MutableLiveData<>();
    private UsersRemoteSource usersRemoteSource;

    public HealthRecordViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getUserInfo(int userId){
        usersRemoteSource.getUserInfo(userId, BaseApplication.getToken(), new UsersInterface.GetUserInfoCallback() {
            @Override
            public void sendSucceed(HealthRecordResponse healthRecordResponse) {
                if (healthRecordResponse.getData()!=null){
                    dataBeanMutableLiveData.postValue(healthRecordResponse.getData());
                }else {
                    dataBeanMutableLiveData.postValue(null);
                }
            }

            @Override
            public void sendFailed(String msg) {
                dataBeanMutableLiveData.postValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    /**
     *
     * @param userId
     */
    public void getCheckReportList(int userId){
        usersRemoteSource.getCheckReportList(userId, BaseApplication.getToken(), new UsersInterface.GetCheckReportListCallback() {
            @Override
            public void sendSucceed(CheckReportResponse checkReportResponse) {
                if (checkReportResponse.getData()!=null && checkReportResponse.getData().size()>0){
                    checkReportListLiveData.postValue(checkReportResponse.getData());
                }else {
                    checkReportListLiveData.postValue(null);
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

    /**
     *
     * @param userId
     */
    public void getHistoryAssessList(int userId){
        usersRemoteSource.getHistoryAssessList(userId, BaseApplication.getToken(), new UsersInterface.GetHistoryAssessListCallback() {
            @Override
            public void sendSucceed(HistoryAssessListResponse historyAssessListResponse) {
                if (historyAssessListResponse.getData()!=null && historyAssessListResponse.getData().size()>0){
                    historyAssessListLiveData.postValue(historyAssessListResponse.getData());
                }else {
                    historyAssessListLiveData.postValue(null);
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

    public void getMedicalAll(int userId){
        usersRemoteSource.getMedicalRecordAll(userId, BaseApplication.getToken(), new UsersInterface.GetMedicalRecordAllCallback() {
            @Override
            public void sendSucceed(MedicineBookResponse medicineBookResponse) {
                if (medicineBookResponse.getData()!=null && medicineBookResponse.getData().size()>0){
                    medicineListLiveData.postValue(medicineBookResponse.getData());
                }else {
                    medicineListLiveData.postValue(null);
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
