package com.example.healthmanage.ui.activity.mywallet;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.view.PurseFlowingRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyWalletViewModel extends BaseViewModel {

    public MutableLiveData<List<PurseFlowingRecyclerView>> purseFlowingMutableLiveData =
            new MutableLiveData<>();

    private List<PurseFlowingRecyclerView> purseFlowingRecyclerViewList;

    public void getPurseFlowing(int type) {
        purseFlowingRecyclerViewList = new ArrayList<>();
        switch (type) {
            case 0:
                for (int i = 0; i < 3; i++) {
                    purseFlowingRecyclerViewList.add(new PurseFlowingRecyclerView(R.drawable.activity_purse_flowing_income, "收入", "7月30日 10:10", "+88.00"));
                    purseFlowingRecyclerViewList.add(new PurseFlowingRecyclerView(R.drawable.activity_purse_flowing_expenditure, "我的消费", "7月30日 11:11", "-88.00"));
                }
                purseFlowingMutableLiveData.setValue(purseFlowingRecyclerViewList);
                break;
            case 1:
                for (int i = 0; i < 3; i++) {
                    purseFlowingRecyclerViewList.add(new PurseFlowingRecyclerView(R.drawable.activity_purse_flowing_income, "收入", "7月30日 10:10", "+88.00"));
                }
                purseFlowingMutableLiveData.setValue(purseFlowingRecyclerViewList);
                break;
            case 2:
                for (int i = 0; i < 3; i++) {
                    purseFlowingRecyclerViewList.add(new PurseFlowingRecyclerView(R.drawable.activity_purse_flowing_expenditure, "我的消费", "7月30日 11:11", "-88.00"));
                }
                purseFlowingMutableLiveData.setValue(purseFlowingRecyclerViewList);
                break;
        }

    }
}
