package com.example.healthmanage.ui.activity.referral.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.referral.response.ReferralResponse;

import java.util.List;

public class ReferralAdapter extends BaseQuickAdapter<ReferralResponse.DataBean, BaseViewHolder> {
    public ReferralAdapter(@Nullable List<ReferralResponse.DataBean> data) {
        super(R.layout.item_referral_task,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReferralResponse.DataBean item) {
        if (item.getStatus()==0){
            helper.setText(R.id.tv_referral_result_type,"转诊结果：待处理");
        }else if (item.getStatus()==1){
            helper.setText(R.id.tv_referral_result_type,"转诊结果：处理中");
        }else if (item.getStatus()==2){
            helper.setText(R.id.tv_referral_result_type,"转诊结果：已处理-转诊成功");
        }else if (item.getStatus()==3){
            helper.setText(R.id.tv_referral_result_type,"转诊结果：已处理-转诊失败");
        }else if (item.getStatus()==4){
            helper.setText(R.id.tv_referral_result_type,"转诊结果：已处理-取消转诊");
        }
        helper.setText(R.id.tv_referral_patient_name,"转诊患者:"+item.getReferralPatientName());
        helper.setText(R.id.tv_illness_description,"病情描述:"+item.getPatientIllnessDescribe());
        helper.setText(R.id.tv_referral_reason,"转诊原因:"+item.getReferralReasons());
        helper.setText(R.id.tv_referral_time,"创建时间:"+item.getCreateTime());
        helper.setText(R.id.tv_referral_target,"转诊对象:"+item.getChoiseReferral());
    }
}
