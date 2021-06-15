package com.example.healthmanage.ui.activity.healthrecord.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.healthrecord.response.MedicineBookResponse;

import java.util.List;

/**
 * 病历本图片显示适配器
 */
public class MedicinePicAdapter extends BaseQuickAdapter<MedicineBookResponse.DataBean.ListBean, BaseViewHolder> {
    private Context mContext;
    public MedicinePicAdapter(Context context,@Nullable List<MedicineBookResponse.DataBean.ListBean> data) {
        super(R.layout.item_report_pic,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MedicineBookResponse.DataBean.ListBean item) {
        if (item.getUrl()!=null){
            RequestOptions options = new RequestOptions().error(R.drawable.ic_size_seat);
            Glide.with(mContext).load(item.getUrl()).apply(options).into((ImageView) helper.getView(R.id.iv_grid_pic));
        }else {
            Glide.with(mContext).load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=259981538,2780056385&fm=11&gp=0.jpg").apply(new RequestOptions())
                    .into((ImageView) helper.getView(R.id.iv_grid_pic));
        }
    }
}
