package com.example.healthmanage.ui.activity.academicJournals.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.academicJournals.response.PeriodicalListResponse;

import java.util.List;

public class PeriodicalAdapter extends BaseQuickAdapter<PeriodicalListResponse.DataBean, BaseViewHolder> {
    private Context context;
    public PeriodicalAdapter(Context context,@Nullable List<PeriodicalListResponse.DataBean> data) {
        super(R.layout.item_academic_journals,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PeriodicalListResponse.DataBean item) {
        switch (item.getStatus()){
            case 0:
                helper.getView(R.id.layout_contribution_header).setVisibility(View.GONE);
                helper.getView(R.id.layout_contribution).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_go_contribution).setVisibility(View.VISIBLE);
                helper.getView(R.id.layout_see_contribution).setVisibility(View.GONE);
                break;
            case 1:
                helper.getView(R.id.layout_contribution_header).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_contribution_time).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_watch_detail).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_examine_time).setVisibility(View.GONE);
                helper.getView(R.id.layout_contribution).setVisibility(View.GONE);
                break;
            case 2:
                helper.getView(R.id.layout_contribution_header).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_contribution_time).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_watch_detail).setVisibility(View.GONE);
                helper.getView(R.id.tv_examine_time).setVisibility(View.VISIBLE);
                helper.getView(R.id.layout_contribution).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_go_contribution).setVisibility(View.GONE);
                helper.getView(R.id.layout_see_contribution).setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_pass_or_rejection_explain,"????????????");
                break;
            case 3:
                helper.getView(R.id.layout_contribution_header).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_contribution_time).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_watch_detail).setVisibility(View.GONE);
                helper.getView(R.id.tv_examine_time).setVisibility(View.VISIBLE);
                helper.getView(R.id.layout_contribution).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_go_contribution).setVisibility(View.GONE);
                helper.getView(R.id.layout_see_contribution).setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_pass_or_rejection_explain,"????????????");
                break;
        }
        helper.setText(R.id.tv_contribution_title, Html.fromHtml("????????????:<font color=\"#000000\">\t"+item.getTitle()+"</font>"));
        helper.setText(R.id.tv_contribution_journals, Html.fromHtml("????????????:<font color=\"#000000\">\t"+item.getPeriodical()+"</font>"));
        helper.setText(R.id.tv_contribution_column, Html.fromHtml("????????????:<font color=\"#000000\">\t"+item.getDeliveryColumn()+"</font>"));
        helper.setText(R.id.tv_contribution_time, "????????????:"+item.getCreateTime());
        helper.setText(R.id.tv_examine_time, "????????????:"+item.getAuditTime());
        helper.addOnClickListener(R.id.tv_go_contribution);
        helper.addOnClickListener(R.id.tv_watch_detail);
        helper.addOnClickListener(R.id.tv_see_contribution_detail);
        helper.addOnClickListener(R.id.tv_pass_or_rejection_explain);
    }
}
