package com.example.healthmanage.ui.activity.qualification;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityHospitalBinding;
import com.example.healthmanage.ui.activity.qualification.adapter.HospitalAdapter;
import com.example.healthmanage.ui.activity.qualification.response.HospitalResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择医院
 */
public class SelectHospitalActivity extends BaseActivity<ActivityHospitalBinding,DepartMentViewModel> {
    private HospitalAdapter hospitalAdapter;
    private List<HospitalResponse.DataBean> hospitalList;
    @Override
    protected void initData() {
        initView();
        viewModel.getHospitalByName("");
    }

    private void initView() {
        hospitalList = new ArrayList<>();
        dataBinding.recyclerViewHospital.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        dataBinding.recyclerViewHospital.addItemDecoration(new DividerItemDecoration(SelectHospitalActivity.this,DividerItemDecoration.VERTICAL));
        hospitalAdapter = new HospitalAdapter(hospitalList);
        dataBinding.recyclerViewHospital.setAdapter(hospitalAdapter);
        hospitalAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("hospitalName",hospitalList.get(position).getName());
                intent.putExtra("hospitalId",hospitalList.get(position).getId());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        dataBinding.edtSearchHospital.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    viewModel.getHospitalByName(s.toString());
                }else {
                    viewModel.getHospitalByName("");
                }
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        viewModel.hospitalMutableLiveData.observe(this, new Observer<List<HospitalResponse.DataBean>>() {
            @Override
            public void onChanged(List<HospitalResponse.DataBean> dataBeans) {
                if (hospitalList!=null && hospitalList.size()>0){
                    hospitalList.clear();
                }
                hospitalList.addAll(dataBeans);
                hospitalAdapter.setNewData(hospitalList);
            }
        });
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_hospital;
    }
}
