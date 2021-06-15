package com.example.healthmanage.ui.activity.team.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityBusinessTeamBinding;
import com.example.healthmanage.ui.activity.team.TeamActivity;
import com.example.healthmanage.ui.activity.team.TeamDetailActivity;
import com.example.healthmanage.ui.activity.team.TeamViewModel;
import com.example.healthmanage.ui.activity.team.adapter.TeamMemberAdapter;
import com.example.healthmanage.ui.activity.team.response.DoctorTeamResponse;
import com.example.healthmanage.ui.activity.team.response.TeamMemberResponse;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务团队界面
 */
public class BusinessTeamActivity extends BaseActivity<ActivityBusinessTeamBinding, TeamViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<TeamMemberResponse.DataBean> teamMembers ;
    private TeamMemberAdapter teamMemberAdapter;
    private int index;
    private int mPosition = -1;
    @Override
    protected void initData() {
        context = BusinessTeamActivity.this;
        titleToolBar.setTitle("业务团队");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        viewModel.getDoctorTeam();
        teamMembers = new ArrayList<>();
        teamMemberAdapter = new TeamMemberAdapter(context,teamMembers);
        dataBinding.recylerView.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_divider));
        if (dataBinding.recylerView.getItemDecorationCount()==0){
            dataBinding.recylerView.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recylerView.setAdapter(teamMemberAdapter);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.doctorTeamResponseMutableLiveData.observe(this, new Observer<DoctorTeamResponse>() {
            @Override
            public void onChanged(DoctorTeamResponse doctorTeamResponse) {
                if (doctorTeamResponse!=null){
                    dataBinding.layoutTeamContent.setVisibility(View.VISIBLE);
                    dataBinding.layoutNoTeam.setVisibility(View.GONE);
                    dataBinding.btnAdd.setVisibility(View.GONE);
                    dataBinding.btnExit.setVisibility(View.VISIBLE);
                    dataBinding.btnExit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewModel.quitTeam();
                        }
                    });
                    Glide.with(context).load(doctorTeamResponse.getData().getAvatar()).apply(new RequestOptions().placeholder(R.drawable.ic_load_error).error(R.drawable.ic_load_error).circleCrop())
                            .into(dataBinding.ivAvatar);
                    dataBinding.tvName.setText(doctorTeamResponse.getData().getName());
                    dataBinding.tvRankPhone.setText(doctorTeamResponse.getData().getRank()+"\u3000|\u3000"+doctorTeamResponse.getData().getAppSystemUser().getPhone());
                    dataBinding.tvArea.setText("所在地区:"+doctorTeamResponse.getData().getAddr());
                    viewModel.getDoctorTeamList(10);
                }else {
                    dataBinding.layoutTeamContent.setVisibility(View.GONE);
                    dataBinding.layoutNoTeam.setVisibility(View.VISIBLE);
                    dataBinding.btnAdd.setVisibility(View.VISIBLE);
                    dataBinding.btnExit.setVisibility(View.GONE);
                    dataBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(JoinTeamActivity.class);
                        }
                    });
                }
            }
        });
        viewModel.teamMutableLiveData.observe(this, new Observer<List<TeamMemberResponse.DataBean>>() {
            @Override
            public void onChanged(List<TeamMemberResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recylerView.setVisibility(View.VISIBLE);
                    dataBinding.tvNullData.setVisibility(View.GONE);
                    if (teamMembers!=null && teamMembers.size()>0){
                        teamMembers.clear();
                    }
                    teamMembers.addAll(dataBeans);
                    teamMemberAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recylerView.setVisibility(View.GONE);
                    dataBinding.tvNullData.setVisibility(View.VISIBLE);
                }
            }
        });
        dataBinding.tablayoutTeam.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        viewModel.getDoctorTeamList(10);
                        index = 0;
                        //切换的时候如果不恢复会报越界
                        mPosition = -1;
                        break;
                    case 1:
                        viewModel.getDoctorTeamList(12);
                        index = 1;
                        mPosition = -1;
                        break;
                    case 2:
                        viewModel.getDoctorTeamList(11);
                        index = 2;
                        mPosition = -1;
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                dataBinding.smartRefreshLayout.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (index == 0){
                    viewModel.getDoctorTeamList(10);
                }else if (index == 1){
                    viewModel.getDoctorTeamList(12);
                }else if (index == 2){
                    viewModel.getDoctorTeamList(11);
                }
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });
        viewModel.isQuitSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    dataBinding.layoutTeamContent.setVisibility(View.GONE);
                    dataBinding.layoutNoTeam.setVisibility(View.VISIBLE);
                    dataBinding.btnAdd.setVisibility(View.VISIBLE);
                    dataBinding.btnExit.setVisibility(View.GONE);
                    dataBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(JoinTeamActivity.class);
                        }
                    });
                }
            }
        });
        teamMemberAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_more_detail:
                        Intent intent = new Intent(BusinessTeamActivity.this, TeamDetailActivity.class);
                        intent.putExtra("dataBean",teamMembers.get(position));
                        startActivity(intent);
                        break;
                }
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
        return R.layout.activity_business_team;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
