package com.example.healthmanage.ui.activity.academicJournals.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityAcademicJournalsBinding;
import com.example.healthmanage.ui.activity.academicJournals.adapter.PeriodicalAdapter;
import com.example.healthmanage.ui.activity.academicJournals.response.PeriodicalListResponse;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 学术期刊列表界面
 */
public class AcademicJournalsActivity extends BaseActivity<ActivityAcademicJournalsBinding,AcademicJournalsViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private Context context;
    private List<PeriodicalListResponse.DataBean> periodicalBeans;
    private PeriodicalAdapter periodicalAdapter;
    private int index = 0;
    private int mPosition;

    @Override
    protected void initData() {
        context = AcademicJournalsActivity.this;
        titleToolBar.setTitle("学术期刊");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        periodicalBeans = new ArrayList<>();
        periodicalAdapter = new PeriodicalAdapter(context,periodicalBeans);
        dataBinding.recyclerViewAcademicJournals.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        if (dataBinding.recyclerViewAcademicJournals.getItemDecorationCount()==0){
            dataBinding.recyclerViewAcademicJournals.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewAcademicJournals.setAdapter(periodicalAdapter);
        viewModel.getPeriodicalList(0);

        periodicalAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_go_contribution:
                        Intent intent = new Intent(context,EditAcademicActivity.class);
                        intent.putExtra("dataBean",periodicalBeans.get(position));
                        startActivity(intent);
                        break;
                    case R.id.tv_watch_detail:
                        Intent intent1 = new Intent(context,AcademicInfoActivity.class);
                        intent1.putExtra("id",periodicalBeans.get(position).getId());
                        startActivity(intent1);
                        break;
                    case R.id.tv_see_contribution_detail:
                        ToastUtil.showShort("查看过稿详情");
                        break;
                    case R.id.tv_pass_or_rejection_explain:
                        if (index==2){
                            ToastUtil.showShort("过稿说明");
                        }else {
                            ToastUtil.showShort("退稿说明");
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (index == 0){
            initData();
            periodicalAdapter.notifyDataSetChanged();
        }else if (index == 1){
            viewModel.getPeriodicalList(1);
            periodicalAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.periodicalLiveData.observe(this, new Observer<List<PeriodicalListResponse.DataBean>>() {
            @Override
            public void onChanged(List<PeriodicalListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewAcademicJournals.setVisibility(View.VISIBLE);
                    dataBinding.layoutAcademicJournalsNull.setVisibility(View.GONE);
                    if (periodicalBeans!=null && periodicalBeans.size()>0){
                        periodicalBeans.clear();
                    }
                    periodicalBeans.addAll(dataBeans);
                    periodicalAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewAcademicJournals.setVisibility(View.GONE);
                    dataBinding.layoutAcademicJournalsNull.setVisibility(View.VISIBLE);
                }
            }
        });
        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_draft_box:
                        index = 0;
                        viewModel.getPeriodicalList(0);
                        break;
                    case R.id.rb_wait_examine:
                        index = 1;
                        viewModel.getPeriodicalList(1);
                        break;
                    case R.id.rb_already_pass:
                        index = 2;
                        viewModel.getPeriodicalList(2);
                        break;
                    case R.id.rb_already_rejection:
                        index = 3;
                        viewModel.getPeriodicalList(3);
                        break;
                }
            }
        });

        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                dataBinding.smartRefreshLayout.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                switch (index){
                    case 0:
                        viewModel.getPeriodicalList(0);
                        periodicalAdapter.notifyDataSetChanged();
                        dataBinding.smartRefreshLayout.finishRefresh(200);
                        break;
                    case 1:
                        viewModel.getPeriodicalList(1);
                        periodicalAdapter.notifyDataSetChanged();
                        dataBinding.smartRefreshLayout.finishRefresh(200);
                        break;
                    case 2:
                        viewModel.getPeriodicalList(2);
                        periodicalAdapter.notifyDataSetChanged();
                        dataBinding.smartRefreshLayout.finishRefresh(200);
                        break;
                    case 3:
                        viewModel.getPeriodicalList(3);
                        periodicalAdapter.notifyDataSetChanged();
                        dataBinding.smartRefreshLayout.finishRefresh(200);
                        break;
                }
            }
        });
        dataBinding.layoutWriteAcademicJournals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CreateAcademicJournalsActivity.class);
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
        return R.layout.activity_academic_journals;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
