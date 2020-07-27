package com.example.healthmanage.widget;

import android.view.View;

import com.example.healthmanage.R;

/**
 * 通用下拉框
 */
public class DropdownBar {

    private String title;
    private boolean isMoreVisible, isExpandVisible, isCollapseVisible;

    private OnMoreClickCallback onMoreClickCallback;

    public DropdownBar(String title, boolean isMoreVisible, boolean isExpandVisible, boolean isCollapseVisible) {
        this.title = title;
        this.isMoreVisible = isMoreVisible;
        this.isExpandVisible = isExpandVisible;
        this.isCollapseVisible = isCollapseVisible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMoreVisible() {
        return isMoreVisible;
    }

    public void setMoreVisible(boolean moreVisible) {
        isMoreVisible = moreVisible;
    }

    public void viewOnclick(View view) {
        switch (view.getId()) {
            case R.id.tv_more:
                if (onMoreClickCallback != null) {
                    onMoreClickCallback.onMoreIconClick();
                }
                break;
        }
    }

    public boolean isExpandVisible() {
        return isExpandVisible;
    }

    public void setExpandVisible(boolean expandVisible) {
        isExpandVisible = expandVisible;
    }

    public boolean isCollapseVisible() {
        return isCollapseVisible;
    }

    public void setCollapseVisible(boolean collapseVisible) {
        isCollapseVisible = collapseVisible;
    }

    public interface OnMoreClickCallback {

        void onMoreIconClick();
    }
}
