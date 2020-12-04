package com.example.healthmanage.ui.activity.consultationinfo;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityConsultationInformationBinding;
import com.example.healthmanage.bean.recyclerview.ConsultationInformationRecyclerView;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.List;

public class ConsultationInfoActivity extends BaseActivity<ActivityConsultationInformationBinding, ConsultationInfoViewModel> {

    private TitleToolBar titleToolBar = new TitleToolBar();
    BaseAdapter consultationInfoAdapter;

    @Override
    protected void initData() {
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitle("咨询信息");
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.getConsultationList(0);
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
        consultationInfoAdapter = new BaseAdapter(this, null,
                R.layout.recycler_view_item_consultation_information,
                BR.ConsultationInformationRecyclerView);
        dataBinding.recyclerViewConsultationInformation.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewConsultationInformation.setAdapter(consultationInfoAdapter);
        viewModel.consultationInfoMutableLiveData.observe(this, new Observer<List<ConsultationInformationRecyclerView>>() {
            @Override
            public void onChanged(List<ConsultationInformationRecyclerView> consultationInformationRecyclerViews) {
                consultationInfoAdapter.setRecyclerViewList(consultationInformationRecyclerViews);
                consultationInfoAdapter.notifyDataSetChanged();
            }
        });

        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_untreated:
                        viewModel.getConsultationList(0);
                        break;
                    case R.id.rb_processing:
                        viewModel.getConsultationList(1);
                        break;
                    case R.id.rb_processed:
                        viewModel.getConsultationList(2);
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
        return R.layout.activity_consultation_information;
    }
}
