package com.example.healthmanage.ui.activity.mytask.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.bean.network.response.DoctorListResponse;
import com.example.healthmanage.ui.activity.team.response.TeamMemberResponse;

import java.util.List;

public class DoctorAdapter extends BaseQuickAdapter<TeamMemberResponse.DataBean, BaseViewHolder> {
    private Context mContext;
    public DoctorAdapter(Context context , @Nullable List<TeamMemberResponse.DataBean> data) {
        super(R.layout.item_tranmit_deal,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamMemberResponse.DataBean item) {
        Glide.with(mContext).load(item.getAppDoctorInfo().getAvatar()).apply(new RequestOptions().placeholder(R.drawable.ic_load_error).error(R.drawable.ic_load_error).circleCrop())
                .into((ImageView) helper.getView(R.id.iv_avatar));
        helper.setText(R.id.tv_doctor_name,item.getAppDoctorInfo().getName());
        helper.setText(R.id.tv_doctor_phone,"手机:\u3000"+item.getAppDoctorInfo().getAppSystemUser().getPhone());
        helper.addOnClickListener(R.id.btn_select);
        helper.setChecked(R.id.btn_select,item.isChecked() ? true : false);
    }
}
