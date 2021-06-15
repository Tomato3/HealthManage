package com.example.healthmanage.widget;

import android.view.View;

import com.example.healthmanage.R;

public class DropdownNewBar {
    private String title;
    private boolean isExpandVisible, isCollapseVisible;

    private DropdownBar.OnMoreClickCallback onMoreClickCallback;

    public DropdownNewBar(String title, boolean isExpandVisible, boolean isCollapseVisible) {
        this.title = title;
        this.isExpandVisible = isExpandVisible;
        this.isCollapseVisible = isCollapseVisible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
