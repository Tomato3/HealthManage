package com.example.healthmanage.ui.activity.serviceplan.serviceplandetail;


import android.util.Log;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.GeneralResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.bean.recyclerview.ServicePlanRecyclerView;
import com.jeremyliao.liveeventbus.LiveEventBus;

import static com.example.healthmanage.utils.Constants.HTAG;

public class ServicePlanDetailViewModel extends BaseViewModel {

    public ServicePlanRecyclerView getServicePlanRecyclerView() {
        return servicePlanRecyclerView;
    }

    public void setServicePlanRecyclerView(ServicePlanRecyclerView servicePlanRecyclerView) {
        this.servicePlanRecyclerView = servicePlanRecyclerView;
    }

    public ServicePlanRecyclerView servicePlanRecyclerView;

    UsersRemoteSource usersRemoteSource;

    public ServicePlanDetailViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void uploadServiceResult(long servicePlanId, String serviceTime, String serviceLocation,
                                    String serviceResult) {

        usersRemoteSource.uploadServiceResult(BaseApplication.getToken(), servicePlanId, serviceTime,
                serviceLocation, serviceResult, new UsersInterface.UploadServiceResultCallback() {
                    @Override
                    public void uploadSucceed(GeneralResponse generalResponse) {
                        showToast(generalResponse.getMessage(), 0);
                        LiveEventBus.get("close").post(true);

                    }

                    @Override
                    public void uploadFailed(String msg) {
                        showToast(msg, 1);
                    }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {
                        Log.d(HTAG, "error==========>: " + e.getMessage());
                    }
                });
    }
}
