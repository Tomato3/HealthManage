package com.example.healthmanage.ui.activity.mycomment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityMyCommentBinding;
import com.example.healthmanage.ui.activity.commentdetail.CommentDetailActivity;
import com.example.healthmanage.view.MyCommentRecyclerView;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.List;

public class MyCommentActivity extends BaseActivity<ActivityMyCommentBinding, MyCommentViewModel> implements TitleToolBar.OnTitleIconClickCallBack {

    TitleToolBar titleToolBar = new TitleToolBar();
    BaseAdapter commentAdapter;
    Bundle bundle;

    @Override
    protected void initData() {
        titleToolBar.setTitle(getString(R.string.my_comment));
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.getMyComment();
    }

    @Override
    public void initViewParameters() {
        super.initViewParameters();

    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        commentAdapter = new BaseAdapter(this, null, R.layout.recycler_view_item_my_comment,
                BR.MyCommentRecyclerView);
        dataBinding.recyclerViewComment.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewComment.setAdapter(commentAdapter);
        viewModel.myCommentMutableLiveData.observe(this, new Observer<List<MyCommentRecyclerView>>() {
            @Override
            public void onChanged(List<MyCommentRecyclerView> myCommentRecyclerViews) {
                commentAdapter.setRecyclerViewList(myCommentRecyclerViews);
                commentAdapter.notifyDataSetChanged();
            }
        });

        commentAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MyCommentRecyclerView myCommentRecyclerView =
                        (MyCommentRecyclerView) commentAdapter.getRecyclerViewList().get(position);
                String nickName = myCommentRecyclerView.nickName;
                bundle = new Bundle();
                bundle.putString("nickName", nickName);
                startActivity(CommentDetailActivity.class, bundle);
            }
        });


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
        return R.layout.activity_my_comment;
    }
}
