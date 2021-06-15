package com.example.healthmanage.ui.activity.temperature.adapter;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.temperature.PopItem;

import java.util.List;

public class ItemPopAdapter extends BaseQuickAdapter<PopItem, BaseViewHolder> {
    Context mContext;
    public ItemPopAdapter(@Nullable List<PopItem> data,Context context) {
        super(R.layout.item_job, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PopItem item) {
        ((TextView)helper.getView(R.id.tv_content)).setText(item.getName());
        helper.getView(R.id.ll_layout).setBackgroundResource(item.isSelect()?R.color.colorTxtBlue:R.color.white);
        helper.getView(R.id.ll_layout).getBackground().mutate().setAlpha(20);
        helper.setTextColor(R.id.tv_content,item.isSelect()?mContext.getResources().getColor(R.color.colorTxtBlue):mContext.getResources().getColor(R.color.colorTxtBlack));
    }
}
