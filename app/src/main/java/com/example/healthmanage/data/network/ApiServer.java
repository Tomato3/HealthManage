package com.example.healthmanage.data.network;


import com.example.healthmanage.bean.ProfessionBeanResponse;
import com.example.healthmanage.bean.network.response.AbnormalDataResponse;
import com.example.healthmanage.bean.network.response.AirResponse;
import com.example.healthmanage.bean.network.response.BaseResponse;
import com.example.healthmanage.bean.network.response.ConsultationRecordResponse;
import com.example.healthmanage.bean.network.response.ConsultationResponse;
import com.example.healthmanage.bean.network.response.DoctorDetailResponse;
import com.example.healthmanage.bean.network.response.DoctorListResponse;
import com.example.healthmanage.bean.network.response.GeneralResponse;
import com.example.healthmanage.bean.network.response.HistoryDataResponse;
import com.example.healthmanage.bean.network.response.LoginResponse;
import com.example.healthmanage.bean.network.response.MemberInfoResponse;
import com.example.healthmanage.bean.network.response.MyDoctorResponse;
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.bean.network.response.NursingResponse;
import com.example.healthmanage.bean.network.response.ServicePlanResponse;
import com.example.healthmanage.bean.network.response.SmsCodeResponse;
import com.example.healthmanage.bean.network.response.TaskDetailResponse;
import com.example.healthmanage.bean.network.response.TaskResponse;
import com.example.healthmanage.bean.network.response.WeatherResponse;
import com.example.healthmanage.ui.activity.academicJournals.bean.AddPeriodicalBean;
import com.example.healthmanage.ui.activity.academicJournals.bean.EditPeriodicalBean;
import com.example.healthmanage.ui.activity.academicJournals.response.AddOrEditSucceedResponse;
import com.example.healthmanage.ui.activity.academicJournals.response.PeriodicalInfoResponse;
import com.example.healthmanage.ui.activity.academicJournals.response.PeriodicalListResponse;
import com.example.healthmanage.ui.activity.consultation.response.AddConsultationPlanResponse;
import com.example.healthmanage.ui.activity.consultation.response.AddPatientInfoResponse;
import com.example.healthmanage.ui.activity.consultation.response.ConsultationListResponse;
import com.example.healthmanage.ui.activity.consultation.response.DoctorTeamListResponse;
import com.example.healthmanage.ui.activity.consultation.response.DoctordepartMentResponse;
import com.example.healthmanage.ui.activity.consultation.response.PatientInfoBean;
import com.example.healthmanage.ui.activity.delegate.response.CreateDelegateResponse;
import com.example.healthmanage.ui.activity.delegate.response.DelegateBean;
import com.example.healthmanage.ui.activity.delegate.response.DelegateListResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.CheckReportResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.HealthRecordResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.HistoryAssessListResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.MedicineBookResponse;
import com.example.healthmanage.ui.activity.healthreport.HealthReportInfo;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportConfirmResponse;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportDetailResponse;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportResponse;
import com.example.healthmanage.ui.activity.memberdetail.bean.CreateTaskBean;
import com.example.healthmanage.ui.activity.memberdetail.response.CreateTaskResponse;
import com.example.healthmanage.ui.activity.memberdetail.response.HealthDataResponse;
import com.example.healthmanage.ui.activity.memberdetail.response.SpiritHealthResponse;
import com.example.healthmanage.ui.activity.mytask.response.HealthTaskDetailResponse;
import com.example.healthmanage.ui.activity.mytask.response.TransferResponse;
import com.example.healthmanage.ui.activity.mytask.response.UpdateTaskResponse;
import com.example.healthmanage.ui.activity.notice.response.NoticeResponse;
import com.example.healthmanage.ui.activity.notice.response.TeamApplyResponse;
import com.example.healthmanage.ui.activity.personalRequest.response.AddRequestBean;
import com.example.healthmanage.ui.activity.personalRequest.response.AddRequestResponse;
import com.example.healthmanage.ui.activity.personalRequest.response.CancelResponse;
import com.example.healthmanage.ui.activity.personalRequest.response.ReplyResponse;
import com.example.healthmanage.ui.activity.personalRequest.response.RequestResponse;
import com.example.healthmanage.ui.activity.qualification.response.CertificateResponse;
import com.example.healthmanage.ui.activity.qualification.response.DepartmentResponse;
import com.example.healthmanage.ui.activity.qualification.response.HospitalResponse;
import com.example.healthmanage.ui.activity.qualification.response.UploadResponse;
import com.example.healthmanage.ui.activity.qualification.response.DoctorInfoResponse;
import com.example.healthmanage.ui.activity.referral.response.InsertReferralResponse;
import com.example.healthmanage.ui.activity.referral.response.ReferralBean;
import com.example.healthmanage.ui.activity.referral.response.ReferralResponse;
import com.example.healthmanage.ui.activity.signmember.response.SignMemberResponse;
import com.example.healthmanage.ui.activity.signmember.response.SignMemberSuccessResponse;
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
import com.example.healthmanage.ui.activity.temperature.InsertPrescriptionBean;
import com.example.healthmanage.ui.activity.temperature.ReceivePatientBean;
import com.example.healthmanage.ui.activity.temperature.response.HealthTaskResponse;
import com.example.healthmanage.ui.activity.temperature.response.InsertResponse;
import com.example.healthmanage.ui.activity.temperature.response.PrescriptionResponse;
import com.example.healthmanage.ui.activity.temperature.response.RefusalResponse;
import com.example.healthmanage.ui.activity.temperature.response.TemperatureResponse;
import com.example.healthmanage.ui.activity.temperature.response.TransferBean;
import com.example.healthmanage.ui.activity.temperature.response.UpdateResponse;
import com.example.healthmanage.ui.activity.vipmanager.VipPostBean;
import com.example.healthmanage.ui.activity.workplan.response.InsertPlanResponse;
import com.example.healthmanage.ui.activity.workplan.response.UpdateWorkResponse;
import com.example.healthmanage.ui.activity.workplan.response.WorkPlanListResponse;
import com.example.healthmanage.ui.fragment.qualification.bean.DoctorInfo;
import com.example.healthmanage.ui.fragment.qualification.bean.UpdateDoctorInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiServer {

    /**
     * 使用密码登录系统
     *
     * @param userName 用户账号
     * @param password 密码
     * @return LoginResponse
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("doctor/appSystemUser/phoneLogin")
    Observable<LoginResponse> loginByPassword(@Field("phone") String userName,
                                              @Field("password") String password);

    /**
     * 获取手机验证码
     *
     * @param phone 接收验证码的手机号码
     * @return Register
     */
    @FormUrlEncoded
    @POST("api/appSms/sendMessage")
    Observable<SmsCodeResponse> getSmsCode(@Field("phone") String phone);

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
    Observable<GeneralResponse> forgetPassword(@Field("phone") String phone,
                                               @Field("password") String newPassword,
                                               @Field("smsCode") String smsCode,
                                               @Field("smsIdentity") String smsIdentity);


    /**
     * 获取职业
     */
    @GET("api/doctorInfo/getPadRole")
    Observable<ProfessionBeanResponse> getJobData();

    /**
     * 获取科室列表
     */
    @GET("api/doctorInfo/getDepartment")
    Observable<DepartmentResponse> getDepartmentListData();

    /**
     * 搜索科室
     * @param name 科室名称
     */
    @FormUrlEncoded
    @POST("api/doctorInfo/getDepartmentByName")
    Observable<DepartmentResponse> getDepartmentByName(@Field("name") String name);

    /**
     * 获取医院列表
     */
    @FormUrlEncoded
    @POST("api/doctorInfo/getHospitalByName")
    Observable<HospitalResponse> getHospitalByName(@Field("name") String name);

    /**
     * 上传图片
     */
    @Multipart
    @POST("api/doctorInfo/uploadIdCard")
    Observable<UploadResponse> uploadIdCard(@Part MultipartBody.Part file);

    /**
     * 资格认证上传
     */
    @Multipart
    @POST("api/doctorInfo/uploadCertificate")
    Observable<CertificateResponse> uploadCertificate(@Part List<MultipartBody.Part> files);

    /**
     * 提交验证接口
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/doctorInfo/saveDoctorInfo")
    Observable<UploadResponse> saveDoctorInfo(@Body DoctorInfo doctorInfo);

    /**
     * 提交会诊任务
     * @param patientInfoBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/appExamineManage/insertPatientExamine")
    Observable<AddPatientInfoResponse> insertPatientExamine(@Body PatientInfoBean patientInfoBean);

    /**
     * 提交转诊
     * @param referralBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/appReferralManage/insertPatientReferral")
    Observable<InsertReferralResponse> insertPatientReferral(@Body ReferralBean referralBean);

    /**
     * 转诊任务列表
     * @param doctorId
     * @param token
     * @return
     */
    @GET("api/appReferralManage/getPatientReferral")
    Observable<ReferralResponse> getPatientReferral(@Query("doctorId") int doctorId,
                                                    @Query("token") String token);

    /**
     * 获取会诊列表
     * @param id
     * @param token
     * @return
     */
    @GET("api/appExamineManage/getPatientExamine")
    Observable<ConsultationListResponse> getPatientExamine(@Query("sysId") int id,
                                                           @Query("status") int status,
                                                           @Query("token") String token);

    /**
     * 更新会诊
     * @param id
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/appExamineManage/updatePatientExamine")
    Observable<AddConsultationPlanResponse> updatePatientExamine(@Field("id") int id,
                                                                 @Field("examinePlan") String examinePlan,
                                                                 @Field("token") String token);

    /**
     * 查看资格认证是否通过
     */
    @FormUrlEncoded
    @POST("api/doctorInfo/getDoctorInfo")
    Observable<DoctorInfoResponse> getDoctorInfo(@Field("token") String token);

    /**
     * 修改资格认证
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/doctorInfo/updateDoctorInfo")
    Observable<UploadResponse> updateDoctorInfo(@Body UpdateDoctorInfo updateDoctorInfo);

    /**
     * 获取健康档案数据
     */
    @FormUrlEncoded
    @POST("api/userInfo/getUserInfo")
    Observable<HealthRecordResponse> getUserInfo(@Field("userId") int userId,
                                                 @Field("token") String token);

    /**
     * 获取健康资料中体检报告
     * @param userId
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/userInfo/checkReportList")
    Observable<CheckReportResponse> checkReportList(@Field("userId") int userId,
                                                    @Field("token") String token);

    /**
     * 获取健康资料中健康评估
     * @param userId
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/userInfo/historyAssessList")
    Observable<HistoryAssessListResponse> historyAssessList(@Field("userId") int userId,
                                                            @Field("token") String token);

    /**
     * 获取病历本数据
     * @param userId
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/userInfo/getMedicalRecordAll")
    Observable<MedicineBookResponse> getMedicalRecordAll(@Field("userId") int userId,
                                                         @Field("token") String token);

    /**
     * 获取健康报告数据
     * @param userId
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/health/getHealthReportList")
    Observable<HealthReportResponse> getHealthReportList(@Field("userId") int userId,
                                                         @Field("date") String date,
                                                         @Field("token") String token);

    /**
     * 提交健康报告
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/health/saveHealthReport")
    Observable<HealthReportConfirmResponse> saveHealthReport(@Body HealthReportInfo healthReportInfo);

    /**
     * 查看健康报告详情
     */
    @FormUrlEncoded
    @POST("api/health/getHealthReport")
    Observable<HealthReportDetailResponse> getHealthReport(@Field("id") int id,
                                                               @Field("token") String token);

    /**
     * 获取工作计划
     * @param time
     * @param doctorId
     * @param token
     * @return
     */
//    @FormUrlEncoded
    @GET("api/appWorkPlanManage/getWorkPlanByTime")
    Observable<WorkPlanListResponse> getWorkPlanByTime(@Query("targetTime") String time,
                                                       @Query("doctorId") int doctorId,
                                                       @Query("token") String token);

    /**
     * 修改工作计划状态
     */
    @FormUrlEncoded
    @POST("api/appWorkPlanManage/updateWorkPlanById")
    Observable<UpdateWorkResponse> updateWorkPlanById(@Field("id") int id,
                                                      @Field("updateTime") String updateTime,
                                                      @Field("token") String token);

    /**
     * 创建工作计划
     * @param workText
     * @param doctorId
     * @param createTime
     * @return
     */
    @FormUrlEncoded
    @POST("api/appWorkPlanManage/insertWorkPlan")
    Observable<InsertPlanResponse> insertWorkPlan(@Field("workText") String workText,
                                                  @Field("doctorId") int doctorId,
                                                  @Field("createTime") String createTime,
                                                  @Field("targetTime") String targetTime,
                                                  @Field("token") String token);

    /**
     * 异常数据
     * @param userId
     * @param token
     * @return
     */
    @GET("api/health/getExceptionData")
    Observable<HealthTaskDetailResponse> getExceptionData(@Query("userId") int userId,
                                                          @Query("token") String token);

    /**
     * 会员签约
     */

    /**
     * 获取会员签约列表
     * @param doctorId
     * @param token
     * @return
     */
//    @FormUrlEncoded
    @GET("doctor/appSystemUser/getSignMember")
    Observable<SignMemberResponse> getSignMemberData(@Query("doctorId") int doctorId,
                                                     @Query("token") String token);

    /**
     * 获取会员未签约列表
     * @param doctorId
     * @param token
     * @return
     */
//    @FormUrlEncoded
    @GET("doctor/appSystemUser/getNotSignMember")
    Observable<SignMemberResponse> getSignNotMemberData(@Query("doctorId") int doctorId,
                                                     @Query("token") String token);

    /**
     * 是否会员签约
     * @param doctorSignStatus
     * @param doctorId
     * @param id
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/addSignMember")
    Observable<SignMemberSuccessResponse> addSignMember(@Field("doctorSignStatus") int doctorSignStatus,
                                                        @Field("doctorId") long doctorId,
                                                        @Field("id") long id,
                                                        @Field("token") String token);

    /**
     * 搜索会员
     *
     * @param phone 手机号
     * @return SearchMemberResponse
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/searchUser")
    Observable<BaseResponse<MyMemberResponse.DataBean>> searchMembers(@Field("phone") String phone);


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
     * @param
     * @param
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("doctor/appSystemUser/selectMember")
    Observable<MyMemberResponse> selectMember(@Body VipPostBean vipPostBean);

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


//    /**
//     * 健康数据 （心率、血压、血糖、运动和睡眠和血糖手表）接口
//     *
//     * @param userId
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/health/healthList")
//    Observable<HealthDataResponse> getHealthList(@Field("userId") String userId);

    /**
     * 今日健康数据
     */
    @FormUrlEncoded
    @POST("api/health/sportWatch")
    Observable<HealthDataResponse> getTodayHealth(@Field("userId") String memberId,
                                                  @Field("token") String token);


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
     * @param doctorId
     * @return
     */
    @FormUrlEncoded
    @POST("api/healthTask/create")
    Observable<GeneralResponse> createMyTask(@Field("userId") long userId,
                                             @Field("title") String title,
                                             @Field("content") String content,
                                             @Field("doctorId") long doctorId,
                                             @Field("token") String token,
                                             @Field("createTime") String createTime,
                                             @Field("queryTime") String queryTime);

    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/healthTask/createHealthTask")
//    Observable<CreateTaskResponse> createHealthTask(@Field("systemUserId") int systemUserId,
//                                                    @Field("userId") int userId,
//                                                    @Field("content") String content);

    Observable<CreateTaskResponse> createHealthTask(@Body CreateTaskBean createTaskBean);


    /**
     * 获取医师列表
     * @param roleId
     * @return
     */
    @GET("api/healthTask/getDoctorList")
    Observable<DoctorListResponse> getDoctorList(@Query("roleId") int roleId,
                                             @Query("token") String token);


    /**
     * 查询异常任务列表
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/healthTask/getTaskList")
    Observable<TaskResponse> loadMyTaskList(@Field("token") String token,
                                            @Field("pageNum") int pageNum,
                                            @Field("pageSize") int pageSize,
                                            @Field("status") int status);

    /**
     * 查询未处理和已处理异常任务
     * @param status
     * @param token
     * @return
     */
    @GET("api/healthTask/getHealthTaskList")
    Observable<HealthTaskResponse> getHealthTaskList(@Query("status") int status,
                                                     @Query("token") String token);

    /**
     * 已转交异常任务
     * @param transferStatus
     * @param token
     * @return
     */
    @GET("api/healthTask/getTransferHealthTaskList")
    Observable<HealthTaskResponse> getTransferHealthTaskList(@Query("transferStatus") int transferStatus,
                                                     @Query("token") String token);

    /**
     * 处理异常
     */
    @GET("api/healthTask/handleHealthTask")
    Observable<UpdateTaskResponse> updateTask(@Query("id") int id,
                                              @Query("token") String token,
                                              @Query("proposal") String proposal);

    /**
     * 异常转交处理
     */
    @GET("api/healthTask/transferHealthTask")
    Observable<TransferResponse> sendTransferTask(@Query("id") int id,
                                          @Query("systemUserId") int systemUserId,
                                          @Query("token") String token);



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
     *.
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
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/appIAQ/getARIList")
    Observable<AirResponse> getAirList(@Field("userId") String memberId,
                                       @Field("token") String token);


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
     * 精神健康
     * @param token
     * @param memberId
     */
    @FormUrlEncoded
    @POST("api/health/sleepData")
    Observable<SpiritHealthResponse> getSpiritList(@Field("userId") String memberId,
                                                   @Field("token") String token);


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

    /**
     * 获取医生列表
     */
    @GET("api/appExamineManage/getDoctorTeamList")
    Observable<DoctorTeamListResponse> getHospitalDepartmentList(@Query("token") String token);

    /**
     * 获取咨询信息列表
     * @param status 0未回复 1已回复 2拒诊
     * @param token
     * @return
     */
    @GET("api/appHealthManage/getHealthConsultStatus")
    Observable<TemperatureResponse> getHealthConsultStatus(@Query("status") int status,
                                                     @Query("token") String token);

    @GET("api/appHealthManage/getHealthConsultTransferStatus")
    Observable<TemperatureResponse> getHealthConsultTransferStatus(@Query("transferStatus") int transferStatus,
                                                           @Query("token") String token);

    /**
     * 转交咨询信息处理
     * @param transferBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/appHealthManage/updateHealthConsult")
    Observable<UpdateResponse> updateHealthConsult(@Body TransferBean transferBean);

    /**
     * 新建药方模版
     * @param insertPrescriptionBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/appHealthManage/insertHealthConsultDrugModel")
    Observable<InsertResponse> insertHealthConsultDrugModel(@Body InsertPrescriptionBean insertPrescriptionBean);

    /**
     * 接诊
     * @param receivePatientBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/appHealthManage/insertHealthConsultDrug")
    Observable<InsertResponse> insertHealthConsultDrug(@Body ReceivePatientBean receivePatientBean);

    /**
     * 获取处方模版
     * @param modelType
     * @return
     */
    @GET("api/appHealthManage/getHealthConsultDrugModel")
    Observable<PrescriptionResponse> getHealthConsultDrugModel(@Query("modelType") int modelType,
                                                               @Query("token") String token);

    /**
     * 拒诊
     */
    @GET("api/appHealthManage/getHealthConsultDrugBack")
    Observable<RefusalResponse> getHealthConsultDrugBack(@Query("id") int id,
                                                         @Query("reason") String reason,
                                                         @Query("token") String token);

    /**
     * 搜索团队成员
     * @param phone
     * @param token
     * @return
     */
    @GET("api/doctorInfo/teamInvitationList")
    Observable<SearchTeamResponse> teamInvitationList(@Query("phone") String phone,
                                                      @Query("token") String token);

    /**
     * 管理师邀请成员
     * @param phone
     * @param token
     * @return
     */
    @GET("api/doctorTeam/getDoctorByPhone")
    Observable<SearchTeamResponse> getDoctorByPhone(@Query("phone") String phone,
                                                      @Query("token") String token);

    /**
     * 发起团队签约
     * @param token
     * @return
     */
    @GET("api/doctorTeam/sendApply")
    Observable<SendResponse> sendApply(@Query("systemUserId") int systemUserId,
                                       @Query("token") String token);


    /**
     * 获取团队签约列表
     * @param token
     * @param status
     * @return
     */
    @GET("api/doctorTeam/doctorTeamApplyList")
    Observable<TeamApplyResponse> getDoctorTeamApplyList(@Query("token") String token,
                                                         @Query("status") int status);

    /**
     * 获取签约通知
     * @param token
     * @return
     */
    @GET("api/doctorTeam/getDoctorTeamApplyList")
    Observable<NoticeResponse> getDoctorTeamApplyNoticeList(@Query("token") String token);

    /**
     * 同意或拒绝签约
     * @param id
     * @param status
     * @param token
     * @return
     */
    @GET("api/doctorTeam/editDoctorTeamApply")
    Observable<SignTeamResponse> signOrNot(@Query("id") int id,
                                           @Query("status") int status,
                                           @Query("token") String token);

    /**
     * 获取团队信息
     * @param token
     * @return
     */
    @GET("api/doctorTeam/getDoctorTeam")
    Observable<DoctorTeamResponse> getDoctorTeam(@Query("token") String token);

    /**
     * 获取团队列表
     * @param token
     * @param roleId
     * @return
     */
    @GET("api/doctorTeam/getDoctorTeamList")
    Observable<TeamMemberResponse> getDoctorTeamList(@Query("token") String token,
                                                     @Query("roleId") int roleId);

    /**
     * 退出团队
     * @param token
     * @return
     */
    @GET("api/doctorTeam/quitTeam")
    Observable<QuitTeamResponse> quitTeam(@Query("token") String token);

    /**业务处理模块*/

    /**
     * 业务处理列表
     * @param token
     * @param status
     * @return
     */
    @GET("api/service/getBusineServiceList")
    Observable<BusinessDealListResponse> getBusineServiceList(@Query("token") String token,
                                                              @Query("status") int status);

    /**
     * 查看业务处理详情
     * @param token
     * @param id
     * @return
     */
    @GET("api/service/getBusineServiceInfo")
    Observable<BusinessDetailResponse> getBusineServiceInfo(@Query("token") String token,
                                                            @Query("id") int id);

    /**
     * 完成任务
     * @param businessDealBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/service/editBusineService")
    Observable<EditResponse> editBusineService(@Body BusinessDealBean businessDealBean);


    /**
     * 委派业务列表
     * @param token
     * @param date
     * @return
     */
    @GET("api/service/getBusineService")
    Observable<DelegateListResponse> getBusineService(@Query("token") String token,
                                                      @Query("date") String date);

    /**
     * 新建委派任务
     * @param delegateBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/service/addBusineService")
    Observable<CreateDelegateResponse> addBusineService(@Body DelegateBean delegateBean);

    /**
     * 个人请求模块
     */

    /**
     * 获取个人请求列表
     * @param token
     * @param status
     * @return
     */
    @GET("api/personal/getPersonalRequestList")
    Observable<RequestResponse> getPersonalRequestList(@Query("token") String token,
                                                       @Query("status") int status);

    /**
     * 创建个人请求
     * @param addRequestBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/personal/addPersonalRequest")
    Observable<AddRequestResponse> addPersonalRequest(@Body AddRequestBean addRequestBean);


    /**
     * 获取个人请求回复
     * @param token
     * @param id
     * @return
     */
    @GET("api/personal/getPersonalRequest")
    Observable<ReplyResponse> getPersonalRequest(@Query("token") String token,
                                                 @Query("id") int id);

    /**
     * 取消请求
     * @param token
     * @param id
     * @return
     */
    @GET("api/personal/cancelPersonalRequest")
    Observable<CancelResponse> cancelPersonalRequest(@Query("token") String token,
                                                     @Query("id") int id);

    /**
     * 书刊板块
     */
    /**
     * 获取学术期刊列表
     * @param token
     * @param status
     * @return
     */
    @GET("api/periodical/getPeriodicalList")
    Observable<PeriodicalListResponse> getPeriodicalList(@Query("token") String token,
                                                         @Query("status")  int status);

    /**
     * 查看学术期刊详情
     * @param token
     * @param id
     * @return
     */
    @GET("api/periodical/getPeriodical")
    Observable<PeriodicalInfoResponse> getPeriodical(@Query("token") String token,
                                                     @Query("id")  int id);

    /**
     * 添加学术期刊
     * @param addPeriodicalBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/periodical/addPeriodical")
    Observable<AddOrEditSucceedResponse> addPeriodical(@Body AddPeriodicalBean addPeriodicalBean);

    /**
     * 修改学术期刊
     * @param editPeriodicalBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/periodical/editPeriodical")
    Observable<AddOrEditSucceedResponse> editPeriodical(@Body EditPeriodicalBean editPeriodicalBean);

}

