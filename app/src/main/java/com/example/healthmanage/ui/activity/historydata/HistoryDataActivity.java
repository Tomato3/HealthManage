package com.example.healthmanage.ui.activity.historydata;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityHistoryDataBinding;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.view.DataItem;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class HistoryDataActivity extends BaseActivity<ActivityHistoryDataBinding, HistoryDataViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {

    Bundle bundle;
    int memberId;
    String memberName;
    TitleToolBar titleToolBar = new TitleToolBar();
    BaseAdapter historyDataAdapter;
    private DatePickerDialog datePickerDialog;
    int type;

    @Override
    protected void initData() {
        bundle = this.getIntent().getExtras();
        memberId = bundle.getInt("memberId");
        memberName = bundle.getString("memberName");
        titleToolBar.setTitle(memberName + "的历史数据");
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.getHistoryData(memberId, ToolUtil.getStartTime(), ToolUtil.getEndTime(), 1);
    }

    @Override
    public void initViewParameters() {
        super.initViewParameters();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(HTAG, "onDateSet==========>: " + year + "===>" + month + "===>" + dayOfMonth);
                switch (type) {
                    case 1:
                        viewModel.startTime.setValue(year + "-" + (month + 1) + "-" + dayOfMonth);
                        break;
                    case 2:
                        viewModel.endTime.setValue(year + "-" + (month + 1) + "-" + dayOfMonth);
                        break;
                }
            }
        }, ToolUtil.currentDate().get(0), ToolUtil.currentDate().get(1), ToolUtil.currentDate().get(2));
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

        historyDataAdapter = new BaseAdapter(this, null, R.layout.item_data,
                BR.DataItem);
        dataBinding.recyclerViewHistoryData.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewHistoryData.setAdapter(historyDataAdapter);
        viewModel.dataItemMutableLiveData.observe(this, new Observer<List<DataItem>>() {
            @Override
            public void onChanged(List<DataItem> dataItems) {
                historyDataAdapter.setRecyclerViewList(dataItems);
                historyDataAdapter.notifyDataSetChanged();
            }
        });

        dataBinding.tvDatePickerStart.setOnClickListener(this);
        dataBinding.tvDatePickerEnd.setOnClickListener(this);
        dataBinding.tvSearch.setOnClickListener(this::onClick);

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_date_picker_start:
                type = 1;
                datePickerDialog.show();
                break;
            case R.id.tv_date_picker_end:
                type = 2;
                datePickerDialog.show();
                break;
            case R.id.tv_search:
                if (!TextUtils.isEmpty(viewModel.startTime.getValue()) && !TextUtils.isEmpty(viewModel.endTime.getValue())) {
                    if (ToolUtil.compareDate(viewModel.startTime.getValue(), viewModel.endTime.getValue())) {
                        viewModel.getUiChangeEvent().getToastTxt().setValue("结束时间不能小于开始时间");
                    } else {
                        viewModel.getHistoryData(memberId, viewModel.startTime.getValue(),
                                viewModel.endTime.getValue(), 1);
                    }
                }
                break;
        }
    }


    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_history_data;
    }

}
