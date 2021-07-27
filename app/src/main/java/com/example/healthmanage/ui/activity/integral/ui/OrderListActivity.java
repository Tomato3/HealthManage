package com.example.healthmanage.ui.activity.integral.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityIntegralOrderBinding;
import com.example.healthmanage.ui.activity.integral.IntegralViewModel;
import com.example.healthmanage.ui.activity.integral.adapter.IntegralRulesAdapter;
import com.example.healthmanage.ui.activity.integral.adapter.OrderListAdapter;
import com.example.healthmanage.ui.activity.integral.response.OrderListResponse;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * desc:积分订单
 * date:2021/7/20 15:03
 * author:bWang
 */
public class OrderListActivity extends BaseActivity<ActivityIntegralOrderBinding, IntegralViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context mContext;
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private List<OrderListResponse.DataBean> mDataBeans;
    private OrderListAdapter mOrderListAdapter;
    private String status = null;

    @Override
    protected void initData() {
        mContext = OrderListActivity.this;
        mTitleToolBar.setTitle("积分订单");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutPointsOrderTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);

        mDataBeans = new ArrayList<>();
        viewModel.getOrderList(status);
        mOrderListAdapter = new OrderListAdapter(mDataBeans,mContext);
        dataBinding.recyclerViewIntegralOrder.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_notice));
        if (dataBinding.recyclerViewIntegralOrder.getItemDecorationCount()==0){
            dataBinding.recyclerViewIntegralOrder.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewIntegralOrder.setAdapter(mOrderListAdapter);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_all_order:
                        status = null;
                        viewModel.getOrderList(status);
                        break;
                    case R.id.rb_wit_post:
                        status = "1";
                        viewModel.getOrderList(status);
                        break;
                    case R.id.rb_wait_receive:
                        status = "2";
                        viewModel.getOrderList(status);
                        break;
                    case R.id.rb_already_complete:
                        status = "3";
                        viewModel.getOrderList(status);
                        break;
                }
            }
        });
        viewModel.mOrderListMutableLiveData.observe(this, new Observer<List<OrderListResponse.DataBean>>() {
            @Override
            public void onChanged(List<OrderListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewIntegralOrder.setVisibility(View.VISIBLE);
                    dataBinding.tvNoOrderData.setVisibility(View.GONE);
                    if (mDataBeans!=null && mDataBeans.size()>0){
                        mDataBeans.clear();
                    }
                    mDataBeans.addAll(dataBeans);
                    mOrderListAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewIntegralOrder.setVisibility(View.GONE);
                    dataBinding.tvNoOrderData.setVisibility(View.VISIBLE);
                }
            }
        });

        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
                dataBinding.smartRefreshLayout.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
                viewModel.getOrderList(status);
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });
        mOrderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext,OrderInfoActivity.class);
                intent.putExtra("id",mDataBeans.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        mTitleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_integral_order;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
