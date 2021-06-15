package com.example.healthmanage.ui.activity.referral;


import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.qualification.response.CertificateResponse;
import com.example.healthmanage.ui.activity.referral.response.InsertReferralResponse;
import com.example.healthmanage.ui.activity.referral.response.ReferralBean;
import com.example.healthmanage.ui.activity.referral.response.ReferralResponse;

import java.io.File;
import java.util.List;

public class ReferralViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<List<String>> patientPhotos = new MutableLiveData<>();
    //患者病史
    public MutableLiveData<String> patientHistory = new MutableLiveData<>();
    //初步诊断
    public MutableLiveData<String> primaryDiagnosis = new MutableLiveData<>();
    //转诊原因
    public MutableLiveData<String> referralReason = new MutableLiveData<>();
    //转出医院
    public MutableLiveData<String> transferHospital = new MutableLiveData<>();
    //转入医院
    public MutableLiveData<String> intoHospital = new MutableLiveData<>();
    //是否提交成功
    public MutableLiveData<Boolean> isInsertSuccess = new MutableLiveData<>();
    //转诊列表
    public MutableLiveData<List<ReferralResponse.DataBean>> referralListData = new MutableLiveData<>();


    public ReferralViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void insertPatientReferral(ReferralBean referralBean){
        usersRemoteSource.insertPatientReferral(referralBean, new UsersInterface.InsertPatientReferralCallback() {
            @Override
            public void insertSucceed(InsertReferralResponse insertReferralResponse) {
                isInsertSuccess.setValue(true);
            }

            @Override
            public void insertFailed(String msg) {
                isInsertSuccess.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isInsertSuccess.setValue(false);
            }
        });
    }

    public void getPatientReferral(){
        usersRemoteSource.getPatientReferral(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId(), BaseApplication.getToken(), new UsersInterface.GetPatientReferralCallback() {
            @Override
            public void getSucceed(ReferralResponse referralResponse) {
                if (referralResponse.getData()!=null && referralResponse.getData().size()>0){
                    referralListData.setValue(referralResponse.getData());
                }else {
                    referralListData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                referralListData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                referralListData.setValue(null);
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
}
