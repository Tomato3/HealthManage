package com.example.healthmanage.ui.activity.selectTaskReceiver;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.DoctorListResponse;
import com.example.healthmanage.bean.GeneralResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.view.TaskReceiverRecyclerView;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;
import java.util.List;

public class SelectTaskReceiverViewModel extends BaseViewModel {
    public MutableLiveData<String> taskReceiverName = new MutableLiveData<>("");
    UsersRemoteSource usersRemoteSource;
    public MutableLiveData<List<TaskReceiverRecyclerView>> taskReceiverMutableLiveData =
            new MutableLiveData<>();

    private List<TaskReceiverRecyclerView> taskReceiverRecyclerViewList;

    private String type;

    public SelectTaskReceiverViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getTaskReceiverList() {
        usersRemoteSource.getDoctorList(new UsersInterface.GetDoctorListCallback() {
            @Override
            public void getSucceed(DoctorListResponse doctorListResponse) {
                taskReceiverRecyclerViewList = new ArrayList<>();
                if (doctorListResponse.getData() != null) {
                    for (int i = 0; i < doctorListResponse.getData().size(); i++) {
                        switch (doctorListResponse.getData().get(i).getType()) {
                            case 0:
                                type = "中医";
                                break;
                            case 1:
                                type = "西医";
                                break;
                        }
                        taskReceiverRecyclerViewList.add(new TaskReceiverRecyclerView(doctorListResponse.getData().get(i).getAvatar(),
                                doctorListResponse.getData().get(i).getName(),
                                type,
                                doctorListResponse.getData().get(i).getProfessionalTitle(),
                                doctorListResponse.getData().get(i).getSittingPlace(),
                                doctorListResponse.getData().get(i).getId()));
                    }
                    taskReceiverMutableLiveData.setValue(taskReceiverRecyclerViewList);
                }
            }

            @Override
            public void getFailed(String msg) {
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
            }
        });
    }

    public void sendMyTask(long taskId, long receiverId) {
        usersRemoteSource.sendMyTask(taskId, receiverId, new UsersInterface.SendMyTaskCallback() {
            @Override
            public void sendSucceed(GeneralResponse generalResponse) {
                LiveEventBus.get("close").post(false);
                showToast("发送成功", 0);
            }

            @Override
            public void sendFailed(String msg) {
                LiveEventBus.get("close").post(false);
                showToast(msg, 1);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                LiveEventBus.get("close").post(false);
                showToast(e.getMessage(), 1);
            }
        });
    }
}
