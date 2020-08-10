package com.example.healthmanage.ui.activity.mypoint;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.view.ExchangeCommodityRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyPointViewModel extends BaseViewModel {


    public MutableLiveData<List<ExchangeCommodityRecyclerView>> exchangeCommodityMutableLiveData
            = new MutableLiveData<>();

    private List<ExchangeCommodityRecyclerView> exchangeCommodityRecyclerViewList;

    public void getExchangeCommodity() {
        exchangeCommodityRecyclerViewList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            exchangeCommodityRecyclerViewList.add(new ExchangeCommodityRecyclerView("http://pic" +
                    ".616pic.com/ys_bnew_img/00/11/13/970W1cRad0.jpg", "手表", "积分：9999"));
        }
        exchangeCommodityMutableLiveData.setValue(exchangeCommodityRecyclerViewList);
    }
}
