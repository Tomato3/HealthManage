package com.example.healthmanage.view;

import android.util.Log;

import static com.example.healthmanage.utils.Constants.HTAG;

public class ExchangeCommodityRecyclerView {

    public String productIcon, productName, productPoint;

    public ExchangeCommodityRecyclerView(String productIcon, String productName, String productPoint) {
        this.productIcon = productIcon;
        this.productName = productName;
        this.productPoint = productPoint;
        Log.d(HTAG, "ExchangeCommodityRecyclerView==========>: " + productName);
    }
}
