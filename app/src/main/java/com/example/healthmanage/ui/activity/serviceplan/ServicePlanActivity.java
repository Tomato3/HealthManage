package com.example.healthmanage.ui.activity.serviceplan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityServicePlanBinding;
import com.example.healthmanage.ui.activity.serviceplan.serviceplandetail.ServicePlanDetailActivity;
import com.example.healthmanage.view.ServicePlanRecyclerView;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class ServicePlanActivity extends BaseActivity<ActivityServicePlanBinding, ServicePlanViewModel> implements TitleToolBar.OnTitleIconClickCallBack {

    private TitleToolBar titleToolBar = new TitleToolBar();
    private BaseAdapter servicePlanAdapter;

    @Override
    protected void initData() {
        titleToolBar.setTitle("护理计划");
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.getServicePlanList(true);
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }


    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

        servicePlanAdapter = new BaseAdapter(this, null, R.layout.recycler_view_item_service_plan,
                BR.ServicePlanRecyclerView);
        dataBinding.recyclerViewServicePlan.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewServicePlan.setAdapter(servicePlanAdapter);
        viewModel.servicePlanMutableLiveData.observe(this, new Observer<List<ServicePlanRecyclerView>>() {
            @Override
            public void onChanged(List<ServicePlanRecyclerView> servicePlanRecyclerViews) {
                servicePlanAdapter.setRecyclerViewList(servicePlanRecyclerViews);
                servicePlanAdapter.notifyDataSetChanged();
            }
        });

        servicePlanAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ServicePlanRecyclerView servicePlanRecyclerView =
                        (ServicePlanRecyclerView) servicePlanAdapter.getRecyclerViewList().get(position);
                startActivity(new Intent(ServicePlanActivity.this,
                        ServicePlanDetailActivity.class).putExtra("servicePlanRecyclerView", servicePlanRecyclerView));
            }
        });

        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.getServicePlanList(false);
                viewModel.isSucceed.observe(ServicePlanActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        dataBinding.smartRefreshLayout.finishLoadMore(aBoolean);
                    }
                });

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.getServicePlanList(true);
                viewModel.isSucceed.observe(ServicePlanActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        dataBinding.smartRefreshLayout.finishRefresh(aBoolean);
                    }
                });
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        Log.d(HTAG, "onTouchEvent==========>: " + y+"===>");
        return super.onTouchEvent(event);
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_service_plan;
    }
}
