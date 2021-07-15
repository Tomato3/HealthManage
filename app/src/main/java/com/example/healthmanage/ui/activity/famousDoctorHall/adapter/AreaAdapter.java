package com.example.healthmanage.ui.activity.famousDoctorHall.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.ChinaCityDataBean;

import java.util.List;

/**
 * desc:
 * date:2021/7/5 14:15
 * author:bWang
 */
public class AreaAdapter extends BaseQuickAdapter<ChinaCityDataBean.CityListBean.AreaListBean, BaseViewHolder> {
    private Context mContext;
    public AreaAdapter(@Nullable List<ChinaCityDataBean.CityListBean.AreaListBean> data,Context context) {
        super(R.layout.item_select_right,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ChinaCityDataBean.CityListBean.AreaListBean item) {
        helper.setText(R.id.tv_rank,item.getName())
                .setTextColor(R.id.tv_rank,item.isSelect()?mContext.getResources().getColor(R.color.colorTxtBlue):mContext.getResources().getColor(R.color.colorTxtBlack));
    }
}
