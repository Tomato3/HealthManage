package com.example.healthmanage.ui.activity.memberdetail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityMemberDetailBinding;
import com.example.healthmanage.utils.LocationUtil;
import com.example.healthmanage.dialog.EditTextDialog;
import com.example.healthmanage.widget.DropdownBar;
import com.example.healthmanage.widget.TitleToolBar;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;
import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MemberDetailActivity extends BaseActivity<ActivityMemberDetailBinding,
        MemberDetailViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {

    private TitleToolBar titleToolBar = new TitleToolBar();
    private Bundle bundle;
    private boolean b;
    private int memberId;
    private String memberName;
    private EditTextDialog editTextDialog;
    DropdownBar dropdownBarTodayEnvironment;

    @Override
    protected void initData() {
        bundle = this.getIntent().getExtras();
        memberName = bundle.getString("MemberName");
        memberId = bundle.getInt("MemberId");
        b = bundle.getBoolean("FocusState");
        titleToolBar.setTitle(memberName + "会员详情");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setRightIconVisible(true);
        titleToolBar.setRightIconSrc(R.drawable.ic_md_more_vert);
        dataBinding.includeTitle.ivRight.setVisibility(View.INVISIBLE);
        dataBinding.includeTitle.ivRight.setImageResource(R.drawable.ic_md_more_vert);
        showFocus(b);
        viewModel.setTitleToolBar(titleToolBar);
        LocationUtil.getAddress(this);

        LocationUtil.city.observe(this, String -> {
            if (String != null) {
                viewModel.location.setValue(LocationUtil.city.getValue().substring(0, 2));
                viewModel.getWeather();
                LocationUtil.stopLocation();
            }
        });

//        viewModel.getHealthDataList(String.valueOf(memberId));

        /**空气检测仪数据*/
        viewModel.getAirList(String.valueOf(memberId));

        /**护理仪数据*/
        viewModel.getNursingData(memberId);

        dropdownBarTodayEnvironment = new DropdownBar("今日环境", false,
                viewModel.todayAirVisible.getValue(), false);
        DropdownBar dropdownBarTodayHealth = new DropdownBar("今日健康", false, false, false);
        DropdownBar dropdownBarBodyHealth = new DropdownBar("身体健康", viewModel.bodyHealthVisible.getValue(), false, false);
        DropdownBar dropdownBarSpiritHealth = new DropdownBar("精神健康", false, false, false);
        DropdownBar dropdownBarHealthDataAnalyse = new DropdownBar("健康数据分析", false, false, false);
        DropdownBar dropdownBarInspectionQuantity = new DropdownBar("定期服务", false, false, false);
        ArrayList<DropdownBar> dropdownBarArrayList = new ArrayList<>();
        dropdownBarArrayList.add(dropdownBarTodayEnvironment);
        dropdownBarArrayList.add(dropdownBarTodayHealth);
        dropdownBarArrayList.add(dropdownBarBodyHealth);
        dropdownBarArrayList.add(dropdownBarSpiritHealth);
        dropdownBarArrayList.add(dropdownBarHealthDataAnalyse);
        dropdownBarArrayList.add(dropdownBarInspectionQuantity);
        viewModel.dropdownBarLists.setValue(dropdownBarArrayList);

    }

    @Override
    public void initViewParameters() {
        super.initViewParameters();
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

        viewModel.currentFocusState.observe(this, aBoolean -> {
            LiveEventBus.get("refresh").post("value");
            b = aBoolean;
            if (aBoolean) {
                dataBinding.includeTitle.ivRight.setImageResource(R.drawable.fragment_main_my_member_focus_selected);
            } else {
                dataBinding.includeTitle.ivRight.setImageResource(R.drawable.fragment_main_my_member_focus_normal);
            }
        });


        viewModel.todayAirVisible.observe(this, aBoolean -> {
            if (aBoolean) {
                dataBinding.includeTodayEnvironment.tvExpand.setVisibility(View.VISIBLE);
                dataBinding.includeTodayEnvironmentNull.setVisibility(View.VISIBLE);
            } else {
                dataBinding.includeTodayEnvironment.tvExpand.setVisibility(View.GONE);
                dataBinding.includeTodayEnvironmentNull.setVisibility(View.GONE);
            }
        });

        viewModel.todayHealthVisible.observe(this, aBoolean -> {
            if (aBoolean) {
                dataBinding.includeTodayHealthNull.setVisibility(View.VISIBLE);
            } else {
                dataBinding.includeTodayHealthNull.setVisibility(View.GONE);
            }
        });

        dataBinding.includeTodayHealth.tvExpand.setOnClickListener(v -> {
            dataBinding.includeTodayHealth.tvExpand.setVisibility(View.GONE);
            dataBinding.includeTodayHealth.tvCollapse.setVisibility(View.VISIBLE);
            dataBinding.includeTodayHealthData.getRoot().setVisibility(View.VISIBLE);
        });

        dataBinding.includeTodayHealth.tvCollapse.setOnClickListener(v -> {
            dataBinding.includeTodayHealth.tvExpand.setVisibility(View.VISIBLE);
            dataBinding.includeTodayHealth.tvCollapse.setVisibility(View.GONE);
            dataBinding.includeTodayHealthData.getRoot().setVisibility(View.GONE);
        });

        dataBinding.includeTodayEnvironment.tvExpand.setOnClickListener(v -> {
            dataBinding.includeTodayEnvironment.tvExpand.setVisibility(View.GONE);
            dataBinding.includeTodayEnvironment.tvCollapse.setVisibility(View.VISIBLE);
            dataBinding.includeTodayEnvironmentDataDevice.getRoot().setVisibility(View.VISIBLE);
        });

        dataBinding.includeTodayEnvironment.tvCollapse.setOnClickListener(v -> {
            dataBinding.includeTodayEnvironment.tvExpand.setVisibility(View.VISIBLE);
            dataBinding.includeTodayEnvironment.tvCollapse.setVisibility(View.GONE);
            dataBinding.includeTodayEnvironmentDataDevice.getRoot().setVisibility(View.GONE);
        });

        dataBinding.tvCreateTask.setOnClickListener(this::onClick);
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    public void onRightIconClick() {
//        viewModel.changeFocus(String.valueOf(memberId), b);
    }

    @Override
    public void onBackIconClick() {
        finish();
    }


    private void showFocus(boolean focus) {
        Log.d(HTAG, "showFocus: ====>" + focus);
        if (focus) {
            titleToolBar.setRightIconSrc(R.drawable.fragment_main_my_member_focus_selected);
        } else {
            titleToolBar.setRightIconSrc(R.drawable.fragment_main_my_member_focus_normal);
        }
    }


    @BindingAdapter("android:ImageSrc")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_create_task:
                editTextDialog = new EditTextDialog(MemberDetailActivity.this,
                        R.layout.dialog_create_task, memberName);
                editTextDialog.show();
                editTextDialog.setOnEditTextDialogClickListener(new EditTextDialog.OnEditTextDialogClickListener() {
                    @Override
                    public void doCreate(List<String> content) {
//                        viewModel.createMyTask(memberId, memberName, content.get(0));
                    }

                    @Override
                    public void doSend() {

                    }
                });
                break;
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_member_detail;
    }
}
