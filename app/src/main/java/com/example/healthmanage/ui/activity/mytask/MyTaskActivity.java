package com.example.healthmanage.ui.activity.mytask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityMyTaskBinding;
import com.example.healthmanage.view.MyTaskRecyclerView;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MyTaskActivity extends BaseActivity<ActivityMyTaskBinding, MyTaskViewModel> implements TitleToolBar.OnTitleIconClickCallBack {

    private TitleToolBar titleToolBar = new TitleToolBar();
    private BaseAdapter myTaskAdapter;

    @Override
    protected void initData() {
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitle("我的任务");
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.loadMyTask(true, 0);
    }


    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        myTaskAdapter = new BaseAdapter(this, null, R.layout.recycler_view_item_my_task,
                BR.MyTaskRecyclerView);
        dataBinding.recyclerViewMyTask.setLayoutManager(new LinearLayoutManager(this));
        viewModel.myTaskMutableLiveData.observe(this, new Observer<List<MyTaskRecyclerView>>() {
            @Override
            public void onChanged(List<MyTaskRecyclerView> myTaskRecyclerViews) {
                myTaskAdapter.setRecyclerViewList(myTaskRecyclerViews);
                dataBinding.recyclerViewMyTask.setAdapter(myTaskAdapter);
            }
        });

        if (myTaskAdapter != null) {
            myTaskAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(View view, int position) {
                    Log.d(HTAG,
                            "onItemClick==========>: " + myTaskAdapter.getRecyclerViewList().get(position));
                }
            });
        }

        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_untreated:
                        viewModel.loadMyTask(viewModel.isSucceed.getValue(), 0);
                        break;
                    case R.id.rb_processing:
                        viewModel.loadMyTask(viewModel.isSucceed.getValue(), 1);
                        break;
                    case R.id.rb_processed:
                        viewModel.loadMyTask(viewModel.isSucceed.getValue(), 2);
                        break;
                }
            }
        });


        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.loadMyTask(false, viewModel.status.getValue());
                viewModel.isSucceed.observe(MyTaskActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean) {
                            dataBinding.smartRefreshLayout.finishLoadMore(aBoolean);
                        }
                    }
                });
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.loadMyTask(true, viewModel.status.getValue());
                viewModel.isSucceed.observe(MyTaskActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean) {
                            dataBinding.smartRefreshLayout.finishRefresh(aBoolean);
                        }
                    }
                });
            }
        });


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
