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
import com.example.healthmanage.base.BaseRecyclerAdapter;
import com.example.healthmanage.base.RecyclerViewHolder;
import com.example.healthmanage.bean.HuodongBean;
import com.example.healthmanage.bean.HuodongyuyueBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BFragment extends Fragment {
    private Banner mBanner;
    private SmartRefreshLayout mSmartRefreshLayout;
    private List<String> imgUrl;
    private RecyclerView mRecyclerView;

    public BFragment() {
        // Required empty public constructor
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

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_b, container, false);
        mBanner = view.findViewById(R.id.banner_b);
        mSmartRefreshLayout = view.findViewById(R.id.smart_refresh_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
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
        List<HuodongyuyueBean> list1 = new ArrayList<>();
        list1.add(new HuodongyuyueBean("孕期防范新冠病毒：解答怀孕患者的各项担忧以及科普"));
        list1.add(new HuodongyuyueBean("我竟然在重庆的半山腰上发现了陶渊明式的“桃花源记”"));
        list1.add(new HuodongyuyueBean("我竟然在重庆的半山腰上发现了陶渊明式的“桃花源记”"));
        list1.add(new HuodongyuyueBean("我竟然在重庆的半山腰上发现了陶渊明式的“桃花源记”"));
        list1.add(new HuodongyuyueBean("我竟然在重庆的半山腰上发现了陶渊明式的“桃花源记”"));
        list1.add(new HuodongyuyueBean("我竟然在重庆的半山腰上发现了陶渊明式的“桃花源记”"));
        list1.add(new HuodongyuyueBean("我竟然在重庆的半山腰上发现了陶渊明式的“桃花源记”"));
        list1.add(new HuodongyuyueBean("我竟然在重庆的半山腰上发现了陶渊明式的“桃花源记”"));
        list1.add(new HuodongyuyueBean("我竟然在重庆的半山腰上发现了陶渊明式的“桃花源记”"));
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
        mRecyclerView.setLayoutManager(manager1);
        mRecyclerView.setAdapter(adapter1);
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
}