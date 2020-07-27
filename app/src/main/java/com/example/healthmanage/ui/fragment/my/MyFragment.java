package com.example.healthmanage.ui.fragment.my;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentMyBinding;
import com.example.healthmanage.ui.activity.invitingmembers.InvitingMembersActivity;
import com.example.healthmanage.ui.activity.myinfo.MyInfoActivity;
import com.example.healthmanage.ui.activity.mystudio.MyStudioActivity;

import java.util.List;

public class MyFragment extends BaseFragment<FragmentMyBinding, MyViewModel> implements View.OnClickListener {

    private static String TAG = "MyFragment";

    private static String baseImgUrl = "http://b-ssl.duitang.com/uploads/item/201803/02/20180302222228_v3JdH.jpeg";
    private List<Fragment> mFragments;

    @Override
    protected void initData() {
        Log.d(TAG, "initData: ===>" + BaseApplication.getUserInfoBean().getSysId());

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
                startActivity(InvitingMembersActivity.class);
                break;
            case R.id.tv_my_studio:
                startActivity(MyStudioActivity.class);
                break;
            case R.id.tv_name:
                startActivity(MyInfoActivity.class);
                break;

        }
    }


    @BindingAdapter("android:url")
    public static void setUrl(ImageView view, String imgUrl) {
        if (imgUrl != null && !"".equals(imgUrl)) {
            Glide.with(view.getContext()).asBitmap().load(imgUrl).apply(RequestOptions.circleCropTransform()).into(view);
        } else {
            Glide.with(view.getContext()).asBitmap().load(baseImgUrl).apply(RequestOptions.circleCropTransform()).into(view);
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
