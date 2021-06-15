package com.example.healthmanage.ui.fragment.qualification;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.adapter.ProfessionListAdapter;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.bean.ProfessionBeanResponse;
import com.example.healthmanage.bean.QualificationInputItem;
import com.example.healthmanage.databinding.FragmentStepFirstBinding;
import com.example.healthmanage.ui.activity.qualification.QualificationActivity;
import com.example.healthmanage.ui.activity.qualification.SelectDepartmentActivity;
import com.example.healthmanage.ui.activity.qualification.SelectHospitalActivity;
import com.example.healthmanage.ui.fragment.qualification.bean.DoctorInfo;
import com.example.healthmanage.ui.fragment.qualification.bean.UpdateDoctorInfo;
import com.example.healthmanage.utils.SPUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.utils.ToolUtil;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Description:
 * Author:bwang
 * Date:2020/12/2 16:55
 */
public class FirstStepFragment extends BaseFragment<FragmentStepFirstBinding, FirstStepViewModel> implements View.OnClickListener {

    PopupWindow popupWindow;
    List<QualificationInputItem> qualificationInputItemList;
    String profession;
    String rank;
    List<ProfessionBeanResponse.DataBean> professionList = new ArrayList<>();
    List<ProfessionBeanResponse.DataBean> zhichengList = new ArrayList<>();
    ProfessionListAdapter professionListAdapter;
    ProfessionListAdapter zhichengAdapter;
    int oldPosition = -1;
    //职业id
    int professionId;
    //科室id
    int departmentId;
    String department;
    //医院id
    int hospitalId;
    CityPickerView mCityPickerView;
    String cityData;
    private DoctorInfo doctorInfo;
    private UpdateDoctorInfo updateDoctorInfo;
    private QualificationActivity qualificationActivity;

    @Override
    protected void initData() {

        qualificationInputItemList = new ArrayList<>();
        qualificationInputItemList.add(new QualificationInputItem(getString(R.string.activity_qualification_title_name),
                getString(R.string.activity_qualification_hint_name), false));
        qualificationInputItemList.add(new QualificationInputItem(getString(R.string.activity_qualification_title_IdCard),
                getString(R.string.activity_qualification_hint_IdCard), false));
        viewModel.qualificationInputItemListMutableLiveData.setValue(qualificationInputItemList);
        qualificationInputItemList.add(new QualificationInputItem(getString(R.string.activity_qualification_title_department),
                getString(R.string.activity_qualification_hint_department), true));
        viewModel.qualificationInputItemListMutableLiveData.setValue(qualificationInputItemList);
        qualificationInputItemList.add(new QualificationInputItem(getString(R.string.activity_qualification_title_title),
                getString(R.string.activity_qualification_hint_title), true));
        viewModel.qualificationInputItemListMutableLiveData.setValue(qualificationInputItemList);
        qualificationInputItemList.add(new QualificationInputItem(getString(R.string.activity_qualification_title_hospital),
                getString(R.string.activity_qualification_hint_hospital), true));
        viewModel.qualificationInputItemListMutableLiveData.setValue(qualificationInputItemList);

        dataBinding.includeName.etInput.setInputType(InputType.TYPE_CLASS_TEXT);
        dataBinding.includeIdCard.etInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        String html = "我已阅读并了解 " + "<a href=\"" +
                "https://www.cnblogs.com/csn0721/archive/2013/01/23/2873682.html" + "\">" +
                "《保健医苑医生端服务协议》" + "与" + "<a href=\"" +
                "https://www.cnblogs.com/csn0721/archive/2013/01/23/2873682.html" + "\">" +
                "《保健医苑医生端隐私协议政策》";
        CharSequence charSequence = Html.fromHtml(html);
        dataBinding.text.setMovementMethod(LinkMovementMethod.getInstance());
        dataBinding.text.setText(charSequence);
        mCityPickerView = new CityPickerView();
        mCityPickerView.init(getActivity());
        qualificationActivity = (QualificationActivity) getActivity();
        doctorInfo = qualificationActivity.getDoctorInfo();
        updateDoctorInfo = qualificationActivity.getUpdateDoctorInfo();
    }

    @Override
    protected void initListener() {
        dataBinding.btnNext.setOnClickListener(this::onClick);
        dataBinding.tvSelectProfession.setOnClickListener(this::onClick);
        dataBinding.text.setMovementMethod(LinkMovementMethod.getInstance());
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
    protected void registerUIChangeLiveDataCallBack() {
        super.registerUIChangeLiveDataCallBack();
        viewModel.profession.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("请选择您的职业")) {
                    dataBinding.linearLayoutTip.setVisibility(View.VISIBLE);
                    dataBinding.linearLayoutDoctor.setVisibility(View.GONE);
                }else {
                    dataBinding.linearLayoutTip.setVisibility(View.GONE);
                    if (s.contains("医师")) {
                        dataBinding.linearLayoutDoctor.setVisibility(View.VISIBLE);
                        dataBinding.includeDepartment.getRoot().setClickable(true);
                        dataBinding.includeTitle.getRoot().setClickable(true);
                        dataBinding.includeDepartment.tvInput.setText("");
                        dataBinding.includeTitle.tvInput.setText("");
                        dataBinding.includeDepartment.tvInput.setHint("请选择您的科室");
                        dataBinding.includeTitle.tvInput.setHint("请选择您的职称");
                        dataBinding.includeHospital.tvChooseName.setText("所属医院");
                        dataBinding.includeHospital.tvInput.setHint("请选择您的工作单位");
                        dataBinding.includeHospital.tvInput.setText("");
                        dataBinding.includeDepartment.getRoot().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), SelectDepartmentActivity.class);
                                startActivityForResult(intent,1);
                            }
                        });
                        dataBinding.includeHospital.getRoot().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), SelectHospitalActivity.class);
                                startActivityForResult(intent,2);
                            }
                        });
                    } else {
                        dataBinding.linearLayoutDoctor.setVisibility(View.VISIBLE);
                        dataBinding.includeDepartment.tvInput.setText("");
                        dataBinding.includeTitle.tvInput.setText("");
                        dataBinding.includeDepartment.tvInput.setHint("其他科");
                        rank = s;
                        dataBinding.includeTitle.tvInput.setHint(rank);
                        dataBinding.includeDepartment.getRoot().setClickable(false);
                        dataBinding.includeTitle.getRoot().setClickable(false);
                        dataBinding.includeHospital.tvChooseName.setText("所在地区");
                        dataBinding.includeHospital.tvInput.setHint("请选择");
                        dataBinding.includeHospital.tvInput.setText("");
                        dataBinding.includeHospital.getRoot().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cityPop();
                            }
                        });
                    }
                }
            }
        });
        dataBinding.includeTitle.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow("选择您的职称",1);
            }
        });
        viewModel.jobMutableLiveData.observe(this, new Observer<List<ProfessionBeanResponse.DataBean>>() {
            @Override
            public void onChanged(List<ProfessionBeanResponse.DataBean> dataBeans) {
                if (professionList != null){
                    professionList.clear();
                }
                professionList.addAll(dataBeans);
                professionListAdapter.setNewData(professionList);
            }
        });
    }

    private void cityPop() {
        CityConfig cityConfig = new CityConfig.Builder()
                .title("选择城市")
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.item_city_name_tv)
                .setShowGAT(true)//是否显示港澳
                .build();

        mCityPickerView.setConfig(cityConfig);
        mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                StringBuilder sb = new StringBuilder();
                if (province != null) {
                    sb.append(province.getName());
                }

                if (city != null) {
                    sb.append(city.getName());
                }

                if (district != null) {
                    sb.append(district.getName());
                }
                dataBinding.includeHospital.tvInput.setText(sb.toString());
                dataBinding.includeHospital.tvInput.setTextColor(getActivity().getColor(R.color.colorTxtBlack));
                cityData = sb.toString();
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(getActivity(), "已取消");
            }
        });
        mCityPickerView.showCityPicker();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            if (requestCode == 1){
                if (resultCode == RESULT_OK){
                    dataBinding.includeDepartment.tvInput.setText(data.getStringExtra("department"));
                    departmentId = data.getIntExtra("departmentId",0);
                    department = data.getStringExtra("department");
//                    Toast.makeText(getActivity(),"id:"+departmentId,Toast.LENGTH_SHORT).show();
                    dataBinding.includeDepartment.tvInput.setTextColor(getActivity().getColor(R.color.colorTxtBlack));
                }
            }else if (requestCode == 2){
                if (resultCode == RESULT_OK){
                    dataBinding.includeHospital.tvInput.setText(data.getStringExtra("hospitalName"));
                    hospitalId = data.getIntExtra("hospitalId",0);
//                    Toast.makeText(getActivity(),"id:"+hospitalId,Toast.LENGTH_SHORT).show();
                    dataBinding.includeHospital.tvInput.setTextColor(getActivity().getColor(R.color.colorTxtBlack));
                }
            }
        }
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_step_first;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                if (TextUtils.isEmpty(dataBinding.includeName.etInput.getText())) {
                    ToastUtil.showShort("姓名不能为空");
                } else if (TextUtils.isEmpty(dataBinding.includeIdCard.etInput.getText())) {
                    ToastUtil.showShort("身份证号不能为空");
                }else if (!RegexUtils.isIDCard18Exact(dataBinding.includeIdCard.etInput.getText())){
                    ToastUtil.showShort("请输入正确身份证号");
                } else if (viewModel.profession.getValue().equals("请选择您的职业")) {
                    ToastUtil.showShort("职业不能为空");
                } else if (!dataBinding.cbAgree.isChecked()) {
                    ToastUtil.showShort("请先勾选同意协议");
                } else {
                    LiveEventBus.get("ChangeFragment").post(1);
                    if (qualificationActivity.getType()!=null){
                        updateDoctorInfo.setName(dataBinding.includeName.etInput.getText().toString());
                        updateDoctorInfo.setIdCard(dataBinding.includeIdCard.etInput.getText().toString());
                        updateDoctorInfo.setRoleId(professionId);
//                        updateDoctorInfo.setId(Integer.valueOf(SPUtil.getId(getActivity())));
//                        updateDoctorInfo.setPhone(SPUtil.getPhone(getActivity()));
                        if (viewModel.profession.getValue().equals("医师")){
                            updateDoctorInfo.setDepartmentId(departmentId);
                            updateDoctorInfo.setRank(rank);
                            updateDoctorInfo.setHospitalId(hospitalId);
                        }else {
                            updateDoctorInfo.setAddr(cityData);
                            updateDoctorInfo.setDepartmentId(0);
                            updateDoctorInfo.setRank(rank);
                        }
                    }else {
                        doctorInfo.setName(dataBinding.includeName.etInput.getText().toString());
                        doctorInfo.setIdCard(dataBinding.includeIdCard.etInput.getText().toString());
                        doctorInfo.setRoleId(professionId);
                        if (viewModel.profession.getValue().equals("医师")){
                            doctorInfo.setDepartmentId(departmentId);
                            doctorInfo.setRank(rank);
                            doctorInfo.setHospitalId(hospitalId);
                        }else {
                            doctorInfo.setAddr(cityData);
                            doctorInfo.setDepartmentId(0);
                            doctorInfo.setRank(rank);
                        }
                    }

                }
                break;
            case R.id.tv_select_profession:
                viewModel.getJobList();
                initPopupWindow("选择您的职业",0);
                break;
        }

    }


    private void initPopupWindow(String title,int type){
        ToolUtil.hideKeyboard(dataBinding.includeName.etInput);
        ToolUtil.hideKeyboard(dataBinding.includeIdCard.etInput);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_popup_job, null, false);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.Animation);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0,
                ToolUtil.getNavigationBarHeight(getActivity()));
        popupWindow.setFocusable(true);
        bgAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                bgAlpha(1);
            }
        });
        TextView popTitle = view.findViewById(R.id.tv_pop_title);
        popTitle.setText(title);
        Button button = view.findViewById(R.id.btn_cancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recycleview_job);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        if (type == 0){
            professionListAdapter = new ProfessionListAdapter(professionList,getActivity());
            recyclerView.setAdapter(professionListAdapter);
            professionListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (oldPosition != -1 && oldPosition != position){
                        professionList.get(oldPosition).setSelect(false);
                    }
                    professionList.get(position).setSelect(true);
                    oldPosition = position;
                    profession = professionList.get(position).getRoleName();
                    professionId = professionList.get(position).getId();
                    professionListAdapter.notifyDataSetChanged();
                }
            });
            Button button1 = view.findViewById(R.id.btn_confirm);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(profession)) {
                        ToastUtil.showShort("请选择您的职业");
                    } else {
                        popupWindow.dismiss();
                        viewModel.profession.setValue(profession);
                        dataBinding.tvSelectProfession.setTextColor(getActivity().getColor(R.color.colorTxtBlack));
                    }
                }
            });
        }
        else {
            if (zhichengList != null){
                zhichengList.clear();
            }
            zhichengList.add(new ProfessionBeanResponse.DataBean("主任医师"));
            zhichengList.add(new ProfessionBeanResponse.DataBean("副主任医师"));
            zhichengList.add(new ProfessionBeanResponse.DataBean("主治医师"));
            zhichengList.add(new ProfessionBeanResponse.DataBean("住院医师"));
            zhichengAdapter = new ProfessionListAdapter(zhichengList,getActivity());
            recyclerView.setAdapter(zhichengAdapter);
            zhichengAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    zhichengList.get(oldPosition).setSelect(false);
                    zhichengList.get(position).setSelect(true);
                    oldPosition = position;
                    rank = zhichengList.get(position).getRoleName();
                    zhichengAdapter.notifyDataSetChanged();
                }
            });
            Button button1 = view.findViewById(R.id.btn_confirm);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(rank)) {
                        ToastUtil.showShort("请选择您的职业");
                    } else {
                        popupWindow.dismiss();
                        dataBinding.includeTitle.tvInput.setText(rank);
                        dataBinding.includeTitle.tvInput.setTextColor(getActivity().getColor(R.color.colorTxtBlack));
                    }
                }
            });
        }

    }


    private void bgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }
}
