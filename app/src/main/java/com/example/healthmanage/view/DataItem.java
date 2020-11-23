package com.example.healthmanage.view;

import android.util.Log;

import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class DataItem {

    public String[] name;
    public List<String> data;

    public DataItem(String[] name, List<String> data) {
        this.name = name;
        this.data = data;
        Log.d(HTAG, "DataItem==========>: " + data.get(0));
    }
}
