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
import com.example.healthmanage.ui.activity.team.response.TeamMemberResponse;

import java.util.List;

public class TeamMemberAdapter extends BaseQuickAdapter<TeamMemberResponse.DataBean, BaseViewHolder> {
    private Context context;
    public TeamMemberAdapter(Context context, @Nullable List<TeamMemberResponse.DataBean> data) {
        super(R.layout.item_team_manager,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TeamMemberResponse.DataBean item) {
        Glide.with(mContext).load(item.getAppDoctorInfo().getAvatar()).apply(new RequestOptions().placeholder(R.drawable.ic_load_error).error(R.drawable.ic_load_error).circleCrop())
                .into((ImageView) helper.getView(R.id.iv_avatar));
        if (item.getRoleId()==11){
            helper.getView(R.id.tv_rank_phone).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_rank_phone,item.getAppDoctorInfo().getRank());
        }else {
            helper.getView(R.id.tv_rank_phone).setVisibility(View.GONE);
        }
        if (item.getAppDoctorInfo().getAppSystemUser()!=null){
            helper.setText(R.id.tv_name,item.getAppDoctorInfo().getName());
            helper.setText(R.id.tv_phone_number,"手机:\b"+item.getAppDoctorInfo().getAppSystemUser().getPhone());
        }
        helper.addOnClickListener(R.id.tv_more_detail);
    }
}
