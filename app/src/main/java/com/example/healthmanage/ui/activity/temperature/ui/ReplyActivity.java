package com.example.healthmanage.ui.activity.temperature.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityReceivePatientBinding;
import com.example.healthmanage.ui.activity.healthreport.ui.CreateHealthReportActivity;
import com.example.healthmanage.ui.activity.healthreport.ui.HealthReportActivity;
import com.example.healthmanage.ui.activity.memberdetail.MemberNewDetailActivity;
import com.example.healthmanage.ui.activity.temperature.InsertPrescriptionBean;
import com.example.healthmanage.ui.activity.temperature.ReceivePatientBean;
import com.example.healthmanage.ui.activity.temperature.adapter.AddPrescriptionAdapter;
import com.example.healthmanage.ui.activity.temperature.adapter.GridPictureAdapter;
import com.example.healthmanage.ui.activity.temperature.response.PrescriptionResponse;
import com.example.healthmanage.ui.activity.temperature.response.TemperatureResponse;
import com.example.healthmanage.utils.SPUtil;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.tools.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 回复/接诊界面
 */
public class ReplyActivity extends BaseActivity<ActivityReceivePatientBinding,TemperatureViewModel> implements TitleToolBar.OnTitleIconClickCallBack, AddPrescriptionAdapter.OnListRemovedListener {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private AppCompatActivity context;
    private TemperatureResponse.DataBean dataBean;
    private String sex;
    private int sexSign;
    private GridPictureAdapter gridPictureAdapter;
    private AddPrescriptionAdapter addPrescriptionAdapter;
    private List<ReceivePatientBean.DrugListBean> prescriptionBeans = new ArrayList<>();
    ReceivePatientBean receivePatientBean;
    private List<PrescriptionResponse.DataBean.DrugListBean> drugListBeanList = new ArrayList<>();
    @Override
    protected void initData() {
        context = ReplyActivity.this;
        receivePatientBean = new ReceivePatientBean();
        dataBean = (TemperatureResponse.DataBean) getIntent().getSerializableExtra("dataBean");
        titleToolBar.setTitle(dataBean.getAppUser().getNickName()+"咨询回复");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setRightTitle("患者信息");
        titleToolBar.setRightTitleVisible(true);
        dataBinding.layoutTitle.tvRight.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_md_description),null,null,null);
        titleToolBar.setRightTitleColor(getResources().getColor(R.color.colorBlack));
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        /**0:保密
         * 1:男
         * 2:女
         * **/
        sexSign = dataBean.getAppUser().getSex();
        if (sexSign==0){
            sex = "保密";
        }else if (sexSign == 1){
            sex = "男";
        }else{
            sex = "女";
        }
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.layoutTitle.tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MemberNewDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MemberName", dataBean.getAppUser().getNickName());
                bundle.putInt("MemberId", dataBean.getAppUser().getId());
//        bundle.putBoolean("FocusState", focusState);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Glide.with(context).load(dataBean.getAppUser().getAvatar()).apply(new RequestOptions().placeholder(R.drawable.ic_size_seat).error(R.drawable.ic_size_seat).circleCrop())
                .into(dataBinding.ivAvatar);
        dataBinding.tvName.setText(dataBean.getAppUser().getNickName());
        if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==11){
            dataBinding.tvInfo.setText(sex+"\u3000｜\u3000"+dataBean.getAppUser().getOld()+"岁");
            dataBinding.layoutDrugList.setVisibility(View.VISIBLE);
            dataBinding.recyclerviewRp.setLayoutManager(new LinearLayoutManager(this));
            prescriptionBeans.add(new ReceivePatientBean.DrugListBean("盒","口服","睡前","一天三次"));
            addPrescriptionAdapter = new AddPrescriptionAdapter(context,prescriptionBeans);
            //最后一个不显示分割线且自定义分割线
            GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
            gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_white));
            if (dataBinding.recyclerviewRp.getItemDecorationCount()==0){
                dataBinding.recyclerviewRp.addItemDecoration(gridItemDecoration);
            }
            dataBinding.recyclerviewRp.setAdapter(addPrescriptionAdapter);
            addPrescriptionAdapter.setOnListRemovedListener(this);
        }else {
            //会员等级
            dataBinding.tvInfo.setText("会员"+sex+"\u3000｜\u3000"+dataBean.getAppUser().getOld()+"岁");
            dataBinding.layoutDrugList.setVisibility(View.GONE);
        }
        if (dataBean.getPrescriptionStatus()==0){
            dataBinding.tvApplyPrescription.setText( Html.fromHtml("申请处方:<font color=\"#000000\">\t"+"否"+"</font>"));
//            dataBinding.layoutDrugList.setVisibility(View.VISIBLE);
        }else {
            dataBinding.tvApplyPrescription.setText(Html.fromHtml("申请处方:<font color=\"#000000\">\t"+"是"+"</font>"));
//            dataBinding.layoutDrugList.setVisibility(View.VISIBLE);
        }
        dataBinding.tvConsultationTime.setText("问诊时间:"+dataBean.getCreateTime());
        dataBinding.tvDiseaseDescription.setText(dataBean.getConsultContent());
        if (dataBean.getList()!=null && dataBean.getList().size()>0){
            dataBinding.recyclerviewPatientPic.setVisibility(View.VISIBLE);
            dataBinding.tvPicNull.setVisibility(View.GONE);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,5,GridLayoutManager.VERTICAL,false);
            dataBinding.recyclerviewPatientPic.setLayoutManager(gridLayoutManager);
            GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(5, ScreenUtils.dip2px(context,8),false);
            if (dataBinding.recyclerviewRp.getItemDecorationCount()==0){
                dataBinding.recyclerviewPatientPic.addItemDecoration(gridSpacingItemDecoration);
            }
            gridPictureAdapter = new GridPictureAdapter(context,dataBean.getList());
            dataBinding.recyclerviewPatientPic.setAdapter(gridPictureAdapter);
            gridPictureAdapter.notifyDataSetChanged();
        }else {
            dataBinding.recyclerviewPatientPic.setVisibility(View.GONE);
            dataBinding.tvPicNull.setVisibility(View.VISIBLE);
        }

        dataBinding.btnQueryReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==11){
                    if (dataBean.getPrescriptionStatus()==0){
                        if (TextUtils.isEmpty(viewModel.relayData.getValue())){
                            ToastUtil.showShort("请输入问诊回复");
                        }else {
                            receivePatientBean.setId(String.valueOf(dataBean.getId()));
                            receivePatientBean.setReplyContent(viewModel.relayData.getValue());
                            receivePatientBean.setToken(BaseApplication.getToken());
                            receivePatientBean.setPhysician(SPUtil.getSignName(context));
                            viewModel.insertHealthConsultDrug(receivePatientBean);
                        }
                    }else {
                        if (TextUtils.isEmpty(viewModel.relayData.getValue())){
                            ToastUtil.showShort("请输入问诊回复");
                        }
                        else if (TextUtils.isEmpty(viewModel.preliminaryDiagnosisData.getValue())){
                            ToastUtil.showShort("请输入初步诊断");
                        }else if (TextUtils.isEmpty(SPUtil.getSignName(context))){
                            UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(context).setMessage("您好，您还未设置处方签名")
                                    .setMessageTextColorResource(R.color.colorBlack)
                                    .setPositiveButton("去签名", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(SignPrescriptionActivity.class);
                                            finish();
                                        }
                                    })
                                    .setMinHeight(SizeUtil.dp2px(160))
                                    .setPositiveButtonTextColorResource(R.color.colorTxtBlue)
                                    .create()
                                    .setDimAmount(0.6f);
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
                            lp.width= (int) (display.getWidth()* 0.5);
                            uiAlertDialog.getWindow().setAttributes(lp);
                        }else if(check(addPrescriptionAdapter.list)){
                            receivePatientBean.setId(String.valueOf(dataBean.getId()));
                            receivePatientBean.setReplyContent(viewModel.relayData.getValue());
                            receivePatientBean.setDiagnosis(viewModel.preliminaryDiagnosisData.getValue());
                            receivePatientBean.setDrugList(addPrescriptionAdapter.list);
                            receivePatientBean.setToken(BaseApplication.getToken());
                            receivePatientBean.setPhysician(SPUtil.getSignName(context));
                            viewModel.insertHealthConsultDrug(receivePatientBean);
                        }else {
                            return;
                        }
                    }
                }else {
                    if (TextUtils.isEmpty(viewModel.relayData.getValue())){
                        ToastUtil.showShort("请输入问诊回复");
                    }else {
                        receivePatientBean.setId(String.valueOf(dataBean.getId()));
                        receivePatientBean.setReplyContent(viewModel.relayData.getValue());
                        receivePatientBean.setToken(BaseApplication.getToken());
                        viewModel.insertHealthConsultDrug(receivePatientBean);
                    }
                }


            }
        });

        viewModel.isReceiveSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    View view = View.inflate(ReplyActivity.this,R.layout.dialog_insert_model,null);
                    TextView tvTitle = view.findViewById(R.id.tv_success);
                    TextView tvContent = view.findViewById(R.id.tv_tips_task);
                    tvTitle.setText("问诊回复成功");
                    tvContent.setText("可在'已回复'里查看回复内容");
                    UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(ReplyActivity.this)
                            .setView(view)
                            .setCanceledOnTouchOutside(false)//设置空白处不消失
                            .setMinHeight(SizeUtil.dp2px(160))
                            .setPositiveButtonTextColorResource(R.color.colorTxtBlue)
                            .create()
                            .setDimAmount(0.6f);
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
                    lp.width= (int) (display.getWidth()* 0.5);
                    uiAlertDialog.getWindow().setAttributes(lp);
                    Button button = view.findViewById(R.id.btn_success_insert);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }else {
                    return;
                }
            }
        });
        dataBinding.tvChooseRp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ChoosePrescriptionActivity.class);
                startActivityForResult(intent,2);
            }
        });
    }

    public boolean check(List<ReceivePatientBean.DrugListBean> drugListBeanList){
        for (ReceivePatientBean.DrugListBean drugListBean:drugListBeanList) {
            if (TextUtils.isEmpty(drugListBean.getName())){
                ToastUtil.showShort("请填写药品名称");
                return false;
            }else if (drugListBean.getNumber()==0){
                ToastUtil.showShort("请填写药品数量");
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            if (requestCode == 2){
                if (resultCode == RESULT_OK){
                    if (drugListBeanList != null && drugListBeanList.size()>0){
                        drugListBeanList.clear();
                    }
                    Bundle bundle = data.getExtras();
                    drugListBeanList = (List<PrescriptionResponse.DataBean.DrugListBean>) bundle.getSerializable("drugList");
                    if (addPrescriptionAdapter.list!=null && addPrescriptionAdapter.list.size()>0){
                        addPrescriptionAdapter.list.clear();
                    }
                    List<ReceivePatientBean.DrugListBean> listBeans = new ArrayList<>();
                    if (listBeans!=null && listBeans.size()>0){
                        listBeans.clear();
                    }
                    for (PrescriptionResponse.DataBean.DrugListBean drugData : drugListBeanList) {
                        ReceivePatientBean.DrugListBean drugListBean = new ReceivePatientBean.DrugListBean(drugData.getUnit(),drugData.getUseMode(),drugData.getUseTime(),drugData.getUseFrequency());
                        drugListBean.setName(drugData.getName());
                        drugListBean.setNumber(drugData.getNumber());
                        listBeans.add(drugListBean);
                    }
                    addPrescriptionAdapter.list.addAll(listBeans);
                    addPrescriptionAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_receive_patient;
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

    @Override
    public void onRemoved() {
        addPrescriptionAdapter.notifyDataSetChanged();
    }
}
