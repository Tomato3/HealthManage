package com.example.healthmanage.ui.activity.healthrecord.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.healthrecord.response.CheckReportResponse;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.tools.ScreenUtils;

import java.util.List;

public class CheckReportAdapter extends BaseQuickAdapter<CheckReportResponse.DataBean, BaseViewHolder> {
    Context mContext;
    private GridPicAdapter gridPicAdapter;
    private RecyclerView gridView;
    private CheckBox checkBox;

    public CheckReportAdapter(Context context, @Nullable List<CheckReportResponse.DataBean> data) {
        super(R.layout.item_check_report,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckReportResponse.DataBean item) {
        helper.setText(R.id.check_report_title,item.getReportName());
        helper.setText(R.id.check_report_time,"检查时间:"+item.getCheckTime());
        helper.setText(R.id.check_report_organ,"检查机构:"+item.getCheckOrganization());
        checkBox = helper.getView(R.id.iv_selector_report);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    helper.getView(R.id.layout_check_report_content).setVisibility(View.GONE);
                }else {
                    helper.getView(R.id.layout_check_report_content).setVisibility(View.VISIBLE);
                }
            }
        });
        gridView = helper.getView(R.id.grid_recyclerview_report);
        gridView.setLayoutManager(new GridLayoutManager(mContext,3, LinearLayoutManager.VERTICAL,false));
        gridPicAdapter = new GridPicAdapter(mContext,item.getAppUserReportList());
        gridView.setAdapter(gridPicAdapter);
        gridView.addItemDecoration(new GridSpacingItemDecoration(3, ScreenUtils.dip2px(mContext,8),false));
        gridPicAdapter.notifyDataSetChanged();
    }
}
