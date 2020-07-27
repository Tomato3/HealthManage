package com.example.healthmanage.ui.activity.mytaskdetail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityMyTaskDetailBinding;
import com.example.healthmanage.ui.activity.selectTaskReceiver.SelectTaskReceiverActivity;
import com.example.healthmanage.utils.Constants;
import com.example.healthmanage.view.EditTextDialog;
import com.example.healthmanage.view.MyTaskRecyclerViewMenu;
import com.example.healthmanage.widget.TitleToolBar;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MyTaskDetailActivity extends BaseActivity<ActivityMyTaskDetailBinding, MyTaskDetailViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {

    Bundle bundle;
    TitleToolBar titleToolBar;
    EditTextDialog editTextDialog;
    int taskId;
    String taskContent;
    BaseAdapter taskContentAdapter;

    @Override
    protected void initData() {
        titleToolBar = new TitleToolBar();
        bundle = this.getIntent().getExtras();
        taskId = bundle.getInt("taskId");
        taskContent = bundle.getString("taskContent");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitle("任务详情");
        titleToolBar.setOnClickCallBack(MyTaskDetailActivity.this);
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.loadTaskDetail(taskId);
        switch (bundle.getInt("state")) {
            case 0:
                new MyTaskRecyclerViewMenu(this, "未处理");
                break;
            case 1:
                new MyTaskRecyclerViewMenu(this, "处理中");
                break;
            case 2:
                new MyTaskRecyclerViewMenu(this, "已处理");
                break;
            case 3:
                new MyTaskRecyclerViewMenu(this, "无法处理");
                break;
        }

    }

    @Override
    public void initViewListener() {
        super.initViewListener();
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

//        dataBinding.recyclerViewMyTaskComments.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.includeMenu.tvEdit.setOnClickListener(this::onClick);
        dataBinding.includeMenu.tvSend.setOnClickListener(this::onClick);
        viewModel.refresh.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                viewModel.loadTaskDetail(taskId);
            }
        });

        taskContentAdapter = new BaseAdapter(this, null, R.layout.recycler_view_item_my_task_detail
                , BR.MyTaskDetailRecyclerView);
//        dataBinding.recyclerViewMyTaskComments.setLayoutManager(new LinearLayoutManager(this));
//        dataBinding.recyclerViewMyTaskComments.setAdapter(taskContentAdapter);


    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_my_task_detail;
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
                Log.d(HTAG, "onClick==========>: "+Constants.ROLE_ID);
                switch (Constants.ROLE_ID) {
                    case "9":
                        editTextDialog = new EditTextDialog(this, R.layout.dialog_update_task);
                        editTextDialog.show();
                        editTextDialog.setOnEditTextDialogClickListener(new EditTextDialog.OnEditTextDialogClickListener() {
                            @Override
                            public void doCreate(String title, String content) {
                                viewModel.editTaskByManager(taskId, title, content);
                            }

                            @Override
                            public void doCreate(String doctorReplay) {

                            }

                            @Override
                            public void doSend() {

                            }
                        });
                        break;
                    case "10":
                    case "11":
                        editTextDialog = new EditTextDialog(this, R.layout.dialog_update_task_content);
                        editTextDialog.show();
                        editTextDialog.setOnEditTextDialogClickListener(new EditTextDialog.OnEditTextDialogClickListener() {
                            @Override
                            public void doCreate(String title, String content) {
                            }

                            @Override
                            public void doCreate(String doctorReplay) {
                                viewModel.editTaskByOthers(Constants.ROLE_ID, doctorReplay);

                            }

                            @Override
                            public void doSend() {

                            }
                        });
                        break;
                }
                break;
            case R.id.tv_send:
                Bundle bundle = new Bundle();
                bundle.putInt("taskId", taskId);
                bundle.putString("taskContent", taskContent);
                startActivity(SelectTaskReceiverActivity.class, bundle);
                break;
        }
    }
}
