package com.example.healthmanage.ui.activity.qualification.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.qualification.response.HospitalResponse;

import java.util.List;

public class HospitalAdapter extends BaseQuickAdapter<HospitalResponse.DataBean, BaseViewHolder> {
    public HospitalAdapter(@Nullable List<HospitalResponse.DataBean> data) {
        super(R.layout.item_hospital,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalResponse.DataBean item) {
        helper.setText(R.id.tv_hospital,item.getName());
    }
}
