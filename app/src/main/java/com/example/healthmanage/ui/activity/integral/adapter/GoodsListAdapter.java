package com.example.healthmanage.ui.activity.integral.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.integral.response.GoodsListResponse;
import com.example.healthmanage.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * desc:
 * date:2021/7/16 11:13
 * author:bWang
 */
public class GoodsListAdapter extends BaseQuickAdapter<GoodsListResponse.DataBean, BaseViewHolder> {
    private Context mContext;

    public GoodsListAdapter(@Nullable List<GoodsListResponse.DataBean> data, Context context) {
        super(R.layout.item_integraal_shop,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsListResponse.DataBean item) {
        Glide.with(mContext).load(item.getUrl()).apply(new RequestOptions().placeholder(R.drawable.ic_shop_pic).error(R.drawable.ic_shop_pic))
                .into((ImageView) helper.getView(R.id.iv_shop_pic));
        helper.setText(R.id.tv_shop_goods_name,item.getName());
        helper.setText(R.id.tv_integral,item.getPoint()+"积分");
        helper.setText(R.id.tv_goods_price,"价值:￥"+item.getMarketPrice());
        helper.setText(R.id.tv_exchange_number,"已兑:"+item.getExchangeQuantity());
    }
}
