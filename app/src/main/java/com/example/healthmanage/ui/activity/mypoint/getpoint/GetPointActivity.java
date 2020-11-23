package com.example.healthmanage.ui.activity.mypoint.getpoint;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.bean.Banner;
import com.example.healthmanage.databinding.ActivityGetPointBinding;
import com.example.healthmanage.view.GetPointRecyclerView;
import com.example.healthmanage.widget.TitleToolBar;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class GetPointActivity extends BaseActivity<ActivityGetPointBinding, GetPointViewModel> implements TitleToolBar.OnTitleIconClickCallBack, OnBannerListener {

    TitleToolBar titleToolBar = new TitleToolBar();
    BaseAdapter getPointAdapter;

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
        viewModel.initGetPoint();
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

        List<String> imgs = new ArrayList<>();
        imgs.add(getStringFromDrawableRes(this, R.drawable.banner_img_one));
        imgs.add(getStringFromDrawableRes(this, R.drawable.banner_img_two));
        imgs.add(getStringFromDrawableRes(this, R.drawable.banner_img_three));
        viewModel.bannerMutableLiveData.observe(this, new Observer<List<Banner>>() {
            @Override
            public void onChanged(List<Banner> banners) {
                dataBinding.bannerGetPoint.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                        .setImageLoader(new MyLoader())
                        .setImages(imgs)
                        .setBannerTitles(banners.get(0).bannerTitle)
                        .setBannerAnimation(Transformer.Default)
                        .setDelayTime(3000)
                        .isAutoPlay(true)
                        .setIndicatorGravity(BannerConfig.CENTER)
                        .setOnBannerListener(GetPointActivity.this::OnBannerClick)
                        .start();
            }
        });

        getPointAdapter = new BaseAdapter(this, null, R.layout.recycler_view_item_get_point,
                BR.GetPointRecyclerView);
        dataBinding.recyclerViewGetPoint.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewGetPoint.setAdapter(getPointAdapter);
        viewModel.getPointMutableLiveData.observe(this, new Observer<List<GetPointRecyclerView>>() {
            @Override
            public void onChanged(List<GetPointRecyclerView> getPointRecyclerViews) {
                getPointAdapter.setRecyclerViewList(getPointRecyclerViews);
                getPointAdapter.notifyDataSetChanged();
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
            Glide.with(context).load(String.valueOf(path)).into(imageView);
        }
    }

    //drawable转path
    public static String getStringFromDrawableRes(Context context, int id) {
        Resources resources = context.getResources();
        String path = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + resources.getResourcePackageName(id) + "/"
                + resources.getResourceTypeName(id) + "/"
                + resources.getResourceEntryName(id);
        return path;
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
