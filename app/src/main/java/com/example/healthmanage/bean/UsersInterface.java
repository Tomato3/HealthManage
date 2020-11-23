package com.example.healthmanage.bean;

import com.example.healthmanage.data.network.exception.ExceptionHandle;

public interface UsersInterface {

    interface LoginResponseCallback {
        void loginSucceed(LoginResponse loginResponse);

        void loginFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface SendSmsMessageCallback {
        void sendSucceed(RegisterResponse registerResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface RegisterCallback {
        void registerSucceed();

        void registerFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface UpdatePasswordCallback {
        void updateSucceed(GeneralResponse generalResponse);

        void updateFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface SearchMembersCallback {
        void searchSucceed(SearchMemberResponse searchMemberResponse);

        void searchFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface InvitingMembersCallback {
        void inviteSucceed(String msg);

        void inviteFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface LoadMyMembersCallback {
        void loadSucceed(MyMemberResponse myMemberResponse);

        void loadFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface AddFocusCallback {
        void addSucceed();

        void addFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface DeleteFocusCallback {
        void deleteSucceed();

        void deleteFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetWeatherCallback {
        void getSucceed(WeatherResponse weatherResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHealthListCallback {
        void getSucceed(HealthDataResponse healthDataResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface CreateMyTaskCallback {
        void createSucceed(GeneralResponse generalResponse);

        void createFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }


    interface LoadMyTaskCallback {
        void loadSucceed(TaskResponse taskResponse);

        void loadFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface LoadMyTaskDetailCallback {
        void loadSucceed(TaskDetailResponse taskDetailResponse);

        void loadFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface UpdateMyTaskDetailCallback {
        void updateSucceed(GeneralResponse generalResponse);

        void updateFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetDoctorListCallback {
        void getSucceed(DoctorListResponse doctorListResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface UpdateMyTaskStatusCallback {
        void updateSucceed(GeneralResponse generalResponse);

        void updateFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface SendMyTaskCallback {
        void sendSucceed(GeneralResponse generalResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface SearchDoctorCallback {
        void searchSucceed(DoctorListResponse doctorListResponse);

        void searchFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetAbnormalDataCallback {
        void getSucceed(AbnormalDataResponse abnormalDataResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHistoryDataCallback {
        void getSucceed(HistoryDataResponse historyDataResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetMemberInfoCallback {
        void getSucceed(MemberInfoResponse memberInfoResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetServicePlanListCallback {
        void getSucceed(ServicePlanResponse servicePlanResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface UploadServiceResultCallback {
        void uploadSucceed(GeneralResponse generalResponse);

        void uploadFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetAirListCallback {
        void getSucceed(AirResponse airResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetInviteCodeCallback {
        void getSucceed(GeneralResponse generalResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetNursingDataCallback {
        void getSucceed(NursingResponse nursingResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface WriteInviteCodeCallback {
        void writeSucceed(GeneralResponse generalResponse);

        void writeFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetMyDoctorCallback {
        void getSucceed(MyDoctorResponse myDoctorResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetMyDoctorDetailCallback {
        void getSucceed(DoctorDetailResponse doctorDetailResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetConsultationListCallback {
        void getSucceed(ConsultationResponse consultationResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface ReplayConsultationCallback {
        void replaySucceed(GeneralResponse generalResponse);

        void replayFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetConsultationRecordCallback {
        void getSucceed(ConsultationRecordResponse consultationRecordResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

}
