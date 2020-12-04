package com.example.healthmanage.ui.activity.memberlist;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityMemberListBinding;
import com.example.healthmanage.bean.recyclerview.MyMemberRecyclerView;
import com.example.healthmanage.widget.TitleToolBar;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.List;

public class MemberListActivity extends BaseActivity<ActivityMemberListBinding,
        MemberListViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {

    private TitleToolBar titleToolBar = new TitleToolBar();
    private LinearLayoutManager linearLayoutManager;
    private BaseAdapter myMemberAdapter;
    private int rank, type;
    private Bundle bundle;

    @Override
    protected void initData() {
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
        bundle = this.getIntent().getExtras();
        type = bundle.getInt("focusOrMember");
        viewModel.loadDifferentLevelMember(type, 0);
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.rbOrdinaryMember.setOnClickListener(this::onClick);
        dataBinding.rbVipMember.setOnClickListener(this::onClick);
        dataBinding.rbSvipMember.setOnClickListener(this::onClick);

        linearLayoutManager = new LinearLayoutManager(MemberListActivity.this);
        myMemberAdapter = new BaseAdapter(this, null, R.layout.recycler_view_item_my_member,
                BR.MyMemberRecyclerView);
        viewModel.myMemberMutableLiveData.observe(this, new Observer<List<MyMemberRecyclerView>>() {
            @Override
            public void onChanged(List<MyMemberRecyclerView> myMemberRecyclerViewList) {
                myMemberAdapter.setRecyclerViewList(myMemberRecyclerViewList);
                dataBinding.recyclerViewMemberList.setLayoutManager(linearLayoutManager);
                dataBinding.recyclerViewMemberList.setAdapter(myMemberAdapter);
            }
        });

        LiveEventBus.get("refresh", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        viewModel.loadDifferentLevelMember(type, rank);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_ordinary_member:
                rank = 0;
                viewModel.loadDifferentLevelMember(type, rank);
                break;
            case R.id.rb_vip_member:
                rank = 1;
                viewModel.loadDifferentLevelMember(type, rank);
                break;
            case R.id.rb_svip_member:
                rank = 2;
                viewModel.loadDifferentLevelMember(type, rank);
                break;
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_member_list;
    }
}
