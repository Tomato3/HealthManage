package com.example.healthmanage.ui.activity.doctorhall;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.DoctorListResponse;
import com.example.healthmanage.bean.network.response.MyDoctorResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.bean.recyclerview.TaskReceiverRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class DoctorHallViewModel extends BaseViewModel {
    public MutableLiveData<List<TaskReceiverRecyclerView>> taskReceiverMutableLiveData =
            new MutableLiveData<>();

    private List<TaskReceiverRecyclerView> taskReceiverRecyclerViewList;

    private UsersRemoteSource usersRemoteSource = new UsersRemoteSource();

    private String type;

    public void getMyDoctor() {
        usersRemoteSource.getMyDoctor(BaseApplication.getToken(), 1, 10,
                new UsersInterface.GetMyDoctorCallback() {
                    @Override
                    public void getSucceed(MyDoctorResponse myDoctorResponse) {
                        taskReceiverRecyclerViewList = new ArrayList<>();
                        if (myDoctorResponse.getData() != null) {
                            for (int i = 0; i < myDoctorResponse.getData().getTotal(); i++) {
                                switch (myDoctorResponse.getData().getRows().get(i).getType()) {
                                    case 0:
                                        type = "中医";
                                        break;
                                    case 1:
                                        type = "西医";
                                        break;
                                }
                                taskReceiverRecyclerViewList.add(new TaskReceiverRecyclerView(myDoctorResponse.getData().getRows().get(i).getAvatar(),
                                        myDoctorResponse.getData().getRows().get(i).getName(),
                                        type,
                                        myDoctorResponse.getData().getRows().get(i).getProfessionalTitle(),
                                        myDoctorResponse.getData().getRows().get(i).getSittingPlace(),
                                        myDoctorResponse.getData().getRows().get(i).getId()));
                            }
                            taskReceiverMutableLiveData.setValue(taskReceiverRecyclerViewList);
                        }
                    }

                    @Override
                    public void getFailed(String msg) {
                    }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {
                        Log.d(HTAG, "error==========>: " + e.getMessage());
                    }
                });
    }

    public void getAllDoctor() {
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

}
