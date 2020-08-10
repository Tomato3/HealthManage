package com.example.healthmanage.ui.activity.historydata;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.HistoryDataResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.view.DataItem;

import java.util.ArrayList;
import java.util.List;

public class HistoryDataViewModel extends BaseViewModel {

    public MutableLiveData<String> startTime = new MutableLiveData<>("");
    public MutableLiveData<String> endTime = new MutableLiveData<>("");
    UsersRemoteSource usersRemoteSource;

    public MutableLiveData<List<DataItem>> dataItemMutableLiveData = new MutableLiveData<>();
    private List<DataItem> dataItemList;
    private List<String> data;

    public HistoryDataViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getHistoryData(long memberId, String beginTime, String endTime, int pageNum) {
        usersRemoteSource.getHistoryData(memberId, beginTime, endTime, pageNum, 10,
                new UsersInterface.GetHistoryDataCallback() {
                    @Override
                    public void getSucceed(HistoryDataResponse historyDataResponse) {
                        dataItemList = new ArrayList<>();
                        if (historyDataResponse.getTotal() > 0) {
                            if (historyDataResponse.getTotal() > 10) {
                                for (int i = 0; i < 10; i++) {
                                    data = new ArrayList<>();
                                    data.add(test(historyDataResponse.getRows().get(i).getCreateTime()));
                                    data.add(test(historyDataResponse.getRows().get(i).getBloodSugar()));
                                    data.add(test(historyDataResponse.getRows().get(i).getBloodLowPressure()));
                                    data.add(test(historyDataResponse.getRows().get(i).getBloodHighPressure()));
                                    data.add(test(historyDataResponse.getRows().get(i).getBloodStick()));
                                    data.add(test(historyDataResponse.getRows().get(i).getBloodLoop()));
                                    data.add(test(historyDataResponse.getRows().get(i).getHeartRate()));
                                    data.add(test(historyDataResponse.getRows().get(i).getTemperature()));
                                    data.add(test(historyDataResponse.getRows().get(i).getFatiguePredict()));
                                    dataItemList.add(new DataItem(data));
                                }
                                dataItemMutableLiveData.postValue(dataItemList);
                            } else {
                                for (int i = 0; i < historyDataResponse.getTotal(); i++) {
                                    data = new ArrayList<>();
                                    data.add(test(historyDataResponse.getRows().get(i).getCreateTime()));
                                    data.add(test(historyDataResponse.getRows().get(i).getBloodSugar()));
                                    data.add(test(historyDataResponse.getRows().get(i).getBloodLowPressure()));
                                    data.add(test(historyDataResponse.getRows().get(i).getBloodHighPressure()));
                                    data.add(test(historyDataResponse.getRows().get(i).getBloodStick()));
                                    data.add(test(historyDataResponse.getRows().get(i).getBloodLoop()));
                                    data.add(test(historyDataResponse.getRows().get(i).getHeartRate()));
                                    data.add(test(historyDataResponse.getRows().get(i).getTemperature()));
                                    data.add(test(historyDataResponse.getRows().get(i).getFatiguePredict()));
                                    dataItemList.add(new DataItem(data));
                                }
                                dataItemMutableLiveData.postValue(dataItemList);
                            }
                            showToast("加载成功", 0);
                        } else {
                            getUiChangeEvent().getToastTxt().setValue("暂无历史数据");
                        }
                    }

                    @Override
                    public void getFailed(String msg) {
                        showToast(msg, 1);
                    }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {
                        showToast(e.getMessage(), 1);
                    }
                });
    }

    private String test(Object test) {
        return TextUtils.isEmpty(String.valueOf(test)) ? "null" : String.valueOf(test);
    }
}
