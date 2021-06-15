package com.example.healthmanage.ui.activity.mytask;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityDealtTaskBinding;
import com.example.healthmanage.ui.activity.healthreport.ui.CreateHealthReportActivity;
import com.example.healthmanage.ui.activity.healthreport.ui.HealthReportActivity;
import com.example.healthmanage.ui.activity.mytask.adapter.TaskGridPicAdapter;
import com.example.healthmanage.ui.activity.temperature.response.HealthTaskResponse;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.SoftKeyboardUtils;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.tools.ScreenUtils;

/**
 * 直接处理异常
 */
public class DealTaskActivity extends BaseActivity<ActivityDealtTaskBinding,MyNewTaskViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private Context context;
    private HealthTaskResponse.DataBean dataBean;
    private TaskGridPicAdapter taskGridPicAdapter;

    @Override
    protected void initData() {
        context = DealTaskActivity.this;
        titleToolBar.setTitle("处理异常");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        dataBean = (HealthTaskResponse.DataBean) getIntent().getSerializableExtra("dataBean");
        dataBinding.tvTaskContentDetail.setText(dataBean.getContent());
        taskGridPicAdapter = new TaskGridPicAdapter(context,dataBean.getList());
        //最后一个不显示分割线且自定义分割线
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,5,GridLayoutManager.VERTICAL,false);
        dataBinding.recyclerViewTaskPic.setLayoutManager(gridLayoutManager);
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(5, ScreenUtils.dip2px(context,8),false);
        if (dataBean.getList()!=null && dataBean.getList().size()>0){
            dataBinding.recyclerViewTaskPic.setVisibility(View.VISIBLE);
            dataBinding.tvNoPic.setVisibility(View.GONE);
            if (dataBinding.recyclerViewTaskPic.getItemDecorationCount()==0){
                dataBinding.recyclerViewTaskPic.addItemDecoration(gridSpacingItemDecoration);
            }
            dataBinding.recyclerViewTaskPic.setAdapter(taskGridPicAdapter);
        }else {
            dataBinding.tvNoPic.setVisibility(View.VISIBLE);
            dataBinding.recyclerViewTaskPic.setVisibility(View.GONE);
        }
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.btnQueryTaskData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.updateTask(dataBean.getId(),viewModel.contentNumber.getValue());
            }
        });

        viewModel.isUpdateSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    View view = View.inflate(DealTaskActivity.this,R.layout.dialog_task_deal,null);
                    UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(DealTaskActivity.this)
                            .setView(view)
                            .setCanceledOnTouchOutside(false)//设置空白处不消失
                            .setMinHeight(SizeUtil.dp2px(160))
                            .setPositiveButtonTextColorResource(R.color.colorTxtBlue)
                            .create()
                            .setDimAmount(0.6f);
                    uiAlertDialog.show();
                    Window window = uiAlertDialog.getWindow();
                    WindowManager.LayoutParams lp = window.getAttributes();
                    lp.gravity = Gravity.CENTER;
                    //dialog宽高适应子布局xml
                    //lp.width = WindowManager.LayoutParams.MATCH_PARENT;//宽高可设置具体大小
                    //dialog宽高适应屏幕
                    WindowManager manager= getWindowManager();
                    Display display= manager.getDefaultDisplay();
                    //params.height= (int) (display.getHeight()* 0.8);
                    lp.width= (int) (display.getWidth()* 0.5);
                    uiAlertDialog.getWindow().setAttributes(lp);
                    Button button = view.findViewById(R.id.btn_success_task);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }else {
                    return;
                }
            }
        });

        dataBinding.btnSeeTaskData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("userId",dataBean.getId());
                startActivity(HealthTaskDetailActivity.class,bundle);
            }
        });
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_dealt_task;
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
