package com.example.healthmanage.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.qualification.response.DepartmentResponse;

import java.util.List;

public class SelectOfficeAdapter extends BaseQuickAdapter<DepartmentResponse.DataBean, BaseViewHolder> {
    Context mContext;
    public SelectOfficeAdapter(@Nullable List<DepartmentResponse.DataBean> data,Context context) {
        super(R.layout.item_choose_office_left, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DepartmentResponse.DataBean item) {
        helper.getView(R.id.item_recycle_all).setBackgroundResource(item.isSelect()?R.color.white:R.color.color_line_grey);
        helper.setText(R.id.tv_office,item.getName())
                .setTextColor(R.id.tv_office,item.isSelect()?mContext.getResources().getColor(R.color.colorTxtBlue):mContext.getResources().getColor(R.color.colorTxtBlack));
        helper.getView(R.id.line_blue).setVisibility(item.isSelect()?View.VISIBLE:View.GONE);
    }
}
