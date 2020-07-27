package com.example.healthmanage.widget;

import android.view.View;

import com.example.healthmanage.R;

public class TitleToolBar {

    private String title;
    private boolean rightIconVisible;
    private boolean leftIconVisible;
    private int backgroundColor;
    private int rightIconSrc;
    private OnTitleIconClickCallBack onTitleIconClickCallBack;

    public TitleToolBar() {
    }


    public boolean isRightIconVisible() {
        return rightIconVisible;
    }

    public void setRightIconVisible(boolean rightIconVisible) {
        this.rightIconVisible = rightIconVisible;
    }

    public boolean isLeftIconVisible() {
        return leftIconVisible;
    }


    public void setLeftIconVisible(boolean leftIconVisible) {
        this.leftIconVisible = leftIconVisible;
    }

    public int getRightIconSrc() {
        return rightIconSrc;
    }

    public void setRightIconSrc(int rightIconSrc) {
        this.rightIconSrc = rightIconSrc;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OnTitleIconClickCallBack getOnClickCallBack() {
        return onTitleIconClickCallBack;
    }

    public void setOnClickCallBack(OnTitleIconClickCallBack onTitleIconClickCallBack) {
        this.onTitleIconClickCallBack = onTitleIconClickCallBack;
    }

    public void viewOnclick(View view) {
        switch (view.getId()) {
            case R.id.iv_right:
                if (onTitleIconClickCallBack != null) {
                    onTitleIconClickCallBack.onRightIconClick();
                }
                break;
            case R.id.iv_back:
                if (onTitleIconClickCallBack != null) {
                    onTitleIconClickCallBack.onBackIconClick();
                }
                break;
        }
    }

    public interface OnTitleIconClickCallBack {
        void onRightIconClick();

        void onBackIconClick();
    }
}
