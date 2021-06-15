package com.example.healthmanage.ui.activity.consultation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityChooseDoctorBinding;
import com.example.healthmanage.ui.activity.consultation.adapter.HospitalAdapter;
import com.example.healthmanage.ui.activity.consultation.response.DoctorTeamListResponse;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 患者会诊
 */
public class GetHospitalDepartmentListActivity extends BaseActivity<ActivityChooseDoctorBinding,ConsultationViewModel> implements TitleToolBar.OnTitleIconClickCallBack{
    private TitleToolBar titleToolBar = new TitleToolBar();
    private HospitalAdapter hospitalAdapter;
    private List<DoctorTeamListResponse.DataBean> doctorDataBeans;
    private List<String> chooseDoctorList = new ArrayList<>();
    private List<String> memberIds = new ArrayList<>();
    @Override
    protected void initData() {
        if (chooseDoctorList!=null){
            chooseDoctorList.clear();
        }
        if (memberIds!=null){
            memberIds.clear();
        }
        titleToolBar.setTitle("患者会诊");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        doctorDataBeans = new ArrayList<>();
        hospitalAdapter = new HospitalAdapter(GetHospitalDepartmentListActivity.this,doctorDataBeans);
        dataBinding.recyclerViewConsultationChooseDoctor.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        if (dataBinding.recyclerViewConsultationChooseDoctor.getItemDecorationCount()==0){
            dataBinding.recyclerViewConsultationChooseDoctor.addItemDecoration(dividerItemDecoration);
        }
        dataBinding.recyclerViewConsultationChooseDoctor.setAdapter(hospitalAdapter);
        viewModel.getHospitalDepartmentList();
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.hospitalDepartMutableData.observe(this, new Observer<List<DoctorTeamListResponse.DataBean>>() {
            @Override
            public void onChanged(List<DoctorTeamListResponse.DataBean> dataBeans) {
                if (dataBeans != null && dataBeans.size() > 0){
                    dataBinding.recyclerViewConsultationChooseDoctor.setVisibility(View.VISIBLE);
                    dataBinding.layoutChooseDoctorNull.setVisibility(View.GONE);
                    if (doctorDataBeans != null && doctorDataBeans.size()>0){
                        doctorDataBeans.clear();
                    }
                    doctorDataBeans.addAll(dataBeans);
                }else {
                    dataBinding.recyclerViewConsultationChooseDoctor.setVisibility(View.GONE);
                    dataBinding.layoutChooseDoctorNull.setVisibility(View.VISIBLE);
                }
                hospitalAdapter.notifyDataSetChanged();
            }
        });
        hospitalAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.btn_select_doctor:
                        if (doctorDataBeans.get(position).isSelect()){
                            doctorDataBeans.get(position).setSelect(false);
                            chooseDoctorList.remove(doctorDataBeans.get(position).getName());
                            memberIds.remove(doctorDataBeans.get(position).getPhone());
                        }else {
                            doctorDataBeans.get(position).setSelect(true);
                            chooseDoctorList.add(doctorDataBeans.get(position).getName());
                            memberIds.add(String.valueOf(doctorDataBeans.get(position).getSystemUserId()));
                        }
                        hospitalAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        dataBinding.btnQueryChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseDoctorList!=null && chooseDoctorList.size()>0){
                    Intent intent = new Intent();
                    intent.putExtra("hospitalDoctor",getStr(chooseDoctorList));
                    intent.putExtra("memberIds", getStr(memberIds));
                    setResult(RESULT_OK,intent);
                    finish();
                }else {
                    Toast.makeText(GetHospitalDepartmentListActivity.this,"请至少选择一个参会人员",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    /**
     * list中的字段拼接成字符串
     * @param strList
     * @return
     */
    public static String getStr(List<String> strList){
        String result = "";
        if (strList != null && strList.size()>0){
            for (int i = 0; i < strList.size(); i++) {
                result = result + strList.get(i)+',';
            }
            result = result.substring(0,result.length()-1);
        }
        return result;
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
        return R.layout.activity_choose_doctor;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
