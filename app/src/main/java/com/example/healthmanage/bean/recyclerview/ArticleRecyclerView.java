package com.example.healthmanage.bean.recyclerview;

public class ArticleRecyclerView {

    public String title, content, settingScore, releaseTime, like, comment, photo;

    public ArticleRecyclerView(String title, String content, String settingScore, String releaseTime
            , String like, String comment, String photo) {
        this.title = title;
        this.content = content;
        this.settingScore = "设置积分：" + settingScore;
        this.releaseTime = "发布时间：" + releaseTime;
        this.like = like;
        this.comment = comment;
        this.photo = photo;
    }
}
