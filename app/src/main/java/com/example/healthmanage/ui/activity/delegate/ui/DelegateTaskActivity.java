package com.example.healthmanage.ui.activity.delegate.ui;

import android.content.Context;
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
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityDelegateTaskBinding;
import com.example.healthmanage.ui.activity.delegate.adapter.DelegateAdapter;
import com.example.healthmanage.ui.activity.delegate.response.DelegateListResponse;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 委派任务列表
 * adapter layout item_delegate_task
 */
public class DelegateTaskActivity extends BaseActivity<ActivityDelegateTaskBinding,DelegateViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    PopupWindow popupWindow;
    private List<DelegateListResponse.DataBean> delegateDataBeans;
    private DelegateAdapter delegateAdapter;
    Calendar calendar = Calendar.getInstance();
    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";// 中文正则
    private String year;
    private String selectMonth;
    private String selectYear;
    private String month;

    @Override
    protected void initData() {
        context = DelegateTaskActivity.this;
        titleToolBar.setTitle("委派任务");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        delegateDataBeans = new ArrayList<>();
        delegateAdapter = new DelegateAdapter(context,delegateDataBeans);
        dataBinding.recyclerViewDelegateTask.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        if (dataBinding.recyclerViewDelegateTask.getItemDecorationCount()==0){
            dataBinding.recyclerViewDelegateTask.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewDelegateTask.setAdapter(delegateAdapter);
        if (TextUtils.isEmpty(selectMonth)){
            if (calendar.get(Calendar.MONTH)+1<10){
                month = "0"+(calendar.get(Calendar.MONTH)+1);
            }else {
                month = String.valueOf(calendar.get(Calendar.MONTH)+1);
            }
        }else {
            month = selectMonth;
        }
        viewModel.dateChoose.setValue(month+"月");
        if (TextUtils.isEmpty(selectYear)){
            year = String.valueOf(calendar.get(Calendar.YEAR));
        }else {
            year = selectYear;
        }
        viewModel.getBusineService(year+"-"+month);
        delegateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_deal_result:
                        Intent intent = new Intent(context,DelegateInfoActivity.class);
                        intent.putExtra("dataBean",delegateDataBeans.get(position));
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        delegateAdapter.notifyDataSetChanged();
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                dataBinding.smartRefreshLayout.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.getBusineService(year+"-"+month);
                delegateAdapter.notifyDataSetChanged();
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });
        dataBinding.tvChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(Integer.valueOf(viewModel.dateChoose.getValue().replaceAll(REGEX_CHINESE,""))-1);
            }
        });
        viewModel.delegateLiveData.observe(this, new Observer<List<DelegateListResponse.DataBean>>() {
            @Override
            public void onChanged(List<DelegateListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewDelegateTask.setVisibility(View.VISIBLE);
                    dataBinding.layoutDelegateTaskNull.setVisibility(View.GONE);
                    if (delegateDataBeans!=null && delegateDataBeans.size()>0){
                        delegateDataBeans.clear();
                    }
                    delegateDataBeans.addAll(dataBeans);
                    delegateAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewDelegateTask.setVisibility(View.GONE);
                    dataBinding.layoutDelegateTaskNull.setVisibility(View.VISIBLE);
                }
            }
        });
        dataBinding.layoutWriteDelegateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CreateDelegateTaskActivity.class);
//                Intent intent = new Intent(context,CreateDelegateTaskActivity.class);
//                startActivityForResult(intent,2);
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data!=null){
//            if (requestCode==2){
//                if (resultCode==RESULT_OK){
//                    selectMonth = data.getStringExtra("month");
//                    selectYear = data.getStringExtra("year");
//                    viewModel.getBusineService(selectYear+"-"+selectMonth);
//                    delegateAdapter.notifyDataSetChanged();
//                }
//            }
//        }
//    }

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
                viewModel.dateChoose.postValue(String.valueOf(datePicker.getMonth()+1)+"月");
                year = String.valueOf(datePicker.getYear());
                selectYear = String.valueOf(datePicker.getYear());
                if (datePicker.getMonth()+1<10){
                    selectMonth = "0"+String.valueOf(datePicker.getMonth()+1);
                    month = selectMonth;
                    viewModel.getBusineService(datePicker.getYear()+"-0"+(datePicker.getMonth()+1));
                }else {
                    selectMonth = String.valueOf(datePicker.getMonth()+1);
                    month = selectMonth;
                    viewModel.getBusineService(datePicker.getYear()+"-"+(datePicker.getMonth()+1));
                }
                delegateAdapter.notifyDataSetChanged();
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
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_delegate_task;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
