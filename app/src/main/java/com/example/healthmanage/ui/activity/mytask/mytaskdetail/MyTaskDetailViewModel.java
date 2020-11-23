package com.example.healthmanage.ui.activity.mytask.mytaskdetail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.GeneralResponse;
import com.example.healthmanage.bean.TaskDetailResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.view.MyTaskDetailRecyclerView;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;
import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MyTaskDetailViewModel extends BaseViewModel {

    public MutableLiveData<String> title = new MutableLiveData<>("");
    public MutableLiveData<String> content = new MutableLiveData<>("");
    public MutableLiveData<Boolean> refresh = new MutableLiveData<>(false);

    public MutableLiveData<List<MyTaskDetailRecyclerView>> myTaskDetailRecyclerViewList =
            new MutableLiveData<>();

    private List<MyTaskDetailRecyclerView> myTaskDetailRecyclerViews;

    UsersRemoteSource usersRemoteSource;

    public MyTaskDetailViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }


    public void loadTaskDetail(long taskId) {
        usersRemoteSource.loadMyTaskDetail(taskId, new UsersInterface.LoadMyTaskDetailCallback() {
            @Override
            public void loadSucceed(TaskDetailResponse taskDetailResponse) {
                title.setValue(taskDetailResponse.getData().getTitle());
                myTaskDetailRecyclerViews = new ArrayList<>();
                if (taskDetailResponse.getData().getContent() != null) {
                    myTaskDetailRecyclerViews.add(new MyTaskDetailRecyclerView(taskDetailResponse.getData().getMangerName(),
                            taskDetailResponse.getData().getContent(),
                            taskDetailResponse.getData().getCreateTime()));


                    myTaskDetailRecyclerViews.add(new MyTaskDetailRecyclerView(taskDetailResponse.getData().getDoctorName(),
                            taskDetailResponse.getData().getDoctorReply(),
                            taskDetailResponse.getData().getDoctorCreateTime()));
                }
                myTaskDetailRecyclerViewList.setValue(myTaskDetailRecyclerViews);
            }

            @Override
            public void loadFailed(String msg) {
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                Log.d(HTAG, "error==========>: " + e.getMessage());
            }
        });
    }

    public void editTaskByManager(long taskId, String content) {
        usersRemoteSource.updateMyTaskDetailByManager(taskId, content,
                new UsersInterface.UpdateMyTaskDetailCallback() {
                    @Override
                    public void updateSucceed(GeneralResponse generalResponse) {
                        Log.d(HTAG, "updateSucceed==========>: ");
                        refresh.setValue(true);
                        LiveEventBus.get("close").post(false);
                        showToast("修改成功", 0);
                    }

                    @Override
                    public void updateFailed(String msg) {
                        showToast(msg, 1);
                    }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {
                        LiveEventBus.get("close").post(false);
                        showToast(e.getMessage(), 1);
                    }
                });
    }

    public void editTaskByOthers(long taskId, String content) {
        usersRemoteSource.updateMyTaskDetailByDoctor(taskId, content,
                new UsersInterface.UpdateMyTaskDetailCallback() {
                    @Override
                    public void updateSucceed(GeneralResponse generalResponse) {
                        showToast(generalResponse.getMessage(), 0);
                        LiveEventBus.get("close").post(true);
                    }

                    @Override
                    public void updateFailed(String msg) {
                        showToast(msg, 1);
                    }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {
                    }
                });
    }
}
