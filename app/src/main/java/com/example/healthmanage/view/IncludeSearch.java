package com.example.healthmanage.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.healthmanage.R;

import static com.example.healthmanage.utils.Constants.HTAG;

public class IncludeSearch implements View.OnClickListener {

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String hint;
    private OnSearchClickListener onSearchClickListener;
    private Context context;

    public IncludeSearch(Context context, String hint, OnSearchClickListener onSearchClickListener) {
        this.context = context;
        this.hint = hint;
        this.onSearchClickListener = onSearchClickListener;
        initView();
    }

    public void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.include_search, null);
        Button button = view.findViewById(R.id.btn_search);
        button.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
                Log.d(HTAG, "initView==========>: " + hint);
                onSearchClickListener.doSearch(hint);
                break;
        }
    }

    public interface OnSearchClickListener {

        void doSearch(String searchTxt);
    }

}
