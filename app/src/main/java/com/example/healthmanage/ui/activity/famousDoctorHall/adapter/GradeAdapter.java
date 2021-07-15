package com.example.healthmanage.ui.activity.famousDoctorHall.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.HospitalTypeResponse;

import java.util.List;

/**
 * desc:
 * date:2021/7/8 11:26
 * author:bWang
 */
public class GradeAdapter extends BaseQuickAdapter<HospitalTypeResponse.DataBean, BaseViewHolder> {
    private Context mContext;
    public GradeAdapter(@Nullable List<HospitalTypeResponse.DataBean> data, Context context) {
        super(R.layout.item_select_office,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalTypeResponse.DataBean item) {
        helper.getView(R.id.item_recycle_all).setBackgroundResource(item.isSelect()?R.color.color_line_grey:R.color.white);
        helper.setText(R.id.tv_office,item.getName())
                .setTextColor(R.id.tv_office,item.isSelect()?mContext.getResources().getColor(R.color.colorTxtBlue):mContext.getResources().getColor(R.color.colorTxtBlack));
    }
}
