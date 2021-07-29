package com.example.healthmanage.ui.activity.integral.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.integral.response.OrderListResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 * date:2021/7/20 14:44
 * author:bWang
 */
public class OrderListAdapter extends BaseQuickAdapter<OrderListResponse.DataBean, BaseViewHolder> {
    private Context mContext;

    public OrderListAdapter(@Nullable List<OrderListResponse.DataBean> data,Context context) {
        super(R.layout.item_order_list,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListResponse.DataBean item) {
        helper.setText(R.id.tv_order_number,"订单编号:"+item.getOrderNumber());
        Glide.with(mContext).load(item.getAppGoods().getUrl()).apply(new RequestOptions().placeholder(R.drawable.ic_shop_pic).error(R.drawable.ic_shop_pic))
                .into((ImageView) helper.getView(R.id.iv_order_goods_pic));

        switch (item.getOrderStatus()){
            case 1:
                helper.setText(R.id.tv_order_status,"待发货");
                helper.setTextColor(R.id.tv_order_status, ContextCompat.getColor(mContext,R.color.txtPinkColor));
                helper.getView(R.id.tv_order_status).setBackgroundResource(R.drawable.bg_shape_circle_pink);
                helper.getView(R.id.layout_order_status).setVisibility(View.GONE);
                break;
            case 2:
                helper.setText(R.id.tv_order_status,"待收货");
                helper.setTextColor(R.id.tv_order_status, ContextCompat.getColor(mContext,R.color.txtPinkColor));
                helper.getView(R.id.tv_order_status).setBackgroundResource(R.drawable.bg_shape_circle_pink);
                helper.getView(R.id.layout_order_status).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_query_receive).setVisibility(View.VISIBLE);
                break;
            case 3:
                helper.setText(R.id.tv_order_status,"已完成");
                helper.setTextColor(R.id.tv_order_status, ContextCompat.getColor(mContext,R.color.txtGreenColor));
                helper.getView(R.id.tv_order_status).setBackgroundResource(R.drawable.bg_shape_circle_green);
                helper.getView(R.id.layout_order_status).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_query_receive).setVisibility(View.GONE);
                break;
        }
        helper.setText(R.id.tv_goods_name,item.getName());
        helper.setText(R.id.tv_goods_integral,item.getPoint()+"积分");
        helper.setText(R.id.tv_integral_number, Html.fromHtml("<font color=\"#666666\">"+"共"+item.getExchangeQuantity()+"件商品，消耗："+"</font>"+"<font color=\"#FF3A30\">"+item.getPoint()+"积分"+"</font>"));
        helper.addOnClickListener(R.id.tv_see_logistics);
    }
}
