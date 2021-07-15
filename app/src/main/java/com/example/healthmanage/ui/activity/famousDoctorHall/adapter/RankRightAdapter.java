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
public class RankRightAdapter extends BaseQuickAdapter<RankBean.DataBean.ListBean, BaseViewHolder> {
    private Context mContext;
    public RankRightAdapter(@Nullable List<RankBean.DataBean.ListBean> data, Context context) {
        super(R.layout.item_select_right,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RankBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_rank,item.getName())
                .setTextColor(R.id.tv_rank,item.isSelect()?mContext.getResources().getColor(R.color.colorTxtBlue):mContext.getResources().getColor(R.color.colorTxtBlack));
    }
}
