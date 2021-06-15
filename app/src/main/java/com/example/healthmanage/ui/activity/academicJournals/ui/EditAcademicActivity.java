package com.example.healthmanage.ui.activity.academicJournals.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.aries.ui.widget.progress.UIProgressDialog;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityCreateAcademicJournalsBinding;
import com.example.healthmanage.ui.activity.academicJournals.bean.AddPeriodicalBean;
import com.example.healthmanage.ui.activity.academicJournals.bean.EditPeriodicalBean;
import com.example.healthmanage.ui.activity.academicJournals.response.PeriodicalListResponse;
import com.example.healthmanage.utils.GlideEngine;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.widget.TitleToolBar;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 修改学术期刊
 */
public class EditAcademicActivity extends BaseActivity<ActivityCreateAcademicJournalsBinding,AcademicJournalsViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private AppCompatActivity context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private PeriodicalListResponse.DataBean dataBean;
    private List<String> picUrl = new ArrayList<>();
    private UIProgressDialog uiProgressDialog;
    private EditPeriodicalBean editPeriodicalBean;
    @Override
    protected void initData() {
        context = EditAcademicActivity.this;
        editPeriodicalBean = new EditPeriodicalBean();
        titleToolBar.setTitle("发布学术期刊");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        dataBean = (PeriodicalListResponse.DataBean) getIntent().getSerializableExtra("dataBean");

        dataBinding.reMainEditor.setEditorFontColor(Color.BLACK);
        dataBinding.reMainEditor.setEditorFontSize(18);
        dataBinding.reMainEditor.setEditorBackgroundColor(Color.WHITE);
        dataBinding.reMainEditor.setPadding(10,10,10,10);
        dataBinding.reMainEditor.setPlaceholder("请输入编辑内容");
        //编辑
//        dataBinding.reMainEditor.setHtml();

        dataBinding.layoutChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(context)
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
                                uiProgressDialog = new UIProgressDialog.WeChatBuilder(context)
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
        viewModel.periodical.setValue(dataBean.getPeriodical());
        viewModel.contributionColumn.setValue(dataBean.getDeliveryColumn());
        viewModel.personalInfo.setValue(dataBean.getPersonalProfile());
        viewModel.journalsTitle.setValue(dataBean.getTitle());
        dataBinding.reMainEditor.setHtml(dataBean.getContent());

        viewModel.picUrl.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                uiProgressDialog.dismiss();
                if (s!=null){
                    dataBinding.reMainEditor.insertImage(s,"pic vision\" style=\"max-width:100%");
                }
            }
        });

        dataBinding.btnSaveDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPeriodicalBean.setPeriodical(viewModel.periodical.getValue());
                editPeriodicalBean.setDeliveryColumn(viewModel.contributionColumn.getValue());
                editPeriodicalBean.setPersonalProfile(viewModel.personalInfo.getValue());
                editPeriodicalBean.setTitle(viewModel.journalsTitle.getValue());
                editPeriodicalBean.setContent(dataBinding.reMainEditor.getHtml());
                editPeriodicalBean.setStatus(0);
                editPeriodicalBean.setId(dataBean.getId());
                editPeriodicalBean.setSystemUserId(BaseApplication.getUserInfoBean().getSysId());
                viewModel.editDraftPeriodical(editPeriodicalBean);
            }
        });

        dataBinding.btnQueryDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPeriodicalBean.setPeriodical(viewModel.periodical.getValue());
                editPeriodicalBean.setStatus(1);
                editPeriodicalBean.setId(dataBean.getId());
                editPeriodicalBean.setSystemUserId(BaseApplication.getUserInfoBean().getSysId());
                if (TextUtils.isEmpty(viewModel.contributionColumn.getValue())){
                    ToastUtil.showShort("请填写投稿栏目");
                    return;
                }else {
                    editPeriodicalBean.setDeliveryColumn(viewModel.contributionColumn.getValue());
                }
                if (TextUtils.isEmpty(viewModel.personalInfo.getValue())){
                    ToastUtil.showShort("请输入个人简历");
                    return;
                }else {
                    editPeriodicalBean.setPersonalProfile(viewModel.personalInfo.getValue());
                }
                if (TextUtils.isEmpty(viewModel.journalsTitle.getValue())){
                    ToastUtil.showShort("请输入标题");
                    return;
                }else {
                    editPeriodicalBean.setTitle(viewModel.journalsTitle.getValue());
                }
                if (TextUtils.isEmpty(dataBinding.reMainEditor.getHtml())){
                    ToastUtil.showShort("请输入正文内容");
                    return;
                }else {
                    editPeriodicalBean.setContent(dataBinding.reMainEditor.getHtml());
                }
                viewModel.editPeriodical(editPeriodicalBean);
            }
        });

        viewModel.isEditSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    View view = View.inflate(context,R.layout.dialog_create_consultation_task,null);
                    UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(context)
                            .setView(view)
                            .setCanceledOnTouchOutside(false)//设置空白处不消失
                            .setMinHeight(SizeUtil.dp2px(160))
                            .setPositiveButtonTextColorResource(R.color.colorTxtBlue)
                            .create()
                            .setDimAmount(0.6f);
                    TextView tvTitle = view.findViewById(R.id.tv_success);
                    TextView tvContent = view.findViewById(R.id.tv_tips_task);
                    tvTitle.setText("投稿成功");
                    tvContent.setText("请耐心等待审核，审核结果将以app消息通知到您");
                    uiAlertDialog.show();
                    Window window = uiAlertDialog.getWindow();
                    WindowManager.LayoutParams lp = window.getAttributes();
                    lp.gravity = Gravity.CENTER;
                    //dialog宽高适应子布局xml
                    //lp.width = WindowManager.LayoutParams.MATCH_PARENT;//宽高可设置具体大小
                    //dialog宽高适应屏幕
                    WindowManager manager= getWindowManager();
                    Display display= manager.getDefaultDisplay();
                    //params.height= (int) (display.getHeight()* 0.8);
                    lp.width= (int) (display.getWidth()* 0.6);
                    uiAlertDialog.getWindow().setAttributes(lp);
                    Button button = view.findViewById(R.id.btn_success_consultation);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }
            }
        });
        viewModel.isEditDraftSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    finish();
                }
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
        return R.layout.activity_create_academic_journals;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
