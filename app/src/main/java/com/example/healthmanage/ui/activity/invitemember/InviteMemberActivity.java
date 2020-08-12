package com.example.healthmanage.ui.activity.invitemember;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityInviteMemberBinding;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.TitleToolBar;
import com.jeremyliao.liveeventbus.LiveEventBus;

public class InviteMemberActivity extends BaseActivity<ActivityInviteMemberBinding, InviteMemberViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {

    private TitleToolBar titleToolBar = new TitleToolBar();

    @Override
    protected void initData() {
        titleToolBar.setTitle("邀请会员");
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

        LiveEventBus.get("CloseKeyboard", Boolean.class).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                ToolUtil.hideKeyboard(dataBinding.includeSearch.etSearch);
            }
        });


        dataBinding.includeSearch.ivClear.setOnClickListener(this::onClick);
        dataBinding.includeSearch.etSearch.setHint(R.string.hint_input_member_phone);
        dataBinding.includeSearch.etSearch.setInputType(InputType.TYPE_CLASS_PHONE);
        dataBinding.includeSearch.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    viewModel.searchMember(v.getText().toString());
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
                viewModel.searchMember("");
                break;
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_invite_member;
    }
}

