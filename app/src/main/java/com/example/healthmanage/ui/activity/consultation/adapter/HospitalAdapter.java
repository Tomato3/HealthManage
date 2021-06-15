package com.example.healthmanage.ui.activity.consultation.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.consultation.response.DoctorTeamListResponse;
import com.example.healthmanage.ui.activity.consultation.response.DoctordepartMentResponse;

import java.util.List;

public class HospitalAdapter extends BaseQuickAdapter<DoctorTeamListResponse.DataBean, BaseViewHolder> {
    Context mContext;
    public HospitalAdapter(Context context, @Nullable List<DoctorTeamListResponse.DataBean> data) {
        super(R.layout.item_choose_doctor,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorTeamListResponse.DataBean item) {
        Glide.with(mContext).load(item.getAvatar()).apply(new RequestOptions().placeholder(R.drawable.ic_load_error).error(R.drawable.ic_load_error).circleCrop())
                .into((ImageView) helper.getView(R.id.iv_avatar));
        helper.setText(R.id.tv_doctor_name,item.getName());
        helper.setText(R.id.tv_doctor_department,item.getRank());
        helper.setText(R.id.tv_doctor_hospital,item.getAppHospital().getName()+"\u3000"+item.getAppHospitalDepartment().getName());
        helper.addOnClickListener(R.id.btn_select_doctor);
        if (item.isSelect()){
            helper.setChecked(R.id.btn_select_doctor,true);
        }else {
            helper.setChecked(R.id.btn_select_doctor,false);
        }
    }
}
