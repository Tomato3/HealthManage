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
import com.example.healthmanage.ui.activity.consultation.response.AddConsultationPlanResponse;
import com.example.healthmanage.ui.activity.consultation.response.AddPatientInfoResponse;
import com.example.healthmanage.ui.activity.consultation.response.ConsultationListResponse;
import com.example.healthmanage.ui.activity.consultation.response.DoctorTeamListResponse;
import com.example.healthmanage.ui.activity.consultation.response.PatientInfoBean;
import com.example.healthmanage.ui.activity.delegate.response.CreateDelegateResponse;
import com.example.healthmanage.ui.activity.delegate.response.DelegateBean;
import com.example.healthmanage.ui.activity.delegate.response.DelegateListResponse;
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
import com.example.healthmanage.utils.ChartUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Query;

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
    public Observable<LoginResponse> loginByPassword(String userName, String password) {
        return getApiServer().loginByPassword(userName, password);
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
     * 获取职业
     */
    public Observable<ProfessionBeanResponse> getJobDataList(){
        return getApiServer().getJobData();
    }

    /**
     * 获取科室
     */
    public Observable<DepartmentResponse> getDepartmentDataList(){
        return getApiServer().getDepartmentListData();
    }

    /**
     * 搜索科室
     * @param name 科室名称
     */
    public Observable<DepartmentResponse> getDepartmentByName(String name){
        return getApiServer().getDepartmentByName(name);
    }

    /**
     * 搜索医院
     * @param name 医院名称
     */
    public Observable<HospitalResponse> getHospitalByName(String name){
        return getApiServer().getHospitalByName(name);
    }

    /**
     * 上传图片
     */
    public Observable<UploadResponse> uploadIdCard(File file){
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", ChartUtils.encodeHeadInfo(file.getName()), requestFile);
        return getApiServer().uploadIdCard(body);
    }

    /**
     * 资质认证上传
     */
    public Observable<CertificateResponse> uploadCertificate(List<File> files){
        List<MultipartBody.Part> bodes = new ArrayList<>();
        for (File file : files) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", ChartUtils.encodeHeadInfo(file.getName()), requestFile);
            bodes.add(body);
        }
        return getApiServer().uploadCertificate(bodes);
    }

    /**
     * 提交认证
     */
    public Observable<UploadResponse> saveDoctorInfo(DoctorInfo doctorInfo){
        return getApiServer().saveDoctorInfo(doctorInfo);
    }

    /**
     * 提交会诊任务
     * @param patientInfoBean
     * @return
     */
    public Observable<AddPatientInfoResponse> insertPatientExamine(PatientInfoBean patientInfoBean){
        return getApiServer().insertPatientExamine(patientInfoBean);
    }

    /**
     * 提交转诊
     * @param referralBean
     * @return
     */
    public Observable<InsertReferralResponse> insertPatientReferral(ReferralBean referralBean){
        return getApiServer().insertPatientReferral(referralBean);
    }

    /**
     * 获取转诊任务列表
     * @param doctorId
     * @param token
     * @return
     */
    public Observable<ReferralResponse> getPatientReferral(int doctorId,String token){
        return getApiServer().getPatientReferral(doctorId, token);
    }

    /**
     * 获取会诊列表
     * @param id
     * @param token
     * @return
     */
    public Observable<ConsultationListResponse> getPatientExamine(int id,int status,String token){
        return getApiServer().getPatientExamine(id,status,token);
    }

    /**
     * 提交诊疗方案
     * @param id
     * @param examinePlan
     * @param token
     * @return
     */
    public Observable<AddConsultationPlanResponse> updatePatientExamine(int id,String examinePlan,String token){
        return getApiServer().updatePatientExamine(id, examinePlan, token);
    }

    /**
     * 查看资格认证审核是否通过
     */
    public Observable<DoctorInfoResponse> getDoctorInfo(String token){
        return getApiServer().getDoctorInfo(token);
    }

    /**
     * 更新认证
     */
    public Observable<UploadResponse> updateDoctorInfo(UpdateDoctorInfo updateDoctorInfo){
        return getApiServer().updateDoctorInfo(updateDoctorInfo);
    }

    /**
     * 获取健康档案数据
     */
    public Observable<HealthRecordResponse> getUserInfo(int userId, String token){
        return getApiServer().getUserInfo(userId,token);
    }

    /**
     * 获取健康数据中体检报告list
     * @param userId
     * @param token
     * @return
     */
    public Observable<CheckReportResponse> checkReportList(int userId,String token){
        return getApiServer().checkReportList(userId,token);
    }

    /**
     * 获取健康数据中健康评估List
     * @param userId
     * @param token
     * @return
     */
    public Observable<HistoryAssessListResponse> historyAssessList(int userId, String token){
        return getApiServer().historyAssessList(userId,token);
    }

    /**
     * 获取健康数据中病历本数据
     * @param userId
     * @param token
     * @return
     */
    public Observable<MedicineBookResponse> getMedicalRecordAll(int userId, String token){
        return getApiServer().getMedicalRecordAll(userId,token);
    }

    /**
     * 获取健康报告数据
     * @param userId
     * @param token
     * @return
     */
    public Observable<HealthReportResponse> getHealthReportList(int userId, String date, String token){
        return getApiServer().getHealthReportList(userId,date,token);
    }

    /**
     * 提交健康报告
     */
    public Observable<HealthReportConfirmResponse> saveHealthReport(HealthReportInfo healthReportInfo){
        return getApiServer().saveHealthReport(healthReportInfo);
    }

    /**
     * 获取健康报告详情
     * @param id
     * @param token
     * @return
     */
    public Observable<HealthReportDetailResponse> getHealthReport(int id,String token){
        return getApiServer().getHealthReport(id,token);
    }

    /**
     * 获取签约用户
     * @param doctorId
     * @param token
     * @return
     */
    public Observable<SignMemberResponse> getSignMemberData(int doctorId,String token){
        return getApiServer().getSignMemberData(doctorId,token);
    }

    /**
     * 获取未签约用户
     * @param doctorId
     * @param token
     * @return
     */
    public Observable<SignMemberResponse> getNotSignMemberData(int doctorId,String token){
        return getApiServer().getSignNotMemberData(doctorId,token);
    }

    /**
     * 是否进行会员签约
     * @param doctorSignStatus
     * @param doctorId
     * @param id
     * @param token
     * @return
     */
    public Observable<SignMemberSuccessResponse> addSignMember(int doctorSignStatus,long doctorId,long id,String token){
        return getApiServer().addSignMember(doctorSignStatus,doctorId,id,token);
    }

    /**
     * 获取工作计划
     */
    public Observable<WorkPlanListResponse> getWorkPlanByTime(String time,int sysId,String token){
        return getApiServer().getWorkPlanByTime(time,sysId,token);
    }

    /**
     * 更改工作计划状态
     */
    public Observable<UpdateWorkResponse> updateWorkPlanById(int id,String updateTime,String token){
        return getApiServer().updateWorkPlanById(id, updateTime, token);
    }

    /**
     * 添加工作计划
     */
    public Observable<InsertPlanResponse> insertWorkPlan(String workText , int doctorId , String createTime, String targetTime,String token){
        return getApiServer().insertWorkPlan(workText,doctorId,createTime,targetTime,token);
    }

    /**
     * 异常数据
     * @param userId
     * @param token
     * @return
     */
    public Observable<HealthTaskDetailResponse> getExceptionData(int userId,String token){
        return getApiServer().getExceptionData(userId,token);
    }

    /**
     * 搜索会员
     *
     * @param phone 手机号
     * @return SearchMemberResponse
     */
    public Observable<InviteMemberResponse> searchMembers(String token,String phone) {
        return getApiServer().searchMembers(token,phone);
    }

    public Observable<InviteSucceedResponse> inviteUserMember(String token,int userId){
        return getApiServer().inviteUserMember(token, userId);
    }

    public Observable<MemberTeamListResponse> getMemberTeamByName(String token,String nameOrPhone,int status){
        return getApiServer().getMemberTeamByName(token, nameOrPhone, status);
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
    public Observable<MyMemberResponse> selectMember(String sysId, String  rank) {
        return getApiServer().selectMember(new VipPostBean(sysId,rank));
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
    public Observable<HealthDataResponse> getHealthList(String userId, String token) {
        return getApiServer().getTodayHealth(userId,token);
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
     * @param doctorId
     * @return
     */
    public Observable<GeneralResponse> createMyTask(long userId, String title, String content,
                                                    long doctorId,String token,String createTime,String queryTime) {
        return getApiServer().createMyTask(userId, title, content, doctorId,token,createTime,queryTime);
    }

    public Observable<CreateTaskResponse> createHealthTask(int systemUserId,int userId,String content,List<CreateTaskBean.ListBean> files){
        CreateTaskBean createTaskBean = new CreateTaskBean();
        createTaskBean.setContent(content);
        createTaskBean.setList(files);
        createTaskBean.setUserId(userId);
        createTaskBean.setSystemUserId(systemUserId);
        return getApiServer().createHealthTask(createTaskBean);
    }

    public Observable<DoctorListResponse> getDoctorList(int roleId,String token){
        return getApiServer().getDoctorList(roleId,token);
    }

    /**
     * 查询异常任务列表
     *
     * @param token
     * @return
     */
    public Observable<TaskResponse> loadMyTaskList(String  token, int pageNum, int pageSize,int state) {
        return getApiServer().loadMyTaskList(token, pageNum, pageSize,state);
    }

    public Observable<HealthTaskResponse> getHealthTaskList(int status,String token){
        return getApiServer().getHealthTaskList(status, token);
    }

    public Observable<HealthTaskResponse> getTransferHealthTaskList(int transferStatus,String token){
        return getApiServer().getTransferHealthTaskList(transferStatus, token);
    }

    /**
     * 处理异常
     * @param id
     * @param token
     * @return
     */
    public Observable<UpdateTaskResponse> updateTask(int id,String token,String proposal){
        return getApiServer().updateTask(id, token, proposal);
    }

    /**
     * 转交处理异常
     * @param id
     * @param systemUserId
     * @param token
     * @return
     */
    public Observable<TransferResponse> sendTransferTask(int id,int systemUserId,String token){
        return getApiServer().sendTransferTask(id, systemUserId, token);
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
     * 今日健康数据
     * 空气检测仪数据
     *
     * @param memberId
     * @param token
     * @return
     */
    public Observable<AirResponse> getAirList(String memberId, String token) {
        return getApiServer().getAirList(memberId, token);
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
     * 精神健康数据
     *
     * @param memberId
     * @param token
     * @return
     */
    public Observable<SpiritHealthResponse> getSpiritList(String memberId, String token) {
        return getApiServer().getSpiritList(memberId, token);
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

    /**
     * 患者会诊选择医师
     * @param token
     * @return
     */
    public Observable<DoctorTeamListResponse> getHospitalDepartmentList(String token){
        return getApiServer().getHospitalDepartmentList(token);
    }

    /**
     * 获取咨询信息列表
     * @param status
     * @param token
     * @return
     */
    public Observable<TemperatureResponse> getHealthConsultStatus(int status,String token){
        return getApiServer().getHealthConsultStatus(status, token);
    }

    public Observable<TemperatureResponse> getHealthConsultTransferStatus(int transferStatus,String token){
        return getApiServer().getHealthConsultTransferStatus(transferStatus, token);
    }

    /**
     * 转交咨询信息处理
     * @param transferBean
     * @return
     */
    public Observable<UpdateResponse> updateHealthConsult(TransferBean transferBean){
        return getApiServer().updateHealthConsult(transferBean);
    }

    /**
     * 添加处方模版
     * @param insertPrescriptionBean
     * @return
     */
    public Observable<InsertResponse> insertHealthConsultDrugModel(InsertPrescriptionBean insertPrescriptionBean){
        return getApiServer().insertHealthConsultDrugModel(insertPrescriptionBean);
    }

    /**
     * 接诊
     * @param receivePatientBean
     * @return
     */
    public Observable<InsertResponse> insertHealthConsultDrug(ReceivePatientBean receivePatientBean){
        return getApiServer().insertHealthConsultDrug(receivePatientBean);
    }

    /**
     * 获取处方模版
     * @param modelType
     * @return
     */
    public Observable<PrescriptionResponse> getHealthConsultDrugModel(int modelType, String token){
        return getApiServer().getHealthConsultDrugModel(modelType,token);
    }

    /**
     * 拒诊
     * @param id
     * @param reason
     * @param token
     * @return
     */
    public Observable<RefusalResponse> getHealthConsultDrugBack(int id,String reason,String token){
        return getApiServer().getHealthConsultDrugBack(id, reason, token);
    }

    /**
     * 搜索团队成员
     * @param phone
     * @param token
     * @return
     */
    public Observable<SearchTeamResponse> teamInvitationList(String phone, String token){
        return getApiServer().teamInvitationList(phone, token);
    }

    /**
     * 健康管理师搜索会员
     * @param phone
     * @param token
     * @return
     */
    public Observable<SearchTeamResponse> getDoctorByPhone(String phone,String token){
        return getApiServer().getDoctorByPhone(phone,token);
    }

    /**
     * 发起团队签约
     * @param token
     * @return
     */
    public Observable<SendResponse> sendApply(int systemUserId, String token){
        return getApiServer().sendApply(systemUserId, token);
    }

    /**
     * 获取业务签约列表
     * @param token
     * @return
     */
    public Observable<TeamApplyResponse> getDoctorTeamApplyList(String token, int status){
        return getApiServer().getDoctorTeamApplyList(token, status);
    }

    public Observable<NoticeResponse> getDoctorTeamApplyNoticeList(String token){
        return getApiServer().getDoctorTeamApplyNoticeList(token);
    }

    /**
     * 同意或拒绝签约
     * @param id
     * @param status
     * @param token
     * @return
     */
    public Observable<SignTeamResponse> signOrNot(int id,int status,String token){
        return getApiServer().signOrNot(id, status, token);
    }

    /**
     * 获取团队信息
     * @param token
     * @return
     */
    public Observable<DoctorTeamResponse> getDoctorTeam(String token){
        return getApiServer().getDoctorTeam(token);
    }

    /**
     * 获取团队列表
     * @param token
     * @param roleId
     * @return
     */
    public Observable<TeamMemberResponse> getDoctorTeamList(String token,int roleId){
        return getApiServer().getDoctorTeamList(token, roleId);
    }

    /**
     * 退出团队
     * @param token
     * @return
     */
    public Observable<QuitTeamResponse> quitTeam(String token){
        return getApiServer().quitTeam(token);
    }

    /**
     * 业务处理列表
     * @param token
     * @param status
     * @return
     */
    public Observable<BusinessDealListResponse> getBusineServiceList(String token,int status){
        return getApiServer().getBusineServiceList(token, status);
    }

    /**
     * 业务处理详情
     * @param token
     * @param id
     * @return
     */
    public Observable<BusinessDetailResponse> getBusineServiceInfo(String token, int id){
        return getApiServer().getBusineServiceInfo(token, id);
    }

    /**
     * 完成任务
     * @param businessDealBean
     * @return
     */
    public Observable<EditResponse> editBusineService(BusinessDealBean businessDealBean){
        return getApiServer().editBusineService(businessDealBean);
    }

    /**
     * 委派任务列表
     * @param token
     * @param date
     * @return
     */
    public Observable<DelegateListResponse> getBusineService(String token,String date){
        return getApiServer().getBusineService(token, date);
    }

    /**
     * 新建委派任务
     * @param delegateBean
     * @return
     */
    public Observable<CreateDelegateResponse> addBusineService(DelegateBean delegateBean){
        return getApiServer().addBusineService(delegateBean);
    }

    /**
     * 获取个人请求列表
     * @param token
     * @param status
     * @return
     */
    public Observable<RequestResponse> getPersonalRequestList(String token,int status){
        return getApiServer().getPersonalRequestList(token, status);
    }

    /**
     * 创建个人请求
     * @param addRequestBean
     * @return
     */
    public Observable<AddRequestResponse> addPersonalRequest(AddRequestBean addRequestBean){
        return getApiServer().addPersonalRequest(addRequestBean);
    }

    public Observable<ReplyResponse> getPersonalRequest(String token,int id){
        return getApiServer().getPersonalRequest(token, id);
    }

    public Observable<CancelResponse> cancelPersonalRequest(String token, int id){
        return getApiServer().cancelPersonalRequest(token, id);
    }

    public Observable<PeriodicalListResponse> getPeriodicalList(String token,int status){
        return getApiServer().getPeriodicalList(token, status);
    }

    public Observable<PeriodicalInfoResponse> getPeriodical(String token,int id){
        return getApiServer().getPeriodical(token, id);
    }

    public Observable<AddOrEditSucceedResponse> addPeriodical(AddPeriodicalBean addPeriodicalBean){
        return getApiServer().addPeriodical(addPeriodicalBean);
    }

    public Observable<AddOrEditSucceedResponse> editPeriodical(EditPeriodicalBean editPeriodicalBean){
        return getApiServer().editPeriodical(editPeriodicalBean);
    }

    public Observable<MemberTeamListResponse> getMemberTeamList(String token,String ranks,int status){
        return getApiServer().getMemberTeamList(token, ranks,status);
    }

    public Observable<IsFocusResponse> editMemberTeam(String token,int id ,int status){
        return getApiServer().editMemberTeam(token, id, status);
    }

    public Observable<DeleteMemberResponse> deleteMemberTeam(String token,int id){
        return getApiServer().deleteMemberTeam(token, id);
    }

    public Observable<DepartMentResponse> getHospitalDepartment(String token){
        return getApiServer().getHospitalDepartment(token);
    }

    public Observable<FamousDoctorListResponse> getDoctorList(String nameOrHospital,String addr,String departmentId,String rank,String grade,String token){
        return getApiServer().getDoctorList(nameOrHospital, addr, departmentId, rank, grade,token);
    }

    public Observable<HospitalListResponse> getHospitalList(String nameOrHospital,String address,String typeId,String token){
        return getApiServer().getHospitalList(nameOrHospital, address, typeId, token);
    }

    public Observable<HospitalTypeResponse> getHospitalTypeList(String token){
        return getApiServer().getHospitalTypeList(token);
    }

    public Observable<HospitalDetailResponse> getHospitalById(int hospitalId,String token){
        return getApiServer().getHospitalById(hospitalId, token);
    }

    public Observable<FamousDoctorListResponse> getDoctorByHospitalId(String nameOrDepartment,String departmentId,String rank,String grade,int hospitalId,String token){
        return getApiServer().getDoctorByHospitalId(nameOrDepartment, departmentId, rank, grade, hospitalId, token);
    }

    public Observable<FamousDoctorInfoResponse> getDoctorInfo(int systemUserId,String token){
        return getApiServer().getDoctorInfo(systemUserId, token);
    }

    public Observable<YearResponse> getYearList(String token){
        return getApiServer().getYearList(token);
    }

    public Observable<BookListResponse> getBookListByYear(String year,String token){
        return getApiServer().getBookListByYear(year, token);
    }

    public Observable<SubscribeResponse> subscribeBook(String bookId,String token){
        return getApiServer().subscribeBook(bookId, token);
    }

    public Observable<SubscribeResponse> cancelSubscribeBook(String bookId,String token){
        return getApiServer().cancelSubscribeBook(bookId, token);
    }

    public Observable<BookArticleResponse> getBookArticle(String id,String token){
        return getApiServer().getBookArticle(id, token);
    }

    public Observable<BookMenuResponse> getBookCatalogList(String bookId, String token){
        return getApiServer().getBookCatalogList(bookId, token);
    }

}