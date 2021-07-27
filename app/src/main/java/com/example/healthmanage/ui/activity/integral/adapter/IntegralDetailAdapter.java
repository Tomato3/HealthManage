package com.example.healthmanage.ui.activity.integral.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.integral.response.IntegralDetailResponse;

import java.util.List;

/**
 * desc:
 * date:2021/7/19 9:38
 * author:bWang
 */
public class IntegralDetailAdapter extends BaseQuickAdapter<IntegralDetailResponse.DataBean, BaseViewHolder> {
    public IntegralDetailAdapter(@Nullable List<IntegralDetailResponse.DataBean> data) {
        super(R.layout.item_integral_detail,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegralDetailResponse.DataBean item) {
        helper.setText(R.id.tv_integral_date,item.getCreateTime());
        if (item.getType()==0){
            helper.setText(R.id.tv_integral_content,"+"+item.getValue());
            helper.setText(R.id.tv_integral_title,item.getChangeReason());
        }else {
            helper.setText(R.id.tv_integral_content,"-"+item.getValue());
            helper.setText(R.id.tv_integral_title,"兑换商品:"+item.getChangeReason());
        }

    }
}
