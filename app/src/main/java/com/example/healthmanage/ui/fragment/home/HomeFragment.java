package com.example.healthmanage.ui.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentHomeBinding;
import com.example.healthmanage.ui.activity.memberlist.MemberListActivity;
import com.example.healthmanage.ui.activity.mytask.MyTaskActivity;
import com.example.healthmanage.view.MyFocusRecyclerView;
import com.example.healthmanage.view.MyMemberRecyclerView;
import com.example.healthmanage.widget.DropdownBar;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.healthmanage.utils.Constants.HTAG;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements View.OnClickListener {

    private DropdownBar dropdownBarMyFocus;
    private BaseAdapter myMemberAdapter, myFocusAdapter;
    Bundle bundle;

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected void initAdapter() {
        myMemberAdapter = new BaseAdapter(getActivity(), null,
                R.layout.recycler_view_item_my_member, BR.MyMemberRecyclerView);
        myFocusAdapter = new BaseAdapter(getActivity(), null,
                R.layout.recycler_view_item_my_focus, BR.MyFocusRecyclerView);
        dataBinding.recyclerviewMyMember.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerviewMyFocus.setLayoutManager(new LinearLayoutManager(getActivity()));


        viewModel.myFocusMutableLiveData.observe(this, new Observer<List<MyFocusRecyclerView>>() {
            @Override
            public void onChanged(List<MyFocusRecyclerView> myFocusRecyclerViews) {
                myFocusAdapter.setRecyclerViewList(myFocusRecyclerViews);
                dataBinding.recyclerviewMyFocus.setAdapter(myFocusAdapter);
            }
        });

        viewModel.myMemberMutableLiveData.observe(this, new Observer<List<MyMemberRecyclerView>>() {
            @Override
            public void onChanged(List<MyMemberRecyclerView> myMemberRecyclerViewList) {
                myMemberAdapter.setRecyclerViewList(myMemberRecyclerViewList);
                dataBinding.recyclerviewMyMember.setAdapter(myMemberAdapter);
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

        dataBinding.tvMyTask.setOnClickListener(this::onClick);


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(HTAG, "onHiddenChanged: ====>");
        viewModel.loadMyFocus();
        viewModel.loadMyMembers();
    }

    @Override
    protected void initView() {
        dropdownBarMyFocus = new DropdownBar("我的关注", true, false, false);
        DropdownBar dropdownBarMyMember = new DropdownBar("我的会员", true, false, false);
        DropdownBar dropdownBarServiceNavigation = new DropdownBar("服务导航", false, false, false);
        ArrayList<DropdownBar> dropdownBarArrayList = new ArrayList<>();
        dropdownBarArrayList.add(dropdownBarMyFocus);
        dropdownBarArrayList.add(dropdownBarMyMember);
        dropdownBarArrayList.add(dropdownBarServiceNavigation);
        viewModel.dropdownBarLists.setValue(dropdownBarArrayList);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: =====>");
        viewModel.loadMyFocus();
        viewModel.loadMyMembers();
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void initObserver() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_my_task:
                startActivity(MyTaskActivity.class);
                break;
        }
    }
}
