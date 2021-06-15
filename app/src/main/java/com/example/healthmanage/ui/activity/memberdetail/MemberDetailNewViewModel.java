package com.example.healthmanage.ui.activity.memberdetail;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.AirResponse;
import com.example.healthmanage.bean.network.response.GeneralResponse;
import com.example.healthmanage.bean.network.response.NursingResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.bean.network.response.WeatherResponse;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.memberdetail.bean.CreateTaskBean;
import com.example.healthmanage.ui.activity.memberdetail.response.CreateTaskResponse;
import com.example.healthmanage.ui.activity.memberdetail.response.HealthDataResponse;
import com.example.healthmanage.ui.activity.memberdetail.response.SpiritHealthResponse;
import com.example.healthmanage.ui.activity.qualification.response.CertificateResponse;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.DataItem;
import com.example.healthmanage.widget.DropdownBar;
import com.example.healthmanage.widget.DropdownNewBar;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MemberDetailNewViewModel extends BaseViewModel {

    public MutableLiveData<String> location =
            new MutableLiveData<>("");

    public MutableLiveData<String> weatherDegrees = new MutableLiveData<>("-3C");

    public MutableLiveData<String> weatherReminder =
            new MutableLiveData<>("温度过高，注意防晒！");

    public MutableLiveData<List<DropdownNewBar>>
            dropdownBarLists = new MutableLiveData<>();


    public MutableLiveData<List<AirResponse.DataBean>> dataItemAir = new MutableLiveData<>();
    public MutableLiveData<HealthDataResponse.DataBean> dataItemTodayHealth = new MutableLiveData<>();
    public MutableLiveData<NursingResponse.DataBean> dataItemBodyHealth = new MutableLiveData<>();
    public MutableLiveData<List<SpiritHealthResponse.DataBean>> dataItemSpiritHealth = new MutableLiveData<>();

    public MutableLiveData<Boolean> todayHealthVisible = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> bodyHealthVisible = new MutableLiveData<>(false);
    /**今日空气质量是否存在数据*/
    public MutableLiveData<Boolean> todayAirVisible = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> spiritVisiable = new MutableLiveData<>(false);

    private MutableLiveData<HealthDataResponse> tEnvironmentBeanMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<String> contentNumber = new MutableLiveData<>();

    private UsersRemoteSource usersRemoteSource;

    public MutableLiveData<Boolean> isCreateSuccess = new MutableLiveData<>();
    public MutableLiveData<List<String>> patientPhotos = new MutableLiveData<>();

    public MemberDetailNewViewModel() {
        usersRemoteSource = new UsersRemoteSource();
    }

    public void uploadPhotoForPatient(List<File> files) {
        usersRemoteSource.getUploadCertificate(files, new UsersInterface.UpCertificateCallback() {
            @Override
            public void sendSucceed(CertificateResponse certificateResponse) {
                if (certificateResponse.getData() != null && certificateResponse.getData().size()>0){
                    patientPhotos.postValue(certificateResponse.getData());
                }else {
                    patientPhotos.postValue(null);
                }
            }

            @Override
            public void sendFailed(String msg) {
                patientPhotos.postValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                patientPhotos.postValue(null);
            }
        });
    }

    /**获取今日健康数据
     * userId
     */
    public void getHealthDataList(String userId) {
        usersRemoteSource.getHealthList(userId, new UsersInterface.GetHealthListCallback() {
            @Override
            public void getSucceed(HealthDataResponse healthDataResponse) {
                if (healthDataResponse.getData() == null) {
                    todayHealthVisible.setValue(false);
                } else {
                    dataItemTodayHealth.setValue(healthDataResponse.getData());
                    todayHealthVisible.setValue(true);
                }
            }

            @Override
            public void getFailed(String msg) {
                todayHealthVisible.setValue(false);
                getUiChangeEvent().getToastTxt().setValue(msg);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                Log.d(HTAG, "error==========>: " + e.getMessage());
            }
        });
    }

    /**
     * 今日环境获取空气质量数据
     * userId
     */
    public void getAirList(String memberId) {
        usersRemoteSource.getAirList(memberId, new UsersInterface.GetAirListCallback() {
            @Override
            public void getSucceed(AirResponse airResponse) {
                if (airResponse.getData() != null && airResponse.getData().size()>0) {
                    dataItemAir.setValue(airResponse.getData());
                    todayAirVisible.setValue(true);
                } else {
                    todayAirVisible.setValue(false);
                }
            }

            @Override
            public void getFailed(String msg) {
                ToastUtil.showShort(msg);
                todayAirVisible.setValue(false);
            }


            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

    /**
     * 获取精神健康数据
     * UserId
     */
    public void getSpiritList(String userId){
        usersRemoteSource.getSpiritList(userId, new UsersInterface.GetSpiritListCallback() {
            @Override
            public void getSucceed(SpiritHealthResponse spiritHealthResponse) {
                if (spiritHealthResponse.getData() != null && spiritHealthResponse.getData().size()>0){
                    dataItemSpiritHealth.setValue(spiritHealthResponse.getData());
                    spiritVisiable.setValue(true);
                }else {
                    spiritVisiable.setValue(false);
                }
            }

            @Override
            public void getFailed(String msg) {
                spiritVisiable.setValue(false);
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
    public void createMyTask(long userId, String title, String content,String createTime,String queryTime) {
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
            usersRemoteSource.createMyTask(userId, title, content, BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId(),
                    BaseApplication.getToken(), createTime, queryTime, new UsersInterface.CreateMyTaskCallback() {
                        @Override
                        public void createSucceed(GeneralResponse generalResponse) {
//                            LiveEventBus.get("close").post(false);
                            isCreateSuccess.postValue(true);
                            showToast("生成成功", 0);
                        }

                        @Override
                        public void createFailed(String msg) {
                            showToast(msg, 1);
                            isCreateSuccess.postValue(false);
                        }

                        @Override
                        public void error(ExceptionHandle.ResponseException e) {
                            isCreateSuccess.postValue(false);
                        }
                    });
        } else {
            getUiChangeEvent().getToastTxt().setValue("标题、描述不能为空");
        }

    }

    public void createHealthTask(int userId,String content,List<CreateTaskBean.ListBean> files){
        if (TextUtils.isEmpty(content)){
            getUiChangeEvent().getToastTxt().setValue("内容不能为空");
        }else {
            usersRemoteSource.createHealthTask(BaseApplication.getUserInfoBean().getSysId(), userId, content, files,new UsersInterface.CreateHealthTaskCallback() {
                @Override
                public void createSucceed(CreateTaskResponse createTaskResponse) {
                    isCreateSuccess.setValue(true);
                    getUiChangeEvent().getToastTxt().setValue("生成成功");
                }

                @Override
                public void createFailed(String msg) {
                    isCreateSuccess.setValue(false);
                }

                @Override
                public void error(ExceptionHandle.ResponseException e) {
                    isCreateSuccess.setValue(false);
                }
            });
        }
    }

    /**
     * 获取护理仪数据
     * @param memberId
     */
    public void getNursingData(long memberId) {
        usersRemoteSource.getNursingData(BaseApplication.getToken(), memberId, new UsersInterface.GetNursingDataCallback() {
            @Override
            public void getSucceed(NursingResponse nursingResponse) {
                Log.d(HTAG, "getSucceed==========>: ");
                if (nursingResponse.getData() == null) {
                    bodyHealthVisible.setValue(false);
                } else {
                    dataItemBodyHealth.setValue(nursingResponse.getData());
                    bodyHealthVisible.setValue(true);
                }
            }

            @Override
            public void getFailed(String msg) {
                bodyHealthVisible.setValue(false);
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
