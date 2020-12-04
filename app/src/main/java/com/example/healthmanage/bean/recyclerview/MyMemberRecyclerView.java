package com.example.healthmanage.bean.recyclerview;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.AndroidViewModel;

import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.ui.activity.memberdetail.MemberDetailActivity;
import com.example.healthmanage.ui.activity.memberinfo.MemberInfoActivity;

/**
 * 首页会员RecyclerView
 */
public class MyMemberRecyclerView extends AndroidViewModel {

    public String memberName;
    public String memberRank;
    public boolean focusState;
    public int drawable, memberId;

    public MyMemberRecyclerView(String memberName, String memberRank, boolean focusState, int memberId) {
        super(BaseApplication.getInstance());
        this.memberName = memberName;
        this.memberRank = memberRank;
        this.memberId = memberId;
        this.focusState = focusState;
        if (focusState == false) {
            drawable = R.drawable.fragment_main_my_member_focus_normal;
        } else {
            drawable = R.drawable.fragment_main_my_member_focus_selected;
        }
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

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    //跳转会员详情页面
    public void jumpMemberDetails() {
        Intent intent = new Intent(BaseApplication.getInstance(), MemberDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("MemberName", memberName);
        bundle.putInt("MemberId", memberId);
        bundle.putBoolean("FocusState", focusState);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        BaseApplication.getInstance().getApplicationContext().startActivity(intent);
    }

    //跳转会员简介页面
    public void jumpMemberInfo() {
        Intent intent = new Intent(BaseApplication.getInstance(), MemberInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("MemberName", memberName);
        bundle.putInt("MemberId", memberId);
        bundle.putBoolean("FocusState", focusState);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        BaseApplication.getInstance().getApplicationContext().startActivity(intent);
    }
}
