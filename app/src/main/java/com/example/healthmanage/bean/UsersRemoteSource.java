package com.example.healthmanage.bean;

import android.util.Log;

import com.example.healthmanage.base.BaseApplication;
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
import com.example.healthmanage.data.network.ApiWrapper;
import com.example.healthmanage.data.network.MyObserver;
import com.example.healthmanage.data.network.RxHelper;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
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
import com.example.healthmanage.ui.activity.qualification.response.DoctorInfoResponse;
import com.example.healthmanage.ui.activity.qualification.response.HospitalResponse;
import com.example.healthmanage.ui.activity.qualification.response.UploadResponse;
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
import com.example.healthmanage.ui.activity.vipmanager.response.DeleteMemberResponse;
import com.example.healthmanage.ui.activity.vipmanager.response.InviteMemberResponse;
import com.example.healthmanage.ui.activity.vipmanager.response.IsFocusResponse;
import com.example.healthmanage.ui.activity.vipmanager.response.MemberTeamListResponse;
import com.example.healthmanage.ui.activity.workplan.response.InsertPlanResponse;
import com.example.healthmanage.ui.activity.workplan.response.UpdateWorkResponse;
import com.example.healthmanage.ui.activity.workplan.response.WorkPlanListResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.BookArticleResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.BookListResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.SubscribeResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.YearResponse;
import com.example.healthmanage.ui.fragment.qualification.bean.DoctorInfo;
import com.example.healthmanage.ui.fragment.qualification.bean.UpdateDoctorInfo;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
     * @param loginResponseCallback
     */
    public void loginByPassword(String phone, String password,
                                UsersInterface.LoginResponseCallback loginResponseCallback) {
        Log.d(HTAG, "loginByPassword==========>: " + "手机号===>" + phone + "密码===>" + password );
        ApiWrapper.getInstance().loginByPassword(phone, password)
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
     * 获取职业
     */
    public void getJobDataList(UsersInterface.GetJobDataCallback getJobDataCallback){
        ApiWrapper.getInstance().getJobDataList()
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<ProfessionBeanResponse>() {
            @Override
            public void onError(ExceptionHandle.ResponseException responseException) {
                getJobDataCallback.error(responseException);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ProfessionBeanResponse professionBeanResponse) {
                if (professionBeanResponse.getStatus()==0){
                    getJobDataCallback.sendSucceed(professionBeanResponse);
                }else {
                    getJobDataCallback.sendFailed(professionBeanResponse.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 获取科室列表
     */
    public void getDepartmentListData(UsersInterface.GetDepartmentDataCallback getDepartmentDataCallback){
        ApiWrapper.getInstance().getDepartmentDataList()
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<DepartmentResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getDepartmentDataCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull DepartmentResponse departmentResponse) {
                        if (departmentResponse.getStatus()==0){
                            getDepartmentDataCallback.sendSucceed(departmentResponse);
                        }else {
                            getDepartmentDataCallback.sendFailed(departmentResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 搜索科室列表
     * @param name
     */
    public void getDepartmentByName(String name,UsersInterface.GetDepartmentByNameCallback getDepartmentByNameCallback){
        ApiWrapper.getInstance().getDepartmentByName(name)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<DepartmentResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getDepartmentByNameCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull DepartmentResponse departmentItemResponse) {
                        if (departmentItemResponse.getStatus()==0){
                            getDepartmentByNameCallback.sendSucceed(departmentItemResponse);
                        }else {
                            getDepartmentByNameCallback.sendFailed(departmentItemResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 搜索医院列表
     * @param name
     */
    public void getHospitalByName(String name,UsersInterface.GetHospitalByNameCallback getHospitalByNameCallback){
        ApiWrapper.getInstance().getHospitalByName(name)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<HospitalResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHospitalByNameCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HospitalResponse hospitalResponse) {
                        if (hospitalResponse.getStatus()==0){
                            getHospitalByNameCallback.sendSucceed(hospitalResponse);
                        }else {
                            getHospitalByNameCallback.sendFailed(hospitalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 上传图片
     * @param file
     */
    public void getUploadIdCard(File file, UsersInterface.UpLoadIdCardCallback upLoadIdCardCallback){
        ApiWrapper.getInstance().uploadIdCard(file)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<UploadResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        upLoadIdCardCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UploadResponse uploadResponse) {
                        if (uploadResponse.getStatus()==0){
                            upLoadIdCardCallback.sendSucceed(uploadResponse);
                        }else {
                            upLoadIdCardCallback.sendFailed(uploadResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 上传资质认证图片
     * @param files
     * @param upCertificateCallback
     */
    public void getUploadCertificate(List<File> files, UsersInterface.UpCertificateCallback upCertificateCallback)  {
        ApiWrapper.getInstance().uploadCertificate(files)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<CertificateResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        upCertificateCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CertificateResponse certificateResponse) {
                        if (certificateResponse.getStatus()==0){
                            upCertificateCallback.sendSucceed(certificateResponse);
                        }else {
                            upCertificateCallback.sendFailed(certificateResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 提交验证
     */
    public void saveDoctorInfo(DoctorInfo doctorInfo, UsersInterface.SaveDoctorInfoCallback saveDoctorInfoCallback){
        Log.w("doctorInfo=======",""+doctorInfo);
        ApiWrapper.getInstance().saveDoctorInfo(doctorInfo)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<UploadResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        saveDoctorInfoCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UploadResponse uploadResponse) {
                        if (uploadResponse.getStatus()==0){
                            saveDoctorInfoCallback.sendSucceed(uploadResponse);
                        }else {
                            saveDoctorInfoCallback.sendFailed(uploadResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     *提交会诊任务
     * @param patientInfoBean
     * @param insertPatientExamineCallback
     */
    public void insertPatientExamine(PatientInfoBean patientInfoBean,UsersInterface.InsertPatientExamineCallback insertPatientExamineCallback){
        ApiWrapper.getInstance().insertPatientExamine(patientInfoBean).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<AddPatientInfoResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        insertPatientExamineCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AddPatientInfoResponse addPatientInfoResponse) {
                        if (addPatientInfoResponse.getStatus()==0){
                            insertPatientExamineCallback.insertSucceed(addPatientInfoResponse);
                        }else {
                            insertPatientExamineCallback.insertFailed(addPatientInfoResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 提交转诊
     * @param referralBean
     * @param insertPatientReferralCallback
     */
    public void insertPatientReferral(ReferralBean referralBean,UsersInterface.InsertPatientReferralCallback insertPatientReferralCallback){
        ApiWrapper.getInstance().insertPatientReferral(referralBean).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<InsertReferralResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        insertPatientReferralCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull InsertReferralResponse insertReferralResponse) {
                        if (insertReferralResponse.getStatus()==0){
                            insertPatientReferralCallback.insertSucceed(insertReferralResponse);
                        }else {
                            insertPatientReferralCallback.insertFailed(insertReferralResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取转诊列表
     * @param doctorId
     * @param token
     * @param getPatientReferralCallback
     */
    public void getPatientReferral(int doctorId,String token,UsersInterface.GetPatientReferralCallback getPatientReferralCallback){
        ApiWrapper.getInstance().getPatientReferral(doctorId,token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<ReferralResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getPatientReferralCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ReferralResponse referralResponse) {
                        if (referralResponse.getStatus()==0){
                            getPatientReferralCallback.getSucceed(referralResponse);
                        }else {
                            getPatientReferralCallback.getFailed(referralResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取会诊列表
     * @param id
     * @param token
     * @param getPatientExamineCallback
     */
    public void getPatientExamine(int id,int status,String token,UsersInterface.GetPatientExamineCallback getPatientExamineCallback){
        ApiWrapper.getInstance().getPatientExamine(id,status, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<ConsultationListResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getPatientExamineCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ConsultationListResponse consultationListResponse) {
                        if (consultationListResponse.getStatus()==0){
                            getPatientExamineCallback.getSucceed(consultationListResponse);
                        }else {
                            getPatientExamineCallback.getFailed(consultationListResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 提交诊疗方案
     * @param id
     * @param examinePlan
     * @param token
     * @param updatePatientExamineCallback
     */
    public void updatePatientExamine(int id,String examinePlan,String token,UsersInterface.UpdatePatientExamineCallback updatePatientExamineCallback){
        ApiWrapper.getInstance().updatePatientExamine(id, examinePlan, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<AddConsultationPlanResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        updatePatientExamineCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AddConsultationPlanResponse addConsultationPlanResponse) {
                        if (addConsultationPlanResponse.getStatus()==0){
                            updatePatientExamineCallback.updateSucceed(addConsultationPlanResponse);
                        }else {
                            updatePatientExamineCallback.updateFailed(addConsultationPlanResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 查看资格认证是否通过
     */
    public void getDoctorInfo(String token , UsersInterface.GetDoctorInfoCallback getDoctorInfoCallback){
        ApiWrapper.getInstance().getDoctorInfo(token).compose(RxHelper.to_mian()).subscribe(new MyObserver<DoctorInfoResponse>() {
            @Override
            public void onError(ExceptionHandle.ResponseException responseException) {
                getDoctorInfoCallback.error(responseException);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull DoctorInfoResponse doctorInfoResponse) {
                if (doctorInfoResponse.getStatus()==0){
                    getDoctorInfoCallback.sendSucceed(doctorInfoResponse);
                }else {
                    getDoctorInfoCallback.sendFailed(doctorInfoResponse.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 提交更新验证
     */
    public void updateDoctorInfo(UpdateDoctorInfo updateDoctorInfo, UsersInterface.UpdateDoctorInfoCallback updateDoctorInfoCallback){
        ApiWrapper.getInstance().updateDoctorInfo(updateDoctorInfo)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<UploadResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        updateDoctorInfoCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UploadResponse uploadResponse) {
                        if (uploadResponse.getStatus()==0){
                            updateDoctorInfoCallback.sendSucceed(uploadResponse);
                        }else {
                            updateDoctorInfoCallback.sendFailed(uploadResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取健康档案数据
     */
    public void getUserInfo(int userId,String token,UsersInterface.GetUserInfoCallback getUserInfoCallback){
        ApiWrapper.getInstance().getUserInfo(userId,token).compose(RxHelper.to_mian()).subscribe(new MyObserver<HealthRecordResponse>() {
            @Override
            public void onError(ExceptionHandle.ResponseException responseException) {
                getUserInfoCallback.error(responseException);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull HealthRecordResponse healthRecordResponse) {
                if (healthRecordResponse.getStatus()==0){
                    getUserInfoCallback.sendSucceed(healthRecordResponse);
                }else {
                    getUserInfoCallback.sendFailed(healthRecordResponse.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 获取健康数据中体检报告
     * @param userId
     * @param token
     * @param getCheckReportListCallback
     */
    public void getCheckReportList(int userId,String token,UsersInterface.GetCheckReportListCallback getCheckReportListCallback){
        ApiWrapper.getInstance().checkReportList(userId,token).compose(RxHelper.to_mian()).subscribe(new MyObserver<CheckReportResponse>() {
            @Override
            public void onError(ExceptionHandle.ResponseException responseException) {
                getCheckReportListCallback.error(responseException);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull CheckReportResponse checkReportResponse) {
                if (checkReportResponse.getStatus()==0){
                    getCheckReportListCallback.sendSucceed(checkReportResponse);
                }else {
                    getCheckReportListCallback.sendFailed(checkReportResponse.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 获取健康数据中健康评估
     * @param userId
     * @param token
     * @param getHistoryAssessListCallback
     */
    public void getHistoryAssessList(int userId,String token,UsersInterface.GetHistoryAssessListCallback getHistoryAssessListCallback){
        ApiWrapper.getInstance().historyAssessList(userId,token).compose(RxHelper.to_mian()).subscribe(new MyObserver<HistoryAssessListResponse>() {
            @Override
            public void onError(ExceptionHandle.ResponseException responseException) {
                getHistoryAssessListCallback.error(responseException);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull HistoryAssessListResponse historyAssessListResponse) {
                if (historyAssessListResponse.getStatus()==0){
                    getHistoryAssessListCallback.sendSucceed(historyAssessListResponse);
                }else {
                    getHistoryAssessListCallback.sendFailed(historyAssessListResponse.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     * 获取健康数据中病历本数据
     * @param userId
     * @param token
     * @param getMedicalRecordAllCallback
     */
    public void getMedicalRecordAll(int userId,String token,UsersInterface.GetMedicalRecordAllCallback getMedicalRecordAllCallback){
        ApiWrapper.getInstance().getMedicalRecordAll(userId,token).compose(RxHelper.to_mian()).subscribe(new MyObserver<MedicineBookResponse>() {
            @Override
            public void onError(ExceptionHandle.ResponseException responseException) {
                getMedicalRecordAllCallback.error(responseException);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull MedicineBookResponse medicineBookResponse) {
                if (medicineBookResponse.getStatus()==0){
                    getMedicalRecordAllCallback.sendSucceed(medicineBookResponse);
                }else {
                    getMedicalRecordAllCallback.sendFailed(medicineBookResponse.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 获取健康报告数据
     * @param userId
     * @param token
     * @param getHealthReportAllCallback
     */
    public void getHealthReportList(int userId,String date,String token,UsersInterface.GetHealthReportAllCallback getHealthReportAllCallback){
        ApiWrapper.getInstance().getHealthReportList(userId,date,token).compose(RxHelper.to_mian()).subscribe(new MyObserver<HealthReportResponse>() {
            @Override
            public void onError(ExceptionHandle.ResponseException responseException) {
                getHealthReportAllCallback.error(responseException);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull HealthReportResponse healthReportResponse) {
                if (healthReportResponse.getStatus()==0){
                    getHealthReportAllCallback.sendSucceed(healthReportResponse);
                }else {
                    getHealthReportAllCallback.sendFailed(healthReportResponse.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 提交健康报告
     * @param healthReportInfo
     * @param saveHealthReportCallback
     */
    public void saveHealthReport(HealthReportInfo healthReportInfo,UsersInterface.SaveHealthReportCallback saveHealthReportCallback){
        ApiWrapper.getInstance().saveHealthReport(healthReportInfo).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<HealthReportConfirmResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        saveHealthReportCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HealthReportConfirmResponse healthReportConfirmResponse) {
                        if (healthReportConfirmResponse.getStatus()==0){
                            saveHealthReportCallback.sendSucceed(healthReportConfirmResponse);
                        }else {
                            saveHealthReportCallback.sendFailed(healthReportConfirmResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取健康报告详情
     * @param id
     * @param token
     * @param getHealthReportCallback
     */
    public void getHealthReport(int id,String token,UsersInterface.GetHealthReportCallback getHealthReportCallback){
        ApiWrapper.getInstance().getHealthReport(id,token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<HealthReportDetailResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHealthReportCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HealthReportDetailResponse healthReportDetailResponse) {
                        if (healthReportDetailResponse.getStatus()==0){
                            getHealthReportCallback.sendSucceed(healthReportDetailResponse);
                        }else {
                            getHealthReportCallback.sendFailed(healthReportDetailResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 会员签约列表
     * @param doctorId
     * @param token
     * @param getSignMemberCallback
     */
    public void getSignMemberData(int doctorId,String token,UsersInterface.GetSignMemberCallback getSignMemberCallback){
        ApiWrapper.getInstance().getSignMemberData(doctorId,token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<SignMemberResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getSignMemberCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SignMemberResponse signMemberResponse) {
                        if (signMemberResponse.getStatus()==0){
                            getSignMemberCallback.sendSucceed(signMemberResponse);
                        }else {
                            getSignMemberCallback.sendFailed(signMemberResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 会员未签约列表
     * @param doctorId
     * @param token
     * @param getNotSignMemberCallback
     */
    public void getNotSignMemberData(int doctorId,String token,UsersInterface.GetNotSignMemberCallback getNotSignMemberCallback){
        ApiWrapper.getInstance().getNotSignMemberData(doctorId,token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<SignMemberResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getNotSignMemberCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SignMemberResponse signMemberResponse) {
                        if (signMemberResponse.getStatus()==0){
                            getNotSignMemberCallback.sendSucceed(signMemberResponse);
                        }else {
                            getNotSignMemberCallback.sendFailed(signMemberResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 确定是否签约
     * @param doctorSignStatus 0签约 1拒绝签约
     * @param doctorId
     * @param id
     * @param token
     * @param addSignMemberCallback
     */
    public void addSignMember(int doctorSignStatus,long doctorId,long id,String token,UsersInterface.AddSignMemberCallback addSignMemberCallback){
        ApiWrapper.getInstance().addSignMember(doctorSignStatus,doctorId,id,token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<SignMemberSuccessResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        addSignMemberCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SignMemberSuccessResponse signMemberSuccessResponse) {
                        if (signMemberSuccessResponse.getStatus()==0){
                            addSignMemberCallback.sendSucceed(signMemberSuccessResponse);
                        }else {
                            addSignMemberCallback.sendFailed(signMemberSuccessResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取工作计划
     * @param time
     * @param sysId
     * @param token
     * @param getWorkPlanByTimeCallback
     */
    public void getWorkPlanByTime(String time,int sysId,String token ,UsersInterface.GetWorkPlanByTimeCallback getWorkPlanByTimeCallback){
        ApiWrapper.getInstance().getWorkPlanByTime(time,sysId,token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<WorkPlanListResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getWorkPlanByTimeCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull WorkPlanListResponse workPlanListResponse) {
                        if (workPlanListResponse.getStatus()==0){
                            getWorkPlanByTimeCallback.sendSucceed(workPlanListResponse);
                        }else {
                            getWorkPlanByTimeCallback.sendFailed(workPlanListResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 修改工作状态
     * @param id
     * @param updateTime
     * @param token
     */
    public void updateWorkPlanById(int id,String updateTime,String token,UsersInterface.UpdateWorkPlanByIdCallback updateWorkPlanByIdCallback){
        ApiWrapper.getInstance().updateWorkPlanById(id,updateTime,token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<UpdateWorkResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        updateWorkPlanByIdCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UpdateWorkResponse updateWorkResponse) {
                        if (updateWorkResponse.getStatus()==0){
                            updateWorkPlanByIdCallback.updateSucceed(updateWorkResponse);
                        }else {
                            updateWorkPlanByIdCallback.updateFailed(updateWorkResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 添加工作计划
     * @param workText
     * @param doctorId
     * @param createTime
     * @param targetTime
     * @param token
     * @param insertWorkPlanCallback
     */
    public void insertWorkPlan(String workText,int doctorId,String createTime,String targetTime,String token,UsersInterface.InsertWorkPlanCallback insertWorkPlanCallback){
        ApiWrapper.getInstance().insertWorkPlan(workText, doctorId, createTime,targetTime,token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<InsertPlanResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        insertWorkPlanCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull InsertPlanResponse insertPlanResponse) {
                        if (insertPlanResponse.getStatus()==0){
                            insertWorkPlanCallback.insertSucceed(insertPlanResponse);
                        }else {
                            insertWorkPlanCallback.insertFailed(insertPlanResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取用户异常数据
     * @param userId
     * @param token
     * @param getExceptionDataCallback
     */
    public void getExceptionData(int userId,String token,UsersInterface.GetExceptionDataCallback getExceptionDataCallback){
        ApiWrapper.getInstance().getExceptionData(userId, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<HealthTaskDetailResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getExceptionDataCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HealthTaskDetailResponse healthTaskDetailResponse) {
                        if (healthTaskDetailResponse.getStatus()==0){
                            getExceptionDataCallback.getSucceed(healthTaskDetailResponse);
                        }else {
                            getExceptionDataCallback.getFailed(healthTaskDetailResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

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
    public void searchMembers(String token,String phone,
                              UsersInterface.SearchMembersCallback searchMembersCallback) {
        Log.d(HTAG, "searchMembers==========>: " + "手机号===>" + phone);
        ApiWrapper.getInstance().searchMembers(token,phone)
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<InviteMemberResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        searchMembersCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InviteMemberResponse searchMemberResponse) {
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

    public void inviteMember(String token,int userId,UsersInterface.InviteUserMemberCallback inviteUserMemberCallback){
        ApiWrapper.getInstance().inviteUserMember(token, userId).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<InviteSucceedResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        inviteUserMemberCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull InviteSucceedResponse inviteSucceedResponse) {
                        if (inviteSucceedResponse.getStatus()==0){
                            inviteUserMemberCallback.inviteSucceed(inviteSucceedResponse);
                        }else {
                            inviteUserMemberCallback.inviteFailed(inviteSucceedResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getMemberTeamByName(String token,String nameOrPhone,int status,UsersInterface.GetMemberTeamByNameCallback getMemberTeamByNameCallback){
        ApiWrapper.getInstance().getMemberTeamByName(token, nameOrPhone, status).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<MemberTeamListResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getMemberTeamByNameCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull MemberTeamListResponse memberTeamListResponse) {
                        if (memberTeamListResponse.getStatus()==0){
                            getMemberTeamByNameCallback.getSucceed(memberTeamListResponse);
                        }else {
                            getMemberTeamByNameCallback.getFailed(memberTeamListResponse.getMessage());
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
     * @param ranks
     * @param loadMyMembersCallback
     */
    public void selectMember(String sysId,
                             String ranks,
                             UsersInterface.LoadMyMembersCallback loadMyMembersCallback) {
        Log.d(HTAG, "selectMember==========>: " + "当前用户Id===>" + sysId + "会员等级===>" + ranks);
        ApiWrapper.getInstance().selectMember(sysId, ranks)
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
     * 空气检测仪数据
     *
     * @param memberId
     * @param getAirListCallback
     */
    public void getAirList(String memberId,
                           UsersInterface.GetAirListCallback getAirListCallback) {
        Log.d(HTAG, "getAirList memberId==========>: " + memberId);
        ApiWrapper.getInstance().getAirList(memberId, BaseApplication.getToken())
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
     * 获取今日健康数据
     *
     * @param userId
     * @param getHealthListCallback
     */
    public void getHealthList(String userId,
                              UsersInterface.GetHealthListCallback getHealthListCallback) {
        Log.d(HTAG, "getHealthList==========>: " + "会员Id===>" + userId);
        ApiWrapper.getInstance().getHealthList(userId,BaseApplication.getToken())
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<HealthDataResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HealthDataResponse healthDataResponse) {
                        if (healthDataResponse.getStatus() == 0) {
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
     * 精神健康数据
     *
     * @param memberId
     * @param getSpiritListCallback
     */
    public void getSpiritList(String memberId,
                           UsersInterface.GetSpiritListCallback getSpiritListCallback) {
        Log.d(HTAG, "getAirList memberId==========>: " + memberId);
        ApiWrapper.getInstance().getSpiritList(memberId, BaseApplication.getToken())
                .compose(RxHelper.to_mian())
                .subscribe(new MyObserver<SpiritHealthResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(SpiritHealthResponse spiritHealthResponse) {
                        if (spiritHealthResponse.getStatus() == 0) {
                            getSpiritListCallback.getSucceed(spiritHealthResponse);
                        } else {
                            getSpiritListCallback.getFailed(spiritHealthResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getSpiritListCallback.error(responseException);
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
     * @param doctorId
     * @param createMyTaskCallback
     */
    public void createMyTask(long userId,
                             String title,
                             String content,
                             long doctorId,
                             String token,
                             String createTime,
                             String queryTime,
                             UsersInterface.CreateMyTaskCallback createMyTaskCallback) {
        Log.d(HTAG, "createMyTask==========>: " + "会员Id===>" + userId + "异常任务标题===>" + title +
                "异常任务描述===>" + content + "当前用户Id===>" + doctorId);
        ApiWrapper.getInstance().createMyTask(userId, title, content, doctorId,token,createTime,queryTime)
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

    public void createHealthTask(int systemUserId, int userId, String content, List<CreateTaskBean.ListBean> files, UsersInterface.CreateHealthTaskCallback createHealthTaskCallback){
        ApiWrapper.getInstance().createHealthTask(systemUserId, userId, content,files).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<CreateTaskResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        createHealthTaskCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CreateTaskResponse createTaskResponse) {
                        if (createTaskResponse.getStatus()==0){
                            createHealthTaskCallback.createSucceed(createTaskResponse);
                        }else {
                            createHealthTaskCallback.createFailed(createTaskResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 加载我的异常任务列表
     *
     * @param token
     * @param loadMyTaskCallback
     */
    public void loadMyTask(String token,
                           int pageNum,
                           int pageSize,
                           int state,
                           UsersInterface.LoadMyTaskCallback loadMyTaskCallback) {
        Log.d(HTAG, "loadMyTask==========>: " + "当前用户Id===>" + token);
        ApiWrapper.getInstance().loadMyTaskList(token, pageNum, pageSize,state)
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

    public void getHealthTaskList(int status,String token,UsersInterface.GetHealthTaskListCallback getHealthTaskListCallback){
        ApiWrapper.getInstance().getHealthTaskList(status, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<HealthTaskResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHealthTaskListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HealthTaskResponse healthTaskResponse) {
                        if (healthTaskResponse.getStatus()==0){
                            getHealthTaskListCallback.getSucceed(healthTaskResponse);
                        }else {
                            getHealthTaskListCallback.getFailed(healthTaskResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTransferHealthTaskList(int transferStatus,String token,UsersInterface.GetTransferHealthTaskListCallback getTransferHealthTaskListCallback){
        ApiWrapper.getInstance().getTransferHealthTaskList(transferStatus, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<HealthTaskResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getTransferHealthTaskListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HealthTaskResponse healthTaskResponse) {
                        if (healthTaskResponse.getStatus()==0){
                            getTransferHealthTaskListCallback.getSucceed(healthTaskResponse);
                        }else {
                            getTransferHealthTaskListCallback.getFailed(healthTaskResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 直接处理异常
     * @param id
     * @param token
     * @param proposal
     * @param updateTaskCallback
     */
    public void updateTask(int id,String token,String proposal,UsersInterface.UpdateTaskCallback updateTaskCallback){
        ApiWrapper.getInstance().updateTask(id, token, proposal)
                .compose(RxHelper.to_mian()).subscribe(new MyObserver<UpdateTaskResponse>() {
            @Override
            public void onError(ExceptionHandle.ResponseException responseException) {
                updateTaskCallback.error(responseException);
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull UpdateTaskResponse updateTaskResponse) {
                if (updateTaskResponse.getStatus()==0){
                    updateTaskCallback.updateSucceed(updateTaskResponse);
                }else {
                    updateTaskCallback.updateFailed(updateTaskResponse.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 转交处理异常
     * @param id
     * @param systemUserId
     * @param token
     * @param sendTransferTaskCallback
     */
    public void sendTransferTask(int id,int systemUserId,String token,UsersInterface.SendTransferTaskCallback sendTransferTaskCallback){
        ApiWrapper.getInstance().sendTransferTask(id,systemUserId,token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<TransferResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        sendTransferTaskCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TransferResponse transferResponse) {
                        if (transferResponse.getStatus()==0){
                            sendTransferTaskCallback.transferSucceed(transferResponse);
                        }else {
                            sendTransferTaskCallback.transferFailed(transferResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

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
    public void getDoctorList(int roleId,String token ,UsersInterface.GetDoctorListCallback getDoctorListCallback) {
        ApiWrapper.getInstance().getDoctorList(roleId, token)
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

    /**
     * 患者会诊选择医师
     * @param token
     * @param getHospitalDepartmentListCallback
     */
    public void getHospitalDepartmentList(String token,UsersInterface.GetHospitalDepartmentListCallback getHospitalDepartmentListCallback){
        ApiWrapper.getInstance().getHospitalDepartmentList(token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<DoctorTeamListResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHospitalDepartmentListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull DoctorTeamListResponse doctorTeamListResponse) {
                        if (doctorTeamListResponse.getStatus()==0){
                            getHospitalDepartmentListCallback.getSucceed(doctorTeamListResponse);
                        }else {
                            getHospitalDepartmentListCallback.getFailed(doctorTeamListResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取咨询信息列表
     * @param status
     * @param token
     * @param getHealthConsultCallback
     */
    public void getHealthConsultStatus(int status,String token,UsersInterface.GetHealthConsultCallback getHealthConsultCallback){
        ApiWrapper.getInstance().getHealthConsultStatus(status, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<TemperatureResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHealthConsultCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TemperatureResponse temperatureResponse) {
                        if (temperatureResponse.getStatus()==0){
                            getHealthConsultCallback.getSucceed(temperatureResponse);
                        }else {
                            getHealthConsultCallback.getFailed(temperatureResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getHealthConsultTransferStatus(int transferStatus,String token,UsersInterface.GetHealthConsultCallback getHealthConsultCallback){
        ApiWrapper.getInstance().getHealthConsultTransferStatus(transferStatus, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<TemperatureResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHealthConsultCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TemperatureResponse temperatureResponse) {
                        if (temperatureResponse.getStatus()==0){
                            getHealthConsultCallback.getSucceed(temperatureResponse);
                        }else {
                            getHealthConsultCallback.getFailed(temperatureResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 转交咨询信息
     * @param transferBean
     * @param updateHealthConsultCallback
     */
    public void updateHealthConsult(TransferBean transferBean,UsersInterface.UpdateHealthConsultCallback updateHealthConsultCallback){
        ApiWrapper.getInstance().updateHealthConsult(transferBean).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<UpdateResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        updateHealthConsultCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UpdateResponse updateResponse) {
                        if (updateResponse.getStatus()==0){
                            updateHealthConsultCallback.updateSucceed(updateResponse);
                        }else {
                            updateHealthConsultCallback.updateFailed(updateResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void insertHealthConsultDrugModel(InsertPrescriptionBean insertPrescriptionBean,UsersInterface.InsertHealthConsultDrugModelCallback insertHealthConsultDrugModelCallback){
        ApiWrapper.getInstance().insertHealthConsultDrugModel(insertPrescriptionBean).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<InsertResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        insertHealthConsultDrugModelCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull InsertResponse insertResponse) {
                        if (insertResponse.getStatus()==0){
                            insertHealthConsultDrugModelCallback.insertSucceed(insertResponse);
                        }else {
                            insertHealthConsultDrugModelCallback.insertFailed(insertResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void insertHealthConsultDrug(ReceivePatientBean receivePatientBean, UsersInterface.InsertHealthConsultDrugCallback insertHealthConsultDrugCallback){
        ApiWrapper.getInstance().insertHealthConsultDrug(receivePatientBean).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<InsertResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        insertHealthConsultDrugCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull InsertResponse insertResponse) {
                        if (insertResponse.getStatus()==0){
                            insertHealthConsultDrugCallback.insertSucceed(insertResponse);
                        }else {
                            insertHealthConsultDrugCallback.insertFailed(insertResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getHealthConsultDrugModel(int modelType,String token,UsersInterface.GetHealthConsultDrugModelCallback getHealthConsultDrugModelCallback){
        ApiWrapper.getInstance().getHealthConsultDrugModel(modelType,token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<PrescriptionResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHealthConsultDrugModelCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull PrescriptionResponse prescriptionResponse) {
                        if (prescriptionResponse.getStatus()==0){
                            getHealthConsultDrugModelCallback.getSucceed(prescriptionResponse);
                        }else {
                            getHealthConsultDrugModelCallback.getFailed(prescriptionResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getHealthConsultDrugBack(int id,String reason,String token,UsersInterface.GetHealthConsultDrugBackCallback getHealthConsultDrugBackCallback){
        ApiWrapper.getInstance().getHealthConsultDrugBack(id, reason, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<RefusalResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHealthConsultDrugBackCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RefusalResponse refusalResponse) {
                        if (refusalResponse.getStatus()==0){
                            getHealthConsultDrugBackCallback.getSucceed(refusalResponse);
                        }else {
                            getHealthConsultDrugBackCallback.getFailed(refusalResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void teamInvitationList(String phone,String token,UsersInterface.TeamInvitationListCallback teamInvitationListCallback){
        ApiWrapper.getInstance().teamInvitationList(phone,token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<SearchTeamResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        teamInvitationListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SearchTeamResponse searchTeamResponse) {
                        if (searchTeamResponse.getStatus()==0){
                            teamInvitationListCallback.getSucceed(searchTeamResponse);
                        }else {
                            teamInvitationListCallback.getFailed(searchTeamResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDoctorByPhone(String phone,String token,UsersInterface.GetDoctorByPhoneCallback getDoctorByPhoneCallback){
        ApiWrapper.getInstance().getDoctorByPhone(phone, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<SearchTeamResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getDoctorByPhoneCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SearchTeamResponse searchTeamResponse) {
                        if (searchTeamResponse.getStatus()==0){
                            getDoctorByPhoneCallback.getSucceed(searchTeamResponse);
                        }else {
                            getDoctorByPhoneCallback.getFailed(searchTeamResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void sendApply(int systemUserId,String token ,UsersInterface.SendApplyCallback sendApplyCallback){
        ApiWrapper.getInstance().sendApply(systemUserId, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<SendResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        sendApplyCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SendResponse sendResponse) {
                        if (sendResponse.getStatus()==0){
                            sendApplyCallback.sendSucceed(sendResponse);
                        }else {
                            sendApplyCallback.sendFailed(sendResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDoctorTeamApplyList(String token,int status,UsersInterface.DoctorTeamApplyListCallback doctorTeamApplyListCallback){
        ApiWrapper.getInstance().getDoctorTeamApplyList(token,status).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<TeamApplyResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        doctorTeamApplyListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TeamApplyResponse teamApplyResponse) {
                        if (teamApplyResponse.getStatus()==0){
                            doctorTeamApplyListCallback.getSucceed(teamApplyResponse);
                        }else {
                            doctorTeamApplyListCallback.getFailed(teamApplyResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDoctorTeamApplyNoticeList(String token,UsersInterface.GetDoctorTeamApplyListCallback getDoctorTeamApplyListCallback){
        ApiWrapper.getInstance().getDoctorTeamApplyNoticeList(token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<NoticeResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getDoctorTeamApplyListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NoticeResponse noticeResponse) {
                        if (noticeResponse.getStatus()==0){
                            getDoctorTeamApplyListCallback.getSucceed(noticeResponse);
                        }else {
                            getDoctorTeamApplyListCallback.getFailed(noticeResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void signOrNot(int id,int status,String token,UsersInterface.SignOrNotCallback signOrNotCallback){
        ApiWrapper.getInstance().signOrNot(id, status, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<SignTeamResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        signOrNotCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SignTeamResponse signTeamResponse) {
                        if (signTeamResponse.getStatus()==0){
                            signOrNotCallback.getSucceed(signTeamResponse);
                        }else {
                            signOrNotCallback.getFailed(signTeamResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDoctorTeam(String token,UsersInterface.GetDoctorTeamCallback getDoctorTeamCallback){
        ApiWrapper.getInstance().getDoctorTeam(token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<DoctorTeamResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getDoctorTeamCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull DoctorTeamResponse doctorTeamResponse) {
                        if (doctorTeamResponse.getStatus()==0){
                            getDoctorTeamCallback.getSucceed(doctorTeamResponse);
                        }else {
                            getDoctorTeamCallback.getFailed(doctorTeamResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDoctorTeamList(String token,int roleId,UsersInterface.GetDoctorTeamListCallback getDoctorTeamListCallback){
        ApiWrapper.getInstance().getDoctorTeamList(token,roleId).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<TeamMemberResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getDoctorTeamListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TeamMemberResponse teamMemberResponse) {
                        if (teamMemberResponse.getStatus()==0){
                            getDoctorTeamListCallback.getSucceed(teamMemberResponse);
                        }else {
                            getDoctorTeamListCallback.getFailed(teamMemberResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void quitTeam(String token,UsersInterface.QuitTeamCallback quitTeamCallback){
        ApiWrapper.getInstance().quitTeam(token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<QuitTeamResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        quitTeamCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull QuitTeamResponse quitTeamResponse) {
                        if (quitTeamResponse.getStatus()==0){
                            quitTeamCallback.quitSucceed(quitTeamResponse);
                        }else {
                            quitTeamCallback.quitFailed(quitTeamResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getBusineServiceList(String token,int status,UsersInterface.GetBusineServiceListCallback getBusineServiceListCallback){
        ApiWrapper.getInstance().getBusineServiceList(token, status).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<BusinessDealListResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getBusineServiceListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BusinessDealListResponse businessDealListResponse) {
                        if (businessDealListResponse.getStatus()==0){
                            getBusineServiceListCallback.getSucceed(businessDealListResponse);
                        }else {
                            getBusineServiceListCallback.getFailed(businessDealListResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getBusineServiceInfo(String token,int id,UsersInterface.GetBusineServiceInfoCallback getBusineServiceInfoCallback){
        ApiWrapper.getInstance().getBusineServiceInfo(token, id).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<BusinessDetailResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getBusineServiceInfoCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BusinessDetailResponse businessDetailResponse) {
                        if (businessDetailResponse.getStatus()==0){
                            getBusineServiceInfoCallback.getSucceed(businessDetailResponse);
                        }else {
                            getBusineServiceInfoCallback.getFailed(businessDetailResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void editBusineService(BusinessDealBean businessDealBean,UsersInterface.EditBusineServiceCallback editBusineServiceCallback){
        ApiWrapper.getInstance().editBusineService(businessDealBean).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<EditResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        editBusineServiceCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull EditResponse editResponse) {
                        if (editResponse.getStatus()==0){
                            editBusineServiceCallback.editSucceed(editResponse);
                        }else {
                            editBusineServiceCallback.editFailed(editResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getBusineService(String token,String date,UsersInterface.GetBusineServiceCallback getBusineServiceCallback){
        ApiWrapper.getInstance().getBusineService(token, date).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<DelegateListResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getBusineServiceCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull DelegateListResponse delegateListResponse) {
                        if (delegateListResponse.getStatus()==0){
                            getBusineServiceCallback.getSucceed(delegateListResponse);
                        }else {
                            getBusineServiceCallback.getFailed(delegateListResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addBusineService(DelegateBean delegateBean,UsersInterface.AddBusineServiceCallback addBusineServiceCallback){
        ApiWrapper.getInstance().addBusineService(delegateBean).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<CreateDelegateResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        addBusineServiceCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CreateDelegateResponse createDelegateResponse) {
                        if (createDelegateResponse.getStatus()==0){
                            addBusineServiceCallback.addSucceed(createDelegateResponse);
                        }else {
                            addBusineServiceCallback.addFailed(createDelegateResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPersonalRequestList(String token,int status,UsersInterface.GetPersonalRequestListCallback getPersonalRequestListCallback){
        ApiWrapper.getInstance().getPersonalRequestList(token, status).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<RequestResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getPersonalRequestListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RequestResponse requestResponse) {
                        if (requestResponse.getStatus()==0){
                            getPersonalRequestListCallback.getSucceed(requestResponse);
                        }else {
                            getPersonalRequestListCallback.getFailed(requestResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addPersonalRequest(AddRequestBean addRequestBean,UsersInterface.AddPersonalRequestCallback addPersonalRequestCallback){
        ApiWrapper.getInstance().addPersonalRequest(addRequestBean).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<AddRequestResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        addPersonalRequestCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AddRequestResponse addRequestResponse) {
                        if (addRequestResponse.getStatus()==0){
                            addPersonalRequestCallback.addSucceed(addRequestResponse);
                        }else {
                            addPersonalRequestCallback.addFailed(addRequestResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPersonalRequest(String token,int id,UsersInterface.GetPersonalRequestCallback getPersonalRequestCallback){
        ApiWrapper.getInstance().getPersonalRequest(token, id).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<ReplyResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getPersonalRequestCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ReplyResponse replyResponse) {
                        if (replyResponse.getStatus()==0){
                            getPersonalRequestCallback.getSucceed(replyResponse);
                        }else {
                            getPersonalRequestCallback.getFailed(replyResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void cancelPersonalRequest(String token,int id,UsersInterface.CancelPersonalRequestCallback cancelPersonalRequestCallback){
        ApiWrapper.getInstance().cancelPersonalRequest(token, id).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<CancelResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        cancelPersonalRequestCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CancelResponse cancelResponse) {
                        if (cancelResponse.getStatus()==0){
                            cancelPersonalRequestCallback.cancelSucceed(cancelResponse);
                        }else {
                            cancelPersonalRequestCallback.cancelFailed(cancelResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPeriodicalList(String token,int status,UsersInterface.GetPeriodicalListCallback getPeriodicalListCallback){
        ApiWrapper.getInstance().getPeriodicalList(token, status).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<PeriodicalListResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getPeriodicalListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull PeriodicalListResponse periodicalListResponse) {
                        if (periodicalListResponse.getStatus()==0){
                            getPeriodicalListCallback.getSucceed(periodicalListResponse);
                        }else {
                            getPeriodicalListCallback.getFailed(periodicalListResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPeriodical(String token,int id,UsersInterface.GetPeriodicalCallback getPeriodicalCallback){
        ApiWrapper.getInstance().getPeriodical(token, id).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<PeriodicalInfoResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getPeriodicalCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull PeriodicalInfoResponse periodicalInfoResponse) {
                        if (periodicalInfoResponse.getStatus()==0){
                            getPeriodicalCallback.getSucceed(periodicalInfoResponse);
                        }else {
                            getPeriodicalCallback.getFailed(periodicalInfoResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addPeriodical(AddPeriodicalBean addPeriodicalBean,UsersInterface.AddPeriodicalCallback addPeriodicalCallback){
        ApiWrapper.getInstance().addPeriodical(addPeriodicalBean).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<AddOrEditSucceedResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        addPeriodicalCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AddOrEditSucceedResponse addOrEditSucceedResponse) {
                        if (addOrEditSucceedResponse.getStatus()==0){
                            addPeriodicalCallback.addSucceed(addOrEditSucceedResponse);
                        }else {
                            addPeriodicalCallback.addFailed(addOrEditSucceedResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void editPeriodical(EditPeriodicalBean editPeriodicalBean, UsersInterface.EditPeriodicalCallback editPeriodicalCallback) {
        ApiWrapper.getInstance().editPeriodical(editPeriodicalBean).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<AddOrEditSucceedResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        editPeriodicalCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AddOrEditSucceedResponse addOrEditSucceedResponse) {
                        if (addOrEditSucceedResponse.getStatus()==0){
                            editPeriodicalCallback.editSucceed(addOrEditSucceedResponse);
                        }else {
                            editPeriodicalCallback.editFailed(addOrEditSucceedResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getMemberTeamList(String token,String ranks,int status,UsersInterface.GetMemberTeamListCallback getMemberTeamListCallback){
        ApiWrapper.getInstance().getMemberTeamList(token, ranks,status).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<MemberTeamListResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getMemberTeamListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull MemberTeamListResponse memberTeamListResponse) {
                        if (memberTeamListResponse.getStatus()==0){
                            getMemberTeamListCallback.getSucceed(memberTeamListResponse);
                        }else {
                            getMemberTeamListCallback.getFailed(memberTeamListResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void editMemberTeam(String token,int id,int status,UsersInterface.EditMemberTeamCallback editMemberTeamCallback){
        ApiWrapper.getInstance().editMemberTeam(token, id, status).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<IsFocusResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        editMemberTeamCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull IsFocusResponse isFocusResponse) {
                        if (isFocusResponse.getStatus()==0){
                            editMemberTeamCallback.editSucceed(isFocusResponse);
                        }else {
                            editMemberTeamCallback.editFailed(isFocusResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void deleteMemberTeam(String token,int id,UsersInterface.DeleteMemberTeamCallback deleteMemberTeamCallback){
        ApiWrapper.getInstance().deleteMemberTeam(token, id).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<DeleteMemberResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        deleteMemberTeamCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull DeleteMemberResponse deleteMemberResponse) {
                        if (deleteMemberResponse.getStatus()==0){
                            deleteMemberTeamCallback.deleteSucceed(deleteMemberResponse);
                        }else {
                            deleteMemberTeamCallback.deleteFailed(deleteMemberResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getHospitalDepartment(String token,UsersInterface.GetHospitalDepartmentCallback getHospitalDepartmentCallback){
        ApiWrapper.getInstance().getHospitalDepartment(token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<DepartMentResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHospitalDepartmentCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull DepartMentResponse departMentResponse) {
                        if (departMentResponse.getStatus()==0){
                            getHospitalDepartmentCallback.getSucceed(departMentResponse);
                        }else {
                            getHospitalDepartmentCallback.getFailed(departMentResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDoctorList(String nameOrHospital,String addr,String departmentId,String rank,String grade,String token,UsersInterface.GetFamousDoctorListCallback getFamousDoctorListCallback){
        ApiWrapper.getInstance().getDoctorList(nameOrHospital, addr, departmentId, rank,grade, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<FamousDoctorListResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getFamousDoctorListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull FamousDoctorListResponse famousDoctorListResponse) {
                        if (famousDoctorListResponse.getStatus()==0){
                            getFamousDoctorListCallback.getSucceed(famousDoctorListResponse);
                        }else {
                            getFamousDoctorListCallback.getFailed(famousDoctorListResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getHospitalList(String nameOrHospital,String address,String typeId,String token,UsersInterface.GetHospitalListCallback getHospitalListCallback){
        ApiWrapper.getInstance().getHospitalList(nameOrHospital, address, typeId, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<HospitalListResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHospitalListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull HospitalListResponse hospitalListResponse) {
                        if (hospitalListResponse.getStatus()==0){
                            getHospitalListCallback.getSucceed(hospitalListResponse);
                        }else {
                            getHospitalListCallback.getFailed(hospitalListResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getHospitalTypeList(String token,UsersInterface.GetHospitalTypeListCallback getHospitalTypeListCallback){
        ApiWrapper.getInstance().getHospitalTypeList(token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<HospitalTypeResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHospitalTypeListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull HospitalTypeResponse hospitalTypeResponse) {
                        if (hospitalTypeResponse.getStatus()==0){
                            getHospitalTypeListCallback.getSucceed(hospitalTypeResponse);
                        }else {
                            getHospitalTypeListCallback.getFailed(hospitalTypeResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getHospitalById(int hospitalId,String token,UsersInterface.GetHospitalByIdCallback getHospitalByIdCallback){
        ApiWrapper.getInstance().getHospitalById(hospitalId, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<HospitalDetailResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getHospitalByIdCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull HospitalDetailResponse hospitalDetailResponse) {
                        if (hospitalDetailResponse.getStatus()==0){
                            getHospitalByIdCallback.getSucceed(hospitalDetailResponse);
                        }else {
                            getHospitalByIdCallback.getFailed(hospitalDetailResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDoctorByHospitalId(String nameOrDepartment,String departmentId,String rank,String grade,int hospitalId,String token,UsersInterface.GetFamousDoctorListCallback getFamousDoctorListCallback){
        ApiWrapper.getInstance().getDoctorByHospitalId(nameOrDepartment, departmentId, rank, grade, hospitalId, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<FamousDoctorListResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getFamousDoctorListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull FamousDoctorListResponse famousDoctorListResponse) {
                        if (famousDoctorListResponse.getStatus()==0){
                            getFamousDoctorListCallback.getSucceed(famousDoctorListResponse);
                        }else {
                            getFamousDoctorListCallback.getFailed(famousDoctorListResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDoctorInfo(int systemUserId,String token,UsersInterface.GetFamousDoctorInfoCallback getFamousDoctorInfoCallback){
        ApiWrapper.getInstance().getDoctorInfo(systemUserId, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<FamousDoctorInfoResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getFamousDoctorInfoCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull FamousDoctorInfoResponse famousDoctorInfoResponse) {
                        if (famousDoctorInfoResponse.getStatus()==0){
                            getFamousDoctorInfoCallback.getSucceed(famousDoctorInfoResponse);
                        }else {
                            getFamousDoctorInfoCallback.getFailed(famousDoctorInfoResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getYearList(String token,UsersInterface.GetYearListCallback getYearListCallback){
        ApiWrapper.getInstance().getYearList(token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<YearResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getYearListCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull YearResponse yearResponse) {
                        if (yearResponse.getStatus()==200){
                            getYearListCallback.getSucceed(yearResponse);
                        }else {
                            getYearListCallback.getFailed(yearResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getBookListByYear(String year,String token,UsersInterface.GetBookListByYearCallback getBookListByYearCallback){
        ApiWrapper.getInstance().getBookListByYear(year, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<BookListResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getBookListByYearCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull BookListResponse bookListResponse) {
                        if (bookListResponse.getStatus()==200){
                            getBookListByYearCallback.getSucceed(bookListResponse);
                        }else {
                            getBookListByYearCallback.getFailed(bookListResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void subscribeBook(String bookId,String token,UsersInterface.SubscribeBookCallback subscribeBookCallback){
        ApiWrapper.getInstance().subscribeBook(bookId, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<SubscribeResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        subscribeBookCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull SubscribeResponse subscribeResponse) {
                        if (subscribeResponse.getStatus()==200){
                            subscribeBookCallback.getSucceed(subscribeResponse);
                        }else {
                            subscribeBookCallback.getFailed(subscribeResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void cancelSubscribeBook(String bookId,String token,UsersInterface.CancelSubscribeBookCallback cancelSubscribeBookCallback){
        ApiWrapper.getInstance().cancelSubscribeBook(bookId, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<SubscribeResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        cancelSubscribeBookCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull SubscribeResponse subscribeResponse) {
                        if (subscribeResponse.getStatus()==200){
                            cancelSubscribeBookCallback.getSucceed(subscribeResponse);
                        }else {
                            cancelSubscribeBookCallback.getFailed(subscribeResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getBookArticle(String id,String token,UsersInterface.GetBookArticleCallback getBookArticleCallback){
        ApiWrapper.getInstance().getBookArticle(id, token).compose(RxHelper.to_mian())
                .subscribe(new MyObserver<BookArticleResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponseException responseException) {
                        getBookArticleCallback.error(responseException);
                    }

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull BookArticleResponse bookArticleResponse) {
                        if (bookArticleResponse.getStatus()==200){
                            getBookArticleCallback.getSucceed(bookArticleResponse);
                        }else {
                            getBookArticleCallback.getFailed(bookArticleResponse.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
