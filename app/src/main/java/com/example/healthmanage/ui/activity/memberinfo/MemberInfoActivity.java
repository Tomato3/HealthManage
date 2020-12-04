package com.example.healthmanage.ui.activity.memberinfo;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityMemberInfoBinding;
import com.example.healthmanage.bean.recyclerview.DataItem;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.List;

public class MemberInfoActivity extends BaseActivity<ActivityMemberInfoBinding,
        MemberInfoViewModel> implements TitleToolBar.OnTitleIconClickCallBack {

    TitleToolBar titleToolBar = new TitleToolBar();
    BaseAdapter memberInfoAdapter;

    @Override
    protected void initData() {
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitle("会员简介");
        viewModel.setTitleToolBar(titleToolBar);

        viewModel.getMemberInfo(getIntent().getExtras().getInt("MemberId"));
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        memberInfoAdapter = new BaseAdapter(this, null, R.layout.item_data, BR.DataItem);

        dataBinding.recyclerViewMemberInfo.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewMemberInfo.setAdapter(memberInfoAdapter);

        viewModel.dataItemMutableLiveData.observe(this, new Observer<List<DataItem>>() {
            @Override
            public void onChanged(List<DataItem> dataItems) {
                memberInfoAdapter.setRecyclerViewList(dataItems);
                memberInfoAdapter.notifyDataSetChanged();
            }
        });
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
        return R.layout.activity_member_info;
    }
}
