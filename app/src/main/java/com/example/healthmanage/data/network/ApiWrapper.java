package com.example.healthmanage.data.network;


import com.example.healthmanage.bean.DoctorListResponse;
import com.example.healthmanage.bean.GeneralResponse;
import com.example.healthmanage.bean.HealthDataResponse;
import com.example.healthmanage.bean.LoginResponse;
import com.example.healthmanage.bean.MyMemberResponse;
import com.example.healthmanage.bean.RegisterResponse;
import com.example.healthmanage.bean.SearchMemberResponse;
import com.example.healthmanage.bean.TaskDetailResponse;
import com.example.healthmanage.bean.TaskResponse;
import com.example.healthmanage.bean.WeatherResponse;

import io.reactivex.Observable;

public class ApiWrapper extends RetrofitHttpUtils {
    private static volatile ApiWrapper apiWrapper;

    private ApiWrapper() {
        init();
    }

    public static ApiWrapper getInstance() {
        if (apiWrapper == null) {
            synchronized (ApiWrapper.class) {
                if (apiWrapper == null) {
                    apiWrapper = new ApiWrapper();
                }
            }
        }
        return apiWrapper;
    }


    /**
     * 使用密码登录系统
     *
     * @param userName 用户账号
     * @param password 密码
     * @param password 角色
     * @return LoginResponse
     */
    public Observable<LoginResponse> loginByPassword(String userName, String password,
                                                     String roleId) {
        return getApiServer().loginByPassword(userName, password, roleId);
    }


    /**
     * 获取手机验证码
     *
     * @param phone 接收验证码的手机号码
     * @return Register
     */
    public Observable<RegisterResponse> sendSmsMessage(String phone) {
        return getApiServer().sendSmsMessage(phone);
    }


    /**
     * 忘记密码
     *
     * @param phone
     * @param newPassword
     * @param smsCode
     * @param smsIdentity
     * @return
     */
    public Observable<GeneralResponse> updatePassword(String phone, String newPassword, String smsCode,
                                                      String smsIdentity) {
        return getApiServer().updatePassword(phone, newPassword, smsCode, smsIdentity);
    }

    /**
     * 用户注册
     *
     * @param phone       手机号
     * @param password    密码
     * @param smsCode     验证码
     * @param smsIdentity 发送短信时返回的标识
     * @return Response
     */
    public Observable<GeneralResponse> register(String phone, String password, String
            smsCode, String smsIdentity) {
        return getApiServer().register(phone, password, smsCode, smsIdentity);
    }

    /**
     * 搜索会员
     *
     * @param phone 手机号
     * @return SearchMemberResponse
     */
    public Observable<SearchMemberResponse> searchMembers(String phone) {
        return getApiServer().searchMembers(phone);
    }

    /**
     * 邀请会员
     *
     * @param sysId  医生id
     * @param userId 会员id
     * @return Response
     */
    public Observable<GeneralResponse> invitingMembers(String sysId, String userId) {
        return getApiServer().invitingMembers(sysId, userId);
    }

    /**
     * 我的关注
     *
     * @param sysId
     * @return
     */
    public Observable<MyMemberResponse> loadMyFocus(String sysId) {
        return getApiServer().loadMyFocus(sysId);
    }

    /**
     * 我的会员
     *
     * @param sysId
     * @return
     */
    public Observable<MyMemberResponse> loadMyMembers(String sysId) {
        return getApiServer().loadMyMembers(sysId);
    }

    /**
     * @param sysId
     * @param rank
     * @return
     */
    public Observable<MyMemberResponse> selectMember(String sysId, int rank) {
        return getApiServer().selectMember(sysId, rank);
    }

    /**
     * 添加关注
     *
     * @param sysId
     * @param userId
     * @return
     */
    public Observable<GeneralResponse> addFocus(String sysId, String userId) {
        return getApiServer().addFocus(sysId, userId);
    }

    /**
     * 取消关注
     *
     * @param userId
     * @return
     */
    public Observable<GeneralResponse> deleteFocus(String userId) {
        return getApiServer().deleteFocus(userId);
    }

    /**
     * 获取当前天气
     *
     * @param cityCode
     * @return
     */
    public Observable<WeatherResponse> getWeather(String cityCode) {
        return getApiServer().getWeather(cityCode);
    }

    /**
     * 健康数据 （心率、血压、血糖、运动和睡眠和血糖手表）接口
     *
     * @param userId
     * @return
     */
    public Observable<HealthDataResponse> getHealthList(String userId) {
        return getApiServer().getHealthList(userId);
    }

    /**
     * 按名字搜索会员
     *
     * @param userName
     * @param sysId
     * @return
     */
    public Observable<MyMemberResponse> searchMemberByName(String userName, String sysId) {
        return getApiServer().searchMemberByName(userName, sysId);
    }

    /**
     * 生成异常任务
     *
     * @param userId
     * @param title
     * @param content
     * @param managerId
     * @return
     */
    public Observable<GeneralResponse> createMyTask(long userId, String title, String content,
                                                    long managerId, String dataDate) {
        return getApiServer().createMyTask(userId, title, content, managerId, dataDate);
    }

    /**
     * 查询异常任务列表
     *
     * @param sysId
     * @return
     */
    public Observable<TaskResponse> loadMyTaskList(long sysId) {
        return getApiServer().loadMyTaskList(sysId);
    }

    /**
     * 加载异常任务详情
     *
     * @param taskId
     * @return
     */
    public Observable<TaskDetailResponse> loadMyTaskDetail(long taskId) {
        return getApiServer().loadMyTaskDetail(taskId);
    }

    /**
     * 管理师更新异常任务
     *
     * @param taskId
     * @param title
     * @param content
     * @return
     */
    public Observable<GeneralResponse> updateMyTaskDetail(long taskId, String title,
                                                          String content) {
        return getApiServer().updateMyTaskDetail(taskId, title, content);
    }

    /**
     * 其他角色更新异常任务
     *
     * @param taskId
     * @param doctorReply
     * @return
     */
    public Observable<GeneralResponse> updateMyTaskDetail(long taskId, String doctorReply) {
        return getApiServer().updateMyTaskDetail(taskId, doctorReply);
    }

    /**
     * 获取医生列表
     *
     * @return
     */
    public Observable<DoctorListResponse> getDoctorList() {
        return getApiServer().getDoctorList();
    }

    /**
     * 更新异常任务状态
     *
     * @param taskId
     * @param status
     * @return
     */
    public Observable<GeneralResponse> updateMyTaskStatus(long taskId, int status) {
        return getApiServer().updateMyTaskStatus(taskId, status);
    }

    /**
     * 发送异常任务
     *
     * @param taskId
     * @param doctorId
     * @return
     */
    public Observable<GeneralResponse> sendMyTask(long taskId, long doctorId) {
        return getApiServer().sendMyTask(taskId, doctorId);
    }


}