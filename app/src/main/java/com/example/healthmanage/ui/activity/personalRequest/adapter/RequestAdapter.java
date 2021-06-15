package com.example.healthmanage.ui.activity.personalRequest.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.personalRequest.response.RequestResponse;

import java.util.List;

public class RequestAdapter extends BaseQuickAdapter<RequestResponse.DataBean, BaseViewHolder> {
    public RequestAdapter(@Nullable List<RequestResponse.DataBean> data) {
        super(R.layout.item_personal_request,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RequestResponse.DataBean item) {
        helper.addOnClickListener(R.id.tv_cancel_request);
        helper.setText(R.id.tv_request_type,"请求类型:"+item.getType());
        helper.setText(R.id.tv_create_request_time,"创建时间:"+item.getCreateTime());
        helper.setText(R.id.tv_request_describe_content,item.getContent());
        if (item.getStatus().equals("0")){
            helper.setText(R.id.tv_cancel_request,"取消请求");
        }else {
            helper.setText(R.id.tv_cancel_request,"查看回复");
        }
    }
}
