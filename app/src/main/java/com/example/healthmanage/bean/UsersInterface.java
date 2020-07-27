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
}
