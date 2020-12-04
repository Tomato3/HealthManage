package com.example.healthmanage.ui.activity.mytask.mytaskdetail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityMyTaskDetailBinding;
import com.example.healthmanage.ui.activity.mytask.mytaskdetail.abnormaldata.AbnormalDataActivity;
import com.example.healthmanage.ui.activity.mytask.mytaskdetail.selectTaskReceiver.SelectTaskReceiverActivity;
import com.example.healthmanage.utils.Constants;
import com.example.healthmanage.dialog.EditTextDialog;
import com.example.healthmanage.bean.recyclerview.MyTaskDetailRecyclerView;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MyTaskDetailActivity extends BaseActivity<ActivityMyTaskDetailBinding, MyTaskDetailViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {

    Bundle bundle;
    TitleToolBar titleToolBar;
    EditTextDialog editTextDialog;
    int taskId;
    String taskTitle;
    String taskCreateTime;
    String dataDate;
    String state;
    BaseAdapter taskContentAdapter;

    @Override
    protected void initData() {
        titleToolBar = new TitleToolBar();
        bundle = this.getIntent().getExtras();
        taskId = bundle.getInt("taskId");
        taskTitle = bundle.getString("taskTitle");
        taskCreateTime = bundle.getString("taskCreateTime");
        dataDate = bundle.getString("dataDate");
        switch (bundle.getInt("state")) {
            case 0:
                state = "(未处理)";
                break;
            case 1:
                state = "(处理中)";
                break;
            case 2:
                state = "(已处理)";
                break;
            case 3:
                state = "(无法处理)";
                break;
        }
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitle("任务详情" + "\b" + state);
        titleToolBar.setOnClickCallBack(MyTaskDetailActivity.this);
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.loadTaskDetail(taskId);
        if (Constants.ROLE_ID.equals("11")) {
            dataBinding.ll.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void initViewListener() {
        super.initViewListener();
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

        dataBinding.includeMenu.tvEdit.setOnClickListener(this::onClick);
        dataBinding.includeMenu.tvSend.setOnClickListener(this::onClick);
        dataBinding.includeMenu.tvView.setOnClickListener(this::onClick);
//        viewModel.refresh.observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean aBoolean) {
//                viewModel.loadTaskDetail(taskId);
//            }
//        });

        taskContentAdapter = new BaseAdapter(this, null, R.layout.recycler_view_item_my_task_detail
                , BR.MyTaskDetailRecyclerView);
        dataBinding.recyclerViewMyTaskComments.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewMyTaskComments.setAdapter(taskContentAdapter);
        viewModel.myTaskDetailRecyclerViewList.observe(this, new Observer<List<MyTaskDetailRecyclerView>>() {
            @Override
            public void onChanged(List<MyTaskDetailRecyclerView> myTaskDetailRecyclerViews) {
                taskContentAdapter.setRecyclerViewList(myTaskDetailRecyclerViews);
                taskContentAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_edit:
                Log.d(HTAG, "onClick==========>: " + Constants.ROLE_ID);
                editTextDialog = new EditTextDialog(this, R.layout.dialog_update_task, "");
                editTextDialog.show();
                editTextDialog.setOnEditTextDialogClickListener(new EditTextDialog.OnEditTextDialogClickListener() {
                    @Override
                    public void doCreate(List<String> content) {
                        switch (Constants.ROLE_ID) {
                            case "9":
                                viewModel.editTaskByManager(taskId, content.get(0));
                                break;
                            case "10":
                            case "11":
                                viewModel.editTaskByOthers(taskId, content.get(0));
                                break;
                        }
                    }

                    @Override
                    public void doSend() {

                    }
                });
                break;
            case R.id.tv_send:
                bundle = new Bundle();
                bundle.putInt("taskId", taskId);
                bundle.putString("taskContent", taskTitle + "\n" + taskCreateTime);
                startActivity(SelectTaskReceiverActivity.class, bundle);
                break;
            case R.id.tv_view:
                bundle = new Bundle();
                bundle.putInt("taskId", taskId);
                bundle.putString("abnormalDataTitle", taskTitle + "\b" + taskCreateTime + "\b" +
                        "异常数据");
                bundle.putString("dataDate", dataDate);
                startActivity(AbnormalDataActivity.class, bundle);
                break;
        }

    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_my_task_detail;
    }
}
