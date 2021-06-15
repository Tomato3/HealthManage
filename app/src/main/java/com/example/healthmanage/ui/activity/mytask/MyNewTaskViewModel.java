package com.example.healthmanage.ui.activity.mytask;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.bean.network.response.TaskResponse;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.mytask.response.HealthTaskDetailResponse;
import com.example.healthmanage.ui.activity.mytask.response.TransferResponse;
import com.example.healthmanage.ui.activity.mytask.response.UpdateTaskResponse;
import com.example.healthmanage.ui.activity.team.response.TeamMemberResponse;
import com.example.healthmanage.ui.activity.temperature.response.HealthTaskResponse;
import com.example.healthmanage.widget.DropdownNewBar;

import java.util.List;

public class MyNewTaskViewModel extends BaseViewModel {
    public MutableLiveData<List<TaskResponse.DataBean.RowsBean>> myTaskMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<HealthTaskResponse.DataBean>> healthTaskMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<TeamMemberResponse.DataBean>> doctorMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> contentNumber = new MutableLiveData<>();
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<Boolean> isSucceed = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isUpdateSucceed = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSendTransferSucceed = new MutableLiveData<>();

    public MutableLiveData<Integer> status = new MutableLiveData<>();

    public MutableLiveData<String> weatherDegrees = new MutableLiveData<>("-3C");

    public MutableLiveData<String> weatherReminder =
            new MutableLiveData<>("温度过高，注意防晒！");

    public MutableLiveData<String> location =
            new MutableLiveData<>("");
    public MutableLiveData<List<DropdownNewBar>>
            dropdownBarLists = new MutableLiveData<>();

    public MutableLiveData<Boolean> todayHealthVisible = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> bodyHealthVisible = new MutableLiveData<>(false);
    /**今日空气质量是否存在数据*/
    public MutableLiveData<Boolean> todayAirVisible = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> spiritVisiable = new MutableLiveData<>(false);

    public MutableLiveData<List<HealthTaskDetailResponse.DataBean.SongARIListBean>> dataItemAir = new MutableLiveData<>();
    public MutableLiveData<HealthTaskDetailResponse.DataBean.SongEnDataBean> dataItemTodayHealth = new MutableLiveData<>();
    public MutableLiveData<HealthTaskDetailResponse.DataBean.SongHuLiYiDataBean> dataItemBodyHealth = new MutableLiveData<>();
    public MutableLiveData<List<HealthTaskDetailResponse.DataBean.SleepDataBean>> dataItemSpiritHealth = new MutableLiveData<>();

    /**页码*/
    int index = 2;

    public MyNewTaskViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getHealthTaskList(int status){
        usersRemoteSource.getHealthTaskList(status, BaseApplication.getToken(), new UsersInterface.GetHealthTaskListCallback() {
            @Override
            public void getSucceed(HealthTaskResponse healthTaskResponse) {
                if (healthTaskResponse.getData()!=null&&healthTaskResponse.getData().size()>0){
                    healthTaskMutableLiveData.setValue(healthTaskResponse.getData());
                }else {
                    healthTaskMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                healthTaskMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                healthTaskMutableLiveData.setValue(null);
            }
        });
    }

    public void getTransferHealthTaskList(int transferStatus){
        usersRemoteSource.getTransferHealthTaskList(transferStatus, BaseApplication.getToken(), new UsersInterface.GetTransferHealthTaskListCallback() {
            @Override
            public void getSucceed(HealthTaskResponse healthTaskResponse) {
                if (healthTaskResponse.getData()!=null && healthTaskResponse.getData().size()>0){
                    healthTaskMutableLiveData.setValue(healthTaskResponse.getData());
                }else {
                    healthTaskMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                healthTaskMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                healthTaskMutableLiveData.setValue(null);
            }
        });
    }

    public void loadTaskList(boolean isFirst, int state){
        if (isFirst){
            usersRemoteSource.loadMyTask(BaseApplication.getToken(), 1, 100, state, new UsersInterface.LoadMyTaskCallback() {
                @Override
                public void loadSucceed(TaskResponse taskResponse) {
                    if (taskResponse.getData() != null){
                        myTaskMutableLiveData.postValue(taskResponse.getData().getRows());
                        isSucceed.setValue(true);
//                        for (TaskResponse.DataBean.RowsBean rowsBean : taskResponse.getData().getRows()) {
//                            if (rowsBean.getStatus() == state){
//                                status.setValue(state);
//                            }
//                        }
                        status.setValue(state);
                        index = 2;
                    }else {
                        myTaskMutableLiveData.postValue(null);
                        status.setValue(state);
                    }
                }

                @Override
                public void loadFailed(String msg) {
                    myTaskMutableLiveData.postValue(null);
                    status.setValue(state);
                }

                @Override
                public void error(ExceptionHandle.ResponseException e) {
                    status.setValue(state);
                }
            });
        }else {
            usersRemoteSource.loadMyTask(BaseApplication.getToken(), index, 100, state, new UsersInterface.LoadMyTaskCallback() {
                @Override
                public void loadSucceed(TaskResponse taskResponse) {
                    if (taskResponse.getData() != null){
                        myTaskMutableLiveData.postValue(taskResponse.getData().getRows());
                        isSucceed.setValue(true);
                        index++;
                    }else {
                        myTaskMutableLiveData.postValue(null);
                    }
                }

                @Override
                public void loadFailed(String msg) {
                    status.setValue(state);
                }

                @Override
                public void error(ExceptionHandle.ResponseException e) {
                    status.setValue(state);
                }
            });
        }
    }

    public void updateTask(int id,String proposal){
        usersRemoteSource.updateTask(id, BaseApplication.getToken(), proposal, new UsersInterface.UpdateTaskCallback() {
            @Override
            public void updateSucceed(UpdateTaskResponse updateTaskResponse) {
                Log.e("succeed",updateTaskResponse.getMessage());
                isUpdateSucceed.postValue(true);
            }

            @Override
            public void updateFailed(String msg) {
                Log.e("failed",msg);
                isUpdateSucceed.postValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                Log.e("error",e.getMessage());
                isUpdateSucceed.postValue(false);
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

    public void getExceptionData(int userId){
        usersRemoteSource.getExceptionData(userId, BaseApplication.getToken(), new UsersInterface.GetExceptionDataCallback() {
            @Override
            public void getSucceed(HealthTaskDetailResponse healthTaskDetailResponse) {
                if (healthTaskDetailResponse.getData()!=null){
                    if (healthTaskDetailResponse.getData().getSongARIList()!=null){
                        dataItemAir.postValue(healthTaskDetailResponse.getData().getSongARIList());
                        todayAirVisible.postValue(true);
                    }else {
                        dataItemAir.postValue(null);
                        todayAirVisible.postValue(false);
                    }
                    if (healthTaskDetailResponse.getData().getSongEnData()!=null){
                        dataItemTodayHealth.postValue(healthTaskDetailResponse.getData().getSongEnData());
                        todayHealthVisible.setValue(true);
                    }else {
                        dataItemTodayHealth.postValue(null);
                        todayHealthVisible.setValue(false);
                    }
                    if (healthTaskDetailResponse.getData().getSongHuLiYiData()!=null){
                        dataItemBodyHealth.postValue(healthTaskDetailResponse.getData().getSongHuLiYiData());
                        bodyHealthVisible.setValue(true);
                    }else {
                        dataItemBodyHealth.postValue(null);
                        bodyHealthVisible.setValue(false);
                    }
                    if (healthTaskDetailResponse.getData().getSleepData()!=null){
                        dataItemSpiritHealth.postValue(healthTaskDetailResponse.getData().getSleepData());
                        spiritVisiable.setValue(true);
                    }else {
                        dataItemSpiritHealth.postValue(null);
                        spiritVisiable.setValue(false);
                    }
                }else {
                    todayAirVisible.postValue(false);
                    todayHealthVisible.postValue(false);
                    bodyHealthVisible.setValue(false);
                    spiritVisiable.setValue(false);
                }
            }

            @Override
            public void getFailed(String msg) {
                todayAirVisible.postValue(false);
                todayHealthVisible.postValue(false);
                bodyHealthVisible.setValue(false);
                spiritVisiable.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    public void sendTransferTask(int id,int systemUserId){
        usersRemoteSource.sendTransferTask(id, systemUserId, BaseApplication.getToken(), new UsersInterface.SendTransferTaskCallback() {
            @Override
            public void transferSucceed(TransferResponse transferResponse) {
                Log.e("succeed",transferResponse.getMessage());
                isSendTransferSucceed.setValue(true);
            }

            @Override
            public void transferFailed(String msg) {
                Log.e("succeed",msg);
                isSendTransferSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                Log.e("succeed",e.getMessage());
                isSendTransferSucceed.setValue(false);
            }
        });
    }

}
