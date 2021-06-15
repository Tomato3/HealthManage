package com.example.healthmanage.ui.activity.temperature.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.aries.ui.widget.progress.UIProgressDialog;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityTemperatureNewsBinding;
import com.example.healthmanage.ui.activity.memberdetail.MemberNewDetailActivity;
import com.example.healthmanage.ui.activity.mystudio.MyStudioActivity;
import com.example.healthmanage.ui.activity.temperature.adapter.TemperatureAdapter;
import com.example.healthmanage.ui.activity.temperature.response.TemperatureResponse;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.view.GridItemDecoration;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 咨询信息
 */
public class TemperatureActivity extends BaseActivity<ActivityTemperatureNewsBinding,TemperatureViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private int index=0;
    private List<TemperatureResponse.DataBean> dataBeanList;
    private TemperatureAdapter temperatureAdapter;
    private Context context;
    private UIProgressDialog uiProgressDialog;
    @Override
    protected void initData() {
        context = TemperatureActivity.this;
        titleToolBar.setTitle("咨询信息");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        uiProgressDialog = new UIProgressDialog.WeChatBuilder(this)
                .setMessage(R.string.loading)
                .setIndeterminateDrawable(R.drawable.dialog_loading_wei_xin)
                .setBackgroundRadiusResource(R.dimen.dp_radius_loading)
                .create()
                .setDimAmount(0.6f);
        uiProgressDialog.show();
        dataBeanList = new ArrayList<>();
        temperatureAdapter = new TemperatureAdapter(context,dataBeanList);
        dataBinding.recyclerViewTemperatureNews.setLayoutManager(new LinearLayoutManager(this));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        if (dataBinding.recyclerViewTemperatureNews.getItemDecorationCount()==0){
            dataBinding.recyclerViewTemperatureNews.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewTemperatureNews.setAdapter(temperatureAdapter);
        viewModel.getHealthConsultStatus(0);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.temperatureLiveData.observe(this, new Observer<List<TemperatureResponse.DataBean>>() {
            @Override
            public void onChanged(List<TemperatureResponse.DataBean> dataBeans) {
                uiProgressDialog.dismiss();
                if (dataBeans !=null && dataBeans.size()>0){
                    dataBinding.recyclerViewTemperatureNews.setVisibility(View.VISIBLE);
                    dataBinding.layoutTemperatureNewsNull.setVisibility(View.GONE);
                    if (dataBeanList!=null && dataBeanList.size()>0){
                        dataBeanList.clear();
                    }
                    dataBeanList.addAll(dataBeans);
                    temperatureAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.layoutTemperatureNewsNull.setVisibility(View.VISIBLE);
                    dataBinding.recyclerViewTemperatureNews.setVisibility(View.GONE);
                }
            }
        });
        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_wait_reply:
                        index = 0;
                        viewModel.getHealthConsultStatus(0);
                        break;
                    case R.id.rb_already_reply:
                        index = 1;
                        viewModel.getHealthConsultStatus(1);
                        break;
                    case R.id.rb_already_transmit:
                        index = 2;
                        viewModel.getHealthConsultTransferStatus(1);
                        break;
                }
            }
        });
        temperatureAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_see_reply:
                        Intent replyIntent = new Intent(context,TemperatureReplyActivity.class);
                        replyIntent.putExtra("dataBean",dataBeanList.get(position));
                        startActivity(replyIntent);
                        break;
                    case R.id.tv_transmit_reply:
                        Intent transmitIntent = new Intent(context,TransmitTemperatureActivity.class);
                        transmitIntent.putExtra("id",dataBeanList.get(position).getId());
                        startActivity(transmitIntent);
                        break;
                    case R.id.tv_immediately_reply:
                        Intent prescriptionIntent = new Intent(context,ReplyActivity.class);
                        prescriptionIntent.putExtra("dataBean",dataBeanList.get(position));
                        startActivity(prescriptionIntent);
                        break;
                    case R.id.tv_Withdrawal:
                        pop(position);
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
                        viewModel.getHealthConsultStatus(0);
                        temperatureAdapter.notifyDataSetChanged();
                        dataBinding.smartRefreshLayout.finishRefresh(200);
                        break;
                    case 1:
                        viewModel.getHealthConsultStatus(1);
                        temperatureAdapter.notifyDataSetChanged();
                        dataBinding.smartRefreshLayout.finishRefresh(200);
                        break;
                    case 2:
                        viewModel.getHealthConsultTransferStatus(1);
                        temperatureAdapter.notifyDataSetChanged();
                        dataBinding.smartRefreshLayout.finishRefresh(200);
                        break;
                }
            }
        });
        viewModel.isRefusalSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    View view = View.inflate(TemperatureActivity.this,R.layout.dialog_insert_model,null);
                    TextView tvTitle = view.findViewById(R.id.tv_success);
                    TextView tvContent = view.findViewById(R.id.tv_tips_task);
                    tvTitle.setText("已退诊");
                    tvContent.setText("问诊费用将自动退还至患者账户中");
                    UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(TemperatureActivity.this)
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
                    Button button = view.findViewById(R.id.btn_success_insert);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            temperatureAdapter.notifyDataSetChanged();
                        }
                    });
                }else {
                    return;
                }
            }
        });
    }

    private void pop(int position){
        View view = View.inflate(context,R.layout.dialog_task_create,null);
        TextView title = view.findViewById(R.id.tv_hint_task);
        title.setText("请选择退诊原因:");
        UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(context)
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
        lp.width= (int) (display.getWidth()* 0.7);
        uiAlertDialog.getWindow().setAttributes(lp);

        Button okButton = view.findViewById(R.id.btn_query_task_create);
        Button cancelButton = view.findViewById(R.id.btn_cancel_task_create);
        EditText editContent = view.findViewById(R.id.edt_hint_task);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(editContent.getText().toString())){
                    ToastUtil.showShort("请输入拒诊原因");
                }else {
                    viewModel.getHealthConsultDrugBack(dataBeanList.get(position).getId(),editContent.getText().toString());
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiAlertDialog.dismiss();
            }
        });
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_temperature_news;
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (index){
            case 0:
                viewModel.getHealthConsultStatus(0);
                temperatureAdapter.notifyDataSetChanged();
                break;
            case 1:
                viewModel.getHealthConsultStatus(1);
                temperatureAdapter.notifyDataSetChanged();
                break;
            case 2:
                viewModel.getHealthConsultTransferStatus(1);
                temperatureAdapter.notifyDataSetChanged();
                break;
        }
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
