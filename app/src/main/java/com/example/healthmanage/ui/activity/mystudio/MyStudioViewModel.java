package com.example.healthmanage.ui.activity.mystudio;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.recyclerview.ArticleRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyStudioViewModel extends BaseViewModel {

    public MutableLiveData<String> date = new MutableLiveData<>("");
    public MutableLiveData<List<ArticleRecyclerView>> articleMutableLiveData =
            new MutableLiveData<>();
    private List<ArticleRecyclerView> articleRecyclerViewList;

    public void getArticleList() {
        articleRecyclerViewList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            articleRecyclerViewList.add(new ArticleRecyclerView("文章标题",
                    "文章内容文章内容文章内容",
                    "4",
                    "2020-7-31 11:11:11",
                    "99",
                    "99",
                    "http://b-ssl.duitang.com/uploads/item/201803/02/20180302222228_v3JdH.jpeg"));
        }
        articleMutableLiveData.setValue(articleRecyclerViewList);
    }
}
