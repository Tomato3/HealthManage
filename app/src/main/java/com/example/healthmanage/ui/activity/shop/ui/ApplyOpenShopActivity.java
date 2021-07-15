package com.example.healthmanage.ui.activity.shop.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityApplyOpenShopBinding;
import com.example.healthmanage.widget.TitleToolBar;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

/**
 * desc:申请开店
 * date:2021/6/25 16:02
 * author:bWang
 */
public class ApplyOpenShopActivity extends BaseActivity<ActivityApplyOpenShopBinding,ShopViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
   private TitleToolBar mTitleToolBar = new TitleToolBar();
   private Context mContext;
    CityPickerView mCityPickerView;
    String cityData;

    @Override
    protected void initData() {
        mContext = ApplyOpenShopActivity.this;
        mTitleToolBar.setTitle("申请开店");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutShopTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);

        mCityPickerView = new CityPickerView();
        mCityPickerView.init(mContext);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.tvChooseCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityPop();
            }
        });
    }

    private void cityPop() {
        CityConfig cityConfig = new CityConfig.Builder()
                .title("选择城市")
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.item_city_name_tv)
                .setShowGAT(true)//是否显示港澳
                .build();

        mCityPickerView.setConfig(cityConfig);
        mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                StringBuilder sb = new StringBuilder();
                if (province != null) {
                    sb.append(province.getName());
                }

                if (city != null) {
                    sb.append(city.getName());
                }

                if (district != null) {
                    sb.append(district.getName());
                }
                dataBinding.tvChooseCity.setText(sb.toString());
                dataBinding.tvChooseCity.setTextColor(getColor(R.color.colorTxtBlack));
                cityData = sb.toString();
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(mContext, "已取消");
            }
        });
        mCityPickerView.showCityPicker();
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
        return R.layout.activity_apply_open_shop;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
