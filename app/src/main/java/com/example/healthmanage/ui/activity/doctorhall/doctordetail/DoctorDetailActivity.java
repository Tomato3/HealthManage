package com.example.healthmanage.ui.activity.doctorhall.doctordetail;

import android.os.Bundle;
import android.view.View;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityDoctorDetailBinding;
import com.example.healthmanage.ui.activity.doctorhall.doctordetail.appointmentregistration.AppointmentRegistrationActivity;
import com.example.healthmanage.widget.TitleToolBar;

public class DoctorDetailActivity extends BaseActivity<ActivityDoctorDetailBinding, DoctorDetailViewModel> implements View.OnClickListener {

    private TitleToolBar titleToolBar = new TitleToolBar();
    Bundle bundle;
    long doctorId;

    @Override
    protected void initData() {
        doctorId = getIntent().getLongExtra("DoctorId", 0);
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.getMyDoctorDetail(doctorId);
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
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.tvAppointmentRegistration.setOnClickListener(this::onClick);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_doctor_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_appointment_registration:
                bundle = new Bundle();
                bundle.putLong("DoctorId", doctorId);
                startActivity(AppointmentRegistrationActivity.class, bundle);
                break;
        }
    }
}
