package com.example.healthmanage.ui.activity.temperature.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.temperature.response.TemperatureResponse;

import java.util.List;

public class GridPictureAdapter extends BaseQuickAdapter<TemperatureResponse.DataBean.ListBean, BaseViewHolder> {
    Context mContext;
    public GridPictureAdapter(Context context ,@Nullable List<TemperatureResponse.DataBean.ListBean> data) {
        super(R.layout.item_patient_picture,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TemperatureResponse.DataBean.ListBean item) {
        Glide.with(mContext).load(item.getUrl()).apply(new RequestOptions().placeholder(R.drawable.ic_size_seat).error(R.drawable.ic_size_seat))
                .into((ImageView) helper.getView(R.id.iv_patient_pic));
    }
}
