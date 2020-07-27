package com.example.healthmanage.ui.activity.memberinfo;

import android.os.Bundle;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityMemberInfoBinding;
import com.example.healthmanage.widget.TitleToolBar;

public class MemberInfoActivity extends BaseActivity<ActivityMemberInfoBinding, MemberInfoViewModel> implements TitleToolBar.OnTitleIconClickCallBack {

    TitleToolBar titleToolBar = new TitleToolBar();

    @Override
    protected void initData() {
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitle("会员简介");
        viewModel.setTitleToolBar(titleToolBar);
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
        return R.layout.activity_member_info;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
