package com.example.healthmanage.ui.activity.healthreport.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityHealthReportBinding;
import com.example.healthmanage.ui.activity.healthrecord.HealthRecordViewModel;
import com.example.healthmanage.ui.activity.healthreport.adapter.HealthReportAdapter;
import com.example.healthmanage.ui.activity.healthreport.response.HealthReportResponse;
import com.example.healthmanage.ui.activity.healthreport.viewmodel.HealthReportViewModel;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.TitleToolBar;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 健康报告
 */
public class HealthReportActivity extends BaseActivity<ActivityHealthReportBinding, HealthReportViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {
    private TitleToolBar titleToolBar = new TitleToolBar();
    PopupWindow popupWindow;
    private List<HealthReportResponse.DataBean> healthReportList;
    private HealthReportAdapter healthReportAdapter;
    private int userId;
    Calendar calendar = Calendar.getInstance();
    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";// 中文正则
    private int year;
    private int selectMonth;
    private int selectYear;
    private int month;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        healthReportAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void initData() {
        userId = getIntent().getIntExtra("userId",0);
        titleToolBar.setTitle("健康报告");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        healthReportList = new ArrayList<>();
        healthReportAdapter = new HealthReportAdapter(healthReportList);
        dataBinding.recyclerHealthReport.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerHealthReport.setAdapter(healthReportAdapter);
        dataBinding.recyclerHealthReport.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        if (selectMonth==0){
            month = calendar.get(Calendar.MONTH)+1;
        }else {
            month = selectMonth;
        }
        viewModel.dateChoose.setValue(month+"月");
        if (selectYear==0){
            year = calendar.get(Calendar.YEAR);
        }else {
            year = selectYear;
        }
        viewModel.getHealthReportAll(userId,year+"-"+month);
        healthReportAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HealthReportActivity.this,HealthReportDetailActivity.class);
                intent.putExtra("id",healthReportList.get(position).getId());
                intent.putExtra("name",healthReportList.get(position).getName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.tvChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(Integer.valueOf(viewModel.dateChoose.getValue().replaceAll(REGEX_CHINESE,""))-1);
            }
        });
        dataBinding.btnCreateHealthReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthReportActivity.this,CreateHealthReportActivity.class);
                intent.putExtra("userId",userId);
                startActivityForResult(intent,2);
            }
        });
        viewModel.healthReportMutableLiveData.observe(this, new Observer<List<HealthReportResponse.DataBean>>() {
            @Override
            public void onChanged(List<HealthReportResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    if (healthReportList!=null && healthReportList.size()>0){
                        healthReportList.clear();
                    }
                    healthReportList.addAll(dataBeans);
                    healthReportAdapter.notifyDataSetChanged();
                }
                dataBinding.recyclerHealthReport.setVisibility(dataBeans!=null?View.VISIBLE:View.GONE);
                dataBinding.layoutHealthReportNull.setVisibility(dataBeans!=null?View.GONE:View.VISIBLE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            if (requestCode==2){
                if (resultCode==RESULT_OK){
                    selectMonth = data.getIntExtra("month",0);
                    selectYear = data.getIntExtra("year",0);
                    viewModel.getHealthReportAll(userId,selectYear+"-"+selectMonth);
                    healthReportAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void initPopupWindow(int date){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_choose_date, null, false);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.Animation);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0,
                ToolUtil.getNavigationBarHeight(this));
        popupWindow.setFocusable(true);
        bgAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                bgAlpha(1);
            }
        });
        DatePicker datePicker = view.findViewById(R.id.date_picker);
        datePicker.init(year,date,calendar.get(Calendar.DAY_OF_MONTH),null);
        hideDay(datePicker);
        TextView dateCancel = view.findViewById(R.id.tv_date_cancel);
        dateCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        TextView dateConfirm = view.findViewById(R.id.tv_date_confirm);
        dateConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                viewModel.dateChoose.postValue(String.valueOf(datePicker.getMonth()+1)+"月");
                year = datePicker.getYear();
                viewModel.getHealthReportAll(userId,datePicker.getYear()+"-"+(datePicker.getMonth()+1));
                healthReportAdapter.notifyDataSetChanged();
            }
        });
    }

    private void hideDay(DatePicker mDatePicker) {

        try {
            /* 处理android5.0以上的特殊情况 */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int daySpinnerId = Resources.getSystem().getIdentifier("day", "id", "android");
                if (daySpinnerId != 0) {
                    View daySpinner = mDatePicker.findViewById(daySpinnerId);
                    if (daySpinner != null) {
                        daySpinner.setVisibility(View.GONE);
                    }
                }
            } else {
                Field[] datePickerfFields = mDatePicker.getClass().getDeclaredFields();
                for (Field datePickerField : datePickerfFields) {
                    if ("mDaySpinner".equals(datePickerField.getName()) || ("mDayPicker").equals(datePickerField.getName())) {
                        datePickerField.setAccessible(true);
                        Object dayPicker = new Object();
                        try {
                            dayPicker = datePickerField.get(mDatePicker);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                        ((View) dayPicker).setVisibility(View.GONE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_health_report;
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

    private void bgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

}
