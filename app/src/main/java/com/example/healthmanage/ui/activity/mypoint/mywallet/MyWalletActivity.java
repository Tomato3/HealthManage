package com.example.healthmanage.ui.activity.mypoint.mywallet;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityMyWalletBinding;
import com.example.healthmanage.bean.recyclerview.PurseFlowingRecyclerView;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.List;

public class MyWalletActivity extends BaseActivity<ActivityMyWalletBinding, MyWalletViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {

    TitleToolBar titleToolBar = new TitleToolBar();
    BaseAdapter purseFlowingAdapter;

    @Override
    protected void initData() {
        titleToolBar.setTitle("我的钱包");
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.getPurseFlowing(0);
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        purseFlowingAdapter = new BaseAdapter(this, null, R.layout.recycler_view_item_purse_flowing
                , BR.PurseFlowingRecyclerView);
        dataBinding.recyclerViewPurseFlowing.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewPurseFlowing.setAdapter(purseFlowingAdapter);
        viewModel.purseFlowingMutableLiveData.observe(this, new Observer<List<PurseFlowingRecyclerView>>() {
            @Override
            public void onChanged(List<PurseFlowingRecyclerView> purseFlowingRecyclerViews) {
                purseFlowingAdapter.setRecyclerViewList(purseFlowingRecyclerViews);
                purseFlowingAdapter.notifyDataSetChanged();
            }
        });

        dataBinding.rbAll.setOnClickListener(this::onClick);
        dataBinding.rbIncome.setOnClickListener(this::onClick);
        dataBinding.rbExpenditure.setOnClickListener(this::onClick);
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_all:
                viewModel.getPurseFlowing(0);
                break;
            case R.id.rb_income:
                viewModel.getPurseFlowing(1);
                break;
            case R.id.rb_expenditure:
                viewModel.getPurseFlowing(2);
                break;
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_my_wallet;
    }

}