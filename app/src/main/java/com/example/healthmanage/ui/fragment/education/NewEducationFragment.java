package com.example.healthmanage.ui.fragment.education;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentNewEducationBinding;
import com.example.healthmanage.ui.fragment.business.BusinessFragment;
import com.example.healthmanage.ui.fragment.educationchild.AFragment;
import com.example.healthmanage.ui.fragment.educationchild.BFragment;
import com.example.healthmanage.ui.fragment.educationchild.CFragment;
import com.example.healthmanage.ui.fragment.educationchild.DFragment;
import com.example.healthmanage.ui.fragment.educationchild.EFragment;
import com.example.healthmanage.ui.fragment.educationchild.FFragment;
import com.example.healthmanage.ui.fragment.educationchild.ui.MagazinesFragment;
import com.example.healthmanage.ui.fragment.qualification.FirstStepFragment;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class NewEducationFragment extends BaseFragment<FragmentNewEducationBinding, NewEducationViewModel> {
    private List<String> imgUrl;

    @Override
    protected void initData() {
        imgUrl = new ArrayList<>();
        imgUrl.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Fback_pic%2F03%2F62%2F36%2F8857aab36f66893.jpg&refer=http%3A%2F%2Fbpic.588ku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614567897&t=4ddbac8a854c0c94132427d85bf9d5ae");
        imgUrl.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F02%2F09%2Fdec2de877fc95ea06ce5d459aac84237.jpg&refer=http%3A%2F%2Fpic.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614567897&t=8acb9d26203618dd7821194a154e4455");
        imgUrl.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Fback_pic%2F04%2F11%2F46%2F475819e14eb81a6.jpg&refer=http%3A%2F%2Fbpic.588ku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614567897&t=4df908c831d520b7a9a3b8df6ad7903f");
        dataBinding.bannerEducation.setImageLoader(new NewEducationFragment.GlideImageLoader());
        dataBinding.bannerEducation.setImages(imgUrl);
        dataBinding.bannerEducation.startAutoPlay();
        dataBinding.bannerEducation.start();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

        }
    }

    @Override
    protected void initListener() {

        dataBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeFM(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initView() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new AFragment()).commit();
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void initObserver() {

    }

    private void changeFM(int position) {
        switch (position) {
            case 0:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new AFragment()).commit();
                break;
            case 1:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new MagazinesFragment()).commit();
                break;
            case 2:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new CFragment()).commit();
                break;
            case 3:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new DFragment()).commit();
                break;
            case 4:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new EFragment()).commit();
                break;
            case 5:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_fl, new FFragment()).commit();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBinding.bannerEducation.releaseBanner();
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_new_education;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}