package com.example.healthmanage.ui.fragment.qualification;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentStepSecondBinding;
import com.example.healthmanage.ui.activity.qualification.QualificationActivity;
import com.example.healthmanage.ui.fragment.qualification.adapter.GridImageAdapter;
import com.example.healthmanage.ui.fragment.qualification.adapter.NewAdapter;
import com.example.healthmanage.ui.fragment.qualification.bean.DoctorInfo;
import com.example.healthmanage.ui.fragment.qualification.bean.UpdateDoctorInfo;
import com.example.healthmanage.utils.GlideEngine;
import com.example.healthmanage.utils.SPUtil;
import com.jeremyliao.liveeventbus.LiveEventBus;
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
import java.util.stream.Collectors;

import static android.content.ContentValues.TAG;

/**
 * Description:
 * Author:bwang
 * Date:2020/12/2 16:56
 */
public class SecondStepFragment extends BaseFragment<FragmentStepSecondBinding,SecondStepViewModel> {
    private String logoUrl,frontCardUrl,backcardUrl;
    private List<String> avatarPath = new ArrayList<>();
    private List<String> frontIdCardUrl = new ArrayList<>();
    private List<String> backIdCardUrl = new ArrayList<>();
    private List<File> files = new ArrayList<>();
    private List<String> filePaths;
    private GridImageAdapter gridImageAdapter;
    QualificationActivity qualificationActivity;
    private UpdateDoctorInfo updateDoctorInfo;
    private DoctorInfo doctorInfo;


    @Override
    protected void initData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),5,GridLayoutManager.VERTICAL,false);
        dataBinding.gridRecyclerview.setLayoutManager(gridLayoutManager);
        dataBinding.gridRecyclerview.addItemDecoration(new GridSpacingItemDecoration(5, ScreenUtils.dip2px(getActivity(),8),false));
        gridImageAdapter = new GridImageAdapter(getContext(),onAddPicClickListener);
        gridImageAdapter.setSelectMax(5);
        dataBinding.gridRecyclerview.setAdapter(gridImageAdapter);
        filePaths = new ArrayList<>();
        qualificationActivity = (QualificationActivity) getActivity();
        doctorInfo = qualificationActivity.getDoctorInfo();
        updateDoctorInfo = qualificationActivity.getUpdateDoctorInfo();
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(SecondStepFragment.this)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                    .isWeChatStyle(true)// 是否开启微信图片选择风格
                    .maxSelectNum(5)// 最大图片选择数量 定一个size，如果size有值5-size  size == 0 ? 5 : 5-size
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

    public class MyNewresultCallback implements OnResultCallbackListener<LocalMedia>{

        @Override
        public void onResult(List<LocalMedia> result) {
            List<String> path = result.stream().map(LocalMedia::getCompressPath).collect(Collectors.toList());
            NewAdapter newAdapter = new NewAdapter(path);
            dataBinding.gridRecyclerview.setAdapter(newAdapter);
        }

        @Override
        public void onCancel() {

        }
    }

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
            viewModel.uploadCerticate(files);
        }

        @Override
        public void onCancel() {
            Log.i(TAG, "PictureSelector Cancel");
        }
    }

    @Override
    protected void initListener() {
        dataBinding.ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(SecondStepFragment.this)
                        .openGallery(PictureMimeType.ofImage())
                        .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                        .maxSelectNum(1)
                        .isWeChatStyle(true)
                        .isCompress(true)//是否压缩
                        .isEnableCrop(false)//是否裁剪xx
                        .cutOutQuality(90)// 裁剪输出质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(List<LocalMedia> result) {
                                for (LocalMedia media : result) {
                                    if (result!=null && result.size()>0){
                                        if (avatarPath!=null){
                                            avatarPath.clear();
                                        }
                                        avatarPath.add(media.getCompressPath());
                                    }
                                }
                                Glide.with(getActivity()).load(avatarPath.get(0)).apply(new RequestOptions().circleCrop()).into(dataBinding.ivAvatar);
                                viewModel.getLogo(new File(avatarPath.get(0)));

                            }

                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });
        dataBinding.ivCardFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(SecondStepFragment.this).openGallery(PictureMimeType.ofImage())
                        .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                        .maxSelectNum(1)
                        .isWeChatStyle(true)
                        .isCompress(true)//是否压缩
                        .isEnableCrop(false)//是否裁剪
                        .cutOutQuality(90)// 裁剪输出质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(List<LocalMedia> result) {
                                for (LocalMedia media : result) {
                                    if (frontIdCardUrl!=null){
                                        frontIdCardUrl.clear();
                                    }
                                    frontIdCardUrl.add(media.getCompressPath());
                                }
                                Glide.with(getActivity()).load(frontIdCardUrl.get(0)).into(dataBinding.ivCardFront);

                                viewModel.getFrontUrl(new File(frontIdCardUrl.get(0)));

                            }

                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });
        dataBinding.ivCardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(SecondStepFragment.this).openGallery(PictureMimeType.ofImage())
                        .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                        .maxSelectNum(1)
                        .isWeChatStyle(true)
                        .isCompress(true)//是否压缩
                        .isEnableCrop(false)//是否裁剪
                        .cutOutQuality(90)// 裁剪输出质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(List<LocalMedia> result) {
                                for (LocalMedia media : result) {
                                    if (backIdCardUrl!=null){
                                        backIdCardUrl.clear();
                                    }
                                    backIdCardUrl.add(media.getCompressPath());
                                }
                                Glide.with(getActivity()).load(backIdCardUrl.get(0)).into(dataBinding.ivCardBack);
                                viewModel.getBackUrl(new File(backIdCardUrl.get(0)));
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });
        dataBinding.btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.personalProfile.getValue()!=null){
                    if (qualificationActivity.getType()==null){
                        doctorInfo.setExperience(viewModel.personalProfile.getValue());
                    }else {
                        updateDoctorInfo.setExperience(viewModel.personalProfile.getValue());
//                        if (qualificationActivity.getType().contains("failed")){
//                            updateDoctorInfo.setExperience(viewModel.personalProfile.getValue());
//                        }else {
//                            doctorInfo.setExperience(viewModel.personalProfile.getValue());
//                        }
                    }

                }
                if (viewModel.field.getValue()!=null){
                    if (qualificationActivity.getType()==null){
                        doctorInfo.setSpeciality(viewModel.field.getValue());
                    }else {
                        updateDoctorInfo.setSpeciality(viewModel.field.getValue());
                    }
//                    if (qualificationActivity.getType().contains("failed")){
//                        updateDoctorInfo.setSpeciality(viewModel.field.getValue());
//                    }else {
//                        doctorInfo.setSpeciality(viewModel.field.getValue());
//                    }

                }
                if (qualificationActivity.getType()!=null){
                    viewModel.updateDoctorInfo(updateDoctorInfo);
                }else {
                    viewModel.saveUserInfo(doctorInfo);
                }

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void registerUIChangeLiveDataCallBack() {
        super.registerUIChangeLiveDataCallBack();
        viewModel.logoFile.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!StringUtils.isEmpty(s)){
                    logoUrl = s;
                    if (qualificationActivity.getType()!=null){
                        updateDoctorInfo.setAvatar(logoUrl);
                    }else {
                        doctorInfo.setAvatar(logoUrl);
                    }
//                    if (qualificationActivity.getType().contains("failed")){
//                        updateDoctorInfo.setAvatar(logoUrl);
//                    }else {
//                        doctorInfo.setAvatar(logoUrl);
//                    }

                }

            }
        });
        viewModel.frontUrl.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!StringUtils.isEmpty(s)){
                    frontCardUrl = s;
                    if (qualificationActivity.getType()!=null){
                        updateDoctorInfo.setFrontIdCardUrl(frontCardUrl);
                    }else {
                        doctorInfo.setFrontIdCardUrl(frontCardUrl);
                    }

                }

            }
        });
        viewModel.backUrl.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!StringUtils.isEmpty(s)){
                    backcardUrl = s;
                    if (qualificationActivity.getType()!=null){
                        updateDoctorInfo.setBackIdCardUrl(backcardUrl);
                    }else {
                        doctorInfo.setBackIdCardUrl(backcardUrl);
                    }

                }

            }
        });
        viewModel.certicates.observe(getActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> files) {
                if (files==null){
                    return;
                }
                if (filePaths.size()>0){
                    filePaths.clear();
                }
                if (files.size()>0){
                    filePaths.addAll(files);
                }
                if (qualificationActivity.getType()!=null){
                    updateDoctorInfo.setUrlList(filePaths);
                }else {
                    doctorInfo.setUrlList(filePaths);
                }

                Toast.makeText(getActivity(),"num"+filePaths.size(),Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.isSaveSuccess.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    LiveEventBus.get("ChangeFragment").post(2);
                }
            }
        });
        viewModel.isUpdateSuccess.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    LiveEventBus.get("ChangeFragment").post(2);
                    LiveEventBus.get("type").postAcrossProcess("wait");
                }
            }
        });
    }

    @Override
    protected void initObserver() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_step_second;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }

}
