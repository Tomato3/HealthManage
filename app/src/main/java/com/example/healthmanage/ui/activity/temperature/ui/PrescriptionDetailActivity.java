package com.example.healthmanage.ui.activity.temperature.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityPrescriptionDetailBinding;
import com.example.healthmanage.ui.activity.temperature.adapter.PrescriptionDetailAdapter;
import com.example.healthmanage.ui.activity.temperature.response.PrescriptionResponse;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;

/**
 * 处方详情页面
 */
public class PrescriptionDetailActivity extends BaseActivity<ActivityPrescriptionDetailBinding,TemperatureViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private PrescriptionResponse.DataBean dataBean;
    private PrescriptionDetailAdapter prescriptionDetailAdapter;
    @Override
    protected void initData() {
        context = PrescriptionDetailActivity.this;
        titleToolBar.setTitle("处方详情");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        dataBean = (PrescriptionResponse.DataBean) getIntent().getSerializableExtra("dataBean");

        dataBinding.recyclerviewRp.setLayoutManager(new LinearLayoutManager(this));
        prescriptionDetailAdapter = new PrescriptionDetailAdapter(dataBean.getDrugList());
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_white));
        if (dataBinding.recyclerviewRp.getItemDecorationCount()==0){
            dataBinding.recyclerviewRp.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerviewRp.setAdapter(prescriptionDetailAdapter);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.tvModelName.setText(dataBean.getModelName());
        if (dataBean.getModelType()==0){
            dataBinding.groupChoosePrescription.setClickable(false);
            dataBinding.radiobuttonChooseCommon.setChecked(true);
            dataBinding.radiobuttonChooseSpecial.setChecked(false);
        }else {
            dataBinding.radiobuttonChooseSpecial.setChecked(true);
            dataBinding.radiobuttonChooseCommon.setChecked(false);
            dataBinding.groupChoosePrescription.setClickable(false);
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_prescription_detail;
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
