package com.example.healthmanage.view;

import android.util.Log;

import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class DataItem {

    public String[] name = {"测量时间：", "血糖（小数）mmol/L：", "低压mmHg：",
            "高压mmHg：", "血粘（小数）mPa.s：", "血液循环：",
            "心率次/分钟 （脉搏）：", "体温/度：", "疲劳预估："};
    public List<String> data;

    public DataItem(List<String> data) {
        this.data = data;
        Log.d(HTAG, "DataItem==========>: " + data.get(0));
    }
}
