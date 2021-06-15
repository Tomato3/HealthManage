package com.example.healthmanage.ui.activity.consultation.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.consultation.response.ConsultationListResponse;

import java.util.List;

public class GridPicAdapter extends BaseQuickAdapter<ConsultationListResponse.DataBean.AppNinePicturesBean, BaseViewHolder> {
    private Context mContext;

    public GridPicAdapter(Context context,@Nullable List<ConsultationListResponse.DataBean.AppNinePicturesBean> data) {
        super(R.layout.item_patient_picture,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ConsultationListResponse.DataBean.AppNinePicturesBean appNinePicturesBean) {
        Glide.with(mContext).load(appNinePicturesBean.getPatientPic()).apply(new RequestOptions().placeholder(R.drawable.ic_size_seat).error(R.drawable.ic_size_seat))
                .into((ImageView) helper.getView(R.id.iv_patient_pic));
    }
}
