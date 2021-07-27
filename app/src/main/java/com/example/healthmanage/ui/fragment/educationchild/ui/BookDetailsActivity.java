package com.example.healthmanage.ui.fragment.educationchild.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityBookDetailsBinding;
import com.example.healthmanage.ui.activity.famousDoctorHall.ui.HospitalSurveyActivity;
import com.example.healthmanage.ui.fragment.education.NewEducationViewModel;
import com.example.healthmanage.ui.fragment.educationchild.adapter.BookCateAdapter;
import com.example.healthmanage.ui.fragment.educationchild.response.BookArticleResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.BookMenuResponse;
import com.example.healthmanage.utils.MImageGetter;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * desc:
 * date:2021/7/15 10:06
 * author:bWang
 */
public class BookDetailsActivity extends BaseActivity<ActivityBookDetailsBinding, NewEducationViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context mContext;
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private boolean isOpen = true;
    private LinearLayoutManager mManager = new LinearLayoutManager(this);
    private MImageGetter mImageGetter;

    @Override
    protected void initData() {
        mContext = BookDetailsActivity.this;
        mTitleToolBar.setTitle("期刊详情");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.titleBar.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);

        mImageGetter = new MImageGetter(getApplicationContext(), dataBinding.contentTv);

        dataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        Glide.with(this)
                .load(getIntent().getStringExtra("coverImg"))
                .into(dataBinding.coverImg);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.menuBook(getIntent().getStringExtra("bookId"));
        dataBinding.dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                isOpen = false;
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                isOpen = true;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        viewModel.mBookMenuResponseMutableLiveData.observe(this, new Observer<BookMenuResponse>() {
            @Override
            public void onChanged(BookMenuResponse bookMenuResponse) {
                if (bookMenuResponse.getData()!=null && bookMenuResponse.getData().size()>0){
                    dataBinding.contentTv.setText(bookMenuResponse.getData().get(0).getName());
                    initCateRv(bookMenuResponse);
                }
            }
        });
        viewModel.mDataBeanMutableLiveData.observe(this, new Observer<BookArticleResponse.DataBean>() {
            @Override
            public void onChanged(BookArticleResponse.DataBean dataBean) {
                dataBinding.contentScrollview.setVisibility(View.VISIBLE);
                dataBinding.contentTv.setText(Html.fromHtml(dataBean.getContent(), mImageGetter, null));
            }
        });
        dataBinding.menuImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    dataBinding.dl.openDrawer(Gravity.LEFT);
                    isOpen = false;
                } else {
                    dataBinding.dl.closeDrawer(Gravity.LEFT);
                    isOpen = true;
                }
            }
        });
    }

    /**
     * 期刊菜单
     */
    private void initCateRv(BookMenuResponse bookMenuResponse) {
        List<String> titles = new ArrayList<>();
        List<List<BookMenuResponse.DataBean.ListBean>> lists = new ArrayList<>();
        for (int i = 0; i < bookMenuResponse.getData().size(); i++) {
            titles.add(bookMenuResponse.getData().get(i).getName());
            lists.add(bookMenuResponse.getData().get(i).getList());
        }
        BookCateAdapter adapter = new BookCateAdapter(getApplicationContext(), titles, lists);
        dataBinding.recyclerView.setLayoutManager(mManager);
        dataBinding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListenerDIY(new BookCateAdapter.OnItemClickListenerDIY() {
            @Override
            public void onItemClick(int section, int position) {
                viewModel.getArticle(String.valueOf(lists.get(section).get(position).getId()));
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        mTitleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_book_details;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
