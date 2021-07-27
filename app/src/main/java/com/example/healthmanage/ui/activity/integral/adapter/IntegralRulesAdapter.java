package com.example.healthmanage.ui.activity.integral.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.integral.response.IntegralRuleResponse;
import com.example.healthmanage.utils.ToastUtils;
import com.example.healthmanage.view.GridItemDecoration;

import java.util.List;

/**
 * desc:
 * date:2021/7/15 16:36
 * author:bWang
 */
public class IntegralRulesAdapter extends BaseQuickAdapter<IntegralRuleResponse.DataBean, BaseViewHolder> {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private IntegralRuleAdapter mIntegralRuleAdapter;

    public IntegralRulesAdapter(@Nullable List<IntegralRuleResponse.DataBean> data,Context context) {
        super(R.layout.item_points_header,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegralRuleResponse.DataBean item) {
        helper.setText(R.id.tv_points_header_name,item.getName());
        mRecyclerView = helper.getView(R.id.recycler_view);
        mIntegralRuleAdapter = new IntegralRuleAdapter(item.getAppRuleList(),mContext);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.line_divider));
        if (mRecyclerView.getItemDecorationCount()==0){
            mRecyclerView.addItemDecoration(gridItemDecoration);
        }
        mRecyclerView.setAdapter(mIntegralRuleAdapter);

        mIntegralRuleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TextView textView = view.findViewById(R.id.tv_complete_status);
                switch (view.getId()){
                    case R.id.tv_complete_status:
                        if (textView.getText().equals("已完成")){
                            ToastUtils.showShort(mContext,"不可重复完成");
                        }else {
                            ToastUtils.showShort(mContext,"可重复完成");
                        }
                        break;
                }
            }
        });
    }
}
