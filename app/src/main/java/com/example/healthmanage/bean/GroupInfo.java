package com.example.healthmanage.bean;

import androidx.recyclerview.widget.RecyclerView;

public class GroupInfo {
    //组号
    private int mGroupID;
    // Header 的 title
    private String mTitle;

    public GroupInfo(int groupId, String title) {
        this.mGroupID = groupId;
        this.mTitle = title;
    }

    public int getGroupID() {
        return mGroupID;
    }

    public void setGroupID(int groupID) {
        this.mGroupID = groupID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }


    public static class SectionDecoration extends RecyclerView.ItemDecoration {

        public interface GroupInfoCallback {
            GroupInfo getGroupInfo(int position);
        }
    }
}
