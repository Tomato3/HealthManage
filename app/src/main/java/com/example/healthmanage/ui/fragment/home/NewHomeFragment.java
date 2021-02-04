package com.example.healthmanage.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.databinding.FragmentNewHomeBinding;
import com.example.healthmanage.ui.activity.vipmanager.VipTeamActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public
class NewHomeFragment extends BaseFragment<FragmentNewHomeBinding, NewHomeFragmentViewModel> {
    private View includeVVip;
    private RecyclerView mRecyclerView;
    private ImageView ava;
    private TextView vipType;
    private MyMemberResponse mMyMemberResponse;
    private List<MyMemberResponse.DataBean> mDataBeanList;
    private HomeVipAdapter adapter;
    private static final String TAG = "NewHomeFragment";
    //type是为了从会员管理页面操作后返回到本页面，进行一次数据请求刷新
    private int type;

    @Override
    public void onResume() {
        super.onResume();
        if (type == 0) {
            viewModel.myFocus(String.valueOf(BaseApplication.getUserInfoBean().getSysId()));
        } else {
            viewModel.getVip(String.valueOf(BaseApplication.getUserInfoBean().getSysId()), type - 1);
        }
    }

    @Override
    protected void initListener() {

        dataBinding.includeVvip.findViewById(R.id.allofvip_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VipTeamActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });
        dataBinding.includeHomeChoose.findViewById(R.id.huiyuanguanli).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VipTeamActivity.class);
                intent.putExtra("type", 99);
                startActivity(intent);
            }
        });
        dataBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                type = tab.getPosition();
                if (mDataBeanList != null) {
                    mDataBeanList.clear();
                }
                vipType = dataBinding.includeVvip.findViewById(R.id.vip_type_tv);
                vipType.setText(tab.getText());
                Log.e("tab.getposition", "onTabSelected: " + tab.getPosition());
                if (tab.getPosition() == 0) {//如果是0，查询我的关注接口数据
                    viewModel.myFocus(String.valueOf(BaseApplication.getUserInfoBean().getSysId()));
                } else {//如果是其他的1 2 3   则查询不同等级的会员数据  0 1 2
                    viewModel.getVip(String.valueOf(BaseApplication.getUserInfoBean().getSysId()), tab.getPosition() - 1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    private void initRV() {
        mDataBeanList = new ArrayList<>();
        adapter = new HomeVipAdapter(mDataBeanList, getContext(), R.layout.item_home_vvip);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initAdapter() {
        initRV();
    }

    @Override
    protected void initView() {
        includeVVip = (View) getActivity().findViewById(R.id.include_vvip);
        mRecyclerView = includeVVip.findViewById(R.id.recyler_view);
        ava = getActivity().findViewById(R.id.avatar_img);
        Glide.with(getContext())
                .load("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1629493508,3312904397&fm=26&gp=0.jpg")
                .apply(new RequestOptions().circleCrop())
                .into(ava);

        vipType = dataBinding.includeVvip.findViewById(R.id.vip_type_tv);
        vipType.setText("重点关注会员");
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void initObserver() {
        viewModel.mMyMemberResponseMutableLiveData.observe(this, new Observer<List<MyMemberResponse.DataBean>>() {
            @Override
            public void onChanged(List<MyMemberResponse.DataBean> dataBeans) {
                if (dataBeans == null) {
                    dataBinding.includeVvip.findViewById(R.id.nodata_tv).setVisibility(View.VISIBLE);
                    dataBinding.includeVvip.findViewById(R.id.recyler_view).setVisibility(View.GONE);
                } else {
                    dataBinding.includeVvip.findViewById(R.id.nodata_tv).setVisibility(View.GONE);
                    dataBinding.includeVvip.findViewById(R.id.recyler_view).setVisibility(View.VISIBLE);
                }
                NewHomeFragment.this.mDataBeanList = dataBeans;
                adapter.setList(dataBeans);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_new_home;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}