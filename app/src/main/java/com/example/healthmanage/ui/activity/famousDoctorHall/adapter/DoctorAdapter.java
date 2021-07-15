package com.example.healthmanage.ui.activity.famousDoctorHall.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.FamousDoctorListResponse;

import java.util.List;

/**
 * desc:
 * date:2021/7/1 15:24
 * author:bWang
 */
public class DoctorAdapter extends BaseQuickAdapter<FamousDoctorListResponse.DataBean, BaseViewHolder> {
    private Context mContext;
    public DoctorAdapter(@Nullable List<FamousDoctorListResponse.DataBean> data,Context context) {
        super(R.layout.item_star_doctor,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, FamousDoctorListResponse.DataBean item) {
        if (item.getAvatar()!=null){
            Glide.with(mContext).load(item.getAvatar()).apply(new RequestOptions().circleCrop())
                    .error(R.drawable.ic_doctor_logo)
                    .into((ImageView) helper.getView(R.id.iv_doctor_logo));
        }
        helper.setText(R.id.tv_nameOrRank,item.getName()+"\u3000"+item.getRank());
        helper.setText(R.id.tv_hospital_rank,item.getAppHospital().getAppHospitalType().getName());
        helper.setText(R.id.tv_hospital_department,item.getAppHospital().getName()+"\u3000"+item.getAppHospitalDepartment().getName());
        helper.setText(R.id.tv_skill,"擅长："+item.getSpeciality());

    }
}
