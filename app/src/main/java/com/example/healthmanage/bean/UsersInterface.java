package com.example.healthmanage.bean;

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
import com.example.healthmanage.bean.network.response.SmsCodeResponse;
import com.example.healthmanage.bean.network.response.ServicePlanResponse;
import com.example.healthmanage.bean.network.response.TaskDetailResponse;
import com.example.healthmanage.bean.network.response.TaskResponse;
import com.example.healthmanage.bean.network.response.WeatherResponse;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.academicJournals.response.AddOrEditSucceedResponse;
import com.example.healthmanage.ui.activity.academicJournals.response.PeriodicalInfoResponse;
import com.example.healthmanage.ui.activity.academicJournals.response.PeriodicalListResponse;
import com.example.healthmanage.ui.activity.consultation.response.AddConsultationPlanResponse;
import com.example.healthmanage.ui.activity.consultation.response.AddPatientInfoResponse;
import com.example.healthmanage.ui.activity.consultation.response.ConsultationListResponse;
import com.example.healthmanage.ui.activity.consultation.response.DoctorTeamListResponse;
import com.example.healthmanage.ui.activity.delegate.response.CreateDelegateResponse;
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
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportConfirmResponse;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportDetailResponse;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportResponse;
import com.example.healthmanage.ui.activity.invitemember.response.InviteSucceedResponse;
import com.example.healthmanage.ui.activity.memberdetail.response.CreateTaskResponse;
import com.example.healthmanage.ui.activity.memberdetail.response.HealthDataResponse;
import com.example.healthmanage.ui.activity.memberdetail.response.SpiritHealthResponse;
import com.example.healthmanage.ui.activity.mytask.response.HealthTaskDetailResponse;
import com.example.healthmanage.ui.activity.mytask.response.TransferResponse;
import com.example.healthmanage.ui.activity.mytask.response.UpdateTaskResponse;
import com.example.healthmanage.ui.activity.notice.response.NoticeResponse;
import com.example.healthmanage.ui.activity.notice.response.TeamApplyResponse;
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
import com.example.healthmanage.ui.activity.referral.response.ReferralResponse;
import com.example.healthmanage.ui.activity.signmember.response.SignMemberResponse;
import com.example.healthmanage.ui.activity.signmember.response.SignMemberSuccessResponse;
import com.example.healthmanage.ui.activity.team.response.BusinessDealListResponse;
import com.example.healthmanage.ui.activity.team.response.BusinessDetailResponse;
import com.example.healthmanage.ui.activity.team.response.DoctorTeamResponse;
import com.example.healthmanage.ui.activity.team.response.EditResponse;
import com.example.healthmanage.ui.activity.team.response.QuitTeamResponse;
import com.example.healthmanage.ui.activity.team.response.SearchTeamResponse;
import com.example.healthmanage.ui.activity.team.response.SendResponse;
import com.example.healthmanage.ui.activity.team.response.SignTeamResponse;
import com.example.healthmanage.ui.activity.team.response.TeamMemberResponse;
import com.example.healthmanage.ui.activity.temperature.response.HealthTaskResponse;
import com.example.healthmanage.ui.activity.temperature.response.InsertResponse;
import com.example.healthmanage.ui.activity.temperature.response.PrescriptionResponse;
import com.example.healthmanage.ui.activity.temperature.response.RefusalResponse;
import com.example.healthmanage.ui.activity.temperature.response.TemperatureResponse;
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

public interface UsersInterface {

    interface LoginResponseCallback {
        void loginSucceed(LoginResponse loginResponse);

        void loginFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface getSmsCodeCallback {
        void sendSucceed(SmsCodeResponse smsCodeResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface RegisterCallback {
        void registerSucceed();

        void registerFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface ForgetPasswordCallback {
        void forgetSucceed(GeneralResponse generalResponse);

        void forgetFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface SearchMembersCallback {
        void searchSucceed(InviteMemberResponse searchMemberResponse);

        void searchFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface InviteUserMemberCallback{
        void inviteSucceed(InviteSucceedResponse inviteSucceedResponse);

        void inviteFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetMemberTeamByNameCallback{
        void getSucceed(MemberTeamListResponse memberTeamListResponse);
        void getFailed(String msg);
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

    interface CreateHealthTaskCallback{
        void createSucceed(CreateTaskResponse createTaskResponse);
        void createFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }


    interface LoadMyTaskCallback {
        void loadSucceed(TaskResponse taskResponse);

        void loadFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHealthTaskListCallback{
        void getSucceed(HealthTaskResponse healthTaskResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetTransferHealthTaskListCallback{
        void getSucceed(HealthTaskResponse healthTaskResponse);
        void getFailed(String msg);
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

    interface SearchDoctorCallback {
        void searchSucceed(DoctorListResponse doctorListResponse);

        void searchFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetAbnormalDataCallback {
        void getSucceed(AbnormalDataResponse abnormalDataResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHistoryDataCallback {
        void getSucceed(HistoryDataResponse historyDataResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetMemberInfoCallback {
        void getSucceed(MemberInfoResponse memberInfoResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetServicePlanListCallback {
        void getSucceed(ServicePlanResponse servicePlanResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface UploadServiceResultCallback {
        void uploadSucceed(GeneralResponse generalResponse);

        void uploadFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetAirListCallback {
        void getSucceed(AirResponse airResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetSpiritListCallback {
        void getSucceed(SpiritHealthResponse spiritHealthResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetInviteCodeCallback {
        void getSucceed(GeneralResponse generalResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetNursingDataCallback {
        void getSucceed(NursingResponse nursingResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface WriteInviteCodeCallback {
        void writeSucceed(GeneralResponse generalResponse);

        void writeFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetMyDoctorCallback {
        void getSucceed(MyDoctorResponse myDoctorResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetMyDoctorDetailCallback {
        void getSucceed(DoctorDetailResponse doctorDetailResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetConsultationListCallback {
        void getSucceed(ConsultationResponse consultationResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface ReplayConsultationCallback {
        void replaySucceed(GeneralResponse generalResponse);

        void replayFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetConsultationRecordCallback {
        void getSucceed(ConsultationRecordResponse consultationRecordResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetJobDataCallback {
        void sendSucceed(ProfessionBeanResponse professionBeanResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetDepartmentDataCallback {
        void sendSucceed(DepartmentResponse departmentResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetDepartmentByNameCallback{
        void sendSucceed(DepartmentResponse departmentResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHospitalByNameCallback{
        void sendSucceed(HospitalResponse hospitalResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface UpLoadIdCardCallback{
        void sendSucceed(UploadResponse uploadResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface UpCertificateCallback{
        void sendSucceed(CertificateResponse certificateResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface SaveDoctorInfoCallback{
        void sendSucceed(UploadResponse uploadResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetDoctorInfoCallback{
        void sendSucceed(DoctorInfoResponse doctorInfoResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface UpdateDoctorInfoCallback{
        void sendSucceed(UploadResponse uploadResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetUserInfoCallback{
        void sendSucceed(HealthRecordResponse healthRecordResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetCheckReportListCallback{
        void sendSucceed(CheckReportResponse checkReportResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHistoryAssessListCallback{
        void sendSucceed(HistoryAssessListResponse historyAssessListResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetMedicalRecordAllCallback{
        void sendSucceed(MedicineBookResponse medicineBookResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHealthReportAllCallback{
        void sendSucceed(HealthReportResponse healthReportResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface SaveHealthReportCallback{
        void sendSucceed(HealthReportConfirmResponse healthReportConfirmResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHealthReportCallback{
        void sendSucceed(HealthReportDetailResponse healthReportDetailResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetWorkPlanByTimeCallback{
        void sendSucceed(WorkPlanListResponse workPlanListResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface UpdateWorkPlanByIdCallback{
        void updateSucceed(UpdateWorkResponse updateWorkResponse);

        void updateFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface InsertWorkPlanCallback{
        void insertSucceed(InsertPlanResponse insertPlanResponse);

        void insertFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetSignMemberCallback{
        void sendSucceed(SignMemberResponse signMemberResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetNotSignMemberCallback{
        void sendSucceed(SignMemberResponse signMemberResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface AddSignMemberCallback{
        void sendSucceed(SignMemberSuccessResponse signMemberSuccessResponse);

        void sendFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface UpdateTaskCallback{
        void updateSucceed(UpdateTaskResponse updateTaskResponse);

        void updateFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetExceptionDataCallback{
        void getSucceed(HealthTaskDetailResponse healthTaskDetailResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface SendTransferTaskCallback{
        void transferSucceed(TransferResponse transferResponse);

        void transferFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHospitalDepartmentListCallback{
        void getSucceed(DoctorTeamListResponse doctorTeamListResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface InsertPatientExamineCallback{
        void insertSucceed(AddPatientInfoResponse addPatientInfoResponse);

        void insertFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetPatientExamineCallback{
        void getSucceed(ConsultationListResponse consultationListResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface UpdatePatientExamineCallback{
        void updateSucceed(AddConsultationPlanResponse addConsultationPlanResponse);

        void updateFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface InsertPatientReferralCallback{
        void insertSucceed(InsertReferralResponse insertReferralResponse);

        void insertFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetPatientReferralCallback{
        void getSucceed(ReferralResponse referralResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHealthConsultCallback{
        void getSucceed(TemperatureResponse temperatureResponse);

        void getFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface UpdateHealthConsultCallback{
        void updateSucceed(UpdateResponse updateResponse);

        void updateFailed(String msg);

        void error(ExceptionHandle.ResponseException e);
    }

    interface InsertHealthConsultDrugModelCallback{
        void insertSucceed(InsertResponse insertResponse);
        void insertFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface InsertHealthConsultDrugCallback{
        void insertSucceed(InsertResponse insertResponse);
        void insertFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHealthConsultDrugModelCallback{
        void getSucceed(PrescriptionResponse prescriptionResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHealthConsultDrugBackCallback{
        void getSucceed(RefusalResponse refusalResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface TeamInvitationListCallback{
        void getSucceed(SearchTeamResponse searchTeamResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetDoctorByPhoneCallback{
        void getSucceed(SearchTeamResponse searchTeamResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface SendApplyCallback{
        void sendSucceed(SendResponse sendResponse);
        void sendFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetDoctorTeamApplyListCallback{
        void getSucceed(NoticeResponse noticeResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface DoctorTeamApplyListCallback{
        void getSucceed(TeamApplyResponse teamApplyResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface SignOrNotCallback{
        void getSucceed(SignTeamResponse signTeamResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetDoctorTeamCallback{
        void getSucceed(DoctorTeamResponse doctorTeamResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetDoctorTeamListCallback{
        void getSucceed(TeamMemberResponse teamMemberResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface QuitTeamCallback{
        void quitSucceed(QuitTeamResponse quitTeamResponse);
        void quitFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetBusineServiceListCallback{
        void getSucceed(BusinessDealListResponse businessDealListResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetBusineServiceInfoCallback{
        void getSucceed(BusinessDetailResponse businessDetailResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface EditBusineServiceCallback{
        void editSucceed(EditResponse editResponse);
        void editFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetBusineServiceCallback{
        void getSucceed(DelegateListResponse delegateListResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface AddBusineServiceCallback{
        void addSucceed(CreateDelegateResponse createDelegateResponse);
        void addFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetPersonalRequestListCallback{
        void getSucceed(RequestResponse requestResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface AddPersonalRequestCallback{
        void addSucceed(AddRequestResponse addRequestResponse);
        void addFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetPersonalRequestCallback{
        void getSucceed(ReplyResponse replyResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface CancelPersonalRequestCallback{
        void cancelSucceed(CancelResponse cancelResponse);
        void cancelFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetPeriodicalListCallback{
        void getSucceed(PeriodicalListResponse periodicalListResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetPeriodicalCallback{
        void getSucceed(PeriodicalInfoResponse periodicalInfoResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface AddPeriodicalCallback{
        void addSucceed(AddOrEditSucceedResponse addOrEditSucceedResponse);
        void addFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface EditPeriodicalCallback{
        void editSucceed(AddOrEditSucceedResponse addOrEditSucceedResponse);
        void editFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetMemberTeamListCallback{
        void getSucceed(MemberTeamListResponse memberTeamListResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface EditMemberTeamCallback{
        void editSucceed(IsFocusResponse isFocusResponse);
        void editFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface DeleteMemberTeamCallback{
        void deleteSucceed(DeleteMemberResponse deleteMemberResponse);
        void deleteFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHospitalDepartmentCallback{
        void getSucceed(DepartMentResponse departMentResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetFamousDoctorListCallback{
        void getSucceed(FamousDoctorListResponse famousDoctorListResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHospitalTypeListCallback{
        void getSucceed(HospitalTypeResponse hospitalTypeResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHospitalListCallback{
        void getSucceed(HospitalListResponse hospitalListResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetHospitalByIdCallback{
        void getSucceed(HospitalDetailResponse hospitalDetailResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetFamousDoctorInfoCallback{
        void getSucceed(FamousDoctorInfoResponse famousDoctorInfoResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetYearListCallback{
        void getSucceed(YearResponse yearResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetBookListByYearCallback{
        void getSucceed(BookListResponse bookListResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface SubscribeBookCallback{
        void getSucceed(SubscribeResponse subscribeResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface CancelSubscribeBookCallback{
        void getSucceed(SubscribeResponse subscribeResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

    interface GetBookArticleCallback{
        void getSucceed(BookArticleResponse bookArticleResponse);
        void getFailed(String msg);
        void error(ExceptionHandle.ResponseException e);
    }

}
