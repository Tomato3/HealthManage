package com.example.healthmanage.ui.activity.healthreport.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportResponse;
import com.example.healthmanage.utils.ToolUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HealthReportAdapter extends BaseQuickAdapter<HealthReportResponse.DataBean, BaseViewHolder> {
    public HealthReportAdapter(@Nullable List<HealthReportResponse.DataBean> data) {
        super(R.layout.item_health_report,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthReportResponse.DataBean item) {
        helper.setText(R.id.tv_health_report_title,item.getName()+"\u3000("+item.getStartTime()+"~"+item.getEndTime()+")");
        helper.setText(R.id.tv_health_report_time, "创建时间:\u3000"+ToolUtil.timeStampToDate(String.valueOf(item.getCreateTime()),null));
    }
}
