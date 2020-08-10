package com.example.healthmanage.bean;

import android.util.Log;

import com.example.healthmanage.data.network.ApiWrapper;
import com.example.healthmanage.data.network.MyObserver;
import com.example.healthmanage.data.network.RxHelper;
import com.example.healthmanage.data.network.exception.ExceptionHandle;

import io.reactivex.disposables.Disposable;

import static com.example.healthmanage.utils.Constants.HTAG;

/**
 * 用户登录、注册、修改用户信息的网络资源
 */
public class UsersRemoteSource {


    /**
     * 登录
     *
     * @param phone
     * @param password
     * @param roleId
     * @param loginResponseCallback
     */
    public void loginByPassword(String phone, String password,
                                String roleId,
                                UsersInterface.LoginResponseCallback loginResponseCallback) {
        Log.d(HTAG, "loginByPassword==========>: " + "手机号===>" + phone + "密码===>" + password +
                "角色===>" + roleId);
        ApiWrapper.getInstance().loginByPassword(phone, password, roleId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        if (loginResponse.getStatus() == 200) {
                            loginResponseCallback.loginSucceed(loginResponse);
                        } else {
                            loginResponseCallback.loginFailed(loginResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        loginResponseCallback.error(responseException);
                    }
                });

    }

    /**
     * 发验证码
     *
     * @param phone
     * @param sendSmsMessageCallback
     */
    public void sendSmsMessage(String phone,
                               UsersInterface.SendSmsMessageCallback sendSmsMessageCallback) {
        Log.d(HTAG, "sendSmsMessage==========>: " + "手机号===>" + phone);
        ApiWrapper.getInstance().sendSmsMessage(phone).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        if (registerResponse.getStatus() == 200) {//获取成功
                            sendSmsMessageCallback.sendSucceed(registerResponse);//发送时返回的代码
                        } else {//获取失败
                            sendSmsMessageCallback.sendFailed(registerResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        sendSmsMessageCallback.error(responseException);
                    }
                });
    }

    /**
     * 忘记密码
     *
     * @param phone
     * @param newPassword
     * @param smsCode
     * @param smsIdentity
     * @param updatePasswordCallback
     */
    public void updatePassword(String phone,
                               String newPassword,
                               String smsCode,
                               String smsIdentity,
                               UsersInterface.UpdatePasswordCallback updatePasswordCallback) {
        Log.d(HTAG,
                "updatePassword==========>: " + "手机号===>" + phone + "新密码===>" + newPassword +
                        "验证码===>" + smsCode + "验证码标识===>" + smsIdentity);
        ApiWrapper.getInstance().updatePassword(phone, newPassword, smsCode, smsIdentity).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            //重置密码成功
                            updatePasswordCallback.updateSucceed(generalResponse);
                        } else {
                            //重置失败
                            updatePasswordCallback.updateFailed(generalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        updatePasswordCallback.error(responseException);
                    }
                });
    }

    /**
     * 注册
     *
     * @param phone
     * @param password
     * @param smsCode
     * @param smsIdentity
     * @param registerCallback
     */
    public void register(String phone,
                         String password,
                         String smsCode,
                         String smsIdentity,
                         UsersInterface.RegisterCallback registerCallback) {
        Log.d(HTAG, "register==========>: " + "手机号===>" + phone + "密码===>" + password + "验证码===>" + smsCode + "验证码标识===>" + smsIdentity);
        ApiWrapper.getInstance().register(phone, password, smsCode, smsIdentity).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 200) {//注册成功
                            registerCallback.registerSucceed();
                        } else {
                            registerCallback.registerFailed(generalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        registerCallback.error(responseException);
                    }
                });
    }

    /**
     * 搜索会员
     *
     * @param phone
     * @param searchMembersCallback
     */
    public void searchMembers(String phone,
                              UsersInterface.SearchMembersCallback searchMembersCallback) {
        Log.d(HTAG, "searchMembers==========>: " + "手机号===>" + phone);
        ApiWrapper.getInstance().searchMembers(phone)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<SearchMemberResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        searchMembersCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchMemberResponse searchMemberResponse) {
                        if (searchMemberResponse.getStatus() == 0) {
                            searchMembersCallback.searchSucceed(searchMemberResponse);
                        } else {
                            searchMembersCallback.searchFailed(searchMemberResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 邀请会员
     *
     * @param sysId
     * @param userId
     * @param invitingMembersCallback
     */
    public void inviteMembers(String sysId,
                              String userId,
                              UsersInterface.InvitingMembersCallback invitingMembersCallback) {
        Log.d(HTAG, "inviteMembers==========>: " + "当前用户Id===>" + sysId + "会员Id===>" + userId);
        ApiWrapper.getInstance().invitingMembers(sysId, userId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        invitingMembersCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            invitingMembersCallback.inviteSucceed(generalResponse.getMessage());
                        } else {
                            invitingMembersCallback.inviteFailed(generalResponse.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 加载我的会员
     *
     * @param sysId
     * @param loadMyMembersCallback
     */
    public void loadMyMembers(String sysId,
                              UsersInterface.LoadMyMembersCallback loadMyMembersCallback) {
        Log.d(HTAG, "loadMyMembers==========>: " + "当前用户Id===>" + sysId);
        ApiWrapper.getInstance().loadMyMembers(sysId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<MyMemberResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyMemberResponse myMemberResponse) {
                        if (myMemberResponse.getStatus() == 0) {
                            loadMyMembersCallback.loadSucceed(myMemberResponse);
                        } else {
                            loadMyMembersCallback.loadFailed(myMemberResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        loadMyMembersCallback.error(responseException);
                    }
                });
    }

    /**
     * 加载不同等级会员
     *
     * @param sysId
     * @param rank
     * @param loadMyMembersCallback
     */
    public void selectMember(String sysId,
                             int rank,
                             UsersInterface.LoadMyMembersCallback loadMyMembersCallback) {
        Log.d(HTAG, "selectMember==========>: " + "当前用户Id===>" + sysId + "会员等级===>" + rank);
        ApiWrapper.getInstance().selectMember(sysId, rank)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<MyMemberResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyMemberResponse myMemberResponse) {
                        if (myMemberResponse.getStatus() == 0) {
                            loadMyMembersCallback.loadSucceed(myMemberResponse);
                        } else {
                            loadMyMembersCallback.loadFailed(myMemberResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        loadMyMembersCallback.error(responseException);
                    }
                });
    }

    /**
     * 加载我的关注
     *
     * @param sysId
     * @param loadMyMembersCallback
     */
    public void loadMyFocus(String sysId,
                            UsersInterface.LoadMyMembersCallback loadMyMembersCallback) {
        Log.d(HTAG, "loadMyFocus==========>: " + "当前用户Id===>" + sysId);
        ApiWrapper.getInstance().loadMyFocus(sysId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<MyMemberResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyMemberResponse myMemberResponse) {
                        if (myMemberResponse.getStatus() == 0) {
                            loadMyMembersCallback.loadSucceed(myMemberResponse);
                        } else {
                            loadMyMembersCallback.loadFailed(myMemberResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        loadMyMembersCallback.error(responseException);
                    }
                });
    }

    /**
     * 添加关注
     *
     * @param sysId
     * @param userId
     * @param addFocusCallback
     */
    public void addFocus(String sysId,
                         String userId,
                         UsersInterface.AddFocusCallback addFocusCallback) {
        Log.d(HTAG, "addFocus==========>: " + "当前用户Id===>" + sysId + "会员Id===>" + userId);
        ApiWrapper.getInstance().addFocus(sysId, userId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            addFocusCallback.addSucceed();
                        } else {
                            addFocusCallback.addFailed(generalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        addFocusCallback.error(responseException);
                    }
                });
    }

    /**
     * 取消关注
     *
     * @param userId
     * @param deleteFocusCallback
     */
    public void deleteFocus(String userId,
                            UsersInterface.DeleteFocusCallback deleteFocusCallback) {
        Log.d(HTAG, "deleteFocus==========>: " + "会员Id===>" + userId);
        ApiWrapper.getInstance().deleteFocus(userId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            deleteFocusCallback.deleteSucceed();
                        } else {
                            deleteFocusCallback.deleteFailed(generalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        deleteFocusCallback.error(responseException);
                    }
                });
    }

    /**
     * 获取天气环境数据
     *
     * @param cityCode
     * @param getWeatherCallback
     */
    public void getWeather(String cityCode,
                           UsersInterface.GetWeatherCallback getWeatherCallback) {
        Log.d(HTAG, "getWeather==========>: " + "城市===>" + cityCode);
        ApiWrapper.getInstance().getWeather(cityCode)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<WeatherResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        if (weatherResponse.getStatus() == 0) {
                            getWeatherCallback.getSucceed(weatherResponse);
                        } else {
                            getWeatherCallback.getFailed(weatherResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getWeatherCallback.error(responseException);

                    }
                });
    }

    /**
     * 获取会员健康数据
     *
     * @param userId
     * @param getHealthListCallback
     */
    public void getHealthList(String userId,
                              UsersInterface.GetHealthListCallback getHealthListCallback) {
        Log.d(HTAG, "getHealthList==========>: " + "会员Id===>" + userId);
        ApiWrapper.getInstance().getHealthList(userId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<HealthDataResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HealthDataResponse healthDataResponse) {
                        if (healthDataResponse.getStatus() == 200) {
                            getHealthListCallback.getSucceed(healthDataResponse);
                        } else {
                            getHealthListCallback.getFailed(healthDataResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHealthListCallback.error(responseException);
                    }
                });
    }

    /**
     * 通过会员名搜索会员
     *
     * @param userName
     * @param sysId
     * @param loadMyMembersCallback
     */
    public void searchMemberByName(String userName,
                                   String sysId,
                                   UsersInterface.LoadMyMembersCallback loadMyMembersCallback) {
        Log.d(HTAG, "searchMemberByName==========>: " + "会员名===>" + userName + "当前用户Id===>" + sysId);
        ApiWrapper.getInstance().searchMemberByName(userName, sysId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<MyMemberResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyMemberResponse myMemberResponse) {
                        if (myMemberResponse.getStatus() == 0) {
                            loadMyMembersCallback.loadSucceed(myMemberResponse);
                        } else {
                            loadMyMembersCallback.loadFailed(myMemberResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        loadMyMembersCallback.error(responseException);
                    }
                });
    }

    /**
     * 生成异常任务
     *
     * @param userId
     * @param title
     * @param content
     * @param managerId
     * @param createMyTaskCallback
     */
    public void createMyTask(long userId,
                             String title,
                             String content,
                             long managerId,
                             UsersInterface.CreateMyTaskCallback createMyTaskCallback) {
        Log.d(HTAG, "createMyTask==========>: " + "会员Id===>" + userId + "异常任务标题===>" + title +
                "异常任务描述===>" + content + "当前用户Id===>" + managerId);
        ApiWrapper.getInstance().createMyTask(userId, title, content, managerId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            createMyTaskCallback.createSucceed(generalResponse);
                        } else {
                            createMyTaskCallback.createFailed(generalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        createMyTaskCallback.error(responseException);
                    }
                });
    }

    /**
     * 加载我的异常任务列表
     *
     * @param sysId
     * @param loadMyTaskCallback
     */
    public void loadMyTask(long sysId,
                           UsersInterface.LoadMyTaskCallback loadMyTaskCallback) {
        Log.d(HTAG, "loadMyTask==========>: " + "当前用户Id===>" + sysId);
        ApiWrapper.getInstance().loadMyTaskList(sysId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<TaskResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TaskResponse taskResponse) {
                        if (taskResponse.getStatus() == 0) {
                            loadMyTaskCallback.loadSucceed(taskResponse);
                        } else {
                            loadMyTaskCallback.loadFailed(taskResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        loadMyTaskCallback.error(responseException);
                    }
                });
    }

    /**
     * 加载异常任务详情
     *
     * @param taskId
     * @param loadMyTaskDetailCallback
     */
    public void loadMyTaskDetail(long taskId,
                                 UsersInterface.LoadMyTaskDetailCallback loadMyTaskDetailCallback) {
        Log.d(HTAG, "loadMyTaskDetail==========>: " + "当前任务Id===>" + taskId);
        ApiWrapper.getInstance().loadMyTaskDetail(taskId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<TaskDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(TaskDetailResponse taskDetailResponse) {
                        if (taskDetailResponse.getStatus() == 0) {
                            loadMyTaskDetailCallback.loadSucceed(taskDetailResponse);
                        } else {
                            loadMyTaskDetailCallback.loadFailed(taskDetailResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        loadMyTaskDetailCallback.error(responseException);
                    }
                });
    }

    /**
     * 管理师更新异常任务
     *
     * @param taskId
     * @param content
     * @param updateMyTaskDetailCallback
     */
    public void updateMyTaskDetailByManager(long taskId,
                                            String content,
                                            UsersInterface.UpdateMyTaskDetailCallback updateMyTaskDetailCallback) {
        Log.d(HTAG, "updateMyTaskDetail==========>: " + "当前任务Id===>" + taskId);
        ApiWrapper.getInstance().updateMyTaskDetailByManager(taskId, content)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            updateMyTaskDetailCallback.updateSucceed(generalResponse);
                        } else {
                            updateMyTaskDetailCallback.updateFailed(generalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        updateMyTaskDetailCallback.error(responseException);
                    }
                });
    }

    /**
     * 其他角色更新异常任务
     *
     * @param taskId
     * @param doctorReply
     * @param updateMyTaskDetailCallback
     */
    public void updateMyTaskDetailByDoctor(long taskId,
                                           String doctorReply,
                                           UsersInterface.UpdateMyTaskDetailCallback updateMyTaskDetailCallback) {
        Log.d(HTAG, "updateMyTaskDetail==========>: " + "当前任务Id===>" + taskId);
        ApiWrapper.getInstance().updateMyTaskDetailByDoctor(taskId, doctorReply)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            updateMyTaskDetailCallback.updateSucceed(generalResponse);
                        } else {
                            updateMyTaskDetailCallback.updateFailed(generalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        updateMyTaskDetailCallback.error(responseException);
                    }
                });
    }

    /**
     * 获取医生列表
     *
     * @param getDoctorListCallback
     */
    public void getDoctorList(UsersInterface.GetDoctorListCallback getDoctorListCallback) {
        ApiWrapper.getInstance().getDoctorList()
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<DoctorListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DoctorListResponse doctorListResponse) {
                        if (doctorListResponse.getStatus() == 0) {
                            getDoctorListCallback.getSucceed(doctorListResponse);
                        } else {
                            getDoctorListCallback.getFailed(doctorListResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getDoctorListCallback.error(responseException);
                    }
                });
    }

    /**
     * 修改异常任务状态
     *
     * @param taskId
     * @param status
     * @param updateMyTaskStatusCallback
     */
    public void updateMyTaskStatus(long taskId, int status,
                                   UsersInterface.UpdateMyTaskStatusCallback updateMyTaskStatusCallback) {
        Log.d(HTAG, "updateMyTaskStatus==========>: " + "当前任务Id===>" + taskId);
        ApiWrapper.getInstance().updateMyTaskStatus(taskId, status)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            updateMyTaskStatusCallback.updateSucceed(generalResponse);
                        } else {
                            updateMyTaskStatusCallback.updateFailed(generalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        updateMyTaskStatusCallback.error(responseException);
                    }
                });
    }

    /**
     * 发送异常任务
     *
     * @param taskId
     * @param doctorId
     * @param sendMyTaskCallback
     */
    public void sendMyTask(long taskId, long doctorId,
                           UsersInterface.SendMyTaskCallback sendMyTaskCallback) {
        Log.d(HTAG, "sendMyTask==========>: " + "当前任务Id===>" + taskId + "发送Id===>" + doctorId);
        ApiWrapper.getInstance().sendMyTask(taskId, doctorId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            sendMyTaskCallback.sendSucceed(generalResponse);
                        } else {
                            sendMyTaskCallback.sendFailed(generalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        sendMyTaskCallback.error(responseException);
                    }
                });
    }

    /**
     * 搜索医生
     *
     * @param doctorName
     * @param searchDoctorCallback
     */
    public void searchDoctor(String doctorName,
                             UsersInterface.SearchDoctorCallback searchDoctorCallback) {
        Log.d(HTAG, "searchDoctor==========>: " + "医生名字:" + doctorName);
        ApiWrapper.getInstance().searchDoctor(doctorName)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<DoctorListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DoctorListResponse doctorListResponse) {
                        if (doctorListResponse.getStatus() == 0) {
                            searchDoctorCallback.searchSucceed(doctorListResponse);
                        } else {
                            searchDoctorCallback.searchFailed(doctorListResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        searchDoctorCallback.error(responseException);
                    }
                });
    }

    /**
     * 获取异常数据
     *
     * @param taskId
     * @param dataDate
     * @param getAbnormalDataCallback
     */
    public void getAbnormalData(long taskId,
                                String dataDate,
                                UsersInterface.GetAbnormalDataCallback getAbnormalDataCallback) {
        Log.d(HTAG, "getAbnormalData==========>: 任务id:" + taskId + "异常时间===>" + dataDate);
        ApiWrapper.getInstance().getAbnormalDataResponse(taskId, dataDate)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<AbnormalDataResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AbnormalDataResponse abnormalDataResponse) {
                        if (abnormalDataResponse.getStatus() == 0) {
                            getAbnormalDataCallback.getSucceed(abnormalDataResponse);
                        } else {
                            getAbnormalDataCallback.getFailed(abnormalDataResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getAbnormalDataCallback.error(responseException);
                    }
                });
    }


    /**
     * 获取历史数据
     *
     * @param memberId
     * @param beginTime
     * @param endTime
     * @param getHistoryDataCallback
     */
    public void getHistoryData(long memberId,
                               String beginTime,
                               String endTime,
                               int pageNum,
                               int pageSize,
                               UsersInterface.GetHistoryDataCallback getHistoryDataCallback) {
        Log.d(HTAG, "getHistoryData==========>: 会员id:" + memberId + "开始时间===>" + beginTime +
                "结束时间===>" + endTime);
        ApiWrapper.getInstance().getHistoryData(memberId, beginTime, endTime, pageNum, pageSize)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<HistoryDataResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(HistoryDataResponse historyDataResponse) {
                        if (historyDataResponse.getTotal() > 0) {
                            getHistoryDataCallback.getSucceed(historyDataResponse);
                        } else {
                            getHistoryDataCallback.getFailed("historyDataResponse.getMessage()");
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHistoryDataCallback.error(responseException);
                    }
                });
    }
}
