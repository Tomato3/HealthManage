package com.example.healthmanage.ui.activity.mytask;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.TaskResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.view.MyTaskRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MyTaskViewModel extends BaseViewModel {

    private List<MyTaskRecyclerView> myTaskRecyclerViewList;
    private UsersRemoteSource usersRemoteSource;

    public MutableLiveData<List<MyTaskRecyclerView>> myTaskMutableLiveData =
            new MutableLiveData<>();

    public MyTaskViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void loadMyTask() {
        usersRemoteSource.loadMyTask(BaseApplication.getUserInfoBean().getSysId(),
                new UsersInterface.LoadMyTaskCallback() {
                    @Override
                    public void loadSucceed(TaskResponse taskResponse) {
                        myTaskRecyclerViewList = new ArrayList<>();
                        if (taskResponse.getData().size() != 0) {
                            for (int i = 0; i < taskResponse.getData().size(); i++) {
                                myTaskRecyclerViewList.add(new MyTaskRecyclerView(taskResponse.getData().get(i).getTitle(),
                                        taskResponse.getData().get(i).getContent(),
                                        taskResponse.getData().get(i).getCreateTime(),
                                        taskResponse.getData().get(i).getStatus(),
                                        taskResponse.getData().get(i).getId()));
                                myTaskMutableLiveData.setValue(myTaskRecyclerViewList);
                            }
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
}
