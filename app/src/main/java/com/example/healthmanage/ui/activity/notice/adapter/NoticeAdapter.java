package com.example.healthmanage.ui.activity.notice.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.ui.activity.notice.response.NoticeResponse;

import java.util.List;

public class NoticeAdapter extends BaseSectionQuickAdapter<NoticeSection, BaseViewHolder> {
    private Context context;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public NoticeAdapter(Context context,int layoutResId, int sectionHeadResId, List<NoticeSection> data) {
        super(layoutResId, sectionHeadResId, data);
        this.context = context;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, NoticeSection item) {
        helper.setText(R.id.tv_head_create_time,item.header);
        helper.addOnClickListener(R.id.tv_head_create_time);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeSection item) {
        NoticeResponse.DataBean.ListBean dataBean = item.t;
        helper.setText(R.id.tv_create_time,dataBean.getCreateTime());
        if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==9){
            helper.getView(R.id.layout_status).setVisibility(View.GONE);
            helper.getView(R.id.tv_already_pass_or_refuse).setVisibility(View.GONE);
            if (dataBean.getStatus() == 1) {
                helper.setText(R.id.tv_notice,"成功邀请\u3000"+dataBean.getAppDoctorInfo().getName()+"\u3000"+dataBean.getAppDoctorInfo().getRank());
                helper.setText(R.id.tv_content,"您已成功邀请"+dataBean.getAppDoctorInfo().getName()+dataBean.getAppDoctorInfo().getAppSystemUser().getPhone()+dataBean.getAppDoctorInfo().getRank()+"加入团队");
            }else if (dataBean.getStatus()==2){
                helper.setText(R.id.tv_notice,dataBean.getAppDoctorInfo().getName()+"\u3000"+dataBean.getAppDoctorInfo().getRank()+"拒绝了您的邀请");
                helper.setText(R.id.tv_content,dataBean.getAppDoctorInfo().getName()+"\u3000"+dataBean.getAppDoctorInfo().getRank()+"拒绝了您的邀请");
            }
        }else {
            if (dataBean.getApplySystemUserId()==BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()){
                helper.getView(R.id.layout_status).setVisibility(View.GONE);
                helper.getView(R.id.tv_already_pass_or_refuse).setVisibility(View.GONE);
                TextView tvNotice = helper.getView(R.id.tv_notice);
                if (dataBean.getStatus()==1){
                    tvNotice.setText("健康管理师签约成功");
                    helper.setText(R.id.tv_content,"您已成功签约\u3000"+dataBean.getAppDoctorInfo().getName()+"\u3000健康管理师");
                }else if (dataBean.getStatus()==2){
                    tvNotice.setText("健康管理师签约失败");
                    helper.setText(R.id.tv_content,dataBean.getAppDoctorInfo().getName()+"健康管理师拒绝了您的签约邀请");
                }
            }else {
                TextView tvNotice = helper.getView(R.id.tv_notice);
                helper.setText(R.id.tv_content,dataBean.getAppDoctorInfo().getName()+"健康管理师正在邀请您成为他的团队成员");
                if (dataBean.getStatus()==0){
                    helper.getView(R.id.layout_status).setVisibility(View.VISIBLE);
                    helper.getView(R.id.tv_already_pass_or_refuse).setVisibility(View.GONE);
                    helper.addOnClickListener(R.id.tv_refuse);
                    helper.addOnClickListener(R.id.tv_pass);
                    tvNotice.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getResources().getDrawable(R.drawable.icon_red_circle),null,null,null);
                    tvNotice.setText("健康管理师签约通知");
                }else if (dataBean.getStatus()==1){
                    helper.getView(R.id.layout_status).setVisibility(View.GONE);
                    helper.getView(R.id.tv_already_pass_or_refuse).setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_already_pass_or_refuse,"已通过");
                    tvNotice.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);
                    tvNotice.setText("健康管理师签约通知");
                }else {
                    helper.getView(R.id.layout_status).setVisibility(View.GONE);
                    helper.getView(R.id.tv_already_pass_or_refuse).setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_already_pass_or_refuse,"已拒绝");
                    tvNotice.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);
                    tvNotice.setText("健康管理师签约通知");
                }
            }
        }

    }
}
