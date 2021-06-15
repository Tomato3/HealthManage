package com.example.healthmanage.ui.activity.temperature.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityPrescriptionSignNameBinding;
import com.example.healthmanage.ui.activity.workplan.ui.WorkPlanViewModel;
import com.example.healthmanage.utils.PhotoUtils;
import com.example.healthmanage.utils.SPUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.widget.TitleToolBar;

import java.io.File;
import java.io.IOException;

public class SignPrescriptionActivity extends BaseActivity<ActivityPrescriptionSignNameBinding, WorkPlanViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    @Override
    protected void initData() {
        titleToolBar.setTitle("处方签名");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBinding.signatureView.getSigstatus()){
                    try {
                        if (dataBinding.signatureView.save(Environment.getExternalStorageDirectory()+"/jy.png")){
                            Bitmap bitmap = dataBinding.signatureView.getLocalBitmap(Environment.getExternalStorageDirectory()+"/jy.png");
                            File file = PhotoUtils.compressImage(bitmap);
                            viewModel.getPicUrl(file);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    ToastUtil.showShort("请签名");
                }
            }
        });
        dataBinding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBinding.signatureView.clear();
                dataBinding.signatureView.setPaintColor(Color.BLACK);
                dataBinding.signatureView.setPaintWidth(8);
                dataBinding.signatureView.setCanvasColor(Color.WHITE);
            }
        });
        viewModel.picUrl.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Glide.with(SignPrescriptionActivity.this).load(s).apply(new RequestOptions())
                        .into(dataBinding.ivSign);
                SPUtil.saveSignName(s,SignPrescriptionActivity.this);
            }
        });
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
        return R.layout.activity_prescription_sign_name;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
