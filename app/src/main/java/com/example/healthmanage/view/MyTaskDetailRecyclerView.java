package com.example.healthmanage.view;

import android.util.Log;

import static com.example.healthmanage.utils.Constants.HTAG;

public class MyTaskDetailRecyclerView {

    public String postedBy, description, createTime;

    public MyTaskDetailRecyclerView(String postedBy, String description, String createTime) {
        Log.d(HTAG, "MyTaskDetailRecyclerView==========>: " + postedBy + "===>" + description +
                "===>" + createTime);
        this.postedBy = postedBy + ":";
        this.description = description;
        this.createTime = createTime;
    }
}
