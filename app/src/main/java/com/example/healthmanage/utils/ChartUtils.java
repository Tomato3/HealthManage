package com.example.healthmanage.utils;

public class ChartUtils {
    public static int xData(long l) {
        String s = ToolUtil.timeStamp2Date(l + "", null).substring(11, 13);
        int s1 = 0;
        if (s.startsWith("0")) {
            s1 = Integer.parseInt(s.substring(1));
        } else {
            s1 = Integer.parseInt(s);
        }
        return s1;
    }


    //解决ok头部添加中文
    public static String encodeHeadInfo( String headInfo ) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0, length = headInfo.length(); i < length; i++) {
            char c = headInfo.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                stringBuffer.append( String.format ("\\u%04x", (int)c) );
            } else {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }

//    public static void initChart(Context context, LineChart chart1, HistorySleepData historySleepData, String type) {
//        Description description = chart1.getDescription();
//        Legend legend = chart1.getLegend();
//        legend.setEnabled(false);
//
//        description.setEnabled(true);
//        description.setPosition(1f, 1f);
//        description.setText("时间");
//        chart1.setDrawGridBackground(false);
//        chart1.setDrawBorders(false);
//        chart1.setDragEnabled(false);
//        chart1.setTouchEnabled(false);
//        chart1.animateY(1000);
//        chart1.setDescription(description);
//
//        XAxis xAxis = chart1.getXAxis();
//        YAxis leftYAxis = chart1.getAxisLeft();
//        YAxis rightYAxis = chart1.getAxisRight();
//        rightYAxis.setEnabled(false);
//        leftYAxis.setDrawGridLines(false);
//        rightYAxis.setDrawAxisLine(false);
//        leftYAxis.setAxisMinimum(0f);
//
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setAxisMinimum(0f);
//        xAxis.setGranularity(1f);
//        xAxis.setDrawGridLines(false);
//
//        ValueFormatter valueFormatter = new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                String s = "";
//                if (String.valueOf(value).contains(".")) {
//                    s = String.valueOf(value).substring(0, String.valueOf(value).indexOf("."));
//                } else {
//                    s = String.valueOf(value);
//                }
//                return s;
//            }
//        };
//        leftYAxis.setValueFormatter(valueFormatter);
//
//        leftYAxis.setAxisMinimum(0f);
//        List<Entry> entries = new ArrayList<>();
//        if (historySleepData != null && historySleepData.getData() != null && type.equals("heartrate")) {
//            for (int i = 0; i < historySleepData.getData().size(); i++) {
//                entries.add(new Entry(xData(historySleepData.getData().get(i).getDoTime() / 1000),
//                        Integer.parseInt(historySleepData.getData().get(i).getHeartRate())));
//            }
//        } else if (historySleepData != null && historySleepData.getData() != null && type.equals("breathrate")) {
//            for (int i = 0; i < historySleepData.getData().size(); i++) {
//                entries.add(new Entry(xData(historySleepData.getData().get(i).getDoTime() / 1000),
//                        Integer.parseInt(historySleepData.getData().get(i).getBreathRate())));
//            }
//        } else if (historySleepData != null && historySleepData.getData() != null && type.equals("snore")) {
//            for (int i = 0; i < historySleepData.getData().size(); i++) {
//                entries.add(new Entry(xData(historySleepData.getData().get(i).getDoTime() / 1000),
//                        Integer.parseInt(historySleepData.getData().get(i).getSnoring())));
//            }
//        } else {
//            for (int i = 0; i < 5; i++) {
//                entries.add(new Entry(i, new Random().nextInt(50)));
//            }
//        }
//
//        LineDataSet lineDataSet = new LineDataSet(entries, "");
//        lineDataSet.setDrawFilled(true);//去除面积图，折线下的阴影区域
//        lineDataSet.setColor(context.getResources().getColor(R.color.chartcircle));//折线颜色
//        lineDataSet.setCircleHoleColor(context.getResources().getColor(R.color.chartcircle));//折线数据点中心圆形颜色
//        lineDataSet.setCircleColor(context.getResources().getColor(R.color.chartcircle));//折线数据点圆形颜色
//        lineDataSet.setCircleSize(5f);//折线数据点圆形大小
//        lineDataSet.setDrawCircleHole(true);
//        lineDataSet.setDrawIcons(false);
//
//        LineData lineData = new LineData(lineDataSet);
//        lineData.setValueFormatter(valueFormatter);
//        chart1.setData(lineData);
//        chart1.invalidate();
//    }
}
