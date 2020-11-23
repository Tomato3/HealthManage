package com.example.healthmanage.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentHomeBinding;
import com.example.healthmanage.ui.activity.consultationinfo.ConsultationInfoActivity;
import com.example.healthmanage.ui.activity.doctorhall.DoctorHallActivity;
import com.example.healthmanage.ui.activity.memberlist.MemberListActivity;
import com.example.healthmanage.ui.activity.mytask.MyTaskActivity;
import com.example.healthmanage.ui.activity.serviceplan.ServicePlanActivity;
import com.example.healthmanage.view.MyFocusRecyclerView;
import com.example.healthmanage.view.MyMemberRecyclerView;
import com.example.healthmanage.widget.DropdownBar;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements View.OnClickListener {

    private DropdownBar dropdownBarMyFocus, dropdownBarMyMember, dropdownBarServiceNavigation;
    private BaseAdapter myMemberAdapter, myFocusAdapter;
    private Bundle bundle;

    @Override
    protected void initData() {

        viewModel.loadMyFocus();
        viewModel.loadMyMembers();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

        myMemberAdapter = new BaseAdapter(getActivity(), null,
                R.layout.recycler_view_item_my_member, BR.MyMemberRecyclerView);
        myFocusAdapter = new BaseAdapter(getActivity(), null,
                R.layout.recycler_view_item_my_focus, BR.MyFocusRecyclerView);

        dataBinding.recyclerviewMyMember.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerviewMyFocus.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerviewMyMember.setAdapter(myMemberAdapter);
        dataBinding.recyclerviewMyFocus.setAdapter(myFocusAdapter);

        viewModel.myFocusMutableLiveData.observe(this, new Observer<List<MyFocusRecyclerView>>() {
            @Override
            public void onChanged(List<MyFocusRecyclerView> myFocusRecyclerViews) {
                myFocusAdapter.setRecyclerViewList(myFocusRecyclerViews);
                myFocusAdapter.notifyDataSetChanged();
            }
        });

        viewModel.myMemberMutableLiveData.observe(this, new Observer<List<MyMemberRecyclerView>>() {
            @Override
            public void onChanged(List<MyMemberRecyclerView> myMemberRecyclerViewList) {
                myMemberAdapter.setRecyclerViewList(myMemberRecyclerViewList);
                myMemberAdapter.notifyDataSetChanged();
            }
        });

        dataBinding.dropdownBarMyMember.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt("focusOrMember", 0);
                startActivity(MemberListActivity.class, bundle);
            }
        });

        dataBinding.dropdownBarMyFocus.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt("focusOrMember", 1);
                startActivity(MemberListActivity.class, bundle);
            }
        });

        LiveEventBus.get("Refresh", Boolean.class).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean o) {
                viewModel.loadMyMembers();
                viewModel.loadMyFocus();
            }
        });

        dataBinding.tvMyTask.setOnClickListener(this::onClick);
        dataBinding.tvPlan.setOnClickListener(this::onClick);
        dataBinding.tvFamousDoctors.setOnClickListener(this::onClick);
        dataBinding.tvMessage.setOnClickListener(this::onClick);
    }


    @Override
    protected void initView() {
        dropdownBarMyFocus = new DropdownBar("我的关注", true, false, false);
        dropdownBarMyMember = new DropdownBar("我的会员", true, false, false);
        dropdownBarServiceNavigation = new DropdownBar("服务导航", false, false, false);
        ArrayList<DropdownBar> dropdownBarArrayList = new ArrayList<>();
        dropdownBarArrayList.add(dropdownBarMyFocus);
        dropdownBarArrayList.add(dropdownBarMyMember);
        dropdownBarArrayList.add(dropdownBarServiceNavigation);
        viewModel.dropdownBarLists.setValue(dropdownBarArrayList);
    }

    @Override
    protected void initViewModel() {
    }

    @Override
    protected void initObserver() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_my_task:
                startActivity(MyTaskActivity.class);
                break;
            case R.id.tv_plan:
                startActivity(ServicePlanActivity.class);
                break;
            case R.id.tv_famous_doctors:
                startActivity(DoctorHallActivity.class);
                break;
            case R.id.tv_message:
                startActivity(ConsultationInfoActivity.class);
                break;
        }
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }
}
