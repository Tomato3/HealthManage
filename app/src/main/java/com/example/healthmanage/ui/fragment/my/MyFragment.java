package com.example.healthmanage.ui.fragment.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentMyBinding;
import com.example.healthmanage.ui.activity.invitemember.InviteNewMemberActivity;
import com.example.healthmanage.ui.activity.mycomment.MyCommentActivity;
import com.example.healthmanage.ui.activity.myinfo.MyInfoActivity;
import com.example.healthmanage.ui.activity.mypoint.MyPointActivity;
import com.example.healthmanage.ui.activity.mystudio.MyStudioActivity;
import com.example.healthmanage.ui.activity.servicecenter.ServiceCenterActivity;
import java.util.List;


public class MyFragment extends BaseFragment<FragmentMyBinding, MyViewModel> implements View.OnClickListener {


    private List<Fragment> mFragments;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void initObserver() {

    }


    @Override
    public void initDataBindingAndViewModel(Bundle savedInstanceState) {
        super.initDataBindingAndViewModel(savedInstanceState);
        dataBinding.tvInvitingMembers.setOnClickListener(this::onClick);
        dataBinding.tvMyStudio.setOnClickListener(this::onClick);
        dataBinding.tvMyComment.setOnClickListener(this::onClick);
        dataBinding.tvMyPoint.setOnClickListener(this::onClick);
        dataBinding.tvServiceCenter.setOnClickListener(this::onClick);

    }



    @Override
    protected void registerUIChangeLiveDataCallBack() {
        super.registerUIChangeLiveDataCallBack();
        dataBinding.tvName.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_inviting_members:
                startActivity(InviteNewMemberActivity.class);
                break;
            case R.id.tv_my_studio:
                startActivity(MyStudioActivity.class);
                break;
            case R.id.tv_name:
                startActivity(MyInfoActivity.class);
                break;
            case R.id.tv_my_comment:
                startActivity(MyCommentActivity.class);
                break;
            case R.id.tv_my_point:
                startActivity(MyPointActivity.class);
                break;
            case R.id.tv_service_center:
                startActivity(ServiceCenterActivity.class);
                break;

        }
    }


    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_my;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }

}
