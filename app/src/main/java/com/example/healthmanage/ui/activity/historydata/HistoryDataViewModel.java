package com.example.healthmanage.ui.activity.historydata;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.HistoryDataResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.utils.ToolUtil;
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
    String[] name = {"测量时间：", "血糖（小数）mmol/L：", "低压mmHg：",
            "高压mmHg：", "血粘（小数）mPa.s：", "血液循环：",
            "心率次/分钟 （脉搏）：", "体温/度：", "疲劳预估："};


    public HistoryDataViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void getHistoryData(long memberId, String beginTime, String endTime, int pageNum) {
        usersRemoteSource.getHistoryData(memberId, beginTime, endTime, pageNum, 10,
                new UsersInterface.GetHistoryDataCallback() {
                    @Override
                    public void getSucceed(HistoryDataResponse historyDataResponse) {
                        dataItemList = new ArrayList<>();
                        if (historyDataResponse.getData().getTotal() > 10) {
                            for (int i = 0; i < 10; i++) {
                                data = new ArrayList<>();
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getCreateTime()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getBloodSugar()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getBloodLowPressure()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getBloodHighPressure()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getBloodStick()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getBloodLoop()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getHeartRate()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getTemperature()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getFatiguePredict()));
                                dataItemList.add(new DataItem(name, data));
                            }
                            dataItemMutableLiveData.postValue(dataItemList);
                        } else {
                            for (int i = 0; i < historyDataResponse.getData().getTotal(); i++) {
                                data = new ArrayList<>();
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getCreateTime()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getBloodSugar()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getBloodLowPressure()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getBloodHighPressure()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getBloodStick()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getBloodLoop()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getHeartRate()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getTemperature()));
                                data.add(ToolUtil.isNull(historyDataResponse.getData().getRows().get(i).getFatiguePredict()));
                                dataItemList.add(new DataItem(name, data));
                            }
                            dataItemMutableLiveData.postValue(dataItemList);
                        }
                        showToast("加载成功", 0);
                    }

                    @Override
                    public void getFailed(String msg) {
                        showToast("暂无历史记录", 1);
                    }

                    @Override
                    public void error(ExceptionHandle.ResponseException e) {
                    }
                });
    }


}
