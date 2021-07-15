package com.example.healthmanage.ui.activity.famousDoctorHall.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.famousDoctorHall.bean.RankBean;

import java.util.List;

/**
 * desc:
 * date:2021/7/2 11:16
 * author:bWang
 */
public class RankLeftAdapter extends BaseQuickAdapter<RankBean.DataBean, BaseViewHolder> {
    private Context mContext;
    public RankLeftAdapter(@Nullable  List<RankBean.DataBean> data, Context context) {
        super(R.layout.item_select_office,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RankBean.DataBean item) {
        helper.getView(R.id.item_recycle_all).setBackgroundResource(item.isSelect()?R.color.color_line_grey:R.color.white);
        helper.setText(R.id.tv_office,item.getName())
                .setTextColor(R.id.tv_office,item.isSelect()?mContext.getResources().getColor(R.color.colorTxtBlue):mContext.getResources().getColor(R.color.colorTxtBlack));
    }
}
