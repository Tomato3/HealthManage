package com.example.healthmanage.ui.activity.integral.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityMyIntegralBinding;
import com.example.healthmanage.ui.activity.integral.IntegralViewModel;
import com.example.healthmanage.ui.activity.integral.adapter.IntegralRulesAdapter;
import com.example.healthmanage.ui.activity.integral.response.IntegralResponse;
import com.example.healthmanage.ui.activity.integral.response.IntegralRuleResponse;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * desc:我的积分
 * date:2021/7/12 9:29
 * author:bWang
 */
public class IntegralActivity extends BaseActivity<ActivityMyIntegralBinding, IntegralViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private Context mContext;
    private List<IntegralRuleResponse.DataBean> mRulesDataBeans;
    private IntegralRulesAdapter mIntegralRulesAdapter;
    private int mPosition;
    private String points;

    @Override
    protected void initData() {
        mContext = IntegralActivity.this;
        mTitleToolBar.setTitle("我的积分");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutPointsTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);

        mRulesDataBeans = new ArrayList<>();
        viewModel.getIntegral();
        viewModel.getIntegralRule();
        mIntegralRulesAdapter = new IntegralRulesAdapter(mRulesDataBeans,mContext);
        dataBinding.recyclerViewMyIntegralRules.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_notice));
        if (dataBinding.recyclerViewMyIntegralRules.getItemDecorationCount()==0){
            dataBinding.recyclerViewMyIntegralRules.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewMyIntegralRules.setAdapter(mIntegralRulesAdapter);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.mRuleListMutableLiveData.observe(this, new Observer<List<IntegralRuleResponse.DataBean>>() {
            @Override
            public void onChanged(List<IntegralRuleResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    if (mRulesDataBeans!=null && mRulesDataBeans.size()>0){
                        mRulesDataBeans.clear();
                    }
                    mRulesDataBeans.addAll(dataBeans);
                    mIntegralRulesAdapter.notifyDataSetChanged();
                }
            }
        });
        viewModel.mIntegralResponseMutableLiveData.observe(this, new Observer<IntegralResponse>() {
            @Override
            public void onChanged(IntegralResponse integralResponse) {
                if (integralResponse!=null){
                    if (integralResponse.getData()!=null){
                        if (integralResponse.getData().getPoint()!=0){
                            points = String.valueOf(integralResponse.getData().getPoint());
                            BaseApplication.setIntegrals(points);
                            dataBinding.tvPointsAll.setText(String.valueOf(integralResponse.getData().getPoint()));
                        }else {
                            dataBinding.tvPointsAll.setText(String.valueOf(0));
                        }

                    }
                }
            }
        });
        dataBinding.tvPointsShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IntegralShopAActivity.class);
            }
        });
        dataBinding.ivPointsExplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IntegralExplainActivity.class);
            }
        });
        dataBinding.tvPointsDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,IntegralDetailActivity.class);
                intent.putExtra("points",points);
                startActivity(intent);
            }
        });
        dataBinding.tvPointsOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(OrderListActivity.class);
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
        return R.layout.activity_my_integral;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
