package com.example.healthmanage.ui.activity.famousDoctorHall.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityFamousDoctorDetailBinding;
import com.example.healthmanage.ui.activity.famousDoctorHall.DoctorHallViewModel;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.AppraiseAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.AppraiseResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.FamousDoctorInfoResponse;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:医生详情
 * date:2021/7/8 17:20
 * author:bWang
 */
public class FamousDoctorInfoActivity extends BaseActivity<ActivityFamousDoctorDetailBinding, DoctorHallViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context mContext;
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private List<AppraiseResponse.DataBean> mAppraiseDataBeans;
    private AppraiseAdapter mAppraiseAdapter;

    @Override
    protected void initData() {
        mContext = FamousDoctorInfoActivity.this;
        mTitleToolBar.setTitle("医生详情");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutDoctorDetailTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);
        viewModel.getDoctorInfo(getIntent().getIntExtra("systemUserId",0));

        viewModel.getAppraiseList(getIntent().getIntExtra("systemUserId",0));
        mAppraiseDataBeans = new ArrayList<>();
        mAppraiseAdapter = new AppraiseAdapter(mAppraiseDataBeans);
        dataBinding.recyclerviewAppraise.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_divider));
        if (dataBinding.recyclerviewAppraise.getItemDecorationCount()==0){
            dataBinding.recyclerviewAppraise.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerviewAppraise.setAdapter(mAppraiseAdapter);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.doctorInfoLiveData.observe(this, new Observer<FamousDoctorInfoResponse.DataBean>() {
            @Override
            public void onChanged(FamousDoctorInfoResponse.DataBean dataBean) {
                if (dataBean!= null){
                    Glide.with(mContext).load(dataBean.getAvatar()).apply(new RequestOptions().circleCrop())
                            .error(R.drawable.ic_doctor_logo)
                            .into(dataBinding.ivDoctor);
                    dataBinding.tvNameOrRank.setText(dataBean.getName()+"\u3000"+dataBean.getRank());
                    dataBinding.tvAddressOrOffice.setText(dataBean.getAppHospital().getAddr()+"\u3000"+dataBean.getAppHospitalDepartment().getName());
                    dataBinding.tvConsultNumber.setText(String.valueOf(dataBean.getConsultAmount()));
                    dataBinding.tvFocusNumber.setText(String.valueOf(dataBean.getFollowAmount()));
                    dataBinding.tvDoctorSpeciality.setText(dataBean.getSpeciality());
                }
            }
        });

        viewModel.mAppraiseBeanMutableLiveData.observe(this, new Observer<List<AppraiseResponse.DataBean>>() {
            @Override
            public void onChanged(List<AppraiseResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.tvNoAppraise.setVisibility(View.GONE);
                    dataBinding.recyclerviewAppraise.setVisibility(View.VISIBLE);
                    if (mAppraiseDataBeans!=null&& mAppraiseDataBeans.size()>0){
                        mAppraiseDataBeans.clear();
                    }
                    mAppraiseDataBeans.addAll(dataBeans);
                    mAppraiseAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.tvNoAppraise.setVisibility(View.VISIBLE);
                    dataBinding.recyclerviewAppraise.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        mTitleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_famous_doctor_detail;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
