package com.example.healthmanage.ui.activity.abnormaldata;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.AbnormalDataResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.view.DataItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class AbnormalDataViewModel extends BaseViewModel {

    private UsersRemoteSource usersRemoteSource;

    public MutableLiveData<List<DataItem>> dataItemMutableLiveData =
            new MutableLiveData<>();

    private List<DataItem> dataItemList;
    private List<String> data;

    public AbnormalDataViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getAbnormalData(long taskId, String dataDate) {
        usersRemoteSource.getAbnormalData(taskId, dataDate, new UsersInterface.GetAbnormalDataCallback() {
            @Override
            public void getSucceed(AbnormalDataResponse abnormalDataResponse) {
                dataItemList = new ArrayList<>();
                data = new ArrayList<>();
                if (abnormalDataResponse.getData().getBloodSugarList() != null) {
                    for (int i = 0; i < abnormalDataResponse.getData().getBloodSugarList().size(); i++) {
                        data = new ArrayList<>();
                        data.add(test(abnormalDataResponse.getData().getBloodSugarList().get(i).getCreateTime()));
                        data.add(test(abnormalDataResponse.getData().getBloodSugarList().get(i).getBloodSugar()));
                        data.add(test(abnormalDataResponse.getData().getBloodSugarList().get(i).getBloodLowPressure()));
                        data.add(test(abnormalDataResponse.getData().getBloodSugarList().get(i).getBloodHighPressure()));
                        data.add(test(abnormalDataResponse.getData().getBloodSugarList().get(i).getBloodStick()));
                        data.add(test(abnormalDataResponse.getData().getBloodSugarList().get(i).getBloodLoop()));
                        data.add(test(abnormalDataResponse.getData().getBloodSugarList().get(i).getHeartRate()));
                        data.add(test(abnormalDataResponse.getData().getBloodSugarList().get(i).getTemperature()));
                        data.add(test(abnormalDataResponse.getData().getBloodSugarList().get(i).getFatiguePredict()));
                        dataItemList.add(new DataItem(data));
                    }
                    dataItemMutableLiveData.setValue(dataItemList);
                }
            }

            @Override
            public void getFailed(String msg) {
                Log.d(HTAG, "getFailed==========>: ");
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                Log.d(HTAG, "error==========>: ");
            }
        });
    }

    private String test(Object test) {
        return TextUtils.isEmpty(String.valueOf(test)) ? "null" : String.valueOf(test);
    }
}
