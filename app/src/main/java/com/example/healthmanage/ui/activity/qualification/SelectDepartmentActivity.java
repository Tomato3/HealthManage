package com.example.healthmanage.ui.activity.qualification;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.adapter.RightAdapter;
import com.example.healthmanage.adapter.SelectOfficeAdapter;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivitySelectOfficeBinding;
import com.example.healthmanage.ui.activity.qualification.response.DepartmentResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择科室
 */
public class SelectDepartmentActivity extends BaseActivity<ActivitySelectOfficeBinding,DepartMentViewModel> {
    private SelectOfficeAdapter selectOfficeAdapter;
    private RightAdapter secondOfficeAdapter;
    private int perPosition;
    private List<DepartmentResponse.DataBean> firstBeanList;
    private List<DepartmentResponse.DataBean> rightList;
    @Override
    protected void initData() {
        initView();
        viewModel.getDepartmentList();
    }

    private void initView() {
        firstBeanList = new ArrayList<>();
        dataBinding.mainRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dataBinding.secondRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        selectOfficeAdapter = new SelectOfficeAdapter(firstBeanList,SelectDepartmentActivity.this);
        dataBinding.mainRecycleView.setAdapter(selectOfficeAdapter);
        rightList = new ArrayList<>();
        secondOfficeAdapter = new RightAdapter(rightList,SelectDepartmentActivity.this);
        dataBinding.secondRecycleView.setAdapter(secondOfficeAdapter);

        selectOfficeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (perPosition != position){
                    firstBeanList.get(perPosition).setSelect(false);
                    firstBeanList.get(position).setSelect(true);
                    selectOfficeAdapter.notifyDataSetChanged();
                    //设置右列表数据,如果网络请求需添加一个id用于下方查询填值
                    initSecondList(position);
                    secondOfficeAdapter.notifyDataSetChanged();
                    perPosition = position;
                }
            }
        });
        secondOfficeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("department",rightList.get(position).getName());
                intent.putExtra("departmentId",rightList.get(position).getId());
                setResult(RESULT_OK,intent);
                //跳转fragment
//                startActivity(intent);
                finish();
//                Toast.makeText(SelectOfficeActivity.this, secondList.get(position).getOfficeName(), Toast.LENGTH_SHORT).show();
                secondOfficeAdapter.notifyDataSetChanged();
            }
        });
        dataBinding.edtSearchDepartment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    viewModel.getDepartMentByName(s.toString());
                }else {
                    rightList.clear();
                    secondOfficeAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initSecondList(int position) {
        if (rightList != null && rightList.size()>0){
            rightList.clear();
        }
        if (firstBeanList.get(position).getList()!=null && firstBeanList.get(position).getList().size()>0){
            for (DepartmentResponse.DataBean dataBean : firstBeanList.get(position).getList()){
                rightList.add(dataBean);
            }
        }else {
            rightList.add(null);
        }
//        secondOfficeAdapter.setNewData(secondDatas);
    }


    @Override
    public void initViewListener() {
        super.initViewListener();
        viewModel.departmentMutableLiveData.observe(this, new Observer<List<DepartmentResponse.DataBean>>() {
            @Override
            public void onChanged(List<DepartmentResponse.DataBean> dataBeans) {
                if (firstBeanList != null && firstBeanList.size()>0){
                    firstBeanList.clear();
                }
                dataBeans.get(0).setSelect(true);
                firstBeanList.addAll(dataBeans);
                selectOfficeAdapter.setNewData(firstBeanList);
                selectOfficeAdapter.notifyDataSetChanged();
                //左边的recycleview成功获取数据之后才可以给右边的赋值
                initSecondList(0);
            }
        });
        viewModel.departmentRightMutableData.observe(this, new Observer<List<DepartmentResponse.DataBean>>() {
            @Override
            public void onChanged(List<DepartmentResponse.DataBean> dataBeans) {
                if (rightList != null && rightList.size()>0){
                    rightList.clear();
                }
                rightList.addAll(dataBeans);
                secondOfficeAdapter.notifyDataSetChanged();
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
        return R.layout.activity_select_office;
    }
}
