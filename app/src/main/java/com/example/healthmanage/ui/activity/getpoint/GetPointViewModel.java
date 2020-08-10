package com.example.healthmanage.ui.activity.getpoint;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.Banner;

import java.util.ArrayList;
import java.util.List;

public class GetPointViewModel extends BaseViewModel {

    public MutableLiveData<List<Banner>> bannerMutableLiveData = new MutableLiveData<>();

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
}
