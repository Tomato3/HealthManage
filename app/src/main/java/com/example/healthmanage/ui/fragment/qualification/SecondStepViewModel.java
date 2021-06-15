package com.example.healthmanage.ui.fragment.qualification;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.qualification.response.CertificateResponse;
import com.example.healthmanage.ui.activity.qualification.response.UploadResponse;
import com.example.healthmanage.ui.fragment.qualification.bean.DoctorInfo;
import com.example.healthmanage.ui.fragment.qualification.bean.UpdateDoctorInfo;

import java.io.File;
import java.util.List;

/**
 * Description:
 * Author:bwang
 * Date:2020/12/2 17:01
 */
public class SecondStepViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;

    public MutableLiveData<String> logoFile = new MutableLiveData<>();
    public MutableLiveData<String> frontUrl = new MutableLiveData<>();
    public MutableLiveData<String> backUrl = new MutableLiveData<>();
    public MutableLiveData<String> personalProfile = new MutableLiveData<>();
    public MutableLiveData<String> field = new MutableLiveData<>();
    public MutableLiveData<List<String>> certicates = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSaveSuccess = new MutableLiveData<>();
    public MutableLiveData<Boolean> isUpdateSuccess = new MutableLiveData<>();

    public SecondStepViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getLogo(File file){
        usersRemoteSource.getUploadIdCard(file, new UsersInterface.UpLoadIdCardCallback() {
            @Override
            public void sendSucceed(UploadResponse uploadResponse) {
                if (uploadResponse.getData()!=null){
                    logoFile.postValue(uploadResponse.getData());
                }else {
                    logoFile.postValue(null);
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

    public void getFrontUrl(File file){
        usersRemoteSource.getUploadIdCard(file, new UsersInterface.UpLoadIdCardCallback() {
            @Override
            public void sendSucceed(UploadResponse uploadResponse) {
                if (uploadResponse.getData()!=null){
                    frontUrl.postValue(uploadResponse.getData());
                }else {
                    frontUrl.postValue(null);
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

    public void getBackUrl(File file){
        usersRemoteSource.getUploadIdCard(file, new UsersInterface.UpLoadIdCardCallback() {
            @Override
            public void sendSucceed(UploadResponse uploadResponse) {
                if (uploadResponse.getData()!=null){
                    backUrl.postValue(uploadResponse.getData());
                }else {
                    backUrl.postValue(null);
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

    public void uploadCerticate(List<File> files) {
        usersRemoteSource.getUploadCertificate(files, new UsersInterface.UpCertificateCallback() {
            @Override
            public void sendSucceed(CertificateResponse certificateResponse) {
                if (certificateResponse.getData() != null){
                    certicates.postValue(certificateResponse.getData());
                }else {
                    certicates.postValue(null);
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

    public void saveUserInfo(DoctorInfo doctorInfo){
        usersRemoteSource.saveDoctorInfo(doctorInfo, new UsersInterface.SaveDoctorInfoCallback() {
            @Override
            public void sendSucceed(UploadResponse uploadResponse) {
                isSaveSuccess.postValue(true);
            }

            @Override
            public void sendFailed(String msg) {
                isSaveSuccess.postValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    public void updateDoctorInfo(UpdateDoctorInfo updateDoctorInfo){
        usersRemoteSource.updateDoctorInfo(updateDoctorInfo, new UsersInterface.UpdateDoctorInfoCallback() {
            @Override
            public void sendSucceed(UploadResponse uploadResponse) {
                isUpdateSuccess.postValue(true);
            }

            @Override
            public void sendFailed(String msg) {
                isUpdateSuccess.postValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

}
