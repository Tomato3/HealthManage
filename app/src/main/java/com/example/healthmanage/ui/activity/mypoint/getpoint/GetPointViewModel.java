package com.example.healthmanage.ui.activity.mypoint.getpoint;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.Banner;
import com.example.healthmanage.view.GetPointRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GetPointViewModel extends BaseViewModel {

    public MutableLiveData<List<Banner>> bannerMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<GetPointRecyclerView>> getPointMutableLiveData =
            new MutableLiveData<>();

    private List<GetPointRecyclerView> getPointRecyclerViewList;

    private List<Banner> bannerList;
    private List<Integer> bannerUrl;
    private List<String> bannerTitle;

    public void getBannerUrl() {
        bannerList = new ArrayList<>();
        bannerUrl = new ArrayList<>();
        bannerTitle = new ArrayList<>();

        bannerUrl.add(R.drawable.banner_img_one);
        bannerUrl.add(R.drawable.banner_img_two);
        bannerUrl.add(R.drawable.banner_img_three);

        bannerTitle.add("赚取积分须知1");
        bannerTitle.add("赚取积分须知2");
        bannerTitle.add("赚取积分须知3");

        bannerList.add(new Banner(bannerUrl, bannerTitle));

        bannerMutableLiveData.setValue(bannerList);
    }

    public void initGetPoint() {
        getPointRecyclerViewList = new ArrayList<>();

        getPointRecyclerViewList.add(new GetPointRecyclerView(R.drawable.recycler_view_get_point_study,
                "学习", "通过学习获取积分", 1, "已获取1分，每天最高6分", "去学习"));
        getPointRecyclerViewList.add(new GetPointRecyclerView(R.drawable.recycler_view_get_point_read,
                "阅读", "通过阅读获取积分", 2, "已获取1分，每天最高6分", "去阅读"));
        getPointRecyclerViewList.add(new GetPointRecyclerView(R.drawable.recycler_view_get_point_answer,
                "答题", "通过答题获取积分", 3, "已获取1分，每天最高6分", "去答题"));
        getPointRecyclerViewList.add(new GetPointRecyclerView(R.drawable.recycler_view_get_point_action,
                "健康行为", "通过健康行为获取积分", 4, "已获取1分，每天最高6分", "健康行为"));

        getPointMutableLiveData.setValue(getPointRecyclerViewList);
    }
}
