package com.example.healthmanage.ui.activity.workplan.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.aries.ui.widget.progress.UIProgressDialog;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityCreatePlanBinding;
import com.example.healthmanage.utils.GlideEngine;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.TitleToolBar;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

public class CreateWorkPlanActivity extends BaseActivity<ActivityCreatePlanBinding,WorkPlanViewModel>
        implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener{
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<String> picUrl = new ArrayList<>();
    private String dateTime;
    private UIProgressDialog uiProgressDialog;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {
        dateTime = getIntent().getStringExtra("time");
        titleToolBar.setTitle("创建计划");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setRightTitleVisible(true);
        titleToolBar.setRightTitle("保存");
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        titleToolBar.setRightTitleColor(getResources().getColor(R.color.colorBlue));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        dataBinding.reMainEditor.setEditorFontColor(Color.BLACK);
        dataBinding.reMainEditor.setEditorFontSize(18);
        dataBinding.reMainEditor.setEditorBackgroundColor(Color.WHITE);
        dataBinding.reMainEditor.setPadding(10,10,10,10);
        dataBinding.reMainEditor.setPlaceholder("请输入编辑内容");
        dataBinding.reMainEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {

            }
        });
        dataBinding.layoutChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(CreateWorkPlanActivity.this)
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
                                    if (picUrl!=null){
                                        picUrl.clear();
                                    }
                                    picUrl.add(media.getCompressPath());
                                }
                                viewModel.getPicUrl(new File(picUrl.get(0)));
                                uiProgressDialog = new UIProgressDialog.WeChatBuilder(CreateWorkPlanActivity.this)
                                        .setMessage(R.string.loading)
                                        .setIndeterminateDrawable(R.drawable.dialog_loading_wei_xin)
                                        .setBackgroundRadiusResource(R.dimen.dp_radius_loading)
                                        .create()
                                        .setDimAmount(0.6f);
                                uiProgressDialog.show();
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });

    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.picUrl.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                uiProgressDialog.dismiss();
                if (s!=null){
                    dataBinding.reMainEditor.insertImage(s,"pic");
                }
            }
        });
        dataBinding.layoutTitle.tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBinding.reMainEditor.getHtml()==null){
                    Toast.makeText(CreateWorkPlanActivity.this,"不创建计划",Toast.LENGTH_SHORT).show();
                }else {
                    viewModel.insertWorkPlan(dataBinding.reMainEditor.getHtml(), BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId(), ToolUtil.getCurTime(),dateTime);
                }
            }
        });
        viewModel.insertSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    finish();
                }else {
                    Toast.makeText(CreateWorkPlanActivity.this,"服务器错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_create_plan;
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
}
