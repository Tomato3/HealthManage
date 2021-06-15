package com.example.healthmanage.ui.activity.referral;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityReferralDatailBinding;
import com.example.healthmanage.ui.activity.referral.adapter.ReferralGridPicAdapter;
import com.example.healthmanage.ui.activity.referral.response.ReferralResponse;
import com.example.healthmanage.widget.TitleToolBar;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.tools.ScreenUtils;

public class ReferralDetailActivity extends BaseActivity<ActivityReferralDatailBinding,ReferralViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private Context context;
    private ReferralResponse.DataBean dataBean;
    private ReferralGridPicAdapter referralGridPicAdapter;

    @Override
    protected void initData() {
        context = ReferralDetailActivity.this;
        dataBean = (ReferralResponse.DataBean) getIntent().getSerializableExtra("dataBean");

        titleToolBar.setTitle("患者转诊详情");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        if (TextUtils.isEmpty(dataBean.getPatientIllnessDescribe())){
            dataBinding.tvPatientDescribe.setText("暂无患者简要病史");
        }else {
            dataBinding.tvPatientDescribe.setText(dataBean.getPatientIllnessDescribe());
        }
        if (TextUtils.isEmpty(dataBean.getPatientInitialDecide())){
            dataBinding.tvPrimaryDiagnosis.setText("暂无患者初步诊断");
        }else {
            dataBinding.tvPrimaryDiagnosis.setText(dataBean.getPatientInitialDecide());
        }
        if (TextUtils.isEmpty(dataBean.getReferralReasons())){
            dataBinding.tvReferralReason.setText("暂无转诊原因与说明");
        }else {
            dataBinding.tvReferralReason.setText(dataBean.getReferralReasons());
        }
        dataBinding.tvTransferHospitalContent.setText(TextUtils.isEmpty(dataBean.getApplyOutHospital())?"无":dataBean.getApplyOutHospital());
        dataBinding.tvIntoHospitalContent.setText(TextUtils.isEmpty(dataBean.getApplyInHospital())?"无":dataBean.getApplyInHospital());
        dataBinding.tvChooseReferralObject.setText(dataBean.getChoiseReferral());
        dataBinding.tvPatientReferral.setText(dataBean.getReferralPatientName());
        if (dataBean.getAppNinePictures()!=null && dataBean.getAppNinePictures().size()>0){
            dataBinding.gridRecyclerview.setVisibility(View.VISIBLE);
            dataBinding.tvPicNull.setVisibility(View.GONE);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,5,GridLayoutManager.VERTICAL,false);
            dataBinding.gridRecyclerview.setLayoutManager(gridLayoutManager);
            dataBinding.gridRecyclerview.addItemDecoration(new GridSpacingItemDecoration(5, ScreenUtils.dip2px(context,8),false));
            referralGridPicAdapter = new ReferralGridPicAdapter(context,dataBean.getAppNinePictures());
            dataBinding.gridRecyclerview.setAdapter(referralGridPicAdapter);
            referralGridPicAdapter.notifyDataSetChanged();
        }else {
            dataBinding.gridRecyclerview.setVisibility(View.GONE);
            dataBinding.tvPicNull.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_referral_datail;
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
