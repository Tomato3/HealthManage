package com.example.healthmanage.ui.activity.integral.ui;

import android.content.Context;
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
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityIntegrallDetailBinding;
import com.example.healthmanage.ui.activity.integral.IntegralViewModel;
import com.example.healthmanage.ui.activity.integral.adapter.IntegralDetailAdapter;
import com.example.healthmanage.ui.activity.integral.response.IntegralDetailResponse;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * desc:积分明细
 * date:2021/7/19 9:35
 * author:bWang
 */
public class IntegralDetailActivity extends BaseActivity<ActivityIntegrallDetailBinding, IntegralViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context mContext;
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private IntegralDetailAdapter mIntegralDetailAdapter;
    private List<IntegralDetailResponse.DataBean> mDataBeans;
    Calendar calendar = Calendar.getInstance();
    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";// 中文正则
    private String year;
    private String selectYear;
    private String month;
    private String selectMonth;
    PopupWindow popupWindow;
    private int type = 0;

    @Override
    protected void initData() {
        mContext = IntegralDetailActivity.this;
        mTitleToolBar.setTitle("积分明细");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutIntegralDetailTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);
        if (TextUtils.isEmpty(getIntent().getStringExtra("points"))){
            dataBinding.tvPoints.setText("当前积分:"+0);
        }else {
            dataBinding.tvPoints.setText("当前积分:"+getIntent().getStringExtra("points"));
        }

        if (TextUtils.isEmpty(selectMonth)){
            if (calendar.get(Calendar.MONTH)+1<10){
                month = "0"+(calendar.get(Calendar.MONTH)+1);
            }else {
                month = String.valueOf(calendar.get(Calendar.MONTH)+1);
            }
        }else {
            month = selectMonth;
        }
        if (TextUtils.isEmpty(selectYear)){
            year = String.valueOf(calendar.get(Calendar.YEAR));
        }else {
            year = selectYear;
        }
        viewModel.mDateMutableLiveData.setValue(year+"年"+month+"月");

        viewModel.getIntegralDetail(type,year+"-"+month);
        mDataBeans = new ArrayList<>();
        mIntegralDetailAdapter = new IntegralDetailAdapter(mDataBeans);
        dataBinding.recyclerViewIntegralDetail.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_divider));
        if (dataBinding.recyclerViewIntegralDetail.getItemDecorationCount()==0){
            dataBinding.recyclerViewIntegralDetail.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewIntegralDetail.setAdapter(mIntegralDetailAdapter);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_income:
                        type = 0;
                        viewModel.getIntegralDetail(type,year+"-"+month);
                        break;
                    case R.id.rb_expenditure:
                        type = 1;
                        viewModel.getIntegralDetail(type,year+"-"+month);
                        break;
                }
            }
        });
        dataBinding.tvChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(Integer.valueOf(month)-1);
            }
        });
        viewModel.mIntegralDetailListMutableLiveData.observe(this, new Observer<List<IntegralDetailResponse.DataBean>>() {
            @Override
            public void onChanged(List<IntegralDetailResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewIntegralDetail.setVisibility(View.VISIBLE);
                    dataBinding.tvNoData.setVisibility(View.GONE);
                    if (mDataBeans!=null && mDataBeans.size()>0){
                        mDataBeans.clear();
                    }
                    mDataBeans.addAll(dataBeans);
                    mIntegralDetailAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewIntegralDetail.setVisibility(View.GONE);
                    dataBinding.tvNoData.setVisibility(View.VISIBLE);
                }
            }
        });
        dataBinding.swipeRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
                dataBinding.swipeRefresh.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
                viewModel.getIntegralDetail(type,year+"-"+month);
                dataBinding.swipeRefresh.finishRefresh(200);
            }
        });
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
        datePicker.init(Integer.valueOf(year),date,calendar.get(Calendar.DAY_OF_MONTH),null);
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
                viewModel.mDateMutableLiveData.postValue(year+"年"+String.valueOf(datePicker.getMonth()+1)+"月");
                year = String.valueOf(datePicker.getYear());
                selectYear = String.valueOf(datePicker.getYear());
                if (datePicker.getMonth()+1<10){
                    selectMonth = "0"+String.valueOf(datePicker.getMonth()+1);
                    month = selectMonth;
                    viewModel.getIntegralDetail(type,datePicker.getYear()+"-0"+(datePicker.getMonth()+1));
                }else {
                    selectMonth = String.valueOf(datePicker.getMonth()+1);
                    month = selectMonth;
                    viewModel.getIntegralDetail(type,datePicker.getYear()+"-"+(datePicker.getMonth()+1));
                }
                mIntegralDetailAdapter.notifyDataSetChanged();
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

    private void bgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
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
        return R.layout.activity_integrall_detail;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
