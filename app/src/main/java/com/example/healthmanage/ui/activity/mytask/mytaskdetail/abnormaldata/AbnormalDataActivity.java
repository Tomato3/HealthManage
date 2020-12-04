package com.example.healthmanage.ui.activity.mytask.mytaskdetail.abnormaldata;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityAbnormalDataBinding;
import com.example.healthmanage.bean.recyclerview.DataItem;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.List;

public class AbnormalDataActivity extends BaseActivity<ActivityAbnormalDataBinding, AbnormalDataViewModel> implements TitleToolBar.OnTitleIconClickCallBack {

    TitleToolBar titleToolBar = new TitleToolBar();
    Bundle bundle;
    String title, dataDate;
    int taskId;
    BaseAdapter abnormalDataAdapter;

    @Override
    protected void initData() {
        bundle = this.getIntent().getExtras();
        taskId = bundle.getInt("taskId");
        dataDate = bundle.getString("dataDate");
        title = bundle.getString("abnormalDataTitle");
        titleToolBar.setTitle(title);
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.getAbnormalData(taskId, dataDate);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

        abnormalDataAdapter = new BaseAdapter(this, null, R.layout.item_data
                , BR.DataItem);
        dataBinding.recyclerViewAbnormalData.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewAbnormalData.setAdapter(abnormalDataAdapter);

        viewModel.dataItemMutableLiveData.observe(this, new Observer<List<DataItem>>() {
            @Override
            public void onChanged(List<DataItem> dataItems) {
                abnormalDataAdapter.setRecyclerViewList(dataItems);
                abnormalDataAdapter.notifyDataSetChanged();
            }
        });

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
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_abnormal_data;
    }
}
