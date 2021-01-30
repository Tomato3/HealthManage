package com.example.healthmanage.ui.fragment.business;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentNewBusinessBinding;
import com.example.healthmanage.ui.fragment.home.NewHomeFragment;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class NewBusinessFragment extends BaseFragment<FragmentNewBusinessBinding, NewBusinessViewModel> {

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initView() {
        List<String> listImgUrl = new ArrayList<>();
        listImgUrl.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fa2.att.hudong.com%2F27%2F81%2F01200000194677136358818023076.jpg&refer=http%3A%2F%2Fa2.att.hudong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611307848&t=a5f4a27dacdb17c33d9344f5b4b7dcf3");
        listImgUrl.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3069319920,593553609&fm=26&gp=0.jpg");
        listImgUrl.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2024517774,3632014209&fm=26&gp=0.jpg");
        dataBinding.banner.setImages(listImgUrl);
        dataBinding.banner.setImageLoader(new MyImageLoader());
        dataBinding.banner.startAutoPlay();
        dataBinding.banner.start();
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void initObserver() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBinding.banner.stopAutoPlay();
        dataBinding.banner.releaseBanner();
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_new_business;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }

    class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }
}