package com.example.healthmanage.ui.activity.shop.ui;

import android.content.Context;
import android.os.Bundle;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityOrderFormBinding;
import com.example.healthmanage.widget.TitleToolBar;

/**
 * desc:交易订单
 * date:2021/7/9 14:32
 * author:bWang
 */
public class ShopOrderFormActivity extends BaseActivity<ActivityOrderFormBinding,ShopViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context mContext;
    private TitleToolBar mTitleToolBar = new TitleToolBar();

    @Override
    protected void initData() {
        mContext = ShopOrderFormActivity.this;
        mTitleToolBar.setTitle("交易订单");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutOrderFormTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);
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
        return R.layout.activity_order_form;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
