package com.example.healthmanage.ui.activity.famousDoctorHall;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.AppraiseResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.DepartMentResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.FamousDoctorInfoResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.FamousDoctorListResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.HospitalDetailResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.HospitalListResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.HospitalTypeResponse;

import java.util.List;

/**
 * desc:
 * date:2021/6/28 15:43
 * author:bWang
 */
public class DoctorHallViewModel extends BaseViewModel {
    private UsersRemoteSource mUsersRemoteSource;
    public MutableLiveData<List<DepartMentResponse.DataBean>> departmentMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<FamousDoctorListResponse.DataBean>> famousDoctorMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<HospitalListResponse.DataBean>> hospitalMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> hospitalName = new MutableLiveData<>();
    public MutableLiveData<String> hospitalRank = new MutableLiveData<>();
    public MutableLiveData<String> hospitalAddress = new MutableLiveData<>();
    public MutableLiveData<HospitalDetailResponse.DataBean> hospitalDetail = new MutableLiveData<>();
    public MutableLiveData<List<HospitalTypeResponse.DataBean>> typeMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<FamousDoctorInfoResponse.DataBean> doctorInfoLiveData = new MutableLiveData<>();
    public MutableLiveData<List<AppraiseResponse.DataBean>> mAppraiseBeanMutableLiveData = new MutableLiveData<>();

    public DoctorHallViewModel() {
        mUsersRemoteSource = new UsersRemoteSource();
    }

    public void getHospitalDepartment(){
        mUsersRemoteSource.getHospitalDepartment(BaseApplication.getToken(), new UsersInterface.GetHospitalDepartmentCallback() {
            @Override
            public void getSucceed(DepartMentResponse departMentResponse) {
                if (departMentResponse.getData()!=null && departMentResponse.getData().size()>0){
                    departmentMutableLiveData.setValue(departMentResponse.getData());
                }else {
                    departmentMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                departmentMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                departmentMutableLiveData.setValue(null);
            }
        });

    }

    public void getDoctorList(String nameOrHospital,String addr,String departmentId,String rank,String grade){
        mUsersRemoteSource.getDoctorList(nameOrHospital, addr, departmentId, rank, grade,BaseApplication.getToken(), new UsersInterface.GetFamousDoctorListCallback() {
            @Override
            public void getSucceed(FamousDoctorListResponse famousDoctorListResponse) {
                if (famousDoctorListResponse.getData()!=null && famousDoctorListResponse.getData().size()>0){
                    famousDoctorMutableLiveData.setValue(famousDoctorListResponse.getData());
                }else {
                    famousDoctorMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                famousDoctorMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                famousDoctorMutableLiveData.setValue(null);
            }
        });
    }

    public void getHospitalList(String nameOrHospital,String address,String typeId){
        mUsersRemoteSource.getHospitalList(nameOrHospital, address, typeId, BaseApplication.getToken(), new UsersInterface.GetHospitalListCallback() {
            @Override
            public void getSucceed(HospitalListResponse hospitalListResponse) {
                if (hospitalListResponse.getData()!=null && hospitalListResponse.getData().size()>0){
                    hospitalMutableLiveData.setValue(hospitalListResponse.getData());
                }else {
                    hospitalMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                hospitalMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                hospitalMutableLiveData.setValue(null);
            }
        });
    }

    public void getHospitalById(int hospitalId){
        mUsersRemoteSource.getHospitalById(hospitalId, BaseApplication.getToken(), new UsersInterface.GetHospitalByIdCallback() {
            @Override
            public void getSucceed(HospitalDetailResponse hospitalDetailResponse) {
                if (hospitalDetailResponse.getData()!=null){
                    hospitalName.setValue(hospitalDetailResponse.getData().getName());
                    hospitalRank.setValue(hospitalDetailResponse.getData().getAppHospitalType().getName());
                    hospitalAddress.setValue(hospitalDetailResponse.getData().getAddr());
                    hospitalDetail.setValue(hospitalDetailResponse.getData());
                }else {
                    hospitalName.setValue(null);
                    hospitalRank.setValue(null);
                    hospitalAddress.setValue(null);
                    hospitalDetail.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                hospitalDetail.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                hospitalDetail.setValue(null);
            }
        });
    }

    public void getDoctorByHospitalId(String nameOrDepartment,String departmentId,String rank,String grade,int hospitalId){
        mUsersRemoteSource.getDoctorByHospitalId(nameOrDepartment, departmentId, rank, grade, hospitalId, BaseApplication.getToken(), new UsersInterface.GetFamousDoctorListCallback() {
            @Override
            public void getSucceed(FamousDoctorListResponse famousDoctorListResponse) {
                if (famousDoctorListResponse.getData()!=null && famousDoctorListResponse.getData().size()>0){
                    famousDoctorMutableLiveData.setValue(famousDoctorListResponse.getData());
                }else {
                    famousDoctorMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                famousDoctorMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                famousDoctorMutableLiveData.setValue(null);
            }
        });
    }

    public void getHospitalTypeList(){
        mUsersRemoteSource.getHospitalTypeList(BaseApplication.getToken(), new UsersInterface.GetHospitalTypeListCallback() {
            @Override
            public void getSucceed(HospitalTypeResponse hospitalTypeResponse) {
                if (hospitalTypeResponse.getData()!=null && hospitalTypeResponse.getData().size()>0){
                    typeMutableLiveData.setValue(hospitalTypeResponse.getData());
                }else {
                    typeMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                typeMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                typeMutableLiveData.setValue(null);
            }
        });
    }

    public void getDoctorInfo(int systemUserId){
        mUsersRemoteSource.getDoctorInfo(systemUserId, BaseApplication.getToken(), new UsersInterface.GetFamousDoctorInfoCallback() {
            @Override
            public void getSucceed(FamousDoctorInfoResponse famousDoctorInfoResponse) {
                if (famousDoctorInfoResponse.getData()!=null){
                    doctorInfoLiveData.setValue(famousDoctorInfoResponse.getData());
                }else {
                    doctorInfoLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    public void getAppraiseList(int systemUserId){
        mUsersRemoteSource.getAppraiseList(systemUserId, BaseApplication.getToken(), new UsersInterface.GetAppraiseListCallback() {
            @Override
            public void getSucceed(AppraiseResponse appraiseResponse) {
                if (appraiseResponse.getData()!=null && appraiseResponse.getData().size()>0){
                    mAppraiseBeanMutableLiveData.setValue(appraiseResponse.getData());
                }else {
                    mAppraiseBeanMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                mAppraiseBeanMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mAppraiseBeanMutableLiveData.setValue(null);
            }
        });
    }

}
