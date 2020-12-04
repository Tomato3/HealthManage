package com.example.healthmanage.data.network;


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
    public Observable<SmsCodeResponse> getSmsCode(String phone) {
        return getApiServer().getSmsCode(phone);
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
    public Observable<GeneralResponse> forgetPassword(String phone, String newPassword, String smsCode,
                                                      String smsIdentity) {
        return getApiServer().forgetPassword(phone, newPassword, smsCode, smsIdentity);
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
                                                    long managerId) {
        return getApiServer().createMyTask(userId, title, content, managerId);
    }

    /**
     * 查询异常任务列表
     *
     * @param sysId
     * @return
     */
    public Observable<TaskResponse> loadMyTaskList(long sysId, int pageNum, int pageSize) {
        return getApiServer().loadMyTaskList(sysId, pageNum, pageSize);
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
     * @param content
     * @return
     */
    public Observable<GeneralResponse> updateMyTaskDetailByManager(long taskId, String content) {
        return getApiServer().updateMyTaskDetailByManager(taskId, content);
    }

    /**
     * 其他角色更新异常任务
     *
     * @param taskId
     * @param doctorReply
     * @return
     */
    public Observable<GeneralResponse> updateMyTaskDetailByDoctor(long taskId, String doctorReply) {
        return getApiServer().updateMyTaskDetailByDoctor(taskId, doctorReply);
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


    /**
     * 查找医生
     *
     * @param doctorName
     * @return
     */
    public Observable<DoctorListResponse> searchDoctor(String doctorName) {
        return getApiServer().searchDoctor(doctorName);
    }

    /**
     * 获取异常数据
     *
     * @param taskId
     * @param dataDate
     * @return
     */
    public Observable<AbnormalDataResponse> getAbnormalDataResponse(long taskId, String dataDate) {
        return getApiServer().getAbnormalDataResponse(taskId, dataDate);
    }

    /**
     * 获取历史数据
     *
     * @param memberId
     * @param beginTime
     * @param endTime
     * @return
     */
    public Observable<HistoryDataResponse> getHistoryData(long memberId, String beginTime,
                                                          String endTime, int pageNum, int pageSize) {
        return getApiServer().getHistoryData(memberId, beginTime, endTime, pageNum, pageSize);
    }

    /**
     * 获取会员简介
     *
     * @param memberId
     * @return
     */
    public Observable<MemberInfoResponse> getMemberInfo(long memberId, String token) {
        return getApiServer().getMemberInfo(memberId, token);
    }

    /**
     * 获取服务计划列表
     *
     * @param token
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Observable<ServicePlanResponse> getServicePlanList(String token, int pageNum,
                                                              int pageSize) {
        return getApiServer().getServicePlanList(token, pageNum, pageSize);
    }

    /**
     * 上传服务结果
     *
     * @param token
     * @param serviceTime
     * @param servicePlace
     * @param serviceResult
     * @return
     */
    public Observable<GeneralResponse> uploadServiceResult(String token,
                                                           long servicePlanId, String serviceTime,
                                                           String servicePlace, String serviceResult) {
        return getApiServer().uploadServiceResult(token, servicePlanId, serviceTime, servicePlace, serviceResult);
    }

    /**
     * 空气检测仪数据
     *
     * @param memberId
     * @param sceneId
     * @return
     */
    public Observable<AirResponse> getAirList(String memberId, String sceneId) {
        return getApiServer().getAirList(memberId, sceneId);
    }

    /**
     * 生成邀请码
     *
     * @param token
     * @return
     */
    public Observable<GeneralResponse> getInviteCode(String token) {
        return getApiServer().getInviteCode(token);
    }

    /**
     * 获取护理仪数据
     *
     * @param token
     * @param memberId
     * @return
     */
    public Observable<NursingResponse> getNursingData(String token, long memberId) {
        return getApiServer().getNursingData(token, memberId);
    }

    /**
     * 填写邀请码
     *
     * @param token
     * @param inviteCode
     * @return
     */
    public Observable<GeneralResponse> writeInviteCode(String token, String inviteCode) {
        return getApiServer().writeInviteCode(token, inviteCode);
    }

    /**
     * 获取绑定医生
     *
     * @param token
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Observable<MyDoctorResponse> getMyDoctor(String token, int pageNum,
                                                    int pageSize) {
        return getApiServer().getMyDoctor(token, pageNum, pageSize);
    }

    /**
     * 我的医生详情
     *
     * @param token
     * @param doctorId
     * @return
     */
    public Observable<DoctorDetailResponse> getMyDoctorDetail(String token, long doctorId) {
        return getApiServer().getMyDoctorDetail(token, doctorId);
    }


    /**
     * 咨询问题列表
     *
     * @param token
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Observable<ConsultationResponse> getConsultationList(String token, int pageNum, int pageSize) {
        return getApiServer().getConsultationList(token, pageNum, pageSize);
    }


    /**
     * 回复咨询信息
     *
     * @param token
     * @param questionId
     * @param content
     * @param userName
     * @return
     */
    public Observable<GeneralResponse> replayConsultation(String token, long questionId, String content, String userName) {
        return getApiServer().replayConsultation(token, questionId, content, userName);
    }


    /**
     * 咨询回复记录
     *
     * @param token
     * @param questionId
     * @return
     */
    public Observable<ConsultationRecordResponse> getConsultationRecord(String token, long questionId) {
        return getApiServer().getConsultationRecord(token, questionId);
    }



}