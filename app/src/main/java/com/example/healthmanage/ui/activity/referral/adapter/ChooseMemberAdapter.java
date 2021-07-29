package com.example.healthmanage.ui.activity.referral.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.vipmanager.response.MemberTeamListResponse;

import java.util.List;

public class ChooseMemberAdapter extends BaseQuickAdapter<MemberTeamListResponse.DataBean, BaseViewHolder> {
    Context mContext;
    String sex;
    int sexSign;

    public ChooseMemberAdapter(Context context,@Nullable List<MemberTeamListResponse.DataBean> data) {
        super(R.layout.item_choose_member,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberTeamListResponse.DataBean item) {
        if (item.getAppUser()!=null){
            helper.setText(R.id.vip_name_tv,item.getAppUser().getNickName());
            if (item.getAppUser().getAvatar()!=null){
                Glide.with(mContext).load(item.getAppUser().getAvatar()).apply(new RequestOptions().circleCrop())
                        .into((ImageView) helper.getView(R.id.vip_ava_img));
            }else {
                Glide.with(mContext).load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=259981538,2780056385&fm=11&gp=0.jpg").apply(new RequestOptions().circleCrop())
                        .into((ImageView) helper.getView(R.id.vip_ava_img));
            }
            helper.addOnClickListener(R.id.vip_isfocus_bt);
            /**0:保密
             * 1:男
             * 2:女
             * **/
            sexSign = item.getAppUser().getSex();
            if (sexSign==0){
                sex = "保密";
            }else if (sexSign == 1){
                sex = "男";
            }else{
                sex = "女";
            }
            switch (item.getAppUser().getRank()){
                case 0:
                    helper.setText(R.id.vip_info_tv,"普通会员"+"\u3000｜\u3000"+sex);
                    break;
                case 1:
                    helper.setText(R.id.vip_info_tv,"高级会员"+"\u3000｜\u3000"+sex);
                    break;
                case 2:
                    helper.setText(R.id.vip_info_tv,"贵宾会员"+"\u3000｜\u3000"+sex);
                    break;
                case 3:
                    helper.setText(R.id.vip_info_tv,"至尊会员"+"\u3000｜\u3000"+sex);
                    break;
            }
            helper.getView(R.id.isfocus_tv).setVisibility(item.getStatus()==0 ? View.GONE : View.VISIBLE);
            helper.addOnClickListener(R.id.btn_select_member);
            helper.setChecked(R.id.btn_select_member,item.isSelect() ? true : false);
        }

    }
}
