package com.example.healthmanage.ui.activity.integral.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.integral.response.LogisticResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 * date:2021/7/23 16:11
 * author:bWang
 */
public class LogisticAdapter extends BaseQuickAdapter<LogisticResponse.DataBean.TracesBean, BaseViewHolder> {
    private List<LogisticResponse.DataBean.TracesBean> mTracesBeans = new ArrayList<>();
    public LogisticAdapter( @Nullable List<LogisticResponse.DataBean.TracesBean> data) {
        super(R.layout.item_logistic, data);
        this.mTracesBeans = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, LogisticResponse.DataBean.TracesBean item) {
        helper.setText(R.id.tv_address,item.getAcceptStation());
        helper.setText(R.id.tv_time,item.getAcceptTime());
        if (mTracesBeans!=null && mTracesBeans.size()>0){
            if (item.getAcceptTime().equals(mTracesBeans.get(mTracesBeans.size()-1).getAcceptTime())){
                helper.getView(R.id.view_line).setVisibility(View.GONE);
            }else {
                helper.getView(R.id.view_line).setVisibility(View.VISIBLE);
            }
            if (item.getAcceptTime().equals(mTracesBeans.get(0).getAcceptTime())){
                helper.getView(R.id.iv_points).setBackgroundResource(R.drawable.ic_new);
            }else {
                helper.getView(R.id.iv_points).setBackgroundResource(R.drawable.ic_points_circle);
            }
        }
    }
}
