package com.example.healthmanage.ui.activity.mytask.mytaskdetail.abnormaldata;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.AbnormalDataResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.bean.recyclerview.DataItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class AbnormalDataViewModel extends BaseViewModel {

    private UsersRemoteSource usersRemoteSource;

    public MutableLiveData<List<DataItem>> dataItemMutableLiveData =
            new MutableLiveData<>();

    private List<DataItem> dataItemList;
    private List<String> data;
    String[] name = {"测量时间：", "血糖（小数）mmol/L：", "低压mmHg：",
            "高压mmHg：", "血粘（小数）mPa.s：", "血液循环：",
            "心率次/分钟 （脉搏）：", "体温/度：", "疲劳预估："};

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
                        data.add(ToolUtil.isNull(abnormalDataResponse.getData().getBloodSugarList().get(i).getCreateTime()));
                        data.add(ToolUtil.isNull(abnormalDataResponse.getData().getBloodSugarList().get(i).getBloodSugar()));
                        data.add(ToolUtil.isNull(abnormalDataResponse.getData().getBloodSugarList().get(i).getBloodLowPressure()));
                        data.add(ToolUtil.isNull(abnormalDataResponse.getData().getBloodSugarList().get(i).getBloodHighPressure()));
                        data.add(ToolUtil.isNull(abnormalDataResponse.getData().getBloodSugarList().get(i).getBloodStick()));
                        data.add(ToolUtil.isNull(abnormalDataResponse.getData().getBloodSugarList().get(i).getBloodLoop()));
                        data.add(ToolUtil.isNull(abnormalDataResponse.getData().getBloodSugarList().get(i).getHeartRate()));
                        data.add(ToolUtil.isNull(abnormalDataResponse.getData().getBloodSugarList().get(i).getTemperature()));
                        data.add(ToolUtil.isNull(abnormalDataResponse.getData().getBloodSugarList().get(i).getFatiguePredict()));
                        dataItemList.add(new DataItem(name, data));
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
}
