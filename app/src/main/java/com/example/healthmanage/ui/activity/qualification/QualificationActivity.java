package com.example.healthmanage.ui.activity.qualification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityQualificationBinding;
import com.example.healthmanage.widget.TitleToolBar;
import com.wildma.idcardcamera.camera.IDCardCamera;

import me.tatarka.bindingcollectionadapter2.BR;

public class QualificationActivity extends BaseActivity<ActivityQualificationBinding, QualificationViewModel> implements View.OnClickListener {

    TitleToolBar titleToolBar = new TitleToolBar();
    int REQUEST_CODE = 0;

    @Override
    protected void initData() {
        titleToolBar.setTitle("资格认证");
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(new TitleToolBar.OnTitleIconClickCallBack() {
            @Override
            public void onRightIconClick() {

            }

            @Override
            public void onBackIconClick() {
                finish();
            }
        });
    }


    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.tvUploadIdCard.setOnClickListener(this::onClick);
        dataBinding.tvUploadProfessionalQualificationCertificate.setOnClickListener(this::onClick);
        dataBinding.tvNext.setOnClickListener(this::onClick);
        dataBinding.tvUploadFace.setOnClickListener(this::onClick);
        dataBinding.tvUploadBack.setOnClickListener(this::onClick);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_qualification;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_upload_face:
                IDCardCamera.create(this).openCamera(IDCardCamera.TYPE_IDCARD_FRONT);
                break;
            case R.id.tv_upload_back:
                IDCardCamera.create(this).openCamera(IDCardCamera.TYPE_IDCARD_BACK);
                break;
            case R.id.tv_next:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
