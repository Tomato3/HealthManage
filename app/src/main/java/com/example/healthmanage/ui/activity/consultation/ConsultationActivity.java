package com.example.healthmanage.ui.activity.consultation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.example.healthmanage.databinding.ActivityConsultationBinding;
import com.example.healthmanage.ui.activity.consultation.adapter.ConsultationAdapter;
import com.example.healthmanage.ui.activity.consultation.response.ConsultationListResponse;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.constants.EaseConstant;
import com.hyphenate.exceptions.HyphenateException;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 会诊列表
 * status 1表示未开始的会诊
 */
public class ConsultationActivity extends BaseActivity<ActivityConsultationBinding, ConsultationViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private Context context;
    private List<ConsultationListResponse.DataBean> dataBeanList;
    private ConsultationAdapter consultationAdapter;
    private int index;
    private ConsultationListResponse.DataBean dataBean;
    @Override
    protected void initData() {
        context = ConsultationActivity.this;
        titleToolBar.setTitle("患者会诊");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        dataBeanList = new ArrayList<>();
        consultationAdapter  = new ConsultationAdapter(context,dataBeanList);
        dataBinding.recyclerViewConsultationTask.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        if (dataBinding.recyclerViewConsultationTask.getItemDecorationCount()==0){
            dataBinding.recyclerViewConsultationTask.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewConsultationTask.setAdapter(consultationAdapter);
        viewModel.getPatientExamine(1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (index==0){
            viewModel.getPatientExamine(1);
            consultationAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.consultationListLiveData.observe(this, new Observer<List<ConsultationListResponse.DataBean>>() {
            @Override
            public void onChanged(List<ConsultationListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewConsultationTask.setVisibility(View.VISIBLE);
                    dataBinding.layoutConsultationTaskNull.setVisibility(View.GONE);
                    if (dataBeanList!=null && dataBeanList.size()>0){
                        dataBeanList.clear();
                    }
                    dataBeanList.addAll(dataBeans);
                    consultationAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewConsultationTask.setVisibility(View.GONE);
                    dataBinding.layoutConsultationTaskNull.setVisibility(View.VISIBLE);
                }
            }
        });

        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_consultation_task:
                        index = 0;
                        viewModel.getPatientExamine(1);
                        break;
                    case R.id.rb_finish_consultation_task:
                        index = 1;
                        viewModel.getPatientExamine(0);
                        break;
                }
            }
        });
        dataBinding.layoutWriteConsultationTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ConsultationPatientActivity.class);
            }
        });

        dataBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                dataBinding.smartRefreshLayout.finishLoadMore(200);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (index == 0){
                    viewModel.getPatientExamine(1);
                }else {
                    viewModel.getPatientExamine(0);
                }
                consultationAdapter.notifyDataSetChanged();
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });
        consultationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context,ConsultationDetailActivity.class);
                intent.putExtra("dataBean", dataBeanList.get(position));
                startActivity(intent);
            }
        });
        consultationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_consultation_button:
                        Intent intent = new Intent(context,EMCChatActivity.class);
                        intent.putExtra(EaseConstant.EXTRA_CONVERSATION_ID,dataBeanList.get(position).getRoomId());
                        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_CHATROOM);
//                        bundle.putString(DemoConstant.HISTORY_MSG_ID, historyMsgId);
                        intent.putExtra(EaseConstant.EXTRA_IS_ROAM, true);
                        intent.putExtra("createId",dataBeanList.get(position).getCreateUserId());
                        intent.putExtra("id",dataBeanList.get(position).getId());
                        intent.putExtra("type","chat");
//                        intent.putExtra("roomId",dataBeanList.get(position).getRoomId());
                        startActivity(intent);
                        break;
                    case R.id.tv_see_consultation_content:
                        Intent seeIntent = new Intent(context,EMCChatActivity.class);
                        seeIntent.putExtra(EaseConstant.EXTRA_CONVERSATION_ID,dataBeanList.get(position).getRoomId());
                        seeIntent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_CHATROOM);
                        seeIntent.putExtra("type","seeChat");
                        startActivity(seeIntent);
                        break;
                    case R.id.tv_see_consultation_device:
                        if (TextUtils.isEmpty(dataBeanList.get(position).getExaminePlan())){
                            ToastUtil.showShort("没有诊断方案");
                        }else {
                            View dialogView = View.inflate(context,R.layout.dialog_consultation_plan,null);
                            TextView content = dialogView.findViewById(R.id.tv_consultation_plan_content);
                            content.setText(dataBeanList.get(position).getExaminePlan());
                            UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(context)
                                    .setView(dialogView)
                                    .setMinHeight(SizeUtil.dp2px(160))
                                    .setPositiveButtonTextColorResource(R.color.colorTxtBlue)
                                    .create()
                                    .setDimAmount(0.6f);
                            uiAlertDialog.show();
//                            Window window = uiAlertDialog.getWindow();
//                            WindowManager.LayoutParams lp = window.getAttributes();
//                            lp.gravity = Gravity.CENTER;
//                            //dialog宽高适应子布局xml
//                            //lp.width = WindowManager.LayoutParams.MATCH_PARENT;//宽高可设置具体大小
//                            //dialog宽高适应屏幕
//                            WindowManager manager= getWindowManager();
//                            Display display= manager.getDefaultDisplay();
//                            //params.height= (int) (display.getHeight()* 0.8);
//                            lp.width= (int) (display.getWidth()* 0.7);
//                            uiAlertDialog.getWindow().setAttributes(lp);
//                            TextView content = dialogView.findViewById(R.id.tv_consultation_plan_content);
//                            content.setText(dataBeanList.get(position).getExaminePlan());
                        }
                        break;
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
        return R.layout.activity_consultation;
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
