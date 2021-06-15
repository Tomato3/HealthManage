package com.example.healthmanage.utils;

import android.graphics.Color;

import com.example.healthmanage.ui.activity.memberdetail.bean.BreathRateBean;
import com.example.healthmanage.ui.activity.memberdetail.bean.HeartRateBean;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MPChartUtils {
    private static XAxis xAxis;                //X轴
    private static YAxis leftYAxis;            //左侧Y轴
    private static YAxis rightYaxis;           //右侧Y轴
    private static Legend legend;              //图例
    private LimitLine limitLine;        //限制线
    private List<String> xList = new ArrayList<>();
    /**
     * 初始化图表
     */
    public static void initChart(LineChart lineChart,List<String> times,String high,String low, String average) {
        /***图表设置***/
        lineChart.setBackgroundColor(Color.WHITE);
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(false);
        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);

        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        rightYaxis.setEnabled(false);
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(times));
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);
        /***折线图例 标签 设置***/
        legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.NONE);
        legend.setTextSize(12f);
        //显示位置 左上方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
        legend.setXEntrySpace(5f);
        legend.setYEntrySpace(10f);

        LegendEntry legendEntry1 = new LegendEntry();
        legendEntry1.label = "最高"+high;
        LegendEntry legendEntry2 = new LegendEntry();
        legendEntry2.label = "最低"+low;
        LegendEntry legendEntry3 = new LegendEntry();
        legendEntry3.label = "平均"+average;
        List<LegendEntry> legendEntries = Arrays.asList(legendEntry1,legendEntry2,legendEntry3);
        legend.setExtra(legendEntries);
//        legend.setExtra(new int[]{Color.BLACK,Color.BLACK,Color.BLACK},new String[]{"最高"+high,"最低"+low,"平均"+average});
        //去除x轴的网格线
        xAxis.setDrawGridLines(false);
//        rightYaxis.setDrawGridLines(false);
        //去除Y轴的网格线
        leftYAxis.setDrawGridLines(false);
        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);
        lineChart.invalidate();//refresh

    }

    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     * @param lineDataSet 线条
     * @param color 线条颜色
     * @param mode
     */
    public static void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }

    /**
     * 曲线展示
     * @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public static void showHeartRateLineChart(LineChart lineChart , List<HeartRateBean> dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            HeartRateBean data = dataList.get(i);
            /**
             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
             */
            Entry entry = new Entry(i, data.getHeartRate());
            entries.add(entry);
        }
        if (dataList.size()>7){
            lineChart.setScaleMinima(2.0f, 1.0f);
            lineChart.setScaleXEnabled(true);
        }else {
            lineChart.setScaleMinima(0.5f, 1.0f);
        }
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(xList));
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        //整数
        lineDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int n= (int) value;
                return n+"";
            }
        });
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }

    /**
     * 曲线展示
     * @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public static void showBreathRateLineChart(LineChart lineChart , List<BreathRateBean> dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            BreathRateBean data = dataList.get(i);
            /**
             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
             */
            Entry entry = new Entry(i, data.getBreathRate());
            entries.add(entry);
        }
        if (dataList.size()>7){
            lineChart.setScaleMinima(2.0f, 1.0f);
            lineChart.setScaleXEnabled(true);
        }else {
            lineChart.setScaleMinima(0.5f, 1.0f);
        }
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(xList));
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);

        //整数
        lineDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int n= (int) value;
                return n+"";
            }
        });
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }


}

