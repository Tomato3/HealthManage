package com.example.healthmanage.ui.activity.memberdetail;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.GeneralResponse;
import com.example.healthmanage.bean.HealthDataResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.bean.WeatherResponse;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.widget.DataItem;
import com.example.healthmanage.widget.DropdownBar;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;

public class MemberDetailViewModel extends BaseViewModel {

    public MutableLiveData<String> location =
            new MutableLiveData<>("苏州市");

    public MutableLiveData<String> weatherReminder =
            new MutableLiveData<>("温度过高，注意防晒！");

    public MutableLiveData<ArrayList<DropdownBar>>
            dropdownBarLists = new MutableLiveData<>();

    public MutableLiveData<DataItem> dataItemTodayEnvironment = new MutableLiveData<>();
    public MutableLiveData<DataItem> dataItemTodayHealth = new MutableLiveData<>();
    public MutableLiveData<DataItem> dataItemBodyHealth = new MutableLiveData<>();
    public MutableLiveData<DataItem> dataItemSpiritHealth = new MutableLiveData<>();
    public MutableLiveData<Boolean> currentFocusState = new MutableLiveData<>();
    private UsersRemoteSource usersRemoteSource;

    String dataDate;

    public MemberDetailViewModel() {

        usersRemoteSource = new UsersRemoteSource();
        dataItemBodyHealth.setValue(new DataItem("运动：40", "睡眠：40", "饮食：40", "测评：40"));
        dataItemSpiritHealth.setValue(new DataItem("运动：40", "睡眠：40", "饮食：40", "测评：40"));
    }

    public void changeFocus(String userId, boolean focusState) {
        if (focusState) {
            usersRemoteSource.deleteFocus(userId, new UsersInterface.DeleteFocusCallback() {
                @Override
                public void deleteSucceed() {
                    getTitleToolBar().setRightIconSrc(R.drawable.fragment_main_my_member_focus_normal);
                    getUiChangeEvent().getToastTxt().setValue("取消关注成功");
                    currentFocusState.setValue(false);
                }

                @Override
                public void deleteFailed(String msg) {
                    getUiChangeEvent().getToastTxt().setValue(msg);
                }

                @Override
                public void error(ExceptionHandle.ResponseException e) {
                    Log.d("TAG", "Error: ===>" + e.getMessage());

                }
            });
        } else {
            usersRemoteSource.addFocus(String.valueOf(BaseApplication.getUserInfoBean().getSysId()), userId, new UsersInterface.AddFocusCallback() {
                @Override
                public void addSucceed() {
                    Log.d("TAG", "deleteSucceed: " + focusState);
                    getUiChangeEvent().getToastTxt().setValue("添加关注成功");
                    getTitleToolBar().setRightIconSrc(R.drawable.fragment_main_my_member_focus_selected);
                    currentFocusState.setValue(true);
                }

                @Override
                public void addFailed(String msg) {
                    getUiChangeEvent().getToastTxt().setValue(msg);
                }

                @Override
                public void error(ExceptionHandle.ResponseException e) {
                    Log.d("TAG", "Error: ===>" + e.getMessage());

                }
            });
        }

    }

    public void getWeather() {
        usersRemoteSource.getWeather("苏州", new UsersInterface.GetWeatherCallback() {
            @Override
            public void getSucceed(WeatherResponse weatherResponse) {
                if (weatherResponse.getData() == null) {
                    getUiChangeEvent().getToastTxt().setValue("暂无数据");
                } else {
                    getUiChangeEvent().getToastTxt().setValue("天气刷新成功");
                }
                dataItemTodayEnvironment.setValue(new DataItem("温度：" + weatherResponse.getData().getTemperature_current()
                        , "湿度：" + weatherResponse.getData().getHumidity()
                        , "PM2.5：" + weatherResponse.getData().getPm25()
                        , "PM10：" + weatherResponse.getData().getPm10()));

            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                Log.d("TAG", "Error: ===>" + e.getMessage());
            }
        });
    }

    public void getHealthDataList(String userId) {
        usersRemoteSource.getHealthList(userId, new UsersInterface.GetHealthListCallback() {
            @Override
            public void getSucceed(HealthDataResponse healthDataResponse) {
                if (healthDataResponse.getData().getBloodSugarSendList() == null) {
                    getUiChangeEvent().getToastTxt().setValue("暂无健康数据");
                } else {
                    dataDate =
                            healthDataResponse.getData().getBloodSugarSendList().get(0).getCreateTime();
                    getUiChangeEvent().getToastTxt().setValue("刷新成功");
                    dataItemTodayHealth.setValue(new DataItem("脉搏：" + healthDataResponse.getData().getBloodSugarSendList().get(0).getHeartRate(),
                            "收缩压：" + healthDataResponse.getData().getBloodSugarSendList().get(0).getSmallBloodHighPressure() +
                                    "-" + healthDataResponse.getData().getBloodSugarSendList().get(0).getBigBloodHighPressure(),
                            "舒张压：" + healthDataResponse.getData().getBloodSugarSendList().get(0).getSmallBloodLowPressure() +
                                    "-" + healthDataResponse.getData().getBloodSugarSendList().get(0).getBigBloodLowPressure(),
                            "血糖：" + healthDataResponse.getData().getBloodSugarSendList().get(0).getSmallBloodSugar() + "-" +
                                    healthDataResponse.getData().getBloodSugarSendList().get(0).getBigBloodSugar()
                    ));
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

    public void createMyTask(long userId, String title, String content) {
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
            usersRemoteSource.createMyTask(userId,
                    title,
                    content,
                    BaseApplication.getUserInfoBean().getSysId(),
                    dataDate,
                    new UsersInterface.CreateMyTaskCallback() {
                        @Override
                        public void createSucceed(GeneralResponse generalResponse) {
                            LiveEventBus.get("close").post(false);
                            showToast("生成成功", 0);
                        }

                        @Override
                        public void createFailed(String msg) {
                            showToast(msg, 1);
                        }

                        @Override
                        public void error(ExceptionHandle.ResponseException e) {
                            showToast(e.getMessage(), 1);
                        }
                    });
        } else {
            getUiChangeEvent().getToastTxt().setValue("标题、描述不能为空");
        }

    }
}
