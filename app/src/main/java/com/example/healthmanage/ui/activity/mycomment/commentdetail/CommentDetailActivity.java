package com.example.healthmanage.ui.activity.mycomment.commentdetail;

import android.os.Bundle;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityCommentDetailBinding;
import com.example.healthmanage.widget.TitleToolBar;

public class CommentDetailActivity extends BaseActivity<ActivityCommentDetailBinding, CommentDetailViewModel> implements TitleToolBar.OnTitleIconClickCallBack {

    TitleToolBar titleToolBar = new TitleToolBar();
    Bundle bundle;

    @Override
    protected void initData() {
        bundle = this.getIntent().getExtras();
        titleToolBar.setTitle("评论详情");
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
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
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_comment_detail;
    }
}
