package com.example.healthmanage.ui.fragment.educationchild;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.base.BaseRecyclerAdapter;
import com.example.healthmanage.base.RecyclerViewHolder;
import com.example.healthmanage.bean.HuodongBean;
import com.example.healthmanage.bean.HuodongyuyueBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class AFragment extends Fragment {
    private Banner mBanner;
    private List<String> imgUrl;
    private RecyclerView aRv;
    private RecyclerView bRv;
    private SmartRefreshLayout mSmartRefreshLayout;

    public AFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        imgUrl = new ArrayList<>();
        imgUrl.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Fback_pic%2F03%2F62%2F36%2F8857aab36f66893.jpg&refer=http%3A%2F%2Fbpic.588ku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614567897&t=4ddbac8a854c0c94132427d85bf9d5ae");
        imgUrl.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F02%2F09%2Fdec2de877fc95ea06ce5d459aac84237.jpg&refer=http%3A%2F%2Fpic.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614567897&t=8acb9d26203618dd7821194a154e4455");
        imgUrl.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Fback_pic%2F04%2F11%2F46%2F475819e14eb81a6.jpg&refer=http%3A%2F%2Fbpic.588ku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614567897&t=4df908c831d520b7a9a3b8df6ad7903f");

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_a, container, false);
        mBanner = view.findViewById(R.id.banner_a);
        aRv = view.findViewById(R.id.rv_1_a);
        bRv = view.findViewById(R.id.rv_2_a);
        mSmartRefreshLayout = view.findViewById(R.id.smart_refresh_layout);
        mSmartRefreshLayout.setEnableLoadMore(true);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(imgUrl);
        mBanner.startAutoPlay();
        mBanner.start();

        initRv();
        // Inflate the layout for this fragment
        return view;
    }

    private void initRv() {
        List<HuodongBean> list = new ArrayList<>();
        list.add(new HuodongBean("2021???????????????????????????????????????"));
        list.add(new HuodongBean("2021????????????????????????????????????????????????"));
        list.add(new HuodongBean("??????????????????????????????????????????"));
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        BaseRecyclerAdapter<HuodongBean> adapter = new BaseRecyclerAdapter<HuodongBean>(getContext(), list) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_huodong;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, HuodongBean item) {
                holder.setText(R.id.huodong_name, item.getHuodongName());
            }
        };
        aRv.setLayoutManager(manager);
        aRv.setAdapter(adapter);

        List<HuodongyuyueBean> list1 = new ArrayList<>();
        list1.add(new HuodongyuyueBean("????????????????????????????????????????????????????????????????????????"));
        list1.add(new HuodongyuyueBean("???????????????????????????????????????????????????????????????????????????"));
        list1.add(new HuodongyuyueBean("???????????????????????????????????????????????????????????????????????????"));
        list1.add(new HuodongyuyueBean("???????????????????????????????????????????????????????????????????????????"));
        list1.add(new HuodongyuyueBean("???????????????????????????????????????????????????????????????????????????"));
        list1.add(new HuodongyuyueBean("???????????????????????????????????????????????????????????????????????????"));
        list1.add(new HuodongyuyueBean("???????????????????????????????????????????????????????????????????????????"));
        list1.add(new HuodongyuyueBean("???????????????????????????????????????????????????????????????????????????"));
        list1.add(new HuodongyuyueBean("???????????????????????????????????????????????????????????????????????????"));
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        BaseRecyclerAdapter<HuodongyuyueBean> adapter1 = new BaseRecyclerAdapter<HuodongyuyueBean>(getContext(), list1) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_tuijian;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, HuodongyuyueBean item) {
                holder.setText(R.id.content_tv, item.getContent());
            }
        };
        bRv.setLayoutManager(manager1);
        bRv.setAdapter(adapter1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBanner.releaseBanner();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             ?????????
             1.?????????????????????????????????????????????????????????????????????????????????
             2.????????????????????????Object?????????????????????????????????????????????????????????????????????
             ??????????????????????????????????????????????????????Object??????????????????????????????????????????????????????????????????
             ???????????????????????????
             */
            //Glide ????????????????????????
            Glide.with(context).load(path).into(imageView);

        }
    }
}