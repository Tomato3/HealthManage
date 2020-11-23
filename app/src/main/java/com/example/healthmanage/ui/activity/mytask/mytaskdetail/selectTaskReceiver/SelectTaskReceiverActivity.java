package com.example.healthmanage.ui.activity.mytask.mytaskdetail.selectTaskReceiver;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivitySelectTaskReceiverBinding;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.view.EditTextDialog;
import com.example.healthmanage.view.TaskReceiverRecyclerView;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.List;


public class SelectTaskReceiverActivity extends BaseActivity<ActivitySelectTaskReceiverBinding,
        SelectTaskReceiverViewModel> implements View.OnClickListener {

    BaseAdapter taskReceiverAdapter;
    EditTextDialog editTextDialog;
    Bundle bundle;
    int taskId;
    String taskContent;

    @Override
    protected void initData() {
        bundle = this.getIntent().getExtras();
        taskId = bundle.getInt("taskId");
        taskContent = bundle.getString("taskContent");
        viewModel.getTaskReceiverList();
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
                    public void doCreate(List<String> content) {

                    }

                    @Override
                    public void doSend() {
                        viewModel.sendMyTask(taskId, taskReceiverRecyclerView.getReceiverId());
                    }
                });
            }

        });

        LiveEventBus.get("CloseKeyboard", Boolean.class).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                ToolUtil.hideKeyboard(dataBinding.includeSearch.etSearch);
            }
        });

        dataBinding.includeSearch.ivClear.setOnClickListener(this::onClick);
        dataBinding.includeSearch.etSearch.setHint(R.string.hint_input_search);
        dataBinding.includeSearch.etSearch.setInputType(InputType.TYPE_CLASS_TEXT);
        dataBinding.includeSearch.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    viewModel.searchDoctor(v.getText().toString());
                    return true;
                }
                return false;
            }
        });
        dataBinding.includeSearch.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    dataBinding.includeSearch.ivClear.setVisibility(View.VISIBLE);
                } else {
                    dataBinding.includeSearch.ivClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.iv_clear:
                dataBinding.includeSearch.etSearch.setText("");
                viewModel.getTaskReceiverList();
                break;
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_select_task_receiver;
    }
}
