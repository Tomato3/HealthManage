package com.example.healthmanage.ui.activity.doctorhall;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityDoctorHallBinding;
import com.example.healthmanage.ui.activity.doctorhall.doctordetail.DoctorDetailActivity;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.bean.recyclerview.TaskReceiverRecyclerView;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.List;


public class DoctorHallActivity extends BaseActivity<ActivityDoctorHallBinding, DoctorHallViewModel> {

    private TitleToolBar titleToolBar = new TitleToolBar();
    private BaseAdapter doctorAdapter;
    private Bundle bundle;

    @Override
    protected void initData() {
        titleToolBar.setTitle("名医堂");
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.getMyDoctor();
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

        doctorAdapter = new BaseAdapter(this, null, R.layout.recycler_view_item_task_receiver,
                BR.TaskReceiverRecyclerView);
        dataBinding.recyclerViewDoctor.setLayoutManager(new LinearLayoutManager(this));
        viewModel.taskReceiverMutableLiveData.observe(this, new Observer<List<TaskReceiverRecyclerView>>() {
            @Override
            public void onChanged(List<TaskReceiverRecyclerView> taskReceiverRecyclerViews) {
                doctorAdapter.setRecyclerViewList(taskReceiverRecyclerViews);
                dataBinding.recyclerViewDoctor.setAdapter(doctorAdapter);
            }
        });

        doctorAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TaskReceiverRecyclerView taskReceiverRecyclerView =
                        (TaskReceiverRecyclerView) doctorAdapter.getRecyclerViewList().get(position);
                bundle = new Bundle();
                bundle.putLong("DoctorId", taskReceiverRecyclerView.getReceiverId());
                ImageView imageView = findViewById(R.id.iv_avatar);
                ToolUtil.startActivityWithTransition(DoctorHallActivity.this,
                        DoctorDetailActivity.class, imageView, bundle);
            }
        });

        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_my_doctor:
                        viewModel.getMyDoctor();
                        break;
                    case R.id.rb_doctor_hall:
                        viewModel.getAllDoctor();
                        break;
                }
            }
        });
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_doctor_hall;
    }
}
