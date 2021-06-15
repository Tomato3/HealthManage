package com.example.healthmanage.ui.activity.mytask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.bean.network.response.TaskResponse;
import com.example.healthmanage.databinding.ActivityMyTaskBinding;
import com.example.healthmanage.ui.activity.mytask.adapter.MyTaskAdapter;
import com.example.healthmanage.ui.activity.temperature.response.HealthTaskResponse;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的异常列表
 */
public class MyNewTaskActivity extends BaseActivity<ActivityMyTaskBinding,MyNewTaskViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<HealthTaskResponse.DataBean> healthTaskBeans;
    private MyTaskAdapter myTaskAdapter;
    private int index;

    @Override
    protected void initData() {
        titleToolBar.setTitle("健康异常");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        healthTaskBeans = new ArrayList<>();
        myTaskAdapter = new MyTaskAdapter(healthTaskBeans,MyNewTaskActivity.this);
        dataBinding.recyclerViewMyTask.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_7));
        //避免重复添加分割线
        if (dataBinding.recyclerViewMyTask.getItemDecorationCount()==0){
            dataBinding.recyclerViewMyTask.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewMyTask.setAdapter(myTaskAdapter);
        viewModel.getHealthTaskList( 0);
        myTaskAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.btn_deal_task:
                        Intent intent = new Intent(MyNewTaskActivity.this,DealTaskActivity.class);
                        intent.putExtra("dataBean",healthTaskBeans.get(position));
                        startActivity(intent);
                        break;
                    case R.id.btn_see_deal:
                        Intent seeIntent = new Intent(MyNewTaskActivity.this,WatchTaskDetailActivity.class);
                        seeIntent.putExtra("dataBean",healthTaskBeans.get(position));
                        startActivity(seeIntent);
//                        Bundle SeeBundle = new Bundle();
//                        SeeBundle.putInt("id",healthTaskBeans.get(position).getId());
//                        SeeBundle.putString("taskContent",healthTaskBeans.get(position).getContent());
//                        SeeBundle.putString("doctorReply",healthTaskBeans.get(position).getProposal());
//                        startActivity(WatchTaskDetailActivity.class,SeeBundle);
                        break;
                    case R.id.btn_transmit_deal:
                        Bundle transferBundle = new Bundle();
                        transferBundle.putInt("id",healthTaskBeans.get(position).getId());
                        startActivity(TransMitDealActivity.class,transferBundle);
                        break;
                }
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.healthTaskMutableLiveData.observe(this, new Observer<List<HealthTaskResponse.DataBean>>() {
            @Override
            public void onChanged(List<HealthTaskResponse.DataBean> rowsBeans) {
                if (rowsBeans != null && rowsBeans.size()>0){
                    dataBinding.tvTaskNull.setVisibility(View.GONE);
                    dataBinding.recyclerViewMyTask.setVisibility(View.VISIBLE);
                    if (healthTaskBeans!=null && healthTaskBeans.size()>0){
                        healthTaskBeans.clear();
                    }
                    healthTaskBeans.addAll(rowsBeans);
                    myTaskAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.tvTaskNull.setVisibility(View.VISIBLE);
                    dataBinding.recyclerViewMyTask.setVisibility(View.GONE);
                }

            }
        });

        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_untreated:
                        viewModel.getHealthTaskList(0);
                        myTaskAdapter.notifyDataSetChanged();
                        index = 0;
                        break;
                    case R.id.rb_processing:
                        viewModel.getTransferHealthTaskList( 1);
                        myTaskAdapter.notifyDataSetChanged();
                        index =1;
                        break;
                    case R.id.rb_processed:
                        viewModel.getHealthTaskList(1);
                        myTaskAdapter.notifyDataSetChanged();
                        index =2;
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
                dataBinding.smartRefreshLayout.finishRefresh(200);
                switch (index){
                    case 0:
                        viewModel.getHealthTaskList(0);
                        myTaskAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        viewModel.getTransferHealthTaskList(1);
                        myTaskAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        viewModel.getHealthTaskList(1);
                        myTaskAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (index==0){
            initData();
            myTaskAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_my_task;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
