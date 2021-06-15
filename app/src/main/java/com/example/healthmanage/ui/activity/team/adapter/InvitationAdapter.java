package com.example.healthmanage.ui.activity.team.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.notice.response.TeamApplyResponse;

import java.util.List;

public class InvitationAdapter extends BaseQuickAdapter<TeamApplyResponse.DataBean, BaseViewHolder> {
    private Context mContext;

    public InvitationAdapter(Context context, @Nullable List<TeamApplyResponse.DataBean> data) {
        super(R.layout.item_unsign_member,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamApplyResponse.DataBean item) {
        helper.setText(R.id.vip_name_tv,item.getAppDoctorInfo().getName());
        Glide.with(mContext).load(item.getAppDoctorInfo().getAvatar()).apply(new RequestOptions().placeholder(R.drawable.ic_load_error).error(R.drawable.ic_load_error).circleCrop())
                .into((ImageView) helper.getView(R.id.vip_ava_img));

        if (item.getAppDoctorInfo().getAppSystemUser()!=null){
            if (item.getAppDoctorInfo().getRoleId()==11){
                helper.setText(R.id.vip_info_tv,item.getAppDoctorInfo().getRank()+"\u3000｜\u3000"+item.getAppDoctorInfo().getAppHospitalDepartment().getName()+"\u3000｜\u3000"+item.getAppDoctorInfo().getAppSystemUser().getPhone());
            }else {
                helper.setText(R.id.vip_info_tv,item.getAppDoctorInfo().getRank()+"\u3000｜\u3000"+item.getAppDoctorInfo().getAppSystemUser().getPhone());
            }
        }

        if (item.getStatus()==0){
            helper.getView(R.id.vip_cancel).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_sign).setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.tv_sign);
            helper.addOnClickListener(R.id.vip_cancel);
        }else {
            helper.getView(R.id.vip_cancel).setVisibility(View.GONE);
            helper.getView(R.id.tv_sign).setVisibility(View.GONE);
        }

    }
}
