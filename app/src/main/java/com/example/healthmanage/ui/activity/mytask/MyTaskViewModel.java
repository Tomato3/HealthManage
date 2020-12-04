package com.example.healthmanage.ui.activity.mytask;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.TaskResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.bean.recyclerview.MyTaskRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyTaskViewModel extends BaseViewModel {

    private List<MyTaskRecyclerView> myTaskRecyclerViewList = new ArrayList<>();
    private UsersRemoteSource usersRemoteSource;

    public MutableLiveData<List<MyTaskRecyclerView>> myTaskMutableLiveData =
            new MutableLiveData<>();

    public MutableLiveData<Boolean> isSucceed = new MutableLiveData<>(false);

    public MutableLiveData<Integer> status = new MutableLiveData<>();

    int index = 2;

    public MyTaskViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void loadMyTask(boolean isFirst, int state) {
        if (isFirst) {
            usersRemoteSource.loadMyTask(BaseApplication.getUserInfoBean().getSysId(), 1, 3,
                    new UsersInterface.LoadMyTaskCallback() {
                        @Override
                        public void loadSucceed(TaskResponse taskResponse) {
                            if (taskResponse.getData().getRows().size() != 0) {
                                myTaskRecyclerViewList.clear();
                                for (int i = 0; i < taskResponse.getData().getRows().size(); i++) {
                                    if (taskResponse.getData().getRows().get(i).getStatus() == state) {
                                        myTaskRecyclerViewList.add(new MyTaskRecyclerView(taskResponse.getData().getRows().get(i).getTitle(),
                                                taskResponse.getData().getRows().get(i).getContent(),
                                                taskResponse.getData().getRows().get(i).getCreateTime(),
                                                taskResponse.getData().getRows().get(i).getStatus(),
                                                taskResponse.getData().getRows().get(i).getId()));
                                        myTaskMutableLiveData.setValue(myTaskRecyclerViewList);
                                        isSucceed.setValue(true);
                                        status.setValue(state);
                                        index = 2;
                                    }
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
        } else {
            usersRemoteSource.loadMyTask(BaseApplication.getUserInfoBean().getSysId(), index, 3,
                    new UsersInterface.LoadMyTaskCallback() {
                        @Override
                        public void loadSucceed(TaskResponse taskResponse) {
                            if (taskResponse.getData().getRows().size() != 0) {
                                for (int i = 0; i < taskResponse.getData().getRows().size(); i++) {
                                    myTaskRecyclerViewList.add(new MyTaskRecyclerView(taskResponse.getData().getRows().get(i).getTitle(),
                                            taskResponse.getData().getRows().get(i).getContent(),
                                            taskResponse.getData().getRows().get(i).getCreateTime(),
                                            taskResponse.getData().getRows().get(i).getStatus(),
                                            taskResponse.getData().getRows().get(i).getId()));
                                    myTaskMutableLiveData.setValue(myTaskRecyclerViewList);
                                    index++;
                                    isSucceed.setValue(true);
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
}
