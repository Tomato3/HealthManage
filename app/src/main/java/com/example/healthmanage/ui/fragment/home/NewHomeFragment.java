package com.example.healthmanage.ui.fragment.home;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.bean.test.HomeVipBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public
class NewHomeFragment extends Fragment {
    private View includeVVip;
    private RecyclerView mRecyclerView;
    private Banner mBanner;
    private ImageView ava;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_new_home, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        includeVVip = (View) getActivity().findViewById(R.id.include_vvip);
        mRecyclerView = includeVVip.findViewById(R.id.recyler_view);
        mBanner = getActivity().findViewById(R.id.banner_home);
        ava = getActivity().findViewById(R.id.avatar_img);
        initRV();
        initBanner();
        Glide.with(getContext())
                .load("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1629493508,3312904397&fm=26&gp=0.jpg")
                .apply(new RequestOptions().circleCrop())
                .into(ava);
    }

    private void initRV() {
        List<HomeVipBean> mList = new ArrayList<>();
        mList.add(new HomeVipBean("陈羽凡", "高级会员"));
        mList.add(new HomeVipBean("胡海泉", "普通会员"));
        mList.add(new HomeVipBean("特朗普", "高级会员"));
        mList.add(new HomeVipBean("崔斯特", "普通会员"));
        mList.add(new HomeVipBean("弗雷格斯", "高级会员"));
        mList.add(new HomeVipBean("伊芙琳", "高级会员"));

        HomeVipAdapter adapter = new HomeVipAdapter(mList, getContext(), R.layout.item_home_vvip);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
    }

    private void initBanner() {
        List<String> listImgUrl = new ArrayList<>();
        listImgUrl.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fa2.att.hudong.com%2F27%2F81%2F01200000194677136358818023076.jpg&refer=http%3A%2F%2Fa2.att.hudong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611307848&t=a5f4a27dacdb17c33d9344f5b4b7dcf3");
        listImgUrl.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3069319920,593553609&fm=26&gp=0.jpg");
        listImgUrl.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2024517774,3632014209&fm=26&gp=0.jpg");
        mBanner.setImages(listImgUrl);
        mBanner.setImageLoader(new MyImageLoader());
        mBanner.startAutoPlay();
        mBanner.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBanner.stopAutoPlay();
        mBanner.releaseBanner();
    }

    class MyImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(imageView);
        }
    }
}