package com.example.healthmanage.ui.fragment.educationchild.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanage.R;

/**
 * Created by sunguiyong on 2020/12/12
 */
public class BookCateHeadViewHolder extends RecyclerView.ViewHolder {
    TextView date_tv;

    public BookCateHeadViewHolder(@NonNull View itemView) {
        super(itemView);
        date_tv = itemView.findViewById(R.id.head_tv);
    }
    public void render(String text) {
        date_tv.setText(text);
    }
}
