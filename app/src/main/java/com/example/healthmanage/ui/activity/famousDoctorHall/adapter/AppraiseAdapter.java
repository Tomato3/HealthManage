package com.example.healthmanage.ui.activity.famousDoctorHall.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.AppraiseResponse;
import com.example.healthmanage.utils.ToolUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * desc:
 * date:2021/7/27 9:28
 * author:bWang
 */
public class AppraiseAdapter extends BaseQuickAdapter<AppraiseResponse.DataBean, BaseViewHolder> {
    private List<AppraiseResponse.DataBean> mDataBeans = new ArrayList<>();
    public AppraiseAdapter(@Nullable List<AppraiseResponse.DataBean> data) {
        super(R.layout.item_appraise,data);
        this.mDataBeans = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, AppraiseResponse.DataBean item) {
        helper.setRating(R.id.ratingbar,item.getScore());
        helper.setText(R.id.tv_apprasie,item.getContent());
        helper.setText(R.id.tv_name,item.getAppUser().getNickName());
        helper.setText(R.id.tv_time, ToolUtil.timestampToDate(String.valueOf(item.getCreateTime()),null));
    }

    @Override
    public int getItemCount() {
        return mDataBeans.size()>=3 ? 3 : mDataBeans.size();
    }
}
