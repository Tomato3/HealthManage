package com.example.healthmanage.ui.activity.workplan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityWorkPlanBinding;
import com.example.healthmanage.ui.activity.signmember.SignMemberActivity;
import com.example.healthmanage.ui.activity.workplan.adapter.WorkPlanAdapter;
import com.example.healthmanage.ui.activity.workplan.adapter.WorkPlanSection;
import com.example.healthmanage.ui.activity.workplan.response.WorkPlanListResponse;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 工作计划
 */
public class WorkPlanActivity extends BaseActivity<ActivityWorkPlanBinding,WorkPlanViewModel>
        implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener ,
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener{
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<WorkPlanSection> workPlanSections;
    private List<WorkPlanListResponse.DataBean> workPlans = new ArrayList<>();
    private WorkPlanAdapter workPlanAdapter;
    private List<WorkPlanSection> sectionListFinished = new ArrayList<>();
    private List<WorkPlanSection> sectionListNotFinished = new ArrayList<>();
    private int mPosition;
    private String chooseDate;
    private String month , day;
    SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getWorkPlanByTime(chooseDate, BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId());
        workPlanAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData(){
        titleToolBar.setTitle("工作计划");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        dataBinding.calendarLayout.setModeOnlyWeekView();
        dataBinding.calendarView.setOnYearChangeListener(this::onYearChange);
        dataBinding.calendarView.setOnCalendarSelectListener(this);
        try {
            chooseDate = sf2.format(sf1.parse(dataBinding.calendarView.getSelectedCalendar().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        viewModel.getWorkPlanByTime(chooseDate, BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId());
        workPlanSections = new ArrayList<>();
        workPlanAdapter = new WorkPlanAdapter(R.layout.item_work_plan_content,R.layout.item_workplan_head,workPlanSections);
        dataBinding.recyclerViewPlan.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_10));
        if (dataBinding.recyclerViewPlan.getItemDecorationCount()==0){
            dataBinding.recyclerViewPlan.addItemDecoration(gridItemDecoration);
        }
//        dataBinding.recyclerViewPlan.addItemDecoration(gridItemDecoration);
        dataBinding.recyclerViewPlan.setAdapter(workPlanAdapter);
        workPlanAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.btn_confirm_plan:
                        viewModel.updateWorkPlanById(workPlanSections.get(position).t.getId(),ToolUtil.getCurTime());
                        mPosition = position;
                        break;
                    case R.id.layout_plan_detail:
                        Bundle bundle = new Bundle();
                        bundle.putString("planContent",workPlanSections.get(position).t.getWorkText());
                        startActivity(WorkPlanDetailActivity.class,bundle);
                        break;
                }
            }
        });
        dataBinding.layoutWritePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkPlanActivity.this,CreateWorkPlanActivity.class);
                intent.putExtra("time",chooseDate);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.workPlanLiveData.observe(this, new Observer<List<WorkPlanListResponse.DataBean>>() {
            @Override
            public void onChanged(List<WorkPlanListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    if (workPlans!=null && workPlans.size()>0){
                        workPlans.clear();
                    }
                    workPlans.addAll(dataBeans);
                    if (sectionListNotFinished!=null && sectionListNotFinished.size()>0){
                        sectionListNotFinished.clear();
                    }
                    if (sectionListFinished!=null && sectionListFinished.size()>0){
                        sectionListFinished.clear();
                    }
                    sectionListNotFinished.add(new WorkPlanSection(true,"待完成"));
                    sectionListFinished.add(new WorkPlanSection(true,"已完成"));
                    for (WorkPlanListResponse.DataBean dataBean:workPlans) {
                        if (dataBean.getStatus().equals("待完成")){
                            sectionListNotFinished.add(new WorkPlanSection(dataBean));
                        }else {
                            sectionListFinished.add(new WorkPlanSection(dataBean));
                        }
                    }
                    if (workPlanSections!=null && workPlanSections.size()>0){
                        workPlanSections.clear();
                    }
                    workPlanSections.addAll(sectionListNotFinished);
                    workPlanSections.addAll(sectionListFinished);
                    workPlanAdapter.notifyDataSetChanged();
                }else {
                    if (workPlans!=null && workPlans.size()>0){
                        workPlans.clear();
                    }
                    if (sectionListNotFinished!=null && sectionListNotFinished.size()>0){
                        sectionListNotFinished.clear();
                    }
                    if (sectionListFinished!=null && sectionListFinished.size()>0){
                        sectionListFinished.clear();
                    }
                    sectionListNotFinished.add(new WorkPlanSection(true,"待完成"));
                    sectionListFinished.add(new WorkPlanSection(true,"已完成"));
                    if (workPlanSections!=null){
                        workPlanSections.clear();
                    }
                    workPlanSections.addAll(sectionListNotFinished);
                    workPlanSections.addAll(sectionListFinished);
                    workPlanAdapter.notifyDataSetChanged();
                }
            }
        });
        viewModel.updateSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    workPlanSections.remove(mPosition);
                    viewModel.getWorkPlanByTime(chooseDate,BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId());
                    workPlanAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(WorkPlanActivity.this, "服务器错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_work_plan;
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        if (calendar.getMonth()<10){
            month = "0" +calendar.getMonth();
        }else {
            month = String.valueOf(calendar.getMonth());
        }
        if (calendar.getDay()<10){
            day = "0" +calendar.getDay();
        }else {
            day = String.valueOf(calendar.getDay());
        }
        chooseDate = calendar.getYear()+"-"+month+"-"+day;
        viewModel.getWorkPlanByTime(chooseDate,BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId());
        workPlanAdapter.notifyDataSetChanged();
    }

    @Override
    public void onYearChange(int year) {

    }
}
