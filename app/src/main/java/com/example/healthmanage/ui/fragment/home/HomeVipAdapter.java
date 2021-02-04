package com.example.healthmanage.ui.fragment.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.R;
import com.example.healthmanage.bean.network.response.MyMemberResponse;
import com.example.healthmanage.bean.test.HomeVipBean;

import java.util.List;

/**
 * Created by sunguiyong on 2020/12/23
 */
public
class HomeVipAdapter extends RecyclerView.Adapter<HomeVipAdapter.MyViewHolder> {
    private List<MyMemberResponse.DataBean> mList;
    private Context mContext;
    private int resourceId;

    public void setList(List<MyMemberResponse.DataBean> list) {
        mList = list;
    }

    public HomeVipAdapter(List<MyMemberResponse.DataBean> list, Context context, int resourceId) {
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
        holder.name.setText(mList.get(position).getNickName());
        String s = "";
        switch (mList.get(position).getRank()) {
            case 0:
                s = "高级会员";
                break;
            case 1:
                s = "贵宾会员";
                break;
            case 2:
                s = "至尊会员";
                break;
        }
        holder.level.setText(s);
        Glide.with(mContext)
                .load("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3126544359,3032901862&fm=26&gp=0.jpg")
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ava);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, level;
        ImageView ava, levelImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            level = itemView.findViewById(R.id.level);
            ava = itemView.findViewById(R.id.ava_img);
            levelImg = itemView.findViewById(R.id.level_img);
        }
    }
}
