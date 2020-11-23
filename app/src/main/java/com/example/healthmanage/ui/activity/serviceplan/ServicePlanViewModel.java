package com.example.healthmanage.ui.activity.serviceplan;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.ServicePlanResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.view.ServicePlanRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ServicePlanViewModel extends BaseViewModel {

    public MutableLiveData<List<ServicePlanRecyclerView>> servicePlanMutableLiveData =
            new MutableLiveData<>();

    private List<ServicePlanRecyclerView> servicePlanRecyclerViewList = new ArrayList<>();
    public MutableLiveData<Boolean> isSucceed = new MutableLiveData<>(false);
    private UsersRemoteSource usersRemoteSource;
    int index = 2;

    public ServicePlanViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getServicePlanList(boolean isFirst) {
        if (isFirst) {
            usersRemoteSource.getServicePlanList(BaseApplication.getToken(), 1, 3,
                    new UsersInterface.GetServicePlanListCallback() {
                        @Override
                        public void getSucceed(ServicePlanResponse servicePlanResponse) {
                            servicePlanRecyclerViewList.clear();
                            if (servicePlanResponse.getData() != null) {
                                index = 2;
                                for (int i = 0; i < servicePlanResponse.getData().getRows().size(); i++) {
                                    servicePlanRecyclerViewList.add(new ServicePlanRecyclerView(servicePlanResponse.getData().getRows().get(i).getTargetUserName(),
                                            servicePlanResponse.getData().getRows().get(i).getServiceProjectNames(),
                                            servicePlanResponse.getData().getRows().get(i).getPlanTime(),
                                            servicePlanResponse.getData().getRows().get(i).getPlanPlace(),
                                            0, servicePlanResponse.getData().getRows().get(i).getId()));
                                }
                                servicePlanMutableLiveData.setValue(servicePlanRecyclerViewList);
                                isSucceed.setValue(true);
                            }
                        }

                        @Override
                        public void getFailed(String msg) {

                        }

                        @Override
                        public void error(ExceptionHandle.ResponseException e) {

                        }
                    });
        } else {
            usersRemoteSource.getServicePlanList(BaseApplication.getToken(), index, 3,
                    new UsersInterface.GetServicePlanListCallback() {
                        @Override
                        public void getSucceed(ServicePlanResponse servicePlanResponse) {
                            if (servicePlanResponse.getData() != null) {
                                index++;
                                for (int i = 0; i < servicePlanResponse.getData().getRows().size();
                                     i++) {
                                    servicePlanRecyclerViewList.add(new ServicePlanRecyclerView(servicePlanResponse.getData().getRows().get(i).getTargetUserName(),
                                            servicePlanResponse.getData().getRows().get(i).getServiceProjectNames(),
                                            servicePlanResponse.getData().getRows().get(i).getPlanTime().substring(0, 9),
                                            servicePlanResponse.getData().getRows().get(i).getPlanPlace(),
                                            0, servicePlanResponse.getData().getRows().get(i).getId()));
                                }
                                servicePlanMutableLiveData.setValue(servicePlanRecyclerViewList);
                                isSucceed.setValue(true);
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
}
