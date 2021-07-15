package com.example.healthmanage.ui.activity.vipmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityVipTeamBinding;
import com.example.healthmanage.ui.activity.memberdetail.MemberNewDetailActivity;
import com.example.healthmanage.ui.activity.vipmanager.adapter.MemberTeamAdapter;
import com.example.healthmanage.ui.activity.vipmanager.response.MemberTeamListResponse;
import com.example.healthmanage.utils.SoftKeyboardUtils;
import com.example.healthmanage.utils.StatusBarUitils;
import com.example.healthmanage.view.GridItemDecoration;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 会员管理
 */
public class MemberManageListActivity extends BaseActivity<ActivityVipTeamBinding,MemberTeamViewModel> {
    private int type;
    private LinearLayoutManager manager = new LinearLayoutManager(this);
    private MemberTeamAdapter mMemberTeamAdapter;
    private Context mContext;
    private int tabPosition;
    private int mPosition;
    private List<String> ranks;
    private String rank;
    private List<MemberTeamListResponse.DataBean> mDataBeanList;

    @Override
    protected void initData() {
        mContext = MemberManageListActivity.this;
        StatusBarUitils.setStatusBar(R.color.white, true, this);
        ranks = new ArrayList<>();
        if (ranks!=null && ranks.size()>0){
            ranks.clear();
        }
        initRv();
        type = getIntent().getIntExtra("type", 0);
        if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==9){
            dataBinding.tablayout.getTabAt(0).setText("全部会员");
            dataBinding.tvTitle.setText("会员管理");
        }else {
            dataBinding.tablayout.getTabAt(0).setText("全部患者");
            dataBinding.tvTitle.setText("患者管理");
        }
        switch (type){
            case 0://首页点击过重点关注会员,页面进来直接切换重点关注
                //TODO
                dataBinding.checkBox.setChecked(true);
                dataBinding.checkBox2.setChecked(true);
                dataBinding.checkBox3.setChecked(true);
                dataBinding.checkBox4.setChecked(true);
                tabPosition = 1;
                dataBinding.tablayout.getTabAt(1).select();
                ranks.add("0");
                ranks.add("1");
                ranks.add("2");
                ranks.add("3");
                rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
                viewModel.getMemberTeamList(rank,1);
                break;
            case 99://首页点击过会员管理
                dataBinding.checkBox.setChecked(true);
                dataBinding.checkBox2.setChecked(true);
                dataBinding.checkBox3.setChecked(true);
                dataBinding.checkBox4.setChecked(true);
                ranks.add("0");
                ranks.add("1");
                ranks.add("2");
                ranks.add("3");
                rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
                viewModel.getMemberTeamList(rank,0);
                break;
            default://默认则按会员等级查询type为1 2 3
                if (type-1 == 0){
                    dataBinding.checkBox.setChecked(true);
                    dataBinding.checkBox2.setChecked(false);
                    dataBinding.checkBox3.setChecked(false);
                    dataBinding.checkBox4.setChecked(false);
                }else if (type - 1 == 1){
                    dataBinding.checkBox.setChecked(false);
                    dataBinding.checkBox2.setChecked(true);
                    dataBinding.checkBox3.setChecked(false);
                    dataBinding.checkBox4.setChecked(false);
                }else if (type - 1 == 2){
                    dataBinding.checkBox.setChecked(false);
                    dataBinding.checkBox2.setChecked(false);
                    dataBinding.checkBox3.setChecked(true);
                    dataBinding.checkBox4.setChecked(false);
                }else if (type - 1 == 3){
                    dataBinding.checkBox.setChecked(false);
                    dataBinding.checkBox2.setChecked(false);
                    dataBinding.checkBox3.setChecked(false);
                    dataBinding.checkBox4.setChecked(true);
                }
//                ranks.add(String.valueOf(type-1));
                switch (tabPosition){
                    case 0:
                        viewModel.getMemberTeamList(String.valueOf(type-1),0);
                        break;
                    case 1:
                        viewModel.getMemberTeamList(String.valueOf(type-1),1);
                        break;
                }

        }
    }

    private void initRv() {
        mDataBeanList = new ArrayList<>();
        mMemberTeamAdapter = new MemberTeamAdapter(mContext,mDataBeanList);
        dataBinding.recylerView.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_divider));
        dataBinding.recylerView.addItemDecoration(gridItemDecoration);
        dataBinding.recylerView.setAdapter(mMemberTeamAdapter);

        mMemberTeamAdapter.setOnAddFocusClickListener(new MemberTeamAdapter.OnAddFocusClickListener() {
            @Override
            public void OnItemClick(int id, int position) {
                viewModel.addFocusMemberTeam(id,1);
                mPosition = position;
            }
        });

        mMemberTeamAdapter.setOnDeleteFocusClickListener(new MemberTeamAdapter.OnDeleteFocusClickListener() {
            @Override
            public void OnItemClick(int id, int position) {
                viewModel.cancelFocusMemberTeam(id,0);
                mPosition = position;
            }
        });
        mMemberTeamAdapter.setOnRemoveMemberClickListener(new MemberTeamAdapter.OnRemoveMemberClickListener() {
            @Override
            public void OnItemClick(int id, int position) {
                viewModel.deleteMemberTeam(id);
                mPosition = position;
            }
        });
        mMemberTeamAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                jumpMemberDetails(mDataBeanList.get(position).getId(),mDataBeanList.get(position).getAppUser().getNickName());
            }
        });
    }

    //跳转会员详情页面
    public void jumpMemberDetails(int memberId, String memberName) {
        Intent intent = new Intent(BaseApplication.getInstance(), MemberNewDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("MemberName", memberName);
        bundle.putInt("MemberId", memberId);
//        bundle.putBoolean("FocusState", focusState);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.getInstance().getApplicationContext().startActivity(intent);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewModel.memberTeamListLiveData.observe(this, new Observer<List<MemberTeamListResponse.DataBean>>() {
            @Override
            public void onChanged(List<MemberTeamListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recylerView.setVisibility(View.VISIBLE);
                    dataBinding.tvNullData.setVisibility(View.GONE);
                    if (mDataBeanList!=null && mDataBeanList.size()>0){
                        mDataBeanList.clear();
                    }
                    mDataBeanList.addAll(dataBeans);
                    mMemberTeamAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recylerView.setVisibility(View.GONE);
                    dataBinding.tvNullData.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.isAddFocusSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    //更改item的状态，因为返回的true，所以只能手动更改
                    mDataBeanList.get(mPosition).setStatus(1);
                    //刷新单条item数据
                    mMemberTeamAdapter.notifyItemChanged(mPosition);
                    Toast.makeText(mContext,"关注成功",Toast.LENGTH_SHORT).show();
                    mMemberTeamAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(mContext,"关注失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.isCancelFocusSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    switch (tabPosition){
                        case 1:
                            //如果在关注界面，则移除这行
                            mDataBeanList.remove(mPosition);
                            mMemberTeamAdapter.notifyItemChanged(mPosition);
                            mMemberTeamAdapter.notifyDataSetChanged();
                            break;
                        default:
                            //更改item的状态，因为返回的true，所以只能手动更改
                            mDataBeanList.get(mPosition).setStatus(0);
                            //刷新单条item数据
                            mMemberTeamAdapter.notifyItemChanged(mPosition);
                            mMemberTeamAdapter.notifyDataSetChanged();
                            break;
                    }
                }else {
                    Toast.makeText(mContext,"取消关注失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.isRemoveSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    mDataBeanList.remove(mPosition);
                    mMemberTeamAdapter.notifyItemChanged(mPosition);
                    mMemberTeamAdapter.notifyDataSetChanged();
                }
            }
        });

        dataBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tabPosition = 0;
                        dataBinding.checkBox.setChecked(true);
                        dataBinding.checkBox2.setChecked(true);
                        dataBinding.checkBox3.setChecked(true);
                        dataBinding.checkBox4.setChecked(true);
                        if (ranks!=null&&ranks.size()>0){
                            ranks.clear();
                        }
                        ranks.add("0");
                        ranks.add("1");
                        ranks.add("2");
                        ranks.add("3");
                        rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
                        viewModel.getMemberTeamList(rank,0);
                        break;
                    case 1:
                        tabPosition = 1;
                        dataBinding.checkBox.setChecked(true);
                        dataBinding.checkBox2.setChecked(true);
                        dataBinding.checkBox3.setChecked(true);
                        dataBinding.checkBox4.setChecked(true);
                        if (ranks!=null&&ranks.size()>0){
                            ranks.clear();
                        }
                        ranks.add("0");
                        ranks.add("1");
                        ranks.add("2");
                        ranks.add("3");
                        rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
                        viewModel.getMemberTeamList(rank,1);
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

        dataBinding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ranks.add("0");
                }else {
                    ranks.remove("0");
                }
                rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
                switch (tabPosition){
                    case 0:
                        viewModel.getMemberTeamList(rank,0);
                        break;
                    case 1:
                        viewModel.getMemberTeamList(rank,1);
                        break;
                }
                mMemberTeamAdapter.notifyDataSetChanged();
            }
        });
        dataBinding.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ranks.add("1");
                }else {
                    ranks.remove("1");
                }
                rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
                switch (tabPosition){
                    case 0:
                        viewModel.getMemberTeamList(rank,0);
                        break;
                    case 1:
                        viewModel.getMemberTeamList(rank,1);
                        break;
                }
                mMemberTeamAdapter.notifyDataSetChanged();
            }
        });
        dataBinding.checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ranks.add("2");
                }else {
                    ranks.remove("2");
                }
                rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
                switch (tabPosition){
                    case 0:
                        viewModel.getMemberTeamList(rank,0);
                        break;
                    case 1:
                        viewModel.getMemberTeamList(rank,1);
                        break;
                }
                mMemberTeamAdapter.notifyDataSetChanged();
            }
        });
        dataBinding.checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ranks.add("3");
                }else {
                    ranks.remove("3");
                }
                rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
                switch (tabPosition){
                    case 0:
                        viewModel.getMemberTeamList(rank,0);
                        break;
                    case 1:
                        viewModel.getMemberTeamList(rank,1);
                        break;
                }
                mMemberTeamAdapter.notifyDataSetChanged();
            }
        });

        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
                dataBinding.smartRefreshLayout.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
                dataBinding.checkBox.setChecked(true);
                dataBinding.checkBox2.setChecked(true);
                dataBinding.checkBox3.setChecked(true);
                dataBinding.checkBox4.setChecked(true);
                if (ranks!=null && ranks.size()>0){
                    ranks.clear();
                }
                ranks.add("0");
                ranks.add("1");
                ranks.add("2");
                ranks.add("3");
                rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
                switch (tabPosition){
                    case 0:
                        viewModel.getMemberTeamList(rank,0);
                        break;
                    case 1:
                        viewModel.getMemberTeamList(rank,1);
                        break;
                }
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });
        dataBinding.tvSearchVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBinding.tvSearchVip.setVisibility(View.GONE);
                dataBinding.edtSearchVip.setVisibility(View.VISIBLE);
                dataBinding.edtSearchVip.requestFocus();
                dataBinding.edtSearchVip.performClick();
                SoftKeyboardUtils.showSoftInput(MemberManageListActivity.this,dataBinding.edtSearchVip);
            }
        });

        dataBinding.edtSearchVip.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    SoftKeyboardUtils.closeInoutDecorView(MemberManageListActivity.this);
                    dataBinding.tvSearchVip.setVisibility(View.VISIBLE);
                    dataBinding.edtSearchVip.setVisibility(View.GONE);
                    if (tabPosition==0){
                        viewModel.getMemberTeamByName(dataBinding.edtSearchVip.getText().toString(),0);
                    }else {
                        viewModel.getMemberTeamByName(dataBinding.edtSearchVip.getText().toString(),1);
                    }
                    return true;
                }
                return false;//返回true，保留软键盘。false，隐藏软键盘
            }
        });

        viewModel.getMemberTeamByNameListLiveData.observe(this, new Observer<List<MemberTeamListResponse.DataBean>>() {
            @Override
            public void onChanged(List<MemberTeamListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    if (mDataBeanList!=null && mDataBeanList.size()>0){
                        mDataBeanList.clear();
                    }
                    mDataBeanList.addAll(dataBeans);
                    mMemberTeamAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_vip_team;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        SoftKeyboardUtils.closeKeybord(MemberManageListActivity.this);
        return super.onTouchEvent(event);
    }
}
