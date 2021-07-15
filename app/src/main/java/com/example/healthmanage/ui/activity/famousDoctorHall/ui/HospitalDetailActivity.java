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
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityHospitalInfoBinding;
import com.example.healthmanage.ui.activity.famousDoctorHall.DoctorHallViewModel;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.DoctorAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.GradeAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.LeftDepartmentAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.RankLeftAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.RankRightAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.RankTitleAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.adapter.RightDepartmentAdapter;
import com.example.healthmanage.ui.activity.famousDoctorHall.bean.RankBean;
import com.example.healthmanage.ui.activity.famousDoctorHall.bean.RankTitleBean;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.DepartMentResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.FamousDoctorListResponse;
import com.example.healthmanage.ui.activity.famousDoctorHall.response.HospitalDetailResponse;
import com.example.healthmanage.utils.StatusBarUitils;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * desc:医院详情
 * date:2021/7/2 16:43
 * author:bWang
 */
public class HospitalDetailActivity extends BaseActivity<ActivityHospitalInfoBinding, DoctorHallViewModel>
        implements TitleToolBar.OnTitleIconClickCallBack,PopupWindow.OnDismissListener {
    private Context mContext;
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private List<FamousDoctorListResponse.DataBean> mDataBeanList;
    private DoctorAdapter mDoctorAdapter;
    private PopupWindow departMentPopupWindow;
    private List<DepartMentResponse.DataBean> leftBeans;
    private List<DepartMentResponse.DataBean.ListBean> rightBeans;
    private LeftDepartmentAdapter mLeftDepartmentAdapter;
    private RightDepartmentAdapter mRightDepartmentAdapter;
    private int departmentPerPosition;
    private int submitDepartmentPerPosition;
    private String professional;
    private String office;
    private int departmentId;
    private int screenWidth;
    private int screenHeight;
    private PopupWindow rankPopupWindow;
    private List<RankBean.DataBean> rankBeans;
    private List<RankBean.DataBean.ListBean> professionalBeans;
    private RankLeftAdapter mRankLeftAdapter;
    private RankRightAdapter mRankRightAdapter;
    private int rankPerPosition;
    private int rankNewPosition;
    private List<RankTitleBean> mRankTitleBeans;
    private RankTitleAdapter mRankTitleAdapter;
    private PopupWindow rankTitlePopWindow;
    private int rankTitlePrePosition;
    private String rankTitle;
    private int hospitalId;
    private HospitalDetailResponse.DataBean mDataBean;


    @Override
    protected void initData() {
        mContext = HospitalDetailActivity.this;
        hospitalId = getIntent().getIntExtra("hospitalId",0);
        mTitleToolBar.setTitle("医院详情");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setRightTitle("医院概括");
        mTitleToolBar.setRightTitleVisible(true);
        dataBinding.layoutHospitalDetailTitle.tvRight.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_hospital_generalization),null,null,null);
        mTitleToolBar.setRightTitleColor(getResources().getColor(R.color.white));
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.ic_back_white);
        viewModel.setTitleToolBar(mTitleToolBar);
        StatusBarUitils.setStatusBar(R.color.colorBlue, false, this);

        viewModel.getHospitalById(getIntent().getIntExtra("hospitalId",0));

        mDataBeanList = new ArrayList<>();
        mDoctorAdapter = new DoctorAdapter(mDataBeanList,mContext);
        dataBinding.recyclerViewDoctor.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_divider));
        if (dataBinding.recyclerViewDoctor.getItemDecorationCount()==0){
            dataBinding.recyclerViewDoctor.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewDoctor.setAdapter(mDoctorAdapter);
        viewModel.getDoctorByHospitalId(null,null,null,null,getIntent().getIntExtra("hospitalId",0));
        initScreenWidth();
        departMentPopupWindow = new PopupWindow(mContext);
        rankPopupWindow = new PopupWindow(mContext);
        rankTitlePopWindow = new PopupWindow(mContext);

        dataBinding.layoutHospitalDetailTitle.tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,HospitalSurveyActivity.class);
                intent.putExtra("survey",mDataBean.getContent());
                intent.putExtra("address",mDataBean.getAddr());
                intent.putExtra("phone",mDataBean.getPhone());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        mDoctorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext,FamousDoctorInfoActivity.class);
                intent.putExtra("systemUserId",mDataBeanList.get(position).getSystemUserId());
                startActivity(intent);
            }
        });
        viewModel.hospitalDetail.observe(this, new Observer<HospitalDetailResponse.DataBean>() {
            @Override
            public void onChanged(HospitalDetailResponse.DataBean dataBean) {
                if (dataBean!=null){
                    mDataBean = dataBean;
                    Glide.with(mContext).load(dataBean.getAvatar()).apply(new RequestOptions().circleCrop())
                            .error(R.drawable.ic_hospital)
                            .into(dataBinding.ivHospitalLogo);
                }
            }
        });

        viewModel.famousDoctorMutableLiveData.observe(this, new Observer<List<FamousDoctorListResponse.DataBean>>() {
            @Override
            public void onChanged(List<FamousDoctorListResponse.DataBean> dataBeans) {
                if (dataBeans != null && dataBeans.size()>0){
                    dataBinding.recyclerViewDoctor.setVisibility(View.VISIBLE);
                    dataBinding.tvDoctorNull.setVisibility(View.GONE);
                    if (mDataBeanList!=null && mDataBeanList.size()>0){
                        mDataBeanList.clear();
                    }
                    mDataBeanList.addAll(dataBeans);
                    mDoctorAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewDoctor.setVisibility(View.GONE);
                    dataBinding.tvDoctorNull.setVisibility(View.VISIBLE);
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

        dataBinding.layoutChooseDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBinding.tvDepartment.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_blue),null);
                dataBinding.tvDepartment.setTextColor(getColor(R.color.colorBlue));
                dataBinding.tvRank.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                dataBinding.tvRank.setTextColor(getColor(R.color.colorBlack));
                dataBinding.tvRankTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                dataBinding.tvRankTitle.setTextColor(getColor(R.color.colorBlack));
                showDepartmentPop();
                viewModel.getHospitalDepartment();
            }
        });
        dataBinding.layoutRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBinding.tvRank.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_blue),null);
                dataBinding.tvRank.setTextColor(getColor(R.color.colorBlue));
                dataBinding.tvDepartment.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                dataBinding.tvDepartment.setTextColor(getColor(R.color.colorBlack));
                dataBinding.tvRankTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                dataBinding.tvRankTitle.setTextColor(getColor(R.color.colorBlack));
                showRankPop();
            }
        });

        dataBinding.layoutRankTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBinding.tvRankTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_blue),null);
                dataBinding.tvRankTitle.setTextColor(getColor(R.color.colorBlue));
                dataBinding.tvRank.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                dataBinding.tvRank.setTextColor(getColor(R.color.colorBlack));
                dataBinding.tvDepartment.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
                dataBinding.tvDepartment.setTextColor(getColor(R.color.colorBlack));
                showRankTitlePop();
            }
        });
        dataBinding.smartRefreshLayoutDoctor.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
                dataBinding.smartRefreshLayoutDoctor.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
                if (!TextUtils.isEmpty(dataBinding.editSearchHospitalDoctor.getText().toString())){
                    dataBinding.editSearchHospitalDoctor.getText().clear();
                }
                departmentPerPosition = 0;
                rankPerPosition=0;
                office = null;
                submitDepartmentPerPosition=0;
                professional = null;
                rankNewPosition=0;
                rankTitle=null;
                rankTitlePrePosition = 0;
                dataBinding.tvDepartment.setText("全部科室");
                dataBinding.tvRank.setText("按职称");
                dataBinding.tvRankTitle.setText("按职级");
                viewModel.getDoctorList(null,null,null,null,null);
                dataBinding.smartRefreshLayoutDoctor.finishRefresh(200);
            }
        });
        dataBinding.editSearchHospitalDoctor.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    View view = getCurrentFocus();
                    hideKeyboard(view.getWindowToken());
                    departmentPerPosition = 0;
                    rankPerPosition=0;
                    office = null;
                    submitDepartmentPerPosition=0;
                    professional = null;
                    rankNewPosition=0;
                    rankTitle=null;
                    rankTitlePrePosition = 0;
                    dataBinding.tvDepartment.setText("全部科室");
                    dataBinding.tvRank.setText("按职称");
                    dataBinding.tvRankTitle.setText("按职级");
                    viewModel.getDoctorByHospitalId(dataBinding.editSearchHospitalDoctor.getText().toString(),null,null,null,hospitalId);
                    return true;
                }
                return false;
            }
        });

    }

    public void showRankTitlePop(){
        mRankTitleBeans = new ArrayList<>();
        mRankTitleBeans.add(new RankTitleBean("全部"));
        mRankTitleBeans.add(new RankTitleBean("主任医师"));
        mRankTitleBeans.add(new RankTitleBean("副主任医师"));
        mRankTitleBeans.add(new RankTitleBean("主治医师"));
        mRankTitleBeans.add(new RankTitleBean("住院医师"));
        View rankTitleView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_grade,null,false);
        RecyclerView gradeRecyclerview = rankTitleView.findViewById(R.id.grade_recycleView);

        gradeRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRankTitleAdapter = new RankTitleAdapter(mRankTitleBeans,mContext);
        gradeRecyclerview.setAdapter(mRankTitleAdapter);

        if (TextUtils.isEmpty(rankTitle)){
            mRankTitleBeans.get(0).setSelect(true);
        }else {
            for (RankTitleBean rankTitleBean : mRankTitleBeans) {
                if (rankTitleBean.getName().equals(rankTitle)){
                    rankTitleBean.setSelect(true);
                }else {
                    rankTitleBean.setSelect(false);
                }
            }
        }

        mRankTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mRankTitleBeans.get(position).getName().equals("全部")){
                    rankTitle = null;
                    dataBinding.tvRankTitle.setText("按职称");
                }else {
                    rankTitle = mRankTitleBeans.get(position).getName();
                    dataBinding.tvRankTitle.setText(rankTitle);
                }
                mRankTitleBeans.get(position).setSelect(true);
                mRankTitleBeans.get(rankTitlePrePosition).setSelect(false);
                mRankTitleAdapter.notifyDataSetChanged();
                rankTitlePrePosition = position;
                rankTitlePopWindow.dismiss();
                if (TextUtils.isEmpty(professional)){
                    if (TextUtils.isEmpty(office)){
                        viewModel.getDoctorByHospitalId(null,null,rankTitle,null,hospitalId);
                    }else {
                        viewModel.getDoctorByHospitalId(null,String.valueOf(departmentId),rankTitle,null,hospitalId);
                    }
                }else {
                    if (TextUtils.isEmpty(office)){
                        viewModel.getDoctorByHospitalId(null,null,rankTitle,professional,hospitalId);
                    }else {
                        viewModel.getDoctorByHospitalId(null,String.valueOf(departmentId),rankTitle,professional,hospitalId);
                    }
                }
            }
        });
        rankTitlePopWindow.setOnDismissListener(this);
        rankTitlePopWindow.setWidth(screenWidth);
        rankTitlePopWindow.setHeight(getWindowManager().getDefaultDisplay().getHeight() *1/2);
//        bgAlpha(0.5f);
        rankTitlePopWindow.setContentView(rankTitleView);
        rankTitlePopWindow.setBackgroundDrawable(new PaintDrawable());
        rankTitlePopWindow.setFocusable(true);
        rankTitlePopWindow.setOutsideTouchable(true);

        rankTitlePopWindow.showAsDropDown(dataBinding.layoutDropDoctorMenu,0,0);
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
                        if (TextUtils.isEmpty(rankTitle)){
                            if (TextUtils.isEmpty(professional)){
                                viewModel.getDoctorByHospitalId(null,null,null,null,hospitalId);
                            }else {
                                viewModel.getDoctorByHospitalId(null,null,null,professional,hospitalId);
                            }
                        }else {
                            if (TextUtils.isEmpty(professional)){
                                viewModel.getDoctorByHospitalId(null,null,rankTitle,null,hospitalId);
                            }else {
                                viewModel.getDoctorByHospitalId(null,null,rankTitle,professional,hospitalId);
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
                if (TextUtils.isEmpty(rankTitle)){
                    if (TextUtils.isEmpty(professional)){
                        viewModel.getDoctorByHospitalId(null,String.valueOf(departmentId),null,null,hospitalId);
                    }else {
                        viewModel.getDoctorByHospitalId(null,String.valueOf(departmentId),null,professional,hospitalId);
                    }
                }else {
                    if (TextUtils.isEmpty(professional)){
                        viewModel.getDoctorByHospitalId(null,String.valueOf(departmentId),rankTitle,null,hospitalId);
                    }else {
                        viewModel.getDoctorByHospitalId(null,String.valueOf(departmentId),rankTitle,professional,hospitalId);
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
        departMentPopupWindow.setFocusable(true);
        departMentPopupWindow.setOutsideTouchable(true);
        departMentPopupWindow.showAsDropDown(dataBinding.layoutDropDoctorMenu,0,0);

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
                if (TextUtils.isEmpty(rankTitle)){
                    if (TextUtils.isEmpty(office)){
                        viewModel.getDoctorByHospitalId(null,null,null,professional,hospitalId);
                    }else {
                        viewModel.getDoctorByHospitalId(null,String.valueOf(departmentId),null,professional,hospitalId);
                    }
                }else {
                    if (TextUtils.isEmpty(office)){
                        viewModel.getDoctorByHospitalId(null,null,rankTitle,professional,hospitalId);
                    }else {
                        viewModel.getDoctorByHospitalId(null,String.valueOf(departmentId),rankTitle,professional,hospitalId);
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
        rankPopupWindow.setFocusable(true);
        rankPopupWindow.setOutsideTouchable(true);

        rankPopupWindow.showAsDropDown(dataBinding.layoutDropDoctorMenu,0,0);
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
        return R.layout.activity_hospital_info;
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
        dataBinding.tvDepartment.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
        dataBinding.tvDepartment.setTextColor(getColor(R.color.black));
        dataBinding.tvRank.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
        dataBinding.tvRank.setTextColor(getColor(R.color.black));
        dataBinding.tvRankTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_drop_black),null);
        dataBinding.tvRankTitle.setTextColor(getColor(R.color.black));
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
