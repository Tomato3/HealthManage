package com.example.healthmanage.ui.activity.searchMember;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivitySearchMemberBinding;
import com.example.healthmanage.bean.recyclerview.MyMemberRecyclerView;

import java.util.List;

public class SearchMemberActivity extends BaseActivity<ActivitySearchMemberBinding, SearchMemberViewModel> implements View.OnClickListener {

    private BaseAdapter myMemberAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

        myMemberAdapter = new BaseAdapter(SearchMemberActivity.this,
                null, R.layout.recycler_view_item_my_member, BR.MyMemberRecyclerView);
        dataBinding.recyclerViewSearchMember.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewSearchMember.setAdapter(myMemberAdapter);
        viewModel.myMemberMutableLiveData.observe(this, new Observer<List<MyMemberRecyclerView>>() {
            @Override
            public void onChanged(List<MyMemberRecyclerView> myMemberRecyclerViewList) {
                myMemberAdapter.setRecyclerViewList(myMemberRecyclerViewList);
                myMemberAdapter.notifyDataSetChanged();
            }
        });

        dataBinding.tvCancel.setOnClickListener(this::onClick);
        dataBinding.includeSearch.ivClear.setOnClickListener(this::onClick);
        dataBinding.includeSearch.etSearch.setHint(R.string.hint_input_member_name);
        dataBinding.includeSearch.etSearch.setInputType(InputType.TYPE_CLASS_TEXT);
        dataBinding.includeSearch.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    viewModel.search(v.getText().toString());
                    return true;
                }
                return false;
            }
        });

        dataBinding.includeSearch.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    dataBinding.includeSearch.ivClear.setVisibility(View.VISIBLE);
                } else {
                    dataBinding.includeSearch.ivClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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
            case R.id.iv_clear:
                dataBinding.includeSearch.etSearch.setText("");
                viewModel.search("");
                break;
        }
    }
}
