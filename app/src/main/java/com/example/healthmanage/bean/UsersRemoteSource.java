package com.example.healthmanage.bean;

import android.util.Log;

import com.example.healthmanage.bean.network.response.AbnormalDataResponse;
import com.example.healthmanage.bean.network.response.AirResponse;
import com.example.healthmanage.bean.network.response.ConsultationRecordResponse;
import com.example.healthmanage.bean.network.response.ConsultationResponse;
import com.example.healthmanage.bean.network.response.DoctorDetailResponse;
import com.example.healthmanage.bean.network.response.DoctorListResponse;
import com.example.healthmanage.bean.network.response.GeneralResponse;
import com.example.healthmanage.bean.network.response.HealthDataResponse;
import com.example.healthmanage.bean.network.response.HistoryDataResponse;
import com.example.healthmanage.bean.network.response.LoginResponse;
import com.example.healthmanage.bean.network.response.MemberInfoResponse;
import com.example.healthmanage.bean.network.response.MyDoctorResponse;
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.bean.network.response.NursingResponse;
import com.example.healthmanage.bean.network.response.SearchMemberResponse;
import com.example.healthmanage.bean.network.response.ServicePlanResponse;
import com.example.healthmanage.bean.network.response.SmsCodeResponse;
import com.example.healthmanage.bean.network.response.TaskDetailResponse;
import com.example.healthmanage.bean.network.response.TaskResponse;
import com.example.healthmanage.bean.network.response.WeatherResponse;
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
     * @param getSmsCodeCallback
     */
    public void getSmsCode(String phone,
                           UsersInterface.getSmsCodeCallback getSmsCodeCallback) {
        Log.d(HTAG, "getSmsCode==========>: " + "手机号===>" + phone);
        ApiWrapper.getInstance().getSmsCode(phone).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<SmsCodeResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(SmsCodeResponse smsCodeResponse) {
                        if (smsCodeResponse.getStatus() == 200) {//获取成功
                            getSmsCodeCallback.sendSucceed(smsCodeResponse);//发送时返回的代码
                        } else {//获取失败
                            getSmsCodeCallback.sendFailed(smsCodeResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getSmsCodeCallback.error(responseException);
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
    public void forgetPassword(String phone,
                               String newPassword,
                               String smsCode,
                               String smsIdentity,
                               UsersInterface.ForgetPasswordCallback updatePasswordCallback) {
        Log.d(HTAG,
                "forgetPassword==========>: " + "手机号===>" + phone + "新密码===>" + newPassword +
                        "验证码===>" + smsCode + "验证码标识===>" + smsIdentity);
        ApiWrapper.getInstance().forgetPassword(phone, newPassword, smsCode, smsIdentity).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            //重置密码成功
                            updatePasswordCallback.forgetSucceed(generalResponse);
                        } else {
                            //重置失败
                            updatePasswordCallback.forgetFailed(generalResponse.getMessage());
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
                           int pageNum,
                           int pageSize,
                           UsersInterface.LoadMyTaskCallback loadMyTaskCallback) {
        Log.d(HTAG, "loadMyTask==========>: " + "当前用户Id===>" + sysId);
        ApiWrapper.getInstance().loadMyTaskList(sysId, pageNum, pageSize)
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
                        if (historyDataResponse.getStatus() == 0) {
                            getHistoryDataCallback.getSucceed(historyDataResponse);
                        } else {
                            getHistoryDataCallback.getFailed(historyDataResponse.getMessage());
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

    /**
     * 获取会员简介
     *
     * @param memberId
     * @param token
     * @param getMemberInfoCallback
     */
    public void getMemberInfo(long memberId,
                              String token,
                              UsersInterface.GetMemberInfoCallback getMemberInfoCallback) {
        Log.d(HTAG, "getMemberInfo==========>:会员Id: " + memberId);
        ApiWrapper.getInstance().getMemberInfo(memberId, token)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<MemberInfoResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(MemberInfoResponse memberInfoResponse) {
                        if (memberInfoResponse.getStatus() == 0) {
                            getMemberInfoCallback.getSucceed(memberInfoResponse);
                        } else {
                            getMemberInfoCallback.getFailed(memberInfoResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getMemberInfoCallback.error(responseException);
                    }
                });
    }

    /**
     * 获取服务计划列表
     *
     * @param token
     * @param pageNum
     * @param pageSize
     * @param getServicePlanListCallback
     */
    public void getServicePlanList(String token,
                                   int pageNum,
                                   int pageSize,
                                   UsersInterface.GetServicePlanListCallback getServicePlanListCallback) {
        Log.d(HTAG, "getServicePlanList==========>: 请求页码：" + pageNum);
        ApiWrapper.getInstance().getServicePlanList(token, pageNum, pageSize)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<ServicePlanResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ServicePlanResponse servicePlanResponse) {
                        if (servicePlanResponse.getStatus() == 0) {
                            getServicePlanListCallback.getSucceed(servicePlanResponse);
                        } else {
                            getServicePlanListCallback.getFailed(servicePlanResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getServicePlanListCallback.error(responseException);
                    }
                });
    }

    /**
     * 上传服务结果
     *
     * @param token
     * @param serviceTime
     * @param servicePlace
     * @param serviceResult
     * @param uploadServiceResultCallback
     */
    public void uploadServiceResult(String token,
                                    long servicePlanId,
                                    String serviceTime,
                                    String servicePlace,
                                    String serviceResult,
                                    UsersInterface.UploadServiceResultCallback uploadServiceResultCallback) {
        Log.d(HTAG, "uploadServiceResult服务时间==========>: " + serviceTime + "服务地点===>" + servicePlace +
                "服务结果===>" + serviceResult + "服务计划Id===>" + servicePlanId);
        ApiWrapper.getInstance().uploadServiceResult(token, servicePlanId, serviceTime, servicePlace,
                serviceResult)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            uploadServiceResultCallback.uploadSucceed(generalResponse);
                        } else {
                            uploadServiceResultCallback.uploadFailed(generalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        uploadServiceResultCallback.error(responseException);
                    }
                });
    }


    /**
     * 空气检测仪数据
     *
     * @param memberId
     * @param getAirListCallback
     */
    public void getAirList(String memberId,
                           UsersInterface.GetAirListCallback getAirListCallback) {
        Log.d(HTAG, "getAirList memberId==========>: " + memberId);
        ApiWrapper.getInstance().getAirList(memberId, "1")
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<AirResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AirResponse airResponse) {
                        if (airResponse.getStatus() == 0) {
                            getAirListCallback.getSucceed(airResponse);
                        } else {
                            getAirListCallback.getFailed(airResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getAirListCallback.error(responseException);
                    }
                });
    }

    /**
     * 生成邀请码
     *
     * @param token
     * @param getInviteCodeCallback
     */
    public void getInviteCode(String token,
                              UsersInterface.GetInviteCodeCallback getInviteCodeCallback) {
        Log.d(HTAG, "getInviteCode token==========>: " + token);
        ApiWrapper.getInstance().getInviteCode(token)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            getInviteCodeCallback.getSucceed(generalResponse);
                        } else {
                            getInviteCodeCallback.getFailed(generalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getInviteCodeCallback.error(responseException);
                    }
                });
    }

    /**
     * 获取护理仪数据
     *
     * @param token
     * @param memberId
     * @param getNursingDataCallback
     */
    public void getNursingData(String token,
                               long memberId,
                               UsersInterface.GetNursingDataCallback getNursingDataCallback) {
        Log.d(HTAG, "getNursingData token==========>: " + token + "memberId===>" + memberId);
        ApiWrapper.getInstance().getNursingData(token, memberId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<NursingResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(NursingResponse nursingResponse) {
                        if (nursingResponse.getStatus() == 0) {
                            getNursingDataCallback.getSucceed(nursingResponse);
                        } else {
                            getNursingDataCallback.getFailed(nursingResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getNursingDataCallback.error(responseException);
                    }
                });
    }

    /**
     * 填写邀请码
     *
     * @param token
     * @param inviteCode
     * @param writeInviteCodeCallback
     */
    public void writeInviteCode(String token,
                                String inviteCode,
                                UsersInterface.WriteInviteCodeCallback writeInviteCodeCallback) {
        Log.d(HTAG, "getNursingData token==========>: " + token + "inviteCode===>" + inviteCode);
        ApiWrapper.getInstance().writeInviteCode(token, inviteCode)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            writeInviteCodeCallback.writeSucceed(generalResponse);
                        } else {
                            writeInviteCodeCallback.writeFailed(generalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        writeInviteCodeCallback.error(responseException);
                    }
                });
    }

    /**
     * 获取绑定医生
     *
     * @param token
     * @param pageNum
     * @param pageSize
     * @param getMyDoctorCallback
     */
    public void getMyDoctor(String token,
                            int pageNum,
                            int pageSize,
                            UsersInterface.GetMyDoctorCallback getMyDoctorCallback) {
        Log.d(HTAG, "getMyDoctor token==========>: " + token + "pageNum===>" + pageNum + "");
        ApiWrapper.getInstance().getMyDoctor(token, pageNum, pageSize)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<MyDoctorResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(MyDoctorResponse myDoctorResponse) {
                        if (myDoctorResponse.getStatus() == 0) {
                            getMyDoctorCallback.getSucceed(myDoctorResponse);
                        } else {
                            getMyDoctorCallback.getFailed(myDoctorResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getMyDoctorCallback.error(responseException);
                    }
                });
    }

    /**
     * 我的医生详情
     *
     * @param token
     * @param doctorId
     * @param getMyDoctorDetailCallback
     */
    public void getMyDoctorDetail(String token,
                                  long doctorId,
                                  UsersInterface.GetMyDoctorDetailCallback getMyDoctorDetailCallback) {
        Log.d(HTAG, "getMyDoctor token==========>: " + token + "doctorId===>" + doctorId);
        ApiWrapper.getInstance().getMyDoctorDetail(token, doctorId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<DoctorDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DoctorDetailResponse doctorDetailResponse) {
                        if (doctorDetailResponse.getStatus() == 0) {
                            getMyDoctorDetailCallback.getSucceed(doctorDetailResponse);
                        } else {
                            getMyDoctorDetailCallback.getFailed(doctorDetailResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getMyDoctorDetailCallback.error(responseException);
                    }
                });
    }

    /**
     * 咨询问题列表
     *
     * @param token
     * @param pageNum
     * @param pageSize
     * @param getConsultationListCallback
     */
    public void getConsultationList(String token,
                                    int pageNum,
                                    int pageSize,
                                    UsersInterface.GetConsultationListCallback getConsultationListCallback) {
        ApiWrapper.getInstance().getConsultationList(token, pageNum, pageSize)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<ConsultationResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ConsultationResponse consultationResponse) {
                        if (consultationResponse.getStatus() == 0) {
                            getConsultationListCallback.getSucceed(consultationResponse);
                        } else {
                            getConsultationListCallback.getFailed(consultationResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getConsultationListCallback.error(responseException);
                    }
                });
    }


    /**
     * 回复咨询信息
     *
     * @param token
     * @param questionId
     * @param content
     * @param userName
     * @param replayConsultationCallback
     */
    public void replayConsultation(String token,
                                   long questionId,
                                   String content,
                                   String userName,
                                   UsersInterface.ReplayConsultationCallback replayConsultationCallback) {
        ApiWrapper.getInstance().replayConsultation(token, questionId, content, userName)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        if (generalResponse.getStatus() == 0) {
                            replayConsultationCallback.replaySucceed(generalResponse);
                        } else {
                            replayConsultationCallback.replayFailed(generalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        replayConsultationCallback.error(responseException);
                    }
                });
    }

    public void getConsultationRecord(String token,
                                      long questionId,
                                      UsersInterface.GetConsultationRecordCallback getConsultationRecordCallback) {
        ApiWrapper.getInstance().getConsultationRecord(token, questionId)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<ConsultationRecordResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ConsultationRecordResponse consultationRecordResponse) {
                        if (consultationRecordResponse.getStatus() == 0) {
                            getConsultationRecordCallback.getSucceed(consultationRecordResponse);
                        } else {
                            getConsultationRecordCallback.getFailed(consultationRecordResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getConsultationRecordCallback.error(responseException);
                    }
                });
    }

}
