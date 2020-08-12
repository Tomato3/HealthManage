package com.example.healthmanage.ui.activity.mypoint;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityMyPointBinding;
import com.example.healthmanage.ui.activity.getpoint.GetPointActivity;
import com.example.healthmanage.ui.activity.mywallet.MyWalletActivity;
import com.example.healthmanage.view.ExchangeCommodityRecyclerView;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.List;

public class MyPointActivity extends BaseActivity<ActivityMyPointBinding, MyPointViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {

    TitleToolBar titleToolBar = new TitleToolBar();
    BaseAdapter exchangeCommodityAdapter;

    @Override
    protected void initData() {
        titleToolBar.setTitle("我的积分");
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.getExchangeCommodity();
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

        exchangeCommodityAdapter = new BaseAdapter(this, null,
                R.layout.recycler_view_item_exchange_commodity, BR.ExchangeCommodityRecyclerView);
        dataBinding.recyclerViewExchangeCommodity.setLayoutManager(new GridLayoutManager(this, 2));
        dataBinding.recyclerViewExchangeCommodity.setAdapter(exchangeCommodityAdapter);
        viewModel.exchangeCommodityMutableLiveData.observe(this, new Observer<List<ExchangeCommodityRecyclerView>>() {
            @Override
            public void onChanged(List<ExchangeCommodityRecyclerView> exchangeCommodityRecyclerViews) {
                exchangeCommodityAdapter.setRecyclerViewList(exchangeCommodityRecyclerViews);
                exchangeCommodityAdapter.notifyDataSetChanged();
            }
        });

        dataBinding.tvWallet.setOnClickListener(this::onClick);
        dataBinding.tvGrowth.setOnClickListener(this::onClick);
        dataBinding.tvGetPoint.setOnClickListener(this::onClick);


    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
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
            case R.id.tv_wallet:
                startActivity(MyWalletActivity.class);
                break;
            case R.id.tv_growth:
                break;
            case R.id.tv_get_point:
                startActivity(GetPointActivity.class);
                break;
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_my_point;
    }
}
