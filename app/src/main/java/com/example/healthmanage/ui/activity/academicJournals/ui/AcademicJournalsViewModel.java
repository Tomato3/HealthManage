package com.example.healthmanage.ui.activity.academicJournals.ui;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.academicJournals.response.PeriodicalListResponse;
import com.example.healthmanage.ui.activity.qualification.response.UploadResponse;

import java.io.File;
import java.util.List;

public class AcademicJournalsViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;
    //个人简介
    public MutableLiveData<String> personalInfo = new MutableLiveData<>();
    //标题
    public MutableLiveData<String> journalsTitle = new MutableLiveData<>();
    //投稿栏目
    public MutableLiveData<String> contributionColumn = new MutableLiveData<>();

    public MutableLiveData<String> picUrl = new MutableLiveData<>();

    public MutableLiveData<List<PeriodicalListResponse.DataBean>> periodicalLiveData = new MutableLiveData<>();

    public AcademicJournalsViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getPicUrl(File file){
        usersRemoteSource.getUploadIdCard(file, new UsersInterface.UpLoadIdCardCallback() {
            @Override
            public void sendSucceed(UploadResponse uploadResponse) {
                if (uploadResponse.getData()!=null){
                    picUrl.postValue(uploadResponse.getData());
                }else {
                    picUrl.postValue(null);
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

    public void getPeriodicalList(int status){
        usersRemoteSource.getPeriodicalList(BaseApplication.getToken(), status, new UsersInterface.GetPeriodicalListCallback() {
            @Override
            public void getSucceed(PeriodicalListResponse periodicalListResponse) {
                if (periodicalListResponse.getData()!=null && periodicalListResponse.getData().size()>0){
                    periodicalLiveData.setValue(periodicalListResponse.getData());
                }else {
                    periodicalLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                periodicalLiveData.setValue(null);
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                periodicalLiveData.setValue(null);
            }
        });
    }
}
