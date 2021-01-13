package com.example.healthmanage.ui.fragment.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanage.R;
import com.example.healthmanage.bean.test.HomeVipBean;

import java.util.List;

/**
 * Created by sunguiyong on 2020/12/23
 */
public
class HomeVipAdapter extends RecyclerView.Adapter<HomeVipAdapter.MyViewHolder> {
    private List<HomeVipBean> mList;
    private Context mContext;
    private int resourceId;

    public HomeVipAdapter(List<HomeVipBean> list, Context context, int resourceId) {
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
        holder.name.setText(mList.get(position).getName());
        holder.level.setText(mList.get(position).getLevel());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, level;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            level = itemView.findViewById(R.id.level);
        }
    }
}
