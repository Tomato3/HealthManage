package com.example.healthmanage.ui.activity.mystudio;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.PopupMenu;

import androidx.appcompat.view.menu.MenuPopupHelper;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityMyStudioBinding;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.TitleToolBar;

import java.lang.reflect.Field;

import static com.example.healthmanage.utils.Constants.HTAG;


public class MyStudioActivity extends BaseActivity<ActivityMyStudioBinding, MyStudioViewModel> implements View.OnClickListener, TitleToolBar.OnTitleIconClickCallBack {

    private DatePickerDialog datePickerDialog;
    TitleToolBar titleToolBar = new TitleToolBar();

    @Override
    protected void initData() {
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitle("我的工作室");
        titleToolBar.setRightIconVisible(true);
        titleToolBar.setRightIconSrc(R.drawable.toolbar_title_add);
        titleToolBar.setOnClickCallBack(this);
        viewModel.setTitleToolBar(titleToolBar);

        registerUIChangeEventObserver();

    }

    @Override
    public void initViewParameters() {
        super.initViewParameters();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(HTAG, "onDateSet==========>: " + year + "===>" + month + "===>" + dayOfMonth);
                viewModel.date.setValue(year + "/" + month + 1 + "/" + dayOfMonth
                );
            }
        }, ToolUtil.currentDate().get(0), ToolUtil.currentDate().get(1), ToolUtil.currentDate().get(2));
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.tvDatePicker.setOnClickListener(this::onClick);

        dataBinding.spinnerApprovalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void initViewListener() {
        super.initViewListener();
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_my_studio;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_date_picker:
                datePickerDialog.show();
                break;
        }
    }

    @Override
    public void onRightIconClick() {
        showPopupMenu(dataBinding.includeTitle.ivRight);

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    @SuppressLint("RestrictedApi")
    private void showPopupMenu(View ancherView) {
        //创建弹出式菜单对象（最低版本11）
        PopupMenu popupMenu = new PopupMenu(this, ancherView);//第二个参数是绑定的那个view
        //获取菜单填充器
        MenuInflater inflater = popupMenu.getMenuInflater();
        //填充菜单
        inflater.inflate(R.menu.menu, popupMenu.getMenu());
        //使用反射，强制显示菜单图标
        try {
            Field field = popupMenu.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            MenuPopupHelper mPopup = (MenuPopupHelper) field.get(popupMenu);
            mPopup.setForceShowIcon(true);
        } catch (Exception e) {
        }
        //绑定菜单项的点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        break;

                    case R.id.item2:
                        break;
                }
                return true;
            }
        });
        //显示(这一行代码不要忘记了)
        popupMenu.show();
    }

}
