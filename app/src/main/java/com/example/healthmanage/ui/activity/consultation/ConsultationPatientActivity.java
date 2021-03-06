package com.example.healthmanage.ui.activity.consultation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.aries.ui.widget.progress.UIProgressDialog;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityConsultationPatitentBinding;
import com.example.healthmanage.ui.activity.consultation.response.PatientInfoBean;
import com.example.healthmanage.ui.fragment.qualification.SecondStepFragment;
import com.example.healthmanage.ui.fragment.qualification.adapter.GridImageAdapter;
import com.example.healthmanage.utils.GlideEngine;
import com.example.healthmanage.utils.PhotoUtils;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.TitleToolBar;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.ScreenUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * ????????????
 */
public class ConsultationPatientActivity extends BaseActivity<ActivityConsultationPatitentBinding,ConsultationViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private Context context;
    private String memberIds ;
    PopupWindow popupWindow;
    private String startDate;
    private String endTime;
    private List<File> files = new ArrayList<>();
    private List<String> filePaths;
    private GridImageAdapter gridImageAdapter;
    private String examineUser;
    private PatientInfoBean patientInfoBean;
    private UIProgressDialog uiProgressDialog;
    @Override
    protected void initData() {
        patientInfoBean = new PatientInfoBean();
        context = ConsultationPatientActivity.this;
        titleToolBar.setTitle("????????????");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,5,GridLayoutManager.VERTICAL,false);
        dataBinding.gridRecyclerview.setLayoutManager(gridLayoutManager);
        dataBinding.gridRecyclerview.addItemDecoration(new GridSpacingItemDecoration(5, ScreenUtils.dip2px(context,8),false));
        gridImageAdapter = new GridImageAdapter(context,onAddPicClickListener);
        gridImageAdapter.setSelectMax(9);
        dataBinding.gridRecyclerview.setAdapter(gridImageAdapter);
        filePaths = new ArrayList<>();
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // ???????????? ??????????????????????????????api????????????
            PictureSelector.create(ConsultationPatientActivity.this)
                    .openGallery(PictureMimeType.ofImage())// ??????.PictureMimeType.ofAll()?????????.ofImage()?????????.ofVideo()?????????.ofAudio()
                    .imageEngine(GlideEngine.createGlideEngine())// ??????????????????????????????????????????
                    .isWeChatStyle(true)// ????????????????????????????????????
                    .maxSelectNum(9)// ???????????????????????? ?????????size?????????size??????5-size  size == 0 ? 5 : 5-size
                    .imageSpanCount(5)// ??????????????????
                    .isReturnEmpty(false)// ????????????????????????????????????????????????
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// ????????????Activity????????????????????????????????????
                    .isCamera(true)// ????????????????????????
                    .isZoomAnim(true)// ?????????????????? ???????????? ??????true
                    //.imageFormat(PictureMimeType.PNG)// ??????????????????????????????,??????jpeg
                    .isEnableCrop(false)// ????????????
                    .isCompress(true)// ????????????
                    .compressQuality(80)// ??????????????????????????? 0~ 100
                    .synOrAsy(true)//??????false?????????true ?????? ????????????
                    .withAspectRatio(1, 1)// ???????????? ???16:9 3:2 3:4 1:1 ????????????
                    .selectionData(gridImageAdapter.getData())// ????????????????????????
                    //.isDragFrame(false)// ????????????????????????(??????)
                    //.videoMaxSecond(15)
                    //.videoMinSecond(10)
                    .isPreviewEggs(false)// ??????????????? ????????????????????????????????????(???????????????????????????????????????????????????)
                    //.cropCompressQuality(90)// ??????????????? ??????cutOutQuality()
                    .cutOutQuality(90)// ?????????????????? ??????100
                    .minimumCompressSize(100)// ??????100kb??????????????????
                    //.cropWH()// ???????????????????????????????????????????????????????????????
                    //.rotateEnabled(true) // ???????????????????????????
                    //.scaleEnabled(true)// ?????????????????????????????????
                    //.videoQuality()// ?????????????????? 0 or 1
                    //.recordVideoSecond()//?????????????????? ??????60s
                    //.setOutputCameraPath("/CustomPath")// ???????????????????????????  ???????????????
                    //.forResult(PictureConfig.CHOOSE_REQUEST);//????????????onActivityResult code
                    .forResult(new MyResultCallback(gridImageAdapter));

        }

    };

    /**
     * ??????????????????
     */
    public  class MyResultCallback implements OnResultCallbackListener<LocalMedia> {
        private WeakReference<GridImageAdapter> mAdapterWeakReference;
        private String path;

        public MyResultCallback(GridImageAdapter adapter) {
            super();
            this.mAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void onResult(List<LocalMedia> result) {
            if (files!=null && files.size()>0){
                files.clear();
            }
            for (LocalMedia media : result) {
                if (media.isCompressed()){
                    files.add(new File(media.getCompressPath()));
                }else {
                    path = media.getPath();
                    if (path.contains("content://")){
                        Uri uri = Uri.parse(path);
                        path = PhotoUtils.getFilePathByUri_BELOWAPI11(uri,ConsultationPatientActivity.this);
                    }
                    files.add(new File(path));
                }
            }
            if (mAdapterWeakReference.get() != null) {
                mAdapterWeakReference.get().setList(result);
                mAdapterWeakReference.get().notifyDataSetChanged();
            }
            viewModel.uploadPhotoForPatient(files);
        }

        @Override
        public void onCancel() {
            Log.i(TAG, "PictureSelector Cancel");
        }
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.tvChooseMeetPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,GetHospitalDepartmentListActivity.class);
                startActivityForResult(intent,2);
            }
        });
        dataBinding.tvChooseMeetStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(0);
            }
        });
        dataBinding.tvChooseMeetEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(1);
            }
        });
        viewModel.patientPhotos.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> files) {
                if (files==null){
                    return;
                }
                if (filePaths!=null && filePaths.size()>0){
                    filePaths.clear();
                }
                if (files!=null  && files.size()>0){
                    filePaths.addAll(files);
                }
                patientInfoBean.setAppPatientPictures(filePaths);
            }
        });
        dataBinding.btnTransferConsultationTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(dataBinding.editPatientDescribe.getText())){
                    ToastUtil.showShort("???????????????????????????");
                }else if (TextUtils.isEmpty(dataBinding.editPrimaryDiagnosis.getText())){
                    ToastUtil.showShort("???????????????????????????");
                }else if (TextUtils.isEmpty(dataBinding.editConsultationPurpose.getText())){
                    ToastUtil.showShort("??????????????????????????????");
                }else if (TextUtils.isEmpty(memberIds)){
                    ToastUtil.showShort("?????????????????????");
                }else {
                    patientInfoBean.setBriefHistory(dataBinding.editPatientDescribe.getText().toString());
                    patientInfoBean.setPatientInitialDecide(dataBinding.editPrimaryDiagnosis.getText().toString());
                    patientInfoBean.setExamineGoalRequest(dataBinding.editConsultationPurpose.getText().toString());
                    patientInfoBean.setExamineId(memberIds);
                    patientInfoBean.setToken(BaseApplication.getToken());
                    patientInfoBean.setCreateUserId(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                    viewModel.insertPatientExamine(patientInfoBean);
                    uiProgressDialog = new UIProgressDialog.WeChatBuilder(context)
                            .setCanceledOnTouchOutside(false)
                            .setCancelable(false)
                            .setMessage("????????????...")
                            .setIndeterminateDrawable(R.drawable.dialog_loading_wei_xin)
                            .setBackgroundRadiusResource(R.dimen.dp_radius_loading)
                            .create()
                            .setDimAmount(0.6f);
                    uiProgressDialog.show();
                }
            }
        });
        viewModel.isAddSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    uiProgressDialog.dismiss();
                    ToastUtil.showShort("????????????????????????");
                    finish();
                }else {
                    uiProgressDialog.dismiss();
                    ToastUtil.showShort("????????????????????????");
                }
            }
        });
    }

    public void initPopupWindow(int type){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_date_time_picker,null,false);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setAnimationStyle(R.style.Animation);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //??????popupWindow???????????????????????????????????????View???x??????????????????y???????????????
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0,
                ToolUtil.getNavigationBarHeight(this));
        popupWindow.setFocusable(true);
        bgAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                bgAlpha(1);
            }
        });
        DatePicker datePicker = view.findViewById(R.id.dialog_date_picker);
        TimePicker timePicker = view.findViewById(R.id.dialog_time_picker);
        Calendar calendar = Calendar.getInstance();
        datePicker.setMinDate(new Date().getTime());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(null);
        TextView dateType = view.findViewById(R.id.tv_time_type);
        dateType.setText(type == 0?"?????????????????????":"?????????????????????");
        TextView dateCancel = view.findViewById(R.id.tv_date_cancel);
        dateCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });

        TextView dateConfirm = view.findViewById(R.id.tv_date_confirm);
        dateConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chooseHour = timePicker.getHour();
                int chooseMin = timePicker.getMinute();
                int curHour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int chooseMonth = datePicker.getMonth()+1;
                int curMonth = calendar.get(Calendar.MONTH)+1;
                int chooseYear = datePicker.getYear();
                int curYear = calendar.get(Calendar.YEAR);
                int chooseDay = datePicker.getDayOfMonth();
                int curDay = calendar.get(Calendar.DAY_OF_MONTH);
                if (chooseYear > curYear){
                    popupWindow.dismiss();
                    if (type==0){
                        startDate = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                    }else {
                        endTime = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                    }
                    if (timePicker.getMinute()<10){
                        if (type == 0){
                            dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                        }else {
                            dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                        }
                    }else {
                        if (type == 0){
                            dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                        }else {
                            dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                        }

                    }
                    patientInfoBean.setStartTime(startDate);
                    patientInfoBean.setEndTime(endTime);
                }else {
                    if (chooseMonth>curMonth){
                        popupWindow.dismiss();
                        if (type==0){
                            startDate = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                        }else {
                            endTime = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                        }
                        if (timePicker.getMinute()<10){
                            if (type == 0){
                                dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                            }else {
                                dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                            }
                        }else {
                            if (type == 0){
                                dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                            }else {
                                dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                            }

                        }
                        patientInfoBean.setStartTime(startDate);
                        patientInfoBean.setEndTime(endTime);
                    }else {
                        if (chooseDay>chooseDay){
                            popupWindow.dismiss();
                            if (type==0){
                                startDate = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                            }else {
                                endTime = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                            }
                            if (timePicker.getMinute()<10){
                                if (type == 0){
                                    dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                }else {
                                    dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                }
                            }else {
                                if (type == 0){
                                    dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                }else {
                                    dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                }

                            }
                            patientInfoBean.setStartTime(startDate);
                            patientInfoBean.setEndTime(endTime);
                        }else {
                            if (chooseMonth>curMonth){
                                popupWindow.dismiss();
                                if (type==0){
                                    startDate = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                                }else {
                                    endTime = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                                }
                                if (timePicker.getMinute()<10){
                                    if (type == 0){
                                        dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                    }else {
                                        dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                    }
                                }else {
                                    if (type == 0){
                                        dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                    }else {
                                        dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                    }

                                }
                                patientInfoBean.setStartTime(startDate);
                                patientInfoBean.setEndTime(endTime);
                                if (chooseDay>curDay){
                                    popupWindow.dismiss();
                                    if (type==0){
                                        startDate = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                                    }else {
                                        endTime = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                                    }
                                    if (timePicker.getMinute()<10){
                                        if (type == 0){
                                            dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                        }else {
                                            dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                        }
                                    }else {
                                        if (type == 0){
                                            dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                        }else {
                                            dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                        }

                                    }
                                    patientInfoBean.setStartTime(startDate);
                                    patientInfoBean.setEndTime(endTime);
                                }else {
                                    if (chooseHour < curHour){
                                        ToastUtil.showShort("????????????????????????????????????");
                                    }else if (chooseHour == curHour){
                                        if (chooseMin<minute){
                                            ToastUtil.showShort("????????????????????????????????????");
                                        }else {
                                            popupWindow.dismiss();
                                            if (type==0){
                                                startDate = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                                            }else {
                                                endTime = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                                            }
                                            if (timePicker.getMinute()<10){
                                                if (type == 0){
                                                    dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                                }else {
                                                    dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                                }
                                            }else {
                                                if (type == 0){
                                                    dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                                }else {
                                                    dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                                }

                                            }
                                            patientInfoBean.setStartTime(startDate);
                                            patientInfoBean.setEndTime(endTime);
                                        }
                                    }else {
                                        popupWindow.dismiss();
                                        if (type==0){
                                            startDate = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                                        }else {
                                            endTime = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                                        }
                                        if (timePicker.getMinute()<10){
                                            if (type == 0){
                                                dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                            }else {
                                                dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                            }
                                        }else {
                                            if (type == 0){
                                                dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                            }else {
                                                dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                            }

                                        }
                                        patientInfoBean.setStartTime(startDate);
                                        patientInfoBean.setEndTime(endTime);
                                    }
                                }
                            }else {
                                if (chooseHour < curHour){
                                    ToastUtil.showShort("????????????????????????????????????");
                                }else if (chooseHour == curHour){
                                    if (chooseMin<minute){
                                        ToastUtil.showShort("????????????????????????????????????");
                                    }else {
                                        popupWindow.dismiss();
                                        if (type==0){
                                            startDate = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                                        }else {
                                            endTime = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                                        }
                                        if (timePicker.getMinute()<10){
                                            if (type == 0){
                                                dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                            }else {
                                                dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                            }
                                        }else {
                                            if (type == 0){
                                                dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                            }else {
                                                dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                            }

                                        }
                                        patientInfoBean.setStartTime(startDate);
                                        patientInfoBean.setEndTime(endTime);
                                    }
                                }else {
                                    popupWindow.dismiss();
                                    if (type==0){
                                        startDate = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                                    }else {
                                        endTime = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth()+" "+timePicker.getHour()+":"+timePicker.getMinute()+":00";
                                    }
                                    if (timePicker.getMinute()<10){
                                        if (type == 0){
                                            dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                        }else {
                                            dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+"0"+timePicker.getMinute());
                                        }
                                    }else {
                                        if (type == 0){
                                            dataBinding.tvChooseMeetStartTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                        }else {
                                            dataBinding.tvChooseMeetEndTime.setText((datePicker.getMonth()+1)+"???"+datePicker.getDayOfMonth()+"???"+timePicker.getHour()+":"+timePicker.getMinute());
                                        }

                                    }
                                    patientInfoBean.setStartTime(startDate);
                                    patientInfoBean.setEndTime(endTime);
                                }
                            }

                        }
                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            if (requestCode == 2){
                if (resultCode == RESULT_OK){
                    examineUser = data.getStringExtra("hospitalDoctor");
                    dataBinding.tvChooseMeetPerson.setText(examineUser);
                    memberIds = data.getStringExtra("memberIds");
                }
            }
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_consultation_patitent;
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    private void bgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
