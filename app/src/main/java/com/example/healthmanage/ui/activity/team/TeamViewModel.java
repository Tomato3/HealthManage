package com.example.healthmanage.ui.activity.team;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.bean.network.response.DoctorListResponse;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.notice.response.TeamApplyResponse;
import com.example.healthmanage.ui.activity.qualification.response.CertificateResponse;
import com.example.healthmanage.ui.activity.team.bean.BusinessDealBean;
import com.example.healthmanage.ui.activity.team.response.BusinessDealListResponse;
import com.example.healthmanage.ui.activity.team.response.BusinessDetailResponse;
import com.example.healthmanage.ui.activity.team.response.DoctorTeamResponse;
import com.example.healthmanage.ui.activity.team.response.EditResponse;
import com.example.healthmanage.ui.activity.team.response.QuitTeamResponse;
import com.example.healthmanage.ui.activity.team.response.SearchTeamResponse;
import com.example.healthmanage.ui.activity.team.response.SendResponse;
import com.example.healthmanage.ui.activity.team.response.SignTeamResponse;
import com.example.healthmanage.ui.activity.team.response.TeamMemberResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TeamViewModel extends BaseViewModel {
    private UsersRemoteSource usersRemoteSource;
    public MutableLiveData<String> healthName = new MutableLiveData<>();
    public MutableLiveData<String> healthRank = new MutableLiveData<>();
    public MutableLiveData<Boolean> isPostSignSucceed = new MutableLiveData<>();
    public MutableLiveData<String> phone = new MutableLiveData<>();
    public MutableLiveData<SearchTeamResponse.DataBean> searchTeamResponseMutableLiveData = new MutableLiveData<>();
    //健康管理师搜索成员
    public MutableLiveData<SearchTeamResponse.DataBean> searchMemberResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<TeamApplyResponse.DataBean>> invitationLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSignSucceed = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRefuseSucceed = new MutableLiveData<>();
    public MutableLiveData<DoctorTeamResponse> doctorTeamResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<TeamMemberResponse.DataBean>> teamMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isQuitSucceed = new MutableLiveData<>();
    public MutableLiveData<List<BusinessDealListResponse.DataBean>> businessDealMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<BusinessDetailResponse.DataBean> businessInfoLiveData = new MutableLiveData<>();
    public MutableLiveData<List<String>> patientPhotos = new MutableLiveData<>();
    public MutableLiveData<Boolean> editSucceed = new MutableLiveData<>();

    public TeamViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }


    public void teamInvitationList(String phone){
        usersRemoteSource.teamInvitationList(phone, BaseApplication.getToken(), new UsersInterface.TeamInvitationListCallback() {
            @Override
            public void getSucceed(SearchTeamResponse searchTeamResponse) {
                if (searchTeamResponse.getData()!=null){
                    searchTeamResponseMutableLiveData.setValue(searchTeamResponse.getData());
                }else {
                    searchTeamResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                searchTeamResponseMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                searchTeamResponseMutableLiveData.setValue(null);
            }
        });
    }

    public void sendApply(int systemUserId){
        usersRemoteSource.sendApply(systemUserId, BaseApplication.getToken(), new UsersInterface.SendApplyCallback() {
            @Override
            public void sendSucceed(SendResponse sendResponse) {
                isPostSignSucceed.setValue(true);
            }

            @Override
            public void sendFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                isPostSignSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isPostSignSucceed.setValue(false);
            }
        });
    }

    public void getDoctorTeamApplyList(int status){
        usersRemoteSource.getDoctorTeamApplyList(BaseApplication.getToken(), status,new UsersInterface.DoctorTeamApplyListCallback() {
            @Override
            public void getSucceed(TeamApplyResponse teamApplyResponse) {
                if (teamApplyResponse.getData()!=null&&teamApplyResponse.getData().size()>0){
                    invitationLiveData.setValue(teamApplyResponse.getData());
                }else {
                    invitationLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                invitationLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                invitationLiveData.setValue(null);
            }
        });
    }

    public void signTeam(int id,int status){
        usersRemoteSource.signOrNot(id, status, BaseApplication.getToken(), new UsersInterface.SignOrNotCallback() {
            @Override
            public void getSucceed(SignTeamResponse signTeamResponse) {
                isSignSucceed.setValue(true);
            }

            @Override
            public void getFailed(String msg) {
                isSignSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isSignSucceed.setValue(false);
            }
        });
    }

    public void refuseSignTeam(int id,int status){
        usersRemoteSource.signOrNot(id, status, BaseApplication.getToken(), new UsersInterface.SignOrNotCallback() {
            @Override
            public void getSucceed(SignTeamResponse signTeamResponse) {
                isRefuseSucceed.setValue(true);
            }

            @Override
            public void getFailed(String msg) {
                isRefuseSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isRefuseSucceed.setValue(false);
            }
        });
    }

    public void getDoctorByPhone(String phone){
        usersRemoteSource.getDoctorByPhone(phone, BaseApplication.getToken(), new UsersInterface.GetDoctorByPhoneCallback() {
            @Override
            public void getSucceed(SearchTeamResponse searchTeamResponse) {
                if (searchTeamResponse.getData()!=null){
                    searchMemberResponseMutableLiveData.setValue(searchTeamResponse.getData());
                }else {
                    searchMemberResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                searchMemberResponseMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                searchMemberResponseMutableLiveData.setValue(null);
            }
        });
    }

    public void getDoctorTeam(){
        usersRemoteSource.getDoctorTeam(BaseApplication.getToken(), new UsersInterface.GetDoctorTeamCallback() {
            @Override
            public void getSucceed(DoctorTeamResponse doctorTeamResponse) {
                if (doctorTeamResponse.getData()!=null){
                    doctorTeamResponseMutableLiveData.setValue(doctorTeamResponse);
                }else {
                    doctorTeamResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                doctorTeamResponseMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                doctorTeamResponseMutableLiveData.setValue(null);
            }
        });
    }

    public void getDoctorTeamList(int roleId){
        usersRemoteSource.getDoctorTeamList(BaseApplication.getToken(), roleId, new UsersInterface.GetDoctorTeamListCallback() {
            @Override
            public void getSucceed(TeamMemberResponse teamMemberResponse) {
                if (teamMemberResponse.getData()!=null && teamMemberResponse.getData().size()>0){
                    teamMutableLiveData.setValue(teamMemberResponse.getData());
                }else {
                    teamMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                teamMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                teamMutableLiveData.setValue(null);
            }
        });
    }

    public void quitTeam(){
        usersRemoteSource.quitTeam(BaseApplication.getToken(), new UsersInterface.QuitTeamCallback() {
            @Override
            public void quitSucceed(QuitTeamResponse quitTeamResponse) {
                isQuitSucceed.setValue(true);
                getUiChangeEvent().getToastTxt().setValue("退出团队成功");
            }

            @Override
            public void quitFailed(String msg) {
                isQuitSucceed.setValue(false);
                getUiChangeEvent().getToastTxt().setValue("服务器异常");
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isQuitSucceed.setValue(false);
                getUiChangeEvent().getToastTxt().setValue("服务器异常");
            }
        });
    }

    public void getBusineServiceList(int status){
        usersRemoteSource.getBusineServiceList(BaseApplication.getToken(), status, new UsersInterface.GetBusineServiceListCallback() {
            @Override
            public void getSucceed(BusinessDealListResponse businessDealListResponse) {
                if (businessDealListResponse.getData()!=null && businessDealListResponse.getData().size()>0){
                    businessDealMutableLiveData.setValue(businessDealListResponse.getData());
                }else {
                    businessDealMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                businessDealMutableLiveData.setValue(null);
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                businessDealMutableLiveData.setValue(null);
            }
        });
    }

    public void getBusineServiceInfo(int id){
        usersRemoteSource.getBusineServiceInfo(BaseApplication.getToken(), id, new UsersInterface.GetBusineServiceInfoCallback() {
            @Override
            public void getSucceed(BusinessDetailResponse businessDetailResponse) {
                if (businessDetailResponse.getData()!=null){
                    businessInfoLiveData.setValue(businessDetailResponse.getData());
                }else {
                    businessInfoLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                businessInfoLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                businessInfoLiveData.setValue(null);
            }
        });
    }

    public void uploadPhotoForPatient(List<File> files) {
        usersRemoteSource.getUploadCertificate(files, new UsersInterface.UpCertificateCallback() {
            @Override
            public void sendSucceed(CertificateResponse certificateResponse) {
                if (certificateResponse.getData() != null && certificateResponse.getData().size()>0){
                    patientPhotos.postValue(certificateResponse.getData());
                }else {
                    patientPhotos.postValue(null);
                }
            }

            @Override
            public void sendFailed(String msg) {
                patientPhotos.postValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                patientPhotos.postValue(null);
            }
        });
    }

    public void editBusineService(BusinessDealBean businessDealBean){
        usersRemoteSource.editBusineService(businessDealBean, new UsersInterface.EditBusineServiceCallback() {
            @Override
            public void editSucceed(EditResponse editResponse) {
                editSucceed.setValue(true);
            }

            @Override
            public void editFailed(String msg) {
                editSucceed.setValue(false);
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                editSucceed.setValue(false);
            }
        });
    }

}
