package com.example.healthmanage.ui.activity.mytask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityMyTaskBinding;
import com.example.healthmanage.view.MyTaskRecyclerView;
import com.example.healthmanage.widget.TitleToolBar;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.loadMyTask();
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
                            "onItemClick==========>: "+myTaskAdapter.getRecyclerViewList().get(position));
                }
            });
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
