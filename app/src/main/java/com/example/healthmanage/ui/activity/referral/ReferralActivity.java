package com.example.healthmanage.ui.activity.referral;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityReferralBinding;
import com.example.healthmanage.ui.activity.consultation.ConsultationDetailActivity;
import com.example.healthmanage.ui.activity.referral.adapter.ReferralAdapter;
import com.example.healthmanage.ui.activity.referral.response.ReferralResponse;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 患者转诊列表
 */
public class ReferralActivity extends BaseActivity<ActivityReferralBinding, ReferralViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private Context context;
    private List<ReferralResponse.DataBean> dataBeanList;
    private ReferralAdapter referralAdapter;

    @Override
    protected void initData() {
        context = ReferralActivity.this;
        titleToolBar.setTitle("患者转诊");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        dataBeanList = new ArrayList<>();
        referralAdapter = new ReferralAdapter(dataBeanList);
        dataBinding.recyclerViewReferralTask.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        if (dataBinding.recyclerViewReferralTask.getItemDecorationCount()==0){
            dataBinding.recyclerViewReferralTask.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewReferralTask.setAdapter(referralAdapter);
        viewModel.getPatientReferral();

        referralAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context, ReferralDetailActivity.class);
                intent.putExtra("dataBean", dataBeanList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.referralListData.observe(this, new Observer<List<ReferralResponse.DataBean>>() {
            @Override
            public void onChanged(List<ReferralResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewReferralTask.setVisibility(View.VISIBLE);
                    dataBinding.layoutReferralTaskNull.setVisibility(View.GONE);
                    if (dataBeanList!=null && dataBeanList.size()>0){
                        dataBeanList.clear();
                    }
                    dataBeanList.addAll(dataBeans);
                    referralAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewReferralTask.setVisibility(View.GONE);
                    dataBinding.layoutReferralTaskNull.setVisibility(View.VISIBLE);
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
                viewModel.getPatientReferral();
                referralAdapter.notifyDataSetChanged();
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });
        dataBinding.layoutWriteReferralTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PatientReferralActivity.class);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getPatientReferral();
        referralAdapter.notifyDataSetChanged();
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_referral;
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
}
