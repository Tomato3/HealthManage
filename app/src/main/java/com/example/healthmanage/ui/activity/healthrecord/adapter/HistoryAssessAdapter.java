package com.example.healthmanage.ui.activity.healthrecord.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.healthrecord.response.HistoryAssessListResponse;
import com.example.healthmanage.utils.ToolUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoryAssessAdapter extends BaseQuickAdapter<HistoryAssessListResponse.DataBean, BaseViewHolder> {
    public HistoryAssessAdapter(@Nullable List<HistoryAssessListResponse.DataBean> data) {
        super(R.layout.item_history_assess,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryAssessListResponse.DataBean item) {
        helper.setText(R.id.assess_name,item.getName());
//        //时间戳转时间
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        long time = item.getCreateTime();
//        Date date = new Date(time);
        helper.setText(R.id.assess_time,"检查时间:"+ item.getCreateTime());
        helper.setText(R.id.assess_content,item.getTestResult());
    }
}
