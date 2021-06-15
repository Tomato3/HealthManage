package com.example.healthmanage.widget;

import android.view.View;

import com.example.healthmanage.R;

public class TitleToolBar {

    private String title;
    private String rightTitle;
    private boolean rightIconVisible;
    private boolean leftIconVisible;
    private boolean rightTitleVisible;
    private int backgroundColor;
    private int rightIconSrc;
    private int backIconSrc;
    private int titleColor;
    private int rightTitleColor;
    private OnTitleIconClickCallBack onTitleIconClickCallBack;

    public TitleToolBar() {
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getRightTitleColor() {
        return rightTitleColor;
    }

    public void setRightTitleColor(int rightTitleColor) {
        this.rightTitleColor = rightTitleColor;
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

    public boolean isRightTitleVisible() {
        return rightTitleVisible;
    }

    public void setRightTitleVisible(boolean rightTitleVisible) {
        this.rightTitleVisible = rightTitleVisible;
    }

    public int getBackIconSrc() {
        return backIconSrc;
    }

    public void setBackIconSrc(int backIconSrc) {
        this.backIconSrc = backIconSrc;
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

    public String getRightTitle() {
        return rightTitle;
    }

    public void setRightTitle(String rightTitle) {
        this.rightTitle = rightTitle;
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
