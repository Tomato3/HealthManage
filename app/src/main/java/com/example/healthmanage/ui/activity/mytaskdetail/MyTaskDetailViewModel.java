package com.example.healthmanage.ui.activity.mytaskdetail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.GeneralResponse;
import com.example.healthmanage.bean.TaskDetailResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.view.MyTaskDetailRecyclerView;

import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MyTaskDetailViewModel extends BaseViewModel {

    public MutableLiveData<String> title = new MutableLiveData<>("");
    public MutableLiveData<String> content = new MutableLiveData<>("");
    public MutableLiveData<Boolean> refresh = new MutableLiveData<>(false);

    public MutableLiveData<List<MyTaskDetailRecyclerView>> myTaskDetailRecyclerViewList =
            new MutableLiveData<>();

    private MyTaskDetailRecyclerView myTaskDetailRecyclerViews;

    UsersRemoteSource usersRemoteSource;

    public MyTaskDetailViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }


    public void loadTaskDetail(long taskId) {
        usersRemoteSource.loadMyTaskDetail(taskId, new UsersInterface.LoadMyTaskDetailCallback() {
            @Override
            public void loadSucceed(TaskDetailResponse taskDetailResponse) {
                title.setValue("任务标题：" + "\n" + taskDetailResponse.getData().getTitle());
                content.setValue("任务描述：" + "\n" + taskDetailResponse.getData().getContent());
                if (taskDetailResponse.getData().getContent() != null) {
//                    myTaskDetailRecyclerViews =
//                            new MyTaskDetailRecyclerView(taskDetailResponse.getData().get);
                }
            }

            @Override
            public void loadFailed(String msg) {
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
            }
        });
    }

    public void editTaskByManager(long taskId, String title, String content) {
        usersRemoteSource.updateMyTaskDetail(taskId, title, content,
                new UsersInterface.UpdateMyTaskDetailCallback() {
                    @Override
                    public void updateSucceed(GeneralResponse generalResponse) {
                        Log.d(HTAG, "updateSucceed==========>: ");
                        refresh.setValue(true);
                        getUiChangeEvent().getToastTxt().setValue("修改成功");
                        getUiChangeEvent().getToastType().setValue(0);
                    }

                    @Override
                    public void updateFailed(String msg) {
                        Log.d(HTAG, "updateFailed==========>: ");
                    }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {
                        Log.d(HTAG, "error==========>: ");
                    }
                });
    }

    public void editTaskByOthers(String roleId, String content) {
        usersRemoteSource.updateMyTaskDetail(Long.parseLong(roleId), content, new UsersInterface.UpdateMyTaskDetailCallback() {
            @Override
            public void updateSucceed(GeneralResponse generalResponse) {
                Log.d(HTAG, "updateSucceed==========>: ");
            }

            @Override
            public void updateFailed(String msg) {
                Log.d(HTAG, "updateFailed==========>: ");
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                Log.d(HTAG, "error==========>: ");
            }
        });
    }
}
