package com.example.healthmanage.ui.activity.signmember;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

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
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivitySignVipBinding;
import com.example.healthmanage.ui.activity.invitemember.InviteMemberActivity;
import com.example.healthmanage.ui.activity.signmember.adapter.UnSignAdapter;
import com.example.healthmanage.ui.activity.signmember.response.SignMemberResponse;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员签约界面
 */
public class SignMemberActivity extends BaseActivity<ActivitySignVipBinding,SignMemberViewModel> implements TitleToolBar.OnTitleIconClickCallBack, View.OnClickListener {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<SignMemberResponse.DataBean> signList;
    private UnSignAdapter unSignAdapter;
    private int indexPosition = 0;
    private int mPosition;

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void initData() {
        titleToolBar.setTitle("会员签约");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setRightTitleVisible(true);
        titleToolBar.setRightTitle("邀请会员");
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        titleToolBar.setRightTitleColor(getResources().getColor(R.color.colorTxtGrey));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.getNotSignMember(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId());
        signList = new ArrayList<>();
        unSignAdapter = new UnSignAdapter(SignMemberActivity.this,signList);
        dataBinding.recyclerviewSign.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_divider));
        if (dataBinding.recyclerviewSign.getItemDecorationCount()==0){
            dataBinding.recyclerviewSign.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerviewSign.setAdapter(unSignAdapter);
//        unSignAdapter.notifyDataSetChanged();

        unSignAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.vip_cancel:
                        viewModel.addNotSignMember(1,BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId(),signList.get(position).getId());
                        mPosition = position;
                        break;
                    case R.id.tv_sign:
                        showIOSDialog(position);
                        break;
                }
            }
        });
    }

    private void showIOSDialog(int position) {
        new UIAlertDialog.DividerIOSBuilder(this).setMessage("确定签约？")
                .setMessageTextColorResource(R.color.colorTxtRed)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SignMemberActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButtonTextColorResource(R.color.colorBlack)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.addSignMember(0,BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId(),signList.get(position).getId());
                        mPosition = position;
                    }
                })
                .setMinHeight(SizeUtil.dp2px(160))
                .setPositiveButtonTextColorResource(R.color.colorBlack)
                .create()
                .setDimAmount(0.6f)
                .show();
    }


    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.layoutTitle.tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(InviteMemberActivity.class);
            }
        });
        viewModel.signList.observe(this, new Observer<List<SignMemberResponse.DataBean>>() {
            @Override
            public void onChanged(List<SignMemberResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.smartRefreshLayout.setVisibility(View.VISIBLE);
                    dataBinding.tvSignNull.setVisibility(View.GONE);
                    if (signList != null && signList.size()>0){
                        signList.clear();
                    }
                    signList.addAll(dataBeans);
                    unSignAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.smartRefreshLayout.setVisibility(View.GONE);
                    dataBinding.tvSignNull.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.notSignList.observe(this, new Observer<List<SignMemberResponse.DataBean>>() {
            @Override
            public void onChanged(List<SignMemberResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.smartRefreshLayout.setVisibility(View.VISIBLE);
                    dataBinding.tvSignNull.setVisibility(View.GONE);
                    if (signList != null && signList.size()>0){
                        signList.clear();
                    }
                    signList.addAll(dataBeans);
                    unSignAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.smartRefreshLayout.setVisibility(View.GONE);
                    dataBinding.tvSignNull.setVisibility(View.VISIBLE);
                }

            }
        });
        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_unsigned:
                        indexPosition = 0;
                        viewModel.getNotSignMember(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId());
                        break;
                    case R.id.rb_signed:
                        indexPosition =1;
                        viewModel.getSignMember(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId());
                        break;
                }
            }
        });
        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (indexPosition == 0){
                    viewModel.getNotSignMember(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId());
                }else {
                    viewModel.getSignMember(BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId());
                }
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });
        viewModel.isSignSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Toast.makeText(SignMemberActivity.this, signList.get(mPosition).getNickName()+"签约成功", Toast.LENGTH_SHORT).show();
                    signList.remove(signList.get(mPosition));
                    unSignAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(SignMemberActivity.this, "签约失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.isNotSignSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Toast.makeText(SignMemberActivity.this, signList.get(mPosition).getNickName()+"拒绝签约", Toast.LENGTH_SHORT).show();
                    signList.remove(signList.get(mPosition));
                    unSignAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(SignMemberActivity.this, "服务器错误", Toast.LENGTH_SHORT).show();
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
        return R.layout.activity_sign_vip;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
