package com.example.healthmanage.ui.fragment.my;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import com.example.healthmanage.ui.activity.my.aboutus.AboutUsActivity;
import com.example.healthmanage.ui.activity.myinfo.MyInfoActivity;
import com.example.healthmanage.ui.activity.mypoint.MyPointActivity;
import com.example.healthmanage.utils.GlideEngine;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
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
                startActivity(MyPointActivity.class);
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
                        .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                        .maxSelectNum(1)
                        .isWeChatStyle(true)
                        .isCompress(true)//是否压缩
                        .isEnableCrop(true)//是否裁剪
                        .cutOutQuality(90)// 裁剪输出质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {// 图片选择结果回调
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                // 例如 LocalMedia 里面返回五种path
                // 1.media.getPath(); 原图path
                // 2.media.getCutPath();裁剪后path，需判断media.isCut();切勿直接使用
                // 3.media.getCompressPath();压缩后path，需判断media.isCompressed();切勿直接使用
                // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
                // 5.media.getAndroidQToPath();Android Q版本特有返回的字段，但如果开启了压缩或裁剪还是取裁剪或压缩路径；注意：.isAndroidQTransform 为false 此字段将返回空
                // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
                for (LocalMedia media : selectList) {
                    Log.i(TAG, "是否压缩:" + media.isCompressed());
                    Log.i(TAG, "压缩:" + media.getCompressPath());
                    Log.i(TAG, "原图:" + media.getPath());
                    Log.i(TAG, "绝对路径:" + media.getRealPath());
                    Log.i(TAG, "是否裁剪:" + media.isCut());
                    Log.i(TAG, "裁剪:" + media.getCutPath());
                    Log.i(TAG, "是否开启原图:" + media.isOriginal());
                    Log.i(TAG, "原图路径:" + media.getOriginalPath());
                    Log.i(TAG, "Android Q 特有Path:" + media.getAndroidQToPath());
                    Log.i(TAG, "宽高: " + media.getWidth() + "x" + media.getHeight());
                    Log.i(TAG, "Size: " + media.getSize());
                    path.add(media.getCutPath());
                    // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息
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
