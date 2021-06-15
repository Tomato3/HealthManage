package com.example.healthmanage.ui.activity.temperature.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityConsultationReplyBinding;
import com.example.healthmanage.ui.activity.memberdetail.MemberNewDetailActivity;
import com.example.healthmanage.ui.activity.temperature.adapter.GridPictureAdapter;
import com.example.healthmanage.ui.activity.temperature.adapter.ReplyRpAdapter;
import com.example.healthmanage.ui.activity.temperature.response.TemperatureResponse;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.tools.ScreenUtils;

/**
 * 查看咨询回复
 */
public class TemperatureReplyActivity extends BaseActivity<ActivityConsultationReplyBinding,TemperatureViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private Context context;
    private TemperatureResponse.DataBean dataBean;
    private String sex;
    private int sexSign;
    private GridPictureAdapter gridPictureAdapter;
    private ReplyRpAdapter replyRpAdapter;
    @Override
    protected void initData() {
        context = TemperatureReplyActivity.this;
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
        if (!TextUtils.isEmpty(dataBean.getReason())){
            dataBinding.tvReason.setText("退诊原因:");
            dataBinding.tvReplyConsultation.setText(dataBean.getReason());
        }else {
            dataBinding.tvReason.setText("问诊回复:");
            dataBinding.tvReplyConsultation.setText(dataBean.getReplyContent());
        }
        if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId() ==11){
            dataBinding.tvInfo.setText(sex+"\u3000｜\u3000"+dataBean.getAppUser().getOld()+"岁");
            dataBinding.layoutDrugList.setVisibility(View.VISIBLE);
            if (dataBean.getPrescriptionStatus()==0){
                dataBinding.tvApplyPrescription.setText( Html.fromHtml("申请处方:<font color=\"#000000\">\t"+"否"+"</font>"));
                dataBinding.layoutDrugList.setVisibility(View.GONE);
            }else {
                dataBinding.tvApplyPrescription.setText(Html.fromHtml("申请处方:<font color=\"#000000\">\t"+"是"+"</font>"));
                dataBinding.layoutDrugList.setVisibility(View.VISIBLE);
                dataBinding.tvPreliminaryDiagnosis.setText(dataBean.getDiagnosis());
                dataBinding.recyclerviewRp.setLayoutManager(new LinearLayoutManager(this));
                //最后一个不显示分割线且自定义分割线
                GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
                gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_white));
                if (dataBinding.recyclerviewRp.getItemDecorationCount()==0){
                    dataBinding.recyclerviewRp.addItemDecoration(gridItemDecoration);
                }
                replyRpAdapter = new ReplyRpAdapter(dataBean.getDrugList());
                dataBinding.recyclerviewRp.setAdapter(replyRpAdapter);
                replyRpAdapter.notifyDataSetChanged();
            }
        }else {
            //会员等级
            dataBinding.tvInfo.setText("会员"+sex+"\u3000｜\u3000"+dataBean.getAppUser().getOld()+"岁");
            dataBinding.layoutDrugList.setVisibility(View.GONE);
            if (dataBean.getPrescriptionStatus()==0){
                dataBinding.tvApplyPrescription.setText( Html.fromHtml("申请处方:<font color=\"#000000\">\t"+"否"+"</font>"));
            }else {
                dataBinding.tvApplyPrescription.setText(Html.fromHtml("申请处方:<font color=\"#000000\">\t"+"是"+"</font>"));
            }
        }
        dataBinding.tvConsultationTime.setText("问诊时间:"+dataBean.getCreateTime());
        dataBinding.tvDiseaseDescription.setText(dataBean.getConsultContent());
        if (dataBean.getList()!=null && dataBean.getList().size()>0){
            dataBinding.recyclerviewPatientPic.setVisibility(View.VISIBLE);
            dataBinding.tvPicNull.setVisibility(View.GONE);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,5,GridLayoutManager.VERTICAL,false);
            dataBinding.recyclerviewPatientPic.setLayoutManager(gridLayoutManager);
            GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(5, ScreenUtils.dip2px(context,8),false);
            if (dataBinding.recyclerviewPatientPic.getItemDecorationCount()==0){
                dataBinding.recyclerviewPatientPic.addItemDecoration(gridSpacingItemDecoration);
            }
            gridPictureAdapter = new GridPictureAdapter(context,dataBean.getList());
            dataBinding.recyclerviewPatientPic.setAdapter(gridPictureAdapter);
            gridPictureAdapter.notifyDataSetChanged();
        }else {
            dataBinding.recyclerviewPatientPic.setVisibility(View.GONE);
            dataBinding.tvPicNull.setVisibility(View.VISIBLE);
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
        return R.layout.activity_consultation_reply;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
