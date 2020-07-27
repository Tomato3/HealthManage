package com.example.healthmanage.ui.activity.invitingmembers;

import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityInvitingMembersBinding;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.TitleToolBar;

public class InvitingMembersActivity extends BaseActivity<ActivityInvitingMembersBinding, InvitingMembersViewModel> implements TitleToolBar.OnTitleIconClickCallBack {

    private TitleToolBar titleToolBar = new TitleToolBar();

    @Override
    protected void initData() {
        titleToolBar.setTitle("邀请会员");
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_inviting_members;
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.inviteBtnVisible.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    ToolUtil.HideKeyboard(dataBinding.etPhone);
                }
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
}

