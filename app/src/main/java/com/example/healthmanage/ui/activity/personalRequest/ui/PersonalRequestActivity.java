package com.example.healthmanage.ui.activity.personalRequest.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityPersonalRequestBinding;
import com.example.healthmanage.ui.activity.personalRequest.adapter.RequestAdapter;
import com.example.healthmanage.ui.activity.personalRequest.response.RequestResponse;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人请求界面
 */
public class PersonalRequestActivity extends BaseActivity<ActivityPersonalRequestBinding,PersonalViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<RequestResponse.DataBean> requestBeans;
    private RequestAdapter requestAdapter;
    private int index = 0;
    private int mPosition;


    @Override
    protected void onResume() {
        super.onResume();
        if (index==0){
            initData();
        }
    }

    @Override
    protected void initData() {
        context = PersonalRequestActivity.this;
        titleToolBar.setTitle("个人请求");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        requestBeans = new ArrayList<>();
        requestAdapter = new RequestAdapter(requestBeans);
        dataBinding.recyclerViewPersonalRequest.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        if (dataBinding.recyclerViewPersonalRequest.getItemDecorationCount()==0){
            dataBinding.recyclerViewPersonalRequest.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewPersonalRequest.setAdapter(requestAdapter);
        viewModel.getPersonalRequestList(0);
        requestAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_cancel_request:
                        if (index==0){
                            mPosition = position;
                            viewModel.cancelPersonalRequest(requestBeans.get(position).getId());
                        }else {
                            View mView = View.inflate(context,R.layout.dialog_request_reply,null);
                            TextView tvReplyTime = mView.findViewById(R.id.tv_reply_time);
                            TextView tvContent = mView.findViewById(R.id.tv_reply_content);
                            tvReplyTime.setText("回复时间:"+requestBeans.get(position).getReplyTime());
                            tvContent.setText("\u3000\u3000"+requestBeans.get(position).getContent());
                            UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(context)
                                    .setView(mView)
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
                            lp.width= (int) (display.getWidth()* 0.8);
                            uiAlertDialog.getWindow().setAttributes(lp);
                            Button button = mView.findViewById(R.id.btn_success_reply);
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    uiAlertDialog.dismiss();
                                }
                            });
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.layoutWritePersonalRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CreatePersonalRequestActivity.class);
            }
        });
        viewModel.personRequestLiveData.observe(this, new Observer<List<RequestResponse.DataBean>>() {
            @Override
            public void onChanged(List<RequestResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewPersonalRequest.setVisibility(View.VISIBLE);
                    dataBinding.layoutPersonalRequestNull.setVisibility(View.GONE);
                    if (requestBeans!=null && requestBeans.size()>0){
                        requestBeans.clear();
                    }
                    requestBeans.addAll(dataBeans);
                    requestAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewPersonalRequest.setVisibility(View.GONE);
                    dataBinding.layoutPersonalRequestNull.setVisibility(View.VISIBLE);
                }
            }
        });
        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_wait_answer:
                        index = 0;
                        viewModel.getPersonalRequestList(0);
                        break;
                    case R.id.rb_already_answer:
                        index = 1;
                        viewModel.getPersonalRequestList(1);
                        break;
                }
            }
        });
        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                dataBinding.smartRefreshLayout.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                switch (index){
                    case 0:
                        viewModel.getPersonalRequestList(0);
                        requestAdapter.notifyDataSetChanged();
                        dataBinding.smartRefreshLayout.finishRefresh(200);
                        break;
                    case 1:
                        viewModel.getPersonalRequestList(1);
                        requestAdapter.notifyDataSetChanged();
                        dataBinding.smartRefreshLayout.finishRefresh(200);
                        break;
                }
            }
        });

        viewModel.cancelSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    requestBeans.remove(mPosition);
                    requestAdapter.notifyItemRemoved(mPosition);
                    ToastUtil.showShort("取消成功");
                }
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_personal_request;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
