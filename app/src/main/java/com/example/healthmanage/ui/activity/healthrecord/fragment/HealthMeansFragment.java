package com.example.healthmanage.ui.activity.healthrecord.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentHealthMeansBinding;
import com.example.healthmanage.databinding.FragmentInformationBinding;
import com.example.healthmanage.ui.activity.healthrecord.HealthRecordViewModel;
import com.example.healthmanage.ui.activity.healthrecord.adapter.CheckReportAdapter;
import com.example.healthmanage.ui.activity.healthrecord.adapter.HistoryAssessAdapter;
import com.example.healthmanage.ui.activity.healthrecord.adapter.MedicineAdapter;
import com.example.healthmanage.ui.activity.healthrecord.response.CheckReportResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.HealthRecordResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.HistoryAssessListResponse;
import com.example.healthmanage.ui.activity.healthrecord.response.MedicineBookResponse;
import com.example.healthmanage.view.GridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 *健康资料fragment
 */
public class HealthMeansFragment  extends BaseFragment<FragmentHealthMeansBinding, HealthRecordViewModel> {
    private CheckReportAdapter checkReportAdapter;
    private List<CheckReportResponse.DataBean> checkReportList;
    private List<HistoryAssessListResponse.DataBean> historyAssessList;
    private List<MedicineBookResponse.DataBean> medicineList;
    private HistoryAssessAdapter historyAssessAdapter;
    private MedicineAdapter medicineAdapter;
    @Override
    protected void initData() {
    }

    public void setUserId(int userId){
        if (userId != 0){
            checkReportList = new ArrayList<>();
            historyAssessList = new ArrayList<>();
            medicineList = new ArrayList<>();
//            dataBinding.recyclerViewCheckReport.setLayoutManager(new LinearLayoutManager(getActivity()));
            checkReportAdapter = new CheckReportAdapter(getActivity(),checkReportList);
            dataBinding.recyclerViewCheckReport.setLayoutManager(new LinearLayoutManager(getActivity()));
            //最后一个不显示分割线且自定义分割线
            GridItemDecoration gridItemDecoration = new GridItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
            gridItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_divider));
            if (dataBinding.recyclerViewCheckReport.getItemDecorationCount()==0){
                dataBinding.recyclerViewCheckReport.addItemDecoration(gridItemDecoration);
            }
            dataBinding.recyclerViewCheckReport.setAdapter(checkReportAdapter);

            viewModel.getCheckReportList(userId);
//            dataBinding.recyclerViewHistoryAssess.setLayoutManager(new LinearLayoutManager(getActivity()));
            historyAssessAdapter = new HistoryAssessAdapter(historyAssessList);
            dataBinding.recyclerViewHistoryAssess.setLayoutManager(new LinearLayoutManager(getActivity()));
            //最后一个不显示分割线且自定义分割线
            gridItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_divider));
            if (dataBinding.recyclerViewHistoryAssess.getItemDecorationCount()==0){
                dataBinding.recyclerViewHistoryAssess.addItemDecoration(gridItemDecoration);
            }
            dataBinding.recyclerViewHistoryAssess.setAdapter(historyAssessAdapter);
            viewModel.getHistoryAssessList(userId);
//            dataBinding.recyclerViewMedicineReportBook.setLayoutManager(new LinearLayoutManager(getActivity()));
            medicineAdapter = new MedicineAdapter(getActivity(),medicineList);
            dataBinding.recyclerViewMedicineReportBook.setLayoutManager(new LinearLayoutManager(getActivity()));
            //最后一个不显示分割线且自定义分割线
            gridItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_divider));
            if (dataBinding.recyclerViewMedicineReportBook.getItemDecorationCount()==0){
                dataBinding.recyclerViewMedicineReportBook.addItemDecoration(gridItemDecoration);
            }
            dataBinding.recyclerViewMedicineReportBook.setAdapter(medicineAdapter);
            viewModel.getMedicalAll(userId);
        }else {

        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void registerUIChangeLiveDataCallBack() {
        super.registerUIChangeLiveDataCallBack();
        viewModel.checkReportListLiveData.observe(getActivity(), new Observer<List<CheckReportResponse.DataBean>>() {
            @Override
            public void onChanged(List<CheckReportResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewCheckReport.setVisibility(View.VISIBLE);
                    dataBinding.tvCheckReportNull.setVisibility(View.GONE);
                    if (checkReportList!=null && checkReportList.size()>0){
                        checkReportList.clear();
                    }
                    checkReportList.addAll(dataBeans);
                    checkReportAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewCheckReport.setVisibility(View.GONE);
                    dataBinding.tvCheckReportNull.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.historyAssessListLiveData.observe(getActivity(), new Observer<List<HistoryAssessListResponse.DataBean>>() {
            @Override
            public void onChanged(List<HistoryAssessListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewHistoryAssess.setVisibility(View.VISIBLE);
                    dataBinding.tvHistoryAssessNull.setVisibility(View.GONE);
                    if (historyAssessList!=null && historyAssessList.size()>0){
                        historyAssessList.clear();
                    }
                    historyAssessList.addAll(dataBeans);
                    historyAssessAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewHistoryAssess.setVisibility(View.GONE);
                    dataBinding.tvHistoryAssessNull.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.medicineListLiveData.observe(getActivity(), new Observer<List<MedicineBookResponse.DataBean>>() {
            @Override
            public void onChanged(List<MedicineBookResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewMedicineReportBook.setVisibility(View.VISIBLE);
                    dataBinding.tvMeansNull.setVisibility(View.GONE);
                    if (medicineList!=null && medicineList.size()>0){
                        medicineList.clear();
                    }
                    medicineList.addAll(dataBeans);
                    medicineAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewMedicineReportBook.setVisibility(View.GONE);
                    dataBinding.tvMeansNull.setVisibility(View.VISIBLE);
                }
            }
        });
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
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_health_means;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}
