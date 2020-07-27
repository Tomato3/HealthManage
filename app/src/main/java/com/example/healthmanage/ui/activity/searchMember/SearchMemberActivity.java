package com.example.healthmanage.ui.activity.searchMember;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivitySearchMemberBinding;
import com.example.healthmanage.view.MyMemberRecyclerView;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.List;

public class SearchMemberActivity extends BaseActivity<ActivitySearchMemberBinding, SearchMemberViewModel> implements View.OnClickListener {

    LinearLayoutManager linearLayoutManager;
    BaseAdapter myMemberAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

        linearLayoutManager = new LinearLayoutManager(SearchMemberActivity.this);
        viewModel.myMemberMutableLiveData.observe(this, new Observer<List<MyMemberRecyclerView>>() {
            @Override
            public void onChanged(List<MyMemberRecyclerView> myMemberRecyclerViewList) {
                myMemberAdapter = new BaseAdapter(SearchMemberActivity.this,
                        myMemberRecyclerViewList,R.layout.recycler_view_item_my_member,BR.MyMemberRecyclerView);
                dataBinding.recyclerViewSearchMember.setLayoutManager(linearLayoutManager);
                dataBinding.recyclerViewSearchMember.setAdapter(myMemberAdapter);
            }
        });

        dataBinding.tvCancel.setOnClickListener(this::onClick);

        dataBinding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    dataBinding.etSearch.setCursorVisible(false);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(dataBinding.etSearch.getWindowToken(), 0);
                    viewModel.search();
                    return true;
                }
                return false;
            }
        });

        LiveEventBus.get("refresh", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        viewModel.search();
                    }
                });
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_search_member;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
        }
    }
}
