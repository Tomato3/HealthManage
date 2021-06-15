package com.example.healthmanage.ui.activity.mytask.mytaskdetail.selectTaskReceiver;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.DoctorListResponse;
import com.example.healthmanage.bean.network.response.GeneralResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.bean.recyclerview.TaskReceiverRecyclerView;
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
//        usersRemoteSource.getDoctorList(new UsersInterface.GetDoctorListCallback() {
//            @Override
//            public void getSucceed(DoctorListResponse doctorListResponse) {
//                taskReceiverRecyclerViewList = new ArrayList<>();
//                if (doctorListResponse.getData() != null) {
//                    for (int i = 0; i < doctorListResponse.getData().size(); i++) {
//                        switch (doctorListResponse.getData().get(i).getType()) {
//                            case 0:
//                                type = "中医";
//                                break;
//                            case 1:
//                                type = "西医";
//                                break;
//                        }
//                        taskReceiverRecyclerViewList.add(new TaskReceiverRecyclerView(doctorListResponse.getData().get(i).getAvatar(),
//                                doctorListResponse.getData().get(i).getName(),
//                                type,
//                                doctorListResponse.getData().get(i).getProfessionalTitle(),
//                                doctorListResponse.getData().get(i).getSittingPlace(),
//                                doctorListResponse.getData().get(i).getId()));
//                    }
//                    taskReceiverMutableLiveData.setValue(taskReceiverRecyclerViewList);
//                }
//            }
//
//            @Override
//            public void getFailed(String msg) {
//            }
//
//            @Override
//            public void error(ExceptionHandle.ResponseException e) {
//            }
//        });
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

    public void searchDoctor(String doctorName) {
        if (!TextUtils.isEmpty(doctorName)) {
//            usersRemoteSource.searchDoctor(doctorName, new UsersInterface.SearchDoctorCallback() {
//                @Override
//                public void searchSucceed(DoctorListResponse doctorListResponse) {
//                    taskReceiverRecyclerViewList = new ArrayList<>();
//                    if (doctorListResponse.getData() != null) {
//                        LiveEventBus.get("CloseKeyboard").post(true);
//                        for (int i = 0; i < doctorListResponse.getData().size(); i++) {
//                            switch (doctorListResponse.getData().get(i).getType()) {
//                                case 0:
//                                    type = "中医";
//                                    break;
//                                case 1:
//                                    type = "西医";
//                                    break;
//                            }
//                            taskReceiverRecyclerViewList.add(new TaskReceiverRecyclerView(doctorListResponse.getData().get(i).getAvatar(),
//                                    doctorListResponse.getData().get(i).getName(),
//                                    type,
//                                    doctorListResponse.getData().get(i).getProfessionalTitle(),
//                                    doctorListResponse.getData().get(i).getSittingPlace(),
//                                    doctorListResponse.getData().get(i).getId()));
//                        }
//                        taskReceiverMutableLiveData.setValue(taskReceiverRecyclerViewList);
//                    }
//                }
//
//                @Override
//                public void searchFailed(String msg) {
//                    showToast(msg, 1);
//                }
//
//                @Override
//                public void error(ExceptionHandle.ResponseException e) {
//                    showToast(e.getMessage(), 1);
//                }
//            });
        } else {

        }
    }

}
