package com.example.healthmanage.ui.activity.temperature.ui;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.team.response.TeamMemberResponse;
import com.example.healthmanage.ui.activity.temperature.InsertPrescriptionBean;
import com.example.healthmanage.ui.activity.temperature.ReceivePatientBean;
import com.example.healthmanage.ui.activity.temperature.response.InsertResponse;
import com.example.healthmanage.ui.activity.temperature.response.PrescriptionResponse;
import com.example.healthmanage.ui.activity.temperature.response.RefusalResponse;
import com.example.healthmanage.ui.activity.temperature.response.TemperatureResponse;
import com.example.healthmanage.ui.activity.temperature.response.TransferBean;
import com.example.healthmanage.ui.activity.temperature.response.UpdateResponse;

import java.util.List;

public class TemperatureViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<List<TemperatureResponse.DataBean>> temperatureLiveData = new MutableLiveData<>();
    public MutableLiveData<List<TeamMemberResponse.DataBean>> doctorMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSendTransferSucceed = new MutableLiveData<>();
    public MutableLiveData<Boolean> isInsertSucceed = new MutableLiveData<>();
    public MutableLiveData<Boolean> isReceiveSucceed = new MutableLiveData<>();
    public MutableLiveData<String> relayData = new MutableLiveData<>();
    public MutableLiveData<String>preliminaryDiagnosisData = new MutableLiveData<>();
    public MutableLiveData<List<PrescriptionResponse.DataBean>> prescriptionResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRefusalSucceed = new MutableLiveData<>();

    public TemperatureViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getHealthConsultStatus(int status){
        usersRemoteSource.getHealthConsultStatus(status, BaseApplication.getToken(), new UsersInterface.GetHealthConsultCallback() {
            @Override
            public void getSucceed(TemperatureResponse temperatureResponse) {
                if (temperatureResponse.getData()!=null && temperatureResponse.getData().size()>0){
                    temperatureLiveData.setValue(temperatureResponse.getData());
                }else {
                    temperatureLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                temperatureLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                temperatureLiveData.setValue(null);
            }
        });
    }

    public void getHealthConsultTransferStatus(int transferStatus){
        usersRemoteSource.getHealthConsultTransferStatus(transferStatus, BaseApplication.getToken(), new UsersInterface.GetHealthConsultCallback() {
            @Override
            public void getSucceed(TemperatureResponse temperatureResponse) {
                if (temperatureResponse.getData()!=null && temperatureResponse.getData().size()>0){
                    temperatureLiveData.setValue(temperatureResponse.getData());
                }else {
                    temperatureLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                temperatureLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                temperatureLiveData.setValue(null);
            }
        });
    }

    public void getDoctorList(int roleId){
        usersRemoteSource.getDoctorTeamList(BaseApplication.getToken(), roleId,new UsersInterface.GetDoctorTeamListCallback() {
            @Override
            public void getSucceed(TeamMemberResponse teamMemberResponse) {
                if (teamMemberResponse.getData()!=null && teamMemberResponse.getData().size()>0){
                    doctorMutableLiveData.postValue(teamMemberResponse.getData());
                }else {
                    doctorMutableLiveData.postValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                doctorMutableLiveData.postValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    public void sendTransferTemperature(TransferBean transferBean){
        usersRemoteSource.updateHealthConsult(transferBean, new UsersInterface.UpdateHealthConsultCallback() {
            @Override
            public void updateSucceed(UpdateResponse updateResponse) {
                isSendTransferSucceed.setValue(true);
            }

            @Override
            public void updateFailed(String msg) {
                isSendTransferSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isSendTransferSucceed.setValue(false);
            }
        });
    }

    public void insertHealthConsultDrugModel(InsertPrescriptionBean insertPrescriptionBean){
        usersRemoteSource.insertHealthConsultDrugModel(insertPrescriptionBean, new UsersInterface.InsertHealthConsultDrugModelCallback() {
            @Override
            public void insertSucceed(InsertResponse insertResponse) {
                isInsertSucceed.setValue(true);
            }

            @Override
            public void insertFailed(String msg) {
                isInsertSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isInsertSucceed.setValue(false);
            }
        });
    }

    public void insertHealthConsultDrug(ReceivePatientBean receivePatientBean){
        usersRemoteSource.insertHealthConsultDrug(receivePatientBean, new UsersInterface.InsertHealthConsultDrugCallback() {
            @Override
            public void insertSucceed(InsertResponse insertResponse) {
                isReceiveSucceed.setValue(true);
            }

            @Override
            public void insertFailed(String msg) {
                isReceiveSucceed.setValue(false);
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isReceiveSucceed.setValue(false);
            }
        });
    }

    public void getHealthConsultDrugModel(int modelType){
        usersRemoteSource.getHealthConsultDrugModel(modelType, BaseApplication.getToken(), new UsersInterface.GetHealthConsultDrugModelCallback() {
            @Override
            public void getSucceed(PrescriptionResponse prescriptionResponse) {
                if (prescriptionResponse.getData() != null && prescriptionResponse.getData().size()>0){
                    prescriptionResponseMutableLiveData.setValue(prescriptionResponse.getData());
                }else {
                    prescriptionResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                prescriptionResponseMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                prescriptionResponseMutableLiveData.setValue(null);
            }
        });
    }

    public void getHealthConsultDrugBack(int id,String reason){
        usersRemoteSource.getHealthConsultDrugBack(id, reason, BaseApplication.getToken(), new UsersInterface.GetHealthConsultDrugBackCallback() {
            @Override
            public void getSucceed(RefusalResponse refusalResponse) {
                isRefusalSucceed.setValue(true);
            }

            @Override
            public void getFailed(String msg) {
                isRefusalSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isRefusalSucceed.setValue(false);
            }
        });
    }

}
