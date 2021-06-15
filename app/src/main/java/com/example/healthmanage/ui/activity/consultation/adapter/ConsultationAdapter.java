package com.example.healthmanage.ui.activity.consultation.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.consultation.response.ConsultationListResponse;
import com.example.healthmanage.utils.ToolUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ConsultationAdapter extends BaseQuickAdapter<ConsultationListResponse.DataBean, BaseViewHolder> {
    Context context;
    List<String> names;
    public ConsultationAdapter(Context context,@Nullable List<ConsultationListResponse.DataBean> data) {
        super(R.layout.item_consultation_task,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ConsultationListResponse.DataBean item) {
        helper.setText(R.id.tv_consultant_time,"会诊时间:"+ ToolUtil.timeStampToDate(String.valueOf(item.getStartTimeQuery()),null) +"-"+ToolUtil.timeStampToTime(String.valueOf(item.getEndTimeQuery()),null));
        helper.setText(R.id.tv_consultant_create_person,"创建人:"+item.getCreateUserName().getName()+item.getCreateUserName().getRank());
        helper.setText(R.id.tv_main_medical_history,Html.fromHtml("简要病史:<font color=\"#000000\">\t"+item.getBriefHistory()+"</font>"));
        names = new ArrayList<>();
        if (names!=null && names.size()>0){
            names.clear();
        }
        if (item.getExmineName()!=null && item.getExmineName().size()>0){
            for (ConsultationListResponse.DataBean.ExmineNameBean exmineNameBean : item.getExmineName()) {
                if (exmineNameBean!=null){
                    names.add(exmineNameBean.getName());
                }
            }
        }

        helper.setText(R.id.tv_consultation_personal,Html.fromHtml("会诊人员:<font color=\"#000000\">\t"+getStr(names)+"</font>"));
        if (item.getStatus()==1){
            helper.getView(R.id.layout_finish_consultation_task).setVisibility(View.GONE);
            helper.getView(R.id.tv_consultation_button).setVisibility(View.VISIBLE);
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");//格式
            try {
                Date startTime = format.parse(ToolUtil.timeStampToDate(String.valueOf(item.getStartTimeQuery()),null));
                Date currentTime = format.parse(ToolUtil.timeStampToDate(String.valueOf(System.currentTimeMillis()),null));
                if (startTime.getTime() <= currentTime.getTime()){
                    helper.setText(R.id.tv_consultation_button,"进入会诊");
                    helper.addOnClickListener(R.id.tv_consultation_button);
                }else {
                    helper.setText(R.id.tv_consultation_button,"未开始");
                    helper.getView(R.id.tv_consultation_button).setClickable(false);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else {
            helper.getView(R.id.layout_finish_consultation_task).setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.tv_see_consultation_content);
            helper.addOnClickListener(R.id.tv_see_consultation_device);
            helper.getView(R.id.tv_consultation_button).setVisibility(View.GONE);
        }
    }

    /**
     * list中的字段拼接成字符串
     * @param strList
     * @return
     */
    public static String getStr(List<String> strList){
        String result = "";
        if (strList != null && strList.size()>0){
            for (int i = 0; i < strList.size(); i++) {
                result = result + strList.get(i)+',';
            }
            result = result.substring(0,result.length()-1);
        }
        return result;
    }

}
