package com.example.healthmanage.ui.activity.delegate.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.delegate.response.DelegateListResponse;

import java.util.List;

public class DelegatePicAdapter extends BaseQuickAdapter<DelegateListResponse.DataBean.ListBean, BaseViewHolder> {
    private Context context;
    public DelegatePicAdapter(Context context, @Nullable List<DelegateListResponse.DataBean.ListBean> data) {
        super(R.layout.item_patient_picture,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DelegateListResponse.DataBean.ListBean item) {
        Glide.with(context).load(item.getUrl()).apply(new RequestOptions().placeholder(R.drawable.ic_size_seat).error(R.drawable.ic_size_seat))
                .into((ImageView) helper.getView(R.id.iv_patient_pic));
    }
}
