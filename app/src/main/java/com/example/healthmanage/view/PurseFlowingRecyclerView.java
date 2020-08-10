package com.example.healthmanage.view;

import android.util.Log;

import static com.example.healthmanage.utils.Constants.HTAG;

public class PurseFlowingRecyclerView {

    public String typeName, typeTime, typeNumber;
    public int typeIcon;

    public PurseFlowingRecyclerView(int typeIcon, String typeName, String typeTime,
                                    String typeNumber) {
        this.typeIcon = typeIcon;
        this.typeName = typeName;
        this.typeTime = typeTime;
        this.typeNumber = typeNumber;
        Log.d(HTAG, "PurseFlowingRecyclerView==========>: " + typeNumber);
    }
}
