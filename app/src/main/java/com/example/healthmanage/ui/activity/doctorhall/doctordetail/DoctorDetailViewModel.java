package com.example.healthmanage.ui.activity.doctorhall.doctordetail;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.DoctorDetailResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;


public class DoctorDetailViewModel extends BaseViewModel {

    public String avatar;
    public MutableLiveData<String> doctorName = new MutableLiveData<>();
    public MutableLiveData<String> doctorProfessionalTitle = new MutableLiveData<>();
    public MutableLiveData<String> doctorSittingPlace = new MutableLiveData<>();
    public MutableLiveData<String> doctorSpeciality = new MutableLiveData<>();
    public MutableLiveData<String> doctorExperience = new MutableLiveData<>();

    private UsersRemoteSource usersRemoteSource = new UsersRemoteSource();

    public void getMyDoctorDetail(long doctorId) {
        usersRemoteSource.getMyDoctorDetail(BaseApplication.getToken(), doctorId, new UsersInterface.GetMyDoctorDetailCallback() {
            @Override
            public void getSucceed(DoctorDetailResponse doctorDetailResponse) {
                if (doctorDetailResponse.getData() == null) {
                    showToast(doctorDetailResponse.getMessage(), 1);
                } else {
                    avatar = doctorDetailResponse.getData().getAvatar();
                    doctorName.setValue(doctorDetailResponse.getData().getName());
                    doctorProfessionalTitle.setValue(doctorDetailResponse.getData().getProfessionalTitle());
                    doctorSittingPlace.setValue(doctorDetailResponse.getData().getSittingPlace());
                    doctorSpeciality.setValue(doctorDetailResponse.getData().getSpeciality());
                    doctorExperience.setValue(doctorDetailResponse.getData().getExperience());
                }

            }

            @Override
            public void getFailed(String msg) {

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }
}
