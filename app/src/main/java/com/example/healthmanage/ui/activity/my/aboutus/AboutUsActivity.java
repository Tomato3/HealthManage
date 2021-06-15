package com.example.healthmanage.ui.activity.my.aboutus;

import android.os.Bundle;
import android.view.View;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityAboutUsBinding;
import com.example.healthmanage.widget.TitleToolBar;

public class AboutUsActivity extends BaseActivity<ActivityAboutUsBinding,AboutUsViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener{
    private TitleToolBar titleToolBar = new TitleToolBar();

    @Override
    protected void initData() {
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        titleToolBar.setTitle("关于我们");
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.titleAboutUs.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        viewModel.setTitleToolBar(titleToolBar);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_about_us;
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
    public void onClick(View v) {

    }
}
