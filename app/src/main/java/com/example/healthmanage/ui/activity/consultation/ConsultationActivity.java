package com.example.healthmanage.ui.activity.consultation;

import android.os.Bundle;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityConsultationBinding;


public class ConsultationActivity extends BaseActivity<ActivityConsultationBinding, ConsultationViewModel> {
    @Override
    protected void initData() {

    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_consultation;
    }
}
