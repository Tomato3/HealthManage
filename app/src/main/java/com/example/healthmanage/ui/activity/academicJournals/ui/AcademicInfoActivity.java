package com.example.healthmanage.ui.activity.academicJournals.ui;

import android.os.Bundle;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityAcademicInfoBinding;
import com.example.healthmanage.widget.TitleToolBar;

/**
 * 学术期刊详情
 */
public class AcademicInfoActivity extends BaseActivity<ActivityAcademicInfoBinding,AcademicJournalsViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    @Override
    protected void initData() {

    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_academic_info;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {

    }
}
