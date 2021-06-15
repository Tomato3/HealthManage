package com.example.healthmanage.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.bean.ProfessionBeanResponse;

import java.util.List;

public class ProfessionListAdapter extends BaseQuickAdapter<ProfessionBeanResponse.DataBean, BaseViewHolder> {
    Context mContext;
    public ProfessionListAdapter(@Nullable List<ProfessionBeanResponse.DataBean> data, Context context) {
        super(R.layout.item_job, data);
        this.mContext = context;
    }

    @SuppressLint("Range")
    @Override
    protected void convert(BaseViewHolder helper, ProfessionBeanResponse.DataBean item) {
        ((TextView)helper.getView(R.id.tv_content)).setText(item.getRoleName());
        helper.getView(R.id.ll_layout).setBackgroundResource(item.isSelect()?R.color.colorTxtBlue:R.color.white);
        helper.getView(R.id.ll_layout).getBackground().mutate().setAlpha(20);
        helper.setTextColor(R.id.tv_content,item.isSelect()?mContext.getResources().getColor(R.color.colorTxtBlue):mContext.getResources().getColor(R.color.colorTxtBlack));
    }

}
