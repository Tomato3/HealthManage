package com.example.healthmanage.ui.activity.academicJournals.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.aries.ui.widget.progress.UIProgressDialog;
import com.contrarywind.view.WheelView;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityCreateAcademicJournalsBinding;
import com.example.healthmanage.ui.activity.academicJournals.adapter.ColumnAdapter;
import com.example.healthmanage.ui.activity.academicJournals.bean.AddPeriodicalBean;
import com.example.healthmanage.ui.activity.mytask.DealTaskActivity;
import com.example.healthmanage.ui.activity.workplan.ui.CreateWorkPlanActivity;
import com.example.healthmanage.utils.GlideEngine;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.TitleToolBar;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 创建学术期刊
 */
public class CreateAcademicJournalsActivity extends BaseActivity<ActivityCreateAcademicJournalsBinding,AcademicJournalsViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private AppCompatActivity context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<String> columnItems = new ArrayList<>();
    private PopupWindow popupWindow;
    private List<String> picUrl = new ArrayList<>();
    private UIProgressDialog uiProgressDialog;
    private AddPeriodicalBean addPeriodicalBean;
    //adapter layout item_academic_journals
    @Override
    protected void initData() {
        context = CreateAcademicJournalsActivity.this;
        viewModel.periodical.setValue("《保健医苑》");
        addPeriodicalBean = new AddPeriodicalBean();
        titleToolBar.setTitle("发布学术期刊");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

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
                addPeriodicalBean.setPeriodical(viewModel.periodical.getValue());
                addPeriodicalBean.setDeliveryColumn(viewModel.contributionColumn.getValue());
                addPeriodicalBean.setPersonalProfile(viewModel.personalInfo.getValue());
                addPeriodicalBean.setTitle(viewModel.journalsTitle.getValue());
                addPeriodicalBean.setContent(dataBinding.reMainEditor.getHtml());
                addPeriodicalBean.setStatus(0);
                addPeriodicalBean.setSystemUserId(BaseApplication.getUserInfoBean().getSysId());
                viewModel.addDraftPeriodical(addPeriodicalBean);
            }
        });

        dataBinding.btnQueryDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPeriodicalBean.setPeriodical(viewModel.periodical.getValue());
                addPeriodicalBean.setStatus(1);
                addPeriodicalBean.setSystemUserId(BaseApplication.getUserInfoBean().getSysId());
                if (TextUtils.isEmpty(viewModel.contributionColumn.getValue())){
                    ToastUtil.showShort("请填写投稿栏目");
                    return;
                }else {
                    addPeriodicalBean.setDeliveryColumn(viewModel.contributionColumn.getValue());
                }
                if (TextUtils.isEmpty(viewModel.personalInfo.getValue())){
                    ToastUtil.showShort("请输入个人简历");
                    return;
                }else {
                    addPeriodicalBean.setPersonalProfile(viewModel.personalInfo.getValue());
                }
                if (TextUtils.isEmpty(viewModel.journalsTitle.getValue())){
                    ToastUtil.showShort("请输入标题");
                    return;
                }else {
                    addPeriodicalBean.setTitle(viewModel.journalsTitle.getValue());
                }
                if (TextUtils.isEmpty(dataBinding.reMainEditor.getHtml())){
                    ToastUtil.showShort("请输入正文内容");
                    return;
                }else {
                    addPeriodicalBean.setContent(dataBinding.reMainEditor.getHtml());
                }
                viewModel.addPeriodical(addPeriodicalBean);
            }
        });

        viewModel.isAddSucceed.observe(this, new Observer<Boolean>() {
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

        viewModel.isAddDraftSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    finish();
                }
            }
        });

//        dataBinding.tvChooseColumn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                chooseColumn(dataBinding.tvChooseColumn.getText().toString());
//            }
//        });
    }

    private void chooseColumn(String content) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_take_column, null, false);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.Animation);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popupWindow.showAtLocation(context.getWindow().getDecorView(), Gravity.BOTTOM, 0,
                ToolUtil.getNavigationBarHeight(context));
        popupWindow.setFocusable(true);
        bgAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                bgAlpha(1);
            }
        });
        WheelView wheelView = view.findViewById(R.id.wheelview1);
        wheelView.setCyclic(false);
        if (columnItems!=null && columnItems.size()>0){
            columnItems.clear();
        }
        columnItems.add("任意");
        columnItems.add("专家访谈");
        columnItems.add("名人访谈");
        columnItems.add("专家论坛");
        columnItems.add("本期话题");
        columnItems.add("医者心声");
        columnItems.add("专家指导");
        columnItems.add("医普园地");
        columnItems.add("季节提醒");
        columnItems.add("哲理人生");
        columnItems.add("海外飞鸿");
        columnItems.add("行游天下");
        columnItems.add("休闲生活");
        columnItems.add("美味天下");
        columnItems.add("情感港湾");
        columnItems.add("养生交流");
        columnItems.add("膳食营养");
        columnItems.add("健康快递");
        columnItems.add("健康饮食");
        columnItems.add("美食天地");
        ColumnAdapter columnAdapter = new ColumnAdapter(columnItems);
        wheelView.setAdapter(columnAdapter);
        for (String item:columnItems) {
            if (item.equals(content)){
                wheelView.setCurrentItem(columnItems.indexOf(content));
            }
        }
//        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(int index) {
//                ToastUtil.showShort(list.get(index));
//            }
//        });
        Button queryBtn = view.findViewById(R.id.btn_confirm);
        Button cancelButton = view.findViewById(R.id.btn_cancel);
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBinding.tvChooseColumn.setText(columnItems.get(wheelView.getCurrentItem()));
                popupWindow.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
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

    private void bgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
}
