package com.example.healthmanage.ui.fragment.my;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentNewMyBinding;
import com.example.healthmanage.ui.activity.appraise.ui.AppraiseListActivity;
import com.example.healthmanage.ui.activity.integral.ui.IntegralActivity;
import com.example.healthmanage.ui.activity.my.aboutus.AboutUsActivity;
import com.example.healthmanage.ui.activity.myinfo.MyInfoActivity;
import com.example.healthmanage.ui.activity.mypoint.MyPointActivity;
import com.example.healthmanage.utils.GlideEngine;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class NewMyFragment extends BaseFragment<FragmentNewMyBinding,MyViewModel> implements View.OnClickListener {
    private List<String> path;
    private final static String TAG = "=========";
    private boolean isTrue;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_to_information:
                startActivity(MyInfoActivity.class);
                break;
            case R.id.tv_about_us:
                startActivity(AboutUsActivity.class);
                break;
            case R.id.tv_mypoint:
                startActivity(IntegralActivity.class);
                break;
            case R.id.tv_mycomment:
                startActivity(AppraiseListActivity.class);
                break;
        }
    }

    @Override
    protected void initData() {
        path = new ArrayList<>();
        isTrue = getActivity().getIntent().getBooleanExtra("isSuccess",false);
        if (isTrue){
            dataBinding.ivToInformation.setOnClickListener(this::onClick);
            dataBinding.ivToInformation.setVisibility(View.VISIBLE);
            dataBinding.tvNickName.setVisibility(View.VISIBLE);
            dataBinding.tvNickName.setText(BaseApplication.getUserInfoBean().getAppDoctorInfo().getName());
            Glide.with(this)
                    .load(BaseApplication.getUserInfoBean().getAppDoctorInfo().getAvatar())
                    .apply(new RequestOptions().circleCrop())
                    .into(dataBinding.avatarImgMy);
            dataBinding.typeTv.setVisibility(View.VISIBLE);
            dataBinding.typeTv.setText(BaseApplication.getUserInfoBean().getAppDoctorInfo().getRank());
            dataBinding.renzhengTv.setVisibility(View.VISIBLE);
            dataBinding.tvGorenzheng.setVisibility(View.GONE);
            dataBinding.tvNorenzheng.setVisibility(View.GONE);
        }else {
            dataBinding.ivToInformation.setVisibility(View.GONE);
            dataBinding.tvNickName.setVisibility(View.GONE);
            dataBinding.typeTv.setVisibility(View.GONE);
            dataBinding.renzhengTv.setVisibility(View.GONE);
            dataBinding.tvGorenzheng.setVisibility(View.VISIBLE);
            dataBinding.tvNorenzheng.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1629493508,3312904397&fm=26&gp=0.jpg")
                    .apply(new RequestOptions().circleCrop())
                    .into(dataBinding.avatarImgMy);
        }

        if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==9){
            dataBinding.tvMyorder.setVisibility(View.GONE);
            dataBinding.tvMycomment.setVisibility(View.GONE);
            dataBinding.tvMywallet.setVisibility(View.VISIBLE);
            dataBinding.tvMyWallet.setVisibility(View.GONE);
            dataBinding.tvMyAgent.setVisibility(View.GONE);
            dataBinding.tvMyCollect.setVisibility(View.VISIBLE);
        }else if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==11){
            dataBinding.tvMyorder.setVisibility(View.GONE);
            dataBinding.tvMycomment.setVisibility(View.VISIBLE);
            dataBinding.tvMywallet.setVisibility(View.VISIBLE);
            dataBinding.tvMyWallet.setVisibility(View.GONE);
            dataBinding.tvMyAgent.setVisibility(View.GONE);
            dataBinding.tvMyCollect.setVisibility(View.VISIBLE);
        }else {
            dataBinding.tvMyorder.setVisibility(View.VISIBLE);
            dataBinding.tvMycomment.setVisibility(View.VISIBLE);
            dataBinding.tvMywallet.setVisibility(View.GONE);
            dataBinding.tvMyWallet.setVisibility(View.VISIBLE);
            dataBinding.tvMyAgent.setVisibility(View.VISIBLE);
            dataBinding.tvMyCollect.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initView() {
        dataBinding.tvGorenzheng.setOnClickListener(this::onClick);
        dataBinding.tvMypoint.setOnClickListener(this::onClick);
        dataBinding.tvMyorder.setOnClickListener(this::onClick);
        dataBinding.tvMycomment.setOnClickListener(this::onClick);
        dataBinding.tvShenfenRenzheng.setOnClickListener(this::onClick);
        dataBinding.tvMyWallet.setOnClickListener(this::onClick);
        dataBinding.tvMyStudy.setOnClickListener(this::onClick);
        dataBinding.tvMyAgent.setOnClickListener(this::onClick);
        dataBinding.tvAboutUs.setOnClickListener(this::onClick);
        dataBinding.tvServiceCenter.setOnClickListener(this::onClick);
        dataBinding.tvAdvice.setOnClickListener(this::onClick);
        dataBinding.avatarImgMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(getActivity()).openGallery(PictureMimeType.ofImage())
                        .loadImageEngine(GlideEngine.createGlideEngine()) // ?????????Demo GlideEngine.java
                        .maxSelectNum(1)
                        .isWeChatStyle(true)
                        .isCompress(true)//????????????
                        .isEnableCrop(true)//????????????
                        .cutOutQuality(90)// ?????????????????? ??????100
                        .minimumCompressSize(100)// ??????100kb??????????????????
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {// ????????????????????????
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                // ?????? LocalMedia ??????????????????path
                // 1.media.getPath(); ??????path
                // 2.media.getCutPath();?????????path????????????media.isCut();??????????????????
                // 3.media.getCompressPath();?????????path????????????media.isCompressed();??????????????????
                // 4.media.getOriginalPath()); media.isOriginal());???true?????????????????????
                // 5.media.getAndroidQToPath();Android Q?????????????????????????????????????????????????????????????????????????????????????????????????????????.isAndroidQTransform ???false ?????????????????????
                // ???????????????????????????????????????????????????????????????????????????????????????
                for (LocalMedia media : selectList) {
                    Log.i(TAG, "????????????:" + media.isCompressed());
                    Log.i(TAG, "??????:" + media.getCompressPath());
                    Log.i(TAG, "??????:" + media.getPath());
                    Log.i(TAG, "????????????:" + media.getRealPath());
                    Log.i(TAG, "????????????:" + media.isCut());
                    Log.i(TAG, "??????:" + media.getCutPath());
                    Log.i(TAG, "??????????????????:" + media.isOriginal());
                    Log.i(TAG, "????????????:" + media.getOriginalPath());
                    Log.i(TAG, "Android Q ??????Path:" + media.getAndroidQToPath());
                    Log.i(TAG, "??????: " + media.getWidth() + "x" + media.getHeight());
                    Log.i(TAG, "Size: " + media.getSize());
                    path.add(media.getCutPath());
                    // TODO ????????????PictureSelectorExternalUtils.getExifInterface();??????????????????????????????????????????????????????????????????????????????
                }
                Toast.makeText(getActivity(),path.get(0),Toast.LENGTH_SHORT).show();
                Glide.with(getActivity()).load(path.get(0)).into(dataBinding.avatarImgMy);
            }
        }
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void initObserver() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_new_my;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}
