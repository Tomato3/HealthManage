package com.example.healthmanage.widget;

/**
 * 会员详情数据展示Item
 */
public class DataItem {

    public String firstTxt;
    public String secondTxt;
    public String thirdTxt;
    public String fourthTxt;

    public DataItem(String firstTxt, String secondTxt, String thirdTxt, String fourthTxt) {
        this.firstTxt = firstTxt;
        this.secondTxt = secondTxt;
        this.thirdTxt = thirdTxt;
        this.fourthTxt = fourthTxt;
    }

    public String getFirstTxt() {
        return firstTxt;
    }

    public void setFirstTxt(String firstTxt) {
        this.firstTxt = firstTxt;
    }

    public String getSecondTxt() {
        return secondTxt;
    }

    public void setSecondTxt(String secondTxt) {
        this.secondTxt = secondTxt;
    }

    public String getThirdTxt() {
        return thirdTxt;
    }

    public void setThirdTxt(String thirdTxt) {
        this.thirdTxt = thirdTxt;
    }

    public String getFourthTxt() {
        return fourthTxt;
    }

    public void setFourthTxt(String fourthTxt) {
        this.fourthTxt = fourthTxt;
    }

}
