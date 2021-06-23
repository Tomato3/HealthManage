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
import com.example.healthmanage.ui.activity.vipmanager.response.MemberTeamListResponse;

import java.util.List;

/**
 * desc:
 * date:2021/6/22 14:00
 * author:bWang
 */
public class MemberTeamAdapter extends BaseQuickAdapter<MemberTeamListResponse.DataBean, BaseViewHolder> {
    private Context mContext;
    String sex;
    int sexSign;

    public MemberTeamAdapter(Context context,@Nullable List<MemberTeamListResponse.DataBean> data) {
        super(R.layout.item_vipmanager, data);
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
            helper.addOnClickListener(R.id.vip_delete);
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
                helper.getView(R.id.vip_delete).setVisibility(View.VISIBLE);
                if (onRemoveMemberClickListener!=null){
                    helper.getView(R.id.vip_delete).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onRemoveMemberClickListener.OnItemClick(item.getId(),helper.getLayoutPosition());
                            notifyDataSetChanged();
                        }
                    });
                }
            }else {
                helper.getView(R.id.vip_delete).setVisibility(View.GONE);
            }

            if (item.getStatus()==0){
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
    }

    public interface OnRemoveMemberClickListener{
        void OnItemClick(int id,int position);
    }

    public interface OnAddFocusClickListener{
        void OnItemClick(int id,int position);
    }

    public interface OnDeleteFocusClickListener{
        void OnItemClick(int id,int position);
    }

    OnRemoveMemberClickListener onRemoveMemberClickListener;
    public void setOnRemoveMemberClickListener(OnRemoveMemberClickListener onRemoveMemberClickListener){
        this.onRemoveMemberClickListener = onRemoveMemberClickListener;
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
