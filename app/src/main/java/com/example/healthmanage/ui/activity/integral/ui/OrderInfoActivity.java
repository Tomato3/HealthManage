package com.example.healthmanage.ui.activity.integral.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityOrderDetailBinding;
import com.example.healthmanage.ui.activity.integral.IntegralViewModel;
import com.example.healthmanage.ui.activity.integral.response.ExchangeGoodsBean;
import com.example.healthmanage.ui.activity.integral.response.OrderInfoResponse;
import com.example.healthmanage.widget.TitleToolBar;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * desc:订单详情
 * date:2021/7/23 13:44
 * author:bWang
 */
public class OrderInfoActivity extends BaseActivity<ActivityOrderDetailBinding, IntegralViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private Context mContext;
    private int id;

    @Override
    protected void initData() {
        mContext = OrderInfoActivity.this;
        mTitleToolBar.setTitle("订单详情");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutOrderTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);

        id = getIntent().getIntExtra("id",0);
        viewModel.getOrderInfo(id);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.mOrderInfoMutableLiveData.observe(this, new Observer<OrderInfoResponse.DataBean>() {
            @Override
            public void onChanged(OrderInfoResponse.DataBean dataBean) {
                if (dataBean!=null){
                    switch (dataBean.getOrderStatus()){
                        case 1:
                            dataBinding.layoutBottom.setVisibility(View.GONE);
                            dataBinding.tvOrderStatus.setText("等待发货");
                            dataBinding.tvOrderStatus.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mContext,R.drawable.ic_clock_white),null,null,null);
                            dataBinding.tvOrderStatusInfo.setText("订单将在30天内发货，请关注物流信息");
                            dataBinding.layoutLogisticInfo.setVisibility(View.GONE);
                            dataBinding.tvOrderNumber.setVisibility(View.VISIBLE);
                            dataBinding.tvExchangeTime.setVisibility(View.VISIBLE);
                            dataBinding.tvLogisticType.setVisibility(View.VISIBLE);
                            dataBinding.tvSendTime.setVisibility(View.GONE);
                            break;
                        case 2:
                            dataBinding.layoutBottom.setVisibility(View.VISIBLE);
                            dataBinding.tvOrderStatus.setText("等待收货");
                            dataBinding.tvOrderStatus.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(mContext,R.drawable.ic_local_shipping),null,null,null);
                            dataBinding.tvOrderStatusInfo.setText("预计14天23时后自动确认收货");
                            dataBinding.layoutLogisticInfo.setVisibility(View.VISIBLE);
                            dataBinding.tvQueryReceive.setVisibility(View.VISIBLE);
                            dataBinding.tvSeeLogistic.setVisibility(View.VISIBLE);
                            dataBinding.tvOrderNumber.setVisibility(View.VISIBLE);
                            dataBinding.tvExchangeTime.setVisibility(View.VISIBLE);
                            dataBinding.tvLogisticType.setVisibility(View.VISIBLE);
                            dataBinding.tvSendTime.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            dataBinding.layoutBottom.setVisibility(View.VISIBLE);
                            dataBinding.tvOrderStatus.setText("订单已完成");
                            dataBinding.tvOrderStatus.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);
                            dataBinding.tvOrderStatusInfo.setText("愿此次购物能给您带来好的体验");
                            dataBinding.layoutLogisticInfo.setVisibility(View.VISIBLE);
                            dataBinding.tvQueryReceive.setVisibility(View.GONE);
                            dataBinding.tvSeeLogistic.setVisibility(View.VISIBLE);
                            dataBinding.tvOrderNumber.setVisibility(View.VISIBLE);
                            dataBinding.tvExchangeTime.setVisibility(View.VISIBLE);
                            dataBinding.tvLogisticType.setVisibility(View.VISIBLE);
                            dataBinding.tvSendTime.setVisibility(View.VISIBLE);
                            break;
                    }
                    dataBinding.tvNamePhone.setText(dataBean.getName()+"\u3000"+dataBean.getPhone());
                    Glide.with(mContext).load(dataBean.getAppGoods().getUrl()).apply(new RequestOptions().placeholder(R.drawable.ic_shop_pic).error(R.drawable.ic_shop_pic))
                            .into(dataBinding.ivGoodsPic);
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
        return R.layout.activity_order_detail;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
