package com.example.healthmanage.bean.recyclerview;

public class GetPointRecyclerView {

    public int src, point;
    public String title, description, tip, jumpTxt;

    public GetPointRecyclerView(int src, String title, String description, int point, String tip,
                                String jumpTxt) {
        this.src = src;
        this.title = title;
        this.description = description;
        this.point = point;
        this.tip = tip;
        this.jumpTxt = jumpTxt;
    }
}
