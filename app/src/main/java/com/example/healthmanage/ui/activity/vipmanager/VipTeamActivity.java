package com.example.healthmanage.ui.activity.vipmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseRecyclerAdapter;
import com.example.healthmanage.base.RecyclerViewHolder;
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.databinding.ActivityVipTeamBinding;
import com.example.healthmanage.ui.activity.memberdetail.MemberDetailActivity;
import com.example.healthmanage.utils.StatusBarUitils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过首页的查看全部和会员管理点击进入该页面
 * <p>
 * 会员管理页面分为所有会员和重点关注两块
 * <p>
 * 默认展现所有等级会员列表，不进行筛选
 */
public class VipTeamActivity extends BaseActivity<ActivityVipTeamBinding, VipTeamViewModel> {
    //type是首页传进来的类型值，需要-1，得到0 1 2分别是高级 贵宾 至尊会员用来请求接口
    private int type;
    private static final String TAG = "VipTeamActivity";
    private LinearLayoutManager manager = new LinearLayoutManager(this);
    private List<MyMemberResponse.DataBean> mDataBeanList;
    private BaseRecyclerAdapter<MyMemberResponse.DataBean> adapter;

    @Override
    protected void initData() {
        StatusBarUitils.setStatusBar(R.color.white, true, this);
        initRv();
        type = getIntent().getIntExtra("type", 0);

        Log.e(TAG, "会员类型: " + type);

        switch (type) {
            case 0://首页点击过重点关注会员,页面进来直接切换重点关注
                //TODO
                dataBinding.checkBox.setChecked(true);
                dataBinding.checkBox2.setChecked(true);
                dataBinding.checkBox3.setChecked(true);
                dataBinding.checkBox4.setChecked(true);
                dataBinding.tablayout.getTabAt(1).select();
                viewModel.getFocus();
                break;
            case 99://首页点击过会员管理
                viewModel.getMemberss();//查询所有会员
                break;
            default://默认则按会员等级查询type为1 2 3
                viewModel.getMembersByRank(type - 1);
                break;
        }
    }

    private void initRv() {
        mDataBeanList = new ArrayList<>();
        //初始化adapter
        adapter = new BaseRecyclerAdapter<MyMemberResponse.DataBean>(this, mDataBeanList) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_vipmanager;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, MyMemberResponse.DataBean item) {
                holder.setText(R.id.vip_name_tv, item.getNickName());
                Glide.with(mContext)
                        .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=259981538,2780056385&fm=11&gp=0.jpg")
                        .apply(new RequestOptions().circleCrop())
                        .into(holder.getImageView(R.id.vip_ava_img));
                //设为特别关注的点击事件
                holder.setClickListener(R.id.vip_isfocus_bt, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.setFocus(String.valueOf(item.getId()));
                    }
                });
                //item的点击事件
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e(TAG, "onClickItem: ====" + position);
                        jumpMemberDetails(item.getId(), item.getNickName());
                    }
                });
            }
        };
        dataBinding.recylerView.setLayoutManager(manager);
        dataBinding.recylerView.setAdapter(adapter);
    }

    //跳转会员详情页面
    public void jumpMemberDetails(int memberId, String memberName) {
        Intent intent = new Intent(BaseApplication.getInstance(), MemberDetailActivity.class);
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
        viewModel.mBooleanMutableLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });
        dataBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        viewModel.getMemberss();
                        break;
                    case 1:
                        viewModel.getFocus();
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
                mDataBeanList = dataBeans;
                adapter.setmItems(dataBeans);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_vip_team;
    }
}