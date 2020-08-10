package com.example.healthmanage.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.ui.activity.mytaskdetail.MyTaskDetailActivity;

public class MyTaskRecyclerView {

    public String title;
    public String description;
    public String createTime;
    public int imageSrc, state;
    int taskId;

    public MyTaskRecyclerView(String title, String description, String createTime, int state,
                              int taskId) {
        this.title = title;
        this.description = description;
        this.createTime = createTime;
        this.state = state;
        this.taskId = taskId;
        switch (state) {
            case 0:
                imageSrc = R.drawable.recycler_view_my_task_red;
                break;
            case 1:
                imageSrc = R.drawable.recycler_view_my_task_blue;
                break;
            case 2:
                imageSrc = R.drawable.recycler_view_my_task_green;
                break;
        }
    }

    public void change() {
        Intent intent = new Intent(BaseApplication.getInstance(), MyTaskDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("taskId", taskId);
        bundle.putString("taskTitle", title);
        bundle.putString("taskCreateTime", createTime);
        bundle.putString("dataDate", createTime.substring(0, 10));
        bundle.putInt("state", state);
        intent.putExtras(bundle);
        BaseApplication.getInstance().getApplicationContext().startActivity(intent);
    }
}
