package com.example.healthmanage.ui.activity.temperature.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.ui.activity.temperature.response.TemperatureResponse;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.tools.ScreenUtils;

import java.util.List;

public class TemperatureAdapter extends BaseQuickAdapter<TemperatureResponse.DataBean, BaseViewHolder> {
    private Context mContext;
    String sex;
    int sexSign;
    CheckBox checkBox;
    RecyclerView gridView;
    GridPictureAdapter gridPictureAdapter;

    public TemperatureAdapter(Context context, @Nullable List<TemperatureResponse.DataBean> data) {
        super(R.layout.item_temperature_news,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TemperatureResponse.DataBean item) {
        /**0:保密
         * 1:男
         * 2:女
         * **/
        if (item.getAppUser()!=null){
            sexSign = item.getAppUser().getSex();
            if (sexSign==0){
                sex = "保密";
            }else if (sexSign == 1){
                sex = "男";
            }else{
                sex = "女";
            }
        }
        if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId() ==11){
            helper.getView(R.id.tv_vip_type).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_Withdrawal).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_immediately_reply,"接诊");
            if (item.getAppUser()!=null){
                helper.setText(R.id.tv_info,sex+"\u3000｜\u3000"+item.getAppUser().getOld()+"岁");
            }
        }else {
            helper.getView(R.id.tv_vip_type).setVisibility(View.GONE);
            helper.getView(R.id.tv_Withdrawal).setVisibility(View.GONE);
            helper.setText(R.id.tv_immediately_reply,"立即回复");
        }
        if (item.getStatus()==0){
            helper.getView(R.id.layout_wait_temperature).setVisibility(View.VISIBLE);
            helper.getView(R.id.layout_already_temperature).setVisibility(View.GONE);
            helper.getView(R.id.layout_transmit_temperature).setVisibility(View.GONE);
            //type=0代表团队 1代表个人
            if (item.getType()==0){
                helper.getView(R.id.tv_transmit_reply).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_Withdrawal).setVisibility(View.GONE);
                helper.getView(R.id.tv_vip_type).setBackgroundResource(R.drawable.bg_temperature_yellow);
                helper.setText(R.id.tv_vip_type,"团队患者");
            }else {
                helper.getView(R.id.tv_transmit_reply).setVisibility(View.GONE);
                helper.getView(R.id.tv_Withdrawal).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_vip_type).setBackgroundResource(R.drawable.bg_temperature_soild);
                helper.setText(R.id.tv_vip_type,"单次付费");
            }
        }else{
            helper.getView(R.id.layout_wait_temperature).setVisibility(View.GONE);
            helper.getView(R.id.layout_already_temperature).setVisibility(View.VISIBLE);
            helper.getView(R.id.layout_transmit_temperature).setVisibility(View.GONE);
            helper.setText(R.id.tv_reply_time,"回复时间:"+item.getReplyTime());
        }
        if (item.getRecordList().getTransferStatus()==1){
            helper.getView(R.id.layout_wait_temperature).setVisibility(View.GONE);
            helper.getView(R.id.layout_already_temperature).setVisibility(View.GONE);
            helper.getView(R.id.layout_transmit_temperature).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_transmit_time,"转交时间:"+item.getRecordList().getTransferTime());
            helper.setText(R.id.tv_transmit_person,"转交对象:"+item.getRecordList().getTransferSysNickName()+"\u3000"+item.getRecordList().getTransferRank());
        }
        helper.addOnClickListener(R.id.tv_transmit_reply);
        helper.addOnClickListener(R.id.tv_immediately_reply);
        helper.addOnClickListener(R.id.tv_Withdrawal);
        helper.addOnClickListener(R.id.tv_see_reply);
        if (item.getAppUser()!=null){
            Glide.with(mContext).load(item.getAppUser().getAvatar()).apply(new RequestOptions().placeholder(R.drawable.ic_load_error).error(R.drawable.ic_load_error).circleCrop())
                    .into((ImageView) helper.getView(R.id.iv_avatar));
            helper.setText(R.id.tv_name,item.getAppUser().getNickName());
        }

        if (item.getCreateTime()!=null){
            helper.setText(R.id.tv_consultation_time,"问诊时间:"+item.getCreateTime());
        }
        helper.setText(R.id.tv_disease_description,item.getConsultContent());
        if (item.getPrescriptionStatus()==0){
            helper.setText(R.id.tv_apply_prescription, Html.fromHtml("申请处方:<font color=\"#000000\">\t"+"否"+"</font>"));
        }else {
            helper.setText(R.id.tv_apply_prescription, Html.fromHtml("申请处方:<font color=\"#000000\">\t"+"是"+"</font>"));
        }
        if (item.getList()!=null && item.getList().size()>0){
            helper.getView(R.id.recyclerview_patient_pic).setVisibility(View.GONE);
            helper.getView(R.id.tv_select_recyclerview).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_apply_prescription).setVisibility(View.GONE);
            gridView = helper.getView(R.id.recyclerview_patient_pic);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,5,GridLayoutManager.VERTICAL,false);
            gridView.setLayoutManager(gridLayoutManager);
            GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(5, ScreenUtils.dip2px(mContext,8),false);
            if (gridView.getItemDecorationCount()==0){
                gridView.addItemDecoration(gridSpacingItemDecoration);
            }
            gridPictureAdapter = new GridPictureAdapter(mContext,item.getList());
            gridView.setAdapter(gridPictureAdapter);
            gridPictureAdapter.notifyDataSetChanged();

            checkBox = helper.getView(R.id.tv_select_recyclerview);
            checkBox.setChecked(false);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        helper.getView(R.id.recyclerview_patient_pic).setVisibility(View.VISIBLE);
                        helper.getView(R.id.tv_apply_prescription).setVisibility(View.VISIBLE);
                    }else {
                        helper.getView(R.id.recyclerview_patient_pic).setVisibility(View.GONE);
                        helper.getView(R.id.tv_apply_prescription).setVisibility(View.GONE);
                    }
                }
            });
        }else {
            helper.getView(R.id.recyclerview_patient_pic).setVisibility(View.GONE);
            helper.getView(R.id.tv_select_recyclerview).setVisibility(View.GONE);
            helper.getView(R.id.tv_apply_prescription).setVisibility(View.VISIBLE);
        }
    }
}
