package com.example.healthmanage.ui.activity.temperature.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.temperature.response.TemperatureResponse;

import java.util.List;

public class ReplyRpAdapter extends BaseQuickAdapter<TemperatureResponse.DrugListBean, BaseViewHolder> {
    public ReplyRpAdapter(@Nullable List<TemperatureResponse.DrugListBean> data) {
        super(R.layout.item_reply_drugs,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TemperatureResponse.DrugListBean item) {
        helper.setText(R.id.tv_drugs_name,item.getName());
        helper.setText(R.id.tv_drugs_num,String.valueOf(item.getNumber()));
        helper.setText(R.id.tv_select_num_type,item.getUnit());
        helper.setText(R.id.tv_take_method,item.getUseMode());
        helper.setText(R.id.tv_take_time,item.getUseTime());
        helper.setText(R.id.tv_take_rate,item.getUseFrequency());
    }
}
