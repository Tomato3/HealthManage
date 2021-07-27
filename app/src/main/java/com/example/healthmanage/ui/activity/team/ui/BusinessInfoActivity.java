package com.example.healthmanage.ui.activity.team.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.aries.ui.widget.progress.UIProgressDialog;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityBusinessDetailBinding;
import com.example.healthmanage.ui.activity.team.TeamViewModel;
import com.example.healthmanage.ui.activity.team.adapter.BusinessPicAdapter;
import com.example.healthmanage.ui.activity.team.response.BusinessDetailResponse;
import com.example.healthmanage.widget.TitleToolBar;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.tools.ScreenUtils;

/**
 * 查看业务详情界面
 */
public class BusinessInfoActivity extends BaseActivity<ActivityBusinessDetailBinding, TeamViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private int id;
    private BusinessPicAdapter businessPicAdapter;
    private UIProgressDialog uiProgressDialog;

    @Override
    protected void initData() {
        uiProgressDialog = new UIProgressDialog.WeChatBuilder(this)
                .setMessage(R.string.loading)
                .setIndeterminateDrawable(R.drawable.dialog_loading_wei_xin)
                .setBackgroundRadiusResource(R.dimen.dp_radius_loading)
                .create()
                .setDimAmount(0.6f);
        uiProgressDialog.show();
        id = getIntent().getIntExtra("id",0);
        context = BusinessInfoActivity.this;
        titleToolBar.setTitle("业务详情");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        viewModel.getBusineServiceInfo(id);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.businessInfoLiveData.observe(this, new Observer<BusinessDetailResponse.DataBean>() {
            @Override
            public void onChanged(BusinessDetailResponse.DataBean dataBean) {
                if (dataBean!=null){
                    switch (dataBean.getAssignStatus()){
                        case 0:
                            if (dataBean.getStatus()==1){
                                dataBinding.tvDealStatus.setText("团队指派-未完成");
                                dataBinding.rbtAlreadyComplete.setChecked(false);
                                dataBinding.rbtAlreadyComplete.setClickable(false);
                                dataBinding.rbtNotComplete.setChecked(true);
                                dataBinding.rbtNotComplete.setClickable(false);
                            }else if (dataBean.getStatus()==2){
                                dataBinding.tvDealStatus.setText("团队指派-已完成");
                                dataBinding.rbtAlreadyComplete.setChecked(true);
                                dataBinding.rbtNotComplete.setChecked(false);
                                dataBinding.rbtAlreadyComplete.setClickable(false);
                                dataBinding.rbtNotComplete.setClickable(false);
                            }
                            break;
                        case 1:
                            if (dataBean.getStatus()==1){
                                dataBinding.tvDealStatus.setText("平台指派-未完成");
                                dataBinding.rbtAlreadyComplete.setChecked(false);
                                dataBinding.rbtNotComplete.setChecked(true);
                                dataBinding.rbtAlreadyComplete.setClickable(false);
                                dataBinding.rbtNotComplete.setClickable(false);
                            }else if (dataBean.getStatus()==2){
                                dataBinding.tvDealStatus.setText("平台指派-已完成");
                                dataBinding.rbtAlreadyComplete.setChecked(true);
                                dataBinding.rbtNotComplete.setChecked(false);
                                dataBinding.rbtAlreadyComplete.setClickable(false);
                                dataBinding.rbtNotComplete.setClickable(false);
                            }
                            break;
                    }
                    if (dataBean.getAppUser()!=null){
                        switch (dataBean.getAppUser().getRank()){
                            case 0:
                                dataBinding.tvServiceObjectName.setText(Html.fromHtml("服务对象:\u3000<font color=\"#000000\">"+dataBean.getAppUser().getNickName()+"\u3000|\u3000"+"普通会员"+"</font>"));
                                break;
                            case 1:
                                dataBinding.tvServiceObjectName.setText(Html.fromHtml("服务对象:\u3000<font color=\"#000000\">"+dataBean.getAppUser().getNickName()+"\u3000|\u3000"+"高级会员"+"</font>"));
                                break;
                            case 2:
                                dataBinding.tvServiceObjectName.setText(Html.fromHtml("服务对象:\u3000<font color=\"#000000\">"+dataBean.getAppUser().getNickName()+"\u3000|\u3000"+"贵宾会员"+"</font>"));
                                break;
                            case 3:
                                dataBinding.tvServiceObjectName.setText(Html.fromHtml("服务对象:\u3000<font color=\"#000000\">"+dataBean.getAppUser().getNickName()+"\u3000|\u3000"+"至尊会员"+"</font>"));
                                break;
                        }
                        dataBinding.tvVipPhone.setText(Html.fromHtml("会员电话:\u3000<font color=\"#000000\">"+dataBean.getAppUser().getPhone()+"</font>"));
                    }
                    dataBinding.tvTaskDescribe.setText(Html.fromHtml("任务描述:\u3000<font color=\"#000000\">"+dataBean.getContent()+"</font>"));
                    dataBinding.tvTaskTime.setText(Html.fromHtml("任务时间:\u3000<font color=\"#000000\">"+dataBean.getDate()+"\u3000"+dataBean.getTime()+"</font>"));
                    dataBinding.tvTaskLocation.setText(Html.fromHtml("<font color=\"#000000\">"+dataBean.getAddr()+"</font>"));
                    dataBinding.tvDelegateTime.setText("委派时间:\u3000"+dataBean.getCreateTime());
                    dataBinding.tvDelegatePerson.setText("委派人:\u3000"+dataBean.getAppDoctorInfo().getName()+"\u3000"+dataBean.getAppDoctorInfo().getRank());
                    dataBinding.tvDetails.setText(dataBean.getDetails());

                    if (dataBean.getList()!=null && dataBean.getList().size()>0){
                        dataBinding.recyclerViewGrid.setVisibility(View.VISIBLE);
                        dataBinding.tvPicNoData.setVisibility(View.GONE);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,5,GridLayoutManager.VERTICAL,false);
                        dataBinding.recyclerViewGrid.setLayoutManager(gridLayoutManager);
                        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(5, ScreenUtils.dip2px(context,8),false);
                        if (dataBinding.recyclerViewGrid.getItemDecorationCount()==0){
                            dataBinding.recyclerViewGrid.addItemDecoration(gridSpacingItemDecoration);
                        }
                        businessPicAdapter = new BusinessPicAdapter(context,dataBean.getList());
                        dataBinding.recyclerViewGrid.setAdapter(businessPicAdapter);
                        businessPicAdapter.notifyDataSetChanged();
                    }else {
                        dataBinding.recyclerViewGrid.setVisibility(View.GONE);
                        dataBinding.tvPicNoData.setVisibility(View.VISIBLE);
                    }
                    uiProgressDialog.dismiss();
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
        return R.layout.activity_business_detail;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
