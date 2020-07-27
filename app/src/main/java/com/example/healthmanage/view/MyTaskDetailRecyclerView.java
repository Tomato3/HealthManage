package com.example.healthmanage.view;

import com.example.healthmanage.R;

public class MyTaskDetailRecyclerView {

    public String postedBy;
    public String description;
    public String createTime;
    public int color;

    public MyTaskDetailRecyclerView(String postedBy, String description, String createTime,
                                    int type) {
        this.postedBy = postedBy;
        this.description = description;
        this.createTime = createTime;
        switch (type) {
            case 9:
                color = R.color.colorAccent;
                break;
            case 10:
                color = R.color.colorAccent;
                break;
            case 11:
                color = R.color.colorAccent;
                break;
        }
    }

}
