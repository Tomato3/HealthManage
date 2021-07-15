package com.example.healthmanage.ui.activity.team.adapter;

import android.content.Context;
import android.text.Html;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.team.response.BusinessDealListResponse;

import java.util.List;

public class BusinessDealAdapter extends BaseQuickAdapter<BusinessDealListResponse.DataBean, BaseViewHolder> {
    private Context context;
    public BusinessDealAdapter(Context context,@Nullable List<BusinessDealListResponse.DataBean> data) {
        super(R.layout.item_business_deal,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BusinessDealListResponse.DataBean item) {
        switch (item.getStatus()){
            case 0:
                helper.setText(R.id.tv_deal_result,"去完成");
                if (item.getAssignStatus()==0){
                    helper.setText(R.id.tv_deal_result_type,"团队指派-待完成");
                }else {
                    helper.setText(R.id.tv_deal_result_type,"平台指派-待完成");
                }
                break;
            case 1:
                helper.setText(R.id.tv_deal_result,"查看详情");
                if (item.getAssignStatus()==0){
                    helper.setText(R.id.tv_deal_result_type,"团队指派-未完成");
                }else {
                    helper.setText(R.id.tv_deal_result_type,"平台指派-未完成");
                }
                break;
            case 2:
                helper.setText(R.id.tv_deal_result,"查看详情");
                if (item.getAssignStatus()==0){
                    helper.setText(R.id.tv_deal_result_type,"团队指派-已完成");
                }else {
                    helper.setText(R.id.tv_deal_result_type,"平台指派-已完成");
                }
                break;
        }
        if (item.getAppUser()!=null){
            switch (item.getAppUser().getRank()){
                case 0:
                    helper.setText(R.id.tv_service_object_name, Html.fromHtml("服务对象:\u3000<font color=\"#000000\">"+item.getAppUser().getNickName()+"\u3000|\u3000"+"普通会员"+"</font>"));
                    break;
                case 1:
                    helper.setText(R.id.tv_service_object_name, Html.fromHtml("服务对象:\u3000<font color=\"#000000\">"+item.getAppUser().getNickName()+"\u3000|\u3000"+"高级会员"+"</font>"));
                    break;
                case 2:
                    helper.setText(R.id.tv_service_object_name, Html.fromHtml("服务对象:\u3000<font color=\"#000000\">"+item.getAppUser().getNickName()+"\u3000|\u3000"+"贵宾会员"+"</font>"));
                    break;
                case 3:
                    helper.setText(R.id.tv_service_object_name, Html.fromHtml("服务对象:\u3000<font color=\"#000000\">"+item.getAppUser().getNickName()+"\u3000|\u3000"+"至尊会员"+"</font>"));
                    break;
            }
        }
        helper.setText(R.id.tv_task_description, Html.fromHtml("任务描述:\u3000<font color=\"#000000\">"+item.getContent()+"</font>"));
        helper.setText(R.id.tv_task_time, Html.fromHtml("任务时间:\u3000<font color=\"#000000\">"+item.getDate()+"\u3000"+item.getTime()+"</font>"));
        helper.setText(R.id.tv_delegate_time,"委派时间:\u3000"+item.getCreateTime());
        helper.setText(R.id.tv_delegate_person,"委派人:\u3000"+item.getAppDoctorInfo().getName()+"\u3000"+item.getAppDoctorInfo().getRank());
        helper.addOnClickListener(R.id.tv_deal_result);
    }
}
