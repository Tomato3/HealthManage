package com.example.healthmanage.bean;

import java.util.List;

public class Banner {

    public List<Integer> bannerUrl;
    public List<String> bannerTitle;

    public Banner(List<Integer> bannerUrl, List<String> bannerTitle) {
        this.bannerUrl = bannerUrl;
        this.bannerTitle = bannerTitle;
    }

    public List<Integer> getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(List<Integer> bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public List<String> getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(List<String> bannerTitle) {
        this.bannerTitle = bannerTitle;
    }
}
