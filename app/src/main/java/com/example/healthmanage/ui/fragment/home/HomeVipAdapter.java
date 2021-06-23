package com.example.healthmanage.ui.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.ui.activity.historydata.HistoryDataActivity;
import com.example.healthmanage.ui.activity.memberdetail.MemberNewDetailActivity;
import com.example.healthmanage.ui.activity.vipmanager.response.MemberTeamListResponse;

import java.util.List;

/**
 * Created by sunguiyong on 2020/12/23
 */
public
class HomeVipAdapter extends RecyclerView.Adapter<HomeVipAdapter.MyViewHolder> {
    private List<MemberTeamListResponse.DataBean> mList;
    private Context mContext;
    private int resourceId;

    public void setList(List<MemberTeamListResponse.DataBean> list) {
        mList = list;
    }

    public HomeVipAdapter(List<MemberTeamListResponse.DataBean> list, Context context, int resourceId) {
        mList = list;
        mContext = context;
        this.resourceId = resourceId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(mList.get(position).getAppUser().getNickName());
        String s = "";
        switch (mList.get(position).getRank()) {
            case 0:
                s = "普通会员";
                break;
            case 1:
                s = "高级会员";
                break;
            case 2:
                s = "贵宾会员";
                break;
            case 3:
                s = "至尊会员";
                break;
        }
        holder.level.setText(s);
        Glide.with(mContext)
                .load("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3126544359,3032901862&fm=26&gp=0.jpg")
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ava);
        holder.tvGoManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MemberNewDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MemberName", mList.get(position).getAppUser().getNickName());
                bundle.putInt("MemberId", mList.get(position).getId());
//        bundle.putBoolean("FocusState", focusState);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        holder.tvGoHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyIntent = new Intent(mContext, HistoryDataActivity.class);
                Bundle historyBundle = new Bundle();
                historyBundle.putInt("memberId",mList.get(position).getId());
                historyBundle.putString("memberName",mList.get(position).getAppUser().getNickName());
                historyIntent.putExtras(historyBundle);
                mContext.startActivity(historyIntent);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseApplication.getInstance(), MemberNewDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MemberName", mList.get(position).getAppUser().getNickName());
                bundle.putInt("MemberId", mList.get(position).getId());
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, level;
        ImageView ava, levelImg;
        TextView tvGoManager;
        TextView tvGoHistory;
        ConstraintLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            level = itemView.findViewById(R.id.level);
            ava = itemView.findViewById(R.id.ava_img);
            levelImg = itemView.findViewById(R.id.level_img);
            tvGoManager = itemView.findViewById(R.id.textView13);
            tvGoHistory = itemView.findViewById(R.id.textView8);
            layout = itemView.findViewById(R.id.layout_item);
        }
    }
}
