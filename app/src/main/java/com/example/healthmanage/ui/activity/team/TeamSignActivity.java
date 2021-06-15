package com.example.healthmanage.ui.activity.team;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

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
import com.example.healthmanage.databinding.ActivitySignTeamBinding;
import com.example.healthmanage.ui.activity.notice.response.TeamApplyResponse;
import com.example.healthmanage.ui.activity.team.adapter.InvitationAdapter;
import com.example.healthmanage.ui.activity.team.ui.InvitationMemberActivity;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 团队邀请
 */
public class TeamSignActivity extends BaseActivity<ActivitySignTeamBinding,TeamViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<TeamApplyResponse.DataBean> teamApplyBeans ;
    private InvitationAdapter invitationAdapter;
    private int indexPosition = 0;
    private int mPosition;

    @Override
    protected void initData() {
        context = TeamSignActivity.this;
        titleToolBar.setTitle("团队邀请");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setRightTitleVisible(true);
        titleToolBar.setRightTitle("邀请成员");
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        titleToolBar.setRightTitleColor(getResources().getColor(R.color.colorTxtGrey));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        viewModel.getDoctorTeamApplyList(0);
        teamApplyBeans = new ArrayList<>();
        invitationAdapter = new InvitationAdapter(context,teamApplyBeans);
        dataBinding.recyclerviewSign.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_divider));
        if (dataBinding.recyclerviewSign.getItemDecorationCount()==0){
            dataBinding.recyclerviewSign.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerviewSign.setAdapter(invitationAdapter);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.layoutTitle.tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(InvitationMemberActivity.class);
            }
        });
        viewModel.invitationLiveData.observe(this, new Observer<List<TeamApplyResponse.DataBean>>() {
            @Override
            public void onChanged(List<TeamApplyResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.smartRefreshLayout.setVisibility(View.VISIBLE);
                    dataBinding.tvSignNull.setVisibility(View.GONE);
                    if (teamApplyBeans!=null && teamApplyBeans.size()>0){
                        teamApplyBeans.clear();
                    }
                    teamApplyBeans.addAll(dataBeans);
                    invitationAdapter.notifyDataSetChanged();
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
                        viewModel.getDoctorTeamApplyList(indexPosition);
                        break;
                    case R.id.rb_signed:
                        indexPosition =1;
                        viewModel.getDoctorTeamApplyList(indexPosition);
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
                    viewModel.getDoctorTeamApplyList(0);
                }else {
                    viewModel.getDoctorTeamApplyList(1);
                }
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });

        invitationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_sign:
                        viewModel.signTeam(teamApplyBeans.get(position).getId(),1);
                        mPosition = position;
                        break;
                    case R.id.vip_cancel:
                        viewModel.refuseSignTeam(teamApplyBeans.get(position).getId(),2);
                        mPosition = position;
                        break;
                }
            }
        });
        viewModel.isSignSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    UIAlertDialog uiAlertDialog= new UIAlertDialog.DividerIOSBuilder(TeamSignActivity.this)
                            .setMessage("签约成功")
                            .setMessageTextGravity(Gravity.CENTER)
                            .setPadding(SizeUtil.dp2px(20))
                            .setMessageTextSize(20)
                            .setMessageTextColorResource(R.color.colorBlack)
                            .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dataBinding.rbUnsigned.setChecked(false);
                                    dataBinding.rbSigned.setChecked(true);
                                    viewModel.getDoctorTeamApplyList(1);
                                    invitationAdapter.notifyDataSetChanged();
                                }
                            })
                            .setMinHeight(SizeUtil.dp2px(160))
                            .setPositiveButtonTextColorResource(R.color.colorTxtBlue)
                            .create()
                            .setDimAmount(0.6f);
                    Window window = uiAlertDialog.getWindow();
                    WindowManager.LayoutParams lp = window.getAttributes();
                    WindowManager manager= getWindowManager();
                    Display display= manager.getDefaultDisplay();
                    //params.height= (int) (display.getHeight()* 0.8);
                    lp.width= (int) (display.getWidth()* 0.6);
                    uiAlertDialog.getWindow().setAttributes(lp);
                    uiAlertDialog.setWidth(lp.width).show();
                }
            }
        });
        viewModel.isRefuseSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    ToastUtil.showShort("拒绝签约"+teamApplyBeans.get(mPosition).getAppDoctorInfo().getName());
                    teamApplyBeans.remove(teamApplyBeans.get(mPosition));
                    invitationAdapter.notifyDataSetChanged();
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
        return R.layout.activity_sign_team;
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
