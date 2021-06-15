package com.example.healthmanage.ui.activity.vipmanager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.bean.network.response.MyMemberResponse;

import java.util.List;

public class VipAdapter extends BaseQuickAdapter<MyMemberResponse.DataBean, BaseViewHolder> {
    Context mContext;
    String sex;
    int sexSign;

    public VipAdapter(@Nullable List<MyMemberResponse.DataBean> data, Context context) {
        super(R.layout.item_vipmanager, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyMemberResponse.DataBean item) {
        helper.setText(R.id.vip_name_tv,item.getNickName());
        if (item.getAvatar()!=null){
            Glide.with(mContext).load(item.getAvatar()).apply(new RequestOptions().circleCrop())
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
        sexSign = item.getSex();
        if (sexSign==0){
            sex = "保密";
        }else if (sexSign == 1){
            sex = "男";
        }else{
            sex = "女";
        }
        /**
         * （+"\u3000｜\u3000"+item.getOld()）
         */
        switch (item.getRank()){
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
        if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==9){
            helper.getView(R.id.tv_vip_type).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.tv_vip_type).setVisibility(View.VISIBLE);
            if (item.getUserType()==0){
                helper.getView(R.id.tv_vip_type).setBackgroundResource(R.drawable.bg_temperature_soild);
                helper.setText(R.id.tv_vip_type,"团队患者");
            }else {
                helper.getView(R.id.tv_vip_type).setBackgroundResource(R.drawable.bg_temperature_yellow);
                helper.setText(R.id.tv_vip_type,"私人医生");
            }
        }

        if (item.getFollowStatus()==0){
            helper.setText(R.id.vip_isfocus_bt,"设为特别关注");
            helper.getView(R.id.vip_isfocus_bt).setBackgroundResource(R.drawable.bg_vipmanager_btn);
            helper.getView(R.id.isfocus_tv).setVisibility(View.GONE);
            if (onAddFocusClickListener != null){
                helper.getView(R.id.vip_isfocus_bt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onAddFocusClickListener.OnItemClick(item.getId(),helper.getLayoutPosition());
                        notifyDataSetChanged();
                    }
                });
            }
        }else {
            helper.setText(R.id.vip_isfocus_bt,"取消关注");
            helper.getView(R.id.vip_isfocus_bt).setBackgroundResource(R.drawable.bg_vipmanager_follow);
            helper.getView(R.id.isfocus_tv).setVisibility(View.VISIBLE);
            if (onDeleteFocusClickListener != null){
                helper.getView(R.id.vip_isfocus_bt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDeleteFocusClickListener.OnItemClick(item.getId(),helper.getLayoutPosition());
                        notifyDataSetChanged();
                    }
                });
            }
        }
    }

    public interface OnAddFocusClickListener{
        void OnItemClick(int id,int position);
    }

    public interface OnDeleteFocusClickListener{
        void OnItemClick(int id,int position);
    }

    OnAddFocusClickListener onAddFocusClickListener;
    public void setOnAddFocusClickListener(OnAddFocusClickListener onAddFocusClickListener){
        this.onAddFocusClickListener = onAddFocusClickListener;
    }

    OnDeleteFocusClickListener onDeleteFocusClickListener;
    public void setOnDeleteFocusClickListener(OnDeleteFocusClickListener onDeleteFocusClickListener){
        this.onDeleteFocusClickListener = onDeleteFocusClickListener;
    }

}
