package com.example.healthmanage.widget;

import android.util.Log;

import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

/**
 * 会员详情数据展示Item
 */
public class DataItem {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String title;
    public List<String> data;

    public DataItem(String title, List<String> data) {
        Log.d(HTAG, "DataItem==========>: "+title);
        this.title = title;
        this.data = data;
    }
}
