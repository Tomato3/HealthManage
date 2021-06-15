package com.example.healthmanage.ui.activity.workplan.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.workplan.response.WorkPlanListResponse;
import com.example.healthmanage.utils.ToolUtil;

import java.util.List;

public class WorkPlanAdapter extends BaseSectionQuickAdapter<WorkPlanSection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public WorkPlanAdapter(int layoutResId, int sectionHeadResId, List<WorkPlanSection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, WorkPlanSection item) {
        helper.setText(R.id.work_plan_head,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkPlanSection item) {
        WorkPlanListResponse.DataBean dataBean = item.t;

        if (dataBean.getWorkText().contains("<img")){
            if (dataBean.getWorkText().substring(0,dataBean.getWorkText().indexOf("<img")).replaceAll("&nbsp;","").isEmpty()){
                helper.setText(R.id.tv_work_plan_content,"(图片)");
            }else {
                helper.setText(R.id.tv_work_plan_content,dataBean.getWorkText().substring(0,dataBean.getWorkText().indexOf("<img")));
            }
        }else {
            helper.setText(R.id.tv_work_plan_content,dataBean.getWorkText());
        }


        if (dataBean.getStatus().equals("已完成")){
            helper.getView(R.id.btn_confirm_plan).setVisibility(View.GONE);
            if (String.valueOf(dataBean.getUpdateTime())!=null){
                helper.setText(R.id.tv_work_plan_time, ToolUtil.timeStamp2Date(String.valueOf(dataBean.getUpdateTime()),null));
            }
        }else {
            helper.getView(R.id.btn_confirm_plan).setVisibility(View.VISIBLE);
            if (String.valueOf(dataBean.getCreateTime())!=null){
                helper.setText(R.id.tv_work_plan_time,ToolUtil.timeStamp2Date(String.valueOf(dataBean.getCreateTime()),null));
            }
            helper.addOnClickListener(R.id.btn_confirm_plan);
        }
        helper.addOnClickListener(R.id.layout_plan_detail);
    }
}
