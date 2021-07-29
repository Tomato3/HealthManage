package com.example.healthmanage.ui.activity.referral;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityChooseMemberBinding;
import com.example.healthmanage.ui.activity.referral.adapter.ChooseMemberAdapter;
import com.example.healthmanage.ui.activity.vipmanager.MemberTeamViewModel;
import com.example.healthmanage.ui.activity.vipmanager.response.MemberTeamListResponse;
import com.example.healthmanage.utils.SoftKeyboardUtils;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.widget.TitleToolBar;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 选择会员管理列表
 * 通过首页的查看全部和会员管理点击进入该页面
 * <p>
 * 会员管理页面分为所有会员和重点关注两块
 * <p>
 * 默认展现所有等级会员列表，不进行筛选
 */
public class ChooseMemberActivity extends BaseActivity<ActivityChooseMemberBinding, MemberTeamViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private Context context;
    private LinearLayoutManager manager = new LinearLayoutManager(this);
    private List<MemberTeamListResponse.DataBean> mDataBeanList;
    private ChooseMemberAdapter chooseMemberAdapter;
    private String rank;
    private List<String> ranks = new ArrayList<>();
    private int tabPosition;
    private int mPosition = -1;
    private String transferId;
    private String memberName;
    private String memberStatus;

    @Override
    protected void initData() {
        context = ChooseMemberActivity.this;
        titleToolBar.setTitle("患者管理");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        initRv();
        if (ranks!=null && ranks.size()>0){
            ranks.clear();
        }
        ranks.add("0");
        ranks.add("1");
        ranks.add("2");
        ranks.add("3");
        rank = ranks.stream().map(Object::toString).collect(Collectors.joining(","));
        viewModel.getMemberTeamList(rank,0);
    }

    private void initRv() {
        mDataBeanList = new ArrayList<>();
        chooseMemberAdapter = new ChooseMemberAdapter(context,mDataBeanList);
        dataBinding.recylerView.setLayoutManager(manager);
        dataBinding.recylerView.setAdapter(chooseMemberAdapter);

        chooseMemberAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.btn_select_member:
                        if (mPosition != -1 && mPosition != position){
                            Log.e("mPosition+error==========",mPosition+"");
                            Log.e("Position+error==========",position+"");
                            mDataBeanList.get(mPosition).setSelect(false);
                        }
                        mDataBeanList.get(position).setSelect(true);
                        mPosition = position;
                        chooseMemberAdapter.notifyDataSetChanged();
                        transferId = String.valueOf(mDataBeanList.get(position).getId());
                        memberName = mDataBeanList.get(position).getAppUser().getNickName();
                        if (mDataBeanList.get(position).getAppUser().getRank()==0){
                            memberStatus = "普通会员";
                        }else if (mDataBeanList.get(position).getAppUser().getRank()==1){
                            memberStatus = "高级会员";
                        }else if (mDataBeanList.get(position).getAppUser().getRank()==2){
                            memberStatus = "贵宾会员";
                        }else if (mDataBeanList.get(position).getAppUser().getRank()==3){
                            memberStatus = "至尊会员";
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
//        viewModel.userId.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                userId = s;
//                initRv();
//                ranks.add("0");
//                ranks.add("1");
//                ranks.add("2");
//                ranks.add("3");
//                if (TextUtils.isEmpty(userId)){
//                    viewModel.getMemberss(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
//                }else {
//                    viewModel.getMemberss(userId);
//                }
//            }
//        });

        dataBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabPosition = 0;
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
                        viewModel.getMemberTeamList(rank,0);
                        mPosition = -1;
                        transferId = null;
                        memberName = null;
                        memberStatus=null;
                        break;
                    case 1:
                        tabPosition = 1;
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
                        viewModel.getMemberTeamList(rank,1);
                        mPosition = -1;
                        transferId = null;
                        memberName = null;
                        memberStatus=null;
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

        viewModel.memberTeamListLiveData.observe(this, new Observer<List<MemberTeamListResponse.DataBean>>() {
            @Override
            public void onChanged(List<MemberTeamListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recylerView.setVisibility(View.VISIBLE);
                    dataBinding.tvNullData.setVisibility(View.GONE);
                    if (mDataBeanList!=null &&  mDataBeanList.size()>0){
                        mDataBeanList.clear();
                    }
                    mDataBeanList.addAll(dataBeans);
                    chooseMemberAdapter.setNewData(mDataBeanList);
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
                chooseMemberAdapter.notifyDataSetChanged();
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
                chooseMemberAdapter.notifyDataSetChanged();
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
                chooseMemberAdapter.notifyDataSetChanged();
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
                chooseMemberAdapter.notifyDataSetChanged();
            }
        });
        dataBinding.tvSearchVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBinding.tvSearchVip.setVisibility(View.GONE);
                dataBinding.edtSearchVip.setVisibility(View.VISIBLE);
                dataBinding.edtSearchVip.requestFocus();
                dataBinding.edtSearchVip.performClick();
                SoftKeyboardUtils.showSoftInput(context,dataBinding.edtSearchVip);
            }
        });

        dataBinding.edtSearchVip.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    SoftKeyboardUtils.closeInoutDecorView(ChooseMemberActivity.this);
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
                    chooseMemberAdapter.notifyDataSetChanged();
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
                mPosition = -1;
                transferId = null;
                memberName=null;
                memberStatus=null;
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

        dataBinding.btnQueryChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (memberName==null){
                    ToastUtil.showShort("请选择转诊患者");
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("memberName",memberName);
                    intent.putExtra("memberId",transferId);
                    intent.putExtra("rank",memberStatus);
                    setResult(RESULT_OK,intent);
                    finish();
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
        return R.layout.activity_choose_member;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        SoftKeyboardUtils.closeKeybord(ChooseMemberActivity.this);
        return super.onTouchEvent(event);
    }
}
