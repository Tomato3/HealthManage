package com.example.healthmanage.ui.activity.famousDoctorHall.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.DepartMentResponse;

import java.util.List;

/**
 * desc:
 * date:2021/6/28 16:10
 * author:bWang
 */
public class LeftDepartmentAdapter extends BaseQuickAdapter<DepartMentResponse.DataBean, BaseViewHolder> {
    private Context mContext;
    public LeftDepartmentAdapter(@Nullable List<DepartMentResponse.DataBean> data, Context context) {
        super(R.layout.item_select_office,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DepartMentResponse.DataBean item) {
        helper.getView(R.id.item_recycle_all).setBackgroundResource(item.isSelect()?R.color.color_line_grey:R.color.white);
        helper.setText(R.id.tv_office,item.getName())
                .setTextColor(R.id.tv_office,item.isSelect()?mContext.getResources().getColor(R.color.colorTxtBlue):mContext.getResources().getColor(R.color.colorTxtBlack));
    }
}
