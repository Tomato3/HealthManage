package com.example.healthmanage.ui.fragment.educationchild.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanage.R;


/**
 * Created by 86137 on 2021/6/9.
 */
public class BookCateContentVH extends RecyclerView.ViewHolder {
    private TextView report_name_tv;

    public BookCateContentVH(@NonNull View itemView) {
        super(itemView);
        report_name_tv = itemView.findViewById(R.id.content_tv);
    }

    public void render(String shiduan) {
        report_name_tv.setText(shiduan);
    }
}
