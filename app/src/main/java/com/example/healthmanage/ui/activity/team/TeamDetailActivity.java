package com.example.healthmanage.ui.activity.team;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityTeamDetailBinding;
import com.example.healthmanage.ui.activity.team.response.TeamMemberResponse;
import com.example.healthmanage.widget.TitleToolBar;

public class TeamDetailActivity extends BaseActivity<ActivityTeamDetailBinding,TeamViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private Context context;
    private TeamMemberResponse.DataBean dataBean;
    @Override
    protected void initData() {
        context = TeamDetailActivity.this;
        titleToolBar.setTitle("资料详情");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        dataBean = (TeamMemberResponse.DataBean) getIntent().getSerializableExtra("dataBean");
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        Glide.with(context).load(dataBean.getAppDoctorInfo().getAvatar()).apply(new RequestOptions().placeholder(R.drawable.ic_load_error).error(R.drawable.ic_load_error).circleCrop())
                .into(dataBinding.ivAvatar);
        dataBinding.tvName.setText(dataBean.getAppDoctorInfo().getName());
        dataBinding.tvTeamRank.setText(dataBean.getAppDoctorInfo().getRank()+"\u3000|\u3000"+dataBean.getAppDoctorInfo().getAppHospitalDepartment().getName());
        dataBinding.tvTeamPhone.setText("手机:"+dataBean.getAppDoctorInfo().getAppSystemUser().getPhone());
        if (dataBean.getAppDoctorInfo().getRoleId()==11){
            dataBinding.tvTeamAddr.setText("所属医院:"+dataBean.getAppDoctorInfo().getAppHospital().getName());
        }else {
            dataBinding.tvTeamAddr.setText("所属地区:"+dataBean.getAppDoctorInfo().getAddr());
        }
        if (TextUtils.isEmpty(dataBean.getAppDoctorInfo().getExperience())){
            dataBinding.tvPersonalInfo.setText("暂无个人简介");
        }else {
            dataBinding.tvPersonalInfo.setText(dataBean.getAppDoctorInfo().getExperience());
        }
        if (TextUtils.isEmpty(dataBean.getAppDoctorInfo().getSpeciality())){
            dataBinding.tvFiled.setText("暂无擅长领域");
        }else {
            dataBinding.tvFiled.setText(dataBean.getAppDoctorInfo().getSpeciality());
        }
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
        return R.layout.activity_team_detail;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
