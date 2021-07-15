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
import com.example.healthmanage.ui.activity.famousDoctorHall.response.HospitalListResponse;

import java.util.List;

/**
 * desc:
 * date:2021/7/1 15:43
 * author:bWang
 */
public class HospitalAdapter extends BaseQuickAdapter<HospitalListResponse.DataBean, BaseViewHolder> {
    private Context mContext;
    public HospitalAdapter(@Nullable List<HospitalListResponse.DataBean> data, Context context) {
        super(R.layout.item_hall_hospital,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalListResponse.DataBean item) {
        if (item.getAvatar()!=null){
            Glide.with(mContext).load(item.getAvatar()).apply(new RequestOptions().circleCrop())
                    .error(R.drawable.ic_hospital)
                    .into((ImageView) helper.getView(R.id.iv_hospital_logo));
        }
        helper.setText(R.id.tv_hospitalName,item.getName());
        helper.setText(R.id.tv_hospital_rank,item.getAppHospitalType().getName());
        helper.setText(R.id.tv_hospital_address,item.getAddr());
    }
}
