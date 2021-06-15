package com.example.healthmanage.ui.activity.mytask;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityTaskDetailBinding;
import com.example.healthmanage.ui.activity.mytask.adapter.TaskGridPicAdapter;
import com.example.healthmanage.ui.activity.temperature.response.HealthTaskResponse;
import com.example.healthmanage.widget.TitleToolBar;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.tools.ScreenUtils;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 健康异常
 */
public class WatchTaskDetailActivity extends BaseActivity<ActivityTaskDetailBinding,MyNewTaskViewModel> implements TitleToolBar.OnTitleIconClickCallBack{
    private TitleToolBar titleToolBar = new TitleToolBar();
//    private String taskContent;
//    private String doctorReply;
//    private int id;
    private Context context;
    private HealthTaskResponse.DataBean dataBean;
    private TaskGridPicAdapter taskGridPicAdapter;

    @Override
    protected void initData() {
        context = WatchTaskDetailActivity.this;
        titleToolBar.setTitle("健康异常");
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
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.tvTaskContentDetail.setText(dataBean.getContent());
        dataBinding.tvTaskContentDevice.setText(dataBean.getProposal());
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_task_detail;
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
