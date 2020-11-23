package com.example.healthmanage.ui.activity.serviceplan.serviceplandetail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityServicePlanDetailBinding;
import com.example.healthmanage.view.EditTextDialog;
import com.example.healthmanage.view.ServiceItemRecyclerView;
import com.example.healthmanage.view.ServicePlanRecyclerView;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.ArrayList;
import java.util.List;

public class ServicePlanDetailActivity extends BaseActivity<ActivityServicePlanDetailBinding, ServicePlanDetailViewModel> implements TitleToolBar.OnTitleIconClickCallBack {

    private TitleToolBar titleToolBar = new TitleToolBar();
    private BaseAdapter serviceItemAdapter;
    private List<ServiceItemRecyclerView> stringList = new ArrayList<>();
    private String item, serviceMember;
    private int servicePlanId;
    TextView last, now;


    @Override
    protected void initData() {
        titleToolBar.setTitle("计划详情");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setRightIconVisible(true);
        titleToolBar.setRightIconSrc(R.drawable.title_toolbar_upload);
        viewModel.setTitleToolBar(titleToolBar);

        viewModel.setServicePlanRecyclerView((ServicePlanRecyclerView) getIntent().getSerializableExtra("servicePlanRecyclerView"));

        item = ((ServicePlanRecyclerView) getIntent().getSerializableExtra("servicePlanRecyclerView")).serviceItem;
        serviceMember = ((ServicePlanRecyclerView) getIntent().getSerializableExtra("servicePlanRecyclerView")).serviceMember;
        servicePlanId = ((ServicePlanRecyclerView) getIntent().getSerializableExtra(
                "servicePlanRecyclerView")).servicePlanId;
        String[] strings = item.split("：");
        String[] item = strings[1].split("，");
        for (int i = 0; i < item.length; i++) {
            stringList.add(new ServiceItemRecyclerView(false, item[i]));
        }

    }


    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        serviceItemAdapter = new BaseAdapter(this, stringList, R.layout.recycler_view_item_service_item,
                BR.ServiceItemRecyclerView);
        dataBinding.recyclerViewServiceItem.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewServiceItem.setAdapter(serviceItemAdapter);


        serviceItemAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                now = view.findViewById(R.id.checked);
                if (last != null) {
                    last.setTextColor(getColor(R.color.colorTextGrey));
                    last.setBackgroundResource(R.drawable.layer_list_activity_service_plan_detail_item_unselected);
                }
                last = now;
                now.setTextColor(getColor(R.color.colorBlue));
                now.setBackgroundResource(R.drawable.layer_list_activity_service_plan_detail_item_selected);
            }
        });

    }

    @Override
    public void onRightIconClick() {
        if (now == null || now.getText() == null) {
            viewModel.getUiChangeEvent().getToastTxt().setValue("请选择服务项目");
        } else {
            EditTextDialog editTextDialog = new EditTextDialog(this,
                    R.layout.dialog_upload_service_result, serviceMember, now.getText().toString());
            editTextDialog.show();
            editTextDialog.setOnEditTextDialogClickListener(new EditTextDialog.OnEditTextDialogClickListener() {
                @Override
                public void doCreate(List<String> content) {
                    viewModel.uploadServiceResult(servicePlanId, content.get(0), content.get(1),
                            content.get(2));
                }

                @Override
                public void doSend() {

                }
            });
        }

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_service_plan_detail;
    }


}
