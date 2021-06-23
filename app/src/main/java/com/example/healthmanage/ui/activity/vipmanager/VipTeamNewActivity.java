package com.example.healthmanage.ui.activity.vipmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.databinding.ActivityVipTeamBinding;
import com.example.healthmanage.ui.activity.memberdetail.MemberNewDetailActivity;
import com.example.healthmanage.ui.activity.vipmanager.adapter.VipAdapter;
import com.example.healthmanage.utils.SoftKeyboardUtils;
import com.example.healthmanage.utils.StatusBarUitils;
import com.example.healthmanage.view.GridItemDecoration;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 会员管理列表
 * 通过首页的查看全部和会员管理点击进入该页面
 * <p>
 * 会员管理页面分为所有会员和重点关注两块
 * <p>
 * 默认展现所有等级会员列表，不进行筛选
 */
public class VipTeamNewActivity extends BaseActivity<ActivityVipTeamBinding,VipTeamViewModel> {
    private int type;
    private LinearLayoutManager manager = new LinearLayoutManager(this);
    private List<MyMemberResponse.DataBean> mDataBeanList;
    private List<MyMemberResponse.DataBean> mPtVipList;
    private List<MyMemberResponse.DataBean> mHighVipList;
    private List<MyMemberResponse.DataBean> mGbVipList;
    private List<MyMemberResponse.DataBean> mSupremeVipList;
    private VipAdapter vipAdapter;
    private Context mContext;
    private String rank;
    private List<String> ranks = new ArrayList<>();
    private int tabPosition;
    private int mPosition;
    private String userId;

    @Override
    protected void initData() {
        mContext = VipTeamNewActivity.this;
        StatusBarUitils.setStatusBar(R.color.white, true, this);
        if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()!=9){
            viewModel.getDoctorTeam();
        }else {
            initRv();
            type = getIntent().getIntExtra("type", 0);
            if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==9){
                dataBinding.tablayout.getTabAt(0).setText("全部会员");
                dataBinding.tvTitle.setText("会员管理");
            }else {
                dataBinding.tablayout.getTabAt(0).setText("全部患者");
                dataBinding.tvTitle.setText("患者管理");
            }
            Log.e("TAG", "type=====: "+type );
            switch (type) {
                case 0://首页点击过重点关注会员,页面进来直接切换重点关注
                    //TODO
                    dataBinding.checkBox.setChecked(true);
                    dataBinding.checkBox2.setChecked(true);
                    dataBinding.checkBox3.setChecked(true);
                    dataBinding.checkBox4.setChecked(true);
                    tabPosition = 1;
                    dataBinding.tablayout.getTabAt(1).select();
                    if (TextUtils.isEmpty(userId)){
                        viewModel.getFocus(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                    }else {
                        viewModel.getFocus(userId);
                    }

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
                    if (TextUtils.isEmpty(userId)){
                        viewModel.getMemberss(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                    }else {
                        viewModel.getMemberss(userId);
                    }
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
                    ranks.add(String.valueOf(type-1));
                    if (TextUtils.isEmpty(userId)){
                        viewModel.getMembersByRank(String.valueOf(type-1),String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                    }else {
                        viewModel.getMembersByRank(String.valueOf(type-1),userId);
                    }
                    break;
            }
        }

    }

    private void initRv() {
        mPtVipList = new ArrayList<>();
        mHighVipList = new ArrayList<>();
        mGbVipList = new ArrayList<>();
        mSupremeVipList = new ArrayList<>();
        mDataBeanList = new ArrayList<>();
        vipAdapter = new VipAdapter(mDataBeanList,VipTeamNewActivity.this);
        dataBinding.recylerView.setLayoutManager(manager);
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_divider));
        dataBinding.recylerView.addItemDecoration(gridItemDecoration);
        dataBinding.recylerView.setAdapter(vipAdapter);

        vipAdapter.setOnAddFocusClickListener(new VipAdapter.OnAddFocusClickListener() {
            @Override
            public void OnItemClick(int id,int position) {
                viewModel.setFocus(String.valueOf(id));
                mPosition = position;
            }
        });
        vipAdapter.setOnDeleteFocusClickListener(new VipAdapter.OnDeleteFocusClickListener() {
            @Override
            public void OnItemClick(int id,int position) {
                Toast.makeText(VipTeamNewActivity.this,"取消关注",Toast.LENGTH_SHORT).show();
                viewModel.cancelFocus(String.valueOf(id));
                mPosition = position;
            }
        });
        vipAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                jumpMemberDetails(mDataBeanList.get(position).getId(),mDataBeanList.get(position).getNickName());
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
    public void initViewListener() {

        viewModel.userId.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                userId = s;
                initRv();
                type = getIntent().getIntExtra("type", 0);
                if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==9){
                    dataBinding.tablayout.getTabAt(0).setText("全部会员");
                    dataBinding.tvTitle.setText("会员管理");
                }else {
                    dataBinding.tablayout.getTabAt(0).setText("全部患者");
                    dataBinding.tvTitle.setText("患者管理");
                }
                Log.e("TAG", "type=====: "+type );
                switch (type) {
                    case 0://首页点击过重点关注会员,页面进来直接切换重点关注
                        //TODO
                        dataBinding.checkBox.setChecked(true);
                        dataBinding.checkBox2.setChecked(true);
                        dataBinding.checkBox3.setChecked(true);
                        dataBinding.checkBox4.setChecked(true);
                        tabPosition = 1;
                        dataBinding.tablayout.getTabAt(1).select();
                        if (TextUtils.isEmpty(userId)){
                            viewModel.getFocus(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                        }else {
                            viewModel.getFocus(userId);
                        }

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
                        if (TextUtils.isEmpty(userId)){
                            viewModel.getMemberss(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                        }else {
                            viewModel.getMemberss(userId);
                        }
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
                        ranks.add(String.valueOf(type-1));
                        if (TextUtils.isEmpty(userId)){
                            viewModel.getMembersByRank(String.valueOf(type-1),String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                        }else {
                            viewModel.getMembersByRank(String.valueOf(type-1),userId);
                        }
                        break;
                }
            }
        });
        viewModel.mBooleanMutableLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                //刷新关注
                if (aBoolean){
                    //更改item的状态，因为返回的true，所以只能手动更改
                    mDataBeanList.get(mPosition).setFollowStatus(1);
                    //刷新单条item数据
                    vipAdapter.notifyItemChanged(mPosition);
                    Toast.makeText(VipTeamNewActivity.this,"关注成功",Toast.LENGTH_SHORT).show();
                    vipAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(VipTeamNewActivity.this,"关注失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.mBooleanDeleteMutableLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    //如果在关注界面，则移除这行
                    if (tabPosition == 1){
                        mDataBeanList.remove(mPosition);
                        vipAdapter.notifyItemRemoved(mPosition);
                    }else {
                        mDataBeanList.get(mPosition).setFollowStatus(0);
                        vipAdapter.notifyItemChanged(mPosition);
                    }
                    vipAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(VipTeamNewActivity.this,"取消关注失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
        dataBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        dataBinding.checkBox.setChecked(true);
                        dataBinding.checkBox2.setChecked(true);
                        dataBinding.checkBox3.setChecked(true);
                        dataBinding.checkBox4.setChecked(true);
                        tabPosition = 0;
                        if (ranks!=null&&ranks.size()>0){
                            ranks.clear();
                        }
                        ranks.add("0");
                        ranks.add("1");
                        ranks.add("2");
                        ranks.add("3");
                        if (TextUtils.isEmpty(userId)){
                            viewModel.getMemberss(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                        }else {
                            viewModel.getMemberss(userId);
                        }
                        break;
                    case 1:
                        dataBinding.checkBox.setChecked(true);
                        dataBinding.checkBox2.setChecked(true);
                        dataBinding.checkBox3.setChecked(true);
                        dataBinding.checkBox4.setChecked(true);
                        tabPosition = 1;
                        if (ranks!=null&&ranks.size()>0){
                            ranks.clear();
                        }
                        ranks.add("0");
                        ranks.add("1");
                        ranks.add("2");
                        ranks.add("3");
                        if (TextUtils.isEmpty(userId)){
                            viewModel.getFocus(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                        }else {
                            viewModel.getFocus(userId);
                        }
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

        dataBinding.backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewModel.mListMutableLiveData.observe(this, new Observer<List<MyMemberResponse.DataBean>>() {
            @Override
            public void onChanged(List<MyMemberResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recylerView.setVisibility(View.VISIBLE);
                    dataBinding.tvNullData.setVisibility(View.GONE);
                    if (mDataBeanList!=null && mDataBeanList.size()>0){
                        mDataBeanList.clear();
                    }
                    mDataBeanList.addAll(dataBeans);
                    vipAdapter.setNewData(mDataBeanList);
                }else {
                    if (mDataBeanList!=null && mDataBeanList.size()>0){
                        mDataBeanList.clear();
                    }
                    dataBinding.recylerView.setVisibility(View.GONE);
                    dataBinding.tvNullData.setVisibility(View.VISIBLE);
                }

            }
        });
        dataBinding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    if (ranks!=null&&ranks.size()>0){
                        ranks.remove("0");
                    }
                    //mDataBeanList移除普通会员
                    mPtVipList = mDataBeanList.stream()
                            .filter(listBean -> listBean.getRank()==0).collect(Collectors.toList());
                    if (mPtVipList!=null && mPtVipList.size()>0){
                        mDataBeanList.removeAll(mPtVipList);
                    }
                    if (mDataBeanList!=null && mDataBeanList.size()>0){
                        dataBinding.recylerView.setVisibility(View.VISIBLE);
                        dataBinding.tvNullData.setVisibility(View.GONE);
                    }else {
                        dataBinding.recylerView.setVisibility(View.GONE);
                        dataBinding.tvNullData.setVisibility(View.VISIBLE);
                    }
                }else {
                    //mDataBeanList显示普通会员
                    if (mPtVipList!=null && mPtVipList.size()>0){
                        ranks.add("0");
                        mDataBeanList.addAll(mPtVipList);
                        dataBinding.recylerView.setVisibility(View.VISIBLE);
                        dataBinding.tvNullData.setVisibility(View.GONE);
                    }else {
                        if (tabPosition == 0){
                            ranks.add("0");
                            rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
                            if (TextUtils.isEmpty(userId)){
                                viewModel.getMembersByRank(rank,String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                            }else {
                                viewModel.getMembersByRank(rank,userId);
                            }
                        }else {
                            if (TextUtils.isEmpty(userId)){
                                viewModel.getFocus(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                            }else {
                                viewModel.getFocus(userId);
                            }
                        }
                    }
                }
                vipAdapter.notifyDataSetChanged();
            }
        });
        dataBinding.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    if (ranks!=null&&ranks.size()>0){
                        ranks.remove("1");
                    }
                    //mDataBeanList移除高级会员
                    mHighVipList = mDataBeanList.stream()
                            .filter(listBean -> listBean.getRank()==1).collect(Collectors.toList());
                    if (mHighVipList!=null && mHighVipList.size()>0){
                        mDataBeanList.removeAll(mHighVipList);
                    }
                    if (mDataBeanList!=null && mDataBeanList.size()>0){
                        dataBinding.recylerView.setVisibility(View.VISIBLE);
                        dataBinding.tvNullData.setVisibility(View.GONE);
                    }else {
                        dataBinding.recylerView.setVisibility(View.GONE);
                        dataBinding.tvNullData.setVisibility(View.VISIBLE);
                    }
                    vipAdapter.notifyDataSetChanged();
                }else {
                    //mDataBeanList显示高级会员
                    //mDataBeanList显示高级会员
                    if (mHighVipList!=null && mHighVipList.size()>0){
                        ranks.add("1");
                        mDataBeanList.addAll(mHighVipList);
                        dataBinding.recylerView.setVisibility(View.VISIBLE);
                        dataBinding.tvNullData.setVisibility(View.GONE);
                    }else {
                        if (tabPosition == 0){
                            ranks.add("1");
                            rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
                            if (TextUtils.isEmpty(userId)){
                                viewModel.getMembersByRank(rank,String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                            }else {
                                viewModel.getMembersByRank(rank,userId);
                            }
                        }else {
                            if (TextUtils.isEmpty(userId)){
                                viewModel.getFocus(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                            }else {
                                viewModel.getFocus(userId);
                            }
                        }
                    }
                    vipAdapter.notifyDataSetChanged();
                }
            }
        });
        dataBinding.checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    if (ranks!=null && ranks.size()>0){
                        ranks.remove("2");
                    }
                    //mDataBeanList移除贵宾会员
                    mGbVipList = mDataBeanList.stream()
                            .filter(listBean -> listBean.getRank()==2).collect(Collectors.toList());
                    if (mGbVipList!=null && mGbVipList.size()>0 ){
                        mDataBeanList.removeAll(mGbVipList);
                    }
                    if (mDataBeanList!=null && mDataBeanList.size()>0){
                        dataBinding.recylerView.setVisibility(View.VISIBLE);
                        dataBinding.tvNullData.setVisibility(View.GONE);
                    }else {
                        dataBinding.recylerView.setVisibility(View.GONE);
                        dataBinding.tvNullData.setVisibility(View.VISIBLE);
                    }
                }else {
                    //mDataBeanList显示贵宾会员
                    if (mGbVipList!=null && mGbVipList.size()>0){
                        ranks.add("2");
                        mDataBeanList.addAll(mGbVipList);
                        dataBinding.recylerView.setVisibility(View.VISIBLE);
                        dataBinding.tvNullData.setVisibility(View.GONE);
                    }else {
                        if (tabPosition == 0){
                            ranks.add("2");
                            rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
                            if (TextUtils.isEmpty(userId)){
                                viewModel.getMembersByRank(rank,String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                            }else {
                                viewModel.getMembersByRank(rank,userId);
                            }
                        }else {
                            if (TextUtils.isEmpty(userId)){
                                viewModel.getFocus(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                            }else {
                                viewModel.getFocus(userId);
                            }
                        }

                    }
                }
                vipAdapter.notifyDataSetChanged();
            }
        });
        dataBinding.checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    if (ranks!=null && ranks.size()>0){
                        ranks.remove("3");
                    }
                    //mDataBeanList移除至尊会员
                    mSupremeVipList = mDataBeanList.stream()
                            .filter(listBean -> listBean.getRank()==3).collect(Collectors.toList());
                    if (mSupremeVipList!=null && mSupremeVipList.size()>0){
                        mDataBeanList.removeAll(mSupremeVipList);
                    }
                    if (mDataBeanList!=null && mDataBeanList.size()>0){
                        dataBinding.recylerView.setVisibility(View.VISIBLE);
                        dataBinding.tvNullData.setVisibility(View.GONE);
                    }else {
                        dataBinding.recylerView.setVisibility(View.GONE);
                        dataBinding.tvNullData.setVisibility(View.VISIBLE);
                    }
                }else {
                    //mDataBeanList显示至尊会员
                    if (mSupremeVipList!=null && mSupremeVipList.size()>0){
                        ranks.add("3");
                        mDataBeanList.addAll(mSupremeVipList);
                        dataBinding.recylerView.setVisibility(View.VISIBLE);
                        dataBinding.tvNullData.setVisibility(View.GONE);
                    }else {
                        if (tabPosition == 0){
                            ranks.add("3");
                            rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
                            if (TextUtils.isEmpty(userId)){
                                viewModel.getMembersByRank(rank,String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                            }else {
                                viewModel.getMembersByRank(rank,userId);
                            }
                        }else {
                            if (TextUtils.isEmpty(userId)){
                                viewModel.getFocus(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                            }else {
                                viewModel.getFocus(userId);
                            }
                        }
                    }
                }
                vipAdapter.notifyDataSetChanged();
            }
        });
        dataBinding.tvSearchVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBinding.tvSearchVip.setVisibility(View.GONE);
                dataBinding.edtSearchVip.setVisibility(View.VISIBLE);
                dataBinding.edtSearchVip.requestFocus();
                dataBinding.edtSearchVip.performClick();
                SoftKeyboardUtils.showSoftInput(VipTeamNewActivity.this,dataBinding.edtSearchVip);
            }
        });
        dataBinding.edtSearchVip.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    SoftKeyboardUtils.closeInoutDecorView(VipTeamNewActivity.this);
                    dataBinding.tvSearchVip.setVisibility(View.VISIBLE);
                    dataBinding.edtSearchVip.setVisibility(View.GONE);
//                    viewModel.getVipByPhone(dataBinding.edtSearchVip.getText().toString());
//                    Toast.makeText(mContext,dataBinding.edtSearchVip.getText().toString(),Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;//返回true，保留软键盘。false，隐藏软键盘
            }
        });
        viewModel.mListSearchMutableLiveData.observe(this, new Observer<MyMemberResponse.DataBean>() {
            @Override
            public void onChanged(MyMemberResponse.DataBean dataBean) {
                if (dataBean != null){
                    mDataBeanList.clear();
                    mDataBeanList.add(dataBean);
                    vipAdapter.notifyDataSetChanged();
//                    mDataBeanList.add(dataBean);
                }
            }
        });
        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                if (vipAdapter != null){
//                    vipAdapter.addData(mDataBeanList);
                dataBinding.smartRefreshLayout.finishLoadMore(200);
//                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (tabPosition == 0){
                    if (TextUtils.isEmpty(userId)){
                        viewModel.getMemberss(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                    }else {
                        viewModel.getMemberss(userId);
                    }
                }else {
                    if (TextUtils.isEmpty(userId)){
                        viewModel.getFocus(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                    }else {
                        viewModel.getFocus(userId);
                    }
                }
                dataBinding.checkBox.setChecked(true);
                dataBinding.checkBox2.setChecked(true);
                dataBinding.checkBox3.setChecked(true);
                dataBinding.checkBox4.setChecked(true);
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
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
        SoftKeyboardUtils.closeKeybord(VipTeamNewActivity.this);
        return super.onTouchEvent(event);
    }
}
