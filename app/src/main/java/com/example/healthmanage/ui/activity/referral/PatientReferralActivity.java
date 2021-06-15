package com.example.healthmanage.ui.activity.referral;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.aries.ui.widget.progress.UIProgressDialog;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityReferralPatientBinding;
import com.example.healthmanage.ui.activity.consultation.ConsultationPatientActivity;
import com.example.healthmanage.ui.activity.referral.response.ReferralBean;
import com.example.healthmanage.ui.activity.signmember.SignMemberActivity;
import com.example.healthmanage.ui.fragment.qualification.adapter.GridImageAdapter;
import com.example.healthmanage.utils.GlideEngine;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.utils.ToolUtil;
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
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * 添加患者转诊
 */
public class PatientReferralActivity extends BaseActivity<ActivityReferralPatientBinding,ReferralViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private String memberName;
    private String memberId;
    private String chooseHealthGroup;
    private ReferralBean referralBean;
    private Context context;
    private List<File> files = new ArrayList<>();
    private List<String> filePaths;
    private GridImageAdapter gridImageAdapter;
    private UIProgressDialog uiProgressDialog;

    @Override
    protected void initData() {
        referralBean = new ReferralBean();
        context = PatientReferralActivity.this;
        titleToolBar.setTitle("患者转诊");
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
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(PatientReferralActivity.this)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                    .isWeChatStyle(true)// 是否开启微信图片选择风格
                    .maxSelectNum(9)// 最大图片选择数量 定一个size，如果size有值5-size  size == 0 ? 5 : 5-size
                    .imageSpanCount(5)// 每行显示个数
                    .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                    .isEnableCrop(false)// 是否裁剪
                    .isCompress(true)// 是否压缩
                    .compressQuality(80)// 图片压缩后输出质量 0~ 100
                    .synOrAsy(true)//同步false或异步true 压缩 默认同步
                    .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .selectionData(gridImageAdapter.getData())// 是否传入已选图片
                    //.isDragFrame(false)// 是否可拖动裁剪框(固定)
                    //.videoMaxSecond(15)
                    //.videoMinSecond(10)
                    .isPreviewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    //.cropCompressQuality(90)// 注：已废弃 改用cutOutQuality()
                    .cutOutQuality(90)// 裁剪输出质量 默认100
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.rotateEnabled(true) // 裁剪是否可旋转图片
                    //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                    //.videoQuality()// 视频录制质量 0 or 1
                    //.recordVideoSecond()//录制视频秒数 默认60s
                    //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径  注：已废弃
                    //.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                    .forResult(new MyResultCallback(gridImageAdapter));

        }

    };

    /**
     * 返回结果回调
     */
    public  class MyResultCallback implements OnResultCallbackListener<LocalMedia> {
        private WeakReference<GridImageAdapter> mAdapterWeakReference;

        public MyResultCallback(GridImageAdapter adapter) {
            super();
            this.mAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void onResult(List<LocalMedia> result) {
            if (files.size()>0){
                files.clear();
            }
            for (LocalMedia media : result) {
                if (media.isCompressed()){
                    files.add(new File(media.getCompressPath()));
                }else {
                    files.add(new File(media.getPath()));
                }
            }
            if (mAdapterWeakReference.get() != null) {
                mAdapterWeakReference.get().setList(result);
                mAdapterWeakReference.get().notifyDataSetChanged();
            }
            viewModel.uploadPhotoForPatient(files);
            uiProgressDialog = new UIProgressDialog.WeChatBuilder(context)
                    .setMessage("正在上传中...")
                    .setIndeterminateDrawable(R.drawable.dialog_loading_wei_xin)
                    .setBackgroundRadiusResource(R.dimen.dp_radius_loading)
                    .create()
                    .setDimAmount(0.6f);
            uiProgressDialog.show();
        }

        @Override
        public void onCancel() {
            Log.i(TAG, "PictureSelector Cancel");
        }
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.tvPatientReferral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientReferralActivity.this,ChooseMemberActivity.class);
                startActivityForResult(intent,2);
            }
        });
        dataBinding.tvChooseReferralObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseHealthGroup = null;
                View view = View.inflate(PatientReferralActivity.this,R.layout.dialog_choose_referral,null);
                RadioGroup radioGroup = view.findViewById(R.id.rg_choose_group);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId){
                            case R.id.radiobutton_health_department:
                                chooseHealthGroup = "保健医院-健康医疗部";
                                break;
                            case R.id.radiobutton_health_management:
                                chooseHealthGroup = "保健医院-健康管理部";
                                break;
                        }
                    }
                });
                UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(PatientReferralActivity.this)
                        .setView(view)
                        .setMessageTextColorResource(R.color.colorTxtRed)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(PatientReferralActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButtonTextColorResource(R.color.colorBlack)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (chooseHealthGroup==null){
                                    ToastUtil.showShort("请选择转诊对象");
                                }else {
                                    dataBinding.tvChooseReferralObject.setText(chooseHealthGroup);
                                    ToastUtil.showShort(chooseHealthGroup);
                                }
                            }
                        })
                        .setMinHeight(SizeUtil.dp2px(160))
                        .setPositiveButtonTextColorResource(R.color.colorTxtBlue)
                        .create()
                        .setDimAmount(0.6f);
                uiAlertDialog.show();
            }
        });

        viewModel.patientPhotos.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> files) {
                uiProgressDialog.dismiss();
                if (files==null){
                    return;
                }
                if (filePaths !=null && filePaths.size()>0){
                    filePaths.clear();
                }
                if (files!=null && files.size()>0){
                    filePaths.addAll(files);
                }
                referralBean.setAppPatientPictures(filePaths);
            }
        });
        dataBinding.btnTransferReferral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(viewModel.patientHistory.getValue())){
                    ToastUtil.showShort("请输入患者简要病史");
                }else if (TextUtils.isEmpty(viewModel.primaryDiagnosis.getValue())){
                    ToastUtil.showShort("请输入患者初步诊断");
                }else if (TextUtils.isEmpty(viewModel.referralReason.getValue())){
                    ToastUtil.showShort("请输入会诊目的与要求");
                }else if (TextUtils.isEmpty(chooseHealthGroup)) {
                    ToastUtil.showShort("请选择转诊");
                }else if (TextUtils.isEmpty(memberName)) {
                    ToastUtil.showShort("请选择转诊患者");
                }else {
                    referralBean.setPatientIllnessDescribe(viewModel.patientHistory.getValue());
                    referralBean.setPatientInitialDecide(viewModel.primaryDiagnosis.getValue());
                    referralBean.setReferralReasons(viewModel.referralReason.getValue());
                    referralBean.setApplyOutHospital(viewModel.transferHospital.getValue());
                    referralBean.setApplyInHospital(viewModel.intoHospital.getValue());
                    referralBean.setToken(BaseApplication.getToken());
                    referralBean.setChoiseReferral(chooseHealthGroup);
                    referralBean.setReferralPatientId(memberId);
                    referralBean.setCreateTime(ToolUtil.getCurTime());
                    viewModel.insertPatientReferral(referralBean);
                }
            }
        });
        viewModel.isInsertSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    ToastUtil.showShort("创建转诊任务成功");
                    finish();
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
                    memberName = data.getStringExtra("memberName");
                    dataBinding.tvPatientReferral.setText(memberName+"\u3000|\u3000"+data.getStringExtra("rank"));
                    memberId = data.getStringExtra("memberId");
                }
            }
        }
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
        return R.layout.activity_referral_patient;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
