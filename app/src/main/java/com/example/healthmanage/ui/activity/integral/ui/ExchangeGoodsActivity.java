package com.example.healthmanage.ui.activity.integral.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import com.aries.ui.widget.alert.UIAlertDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.databinding.ActivityExchangeGoodsBinding;
import com.example.healthmanage.ui.activity.famousDoctorHall.ui.LocalJsonResolutionUtils;
import com.example.healthmanage.ui.activity.integral.IntegralViewModel;
import com.example.healthmanage.ui.activity.integral.response.AddressBean;
import com.example.healthmanage.ui.activity.integral.response.AddressResponse;
import com.example.healthmanage.ui.activity.integral.response.ExchangeGoodsBean;
import com.example.healthmanage.ui.activity.integral.response.GoodsDetailResponse;
import com.example.healthmanage.utils.SizeUtil;
import com.example.healthmanage.utils.ToastUtils;
import com.example.healthmanage.widget.TitleToolBar;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * desc:兑换商品
 * date:2021/7/19 15:25
 * author:bWang
 */
public class ExchangeGoodsActivity extends BaseActivity<ActivityExchangeGoodsBinding, IntegralViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private Context mContext;
    private AddressBean mAddressBean;
//    private AddressResponse.DataBean mDataBean;
    private ExchangeGoodsBean mExchangeGoodsBean;
    private GoodsDetailResponse.DataBean mDataBean;

    @Override
    protected void initData() {
        mContext = ExchangeGoodsActivity.this;
        mTitleToolBar.setTitle("兑换商品");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutExchangeGoodsTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);
        mExchangeGoodsBean = new ExchangeGoodsBean();
        viewModel.queryOneByUserId();

        mDataBean = (GoodsDetailResponse.DataBean) getIntent().getSerializableExtra("goodsInfo");
        viewModel.exchangePoints.setValue(mDataBean.getPoint()+"分");
        viewModel.exchangeTitle.setValue(mDataBean.getName());
        mExchangeGoodsBean.setGoodsId(mDataBean.getId());
        mExchangeGoodsBean.setUserId(BaseApplication.getUserInfoBean().getSysId());
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        if (mDataBean.getLogisticsType()==0){
            dataBinding.tvLogisticsType.setText("包邮");
        }else {
            dataBinding.tvLogisticsType.setText("虚拟商品");
        }
        if ((Integer.valueOf(viewModel.exchangeNumber.getValue())*mDataBean.getPoint())>Integer.valueOf(BaseApplication.getIntegrals())){
            dataBinding.btnExchangeSubmit.setBackgroundResource(R.drawable.bg_shape_grey_circle);
            dataBinding.btnExchangeSubmit.setEnabled(false);
        }else {
            dataBinding.btnExchangeSubmit.setBackgroundResource(R.drawable.bg_shape_red_circle);
            dataBinding.btnExchangeSubmit.setEnabled(true);
        }
        Glide.with(mContext).load(mDataBean.getUrl()).apply(new RequestOptions().placeholder(R.drawable.ic_shop_pic).error(R.drawable.ic_shop_pic))
                .into(dataBinding.ivGoodsPic);
        viewModel.mAddressDataMutableLiveData.observe(this, new Observer<AddressResponse.DataBean>() {
            @Override
            public void onChanged(AddressResponse.DataBean dataBean) {
                if (dataBean!=null){
                    dataBinding.tvNoAddress.setVisibility(View.GONE);
                    dataBinding.tvAddress.setVisibility(View.VISIBLE);
                    dataBinding.tvNamePhone.setVisibility(View.VISIBLE);
                    dataBinding.tvNamePhone.setText(dataBean.getName()+"\u3000"+dataBean.getPhone());
                    dataBinding.tvAddress.setText(dataBean.getAddress()+dataBean.getDetailedAddress());
                    mExchangeGoodsBean.setLocationId(Integer.valueOf(dataBean.getId()));
                    mExchangeGoodsBean.setAddress(dataBean.getAddress());
                    mExchangeGoodsBean.setDetailedAddress(dataBean.getDetailedAddress());
                    mExchangeGoodsBean.setName(dataBean.getName());
                    mExchangeGoodsBean.setPhone(dataBean.getPhone());
                }else {
                    dataBinding.tvNoAddress.setVisibility(View.VISIBLE);
                    dataBinding.tvAddress.setVisibility(View.GONE);
                    dataBinding.tvNamePhone.setVisibility(View.GONE);
                }
            }
        });
        dataBinding.layoutAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ChooseAddressActivity.class);
                startActivityForResult(intent,2);
            }
        });
        dataBinding.tvReduceNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(viewModel.exchangeNumber.getValue())==1){
                    ToastUtils.showShort(mContext,"至少选择一个");
                }else {
                    if ((Integer.valueOf(viewModel.exchangeNumber.getValue())*mDataBean.getPoint())>Integer.valueOf(BaseApplication.getIntegrals())){
                        dataBinding.btnExchangeSubmit.setBackgroundResource(R.drawable.bg_shape_grey_circle);
                        dataBinding.btnExchangeSubmit.setEnabled(false);
                    }else {
                        dataBinding.btnExchangeSubmit.setBackgroundResource(R.drawable.bg_shape_red_circle);
                        dataBinding.btnExchangeSubmit.setEnabled(true);
                    }
                    viewModel.exchangeNumber.setValue(String.valueOf(Integer.valueOf(viewModel.exchangeNumber.getValue())-1));
                }
                dataBinding.tvExchangePoints.setText(Html.fromHtml("<font color=\"#666666\">"+"共"+viewModel.exchangeNumber.getValue()+"件商品，消耗："+"</font>"+"<font color=\"#FF3A30\">"+(Integer.valueOf(viewModel.exchangeNumber.getValue())*mDataBean.getPoint())+"积分"+"</font>"));
            }
        });
        dataBinding.tvAddNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.valueOf(viewModel.exchangeNumber.getValue())*mDataBean.getPoint())>Integer.valueOf(BaseApplication.getIntegrals())){
                    dataBinding.btnExchangeSubmit.setBackgroundResource(R.drawable.bg_shape_grey_circle);
                    dataBinding.btnExchangeSubmit.setEnabled(false);
                }else {
                    dataBinding.btnExchangeSubmit.setBackgroundResource(R.drawable.bg_shape_red_circle);
                    dataBinding.btnExchangeSubmit.setEnabled(true);
                }
                viewModel.exchangeNumber.setValue(String.valueOf(Integer.valueOf(viewModel.exchangeNumber.getValue())+1));
                dataBinding.tvExchangePoints.setText(Html.fromHtml("<font color=\"#666666\">"+"共"+viewModel.exchangeNumber.getValue()+"件商品，消耗："+"</font>"+"<font color=\"#FF3A30\">"+(Integer.valueOf(viewModel.exchangeNumber.getValue())*mDataBean.getPoint())+"积分"+"</font>"));
            }
        });
        dataBinding.btnExchangeSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExchangeGoodsBean.setExchangeQuantity(Integer.valueOf(viewModel.exchangeNumber.getValue()));
                mExchangeGoodsBean.setPoint(Integer.valueOf(viewModel.exchangeNumber.getValue())*mDataBean.getPoint());
                View view = View.inflate(mContext,R.layout.dialog_exchange_remind,null);
                UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(mContext)
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
                lp.width= (int) (display.getWidth()* 0.6);
                uiAlertDialog.getWindow().setAttributes(lp);
                view.findViewById(R.id.tv_query_exchange).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(mContext,mExchangeGoodsBean.toString());
                    }
                });
                view.findViewById(R.id.tv__cancel_exchange).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uiAlertDialog.dismiss();
                    }
                });
            }
        });
        dataBinding.editNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())){
                    if (Integer.valueOf(s.toString())==0){
                        viewModel.exchangeNumber.setValue("1");
                    }else {
                        viewModel.exchangeNumber.setValue(s.toString());
                    }
                    if ((Integer.valueOf(viewModel.exchangeNumber.getValue())*mDataBean.getPoint())>Integer.valueOf(BaseApplication.getIntegrals())){
                        dataBinding.btnExchangeSubmit.setBackgroundResource(R.drawable.bg_shape_grey_circle);
                        dataBinding.btnExchangeSubmit.setEnabled(false);
                    }else {
                        dataBinding.btnExchangeSubmit.setBackgroundResource(R.drawable.bg_shape_red_circle);
                        dataBinding.btnExchangeSubmit.setEnabled(true);
                    }
                    dataBinding.tvExchangePoints.setText(Html.fromHtml("<font color=\"#666666\">"+"共"+viewModel.exchangeNumber.getValue()+"件商品，消耗："+"</font>"+"<font color=\"#FF3A30\">"+(Integer.valueOf(viewModel.exchangeNumber.getValue())*mDataBean.getPoint())+"积分"+"</font>"));
                }
            }
        });

        dataBinding.tvExchangePoints.setText(Html.fromHtml("<font color=\"#666666\">"+"共"+viewModel.exchangeNumber.getValue()+"件商品，消耗："+"</font>"+"<font color=\"#FF3A30\">"+(Integer.valueOf(viewModel.exchangeNumber.getValue())*mDataBean.getPoint())+"积分"+"</font>"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            if (requestCode == 2){
                if (resultCode == RESULT_OK){
                    mAddressBean = (AddressBean) data.getSerializableExtra("addressBean");
                    dataBinding.tvNamePhone.setText(mAddressBean.getName()+"\u3000"+mAddressBean.getPhone());
                    dataBinding.tvAddress.setText(mAddressBean.getDetailedAddress());
                    mExchangeGoodsBean.setLocationId(Integer.valueOf(mAddressBean.getLocationId()));
                    mExchangeGoodsBean.setAddress(mAddressBean.getAddress());
                    mExchangeGoodsBean.setDetailedAddress(mAddressBean.getDetailedAddress());
                    mExchangeGoodsBean.setName(mAddressBean.getName());
                    mExchangeGoodsBean.setPhone(mAddressBean.getPhone());
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        mTitleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_exchange_goods;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
