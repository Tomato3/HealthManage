package com.example.healthmanage.ui.activity.integral.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityIntegralShopBinding;
import com.example.healthmanage.ui.activity.integral.IntegralViewModel;
import com.example.healthmanage.ui.activity.integral.adapter.GoodsListAdapter;
import com.example.healthmanage.ui.activity.integral.response.GoodsListResponse;
import com.example.healthmanage.widget.TitleToolBar;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.tools.ScreenUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * desc:积分商城
 * date:2021/7/16 11:25
 * author:bWang
 */
public class IntegralShopAActivity extends BaseActivity<ActivityIntegralShopBinding, IntegralViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context mContext;
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private List<GoodsListResponse.DataBean> mDataBeans;
    private GoodsListAdapter mGoodsListAdapter;
    private String points;

    @Override
    protected void initData() {
        mContext = IntegralShopAActivity.this;
        mTitleToolBar.setTitle("积分商城");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutPointsShopTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);

        mDataBeans = new ArrayList<>();
        viewModel.getGoodsList(points);
        mGoodsListAdapter = new GoodsListAdapter(mDataBeans,mContext);
        dataBinding.recyclerViewIntegralShop.setLayoutManager(new GridLayoutManager(mContext,2, LinearLayoutManager.VERTICAL,false));
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, ScreenUtils.dip2px(mContext,8),false);
        if (dataBinding.recyclerViewIntegralShop.getItemDecorationCount()==0){
            dataBinding.recyclerViewIntegralShop.addItemDecoration(gridSpacingItemDecoration);
        }
        dataBinding.recyclerViewIntegralShop.setAdapter(mGoodsListAdapter);

        mGoodsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext,GoodsDetailsActivity.class);
                intent.putExtra("id",mDataBeans.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_all_goods:
                        points = null;
                        viewModel.getGoodsList(points);
                        break;
                    case R.id.rb_below_5000:
                        points = "0-5000";
                        viewModel.getGoodsList(points);
                        break;
                    case R.id.rb_5000_10000:
                        points = "5000-10000";
                        viewModel.getGoodsList(points);
                        break;
                    case R.id.rb_above_10000:
                        points = "10000";
                        viewModel.getGoodsList(points);
                        break;
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
                viewModel.getGoodsList(points);
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });

        viewModel.mGoodsListMutableLiveData.observe(this, new Observer<List<GoodsListResponse.DataBean>>() {
            @Override
            public void onChanged(List<GoodsListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewIntegralShop.setVisibility(View.VISIBLE);
                    dataBinding.tvNoShopData.setVisibility(View.GONE);
                    if (mDataBeans!=null && mDataBeans.size()>0){
                        mDataBeans.clear();
                    }
                    mDataBeans.addAll(dataBeans);
                    mGoodsListAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewIntegralShop.setVisibility(View.GONE);
                    dataBinding.tvNoShopData.setVisibility(View.VISIBLE);
                }
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
        return R.layout.activity_integral_shop;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
