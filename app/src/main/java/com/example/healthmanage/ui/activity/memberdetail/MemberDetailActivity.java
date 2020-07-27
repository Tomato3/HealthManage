package com.example.healthmanage.ui.activity.memberdetail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.Observer;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityMemberDetailBinding;
import com.example.healthmanage.view.EditTextDialog;
import com.example.healthmanage.widget.DropdownBar;
import com.example.healthmanage.widget.TitleToolBar;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MemberDetailActivity extends BaseActivity<ActivityMemberDetailBinding,
        MemberDetailViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {

    TitleToolBar titleToolBar = new TitleToolBar();
    Bundle bundle;
    boolean b;
    int userId;
    String memberName;
    EditTextDialog editTextDialog;

    @Override
    protected void initData() {
        bundle = this.getIntent().getExtras();
        memberName = bundle.getString("MemberName");
        userId = bundle.getInt("UserId");
        b = bundle.getBoolean("FocusState");
        titleToolBar.setTitle(memberName + "会员详情");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setRightIconVisible(true);
        showFocus(b);
        viewModel.setTitleToolBar(titleToolBar);


        DropdownBar dropdownBarTodayEnvironment = new DropdownBar("今日环境", false, true, false);
        DropdownBar dropdownBarTodayHealth = new DropdownBar("今日健康", false, true, false);
        DropdownBar dropdownBarBodyHealth = new DropdownBar("身体健康", true, false, false);
        DropdownBar dropdownBarSpiritHealth = new DropdownBar("精神健康", true, false, false);
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

        viewModel.getWeather();

        viewModel.getHealthDataList(String.valueOf(userId));

    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_member_detail;
    }

    @Override
    public void initViewParameters() {
        super.initViewParameters();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();


        viewModel.currentFocusState.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                LiveEventBus.get("refresh").post("value");
                b = aBoolean;
                if (aBoolean) {
                    dataBinding.includeTitle.ivRight.setImageResource(R.drawable.fragment_main_my_member_focus_selected);
                } else {
                    dataBinding.includeTitle.ivRight.setImageResource(R.drawable.fragment_main_my_member_focus_normal);
                }
            }
        });

        dataBinding.includeTodayHealth.tvExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBinding.includeTodayHealth.tvExpand.setVisibility(View.GONE);
                dataBinding.includeTodayHealth.tvCollapse.setVisibility(View.VISIBLE);
                dataBinding.includeTodayHealthData.getRoot().setVisibility(View.VISIBLE);
            }
        });

        dataBinding.includeTodayHealth.tvCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBinding.includeTodayHealth.tvExpand.setVisibility(View.VISIBLE);
                dataBinding.includeTodayHealth.tvCollapse.setVisibility(View.GONE);
                dataBinding.includeTodayHealthData.getRoot().setVisibility(View.GONE);
            }
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
        viewModel.changeFocus(String.valueOf(userId), b);
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

    private String getLocation() {

        return "";
    }


    @BindingAdapter("android:ImageSrc")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_create_task:
                editTextDialog = new EditTextDialog(MemberDetailActivity.this, R.layout.dialog_create_task);
                editTextDialog.show();
                editTextDialog.setOnEditTextDialogClickListener(new EditTextDialog.OnEditTextDialogClickListener() {
                    @Override
                    public void doCreate(String title, String content) {
                        viewModel.createMyTask(userId, title,
                                content);
                    }

                    @Override
                    public void doCreate(String doctorReplay) {

                    }

                    @Override
                    public void doSend() {

                    }
                });
                break;
        }
    }
}
