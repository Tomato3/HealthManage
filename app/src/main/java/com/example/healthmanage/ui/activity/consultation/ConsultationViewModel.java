package com.example.healthmanage.ui.activity.consultation;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.consultation.response.AddConsultationPlanResponse;
import com.example.healthmanage.ui.activity.consultation.response.AddPatientInfoResponse;
import com.example.healthmanage.ui.activity.consultation.response.ConsultationListResponse;
import com.example.healthmanage.ui.activity.consultation.response.DoctorTeamListResponse;
import com.example.healthmanage.ui.activity.consultation.response.PatientInfoBean;
import com.example.healthmanage.ui.activity.qualification.response.CertificateResponse;

import java.io.File;
import java.util.List;

public class ConsultationViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<List<DoctorTeamListResponse.DataBean>> hospitalDepartMutableData = new MutableLiveData<>();
    public MutableLiveData<List<String>> patientPhotos = new MutableLiveData<>();
    //患者病史
    public MutableLiveData<String> patientHistory = new MutableLiveData<>();
    //初步诊断
    public MutableLiveData<String> primaryDiagnosis = new MutableLiveData<>();
    //会诊要求与目的
    public MutableLiveData<String> consultationPurpose = new MutableLiveData<>();

    public MutableLiveData<Boolean> isAddSuccess = new MutableLiveData<>();

    public MutableLiveData<Boolean> isUpdateSuccess = new MutableLiveData<>();

    public MutableLiveData<List<ConsultationListResponse.DataBean>> consultationListLiveData = new MutableLiveData<>();

    public ConsultationViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getHospitalDepartmentList(){
        usersRemoteSource.getHospitalDepartmentList(BaseApplication.getToken(), new UsersInterface.GetHospitalDepartmentListCallback() {
            @Override
            public void getSucceed(DoctorTeamListResponse doctorTeamListResponse) {
                if (doctorTeamListResponse.getData()!=null && doctorTeamListResponse.getData().size()>0){
                    hospitalDepartMutableData.setValue(doctorTeamListResponse.getData());
                }else {
                    hospitalDepartMutableData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                hospitalDepartMutableData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                hospitalDepartMutableData.setValue(null);
            }
        });
    }

    public void uploadPhotoForPatient(List<File> files) {
        usersRemoteSource.getUploadCertificate(files, new UsersInterface.UpCertificateCallback() {
            @Override
            public void sendSucceed(CertificateResponse certificateResponse) {
                if (certificateResponse.getData() != null && certificateResponse.getData().size()>0){
                    patientPhotos.postValue(certificateResponse.getData());
                }else {
                    patientPhotos.postValue(null);
                }
            }

            @Override
            public void sendFailed(String msg) {
                patientPhotos.postValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                patientPhotos.postValue(null);
            }
        });
    }

    public void insertPatientExamine(PatientInfoBean patientInfoBean){
        usersRemoteSource.insertPatientExamine(patientInfoBean, new UsersInterface.InsertPatientExamineCallback() {
            @Override
            public void insertSucceed(AddPatientInfoResponse addPatientInfoResponse) {
                Log.i("成功",addPatientInfoResponse.getMessage());
                isAddSuccess.setValue(true);
            }

            @Override
            public void insertFailed(String msg) {
                Log.e("失败",msg);
                isAddSuccess.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isAddSuccess.setValue(false);
            }
        });
    }

    public void getPatientExamine(int status){
        usersRemoteSource.getPatientExamine(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId(), status, BaseApplication.getToken(), new UsersInterface.GetPatientExamineCallback() {
            @Override
            public void getSucceed(ConsultationListResponse consultationListResponse) {
                if (consultationListResponse.getData()!=null && consultationListResponse.getData().size()>0){
                    consultationListLiveData.setValue(consultationListResponse.getData());
                }else {
                    consultationListLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                consultationListLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                consultationListLiveData.setValue(null);
            }
        });
    }

    public void updatePatientExamine(int id,String examinePlan){
        usersRemoteSource.updatePatientExamine(id, examinePlan, BaseApplication.getToken(), new UsersInterface.UpdatePatientExamineCallback() {
            @Override
            public void updateSucceed(AddConsultationPlanResponse addConsultationPlanResponse) {
                isUpdateSuccess.setValue(true);
            }

            @Override
            public void updateFailed(String msg) {
                isUpdateSuccess.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isUpdateSuccess.setValue(false);
            }
        });
    }

}
