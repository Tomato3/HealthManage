package com.example.healthmanage.ui.activity.consultationinfo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.ConsultationResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.bean.recyclerview.ConsultationInformationRecyclerView;

import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class ConsultationInfoViewModel extends BaseViewModel {

    UsersRemoteSource usersRemoteSource = new UsersRemoteSource();

    public MutableLiveData<List<ConsultationInformationRecyclerView>> consultationInfoMutableLiveData = new MutableLiveData<>();

    private List<ConsultationInformationRecyclerView> consultationInformationRecyclerViewList;


    public void loadMyMessage(boolean isFirst, int state) {

        if (isFirst) {

        } else {

        }

    }

    public void getConsultationList(int state) {
        usersRemoteSource.getConsultationList(BaseApplication.getToken(), 1, 5,
                new UsersInterface.GetConsultationListCallback() {
            @Override
            public void getSucceed(ConsultationResponse consultationResponse) {
                Log.d(HTAG, "getSucceed==========>: ");
            }

            @Override
            public void getFailed(String msg) {
                Log.d(HTAG, "getFailed==========>: ");
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
//        switch (state) {
//            case 0:
//                consultationInformationRecyclerViewList = new ArrayList<>();
//
//                consultationInformationRecyclerViewList.add(new ConsultationInformationRecyclerView(
//                        "王斌", "头疼", "2020-9-1 11:11:11", 0));
//                consultationInformationRecyclerViewList.add(new ConsultationInformationRecyclerView(
//                        "王斌", "头疼", "2020-9-1 11:11:11", 0));
//                consultationInformationRecyclerViewList.add(new ConsultationInformationRecyclerView(
//                        "王斌", "头疼", "2020-9-1 11:11:11", 0));
//
//                consultationInfoMutableLiveData.setValue(consultationInformationRecyclerViewList);
//                break;
//            case 1:
//                consultationInformationRecyclerViewList = new ArrayList<>();
//
//                consultationInformationRecyclerViewList.add(new ConsultationInformationRecyclerView(
//                        "王斌", "头疼", "2020-9-1 11:11:11", 1));
//                consultationInformationRecyclerViewList.add(new ConsultationInformationRecyclerView(
//                        "王斌", "头疼", "2020-9-1 11:11:11", 1));
//                consultationInformationRecyclerViewList.add(new ConsultationInformationRecyclerView(
//                        "王斌", "头疼", "2020-9-1 11:11:11", 1));
//
//                consultationInfoMutableLiveData.setValue(consultationInformationRecyclerViewList);
//                break;
//            case 2:
//                consultationInformationRecyclerViewList = new ArrayList<>();
//
//                consultationInformationRecyclerViewList.add(new ConsultationInformationRecyclerView(
//                        "王斌", "头疼", "2020-9-1 11:11:11", 2));
//                consultationInformationRecyclerViewList.add(new ConsultationInformationRecyclerView(
//                        "王斌", "头疼", "2020-9-1 11:11:11", 2));
//                consultationInformationRecyclerViewList.add(new ConsultationInformationRecyclerView(
//                        "王斌", "头疼", "2020-9-1 11:11:11", 2));
//
//                consultationInfoMutableLiveData.setValue(consultationInformationRecyclerViewList);
//                break;
//        }

    }
}
