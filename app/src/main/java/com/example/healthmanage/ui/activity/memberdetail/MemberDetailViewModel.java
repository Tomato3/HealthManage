package com.example.healthmanage.ui.activity.memberdetail;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.AirResponse;
import com.example.healthmanage.bean.network.response.GeneralResponse;
import com.example.healthmanage.bean.network.response.HealthDataResponse;
import com.example.healthmanage.bean.network.response.NursingResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.bean.network.response.WeatherResponse;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.DataItem;
import com.example.healthmanage.widget.DropdownBar;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;
import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MemberDetailViewModel extends BaseViewModel {

    public MutableLiveData<String> location =
            new MutableLiveData<>("");

    public MutableLiveData<String> weatherReminder =
            new MutableLiveData<>("温度过高，注意防晒！");

    public MutableLiveData<ArrayList<DropdownBar>>
            dropdownBarLists = new MutableLiveData<>();


    public MutableLiveData<DataItem> dataItemTodayEnvironment = new MutableLiveData<>();
    public MutableLiveData<DataItem> dataItemAir = new MutableLiveData<>();
    public MutableLiveData<DataItem> dataItemTodayHealth = new MutableLiveData<>();
    public MutableLiveData<DataItem> dataItemBodyHealth = new MutableLiveData<>();
    public MutableLiveData<DataItem> dataItemSpiritHealth = new MutableLiveData<>();
    public MutableLiveData<Boolean> currentFocusState = new MutableLiveData<>();

    public MutableLiveData<Boolean> todayEnvironmentVisible = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> todayHealthVisible = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> bodyHealthVisible = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> spiritHealthVisible = new MutableLiveData<>(false);

    private UsersRemoteSource usersRemoteSource;

    public MemberDetailViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void changeFocus(String userId, boolean focusState) {
        if (focusState) {
            usersRemoteSource.deleteFocus(userId, new UsersInterface.DeleteFocusCallback() {
                @Override
                public void deleteSucceed() {
                    getTitleToolBar().setRightIconSrc(R.drawable.fragment_main_my_member_focus_normal);
                    showToast("取消关注成功", 0);
                    LiveEventBus.get("Refresh").post(true);
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
                    showToast("添加关注成功", 0);
                    getTitleToolBar().setRightIconSrc(R.drawable.fragment_main_my_member_focus_selected);
                    currentFocusState.setValue(true);
                    LiveEventBus.get("Refresh").post(true);
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
        if (location.getValue() != null) {
            usersRemoteSource.getWeather(location.getValue(), new UsersInterface.GetWeatherCallback() {
                @Override
                public void getSucceed(WeatherResponse weatherResponse) {
                    if (weatherResponse.getData() == null) {
                    } else {
                        List<String> data = new ArrayList<>();
                        data.add("温度：" + ToolUtil.isNull(weatherResponse.getData().getTemperature_current()));
                        data.add("湿度：" + ToolUtil.isNull(weatherResponse.getData().getHumidity()));
                        data.add("天气：" + ToolUtil.isNull(weatherResponse.getData().getWeather()));
                        data.add("风向风速：" + ToolUtil.isNull(weatherResponse.getData().getWind()));
                        data.add("PM2.5：" + ToolUtil.isNull(weatherResponse.getData().getPm25()));
                        data.add("PM10：" + ToolUtil.isNull(weatherResponse.getData().getPm10()));
                        data.add("温度：" + ToolUtil.isNull(weatherResponse.getData().getTemperature_current()));
                        data.add("温度：" + ToolUtil.isNull(weatherResponse.getData().getTemperature_current()));
                        dataItemTodayEnvironment.setValue(new DataItem("室外环境：", data));
                    }
                }

                @Override
                public void getFailed(String msg) {
                    getUiChangeEvent().getToastTxt().setValue(msg);
                }

                @Override
                public void error(ExceptionHandle.ResponseException e) {
                    getUiChangeEvent().getToastTxt().setValue(e.getMessage());
                }
            });
        }
    }

    /**
     * @param userId
     */
    public void getHealthDataList(String userId) {
        usersRemoteSource.getHealthList(userId, new UsersInterface.GetHealthListCallback() {
            @Override
            public void getSucceed(HealthDataResponse healthDataResponse) {
                if (healthDataResponse.getData() == null || healthDataResponse.getData().getBloodSugarSendList() == null) {
                    todayHealthVisible.setValue(true);
                } else {
                    List<String> data = new ArrayList<>();
                    data.add("脉搏：" + healthDataResponse.getData().getBloodSugarSendList().get(0).getHeartRate());
                    data.add("收缩压：" + healthDataResponse.getData().getBloodSugarSendList().get(0).getSmallBloodHighPressure() +
                            "-" + healthDataResponse.getData().getBloodSugarSendList().get(0).getBigBloodHighPressure());
                    data.add("舒张压：" + healthDataResponse.getData().getBloodSugarSendList().get(0).getSmallBloodLowPressure() +
                            "-" + healthDataResponse.getData().getBloodSugarSendList().get(0).getBigBloodLowPressure());
                    data.add("血糖：" + healthDataResponse.getData().getBloodSugarSendList().get(0).getSmallBloodSugar() + "-" +
                            healthDataResponse.getData().getBloodSugarSendList().get(0).getBigBloodSugar());
                    data.add("脉搏：" + healthDataResponse.getData().getBloodSugarSendList().get(0).getHeartRate());
                    data.add("脉搏：" + healthDataResponse.getData().getBloodSugarSendList().get(0).getHeartRate());
                    data.add("脉搏：" + healthDataResponse.getData().getBloodSugarSendList().get(0).getHeartRate());
                    data.add("脉搏：" + healthDataResponse.getData().getBloodSugarSendList().get(0).getHeartRate());
                    dataItemTodayHealth.setValue(new DataItem("血糖表", data));
                    todayHealthVisible.setValue(false);
                }
            }

            @Override
            public void getFailed(String msg) {
                todayHealthVisible.setValue(true);
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                Log.d(HTAG, "error==========>: " + e.getMessage());
            }
        });
    }

    public void getAirList(String memberId) {
        usersRemoteSource.getAirList(memberId, new UsersInterface.GetAirListCallback() {
            @Override
            public void getSucceed(AirResponse airResponse) {
                if (airResponse.getData() == null) {
                    todayEnvironmentVisible.setValue(false);
                } else {
                    List<String> data = new ArrayList<>();
                    data.add("温度：" + ToolUtil.isNull(airResponse.getData().get(0).getTemperature()));
                    data.add("湿度：" + ToolUtil.isNull(airResponse.getData().get(0).getHumidity()));
                    data.add("O2：" + ToolUtil.isNull(airResponse.getData().get(0).getO2()));
                    data.add("CO2：" + ToolUtil.isNull(airResponse.getData().get(0).getCo2()));
                    data.add("PM2.5：" + ToolUtil.isNull(airResponse.getData().get(0).getPm25()));
                    data.add("PM0.3：" + ToolUtil.isNull(airResponse.getData().get(0).getPm03()));
                    data.add("甲醛：" + ToolUtil.isNull(airResponse.getData().get(0).getHcho()));
                    data.add("VOC：" + ToolUtil.isNull(airResponse.getData().get(0).getTvoc()));
                    dataItemAir.setValue(new DataItem(airResponse.getData().get(0).getSceneName(),
                            data));
                    todayEnvironmentVisible.setValue(true);
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

    /**
     * 创建异常任务
     *
     * @param userId
     * @param title
     * @param content
     */
    public void createMyTask(long userId, String title, String content) {
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
            usersRemoteSource.createMyTask(userId,
                    title,
                    content,
                    BaseApplication.getUserInfoBean().getSysId(),
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
                        }
                    });
        } else {
            getUiChangeEvent().getToastTxt().setValue("标题、描述不能为空");
        }

    }

    public void getNursingData(long memberId) {
        usersRemoteSource.getNursingData(BaseApplication.getToken(), memberId, new UsersInterface.GetNursingDataCallback() {
            @Override
            public void getSucceed(NursingResponse nursingResponse) {
                Log.d(HTAG, "getSucceed==========>: ");
                if (nursingResponse.getData() == null) {
                    bodyHealthVisible.setValue(false);
                } else {
                    List<String> data = new ArrayList<>();
                    data.add("Id：" + ToolUtil.isNull(nursingResponse.getData().get(0).getDeviceId()));
                    data.add("设定风温：" + stateToString(nursingResponse.getData().get(0).getSetWindT()));
                    data.add("设定水压：" + stateToString(nursingResponse.getData().get(0).getSetWaterP()));
                    data.add("设定水温：" + stateToString(nursingResponse.getData().get(0).getSetWaterT()));
                    data.add("当前风温：" + ToolUtil.isNull(nursingResponse.getData().get(0).getCurrentWindT()));
                    data.add("清洁空气状态：" + ToolUtil.isNull(nursingResponse.getData().get(0).getCleanAirStatus() == 0 ? "等待中" : "进行中"));
                    data.add("当前工作状态：" + ToolUtil.isNull(nursingResponse.getData().get(0).getCurrentWorkStatus()));
                    data.add("当前工作状态：" + ToolUtil.isNull(nursingResponse.getData().get(0).getCurrentWorkStatus()));
                    dataItemBodyHealth.setValue(new DataItem("护理仪", data));
                    bodyHealthVisible.setValue(true);
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

    private String stateToString(int state) {
        String string = "";
        switch (state) {
            case 0:
                string = "低挡";
                break;
            case 1:
                string = "中档";
                break;
            case 2:
                string = "高挡";
                break;
        }
        return string;
    }
}
