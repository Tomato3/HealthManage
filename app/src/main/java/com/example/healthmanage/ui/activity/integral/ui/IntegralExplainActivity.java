package com.example.healthmanage.ui.activity.integral.ui;

import android.os.Bundle;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityIntegralExplainBinding;
import com.example.healthmanage.ui.activity.integral.IntegralViewModel;
import com.example.healthmanage.widget.TitleToolBar;

/**
 * desc:积分说明
 * date:2021/7/16 16:53
 * author:bWang
 */
public class IntegralExplainActivity extends BaseActivity<ActivityIntegralExplainBinding, IntegralViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar mTitleToolBar = new TitleToolBar();

    @Override
    protected void initData() {
        mTitleToolBar.setTitle("积分说明");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutPointsShopTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
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
        return R.layout.activity_integral_explain;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
