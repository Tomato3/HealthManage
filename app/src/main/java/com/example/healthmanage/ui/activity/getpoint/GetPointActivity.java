package com.example.healthmanage.ui.activity.getpoint;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.bean.Banner;
import com.example.healthmanage.databinding.ActivityGetPointBinding;
import com.example.healthmanage.widget.TitleToolBar;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class GetPointActivity extends BaseActivity<ActivityGetPointBinding, GetPointViewModel> implements TitleToolBar.OnTitleIconClickCallBack, OnBannerListener {

    TitleToolBar titleToolBar = new TitleToolBar();

    @Override
    protected void initData() {
        titleToolBar.setTitle("赚取积分");
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);
        viewModel.getBannerUrl();
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.bannerMutableLiveData.observe(this, new Observer<List<Banner>>() {
            @Override
            public void onChanged(List<Banner> banners) {
                dataBinding.bannerGetPoint.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                        .setImageLoader(new MyLoader())
                        .setImages(banners.get(0).bannerUrl)
                        .setBannerTitles(banners.get(0).bannerTitle)
                        .setBannerAnimation(Transformer.Default)
                        .setDelayTime(3000)
                        .isAutoPlay(true)
                        .setIndicatorGravity(BannerConfig.CENTER)
                        .setOnBannerListener(GetPointActivity.this::OnBannerClick)
                        .start();
            }
        });

    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    @Override
    public void OnBannerClick(int position) {
        Log.d(HTAG, "OnBannerClick==========>: " + position);
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_get_point;
    }

}
