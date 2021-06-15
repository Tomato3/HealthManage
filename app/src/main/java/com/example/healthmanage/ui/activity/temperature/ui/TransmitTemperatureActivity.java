package com.example.healthmanage.ui.activity.temperature.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityTransmitTemperatureBinding;
import com.example.healthmanage.ui.activity.mytask.adapter.DoctorAdapter;
import com.example.healthmanage.ui.activity.team.response.TeamMemberResponse;
import com.example.healthmanage.ui.activity.temperature.response.TransferBean;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.widget.TitleToolBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 咨询信息转交处理
 */
public class TransmitTemperatureActivity extends BaseActivity<ActivityTransmitTemperatureBinding,TemperatureViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar titleToolBar = new TitleToolBar();
    private List<TeamMemberResponse.DataBean> doctorBeans;
    private DoctorAdapter doctorAdapter;
    private int index;
    private int mPosition = -1;
    private String transferId;
    private int id;
    private TransferBean transferBean;
    @Override
    protected void initData() {
        transferBean = new TransferBean();
        titleToolBar.setTitle("转交处理");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);
        id = getIntent().getIntExtra("id",0);

        doctorBeans = new ArrayList<>();
        doctorAdapter = new DoctorAdapter(TransmitTemperatureActivity.this,doctorBeans);
        dataBinding.recyclerViewSelectDoctor.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewSelectDoctor.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        dataBinding.recyclerViewSelectDoctor.setAdapter(doctorAdapter);
        viewModel.getDoctorList(10);
        if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==9){
            dataBinding.rbHealthManager.setVisibility(View.GONE);
        }else {
            dataBinding.rbHealthManager.setVisibility(View.VISIBLE);
        }
        doctorAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.btn_select:
                        if (mPosition != -1 && mPosition != position){
                            Log.e("mPosition+error==========",mPosition+"");
                            Log.e("Position+error==========",position+"");
                            doctorBeans.get(mPosition).setChecked(false);
                        }
                        doctorBeans.get(position).setChecked(true);
                        mPosition = position;
                        doctorAdapter.notifyDataSetChanged();
                        transferId = String.valueOf(doctorBeans.get(position).getAppDoctorInfo().getAppSystemUser().getSysId());
                        break;
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
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.doctorMutableLiveData.observe(this, new Observer<List<TeamMemberResponse.DataBean>>() {
            @Override
            public void onChanged(List<TeamMemberResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.tvDoctorNull.setVisibility(View.GONE);
                    dataBinding.recyclerViewSelectDoctor.setVisibility(View.VISIBLE);
                    if (doctorBeans!=null && doctorBeans.size()>0){
                        doctorBeans.clear();
                    }
                    doctorBeans.addAll(dataBeans);
                    doctorAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.tvDoctorNull.setVisibility(View.VISIBLE);
                    dataBinding.recyclerViewSelectDoctor.setVisibility(View.GONE);
                }
            }
        });
        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_health_dietitian:
                        transferId = null;
                        viewModel.getDoctorList(10);
                        index = 0;
                        //切换的时候如果不恢复会报越界
                        mPosition = -1;
                        break;
                    case R.id.rb_health_nurse:
                        transferId = null;
                        viewModel.getDoctorList(12);
                        index = 1;
                        mPosition = -1;
                        break;
                    case R.id.rb_health_doctor:
                        transferId = null;
                        viewModel.getDoctorList(11);
                        index = 2;
                        mPosition = -1;
                        break;
                    case R.id.rb_health_manager:
                        transferId = null;
                        viewModel.getDoctorList(9);
                        index = 3;
                        mPosition = -1;
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
                if (index == 0){
                    viewModel.getDoctorList(10);
                }else if (index == 1){
                    viewModel.getDoctorList(12);
                }else if (index == 2){
                    viewModel.getDoctorList(11);
                }else if (index == 3){
                    viewModel.getDoctorList(9);
                }
                dataBinding.smartRefreshLayout.finishRefresh(200);
            }
        });
        dataBinding.btnQueryPostDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (transferId==null){
                    Toast.makeText(TransmitTemperatureActivity.this,"请选择转交对象",Toast.LENGTH_SHORT).show();
                }else {
                    if (Integer.valueOf(transferId)==BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId()){
                        ToastUtil.showShort("不能转交给自己");
                    }else {
                        transferBean.setId(id);
                        transferBean.setSystemUserId(Integer.valueOf(transferId));
                        transferBean.setTransferTime(ToolUtil.getCurrentTime());
                        transferBean.setToken(BaseApplication.getToken());
                        viewModel.sendTransferTemperature(transferBean);
                    }
                }

            }
        });
        viewModel.isSendTransferSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    View view = View.inflate(TransmitTemperatureActivity.this,R.layout.dialog_transmit_deal,null);
                    UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(TransmitTemperatureActivity.this)
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
                    Button button = view.findViewById(R.id.btn_success_transmit);
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
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_transmit_temperature;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
