package com.example.healthmanage.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.ui.activity.healthmanage.HealthManageActivity;
import com.example.healthmanage.ui.activity.historydata.HistoryDataActivity;

/**
 * 首页关注RecyclerView
 */
public class MyFocusRecyclerView {

    public String memberName;
    public String memberRank;
    private int memberId;

    public MyFocusRecyclerView(String memberName, String memberRank, int memberId) {
        this.memberName = memberName;
        this.memberRank = memberRank;
        this.memberId = memberId;
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

    //跳转历史数据页面
    public void jumpHistoryData() {
        Intent intent = new Intent(BaseApplication.getInstance(), HistoryDataActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("memberName", memberName);
        bundle.putInt("memberId", memberId);
        intent.putExtras(bundle);
        BaseApplication.getInstance().getApplicationContext().startActivity(intent);
    }

    public void jumpHealthManage() {
        Intent intent = new Intent(BaseApplication.getInstance(), HealthManageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("memberName", memberName);
        bundle.putInt("memberId", memberId);
        intent.putExtras(bundle);
        BaseApplication.getInstance().getApplicationContext().startActivity(intent);
    }


}
