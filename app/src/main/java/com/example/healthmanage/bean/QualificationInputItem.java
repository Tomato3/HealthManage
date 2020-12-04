package com.example.healthmanage.bean;

/**
 * Description:
 * Author:bwang
 * Date:2020/11/30 14:29
 */
public class QualificationInputItem {
    public String title, hint;
    public boolean isShowMore;

    public QualificationInputItem(String title, String hint, boolean isShowMore) {
        this.title = title;
        this.hint = hint;
        this.isShowMore = isShowMore;
    }
}
