package com.example.healthmanage.ui.activity.academicJournals.ui;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.academicJournals.bean.AddPeriodicalBean;
import com.example.healthmanage.ui.activity.academicJournals.bean.EditPeriodicalBean;
import com.example.healthmanage.ui.activity.academicJournals.response.AddOrEditSucceedResponse;
import com.example.healthmanage.ui.activity.academicJournals.response.PeriodicalInfoResponse;
import com.example.healthmanage.ui.activity.academicJournals.response.PeriodicalListResponse;
import com.example.healthmanage.ui.activity.qualification.response.UploadResponse;

import java.io.File;
import java.util.List;

public class AcademicJournalsViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;
    //投稿期刊
    public MutableLiveData<String> periodical = new MutableLiveData<>();
    //个人简介
    public MutableLiveData<String> personalInfo = new MutableLiveData<>();
    //标题
    public MutableLiveData<String> journalsTitle = new MutableLiveData<>();
    //投稿栏目
    public MutableLiveData<String> contributionColumn = new MutableLiveData<>();

    public MutableLiveData<String> picUrl = new MutableLiveData<>();

    public MutableLiveData<List<PeriodicalListResponse.DataBean>> periodicalLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> isAddSucceed = new MutableLiveData<>();
    public MutableLiveData<Boolean> isAddDraftSucceed = new MutableLiveData<>();
    public MutableLiveData<Boolean> isEditSucceed = new MutableLiveData<>();
    public MutableLiveData<Boolean> isEditDraftSucceed = new MutableLiveData<>();
    public MutableLiveData<PeriodicalInfoResponse.DataBean> infoLiveData = new MutableLiveData<>();

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

    public void addDraftPeriodical(AddPeriodicalBean addPeriodicalBean){
        usersRemoteSource.addPeriodical(addPeriodicalBean, new UsersInterface.AddPeriodicalCallback() {
            @Override
            public void addSucceed(AddOrEditSucceedResponse addOrEditSucceedResponse) {
                isAddDraftSucceed.setValue(true);
            }

            @Override
            public void addFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                isAddDraftSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isAddDraftSucceed.setValue(false);
            }
        });
    }

    public void addPeriodical(AddPeriodicalBean addPeriodicalBean){
        usersRemoteSource.addPeriodical(addPeriodicalBean, new UsersInterface.AddPeriodicalCallback() {
            @Override
            public void addSucceed(AddOrEditSucceedResponse addOrEditSucceedResponse) {
                isAddSucceed.setValue(true);
            }

            @Override
            public void addFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                isAddSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isAddSucceed.setValue(false);
            }
        });
    }

    public void editPeriodical(EditPeriodicalBean editPeriodicalBean){
        usersRemoteSource.editPeriodical(editPeriodicalBean, new UsersInterface.EditPeriodicalCallback() {
            @Override
            public void editSucceed(AddOrEditSucceedResponse addOrEditSucceedResponse) {
                isEditSucceed.setValue(true);
            }

            @Override
            public void editFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                isEditSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isEditSucceed.setValue(false);
            }
        });
    }

    public void editDraftPeriodical(EditPeriodicalBean editPeriodicalBean){
        usersRemoteSource.editPeriodical(editPeriodicalBean, new UsersInterface.EditPeriodicalCallback() {
            @Override
            public void editSucceed(AddOrEditSucceedResponse addOrEditSucceedResponse) {
                isEditDraftSucceed.setValue(true);
            }

            @Override
            public void editFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                isEditDraftSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isEditDraftSucceed.setValue(false);
            }
        });
    }

    public void getPeriodical(int id){
        usersRemoteSource.getPeriodical(BaseApplication.getToken(), id, new UsersInterface.GetPeriodicalCallback() {
            @Override
            public void getSucceed(PeriodicalInfoResponse periodicalInfoResponse) {
                if (periodicalInfoResponse.getData()!=null){
                    infoLiveData.setValue(periodicalInfoResponse.getData());
                }else {
                    infoLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                infoLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                infoLiveData.setValue(null);
            }
        });
    }
}
