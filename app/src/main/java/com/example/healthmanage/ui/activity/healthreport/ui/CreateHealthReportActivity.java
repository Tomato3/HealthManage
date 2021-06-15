package com.example.healthmanage.ui.activity.healthreport.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityCreateHealthReportBinding;
import com.example.healthmanage.ui.activity.healthreport.HealthReportInfo;
import com.example.healthmanage.ui.activity.healthreport.viewmodel.HealthReportViewModel;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.TitleToolBar;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 创建健康报告
 */
public class CreateHealthReportActivity extends BaseActivity<ActivityCreateHealthReportBinding, HealthReportViewModel> implements
        TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener,CalendarView.OnCalendarInterceptListener,
        CalendarView.OnCalendarRangeSelectListener,
        CalendarView.OnMonthChangeListener{
    private TitleToolBar titleToolBar = new TitleToolBar();
    PopupWindow popupWindow;
    private CalendarView mCalendarView;
    private TextView dialogTitle;
    private List<Calendar> calendars;
    private String startDate,endDate;
    private HealthReportInfo healthReportInfo;
    private int userId;
    private int startYear,startMonth,startDay;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {
        titleToolBar.setTitle("健康报告");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        healthReportInfo = new HealthReportInfo();
        userId = getIntent().getIntExtra("userId",0);
        if (userId==0){
            return;
        }else {
            healthReportInfo.setUserId(userId);
        }

        InputFilter.LengthFilter filter = new InputFilter.LengthFilter(100){
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if ((dest.length() - (dend - dstart)) >= 100) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    Toast.makeText(CreateHealthReportActivity.this, "最大限制字数100", Toast.LENGTH_SHORT).show();
                }
                return super.filter(source, start, end, dest, dstart, dend);
            }
        };
        dataBinding.edtHintBloodPressure.setFilters(new InputFilter[]{filter});
        dataBinding.edtHintBloodSugar.setFilters(new InputFilter[]{filter});
        dataBinding.edtHintBloodOxygen.setFilters(new InputFilter[]{filter});
        dataBinding.edtHintTemperature.setFilters(new InputFilter[]{filter});
        dataBinding.edtHintSportStatus.setFilters(new InputFilter[]{filter});
        dataBinding.edtHintSleepStatus.setFilters(new InputFilter[]{filter});
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.tvReportTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow();
            }
        });
        dataBinding.btnConfirmHealthReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(viewModel.bloodPressure.getValue())||TextUtils.isEmpty(viewModel.bloodSugar.getValue())||TextUtils.isEmpty(viewModel.bloodOxygen.getValue())
                ||TextUtils.isEmpty(viewModel.temprature.getValue())||TextUtils.isEmpty(viewModel.sportStatus.getValue())||TextUtils.isEmpty(viewModel.sleepStatus.getValue())
                ||TextUtils.isEmpty(viewModel.reportName.getValue())||TextUtils.isEmpty(viewModel.reportTime.getValue())){
                    ToastUtil.showShort("请将未填写的资料补全");
                    return;
                }else {
                    healthReportInfo.setToken(BaseApplication.getToken());
                    healthReportInfo.setBloodPressure(viewModel.bloodPressure.getValue());
                    healthReportInfo.setBloodSugar(viewModel.bloodSugar.getValue());
                    healthReportInfo.setBloodOxygen(viewModel.bloodOxygen.getValue());
                    healthReportInfo.setName(viewModel.reportName.getValue());
                    healthReportInfo.setTemperature(viewModel.temprature.getValue());
                    healthReportInfo.setSport(viewModel.sportStatus.getValue());
                    healthReportInfo.setSleep(viewModel.sleepStatus.getValue());
                    healthReportInfo.setStartTime(startDate);
                    healthReportInfo.setEndTime(endDate);
                    viewModel.saveHealthReport(healthReportInfo);
                }
            }
        });

        viewModel.saveSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    new UIAlertDialog.DividerIOSBuilder(CreateHealthReportActivity.this).setMessage("健康报告发送成功")
                            .setMessageTextColorResource(R.color.colorBlack)
                            .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent();
                                    intent.putExtra("year",startYear);
                                    intent.putExtra("month",startMonth);
                                    setResult(RESULT_OK,intent);
                                    finish();
//                                    startActivity(HealthReportActivity.class);
//                                    finish();
                                }
                            })
                            .setMinHeight(SizeUtil.dp2px(160))
                            .setPositiveButtonTextColorResource(R.color.colorTxtBlue)
                            .create()
                            .setDimAmount(0.6f)
                            .show();
                }else {
                    return;
                }
            }
        });
    }


    private void initPopupWindow(){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_choose_calendarview, null, false);
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

        Calendar calendar = new Calendar();
        mCalendarView = view.findViewById(R.id.calendarView);
        dialogTitle = view.findViewById(R.id.tv_date_title);
        mCalendarView.setFixMode();
        dialogTitle.setText(calendar.getYear()+"年"+calendar.getMonth()+"月");
        mCalendarView.setOnCalendarRangeSelectListener(this);
        mCalendarView.setOnMonthChangeListener(this);
        //设置日期拦截事件，当前有效
        mCalendarView.setOnCalendarInterceptListener(this);
        mCalendarView.setRange(2000, 1, 1,

                2999, 12, 31

        );
        mCalendarView.post(new Runnable() {
            @Override
            public void run() {
                mCalendarView.scrollToCurrent();
            }
        });
        Button btnConfirm = view.findViewById(R.id.btn_confirm_date);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendars = mCalendarView.getSelectCalendarRange();
                if (mCalendarView.getSelectedCalendar()!=null && calendars.size()==0){
                    viewModel.reportTime.postValue(mCalendarView.getSelectedCalendar().getMonth()+"-"+mCalendarView.getSelectedCalendar().getDay()+"~"+mCalendarView.getSelectedCalendar().getMonth()+"-"+mCalendarView.getSelectedCalendar().getDay());
                    startYear = mCalendarView.getSelectedCalendar().getYear();
                    startMonth = mCalendarView.getSelectedCalendar().getMonth();
                    startDay = mCalendarView.getSelectedCalendar().getDay();
                    startDate = startYear +"-"+startMonth+"-"+startDay;
                    endDate = mCalendarView.getSelectedCalendar().getYear()+"-"+mCalendarView.getSelectedCalendar().getMonth()+"-"+mCalendarView.getSelectedCalendar().getDay();
                }else {
                    if (calendars == null || calendars.size() == 0) {
                        return;
                    }
                    viewModel.reportTime.postValue(calendars.get(0).getMonth()+"-"+calendars.get(0).getDay()+"~"+calendars.get(calendars.size() - 1).getMonth()+"-"+calendars.get(calendars.size() - 1).getDay());
                    startYear = calendars.get(0).getYear();
                    startMonth = calendars.get(0).getMonth();
                    startDay = calendars.get(0).getDay();
                    startDate = startYear +"-"+startMonth+"-"+startDay;
                    endDate = calendars.get(calendars.size()-1).getYear()+"-"+calendars.get(calendars.size()-1).getMonth()+"-"+calendars.get(calendars.size()-1).getDay();
                }
                popupWindow.dismiss();
            }
        });
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_create_health_report;
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

    @Override
    public boolean onCalendarIntercept(Calendar calendar) {
        return false;
    }

    @Override
    public void onCalendarInterceptClick(Calendar calendar, boolean isClick) {

    }

    @Override
    public void onCalendarSelectOutOfRange(Calendar calendar) {

    }

    @Override
    public void onSelectOutOfRange(Calendar calendar, boolean isOutOfMinRange) {
        Toast.makeText(this,
                calendar.toString() + (isOutOfMinRange ? "小于最小选择范围" : "超过最大选择范围"),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCalendarRangeSelect(Calendar calendar, boolean isEnd) {

    }

    @Override
    public void onMonthChange(int year, int month) {
        dialogTitle.setText(year+"年"+month+"月");
    }
}
