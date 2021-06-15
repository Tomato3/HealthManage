package com.example.healthmanage.ui.activity.workplan.ui;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.qualification.response.UploadResponse;
import com.example.healthmanage.ui.activity.workplan.response.InsertPlanResponse;
import com.example.healthmanage.ui.activity.workplan.response.UpdateWorkResponse;
import com.example.healthmanage.ui.activity.workplan.response.WorkPlanListResponse;

import java.io.File;
import java.util.List;

public class WorkPlanViewModel extends BaseViewModel {
    public UsersRemoteSource usersRemoteSource;
    public MutableLiveData<List<WorkPlanListResponse.DataBean>> workPlanLiveData = new MutableLiveData<>();
    public MutableLiveData<String> picUrl = new MutableLiveData<>();
    public MutableLiveData<Boolean> updateSuccess = new MutableLiveData<>();
    public MutableLiveData<Boolean> insertSuccess = new MutableLiveData<>();

    public WorkPlanViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getWorkPlanByTime(String targetTime,int doctorId){
        usersRemoteSource.getWorkPlanByTime(targetTime, doctorId, BaseApplication.getToken(), new UsersInterface.GetWorkPlanByTimeCallback() {
            @Override
            public void sendSucceed(WorkPlanListResponse workPlanListResponse) {
                if (workPlanListResponse.getData()!=null && workPlanListResponse.getData().size()>0){
                    workPlanLiveData.postValue(workPlanListResponse.getData());
                }else {
                    workPlanLiveData.postValue(null);
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

    public void updateWorkPlanById(int id,String updateTime){
        usersRemoteSource.updateWorkPlanById(id, updateTime, BaseApplication.getToken(), new UsersInterface.UpdateWorkPlanByIdCallback() {
            @Override
            public void updateSucceed(UpdateWorkResponse updateWorkResponse) {
                updateSuccess.postValue(true);
            }

            @Override
            public void updateFailed(String msg) {
                updateSuccess.postValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                updateSuccess.postValue(false);
            }
        });
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

    public void insertWorkPlan(String workText,int userId,String createTime,String targetTime){
        usersRemoteSource.insertWorkPlan(workText, userId, createTime,targetTime, BaseApplication.getToken(), new UsersInterface.InsertWorkPlanCallback() {
            @Override
            public void insertSucceed(InsertPlanResponse insertPlanResponse) {
                insertSuccess.postValue(true);
            }

            @Override
            public void insertFailed(String msg) {
                insertSuccess.postValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                insertSuccess.postValue(false);
            }
        });
    }

}
