package com.example.healthmanage.ui.activity.delegate.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityChooseDelegateBinding;
import com.example.healthmanage.ui.activity.mytask.adapter.DoctorAdapter;
import com.example.healthmanage.ui.activity.team.response.TeamMemberResponse;
import com.example.healthmanage.ui.activity.temperature.response.TransferBean;
import com.example.healthmanage.ui.activity.temperature.ui.TransmitTemperatureActivity;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择委派对象
 */
public class ChooseDelegatePersonActivity extends BaseActivity<ActivityChooseDelegateBinding,DelegateViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<TeamMemberResponse.DataBean> doctorBeans;
    private DoctorAdapter doctorAdapter;
    private int index;
    private int mPosition = -1;
    private String transferId;
    private String transferName;
    private String transferRank;

    @Override
    protected void initData() {
        context = ChooseDelegatePersonActivity.this;
        titleToolBar.setTitle("委派对象");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        doctorBeans = new ArrayList<>();
        doctorAdapter = new DoctorAdapter(context,doctorBeans);
        dataBinding.recyclerViewSelectDoctor.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewSelectDoctor.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        dataBinding.recyclerViewSelectDoctor.setAdapter(doctorAdapter);
        viewModel.getDoctorList(10);
        if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==9){
            dataBinding.rbHealthManager.setVisibility(View.GONE);
        }else {
            dataBinding.rbHealthManager.setVisibility(View.VISIBLE);
        }
        doctorAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.btn_select:
                        if (mPosition != -1 && mPosition != position){
                            Log.e("mPosition+error==========",mPosition+"");
                            Log.e("Position+error==========",position+"");
                            doctorBeans.get(mPosition).setChecked(false);
                        }
                        doctorBeans.get(position).setChecked(true);
                        mPosition = position;
                        doctorAdapter.notifyDataSetChanged();
                        transferId = String.valueOf(doctorBeans.get(position).getAppDoctorInfo().getAppSystemUser().getSysId());
                        transferName = doctorBeans.get(position).getAppDoctorInfo().getName();
                        transferRank = doctorBeans.get(position).getAppDoctorInfo().getRank();
                        break;
                }
            }
        });
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.doctorMutableLiveData.observe(this, new Observer<List<TeamMemberResponse.DataBean>>() {
            @Override
            public void onChanged(List<TeamMemberResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.tvDoctorNull.setVisibility(View.GONE);
                    dataBinding.recyclerViewSelectDoctor.setVisibility(View.VISIBLE);
                    if (doctorBeans!=null && doctorBeans.size()>0){
                        doctorBeans.clear();
                    }
                    doctorBeans.addAll(dataBeans);
                    doctorAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.tvDoctorNull.setVisibility(View.VISIBLE);
                    dataBinding.recyclerViewSelectDoctor.setVisibility(View.GONE);
                }
            }
        });
        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_health_dietitian:
                        transferId = null;
                        viewModel.getDoctorList(10);
                        index = 0;
                        //切换的时候如果不恢复会报越界
                        mPosition = -1;
                        break;
                    case R.id.rb_health_nurse:
                        transferId = null;
                        viewModel.getDoctorList(12);
                        index = 1;
                        mPosition = -1;
                        break;
                    case R.id.rb_health_doctor:
                        transferId = null;
                        viewModel.getDoctorList(11);
                        index = 2;
                        mPosition = -1;
                        break;
                    case R.id.rb_health_manager:
                        transferId = null;
                        viewModel.getDoctorList(9);
                        index = 3;
                        mPosition = -1;
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
                    viewModel.getDoctorList(10);
                }else if (index == 1){
                    viewModel.getDoctorList(12);
                }else if (index == 2){
                    viewModel.getDoctorList(11);
                }else if (index == 3){
                    viewModel.getDoctorList(9);
                }
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });
        dataBinding.btnQueryChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(transferId)){
                    Toast.makeText(context,"请选择委派对象",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("transferName",transferName);
                    intent.putExtra("transferId",transferId);
                    intent.putExtra("transferRank",transferRank);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
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
        return R.layout.activity_choose_delegate;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
