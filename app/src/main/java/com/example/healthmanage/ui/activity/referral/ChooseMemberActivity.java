package com.example.healthmanage.ui.activity.referral;

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
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.databinding.ActivityChooseMemberBinding;
import com.example.healthmanage.ui.activity.consultation.ConsultationActivity;
import com.example.healthmanage.ui.activity.referral.adapter.ChooseMemberAdapter;
import com.example.healthmanage.ui.activity.vipmanager.VipTeamNewActivity;
import com.example.healthmanage.ui.activity.vipmanager.VipTeamViewModel;
import com.example.healthmanage.ui.activity.vipmanager.adapter.VipAdapter;
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
public class ChooseMemberActivity extends BaseActivity<ActivityChooseMemberBinding, VipTeamViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private Context context;
    private LinearLayoutManager manager = new LinearLayoutManager(this);
    private List<MyMemberResponse.DataBean> mDataBeanList;
    private List<MyMemberResponse.DataBean> mPtVipList;
    private List<MyMemberResponse.DataBean> mHighVipList;
    private List<MyMemberResponse.DataBean> mGbVipList;
    private List<MyMemberResponse.DataBean> mSupremeVipList;
    private ChooseMemberAdapter chooseMemberAdapter;
    private String rank;
    private List<String> ranks = new ArrayList<>();
    private int tabPosition;
    private int mPosition = -1;
    private String transferId;
    private String memberName;
    private String memberStatus;
    private String userId;

    @Override
    protected void initData() {
        context = ChooseMemberActivity.this;
        titleToolBar.setTitle("患者管理");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==11){
            viewModel.getDoctorTeam();
        }else {
            initRv();
            ranks.add("0");
            ranks.add("1");
            ranks.add("2");
            ranks.add("3");
            if (TextUtils.isEmpty(userId)){
                viewModel.getMemberss(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
            }else {
                viewModel.getMemberss(userId);
            }
        }

    }

    private void initRv() {
        mPtVipList = new ArrayList<>();
        mHighVipList = new ArrayList<>();
        mGbVipList = new ArrayList<>();
        mSupremeVipList = new ArrayList<>();
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
                        memberName = mDataBeanList.get(position).getNickName();
                        if (mDataBeanList.get(position).getRank()==0){
                            memberStatus = "普通会员";
                        }else if (mDataBeanList.get(position).getRank()==1){
                            memberStatus = "高级会员";
                        }else if (mDataBeanList.get(position).getRank()==2){
                            memberStatus = "贵宾会员";
                        }else if (mDataBeanList.get(position).getRank()==3){
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
        viewModel.userId.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                userId = s;
                initRv();
                ranks.add("0");
                ranks.add("1");
                ranks.add("2");
                ranks.add("3");
                if (TextUtils.isEmpty(userId)){
                    viewModel.getMemberss(String.valueOf(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()));
                }else {
                    viewModel.getMemberss(userId);
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
                        if (ranks!=null){
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
                        mPosition = -1;
                        transferId = null;
                        memberName = null;
                        memberStatus=null;
                        break;
                    case 1:
                        dataBinding.checkBox.setChecked(true);
                        dataBinding.checkBox2.setChecked(true);
                        dataBinding.checkBox3.setChecked(true);
                        dataBinding.checkBox4.setChecked(true);
                        tabPosition = 1;
                        if (ranks!=null){
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

        viewModel.mListMutableLiveData.observe(this, new Observer<List<MyMemberResponse.DataBean>>() {
            @Override
            public void onChanged(List<MyMemberResponse.DataBean> dataBeans) {
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
                if (!isChecked){
                    if (ranks!=null && ranks.size()>0){
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
                        if (tabPosition==0){
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
                chooseMemberAdapter.notifyDataSetChanged();
            }
        });
        dataBinding.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    if (ranks!=null && ranks.size()>0){
                        ranks.remove("1");
                    }
                    //mDataBeanList移除高级会员
                    mHighVipList = mDataBeanList.stream()
                            .filter(listBean -> listBean.getRank()==1).collect(Collectors.toList());
                    if (mHighVipList!=null &&  mHighVipList.size()>0){
                        mDataBeanList.removeAll(mHighVipList);
                    }
                    if (mDataBeanList!=null && mDataBeanList.size()>0){
                        dataBinding.recylerView.setVisibility(View.VISIBLE);
                        dataBinding.tvNullData.setVisibility(View.GONE);
                    }else {
                        dataBinding.recylerView.setVisibility(View.GONE);
                        dataBinding.tvNullData.setVisibility(View.VISIBLE);
                    }
                }else {
                    //mDataBeanList显示高级会员
                    //mDataBeanList显示高级会员
                    if (mHighVipList!=null && mHighVipList.size()>0){
                        ranks.add("1");
                        mDataBeanList.addAll(mHighVipList);
                        dataBinding.recylerView.setVisibility(View.VISIBLE);
                        dataBinding.tvNullData.setVisibility(View.GONE);
                    }else {
                        if (tabPosition==0){
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
                }
                chooseMemberAdapter.notifyDataSetChanged();
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
                    if (mGbVipList !=null && mGbVipList.size()>0){
                        ranks.add("2");
                        mDataBeanList.addAll(mGbVipList);
                        dataBinding.recylerView.setVisibility(View.VISIBLE);
                        dataBinding.tvNullData.setVisibility(View.GONE);
                    }else {
                        if (tabPosition==0){
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
//                    mDataBeanList.addAll(mGbVipList);
                }
                chooseMemberAdapter.notifyDataSetChanged();
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
                        if (tabPosition==0){
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
                    viewModel.getVipByPhone(dataBinding.edtSearchVip.getText().toString());
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
                    if (mDataBeanList!=null && mDataBeanList.size()>0){
                        mDataBeanList.clear();
                    }
                    mDataBeanList.add(dataBean);
                    chooseMemberAdapter.notifyDataSetChanged();
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
                mPosition = -1;
                transferId = null;
                memberName=null;
                memberStatus=null;
                dataBinding.checkBox.setChecked(true);
                dataBinding.checkBox2.setChecked(true);
                dataBinding.checkBox3.setChecked(true);
                dataBinding.checkBox4.setChecked(true);
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
