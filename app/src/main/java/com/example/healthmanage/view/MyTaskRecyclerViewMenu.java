package com.example.healthmanage.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.healthmanage.R;

public class MyTaskRecyclerViewMenu {
    public String status;
    Context context;

    public MyTaskRecyclerViewMenu(Context context, String status) {
        this.status = status;
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item_my_task_menu
                , null);
        TextView textView = view.findViewById(R.id.tv_progress);
        textView.setText(status);
    }
}
