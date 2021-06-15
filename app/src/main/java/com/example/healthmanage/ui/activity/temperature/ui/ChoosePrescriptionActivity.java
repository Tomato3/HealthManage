package com.example.healthmanage.ui.activity.temperature.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.example.healthmanage.databinding.ActivityChoosePrescriptionBinding;
import com.example.healthmanage.ui.activity.temperature.adapter.ChoosePrescriptionAdapter;
import com.example.healthmanage.ui.activity.temperature.response.PrescriptionResponse;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 选择处方模版
 */
public class ChoosePrescriptionActivity extends BaseActivity<ActivityChoosePrescriptionBinding,TemperatureViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<PrescriptionResponse.DataBean> dataBeanList = new ArrayList<>();
    private ChoosePrescriptionAdapter choosePrescriptionAdapter;
    private int index = 0;
    private int mPosition = -1;
    private String modelId;

    @Override
    protected void initData() {
        context = ChoosePrescriptionActivity.this;
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
        choosePrescriptionAdapter = new ChoosePrescriptionAdapter(context,dataBeanList);
        dataBinding.recyclerViewPrescriptionModel.setAdapter(choosePrescriptionAdapter);
        choosePrescriptionAdapter.notifyDataSetChanged();

        viewModel.getHealthConsultDrugModel(0);

    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        choosePrescriptionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.rbt_choose_model:
                        if (mPosition != -1 && mPosition != position){
                            dataBeanList.get(mPosition).setSelect(false);
                        }
                        dataBeanList.get(position).setSelect(true);
                        mPosition = position;
                        choosePrescriptionAdapter.notifyDataSetChanged();
                        modelId = String.valueOf(dataBeanList.get(position).getId());
                        break;
                    case R.id.tv_watch_detail:
                        Intent intent = new Intent(context,PrescriptionDetailActivity.class);
                        intent.putExtra("dataBean",dataBeanList.get(position));
                        startActivity(intent);
                        break;
                }
            }
        });
        viewModel.prescriptionResponseMutableLiveData.observe(this, new Observer<List<PrescriptionResponse.DataBean>>() {
            @Override
            public void onChanged(List<PrescriptionResponse.DataBean> dataBeans) {
                if (dataBeans !=null && dataBeans.size()>0){
                    dataBinding.recyclerViewPrescriptionModel.setVisibility(View.VISIBLE);
                    dataBinding.layoutPrescriptionModelNull.setVisibility(View.GONE);
                    if (dataBeanList !=null && dataBeanList.size()>0){
                        dataBeanList.clear();
                    }
                    dataBeanList.addAll(dataBeans);
                    choosePrescriptionAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewPrescriptionModel.setVisibility(View.GONE);
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
                        modelId = null;
                        mPosition = -1;
                        break;
                    case R.id.rb_special_prescription:
                        index =1;
                        viewModel.getHealthConsultDrugModel(index);
                        modelId = null;
                        mPosition = -1;
                        break;
                }
            }
        });
        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (index == 0){
                    viewModel.getHealthConsultDrugModel(0);
                }else {
                    viewModel.getHealthConsultDrugModel(1);
                }
                choosePrescriptionAdapter.notifyDataSetChanged();
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });
        dataBinding.btnQueryChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modelId == null){
                    ToastUtil.showShort("请选择处方模板");
                    return;
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("drugList", (Serializable) dataBeanList.get(mPosition).getDrugList());
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_choose_prescription;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
