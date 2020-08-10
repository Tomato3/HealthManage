package com.example.healthmanage.ui.activity.servicecenter;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityServiceCenterBinding;
import com.example.healthmanage.widget.TitleToolBar;

public class ServiceCenterActivity extends BaseActivity<ActivityServiceCenterBinding, ServiceCenterViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {

    TitleToolBar titleToolBar = new TitleToolBar();

    @Override
    protected void initData() {
        titleToolBar.setTitle("服务中心");
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.includeSearch.ivClear.setOnClickListener(this::onClick);
        dataBinding.includeSearch.etSearch.setHint(R.string.hint_input_member_phone);
        dataBinding.includeSearch.etSearch.setInputType(InputType.TYPE_CLASS_PHONE);
        dataBinding.includeSearch.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
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
        return R.layout.activity_service_center;
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
            case R.id.iv_clear:
                dataBinding.includeSearch.etSearch.setText("");
                break;
        }
    }
}
