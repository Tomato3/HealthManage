package com.example.healthmanage.ui.activity.mytask.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.bean.network.response.TaskResponse;
import com.example.healthmanage.ui.activity.temperature.response.HealthTaskResponse;

import java.util.List;

public class MyTaskAdapter extends BaseQuickAdapter<HealthTaskResponse.DataBean, BaseViewHolder> {
    String sex;
    int sexSign;
    Context mContext;

    public MyTaskAdapter(@Nullable List<HealthTaskResponse.DataBean> data , Context context) {
        super(R.layout.item_health_task,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthTaskResponse.DataBean item) {
        Glide.with(mContext).load(item.getAppUser().getAvatar()).apply(new RequestOptions().placeholder(R.drawable.ic_load_error).error(R.drawable.ic_load_error).circleCrop())
                .into((ImageView) helper.getView(R.id.iv_avatar));
        helper.setText(R.id.tv_name,item.getAppUser().getNickName());

        /**0:保密
         * 1:男
         * 2:女
         * **/
        sexSign = item.getAppUser().getSex();
        if (sexSign==0){
            sex = "保密";
        }else if (sexSign == 1){
            sex = "男";
        }else{
            sex = "女";
        }
        switch (item.getAppUser().getRank()){
            case 0:
                helper.setText(R.id.tv_vip_content,"普通会员"+"\u3000｜\u3000"+sex);
                break;
            case 1:
                helper.setText(R.id.tv_vip_content,"高级会员"+"\u3000｜\u3000"+sex);
                break;
            case 2:
                helper.setText(R.id.tv_vip_content,"贵宾会员"+"\u3000｜\u3000"+sex);
                break;
            case 3:
                helper.setText(R.id.tv_vip_content,"至尊会员"+"\u3000｜\u3000"+sex);
                break;
        }
        helper.setText(R.id.tv_task_content, Html.fromHtml("异常描述:<font color=\"#000000\">\t"+item.getContent()+"</font>"));
        if (item.getStatus()==0){
            if (item.getAppDoctorInfo()!=null){
                helper.setText(R.id.tv_create_task_time,"转交人:"+item.getAppUser().getNickName()+"\u3000"+item.getAppUser().getRank());
            }else {
                helper.setText(R.id.tv_create_task_time,"创建时间:"+item.getCreateTime());
            }
            helper.getView(R.id.layout_btn_group).setVisibility(View.VISIBLE);
            helper.getView(R.id.btn_transmit_deal).setVisibility(View.VISIBLE);
            helper.getView(R.id.btn_deal_task).setVisibility(View.VISIBLE);
            helper.getView(R.id.btn_see_deal).setVisibility(View.GONE);
            helper.getView(R.id.tv_transmit_object).setVisibility(View.GONE);
            helper.getView(R.id.tv_deal_time).setVisibility(View.GONE);
        }
        else if (item.getStatus()==1){
            helper.setText(R.id.tv_create_task_time,"创建时间:"+item.getCreateTime());
            helper.getView(R.id.layout_btn_group).setVisibility(View.VISIBLE);
            helper.getView(R.id.btn_transmit_deal).setVisibility(View.GONE);
            helper.getView(R.id.btn_deal_task).setVisibility(View.GONE);
            helper.getView(R.id.btn_see_deal).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_transmit_object).setVisibility(View.GONE);
            helper.getView(R.id.tv_deal_time).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_deal_time,"处理时间:"+item.getProcessTime());
        }
        if (item.getTransferStatus()==1){
            helper.setText(R.id.tv_create_task_time,"创建时间:"+item.getCreateTime());
            helper.getView(R.id.layout_btn_group).setVisibility(View.GONE);
            helper.getView(R.id.tv_transmit_object).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_transmit_object,"转交对象:"+item.getAppDoctorInfo().getName()+"\u3000"+item.getAppDoctorInfo().getRank());
            helper.getView(R.id.tv_deal_time).setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.btn_deal_task);
        helper.addOnClickListener(R.id.btn_see_deal);
        helper.addOnClickListener(R.id.btn_transmit_deal);
    }
}
