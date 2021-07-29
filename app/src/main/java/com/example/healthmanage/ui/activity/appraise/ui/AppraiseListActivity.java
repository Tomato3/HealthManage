package com.example.healthmanage.ui.activity.appraise.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityAppraiseListBinding;
import com.example.healthmanage.ui.activity.appraise.AppraiseViewModel;
import com.example.healthmanage.ui.activity.appraise.response.AppraiseListResponse;
import com.example.healthmanage.ui.activity.integral.adapter.OrderListAdapter;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 我的评价
 */
public class AppraiseListActivity extends BaseActivity<ActivityAppraiseListBinding, AppraiseViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private AppraiseAdapter mAppraiseAdapter;
    private List<AppraiseListResponse.DataBean> mDataBeans;

    @Override
    protected void initData() {
        mTitleToolBar.setTitle("我的评价");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutAppraiseTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);

        mDataBeans = new ArrayList<>();
        viewModel.appraiseList();
        mAppraiseAdapter = new AppraiseAdapter(mDataBeans);
        dataBinding.recyclerViewMyAppraise.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_notice));
        if (dataBinding.recyclerViewMyAppraise.getItemDecorationCount()==0){
            dataBinding.recyclerViewMyAppraise.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewMyAppraise.setAdapter(mAppraiseAdapter);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.mAppraiseListMutableLiveData.observe(this, new Observer<AppraiseListResponse>() {
            @Override
            public void onChanged(AppraiseListResponse appraiseListResponse) {
                if (appraiseListResponse.getData()!=null && appraiseListResponse.getData().size()>0){
                    dataBinding.recyclerViewMyAppraise.setVisibility(View.VISIBLE);
                    dataBinding.tvAppraiseDataNull.setVisibility(View.GONE);
                    if (mDataBeans!=null && mDataBeans.size()>0){
                        mDataBeans.clear();
                    }
                    mDataBeans.addAll(appraiseListResponse.getData());
                    mAppraiseAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewMyAppraise.setVisibility(View.GONE);
                    dataBinding.tvAppraiseDataNull.setVisibility(View.VISIBLE);
                }
            }
        });

        dataBinding.swipeRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
                dataBinding.swipeRefresh.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
                viewModel.appraiseList();
                dataBinding.swipeRefresh.finishRefresh(200);
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
        return R.layout.activity_appraise_list;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    public class AppraiseAdapter extends BaseQuickAdapter<AppraiseListResponse.DataBean, BaseViewHolder>{

        public AppraiseAdapter(@Nullable List<AppraiseListResponse.DataBean> data) {
            super(R.layout.item_my_appraise,data);
        }

        @Override
        protected void convert(BaseViewHolder helper, AppraiseListResponse.DataBean item) {
            Glide.with(AppraiseListActivity.this).load(item.getAppUser().getAvatar()).apply(new RequestOptions().circleCrop())
                    .error(R.drawable.ic_doctor_logo)
                    .into((ImageView) helper.getView(R.id.iv_my_avatar));
            helper.setRating(R.id.ratingbar,item.getScore());
            helper.setText(R.id.tv_appraise,item.getContent());
            helper.setText(R.id.tv_name_time,item.getAppUser().getNickName()+"\u3000"+ToolUtil.timestampToDate(String.valueOf(item.getCreateTime()),null));
        }
    }
}