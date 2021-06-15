package com.example.healthmanage.ui.activity.notice.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityNewsNoticeBinding;
import com.example.healthmanage.ui.activity.notice.adapter.NoticeAdapter;
import com.example.healthmanage.ui.activity.notice.adapter.NoticeSection;
import com.example.healthmanage.ui.activity.notice.response.NoticeResponse;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息通知
 */
public class NewsNoticeActivity extends BaseActivity<ActivityNewsNoticeBinding,NoticeViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private Context context;
    private List<NoticeSection> noticeSections;
    private List<NoticeResponse.DataBean> notices = new ArrayList<>();
    private NoticeAdapter noticeAdapter;
    private int mPosition;
    @Override
    protected void initData() {
        context = NewsNoticeActivity.this;
        titleToolBar.setTitle("消息通知");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        viewModel.getDoctorTeamApplyNoticeList();
        noticeSections = new ArrayList<>();
        noticeAdapter = new NoticeAdapter(context,R.layout.item_sign_health_manager,R.layout.item_notice_header,noticeSections);
        dataBinding.recylerViewNotice.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_notice));
        if (dataBinding.recylerViewNotice.getItemDecorationCount()==0){
            dataBinding.recylerViewNotice.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recylerViewNotice.setAdapter(noticeAdapter);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.invitationLiveData.observe(this, new Observer<List<NoticeResponse.DataBean>>() {
            @Override
            public void onChanged(List<NoticeResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recylerViewNotice.setVisibility(View.VISIBLE);
                    dataBinding.tvNewsNoticeNull.setVisibility(View.GONE);
                    if (notices!=null && notices.size()>0){
                        notices.clear();
                    }
                    notices.addAll(dataBeans);
                    if (noticeSections!=null && noticeSections.size()>0){
                        noticeSections.clear();
                    }
                    for (int i = 0; i < notices.size(); i++) {
                        NoticeSection sectionHeader = new NoticeSection(true,notices.get(i).getTime());
                        noticeSections.add(sectionHeader);
                        if (notices.get(i).getList()!=null && notices.get(i).getList().size()>0){
                            for (int j = 0; j < notices.get(i).getList().size(); j++) {
                                NoticeSection section = new NoticeSection(notices.get(i).getList().get(j));
                                noticeSections.add(section);
                            }
                        }
                    }
                    noticeAdapter.notifyDataSetChanged();

                }else {
                    dataBinding.recylerViewNotice.setVisibility(View.GONE);
                    dataBinding.tvNewsNoticeNull.setVisibility(View.VISIBLE);
                }
            }
        });

        noticeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_refuse:
                        viewModel.refuseSignTeam(noticeSections.get(position).t.getId(),2);
                        mPosition = position;
                        break;
                    case R.id.tv_pass:
                        viewModel.signTeam(noticeSections.get(position).t.getId(),1);
                        mPosition = position;
                        break;
                }
            }
        });
        viewModel.isRefuseSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    noticeSections.get(mPosition).t.setStatus(1);
                    noticeAdapter.notifyItemChanged(mPosition);
                    noticeAdapter.notifyDataSetChanged();
                }else {
                    ToastUtil.showShort("服务器异常");
                }
            }
        });
        viewModel.isSignSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    noticeSections.get(mPosition).t.setStatus(1);
                    noticeAdapter.notifyItemChanged(mPosition);
                    noticeAdapter.notifyDataSetChanged();
                }else {
                    ToastUtil.showShort("服务器异常");
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
                viewModel.getDoctorTeamApplyNoticeList();
                noticeAdapter.notifyDataSetChanged();
                dataBinding.smartRefreshLayout.finishRefresh(200);
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
        return R.layout.activity_news_notice;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
