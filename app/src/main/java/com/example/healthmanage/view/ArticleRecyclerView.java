package com.example.healthmanage.view;

import androidx.lifecycle.MutableLiveData;

public class ArticleRecyclerView {

    public MutableLiveData<String> title = new MutableLiveData<>("标题空");
    public MutableLiveData<String> content = new MutableLiveData<>("内容空");
    public MutableLiveData<String> settingScore = new MutableLiveData<>("设置积分：Null");
    public MutableLiveData<String> releaseTime = new MutableLiveData<>("Null");
    public MutableLiveData<String> like = new MutableLiveData<>("Null");
    public MutableLiveData<String> comment = new MutableLiveData<>("Null");
    public MutableLiveData<String> photo = new MutableLiveData<>("");
}
