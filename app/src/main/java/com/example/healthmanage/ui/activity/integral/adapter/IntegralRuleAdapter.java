package com.example.healthmanage.ui.activity.integral.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.integral.response.IntegralRuleResponse;

import java.util.List;

/**
 * desc:积分子布局
 * date:2021/7/15 15:56
 * author:bWang
 */
public class IntegralRuleAdapter extends BaseQuickAdapter<IntegralRuleResponse.DataBean.AppRuleListBean, BaseViewHolder> {
    private Context mContext;

    public IntegralRuleAdapter(@Nullable List<IntegralRuleResponse.DataBean.AppRuleListBean> data,Context context) {
        super(R.layout.item_points_content,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegralRuleResponse.DataBean.AppRuleListBean item) {
        helper.addOnClickListener(R.id.tv_complete_status);
        helper.setText(R.id.tv_content_name,item.getName());
        if (TextUtils.isEmpty(item.getTimeLimit())){
            if (item.getName().contains("完成资格认证")){
                helper.setText(R.id.tv_content_points,item.getPoints()+"分/"+"审核通过app资格认证");
            }else if (item.getName().contains("注册使用")){
                helper.setText(R.id.tv_content_points,item.getPoints()+"分/"+"注册登录使用app");
            }else if (item.getName().contains("登录")){
                helper.setText(R.id.tv_content_points,item.getPoints()+"分/"+"每日首次登录");
            }else {
                helper.setText(R.id.tv_content_points,item.getPoints()+"分/"+"每日"+item.getDailyLimit()+"次");
            }
        }else {
            if (item.getName().contains("视频")){
                helper.setText(R.id.tv_content_points,item.getPoints()+"分/"+"有效观看视频"+item.getTimeLimit()+"秒以上");
            }else {
                helper.setText(R.id.tv_content_points,item.getPoints()+"分/"+"有效阅读文章"+item.getTimeLimit()+"秒以上");
            }
        }
        helper.setText(R.id.tv_complete_status,item.getDailyLimit() - item.getCompleteNumber() == 0 ? "已完成":"去完成");
        helper.setTextColor(R.id.tv_complete_status,item.getDailyLimit() - item.getCompleteNumber() == 0 ? ContextCompat.getColor(mContext,R.color.textGreyColor):ContextCompat.getColor(mContext,R.color.white));
        helper.getView(R.id.tv_complete_status).setBackgroundResource(item.getDailyLimit() - item.getCompleteNumber() == 0 ? R.color.recyclerView_background : R.color.colorBlue);
        helper.getView(R.id.tv_complete_number).setVisibility(item.getDailyLimit() - item.getCompleteNumber() == 0 ? View.GONE : View.VISIBLE);
        helper.setText(R.id.tv_complete_number,"每日"+item.getCompleteNumber()+"/"+item.getDailyLimit());
    }
}
