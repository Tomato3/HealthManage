package com.example.healthmanage.ui.activity.famousDoctorHall.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityFamousHallBinding;
import com.example.healthmanage.ui.activity.famousDoctorHall.DoctorHallViewModel;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.AreaAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.CityAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.DoctorAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.GradeAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.HospitalAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.LeftDepartmentAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.ProvinceAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.RankLeftAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.RankRightAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.RightDepartmentAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.bean.RankBean;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.ChinaCityDataBean;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.DepartMentResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.FamousDoctorListResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.HospitalListResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.HospitalTypeResponse;
import com.example.healthmanage.utils.ToastUtils;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:名医堂
 * date:2021/7/1 13:41
 * author:bWang
 */
public class FamousHallActivity extends BaseActivity<ActivityFamousHallBinding, DoctorHallViewModel>
        implements TitleToolBar.OnTitleIconClickCallBack,PopupWindow.OnDismissListener {
    private Context mContext;
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private List<FamousDoctorListResponse.DataBean> doctorBeans;
    private List<HospitalListResponse.DataBean> hospitalBeans;
    private DoctorAdapter mDoctorAdapter;
    private HospitalAdapter mHospitalAdapter;
    private LeftDepartmentAdapter mLeftDepartmentAdapter;
    private RightDepartmentAdapter mRightDepartmentAdapter;
    private List<DepartMentResponse.DataBean> leftBeans;
    private List<DepartMentResponse.DataBean.ListBean> rightBeans;
    private List<HospitalTypeResponse.DataBean> gradeBeans;
    private int departmentPerPosition;
    private int submitDepartmentPerPosition;
    private int rankPerPosition;
    private int rankNewPosition;
    private int provincePerPosition;
    private int submitProvincePerPosition;
    private int cityPerPosition;
    private int screenWidth;
    private int screenHeight;
    private int departmentId;
    private List<RankBean.DataBean> rankBeans;
    private List<RankBean.DataBean.ListBean> professionalBeans;
    private RankLeftAdapter mRankLeftAdapter;
    private RankRightAdapter mRankRightAdapter;
    private String professional;
    private String office;
    private String province;
    private String city;
    private String submitCity;
    private String area;
    private String addr;
    private List<ChinaCityDataBean> provinceList;
    private List<ChinaCityDataBean.CityListBean> cityListBeanList ;
    private List<ChinaCityDataBean.CityListBean.AreaListBean> areaListBeanList;
    private ProvinceAdapter mProvinceAdapter;
    private CityAdapter mCityAdapter;
    private AreaAdapter mAreaAdapter;
    private PopupWindow departMentPopupWindow;
    private PopupWindow rankPopupWindow;
    private PopupWindow cityPopWindow;
    private PopupWindow gradePopupWindow;
    private int tabPosition;
    private GradeAdapter mGradeAdapter;
    private int gradePerPosition;
    private int gradeId;
    private String grade;

    @Override
    protected void initData() {
        mContext = FamousHallActivity.this;
        mTitleToolBar.setTitle("名医堂");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutHallTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);
        initScreenWidth();
        initDoctorView();
        cityPopWindow = new PopupWindow(mContext);
        departMentPopupWindow = new PopupWindow(mContext);
        rankPopupWindow = new PopupWindow(mContext);
        gradePopupWindow = new PopupWindow(mContext);
    }

    private void initHospital() {
        hospitalBeans = new ArrayList<>();
        dataBinding.layoutDoctorList.setVisibility(View.GONE);
        dataBinding.layoutHospitalList.setVisibility(View.VISIBLE);
        mHospitalAdapter = new HospitalAdapter(hospitalBeans,mContext);
        dataBinding.recyclerViewHospital.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_divider));
        if (dataBinding.recyclerViewHospital.getItemDecorationCount()==0){
            dataBinding.recyclerViewHospital.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewHospital.setAdapter(mHospitalAdapter);
        viewModel.getHospitalList(null,null,null);

        mHospitalAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext,HospitalDetailActivity.class);
                intent.putExtra("hospitalId",hospitalBeans.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void initDoctorView() {
        doctorBeans = new ArrayList<>();
        dataBinding.layoutDoctorList.setVisibility(View.VISIBLE);
        dataBinding.layoutHospitalList.setVisibility(View.GONE);
        mDoctorAdapter = new DoctorAdapter(doctorBeans,mContext);
        dataBinding.recyclerViewDoctor.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_divider));
        if (dataBinding.recyclerViewDoctor.getItemDecorationCount()==0){
            dataBinding.recyclerViewDoctor.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewDoctor.setAdapter(mDoctorAdapter);
        viewModel.getDoctorList(null,null,null,null,null);
        mDoctorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext,FamousDoctorInfoActivity.class);
                intent.putExtra("systemUserId",doctorBeans.get(position).getSystemUserId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.famousDoctorMutableLiveData.observe(this, new Observer<List<FamousDoctorListResponse.DataBean>>() {
            @Override
            public void onChanged(List<FamousDoctorListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewDoctor.setVisibility(View.VISIBLE);
                    dataBinding.tvDoctorNull.setVisibility(View.GONE);
                    if (doctorBeans!=null && doctorBeans.size()>0){
                        doctorBeans.clear();
                    }
                    doctorBeans.addAll(dataBeans);
                    mDoctorAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewDoctor.setVisibility(View.GONE);
                    dataBinding.tvDoctorNull.setVisibility(View.VISIBLE);
                }
            }
        });

        viewModel.hospitalMutableLiveData.observe(this, new Observer<List<HospitalListResponse.DataBean>>() {
            @Override
            public void onChanged(List<HospitalListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewHospital.setVisibility(View.VISIBLE);
                    dataBinding.tvHospitalNull.setVisibility(View.GONE);
                    if (hospitalBeans!=null && hospitalBeans.size()>0){
                        hospitalBeans.clear();
                    }
                    hospitalBeans.addAll(dataBeans);
                    mHospitalAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewHospital.setVisibility(View.GONE);
                    dataBinding.tvHospitalNull.setVisibility(View.VISIBLE);
                }
            }
        });

        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbt_doctor:
                        tabPosition=0;
                        initDoctorView();
                        departmentPerPosition = 0;
                        rankPerPosition=0;
                        submitDepartmentPerPosition=0;
                        submitProvincePerPosition=0;
                        professional = null;
                        submitCity=null;
                        city = null;
                        province=null;
                        area = null;
                        addr = null;
                        cityPerPosition = 0;
                        dataBinding.tvCity.setText("全国");
                        dataBinding.tvDepartment.setText("全部科室");
                        dataBinding.tvRank.setText("职级");
                        cityPopWindow.setFocusable(false);
                        break;
                    case R.id.rbt_hospital:
                        tabPosition=1;
                        initHospital();
                        submitProvincePerPosition=0;
                        submitCity=null;
                        city = null;
                        province=null;
                        area = null;
                        addr = null;
                        cityPerPosition = 0;
                        grade=null;
                        gradeId=0;
                        dataBinding.tvHospitalCity.setText("全国");
                        dataBinding.tvHospitalRank.setText("按等级");
                        cityPopWindow.setFocusable(false);
                        break;
                }
            }
        });

        dataBinding.smartRefreshLayoutDoctor.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
                dataBinding.smartRefreshLayoutDoctor.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
                departmentPerPosition = 0;
                rankPerPosition=0;
                submitDepartmentPerPosition=0;
                submitProvincePerPosition=0;
                professional = null;
                submitCity=null;
                city = null;
                cityPerPosition = 0;
                dataBinding.tvCity.setText("全国");
                dataBinding.tvDepartment.setText("全部科室");
                dataBinding.tvRank.setText("职级");
                viewModel.getDoctorList(null,null,null,null,null);
                dataBinding.smartRefreshLayoutDoctor.finishRefresh(200);
            }
        });

        dataBinding.smartRefreshLayoutHospital.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
                dataBinding.smartRefreshLayoutHospital.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
                if (!TextUtils.isEmpty(dataBinding.editSearchVip.getText().toString())){
                    dataBinding.editSearchVip.getText().clear();
                }
                submitProvincePerPosition=0;
                submitCity=null;
                city = null;
                province=null;
                area = null;
                addr = null;
                cityPerPosition = 0;
                grade=null;
                gradeId=0;
                dataBinding.tvHospitalCity.setText("全国");
                dataBinding.tvHospitalRank.setText("按等级");
                viewModel.getHospitalList(null,null,null);
                dataBinding.smartRefreshLayoutHospital.finishRefresh(200);
            }
        });
        dataBinding.layoutProfessional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rankPopupWindow.isFocusable()){
                    onDismiss();
                }else {
                    dataBinding.tvRank.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_blue),null);
                    dataBinding.tvRank.setTextColor(getColor(R.color.colorBlue));
                    dataBinding.tvCity.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                    dataBinding.tvCity.setTextColor(getColor(R.color.colorBlack));
                    dataBinding.tvDepartment.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                    dataBinding.tvDepartment.setTextColor(getColor(R.color.colorBlack));
                    showRankPop();
                }
            }
        });
        dataBinding.layoutChooseCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cityPopWindow.isFocusable()){
                    onDismiss();
                }else {
                    dataBinding.tvCity.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_blue),null);
                    dataBinding.tvCity.setTextColor(getColor(R.color.colorBlue));
                    dataBinding.tvDepartment.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                    dataBinding.tvDepartment.setTextColor(getColor(R.color.colorBlack));
                    dataBinding.tvRank.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                    dataBinding.tvRank.setTextColor(getColor(R.color.colorBlack));
                    showCityPop();
                }
            }
        });

        dataBinding.layoutDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (departMentPopupWindow.isFocusable()){
                    onDismiss();
                }else {
                    dataBinding.tvDepartment.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_blue),null);
                    dataBinding.tvDepartment.setTextColor(getColor(R.color.colorBlue));
                    dataBinding.tvRank.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                    dataBinding.tvRank.setTextColor(getColor(R.color.colorBlack));
                    dataBinding.tvCity.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                    dataBinding.tvCity.setTextColor(getColor(R.color.colorBlack));
                    showDepartmentPop();
                    viewModel.getHospitalDepartment();
                }

            }
        });

        dataBinding.layoutHospitalChooseCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cityPopWindow.isFocusable()){
                    onDismiss();
                }else {
                    dataBinding.tvHospitalCity.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_blue),null);
                    dataBinding.tvHospitalCity.setTextColor(getColor(R.color.colorBlue));
                    dataBinding.tvHospitalRank.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                    dataBinding.tvHospitalRank.setTextColor(getColor(R.color.colorBlack));
                    showCityPop();
                }
            }
        });

        dataBinding.layoutHospitalChooseGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gradePopupWindow.isFocusable()){
                    onDismiss();
                }else {
                    dataBinding.tvHospitalCity.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                    dataBinding.tvHospitalCity.setTextColor(getColor(R.color.colorBlack));
                    dataBinding.tvHospitalRank.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_blue),null);
                    dataBinding.tvHospitalRank.setTextColor(getColor(R.color.colorBlue));
                    showGradePop();
                    viewModel.getHospitalTypeList();
                }
            }
        });

        viewModel.departmentMutableLiveData.observe(this, new Observer<List<DepartMentResponse.DataBean>>() {
            @Override
            public void onChanged(List<DepartMentResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBeans.add(0,new DepartMentResponse.DataBean("全部科室"));
                    if (leftBeans!=null && leftBeans.size()>0){
                        leftBeans.clear();
                    }
                    if (departmentPerPosition!=0){
                        if (submitDepartmentPerPosition!=departmentPerPosition){
                            departmentPerPosition = submitDepartmentPerPosition;
                        }
                        dataBeans.get(departmentPerPosition).setSelect(true);
                        leftBeans.addAll(dataBeans);
                        mLeftDepartmentAdapter.notifyDataSetChanged();
                        initSecondList(departmentPerPosition);
                        for (int i = 0; i < rightBeans.size(); i++) {
                            if (!TextUtils.isEmpty(office)){
                                if (office.equals(rightBeans.get(i).getName())){
                                    rightBeans.get(i).setSelect(true);
                                }else {
                                    rightBeans.get(i).setSelect(false);
                                }
                            }

                        }
                    }else {
                        dataBeans.get(0).setSelect(true);
                        leftBeans.addAll(dataBeans);
                        mLeftDepartmentAdapter.notifyDataSetChanged();
                        initSecondList(0);
                    }
                }else {
                    return;
                }
            }
        });

        viewModel.typeMutableLiveData.observe(this, new Observer<List<HospitalTypeResponse.DataBean>>() {
            @Override
            public void onChanged(List<HospitalTypeResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBeans.add(0,new HospitalTypeResponse.DataBean("全部"));
                    if (gradeBeans!=null && gradeBeans.size()>0){
                        gradeBeans.clear();
                    }
                    gradeBeans.addAll(dataBeans);
                    mGradeAdapter.notifyDataSetChanged();
                    if (TextUtils.isEmpty(grade)){
                        dataBeans.get(0).setSelect(true);
                    }else {
                        for (HospitalTypeResponse.DataBean dataBean : gradeBeans){
                            if (dataBean.getName().equals(grade)){
                                dataBean.setSelect(true);
                            }else {
                                dataBean.setSelect(false);
                            }
                        }
                    }
                }else {
                    return;
                }
            }
        });

        dataBinding.editSearchVip.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    View view = getCurrentFocus();
                    hideKeyboard(view.getWindowToken());
                    if (tabPosition==0){
                        departmentPerPosition = 0;
                        rankPerPosition=0;
                        submitDepartmentPerPosition=0;
                        submitProvincePerPosition=0;
                        professional = null;
                        submitCity=null;
                        city = null;
                        cityPerPosition = 0;
                        dataBinding.tvCity.setText("全国");
                        dataBinding.tvDepartment.setText("全部科室");
                        dataBinding.tvRank.setText("职级");
                        viewModel.getDoctorList(dataBinding.editSearchVip.getText().toString(),null,null,null,null);
                    }else {
                        submitProvincePerPosition=0;
                        submitCity=null;
                        city = null;
                        province=null;
                        area = null;
                        addr = null;
                        cityPerPosition = 0;
                        grade=null;
                        gradeId=0;
                        dataBinding.tvHospitalCity.setText("全国");
                        dataBinding.tvHospitalRank.setText("按等级");
                        viewModel.getHospitalList(dataBinding.editSearchVip.getText().toString(),null,null);
                    }
                    return true;
                }
                return false;
            }
        });

    }

    public void showCityPop(){
        provinceList = new ArrayList<>();
        cityListBeanList = new ArrayList<>();
        areaListBeanList = new ArrayList<>();
        String jsonData = LocalJsonResolutionUtils.getJson(mContext,"china_city_data.json");
        provinceList = LocalJsonResolutionUtils.JsonToObject(jsonData, new TypeToken<List<ChinaCityDataBean>>(){}.getType());


        View departmentView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_city,null,false);
        RecyclerView provinceRecyclerview = departmentView.findViewById(R.id.province_recycleView);
        RecyclerView cityRecyclerview = departmentView.findViewById(R.id.city_recycleView);
        RecyclerView areaRecyclerview = departmentView.findViewById(R.id.area_recycleView);

        provinceRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        cityRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        areaRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        mProvinceAdapter = new ProvinceAdapter(provinceList,mContext);
        provinceRecyclerview.setAdapter(mProvinceAdapter);
        mCityAdapter = new CityAdapter(cityListBeanList,mContext);
        cityRecyclerview.setAdapter(mCityAdapter);
        mAreaAdapter = new AreaAdapter(areaListBeanList,mContext);
        areaRecyclerview.setAdapter(mAreaAdapter);

        provinceList.add(0,new ChinaCityDataBean("全国"));
        if (provincePerPosition!=0){
            if (submitProvincePerPosition!=provincePerPosition){
                provincePerPosition=submitProvincePerPosition;
            }
            provinceList.get(provincePerPosition).setSelect(true);
            mProvinceAdapter.notifyDataSetChanged();
            initCityList(provincePerPosition);
            for (ChinaCityDataBean.CityListBean cityListBean : cityListBeanList) {
                if (!TextUtils.isEmpty(submitCity)){
                    if (submitCity.equals(cityListBean.getName())){
                        cityListBean.setSelect(true);
                    }else {
                        cityListBean.setSelect(false);
                    }
                }
            }

            for (ChinaCityDataBean.CityListBean.AreaListBean areaListBean : areaListBeanList) {
                if (!TextUtils.isEmpty(area)){
                    if (area.equals(areaListBean.getName())){
                        areaListBean.setSelect(true);
                    }else {
                        areaListBean.setSelect(false);
                    }
                }
            }

        }else {
            provinceList.get(0).setSelect(true);
            mProvinceAdapter.notifyDataSetChanged();
            initCityList(0);
            initAreaList(0);
        }

        mProvinceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (provincePerPosition != position){
                    province = provinceList.get(position).getName();
                    provinceList.get(provincePerPosition).setSelect(false);
                    provinceList.get(position).setSelect(true);
                    mProvinceAdapter.notifyDataSetChanged();
                    //设置右列表数据,如果网络请求需添加一个id用于下方查询填值
                    initCityList(position);
                    cityPerPosition = 0;
                    mCityAdapter.notifyDataSetChanged();
                    provincePerPosition = position;
                    if (provinceList.get(position).getCityList()==null){
                        submitProvincePerPosition = provincePerPosition;
                        cityPopWindow.dismiss();
                    }
                    if (provinceList.get(position).getName().equals("全国")){
                        province = null;
                        area = null;
                        city = null;
                        submitCity = null;
                        addr = null;
                        if (tabPosition==0){
                            dataBinding.tvCity.setText("全国");
                            if (TextUtils.isEmpty(office)){
                                if (TextUtils.isEmpty(professional)){
                                    viewModel.getDoctorList(null,null,null,null,null);
                                }else {
                                    viewModel.getDoctorList(null,null,null,null,professional);
                                }
                            }else {
                                if (TextUtils.isEmpty(professional)){
                                    viewModel.getDoctorList(null,null,String.valueOf(departmentId),null,null);
                                }else {
                                    viewModel.getDoctorList(null,null,String.valueOf(departmentId),null,professional);
                                }
                            }
                        }else {
                            dataBinding.tvHospitalCity.setText("全国");
                            if (TextUtils.isEmpty(grade)){
                                viewModel.getHospitalList(null,null,null);
                            }else {
                                viewModel.getHospitalList(null,null,String.valueOf(gradeId));
                            }
                        }
                    }
                }
            }
        });

        mCityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (cityListBeanList.size()==1){
                    city = cityListBeanList.get(position).getName();
                    cityListBeanList.get(position).setSelect(true);
                    mCityAdapter.notifyDataSetChanged();
                    initAreaList(position);
                    mAreaAdapter.notifyDataSetChanged();
                }else {
                    if (cityPerPosition != position){
                        city = cityListBeanList.get(position).getName();
                        cityListBeanList.get(cityPerPosition).setSelect(false);
                        cityListBeanList.get(position).setSelect(true);
                        mCityAdapter.notifyDataSetChanged();
                        initAreaList(position);
                        mAreaAdapter.notifyDataSetChanged();
                        cityPerPosition = position;
                    }else {
                        initAreaList(0);
                        mAreaAdapter.notifyDataSetChanged();
                    }
                }

            }
        });

        mAreaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (TextUtils.isEmpty(city)){
                    ToastUtils.showShort(mContext,"请选择所在城市");
                }else {
                    area = areaListBeanList.get(position).getName();
                    submitCity = city;
                    addr = province+","+submitCity+","+area;
                    submitProvincePerPosition = provincePerPosition;
                    cityPopWindow.dismiss();
                    if (tabPosition==0){
                        dataBinding.tvCity.setText(area);
                        if (TextUtils.isEmpty(office)){
                            if (TextUtils.isEmpty(professional)){
                                viewModel.getDoctorList(null,addr,null,null,null);
                            }else {
                                viewModel.getDoctorList(null,addr,null,null,professional);
                            }
                        }else {
                            if (TextUtils.isEmpty(professional)){
                                viewModel.getDoctorList(null,addr,String.valueOf(departmentId),null,null);
                            }else {
                                viewModel.getDoctorList(null,addr,String.valueOf(departmentId),null,professional);
                            }
                        }
                    }else {
                        dataBinding.tvHospitalCity.setText(area);
                        if (TextUtils.isEmpty(grade)){
                            viewModel.getHospitalList(null,addr,null);
                        }else {
                            viewModel.getHospitalList(null,addr,String.valueOf(gradeId));
                        }
                    }
                }
            }
        });

        cityPopWindow.setOnDismissListener(this);
        cityPopWindow.setWidth(screenWidth);
        cityPopWindow.setHeight(getWindowManager().getDefaultDisplay().getHeight() *1/2);
//        bgAlpha(0.5f);
        cityPopWindow.setContentView(departmentView);
        cityPopWindow.setBackgroundDrawable(new PaintDrawable());
        cityPopWindow.setFocusable(false);
        cityPopWindow.setOutsideTouchable(true);
        if (tabPosition==0){
            cityPopWindow.showAsDropDown(dataBinding.layoutDropDoctorMenu,0,0);
        }else {
            cityPopWindow.showAsDropDown(dataBinding.layoutDropHospitalMenu,0,0);
        }
    }

    public void showRankPop(){
        rankBeans = new ArrayList<>();
        professionalBeans = new ArrayList<>();
        List<RankBean.DataBean.ListBean> professionalBean1 = new ArrayList<>();
        professionalBean1.add(new RankBean.DataBean.ListBean("院士"));
        professionalBean1.add(new RankBean.DataBean.ListBean("首席专家"));
        professionalBean1.add(new RankBean.DataBean.ListBean("全国主任"));
        professionalBean1.add(new RankBean.DataBean.ListBean("全国常委"));
        professionalBean1.add(new RankBean.DataBean.ListBean("全国委员"));
        rankBeans.add(new RankBean.DataBean("国家级",professionalBean1));

        List<RankBean.DataBean.ListBean> professionalBean2 = new ArrayList<>();
        professionalBean2.add(new RankBean.DataBean.ListBean("省级主任"));
        professionalBean2.add(new RankBean.DataBean.ListBean("省级常委"));
        professionalBean2.add(new RankBean.DataBean.ListBean("省级委员"));
        rankBeans.add(new RankBean.DataBean("省级",professionalBean2));

        List<RankBean.DataBean.ListBean> professionalBean3 = new ArrayList<>();
        professionalBean3.add(new RankBean.DataBean.ListBean("市级主任"));
        professionalBean3.add(new RankBean.DataBean.ListBean("市级常委"));
        professionalBean3.add(new RankBean.DataBean.ListBean("市级委员"));
        rankBeans.add(new RankBean.DataBean("地级市",professionalBean3));

        professionalBeans.addAll(professionalBean1);
        professionalBeans.addAll(professionalBean2);
        professionalBeans.addAll(professionalBean3);

        View departmentView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_department,null,false);
        RecyclerView leftRecyclerview = departmentView.findViewById(R.id.main_recycleView);
        RecyclerView rightRecyclerview = departmentView.findViewById(R.id.second_recycleView);

        leftRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rightRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        if (TextUtils.isEmpty(professional)){
            rankBeans.get(0).setSelect(true);
            initRankSecondList(0);
        }else {
//            for (int i = 0; i < rankBeans.size(); i++) {
//                if (!TextUtils.isEmpty(submitRank)){
//                    if (submitRank.equals(rankBeans.get(i).getName())){
//                        rankBeans.get(i).setSelect(true);
//                    }else {
//                        rankBeans.get(i).setSelect(false);
//                    }
//                }
//            }
            if (rankNewPosition != rankPerPosition){
                rankPerPosition = rankNewPosition;
            }
            rankBeans.get(rankPerPosition).setSelect(true);
            initRankSecondList(rankPerPosition);
            for (int i = 0; i < professionalBeans.size(); i++) {
                if (!TextUtils.isEmpty(professional)){
                    if (professional.equals(professionalBeans.get(i).getName())){
                        professionalBeans.get(i).setSelect(true);
                    }else {
                        professionalBeans.get(i).setSelect(false);
                    }
                }

            }
        }

        mRankLeftAdapter = new RankLeftAdapter(rankBeans,mContext);
        leftRecyclerview.setAdapter(mRankLeftAdapter);
        mRankRightAdapter = new RankRightAdapter(professionalBeans,mContext);
        rightRecyclerview.setAdapter(mRankRightAdapter);


        mRankLeftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (rankPerPosition != position){
                    rankBeans.get(rankPerPosition).setSelect(false);
                    rankBeans.get(position).setSelect(true);
                    mRankLeftAdapter.notifyDataSetChanged();
                    //设置右列表数据,如果网络请求需添加一个id用于下方查询填值
                    initRankSecondList(position);
                    mRankRightAdapter.notifyDataSetChanged();
                    rankPerPosition = position;
                }
            }
        });
        mRankRightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                professional = professionalBeans.get(position).getName();
                dataBinding.tvRank.setText(professional);
                rankNewPosition = rankPerPosition;
                rankPopupWindow.dismiss();

                if (TextUtils.isEmpty(addr)){
                    if (TextUtils.isEmpty(office)){
                        viewModel.getDoctorList(null,null,null,null,professional);
                    }else {
                        viewModel.getDoctorList(null,null,String.valueOf(departmentId),null,professional);
                    }
                }else {
                    if (TextUtils.isEmpty(office)){
                        viewModel.getDoctorList(null,addr,null,null,professional);
                    }else {
                        viewModel.getDoctorList(null,addr,String.valueOf(departmentId),null,professional);
                    }
                }
            }
        });
        rankPopupWindow.setOnDismissListener(this);
        rankPopupWindow.setWidth(screenWidth);
        rankPopupWindow.setHeight(getWindowManager().getDefaultDisplay().getHeight() *1/2);
//        bgAlpha(0.5f);
        rankPopupWindow.setContentView(departmentView);
        rankPopupWindow.setBackgroundDrawable(new PaintDrawable());
        rankPopupWindow.setFocusable(false);
        rankPopupWindow.setOutsideTouchable(true);

        rankPopupWindow.showAsDropDown(dataBinding.layoutDropDoctorMenu,0,0);
    }

    public void showDepartmentPop(){
        View departmentView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_department,null,false);
        RecyclerView leftRecyclerview = departmentView.findViewById(R.id.main_recycleView);
        RecyclerView rightRecyclerview = departmentView.findViewById(R.id.second_recycleView);
        leftBeans = new ArrayList<>();
        rightBeans = new ArrayList<>();
        leftRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rightRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mLeftDepartmentAdapter = new LeftDepartmentAdapter(leftBeans,mContext);
        leftRecyclerview.setAdapter(mLeftDepartmentAdapter);
        mRightDepartmentAdapter = new RightDepartmentAdapter(rightBeans,mContext);
        rightRecyclerview.setAdapter(mRightDepartmentAdapter);
        mLeftDepartmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (departmentPerPosition != position){
                    leftBeans.get(departmentPerPosition).setSelect(false);
                    leftBeans.get(position).setSelect(true);
                    mLeftDepartmentAdapter.notifyDataSetChanged();
                    //设置右列表数据,如果网络请求需添加一个id用于下方查询填值
                    initSecondList(position);
                    mRightDepartmentAdapter.notifyDataSetChanged();
                    departmentPerPosition = position;
                    if (leftBeans.get(position).getList()==null){
                        submitDepartmentPerPosition = departmentPerPosition;
                        departMentPopupWindow.dismiss();
                    }
                    if (leftBeans.get(position).getName().equals("全部科室")){
                        office = null;
                        dataBinding.tvDepartment.setText("全部科室");

                        if (TextUtils.isEmpty(addr)){
                            if (TextUtils.isEmpty(professional)){
                                viewModel.getDoctorList(null,null,null,null,null);
                            }else {
                                viewModel.getDoctorList(null,null,null,null,professional);
                            }
                        }else {
                            if (TextUtils.isEmpty(professional)){
                                viewModel.getDoctorList(null,addr,null,null,null);
                            }else {
                                viewModel.getDoctorList(null,addr,null,null,professional);
                            }
                        }
                    }
                }
            }
        });
        mRightDepartmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                office = rightBeans.get(position).getName();
                dataBinding.tvDepartment.setText(office);
                departmentId = rightBeans.get(position).getId();
                submitDepartmentPerPosition = departmentPerPosition;
                departMentPopupWindow.dismiss();

                if (TextUtils.isEmpty(addr)){
                    if (TextUtils.isEmpty(professional)){
                        viewModel.getDoctorList(null,null,String.valueOf(departmentId),null,null);
                    }else {
                        viewModel.getDoctorList(null,null,String.valueOf(departmentId),null,professional);
                    }
                }else {
                    if (TextUtils.isEmpty(professional)){
                        viewModel.getDoctorList(null,addr,String.valueOf(departmentId),null,null);
                    }else {
                        viewModel.getDoctorList(null,addr,String.valueOf(departmentId),null,professional);
                    }
                }

            }
        });
        departMentPopupWindow.setOnDismissListener(this);
        departMentPopupWindow.setWidth(screenWidth);
        departMentPopupWindow.setHeight(getWindowManager().getDefaultDisplay().getHeight() *1/2);
//        bgAlpha(0.5f);
        departMentPopupWindow.setContentView(departmentView);
        departMentPopupWindow.setBackgroundDrawable(new PaintDrawable());
        departMentPopupWindow.setFocusable(false);
        departMentPopupWindow.setOutsideTouchable(true);
        departMentPopupWindow.showAsDropDown(dataBinding.layoutDropDoctorMenu,0,0);

    }

    public void showGradePop(){
        gradeBeans = new ArrayList<>();
        View gradeView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_grade,null,false);
        RecyclerView gradeRecyclerview = gradeView.findViewById(R.id.grade_recycleView);

        gradeRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mGradeAdapter = new GradeAdapter(gradeBeans,mContext);
        gradeRecyclerview.setAdapter(mGradeAdapter);

        mGradeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (gradeBeans.get(position).getName().equals("全部")){
                    grade = null;
                    dataBinding.tvHospitalRank.setText("按等级");
                    gradeId = 0;
                    if (TextUtils.isEmpty(addr)){
                        viewModel.getHospitalList(null,null,null);
                    }else {
                        viewModel.getHospitalList(null,addr,null);
                    }
                }else {
                    gradeId = gradeBeans.get(position).getId();
                    grade = gradeBeans.get(position).getName();
                    dataBinding.tvHospitalRank.setText(grade);
                    if (TextUtils.isEmpty(addr)){
                        viewModel.getHospitalList(null,null,String.valueOf(gradeId));
                    }else {
                        viewModel.getHospitalList(null,addr,String.valueOf(gradeId));
                    }
                }
                gradeBeans.get(gradePerPosition).setSelect(false);
                gradeBeans.get(position).setSelect(true);
                mGradeAdapter.notifyDataSetChanged();
                gradePerPosition = position;
                gradePopupWindow.dismiss();
            }
        });
        gradePopupWindow.setOnDismissListener(this);
        gradePopupWindow.setWidth(screenWidth);
        gradePopupWindow.setHeight(getWindowManager().getDefaultDisplay().getHeight() *1/2);
//        bgAlpha(0.5f);
        gradePopupWindow.setContentView(gradeView);
        gradePopupWindow.setBackgroundDrawable(new PaintDrawable());
        gradePopupWindow.setFocusable(false);
        gradePopupWindow.setOutsideTouchable(true);
        gradePopupWindow.showAsDropDown(dataBinding.layoutDropHospitalMenu,0,0);
    }

    private void initSecondList(int position) {
        if (rightBeans != null && rightBeans.size()>0){
            rightBeans.clear();
        }
        if (leftBeans.get(position).getList()!=null && leftBeans.get(position).getList().size()>0){
            for (DepartMentResponse.DataBean.ListBean dataBean : leftBeans.get(position).getList()){
                rightBeans.add(dataBean);
            }
        }
//        secondOfficeAdapter.setNewData(secondDatas);
    }

    private void initRankSecondList(int position) {
        if (professionalBeans != null && professionalBeans.size()>0){
            professionalBeans.clear();
        }
        if (rankBeans.get(position).getList()!=null && rankBeans.get(position).getList().size()>0){
            for (RankBean.DataBean.ListBean dataBean : rankBeans.get(position).getList()){
                professionalBeans.add(dataBean);
            }
        }else {
            professionalBeans.add(null);
        }
//        secondOfficeAdapter.setNewData(secondDatas);
    }

    private void initCityList(int position){
        if (cityListBeanList != null && cityListBeanList.size()>0){
            cityListBeanList.clear();
        }
        if (provinceList.get(position).getCityList() != null && provinceList.get(position).getCityList().size()>0){
            for (ChinaCityDataBean.CityListBean cityListBean: provinceList.get(position).getCityList()){
                cityListBeanList.add(cityListBean);
            }
            for (int i = 0; i < cityListBeanList.size(); i++) {
                if (!TextUtils.isEmpty(area)){
                    if (!TextUtils.isEmpty(submitCity)){
                        if (cityListBeanList.size()==1){
                            if (submitCity.equals(cityListBeanList.get(0).getName())){
                                cityListBeanList.get(0).setSelect(true);
                            }else {
                                cityListBeanList.get(0).setSelect(false);
                            }
                            initAreaList(0);
                        }else {
                            if (submitCity.equals(cityListBeanList.get(i).getName())){
                                initAreaList(i);
                            }
                        }
                    }else {
                        initAreaList(0);
                    }
                }else {
                    initAreaList(0);
                }
                mAreaAdapter.notifyDataSetChanged();
            }
        }

    }

    private void initAreaList(int position){
        if (areaListBeanList != null && areaListBeanList.size()>0){
            areaListBeanList.clear();
        }
        if (cityListBeanList!=null && cityListBeanList.size()>0){
            if (cityListBeanList.get(position).getCityList() != null && cityListBeanList.get(position).getCityList().size()>0){
                for (ChinaCityDataBean.CityListBean.AreaListBean areaListBean: cityListBeanList.get(position).getCityList()){
                    areaListBeanList.add(areaListBean);
                }
            }
        }

    }


    @Override
    public void initViewListener() {
        super.initViewListener();
        mTitleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_famous_hall;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    @Override
    public void onDismiss() {
        bgAlpha(1);
        if (departMentPopupWindow.isFocusable()){
            departMentPopupWindow.setFocusable(false);
        }else {
            departMentPopupWindow.setFocusable(true);
        }
        if (cityPopWindow.isFocusable()){
            cityPopWindow.setFocusable(false);
        }else {
            cityPopWindow.setFocusable(true);
        }
        if (rankPopupWindow.isFocusable()){
            rankPopupWindow.setFocusable(false);
        }else {
            rankPopupWindow.setFocusable(true);
        }
        if (gradePopupWindow.isFocusable()){
            gradePopupWindow.setFocusable(false);
        }else {
            gradePopupWindow.setFocusable(true);
        }
        dataBinding.tvDepartment.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
        dataBinding.tvDepartment.setTextColor(getColor(R.color.black));
        dataBinding.tvRank.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
        dataBinding.tvRank.setTextColor(getColor(R.color.black));
        dataBinding.tvCity.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
        dataBinding.tvCity.setTextColor(getColor(R.color.black));
        dataBinding.tvHospitalCity.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
        dataBinding.tvHospitalCity.setTextColor(getColor(R.color.colorBlack));
        dataBinding.tvHospitalRank.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
        dataBinding.tvHospitalRank.setTextColor(getColor(R.color.colorBlack));
    }
    /**
     * @Title: initScreenWidth
     * @Description: 查看自身的宽高
     * @author lmw
     * @return void 返回类型
     */
    private void initScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        screenHeight = dm.heightPixels;
        screenWidth = dm.widthPixels;
        Log.v("屏幕宽高", "宽度" + screenWidth + "高度" + screenHeight);
    }

    private void bgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }
    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
