package com.example.healthmanage.ui.activity.integral.ui;


import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityLogisticDetailBinding;
import com.example.healthmanage.ui.activity.integral.IntegralViewModel;
import com.example.healthmanage.ui.activity.integral.adapter.LogisticAdapter;
import com.example.healthmanage.ui.activity.integral.response.LogisticResponse;
import com.example.healthmanage.widget.TitleToolBar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * desc:物流信息
 * date:2021/7/22 15:28
 * author:bWang
 */
public class LogisticDetailActivity extends BaseActivity<ActivityLogisticDetailBinding, IntegralViewModel> implements TitleToolBar.OnTitleIconClickCallBack {

    private Context mContext;
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private List<LogisticResponse.DataBean.TracesBean> mTracesBeans;
    private LogisticAdapter mLogisticAdapter;
    //快递单号
    private String courierNumber;
    //快递公司代码
    private String courierCompanyAbbr;

    @Override
    protected void initData() {
        mContext = LogisticDetailActivity.this;
        mTitleToolBar.setTitle("物流详情");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutLogisticTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);

        courierNumber = getIntent().getStringExtra("courierNumber");
        courierCompanyAbbr = getIntent().getStringExtra("courierCompanyAbbr");
        viewModel.getLogistics(courierNumber,courierCompanyAbbr);
        mTracesBeans = new ArrayList<>();
        mLogisticAdapter = new LogisticAdapter(mTracesBeans);
        dataBinding.recyclerViewLogistic.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewLogistic.setAdapter(mLogisticAdapter);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.mLogisticsMutableLiveData.observe(this, new Observer<LogisticResponse.DataBean>() {
            @Override
            public void onChanged(LogisticResponse.DataBean dataBean) {
                if (dataBean!=null){
                    if (dataBean.getTraces()!=null && dataBean.getTraces().size()>0){
                        dataBinding.recyclerViewLogistic.setVisibility(View.VISIBLE);
                        dataBinding.tvNoLogisticData.setVisibility(View.GONE);
                        if (mTracesBeans!=null && mTracesBeans.size()>0){
                            mTracesBeans.clear();
                        }
                        //倒序
                        Collections.reverse(dataBean.getTraces());
                        mTracesBeans.addAll(dataBean.getTraces());
                        mLogisticAdapter.notifyDataSetChanged();
                    }else {
                        dataBinding.recyclerViewLogistic.setVisibility(View.GONE);
                        dataBinding.tvNoLogisticData.setVisibility(View.VISIBLE);
                    }
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
        return R.layout.activity_logistic_detail;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
