package com.example.healthmanage.ui.activity.memberdetail;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.bean.network.response.AirResponse;
import com.example.healthmanage.bean.network.response.BaseResponse;
import com.example.healthmanage.bean.network.response.NursingResponse;
import com.example.healthmanage.databinding.ActivityVipDetailBinding;
import com.example.healthmanage.dialog.EditTextDialog;
import com.example.healthmanage.ui.activity.consultation.ConsultationPatientActivity;
import com.example.healthmanage.ui.activity.healthrecord.HealthRecordActivity;
import com.example.healthmanage.ui.activity.healthreport.ui.HealthReportActivity;
import com.example.healthmanage.ui.activity.historydata.HistoryDataActivity;
import com.example.healthmanage.ui.activity.memberdetail.adapter.AirAdapter;
import com.example.healthmanage.ui.activity.memberdetail.adapter.SpiritAdapter;
import com.example.healthmanage.ui.activity.memberdetail.bean.CreateTaskBean;
import com.example.healthmanage.ui.activity.memberdetail.response.HealthDataResponse;
import com.example.healthmanage.ui.activity.memberdetail.response.HealthResponse;
import com.example.healthmanage.ui.activity.memberdetail.response.SpiritHealthResponse;
import com.example.healthmanage.ui.activity.mytask.DealTaskActivity;
import com.example.healthmanage.ui.fragment.qualification.adapter.GridImageAdapter;
import com.example.healthmanage.utils.GlideEngine;
import com.example.healthmanage.utils.PhotoUtils;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.DropdownNewBar;
import com.example.healthmanage.widget.TitleToolBar;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.ScreenUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MemberNewDetailActivity extends BaseActivity<ActivityVipDetailBinding,MemberDetailNewViewModel>
        implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener{
    private Context mContext;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private RecyclerView recyclerView_today_environment;
    private Bundle bundle;
    private int memberId;
    private String memberName;
    private EditTextDialog editTextDialog;
    private List<AirResponse.DataBean> tEnvironmentBeanList;
    private List<SpiritHealthResponse.DataBean> spiritListData;
    private HealthDataResponse.DataBean todayHealthData;
    private NursingResponse.DataBean bodyHealthData;
    private AirAdapter airAdapter;
    private SpiritAdapter spiritAdapter;
    private LinearLayoutManager manager = new LinearLayoutManager(this);
    private LinearLayoutManager manager1 = new LinearLayoutManager(this);
    private UIAlertDialog uiAlertDialog;
    private String queryTime;
    private String airQueryTime;
    private String todayQueryTime;
    private String bodyQueryTime;
    private String spiritQueryTime;
    private GridImageAdapter gridImageAdapter;
    private List<File> files = new ArrayList<>();
    private List<String> filePaths;
    private List<CreateTaskBean.ListBean> listBeans;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {
        mContext = MemberNewDetailActivity.this;
        bundle = this.getIntent().getExtras();
        memberName = bundle.getString("MemberName");
        memberId = bundle.getInt("MemberId");
        titleToolBar.setTitle(memberName + "????????????");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setRightIconVisible(true);
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        dataBinding.includeTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        titleToolBar.setRightIconSrc(R.drawable.ic_md_more_vert);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.includeTitle.ivRight.setVisibility(View.INVISIBLE);
        dataBinding.includeTitle.ivRight.setImageResource(R.drawable.ic_md_more_vert);
        viewModel.setTitleToolBar(titleToolBar);

        tEnvironmentBeanList = new ArrayList<>();
        airAdapter = new AirAdapter(tEnvironmentBeanList);
        dataBinding.recycleviewTodayenvironment.setLayoutManager(manager);
        dataBinding.recycleviewTodayenvironment.setAdapter(airAdapter);

        spiritListData = new ArrayList<>();
        spiritAdapter = new SpiritAdapter(spiritListData);
        dataBinding.rlSpiritHealth.setLayoutManager(manager1);
        dataBinding.rlSpiritHealth.setAdapter(spiritAdapter);

        todayHealthData = new HealthDataResponse.DataBean();
        bodyHealthData = new NursingResponse.DataBean();

        /**????????????????????????*/
        viewModel.getAirList(String.valueOf(memberId));
        /**????????????????????????*/
        viewModel.getHealthDataList(String.valueOf(memberId));
        /**????????????????????????*/
        viewModel.getNursingData(memberId);
        /**??????????????????*/
        viewModel.getSpiritList(String.valueOf(memberId));

        DropdownNewBar dropdownBarTodayEnvironment = new DropdownNewBar("????????????", false, false);
        DropdownNewBar dropdownBarTodayHealth = new DropdownNewBar("????????????", false, false);
        DropdownNewBar dropdownBarBodyHealth = new DropdownNewBar("????????????",  false, false);
        DropdownNewBar dropdownBarSpiritHealth = new DropdownNewBar("????????????", false, false);
        List<DropdownNewBar> dropdownNewBarList = new ArrayList<>();
        dropdownNewBarList = Arrays.asList(dropdownBarTodayEnvironment,dropdownBarTodayHealth,dropdownBarBodyHealth,dropdownBarSpiritHealth);
        viewModel.dropdownBarLists.setValue(dropdownNewBarList);
        filePaths = new ArrayList<>();
        listBeans = new ArrayList<>();
    }

    @Override
    public void initViewParameters() {
        super.initViewParameters();
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        if (tEnvironmentBeanList!=null && tEnvironmentBeanList.size()>0){
            dataBinding.includeTodayEnvironmentNull.setVisibility(View.GONE);
        }
        viewModel.dataItemAir.observe(this, new Observer<List<AirResponse.DataBean>>() {
            @Override
            public void onChanged(List<AirResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    queryTime = dataBeans.get(0).getCreateTime();
                    if (!tEnvironmentBeanList.isEmpty() && tEnvironmentBeanList.size()>0){
                        tEnvironmentBeanList.clear();
                    }
                    tEnvironmentBeanList.addAll(dataBeans);
                    airAdapter.notifyDataSetChanged();
                }
            }
        });
        viewModel.todayAirVisible.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    dataBinding.includeTodayEnvironment.tvCollapse.setVisibility(View.VISIBLE);
                }else {
                    dataBinding.includeTodayEnvironment.tvCollapse.setVisibility(View.GONE);
                }
            }
        });
        viewModel.dataItemSpiritHealth.observe(this, new Observer<List<SpiritHealthResponse.DataBean>>() {
            @Override
            public void onChanged(List<SpiritHealthResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    queryTime = dataBeans.get(0).getSleepTime();
                    if (spiritListData!=null && spiritListData.size()>0){
                        spiritListData.clear();
                    }
                    spiritListData.addAll(dataBeans);
                    spiritAdapter.notifyDataSetChanged();
                }
            }
        });
        viewModel.spiritVisiable.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    dataBinding.includeSpiritHealth.tvCollapse.setVisibility(View.VISIBLE);
                }else {
                    dataBinding.includeSpiritHealth.tvCollapse.setVisibility(View.GONE);
                }
            }
        });
        viewModel.dataItemTodayHealth.observe(this, new Observer<HealthDataResponse.DataBean>() {
            @Override
            public void onChanged(HealthDataResponse.DataBean dataBean) {
                if (dataBean != null){
                    todayHealthData = dataBean;
                    queryTime = dataBean.getCreateTime();
                    dataBinding.layoutTodayHealth.tvHealth1.setText(Html.fromHtml("??????:<font color=\"#000000\">"+todayHealthData.getSteps()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth2.setText(Html.fromHtml("??????:<font color=\"#000000\">"+todayHealthData.getCalorie()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth3.setText(Html.fromHtml("??????:<font color=\"#000000\">"+todayHealthData.getHighPressure()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth4.setText(Html.fromHtml("??????:<font color=\"#000000\">"+todayHealthData.getLowPressure()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth5.setText(Html.fromHtml("??????:<font color=\"#000000\">"+todayHealthData.getSaturation()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth6.setText(Html.fromHtml("??????:<font color=\"#000000\">"+todayHealthData.getPPG()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth7.setText(Html.fromHtml("??????:<font color=\"#000000\">"+todayHealthData.getTemperature()+"</font>"));
                    dataBinding.layoutTodayHealth.tvHealth8.setText(Html.fromHtml("??????:<font color=\"#000000\">"+todayHealthData.getHeartRate()+"</font>"));
                    dataBinding.layoutTodayHealth.divisionLine4.setVisibility(View.GONE);
                    dataBinding.layoutTodayHealth.layout5.setVisibility(View.GONE);
                }
            }
        });
        viewModel.todayHealthVisible.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    dataBinding.includeTodayHealth.tvCollapse.setVisibility(View.VISIBLE);
                    dataBinding.layoutTodayHealth.getRoot().setVisibility(View.VISIBLE);
                }else {
                    dataBinding.includeTodayHealth.tvCollapse.setVisibility(View.GONE);
                    dataBinding.layoutTodayHealth.getRoot().setVisibility(View.GONE);
                }
            }
        });

        viewModel.dataItemBodyHealth.observe(this, new Observer<NursingResponse.DataBean>() {
            @Override
            public void onChanged(NursingResponse.DataBean dataBean) {
                if (dataBean!=null){
                    queryTime = dataBean.getCreateTime();
                    bodyHealthData = dataBean;
                    dataBinding.layoutBodyHealth.tvDeviceName.setText("?????????");
                    dataBinding.layoutBodyHealth.tvHealth1.setText(Html.fromHtml("ID:<font color=\"#000000\">"+ ToolUtil.isNull(bodyHealthData.getDeviceId())+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth2.setText(Html.fromHtml("????????????:<font color=\"#000000\">"+stateToString(bodyHealthData.getSetWindT())+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth3.setText(Html.fromHtml("????????????:<font color=\"#000000\">"+stateToString(bodyHealthData.getSetWaterP())+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth4.setText(Html.fromHtml("????????????:<font color=\"#000000\">"+stateToString(bodyHealthData.getSetWaterT())+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth5.setText(Html.fromHtml("????????????:<font color=\"#000000\">"+ToolUtil.isNull(bodyHealthData.getCurrentWindT())+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth6.setText(Html.fromHtml("??????????????????:<font color=\"#000000\">"+ToolUtil.isNull(bodyHealthData.getCleanAirStatus() == 0 ? "?????????" : "?????????")+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth7.setText(Html.fromHtml("??????????????????:<font color=\"#000000\">"+ToolUtil.isNull(bodyHealthData.getCurrentWorkStatus())+"</font>"));
                    dataBinding.layoutBodyHealth.tvHealth8.setText(Html.fromHtml("??????????????????:<font color=\"#000000\">"+ToolUtil.isNull(bodyHealthData.getCurrentWorkStatus())+"</font>"));
                    dataBinding.layoutBodyHealth.divisionLine4.setVisibility(View.GONE);
                    dataBinding.layoutBodyHealth.layout5.setVisibility(View.GONE);
                }
            }
        });

        viewModel.bodyHealthVisible.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    dataBinding.layoutBodyHealth.getRoot().setVisibility(View.VISIBLE);
                    dataBinding.includeBodyHealth.tvCollapse.setVisibility(View.VISIBLE);
                }else {
                    dataBinding.includeBodyHealth.tvCollapse.setVisibility(View.GONE);
                    dataBinding.layoutBodyHealth.getRoot().setVisibility(View.GONE);
                }
            }
        });

        dataBinding.includeTodayEnvironment.tvCollapse.setOnClickListener(v -> {
            dataBinding.includeTodayEnvironment.tvExpand.setVisibility(View.VISIBLE);
            dataBinding.includeTodayEnvironment.tvCollapse.setVisibility(View.GONE);
            dataBinding.recycleviewTodayenvironment.setVisibility(View.GONE);
        });

        dataBinding.includeTodayEnvironment.tvExpand.setOnClickListener(v -> {
            dataBinding.includeTodayEnvironment.tvExpand.setVisibility(View.GONE);
            dataBinding.includeTodayEnvironment.tvCollapse.setVisibility(View.VISIBLE);
            dataBinding.recycleviewTodayenvironment.setVisibility(View.VISIBLE);
        });

        dataBinding.includeTodayHealth.tvCollapse.setOnClickListener(v -> {
            dataBinding.includeTodayHealth.tvExpand.setVisibility(View.VISIBLE);
            dataBinding.includeTodayHealth.tvCollapse.setVisibility(View.GONE);
            dataBinding.layoutTodayHealth.getRoot().setVisibility(View.GONE);
        });

        dataBinding.includeTodayHealth.tvExpand.setOnClickListener(v -> {
            dataBinding.includeTodayHealth.tvExpand.setVisibility(View.GONE);
            dataBinding.includeTodayHealth.tvCollapse.setVisibility(View.VISIBLE);
            dataBinding.layoutTodayHealth.getRoot().setVisibility(View.VISIBLE);
        });

        dataBinding.includeBodyHealth.tvCollapse.setOnClickListener(v -> {
            dataBinding.includeBodyHealth.tvExpand.setVisibility(View.VISIBLE);
            dataBinding.includeBodyHealth.tvCollapse.setVisibility(View.GONE);
            dataBinding.layoutBodyHealth.getRoot().setVisibility(View.GONE);
        });

        dataBinding.includeBodyHealth.tvExpand.setOnClickListener(v -> {
            dataBinding.includeBodyHealth.tvExpand.setVisibility(View.GONE);
            dataBinding.includeBodyHealth.tvCollapse.setVisibility(View.VISIBLE);
            dataBinding.layoutBodyHealth.getRoot().setVisibility(View.VISIBLE);
        });

        dataBinding.includeSpiritHealth.tvCollapse.setOnClickListener(v -> {
            dataBinding.includeSpiritHealth.tvExpand.setVisibility(View.VISIBLE);
            dataBinding.includeSpiritHealth.tvCollapse.setVisibility(View.GONE);
            dataBinding.rlSpiritHealth.setVisibility(View.GONE);
        });

        dataBinding.includeSpiritHealth.tvExpand.setOnClickListener(v -> {
            dataBinding.includeSpiritHealth.tvExpand.setVisibility(View.GONE);
            dataBinding.includeSpiritHealth.tvCollapse.setVisibility(View.VISIBLE);
            dataBinding.rlSpiritHealth.setVisibility(View.VISIBLE);
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
            }
        });

        dataBinding.tvCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(MemberNewDetailActivity.this,R.layout.dialog_task_create,null);
                uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(MemberNewDetailActivity.this)
                        .setView(view)
                        .setCanceledOnTouchOutside(false)//????????????????????????
                        .setMinHeight(SizeUtil.dp2px(160))
                        .setPositiveButtonTextColorResource(R.color.colorTxtBlue)
                        .create()
                        .setDimAmount(0.6f);
                uiAlertDialog.show();
                Window window = uiAlertDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.gravity = Gravity.CENTER;
                //dialog?????????????????????xml
                //lp.width = WindowManager.LayoutParams.MATCH_PARENT;//???????????????????????????
                //dialog??????????????????
                WindowManager manager= getWindowManager();
                Display display= manager.getDefaultDisplay();
                //params.height= (int) (display.getHeight()* 0.8);
                lp.width= (int) (display.getWidth()* 0.8);
                uiAlertDialog.getWindow().setAttributes(lp);

                Button okButton = view.findViewById(R.id.btn_query_task_create);
                Button cancelButton = view.findViewById(R.id.btn_cancel_task_create);
                EditText editContent = view.findViewById(R.id.edt_hint_task);
                RecyclerView recyclerView = view.findViewById(R.id.recycler_view_task_pic);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(MemberNewDetailActivity.this,5,GridLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(5, ScreenUtils.dip2px(MemberNewDetailActivity.this,8),false));
                gridImageAdapter = new GridImageAdapter(MemberNewDetailActivity.this,onAddPicClickListener);
                gridImageAdapter.setSelectMax(5);
                recyclerView.setAdapter(gridImageAdapter);

                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listBeans!=null && listBeans.size()>0){
                            listBeans.clear();
                        }
                        if (filePaths!=null && filePaths.size()>0){
                            for (String path:filePaths) {
                                listBeans.add(new CreateTaskBean.ListBean(path));
                            }
                        }else {
                            listBeans.add(new CreateTaskBean.ListBean(null));
                        }

                        viewModel.createHealthTask(memberId,editContent.getText().toString(),listBeans);
//                        viewModel.createMyTask(memberId, memberName, editContent.getText().toString(),ToolUtil.getCurTime(),queryTime);
                    }
                });
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uiAlertDialog.dismiss();
                    }
                });

            }
        });

        viewModel.isCreateSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    uiAlertDialog.dismiss();
                }else {
                    uiAlertDialog.dismiss();
                    return;
                }
            }
        });

    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // ???????????? ??????????????????????????????api????????????
            PictureSelector.create(MemberNewDetailActivity.this)
                    .openGallery(PictureMimeType.ofImage())// ??????.PictureMimeType.ofAll()?????????.ofImage()?????????.ofVideo()?????????.ofAudio()
                    .imageEngine(GlideEngine.createGlideEngine())// ??????????????????????????????????????????
                    .isWeChatStyle(true)// ????????????????????????????????????
                    .maxSelectNum(5)// ???????????????????????? ?????????size?????????size??????5-size  size == 0 ? 5 : 5-size
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
                        path = PhotoUtils.getFilePathByUri_BELOWAPI11(uri,MemberNewDetailActivity.this);
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

    private String stateToString(int state) {
        String string = "";
        switch (state) {
            case 0:
                string = "??????";
                break;
            case 1:
                string = "??????";
                break;
            case 2:
                string = "??????";
                break;
        }
        return string;
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_vip_detail;
    }

    @Override
    public void onRightIconClick() {
        showPopupMenu();
    }

    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this,dataBinding.includeTitle.ivRight);
        popupMenu.inflate(R.menu.vipdetail_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_health:
                        Intent intent = new Intent(MemberNewDetailActivity.this, HealthRecordActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("userId",memberId);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.action_history:
                        Intent historyIntent = new Intent(MemberNewDetailActivity.this, HistoryDataActivity.class);
                        Bundle historyBundle = new Bundle();
                        historyBundle.putInt("memberId",memberId);
                        historyBundle.putString("memberName",memberName);
                        historyIntent.putExtras(historyBundle);
                        startActivity(historyIntent);
//                        Toast.makeText(mContext,"????????????",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_vs:
                        Toast.makeText(mContext,"????????????",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_fenxi:
                        Toast.makeText(mContext,"????????????",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_report:
                        Intent intent1 = new Intent(MemberNewDetailActivity.this, HealthReportActivity.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putInt("userId",memberId);
                        intent1.putExtras(bundle1);
                        startActivity(intent1);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
