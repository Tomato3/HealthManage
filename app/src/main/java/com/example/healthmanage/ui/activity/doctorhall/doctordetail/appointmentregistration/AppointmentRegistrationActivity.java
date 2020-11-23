package com.example.healthmanage.ui.activity.doctorhall.doctordetail.appointmentregistration;

import android.os.Bundle;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityAppointmentRegistrationBinding;
import com.example.healthmanage.widget.TitleToolBar;

public class AppointmentRegistrationActivity extends BaseActivity<ActivityAppointmentRegistrationBinding, AppointmentRegistrationViewModel> {

    private TitleToolBar titleToolBar = new TitleToolBar();

    @Override
    protected void initData() {
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitle("预约挂号");
        viewModel.setTitleToolBar(titleToolBar);
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(new TitleToolBar.OnTitleIconClickCallBack() {
            @Override
            public void onRightIconClick() {

            }

            @Override
            public void onBackIconClick() {
                finish();
            }
        });
    }


    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_appointment_registration;
    }
}
