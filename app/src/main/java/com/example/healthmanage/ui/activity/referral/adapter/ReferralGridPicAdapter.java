package com.example.healthmanage.ui.activity.referral.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.referral.response.ReferralResponse;

import java.util.List;

public class ReferralGridPicAdapter extends BaseQuickAdapter<ReferralResponse.DataBean.AppNinePicturesBean, BaseViewHolder> {
    private Context mContext;

    public ReferralGridPicAdapter(Context context,@Nullable List<ReferralResponse.DataBean.AppNinePicturesBean> data) {
        super(R.layout.item_patient_picture,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ReferralResponse.DataBean.AppNinePicturesBean item) {
        Glide.with(mContext).load(item.getPatientReferralPic()).apply(new RequestOptions().placeholder(R.drawable.ic_size_seat).error(R.drawable.ic_size_seat))
                .into((ImageView) helper.getView(R.id.iv_patient_pic));
    }
}
