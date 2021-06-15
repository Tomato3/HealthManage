package com.example.healthmanage.ui.activity.temperature.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityPrescriptionModelBinding;
import com.example.healthmanage.ui.activity.temperature.adapter.DrugsAdapter;
import com.example.healthmanage.ui.activity.temperature.adapter.PrescriptionAdapter;
import com.example.healthmanage.ui.activity.temperature.response.PrescriptionResponse;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 处方模版列表界面
 */
public class PrescriptionModelActivity extends BaseActivity<ActivityPrescriptionModelBinding,TemperatureViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<PrescriptionResponse.DataBean> dataBeanList = new ArrayList<>();
    private PrescriptionAdapter prescriptionAdapter;
    private int index = 0;
    @Override
    protected void initData() {
        context = PrescriptionModelActivity.this;
        titleToolBar.setTitle("处方模板");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        dataBinding.recyclerViewPrescriptionModel.setLayoutManager(new LinearLayoutManager(context));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(context, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider));
        if (dataBinding.recyclerViewPrescriptionModel.getItemDecorationCount()==0){
            dataBinding.recyclerViewPrescriptionModel.addItemDecoration(gridItemDecoration);
        }
        prescriptionAdapter = new PrescriptionAdapter(context,dataBeanList);
        dataBinding.recyclerViewPrescriptionModel.setAdapter(prescriptionAdapter);
        prescriptionAdapter.notifyDataSetChanged();

        viewModel.getHealthConsultDrugModel(0);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.prescriptionResponseMutableLiveData.observe(this, new Observer<List<PrescriptionResponse.DataBean>>() {
            @Override
            public void onChanged(List<PrescriptionResponse.DataBean> dataBeans) {
                if (dataBeans !=null && dataBeans.size()>0){
                    dataBinding.smartRefreshLayout.setVisibility(View.VISIBLE);
                    dataBinding.layoutPrescriptionModelNull.setVisibility(View.GONE);
                    if (dataBeanList !=null && dataBeanList.size()>0){
                        dataBeanList.clear();
                    }
                    dataBeanList.addAll(dataBeans);
                    prescriptionAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.smartRefreshLayout.setVisibility(View.GONE);
                    dataBinding.layoutPrescriptionModelNull.setVisibility(View.VISIBLE);
                }
            }
        });
        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_common_prescription:
                        index = 0;
                        viewModel.getHealthConsultDrugModel(index);
                        break;
                    case R.id.rb_special_prescription:
                        index =1;
                        viewModel.getHealthConsultDrugModel(index);
                        break;
                }
            }
        });
        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                dataBinding.smartRefreshLayout.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (index == 0){
                    viewModel.getHealthConsultDrugModel(0);
                }else {
                    viewModel.getHealthConsultDrugModel(1);
                }
                prescriptionAdapter.notifyDataSetChanged();
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });
        dataBinding.layoutWritePrescriptionModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CreatePrescriptionActivity.class);
            }
        });
        prescriptionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context,PrescriptionDetailActivity.class);
                intent.putExtra("dataBean",dataBeanList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (index){
            case 0:
                viewModel.getHealthConsultDrugModel(0);
                prescriptionAdapter.notifyDataSetChanged();
                break;
            case 1:
                viewModel.getHealthConsultDrugModel(1);
                prescriptionAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_prescription_model;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
