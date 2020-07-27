package com.example.healthmanage.view;

import android.util.Log;

/**
 *首页关注RecyclerView
 */
public class MyFocusRecyclerView {

    public String memberName;
    public String memberRank;

    public MyFocusRecyclerView(String memberName, String memberRank) {
        this.memberName = memberName;
        this.memberRank = memberRank;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberRank() {
        return memberRank;
    }

    public void setMemberRank(String memberRank) {
        this.memberRank = memberRank;
    }

    public void jumpMemberInfos() {
        Log.d("TAG", "jumpMemberInfos: ==================>");
    }

    public void jumpHistoryData() {
        Log.d("TAG", "jumpHistoryData: ==================>");
    }

    public void jumpHealthManage() {
        Log.d("TAG", "jumpHistoryData: ==================>");
    }


}
