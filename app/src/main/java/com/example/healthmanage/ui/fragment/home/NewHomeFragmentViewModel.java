package com.example.healthmanage.ui.fragment.home;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.bean.network.response.VipUserBean;
import com.example.healthmanage.data.network.ApiWrapper;
import com.example.healthmanage.data.network.MyObserver;
import com.example.healthmanage.data.network.RxHelper;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.qualification.response.DoctorInfoResponse;
import com.example.healthmanage.ui.activity.team.response.DoctorTeamResponse;
import com.example.healthmanage.ui.activity.temperature.response.HealthTaskResponse;
import com.example.healthmanage.ui.activity.temperature.response.TemperatureResponse;
import com.example.healthmanage.ui.activity.vipmanager.response.MemberTeamListResponse;
import com.example.healthmanage.ui.activity.workplan.response.WorkPlanListResponse;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class NewHomeFragmentViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<String> userId = new MutableLiveData<>();
    public MutableLiveData<String> temperatureSize = new MutableLiveData<>();
    public MutableLiveData<String> healthTaskSize = new MutableLiveData<>();
    public MutableLiveData<String> workPlanSize = new MutableLiveData<>();
    public MutableLiveData<List<MemberTeamListResponse.DataBean>> mListMutableLiveData = new MutableLiveData<>();

    public NewHomeFragmentViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getHealthConsultStatus(int status){
        usersRemoteSource.getHealthConsultStatus(status, BaseApplication.getToken(), new UsersInterface.GetHealthConsultCallback() {
            @Override
            public void getSucceed(TemperatureResponse temperatureResponse) {
                if (temperatureResponse.getData()!=null && temperatureResponse.getData().size()>0){
                    temperatureSize.setValue(String.valueOf(temperatureResponse.getData().size()));
                }else {
                    temperatureSize.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                temperatureSize.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                temperatureSize.setValue(null);
            }
        });
    }

    public void getHealthTaskList(int status){
        usersRemoteSource.getHealthTaskList(status, BaseApplication.getToken(), new UsersInterface.GetHealthTaskListCallback() {
            @Override
            public void getSucceed(HealthTaskResponse healthTaskResponse) {
                if (healthTaskResponse.getData()!=null&&healthTaskResponse.getData().size()>0){
                    healthTaskSize.setValue(String.valueOf(healthTaskResponse.getData().size()));
                }else {
                    healthTaskSize.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                healthTaskSize.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                healthTaskSize.setValue(null);
            }
        });
    }

    public void getWorkPlanByTime(String targetTime,int doctorId){
        usersRemoteSource.getWorkPlanByTime(targetTime, doctorId, BaseApplication.getToken(), new UsersInterface.GetWorkPlanByTimeCallback() {
            @Override
            public void sendSucceed(WorkPlanListResponse workPlanListResponse) {
                if (workPlanListResponse.getData()!=null && workPlanListResponse.getData().size()>0){
                    workPlanSize.postValue(String.valueOf(workPlanListResponse.getData().size()));
                }else {
                    workPlanSize.postValue(null);
                }
            }

            @Override
            public void sendFailed(String msg) {
                workPlanSize.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                workPlanSize.setValue(null);
            }
        });
    }

    public void getDoctorTeam(){
        usersRemoteSource.getDoctorTeam(BaseApplication.getToken(), new UsersInterface.GetDoctorTeamCallback() {
            @Override
            public void getSucceed(DoctorTeamResponse doctorTeamResponse) {
                if (doctorTeamResponse.getData()!=null){
                    userId.setValue(String.valueOf(doctorTeamResponse.getData().getSystemUserId()));
                }else {
                    userId.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                userId.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                userId.setValue(null);
            }
        });
    }


    /**
     * 0=?????? 1=??????
     * @param ranks
     * @param status
     */
    public void getMemberTeamList(String ranks,int status){
        usersRemoteSource.getMemberTeamList(BaseApplication.getToken(), ranks,status, new UsersInterface.GetMemberTeamListCallback() {
            @Override
            public void getSucceed(MemberTeamListResponse memberTeamListResponse) {
                if (memberTeamListResponse.getData()!=null && memberTeamListResponse.getData().size()>0){
                    mListMutableLiveData.setValue(memberTeamListResponse.getData());
                }else {
                    mListMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                mListMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mListMutableLiveData.setValue(null);
            }
        });
    }

}
