package com.example.healthmanage.data.network;


import com.example.healthmanage.bean.ProfessionBeanResponse;
import com.example.healthmanage.bean.network.response.AbnormalDataResponse;
import com.example.healthmanage.bean.network.response.AirResponse;
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
import com.example.healthmanage.ui.activity.appraise.response.AppraiseListResponse;
import com.example.healthmanage.ui.activity.consultation.response.AddConsultationPlanResponse;
import com.example.healthmanage.ui.activity.consultation.response.AddPatientInfoResponse;
import com.example.healthmanage.ui.activity.consultation.response.ConsultationListResponse;
import com.example.healthmanage.ui.activity.consultation.response.DoctorTeamListResponse;
import com.example.healthmanage.ui.activity.consultation.response.PatientInfoBean;
import com.example.healthmanage.ui.activity.delegate.response.CreateDelegateResponse;
import com.example.healthmanage.ui.activity.delegate.response.DelegateBean;
import com.example.healthmanage.ui.activity.delegate.response.DelegateListResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.AppraiseResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.DepartMentResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.FamousDoctorInfoResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.FamousDoctorListResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.HospitalDetailResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.HospitalListResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.HospitalTypeResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.CheckReportResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.HealthRecordResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.HistoryAssessListResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.MedicineBookResponse;
import com.example.healthmanage.ui.activity.healthreport.HealthReportInfo;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportConfirmResponse;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportDetailResponse;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportResponse;
import com.example.healthmanage.ui.activity.integral.response.AddressResponse;
import com.example.healthmanage.ui.activity.integral.response.ConfirmReceiptResponse;
import com.example.healthmanage.ui.activity.integral.response.ExchangeGoodsBean;
import com.example.healthmanage.ui.activity.integral.response.ExchangeIntegralResponse;
import com.example.healthmanage.ui.activity.integral.response.GoodsDetailResponse;
import com.example.healthmanage.ui.activity.integral.response.GoodsListResponse;
import com.example.healthmanage.ui.activity.integral.response.IntegralDetailResponse;
import com.example.healthmanage.ui.activity.integral.response.IntegralResponse;
import com.example.healthmanage.ui.activity.integral.response.IntegralRuleResponse;
import com.example.healthmanage.ui.activity.integral.response.LogisticResponse;
import com.example.healthmanage.ui.activity.integral.response.OrderInfoResponse;
import com.example.healthmanage.ui.activity.integral.response.OrderListResponse;
import com.example.healthmanage.ui.activity.invitemember.response.InviteSucceedResponse;
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
import com.example.healthmanage.ui.activity.vipmanager.response.DeleteMemberResponse;
import com.example.healthmanage.ui.activity.vipmanager.response.InviteMemberResponse;
import com.example.healthmanage.ui.activity.vipmanager.response.IsFocusResponse;
import com.example.healthmanage.ui.activity.vipmanager.response.MemberTeamListResponse;
import com.example.healthmanage.ui.activity.workplan.response.InsertPlanResponse;
import com.example.healthmanage.ui.activity.workplan.response.UpdateWorkResponse;
import com.example.healthmanage.ui.activity.workplan.response.WorkPlanListResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.BookArticleResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.BookListResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.BookMenuResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.SubscribeResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.YearResponse;
import com.example.healthmanage.ui.fragment.qualification.bean.DoctorInfo;
import com.example.healthmanage.ui.fragment.qualification.bean.UpdateDoctorInfo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiServer {

    /**
     * ????????????????????????
     *
     * @param userName ????????????
     * @param password ??????
     * @return LoginResponse
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("doctor/appSystemUser/phoneLogin")
    Observable<LoginResponse> loginByPassword(@Field("phone") String userName,
                                              @Field("password") String password);

    /**
     * ?????????????????????
     *
     * @param phone ??????????????????????????????
     * @return Register
     */
    @FormUrlEncoded
    @POST("api/appSms/sendMessage")
    Observable<SmsCodeResponse> getSmsCode(@Field("phone") String phone);

    /**
     * ????????????
     *
     * @param phone       ?????????
     * @param password    ??????
     * @param smsCode     ?????????
     * @param smsIdentity ??????????????????????????????
     * @return Response
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/register")
    Observable<GeneralResponse> register(@Field("phone") String phone,
                                         @Field("password") String password,
                                         @Field("smsCode") String smsCode,
                                         @Field("smsIdentity") String smsIdentity);

    /**
     * ????????????
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
     * ????????????
     */
    @GET("api/doctorInfo/getPadRole")
    Observable<ProfessionBeanResponse> getJobData();

    /**
     * ??????????????????
     */
    @GET("api/doctorInfo/getDepartment")
    Observable<DepartmentResponse> getDepartmentListData();

    /**
     * ????????????
     * @param name ????????????
     */
    @FormUrlEncoded
    @POST("api/doctorInfo/getDepartmentByName")
    Observable<DepartmentResponse> getDepartmentByName(@Field("name") String name);

    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("api/doctorInfo/getHospitalByName")
    Observable<HospitalResponse> getHospitalByName(@Field("name") String name);

    /**
     * ????????????
     */
    @Multipart
    @POST("api/doctorInfo/uploadIdCard")
    Observable<UploadResponse> uploadIdCard(@Part MultipartBody.Part file);

    /**
     * ??????????????????
     */
    @Multipart
    @POST("api/doctorInfo/uploadCertificate")
    Observable<CertificateResponse> uploadCertificate(@Part List<MultipartBody.Part> files);

    /**
     * ??????????????????
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/doctorInfo/saveDoctorInfo")
    Observable<UploadResponse> saveDoctorInfo(@Body DoctorInfo doctorInfo);

    /**
     * ??????????????????
     * @param patientInfoBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/appExamineManage/insertPatientExamine")
    Observable<AddPatientInfoResponse> insertPatientExamine(@Body PatientInfoBean patientInfoBean);

    /**
     * ????????????
     * @param referralBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/appReferralManage/insertPatientReferral")
    Observable<InsertReferralResponse> insertPatientReferral(@Body ReferralBean referralBean);

    /**
     * ??????????????????
     * @param doctorId
     * @param token
     * @return
     */
    @GET("api/appReferralManage/getPatientReferral")
    Observable<ReferralResponse> getPatientReferral(@Query("doctorId") int doctorId,
                                                    @Query("token") String token);

    /**
     * ??????????????????
     * @param id
     * @param token
     * @return
     */
    @GET("api/appExamineManage/getPatientExamine")
    Observable<ConsultationListResponse> getPatientExamine(@Query("sysId") int id,
                                                           @Query("status") int status,
                                                           @Query("token") String token);

    /**
     * ????????????
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
     * ??????????????????????????????
     */
    @FormUrlEncoded
    @POST("api/doctorInfo/getDoctorInfo")
    Observable<DoctorInfoResponse> getDoctorInfo(@Field("token") String token);

    /**
     * ??????????????????
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/doctorInfo/updateDoctorInfo")
    Observable<UploadResponse> updateDoctorInfo(@Body UpdateDoctorInfo updateDoctorInfo);

    /**
     * ????????????????????????
     */
    @FormUrlEncoded
    @POST("api/userInfo/getUserInfo")
    Observable<HealthRecordResponse> getUserInfo(@Field("userId") int userId,
                                                 @Field("token") String token);

    /**
     * ?????????????????????????????????
     * @param userId
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/userInfo/checkReportList")
    Observable<CheckReportResponse> checkReportList(@Field("userId") int userId,
                                                    @Field("token") String token);

    /**
     * ?????????????????????????????????
     * @param userId
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/userInfo/historyAssessList")
    Observable<HistoryAssessListResponse> historyAssessList(@Field("userId") int userId,
                                                            @Field("token") String token);

    /**
     * ?????????????????????
     * @param userId
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/userInfo/getMedicalRecordAll")
    Observable<MedicineBookResponse> getMedicalRecordAll(@Field("userId") int userId,
                                                         @Field("token") String token);

    /**
     * ????????????????????????
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
     * ??????????????????
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/health/saveHealthReport")
    Observable<HealthReportConfirmResponse> saveHealthReport(@Body HealthReportInfo healthReportInfo);

    /**
     * ????????????????????????
     */
    @FormUrlEncoded
    @POST("api/health/getHealthReport")
    Observable<HealthReportDetailResponse> getHealthReport(@Field("id") int id,
                                                               @Field("token") String token);

    /**
     * ??????????????????
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
     * ????????????????????????
     */
    @FormUrlEncoded
    @POST("api/appWorkPlanManage/updateWorkPlanById")
    Observable<UpdateWorkResponse> updateWorkPlanById(@Field("id") int id,
                                                      @Field("updateTime") String updateTime,
                                                      @Field("token") String token);

    /**
     * ??????????????????
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
     * ????????????
     * @param userId
     * @param token
     * @return
     */
    @GET("api/health/getExceptionData")
    Observable<HealthTaskDetailResponse> getExceptionData(@Query("userId") int userId,
                                                          @Query("token") String token);

    /**
     * ????????????
     */

    /**
     * ????????????????????????
     * @param doctorId
     * @param token
     * @return
     */
//    @FormUrlEncoded
    @GET("doctor/appSystemUser/getSignMember")
    Observable<SignMemberResponse> getSignMemberData(@Query("doctorId") int doctorId,
                                                     @Query("token") String token);

    /**
     * ???????????????????????????
     * @param doctorId
     * @param token
     * @return
     */
//    @FormUrlEncoded
    @GET("doctor/appSystemUser/getNotSignMember")
    Observable<SignMemberResponse> getSignNotMemberData(@Query("doctorId") int doctorId,
                                                     @Query("token") String token);

    /**
     * ??????????????????
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
     * ????????????
     *
     * @param phone ?????????
     * @return
     */
    @GET("api/memberTeam/getUserByPhone")
    Observable<InviteMemberResponse> searchMembers(@Query("token") String token,
                                                   @Query("phone") String phone);


    /**
     * ????????????
     * @param token
     * @param userId
     * @return
     */
    @GET("api/memberTeam/inviteUserMember")
    Observable<InviteSucceedResponse> inviteUserMember(@Query("token") String token,
                                                       @Query("userId") int userId);


    /**
     * ????????????????????????????????????
     * @param token
     * @param nameOrPhone
     * @param status
     * @return
     */
    @GET("api/memberTeam/getMemberTeamByName")
    Observable<MemberTeamListResponse> getMemberTeamByName(@Query("token") String token,
                                                         @Query("nameOrPhone") String nameOrPhone,
                                                         @Query("status") int status);

    /**
     * ????????????
     *
     * @param sysId
     * @return MyMemberResponse
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/doctorFollow")
    Observable<MyMemberResponse> loadMyFocus(@Field("sysId") String sysId);


    /**
     * ????????????
     *
     * @param sysId
     * @return MyMemberResponse
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/doctorMember")
    Observable<MyMemberResponse> loadMyMembers(@Field("sysId") String sysId);

    //??????????????????

    /**
     * ????????????
     * @param token
     * @param ranks
     * @return
     */
    @GET("api/memberTeam/getMemberTeamList")
    Observable<MemberTeamListResponse> getMemberTeamList(@Query("token") String token,
                                                         @Query("ranks") String ranks,
                                                         @Query("status") int status);

    /**
     * ?????????????????????
     * 0=????????? 1=??????
     * @param token
     * @param id
     * @param status
     * @return
     */
    @GET("api/memberTeam/editMemberTeam")
    Observable<IsFocusResponse> editMemberTeam(@Query("token") String token,
                                               @Query("id") int id,
                                               @Query("status") int status);

    /**
     * ????????????
     * @param token
     * @param id
     * @return
     */
    @GET("api/memberTeam/deleteMemberTeam")
    Observable<DeleteMemberResponse> deleteMemberTeam(@Query("token") String token,
                                                      @Query("id") int id);

    /**
     * ????????????????????????
     *
     * @param
     * @param
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("doctor/appSystemUser/selectMember")
    Observable<MyMemberResponse> selectMember(@Body VipPostBean vipPostBean);

    /**
     * ????????????
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
     * ????????????
     *
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/deleteFollow")
    Observable<GeneralResponse> deleteFocus(@Field("userId") String userId);


    /**
     * ??????????????????
     *
     * @param cityCode
     * @return
     */
    @FormUrlEncoded
    @POST("api/weather/getRealtimeWeatherInfo")
    Observable<WeatherResponse> getWeather(@Field("city_code") String cityCode);


//    /**
//     * ???????????? ?????????????????????????????????????????????????????????????????????
//     *
//     * @param userId
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/health/healthList")
//    Observable<HealthDataResponse> getHealthList(@Field("userId") String userId);

    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("api/health/sportWatch")
    Observable<HealthDataResponse> getTodayHealth(@Field("userId") String memberId,
                                                  @Field("token") String token);


    /**
     * ?????????????????????
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
     * ??????????????????
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
     * ??????????????????
     * @param roleId
     * @return
     */
    @GET("api/healthTask/getDoctorList")
    Observable<DoctorListResponse> getDoctorList(@Query("roleId") int roleId,
                                             @Query("token") String token);


    /**
     * ????????????????????????
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
     * ???????????????????????????????????????
     * @param status
     * @param token
     * @return
     */
    @GET("api/healthTask/getHealthTaskList")
    Observable<HealthTaskResponse> getHealthTaskList(@Query("status") int status,
                                                     @Query("token") String token);

    /**
     * ?????????????????????
     * @param transferStatus
     * @param token
     * @return
     */
    @GET("api/healthTask/getTransferHealthTaskList")
    Observable<HealthTaskResponse> getTransferHealthTaskList(@Query("transferStatus") int transferStatus,
                                                     @Query("token") String token);

    /**
     * ????????????
     */
    @GET("api/healthTask/handleHealthTask")
    Observable<UpdateTaskResponse> updateTask(@Query("id") int id,
                                              @Query("token") String token,
                                              @Query("proposal") String proposal);

    /**
     * ??????????????????
     */
    @GET("api/healthTask/transferHealthTask")
    Observable<TransferResponse> sendTransferTask(@Query("id") int id,
                                          @Query("systemUserId") int systemUserId,
                                          @Query("token") String token);



    /**
     * ??????????????????
     *
     * @param taskId
     * @return
     */
    @FormUrlEncoded
    @POST("api/healthTask/getTask")
    Observable<TaskDetailResponse> loadMyTaskDetail(@Field("id") long taskId);


    /**
     * ???????????????????????????
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
     * ??????????????????
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
     * ????????????????????????
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
     * ??????????????????
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
     * ????????????
     *
     * @param doctorName
     * @return
     */
    @FormUrlEncoded
    @POST("api/healthTask/getDoctorByName")
    Observable<DoctorListResponse> searchDoctor(@Field("name") String doctorName);


    /**
     * ??????????????????
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
     * ??????????????????
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
     * ??????????????????
     *
     * @param memberId
     * @return
     */
    @FormUrlEncoded
    @POST("doctor/appSystemUser/userInfo")
    Observable<MemberInfoResponse> getMemberInfo(@Field("userId") long memberId,
                                                 @Field("token") String token);


    /**
     * ????????????????????????
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
     * ??????????????????
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
     * ?????????????????????
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
     * ???????????????
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/generate/code")
    Observable<GeneralResponse> getInviteCode(@Field("token") String token);


    /**
     * ?????????????????????
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
     * ????????????
     * @param token
     * @param memberId
     */
    @FormUrlEncoded
    @POST("api/health/sleepData")
    Observable<SpiritHealthResponse> getSpiritList(@Field("userId") String memberId,
                                                   @Field("token") String token);


    /**
     * ???????????????
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
     * ??????????????????
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
     * ??????????????????
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
     * ??????????????????
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
     * ??????????????????
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
     * ??????????????????
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
     * ??????????????????
     */
    @GET("api/appExamineManage/getDoctorTeamList")
    Observable<DoctorTeamListResponse> getHospitalDepartmentList(@Query("token") String token);

    /**
     * ????????????????????????
     * @param status 0????????? 1????????? 2??????
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
     * ????????????????????????
     * @param transferBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/appHealthManage/updateHealthConsult")
    Observable<UpdateResponse> updateHealthConsult(@Body TransferBean transferBean);

    /**
     * ??????????????????
     * @param insertPrescriptionBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/appHealthManage/insertHealthConsultDrugModel")
    Observable<InsertResponse> insertHealthConsultDrugModel(@Body InsertPrescriptionBean insertPrescriptionBean);

    /**
     * ??????
     * @param receivePatientBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/appHealthManage/insertHealthConsultDrug")
    Observable<InsertResponse> insertHealthConsultDrug(@Body ReceivePatientBean receivePatientBean);

    /**
     * ??????????????????
     * @param modelType
     * @return
     */
    @GET("api/appHealthManage/getHealthConsultDrugModel")
    Observable<PrescriptionResponse> getHealthConsultDrugModel(@Query("modelType") int modelType,
                                                               @Query("token") String token);

    /**
     * ??????
     */
    @GET("api/appHealthManage/getHealthConsultDrugBack")
    Observable<RefusalResponse> getHealthConsultDrugBack(@Query("id") int id,
                                                         @Query("reason") String reason,
                                                         @Query("token") String token);

    /**
     * ??????????????????
     * @param phone
     * @param token
     * @return
     */
    @GET("api/doctorInfo/teamInvitationList")
    Observable<SearchTeamResponse> teamInvitationList(@Query("phone") String phone,
                                                      @Query("token") String token);

    /**
     * ?????????????????????
     * @param phone
     * @param token
     * @return
     */
    @GET("api/doctorTeam/getDoctorByPhone")
    Observable<SearchTeamResponse> getDoctorByPhone(@Query("phone") String phone,
                                                      @Query("token") String token);

    /**
     * ??????????????????
     * @param token
     * @return
     */
    @GET("api/doctorTeam/sendApply")
    Observable<SendResponse> sendApply(@Query("systemUserId") int systemUserId,
                                       @Query("token") String token);


    /**
     * ????????????????????????
     * @param token
     * @param status
     * @return
     */
    @GET("api/doctorTeam/doctorTeamApplyList")
    Observable<TeamApplyResponse> getDoctorTeamApplyList(@Query("token") String token,
                                                         @Query("status") int status);

    /**
     * ??????????????????
     * @param token
     * @return
     */
    @GET("api/doctorTeam/getDoctorTeamApplyList")
    Observable<NoticeResponse> getDoctorTeamApplyNoticeList(@Query("token") String token);

    /**
     * ?????????????????????
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
     * ??????????????????
     * @param token
     * @return
     */
    @GET("api/doctorTeam/getDoctorTeam")
    Observable<DoctorTeamResponse> getDoctorTeam(@Query("token") String token);

    /**
     * ??????????????????
     * @param token
     * @param roleId
     * @return
     */
    @GET("api/doctorTeam/getDoctorTeamList")
    Observable<TeamMemberResponse> getDoctorTeamList(@Query("token") String token,
                                                     @Query("roleId") int roleId);

    /**
     * ????????????
     * @param token
     * @return
     */
    @GET("api/doctorTeam/quitTeam")
    Observable<QuitTeamResponse> quitTeam(@Query("token") String token);

    /**??????????????????*/

    /**
     * ??????????????????
     * @param token
     * @param status
     * @return
     */
    @GET("api/service/getBusineServiceList")
    Observable<BusinessDealListResponse> getBusineServiceList(@Query("token") String token,
                                                              @Query("status") int status);

    /**
     * ????????????????????????
     * @param token
     * @param id
     * @return
     */
    @GET("api/service/getBusineServiceInfo")
    Observable<BusinessDetailResponse> getBusineServiceInfo(@Query("token") String token,
                                                            @Query("id") int id);

    /**
     * ????????????
     * @param businessDealBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/service/editBusineService")
    Observable<EditResponse> editBusineService(@Body BusinessDealBean businessDealBean);


    /**
     * ??????????????????
     * @param token
     * @param date
     * @return
     */
    @GET("api/service/getBusineService")
    Observable<DelegateListResponse> getBusineService(@Query("token") String token,
                                                      @Query("date") String date);

    /**
     * ??????????????????
     * @param delegateBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/service/addBusineService")
    Observable<CreateDelegateResponse> addBusineService(@Body DelegateBean delegateBean);

    /**
     * ??????????????????
     */

    /**
     * ????????????????????????
     * @param token
     * @param status
     * @return
     */
    @GET("api/personal/getPersonalRequestList")
    Observable<RequestResponse> getPersonalRequestList(@Query("token") String token,
                                                       @Query("status") int status);

    /**
     * ??????????????????
     * @param addRequestBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/personal/addPersonalRequest")
    Observable<AddRequestResponse> addPersonalRequest(@Body AddRequestBean addRequestBean);


    /**
     * ????????????????????????
     * @param token
     * @param id
     * @return
     */
    @GET("api/personal/getPersonalRequest")
    Observable<ReplyResponse> getPersonalRequest(@Query("token") String token,
                                                 @Query("id") int id);

    /**
     * ????????????
     * @param token
     * @param id
     * @return
     */
    @GET("api/personal/cancelPersonalRequest")
    Observable<CancelResponse> cancelPersonalRequest(@Query("token") String token,
                                                     @Query("id") int id);

    /**
     * ????????????
     */
    /**
     * ????????????????????????
     * @param token
     * @param status
     * @return
     */
    @GET("api/periodical/getPeriodicalList")
    Observable<PeriodicalListResponse> getPeriodicalList(@Query("token") String token,
                                                         @Query("status")  int status);

    /**
     * ????????????????????????
     * @param token
     * @param id
     * @return
     */
    @GET("api/periodical/getPeriodical")
    Observable<PeriodicalInfoResponse> getPeriodical(@Query("token") String token,
                                                     @Query("id")  int id);

    /**
     * ??????????????????
     * @param addPeriodicalBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/periodical/addPeriodical")
    Observable<AddOrEditSucceedResponse> addPeriodical(@Body AddPeriodicalBean addPeriodicalBean);

    /**
     * ??????????????????
     * @param editPeriodicalBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/periodical/editPeriodical")
    Observable<AddOrEditSucceedResponse> editPeriodical(@Body EditPeriodicalBean editPeriodicalBean);

    //?????????

    /**
     * ??????????????????
     * @param token
     * @return
     */
    @GET("api/famousDoctorHall/getHospitalDepartment")
    Observable<DepartMentResponse> getHospitalDepartment(@Query("token") String token);

    /**
     * ????????????
     * @return
     */
    @GET("api/famousDoctorHall/getDoctorList")
    Observable<FamousDoctorListResponse> getDoctorList(@Query("nameOrHospital") String nameOrHospital,
                                                       @Query("addr") String addr,
                                                       @Query("departmentId") String departmentId,
                                                       @Query("rank") String rank,
                                                       @Query("grade") String grade,
                                                       @Query("token") String token);

    /**
     * ????????????
     * @param nameOrHospital
     * @param address
     * @param typeId
     * @param token
     * @return
     */
    @GET("api/famousDoctorHall/getHospitalList")
    Observable<HospitalListResponse> getHospitalList(@Query("nameOrHospital") String nameOrHospital,
                                                     @Query("address") String address,
                                                     @Query("typeId") String typeId,
                                                     @Query("token") String token);

    /**
     * ??????????????????
     * @param token
     * @return
     */
    @GET("api/famousDoctorHall/getHospitalTypeList")
    Observable<HospitalTypeResponse> getHospitalTypeList(@Query("token") String token);

    /**
     * ??????????????????
     * @param hospitalId
     * @param token
     * @return
     */
    @GET("api/famousDoctorHall/getHospitalById")
    Observable<HospitalDetailResponse> getHospitalById(@Query("hospitalId") int hospitalId,
                                                       @Query("token") String token);

    /**
     * ????????????id??????????????????
     * @param nameOrDepartment
     * @param departmentId
     * @param rank
     * @param grade
     * @param hospitalId
     * @param token
     * @return
     */
    @GET("api/famousDoctorHall/getDoctorByHospitalId")
    Observable<FamousDoctorListResponse> getDoctorByHospitalId(@Query("nameOrDepartment") String nameOrDepartment,
                                                               @Query("departmentId") String departmentId,
                                                               @Query("rank") String rank,
                                                               @Query("grade") String grade,
                                                               @Query("hospitalId") int hospitalId,
                                                               @Query("token") String token);

    /**
     * ??????????????????
     * @param systemUserId
     * @param token
     * @return
     */
    @GET("api/famousDoctorHall/getDoctorInfo")
    Observable<FamousDoctorInfoResponse> getDoctorInfo(@Query("systemUserId") int systemUserId,
                                                       @Query("token") String token);

    /**
     * ????????????
     * @param systemUserId
     * @param token
     * @return
     */
    @GET("api/famousDoctorHall/appraiseList")
    Observable<AppraiseResponse> getAppraiseList(@Query("systemUserId") int systemUserId,
                                                 @Query("token") String token);

    /**
     * ??????
     */
    /**
     * ????????????
     * @param token
     * @return
     */
    @GET("api/journal/getYearList")
    Observable<YearResponse> getYearList(@Query("token") String token);

    /**
     * ????????????????????????
     * @param year
     * @param token
     * @return
     */
    @GET("api/journal/getBookListByYear")
    Observable<BookListResponse> getBookListByYear(@Query("year") String year,
                                                   @Query("token") String token);

    /**
     * ????????????
     * @param bookId
     * @param token
     * @return
     */
    @GET("api/journal/subscribeBook")
    Observable<SubscribeResponse> subscribeBook(@Query("bookId") String bookId,
                                                @Query("token") String token);

    /**
     * ??????????????????
     * @param bookId
     * @param token
     * @return
     */
    @GET("api/journal/cancelSubscribeBook")
    Observable<SubscribeResponse> cancelSubscribeBook(@Query("bookId") String bookId,
                                                @Query("token") String token);

    /**
     * ????????????????????????
     * @param id
     * @param token
     * @return
     */
    @GET("api/journal/getBookArticle")
    Observable<BookArticleResponse> getBookArticle(@Query("id") String id,
                                                   @Query("token") String token);

    /**
     * ??????????????????
     * @param bookId
     * @param token
     * @return
     */
    @GET("api/journal/getBookCatalogList")
    Observable<BookMenuResponse> getBookCatalogList(@Query("bookId") String bookId,
                                                  @Query("token") String token);

    /**
     * ????????????
     */
    /**
     * ????????????????????????
     * @param token
     * @return
     */
    @GET("api/integral/getIntegralRule")
    Observable<IntegralRuleResponse> getIntegralRule(@Query("token") String token);

    /**
     * ????????????
     * @param token
     * @return
     */
    @GET("api/integral/getIntegral")
    Observable<IntegralResponse> getIntegral(@Query("token") String token);

    /**
     * ????????????
     * @param point
     * @param token
     * @return
     */
    @GET("api/integral/getGoodsList")
    Observable<GoodsListResponse> getGoodsList(@Query("point") String point,
                                               @Query("token") String token);

    /**
     * ????????????
     * @param id
     * @param token
     * @return
     */
    @GET("api/integral/getGoodsInfo")
    Observable<GoodsDetailResponse> getGoodsInfo(@Query("id") int id,
                                                 @Query("token") String token);

    /**
     * ????????????
     * @param type
     * @param token
     * @param date
     * @return
     */
    @GET("api/integral/getIntegralDetail")
    Observable<IntegralDetailResponse> getIntegralDetail(@Query("type") int type,
                                                         @Query("token") String token,
                                                         @Query("date") String date);

    /**
     * ????????????????????????
     * @param status
     * @param token
     * @return
     */
    @GET("api/integral/getOrderList")
    Observable<OrderListResponse> getOrderList(@Query("status") String status,
                                               @Query("token") String token);

    /**
     * ??????????????????
     * @param token
     * @return
     */
    @GET("/cj/ysk-location/queryOneByUserId")
    Observable<AddressResponse> queryOneByUserId(@Header("token") String token);

    /**
     * ????????????
     * @param exchangeGoodsBean
     * @return
     */
    @Headers("Content-Type:application/json; charset=utf-8")
    @POST("api/integral/exchangeGoods")
    Observable<ExchangeIntegralResponse> exchangeGoods(@Body ExchangeGoodsBean exchangeGoodsBean);

    /**
     * ??????????????????
     * @param id
     * @param token
     * @return
     */
    @GET("api/integral/getOrderInfo")
    Observable<OrderInfoResponse> getOrderInfo(@Query("id") int id,
                                               @Query("token") String token);

    /**
     * ??????????????????
     * @param courierNumber
     * @param courierCompanyAbbr
     * @param token
     * @return
     */
    @GET("api/integral/getLogistics")
    Observable<LogisticResponse> getLogistics(@Query("courierNumber") String courierNumber,
                                              @Query("courierCompanyAbbr") String courierCompanyAbbr,
                                              @Query("token") String token);

    /**
     * ????????????
     * @param id
     * @param token
     * @return
     */
    @GET("/api/integral/confirmReceipt")
    Observable<ConfirmReceiptResponse> confirmReceipt(@Query("id") int id,
                                                      @Query("token") String token);

    /**
     * ????????????
     */
    @GET("api/doctorAppraiseSys/appraiseList")
    Observable<AppraiseListResponse> appraiseList(@Query("token") String token);

}

