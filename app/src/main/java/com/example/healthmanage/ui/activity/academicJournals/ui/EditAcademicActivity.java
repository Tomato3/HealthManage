package com.example.healthmanage.ui.activity.academicJournals.ui;

import android.os.Bundle;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityCreateAcademicJournalsBinding;
import com.example.healthmanage.widget.TitleToolBar;

/**
 * 修改学术期刊
 */
public class EditAcademicActivity extends BaseActivity<ActivityCreateAcademicJournalsBinding,AcademicJournalsViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    @Override
    protected void initData() {

    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_create_academic_journals;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {

    }
}
