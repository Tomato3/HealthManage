package com.example.healthmanage.data.network;


import com.example.healthmanage.bean.AbnormalDataResponse;
import com.example.healthmanage.bean.AirResponse;
import com.example.healthmanage.bean.ConsultationRecordResponse;
import com.example.healthmanage.bean.ConsultationResponse;
import com.example.healthmanage.bean.DoctorDetailResponse;
import com.example.healthmanage.bean.DoctorListResponse;
import com.example.healthmanage.bean.GeneralResponse;
import com.example.healthmanage.bean.HealthDataResponse;
import com.example.healthmanage.bean.HistoryDataResponse;
import com.example.healthmanage.bean.LoginResponse;
import com.example.healthmanage.bean.MemberInfoResponse;
import com.example.healthmanage.bean.MyDoctorResponse;
import com.example.healthmanage.bean.MyMemberResponse;
import com.example.healthmanage.bean.NursingResponse;
import com.example.healthmanage.bean.RegisterResponse;
import com.example.healthmanage.bean.SearchMemberResponse;
import com.example.healthmanage.bean.ServicePlanResponse;
import com.example.healthmanage.bean.TaskDetailResponse;
import com.example.healthmanage.bean.TaskResponse;
import com.example.healthmanage.bean.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServer {

    /**
     * 使用密码登录系统
     *
     * @param userName 用户账号
     * @param password 密码
     * @param roleId   角色
     * @return LoginResponse
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("doctor/appSystemUser/phoneLogin")
    Observable<LoginResponse> loginByPassword(@Field("phone") String userName,
                                              @Field("password") String password,
                                              @Field("roleId") String roleId);

    /**
     * 获取手机验证码
     *
     * @param phone 接收验证码的手机号码
     * @return Register
     */
    @FormUrlEncoded
    @POST("api/appSms/sendMessage")
    Observable<RegisterResponse> sendSmsMessage(@Field("phone") String phone);

    /**
     * 用户注册
     *
     * @param phone       手机号
     * @param password    密码
     * @param smsCode     验证码
     * @param smsIdentity 发送短信时返回的标识
     * @return Response
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/register")
    Observable<GeneralResponse> register(@Field("phone") String phone,
                                         @Field("password") String password,
                                         @Field("smsCode") String smsCode,
                                         @Field("smsIdentity") String smsIdentity);

    /**
     * 忘记密码
     *
     * @param phone
     * @param newPassword
     * @param smsCode
     * @param smsIdentity
     * @return
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/updatePassword")
    Observable<GeneralResponse> updatePassword(@Field("phone") String phone,
                                               @Field("password") String newPassword,
                                               @Field("smsCode") String smsCode,
                                               @Field("smsIdentity") String smsIdentity);


    /**
     * 搜索会员
     *
     * @param phone 手机号
     * @return SearchMemberResponse
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/searchUser")
    Observable<SearchMemberResponse> searchMembers(@Field("phone") String phone);


    /**
     * 邀请会员
     *
     * @param sysId
     * @param userId
     * @return Response
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/invitationUser")
    Observable<GeneralResponse> invitingMembers(@Field("sysId") String sysId,
                                                @Field("userId") String userId);

    /**
     * 我的关注
     *
     * @param sysId
     * @return MyMemberResponse
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/doctorFollow")
    Observable<MyMemberResponse> loadMyFocus(@Field("sysId") String sysId);


    /**
     * 我的会员
     *
     * @param sysId
     * @return MyMemberResponse
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/doctorMember")
    Observable<MyMemberResponse> loadMyMembers(@Field("sysId") String sysId);


    /**
     * 获取不同等级会员
     *
     * @param sysId
     * @param rank
     * @return
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/selectMember")
    Observable<MyMemberResponse> selectMember(@Field("sysId") String sysId,
                                              @Field("rank") int rank);

    /**
     * 添加关注
     *
     * @param sysId
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/addFollow")
    Observable<GeneralResponse> addFocus(@Field("sysId") String sysId,
                                         @Field("userId") String userId);

    /**
     * 取消关注
     *
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/deleteFollow")
    Observable<GeneralResponse> deleteFocus(@Field("userId") String userId);


    /**
     * 获取当前天气
     *
     * @param cityCode
     * @return
     */
    @FormUrlEncoded
    @POST("api/weather/getRealtimeWeatherInfo")
    Observable<WeatherResponse> getWeather(@Field("city_code") String cityCode);


    /**
     * 健康数据 （心率、血压、血糖、运动和睡眠和血糖手表）接口
     *
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("api/health/healthList")
    Observable<HealthDataResponse> getHealthList(@Field("userId") String userId);


    /**
     * 按名字搜索会员
     *
     * @param userName
     * @param sysId
     * @return
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/selectByNameMember")
    Observable<MyMemberResponse> searchMemberByName(@Field("userName") String userName,
                                                    @Field("sysId") String sysId);

    /**
     * 生成异常任务
     *
     * @param userId
     * @param title
     * @param content
     * @param managerId
     * @return
     */
    @FormUrlEncoded
    @POST("api/healthTask/create")
    Observable<GeneralResponse> createMyTask(@Field("userId") long userId,
                                             @Field("title") String title,
                                             @Field("content") String content,
                                             @Field("managerId") long managerId);


    /**
     * 查询异常任务列表
     *
     * @param sysId
     * @return
     */
    @FormUrlEncoded
    @POST("api/healthTask/getTaskList")
    Observable<TaskResponse> loadMyTaskList(@Field("id") long sysId,
                                            @Field("pageNum") int pageNum,
                                            @Field("pageSize") int pageSize);


    /**
     * 加载任务详情
     *
     * @param taskId
     * @return
     */
    @FormUrlEncoded
    @POST("api/healthTask/getTask")
    Observable<TaskDetailResponse> loadMyTaskDetail(@Field("id") long taskId);


    /**
     * 管理师更新异常任务
     *
     * @param taskId
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("api/healthTask/updateTask")
    Observable<GeneralResponse> updateMyTaskDetailByManager(@Field("id") long taskId,
                                                            @Field("content") String content);


    /**
     * 更新异常任务
     *
     * @param taskId
     * @param doctorReply
     * @return
     */
    @FormUrlEncoded
    @POST("api/healthTask/updateTask")
    Observable<GeneralResponse> updateMyTaskDetailByDoctor(@Field("id") long taskId,
                                                           @Field("doctorReply") String doctorReply);


    /**
     * 获取医生列表
     *
     * @return
     */
    @POST("api/healthTask/getDoctorList")
    Observable<DoctorListResponse> getDoctorList();


    /**
     * 修改异常任务状态
     *
     * @param taskId
     * @param status
     * @return
     */
    @FormUrlEncoded
    @POST("api/healthTask/taskStatus")
    Observable<GeneralResponse> updateMyTaskStatus(@Field("id") long taskId,
                                                   @Field("status") int status);


    /**
     * 发送异常任务
     *
     * @param taskId
     * @param doctorId
     * @return
     */
    @FormUrlEncoded
    @POST("api/healthTask/sendTask")
    Observable<GeneralResponse> sendMyTask(@Field("id") long taskId,
                                           @Field("doctorId") long doctorId);


    /**
     * 查找医生
     *
     * @param doctorName
     * @return
     */
    @FormUrlEncoded
    @POST("api/healthTask/getDoctorByName")
    Observable<DoctorListResponse> searchDoctor(@Field("name") String doctorName);


    /**
     * 获取异常数据
     *
     * @param taskId
     * @param dataDate
     * @return
     */
    @FormUrlEncoded
    @POST("api/healthTask/getTaskUserHealth")
    Observable<AbnormalDataResponse> getAbnormalDataResponse(@Field("id") long taskId,
                                                             @Field("date") String dataDate);


    /**
     * 查看历史数据
     *
     * @param memberId
     * @param beginTime
     * @param endTime
     * @return
     */
    @FormUrlEncoded
    @POST("api/health/historyDate")
    Observable<HistoryDataResponse> getHistoryData(@Field("userId") long memberId,
                                                   @Field("beginTime") String beginTime,
                                                   @Field("endTime") String endTime,
                                                   @Field("pageNum") int pageNum,
                                                   @Field("pageSize") int pageSize);


    /**
     * 获取会员简介
     *
     * @param memberId
     * @return
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/userInfo")
    Observable<MemberInfoResponse> getMemberInfo(@Field("userId") long memberId,
                                                 @Field("token") String token);


    /**
     * 获取服务计划列表
     *
     * @param token
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("api/serviceplan/plan")
    Observable<ServicePlanResponse> getServicePlanList(@Field("token") String token,
                                                       @Field("pageNum") int pageNum,
                                                       @Field("pageSize") int pageSize);


    /**
     * 上传服务结果
     *
     * @param token
     * @param serviceTime
     * @param servicePlace
     * @param serviceResult
     * @return
     */
    @FormUrlEncoded
    @POST("api/serviceplan/result")
    Observable<GeneralResponse> uploadServiceResult(@Field("token") String token,
                                                    @Field("servicePlanId") long servicePlanId,
                                                    @Field("svctm") String serviceTime,
                                                    @Field("servicePlace") String servicePlace,
                                                    @Field("serviceResult") String serviceResult);


    /**
     * 空气检测仪数据
     *
     * @param memberId
     * @param sceneId
     * @return
     */
    @FormUrlEncoded
    @POST("api/appIAQ/getARIList")
    Observable<AirResponse> getAirList(@Field("userId") String memberId,
                                       @Field("sceneId") String sceneId);


    /**
     * 生成邀请码
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/generate/code")
    Observable<GeneralResponse> getInviteCode(@Field("token") String token);


    /**
     * 获取护理仪数据
     *
     * @param token
     * @param memberId
     * @return
     */
    @FormUrlEncoded
    @POST("api/health/huLiYiData")
    Observable<NursingResponse> getNursingData(@Field("token") String token,
                                               @Field("userId") long memberId);


    /**
     * 填写邀请码
     *
     * @param token
     * @param inviteCode
     * @return
     */
    @FormUrlEncoded
    @POST("api/appSystemUser/bindingDoctor")
    Observable<GeneralResponse> writeInviteCode(@Field("token") String token,
                                                @Field("code") String inviteCode);


    /**
     * 获取绑定医生
     *
     * @param token
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("api/appSystemUser/doctorList")
    Observable<MyDoctorResponse> getMyDoctor(@Field("token") String token,
                                             @Field("pageNum") int pageNum,
                                             @Field("pageSize") int pageSize);


    /**
     * 我的医生详情
     *
     * @param token
     * @param doctorId
     * @return
     */
    @FormUrlEncoded
    @POST("api/appSystemUser/doctorInfo")
    Observable<DoctorDetailResponse> getMyDoctorDetail(@Field("token") String token,
                                                       @Field("sysId") long doctorId);

    /**
     * 咨询问题列表
     *
     * @param token
     * @param pageNum
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("api/questionAnswer/consultList")
    Observable<ConsultationResponse> getConsultationList(@Field("token") String token,
                                                         @Field("pageNum") int pageNum,
                                                         @Field("pageSize") int pageSize);

    /**
     * 回复咨询信息
     *
     * @param token
     * @param consultationId
     * @param content
     * @param userName
     * @return
     */
    @FormUrlEncoded
    @POST("api/questionAnswer/replyUser")
    Observable<GeneralResponse> replayConsultation(@Field("token") String token,
                                                   @Field("questionId") long consultationId,
                                                   @Field("content") String content,
                                                   @Field("fromName") String userName);

    /**
     * 咨询回复记录
     *
     * @param token
     * @param questionId
     * @return
     */
    @FormUrlEncoded
    @POST("api/questionAnswer/records")
    Observable<ConsultationRecordResponse> getConsultationRecord(@Field("token") String token,
                                                                 @Field("questionId") long questionId);
}

