package com.example.healthmanage.ui.activity.shop.ui;

import android.content.Context;
import android.os.Bundle;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityMyShopBinding;
import com.example.healthmanage.widget.TitleToolBar;

/**
 * desc:我的小店
 * date:2021/6/25 15:25
 * author:bWang
 */
public class ShopActivity extends BaseActivity<ActivityMyShopBinding,ShopViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context mContext;
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    @Override
    protected void initData() {
        mContext = ShopActivity.this;
        mTitleToolBar.setTitle("我的小店");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutShopTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
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
        return R.layout.activity_my_shop;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
