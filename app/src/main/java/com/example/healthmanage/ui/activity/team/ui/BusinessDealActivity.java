package com.example.healthmanage.ui.activity.team.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityBusinessDealBinding;
import com.example.healthmanage.ui.activity.team.TeamViewModel;
import com.example.healthmanage.ui.activity.team.adapter.BusinessDealAdapter;
import com.example.healthmanage.ui.activity.team.response.BusinessDealListResponse;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务处理界面
 */
public class BusinessDealActivity extends BaseActivity<ActivityBusinessDealBinding, TeamViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<BusinessDealListResponse.DataBean> businessDataBeans ;
    private BusinessDealAdapter businessDealAdapter;
    private int index = 0;

    @Override
    protected void initData() {
        context = BusinessDealActivity.this;
        titleToolBar.setTitle("业务处理");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        businessDataBeans = new ArrayList<>();
        businessDealAdapter = new BusinessDealAdapter(context,businessDataBeans);
        dataBinding.recyclerViewBusinessDeal.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        if (dataBinding.recyclerViewBusinessDeal.getItemDecorationCount()==0){
            dataBinding.recyclerViewBusinessDeal.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewBusinessDeal.setAdapter(businessDealAdapter);
        viewModel.getBusineServiceList(0);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.businessDealMutableLiveData.observe(this, new Observer<List<BusinessDealListResponse.DataBean>>() {
            @Override
            public void onChanged(List<BusinessDealListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewBusinessDeal.setVisibility(View.VISIBLE);
                    dataBinding.layoutBusinessDealNull.setVisibility(View.GONE);
                    if (businessDataBeans!=null && businessDataBeans.size()>0){
                        businessDataBeans.clear();
                    }
                    businessDataBeans.addAll(dataBeans);
                    businessDealAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewBusinessDeal.setVisibility(View.GONE);
                    dataBinding.layoutBusinessDealNull.setVisibility(View.VISIBLE);
                }
            }
        });
        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_wait_complete:
                        index = 0;
                        viewModel.getBusineServiceList(0);
                        break;
                    case R.id.rb_not_complete:
                        index = 1;
                        viewModel.getBusineServiceList(1);
                        break;
                    case R.id.rb_already_complete:
                        index = 2;
                        viewModel.getBusineServiceList(2);
                        break;
                }
            }
        });
        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                dataBinding.smartRefreshLayout.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                switch (index){
                    case 0:
                        viewModel.getBusineServiceList(0);
                        businessDealAdapter.notifyDataSetChanged();
                        dataBinding.smartRefreshLayout.finishRefresh(200);
                        break;
                    case 1:
                        viewModel.getBusineServiceList(1);
                        businessDealAdapter.notifyDataSetChanged();
                        dataBinding.smartRefreshLayout.finishRefresh(200);
                        break;
                    case 2:
                        viewModel.getBusineServiceList(2);
                        businessDealAdapter.notifyDataSetChanged();
                        dataBinding.smartRefreshLayout.finishRefresh(200);
                        break;
                }
            }
        });
        businessDealAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_deal_result:
                        Intent intent;
                        if (businessDataBeans.get(position).getStatus()==0){
                            intent = new Intent(context, BusinessDetailToCompleteActivity.class);
                        }else {
                            intent = new Intent(context, BusinessInfoActivity.class);
                        }
                        intent.putExtra("id",businessDataBeans.get(position).getId());
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (index){
            case 0:
                viewModel.getBusineServiceList(0);
                businessDealAdapter.notifyDataSetChanged();
                break;
            case 1:
                viewModel.getBusineServiceList(1);
                businessDealAdapter.notifyDataSetChanged();
                break;
            case 2:
                viewModel.getBusineServiceList(2);
                businessDealAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_business_deal;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
