package com.example.healthmanage.ui.activity.signmember.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.signmember.response.SignMemberResponse;
import com.example.healthmanage.utils.ToolUtil;

import java.util.List;

/**
 * 未签约适配器adapter
 */
public class UnSignAdapter extends BaseQuickAdapter<SignMemberResponse.DataBean, BaseViewHolder> {
    private Context mContext;
    private int sexSign;
    private String sex;
    public UnSignAdapter(Context context,@Nullable List<SignMemberResponse.DataBean> data) {
        super(R.layout.item_unsign_member,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SignMemberResponse.DataBean item) {
        helper.setText(R.id.vip_name_tv,item.getNickName());
        Glide.with(mContext).load(item.getAvatar()).apply(new RequestOptions().placeholder(R.drawable.ic_load_error).error(R.drawable.ic_load_error).circleCrop())
                .into((ImageView) helper.getView(R.id.vip_ava_img));
//        if (!StringUtils.isEmpty(item.getAvatar())){
//            Glide.with(mContext).load(item.getAvatar()).apply(new RequestOptions().circleCrop())
//                    .into((ImageView) helper.getView(R.id.vip_ava_img));
//        }else {
//            Glide.with(mContext).load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=259981538,2780056385&fm=11&gp=0.jpg").apply(new RequestOptions().circleCrop())
//                    .into((ImageView) helper.getView(R.id.vip_ava_img));
//        }
        helper.addOnClickListener(R.id.vip_isfocus_bt);
        /**0:保密
         * 1:男
         * 2:女
         * **/
        sexSign = item.getSex();
        if (sexSign==0){
            sex = "保密";
        }else if (sexSign == 1){
            sex = "男";
        }else{
            sex = "女";
        }
        switch (item.getRank()){
            case 0:
                helper.setText(R.id.vip_info_tv,"普通会员"+"\u3000｜\u3000"+sex+"\u3000｜\u3000"+item.getPhone());
                break;
            case 1:
                helper.setText(R.id.vip_info_tv,"高级会员"+"\u3000｜\u3000"+sex+"\u3000｜\u3000"+item.getPhone());
                break;
            case 2:
                helper.setText(R.id.vip_info_tv,"贵宾会员"+"\u3000｜\u3000"+sex+"\u3000｜\u3000"+item.getPhone());
                break;
            case 3:
                helper.setText(R.id.vip_info_tv,"至尊会员"+"\u3000｜\u3000"+sex+"\u3000｜\u3000"+item.getPhone());
                break;
        }
        if (item.getDoctorSignStatus()==0){
            helper.getView(R.id.tv_sign_date).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_due_date).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_sign_date,"签约时间:"+ToolUtil.timeStampToDate(String.valueOf(item.getStartTime()),null));
            helper.setText(R.id.tv_due_date,"到期时间:"+ToolUtil.timeStampToDate(String.valueOf(item.getEndTime()),null));
            helper.getView(R.id.vip_cancel).setVisibility(View.GONE);
            helper.getView(R.id.tv_sign).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.tv_sign_date).setVisibility(View.GONE);
            helper.getView(R.id.tv_due_date).setVisibility(View.GONE);
            helper.getView(R.id.vip_cancel).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_sign).setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.vip_cancel).addOnClickListener(R.id.tv_sign);
        }
    }
}
