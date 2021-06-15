package com.example.healthmanage.ui.fragment.qualification;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.ProfessionBeanResponse;
import com.example.healthmanage.bean.QualificationInputItem;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;

import java.util.List;

/**
 * Description:
 * Author:bwang
 * Date:2020/12/2 17:01
 */
public class FirstStepViewModel extends BaseViewModel {

    public MutableLiveData<String> title = new MutableLiveData<>("实名资质认证");
    public MutableLiveData<String> operationText = new MutableLiveData<>("下一步");
    public MutableLiveData<String> profession = new MutableLiveData<>("请选择您的职业");
    public MutableLiveData<List<QualificationInputItem>> qualificationInputItemListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<ProfessionBeanResponse.DataBean>> jobMutableLiveData = new MutableLiveData<>();

    private UsersRemoteSource usersRemoteSource ;

    public FirstStepViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getJobList(){
        usersRemoteSource.getJobDataList(new UsersInterface.GetJobDataCallback() {
            @Override
            public void sendSucceed(ProfessionBeanResponse professionBeanResponse) {
                if (professionBeanResponse.getData() != null){
                    jobMutableLiveData.postValue(professionBeanResponse.getData());
                }else {
                    jobMutableLiveData.postValue(null);
                }
            }

            @Override
            public void sendFailed(String msg) {

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

}
