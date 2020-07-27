package com.example.healthmanage.ui.activity.selectTaskReceiver;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivitySelectTaskReceiverBinding;
import com.example.healthmanage.view.EditTextDialog;
import com.example.healthmanage.view.IncludeSearch;
import com.example.healthmanage.view.TaskReceiverRecyclerView;

import java.util.List;


public class SelectTaskReceiverActivity extends BaseActivity<ActivitySelectTaskReceiverBinding,
        SelectTaskReceiverViewModel> implements View.OnClickListener {

    BaseAdapter taskReceiverAdapter;
    EditTextDialog editTextDialog;
    Bundle bundle;
    int taskId;
    String taskContent;
    IncludeSearch includeSearch;

    @Override
    protected void initData() {
        bundle = this.getIntent().getExtras();
        taskId = bundle.getInt("taskId");
        taskContent = bundle.getString("taskContent");
        viewModel.getTaskReceiverList();
        includeSearch = new IncludeSearch(this, "dxdd", new IncludeSearch.OnSearchClickListener() {
            @Override
            public void doSearch(String searchTxt) {

            }
        });
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        taskReceiverAdapter = new BaseAdapter(this, null, R.layout.recycler_view_item_task_receiver,
                BR.TaskReceiverRecyclerView);
        dataBinding.recyclerViewTaskReceiver.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.tvCancel.setOnClickListener(this::onClick);

        viewModel.taskReceiverMutableLiveData.observe(this, new Observer<List<TaskReceiverRecyclerView>>() {
            @Override
            public void onChanged(List<TaskReceiverRecyclerView> taskReceiverRecyclerViews) {
                taskReceiverAdapter.setRecyclerViewList(taskReceiverRecyclerViews);
                dataBinding.recyclerViewTaskReceiver.setAdapter(taskReceiverAdapter);
            }
        });

        taskReceiverAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TaskReceiverRecyclerView taskReceiverRecyclerView =
                        (TaskReceiverRecyclerView) taskReceiverAdapter.getRecyclerViewList().get(position);

                EditTextDialog selectTaskReceiverDialog =
                        new EditTextDialog(SelectTaskReceiverActivity.this,
                                R.layout.dialog_select_task_receiver,
                                taskReceiverRecyclerView.getName(),
                                taskReceiverRecyclerView.getAvatarUrl(), taskContent);
                selectTaskReceiverDialog.show();
                selectTaskReceiverDialog.setOnEditTextDialogClickListener(new EditTextDialog.OnEditTextDialogClickListener() {
                    @Override
                    public void doCreate(String title, String content) {

                    }

                    @Override
                    public void doCreate(String doctorReplay) {

                    }

                    @Override
                    public void doSend() {
                        viewModel.sendMyTask(taskId, taskReceiverRecyclerView.getReceiverId());
                    }
                });
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_select_task_receiver;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
        }
    }
}
