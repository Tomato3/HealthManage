package com.example.healthmanage.ui.activity.setting.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivitySettingBinding;
import com.example.healthmanage.ui.activity.setting.SettingViewModel;
import com.example.healthmanage.utils.ToastUtils;
import com.example.healthmanage.widget.TitleToolBar;

/**
 * desc:设置界面
 * date:2021/7/27 15:55
 * author:bWang
 */
public class SettingActivity extends BaseActivity<ActivitySettingBinding, SettingViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context mContext;
    private TitleToolBar mTitleToolBar = new TitleToolBar();

    @Override
    protected void initData() {
        mContext = SettingActivity.this;
        mTitleToolBar.setTitle("设置");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutSettingTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);

    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_setting;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
