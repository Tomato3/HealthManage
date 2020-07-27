package com.example.healthmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanage.R;
import com.example.healthmanage.databinding.RecyclerViewItemMyTaskBinding;
import com.example.healthmanage.view.MyTaskRecyclerView;

import java.util.List;

public class MyTaskAdapter extends RecyclerView.Adapter<MyTaskAdapter.ViewHolder> implements View.OnClickListener {

    Context context;

    public List<MyTaskRecyclerView> getMyTaskRecyclerViewList() {
        return myTaskRecyclerViewList;
    }

    public void setMyTaskRecyclerViewList(List<MyTaskRecyclerView> myTaskRecyclerViewList) {
        this.myTaskRecyclerViewList = myTaskRecyclerViewList;
    }

    private List<MyTaskRecyclerView> myTaskRecyclerViewList;

    public static final int ONE_ITEM = 1;
    public static final int TWO_ITEM = 2;

    private OnItemClickListener mOnItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public MyTaskAdapter(Context context, List<MyTaskRecyclerView> myTaskRecyclerViewList) {
        this.context = context;
        this.myTaskRecyclerViewList = myTaskRecyclerViewList;
    }

    @NonNull
    @Override
    public MyTaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (myTaskRecyclerViewList.size() == 0 || myTaskRecyclerViewList == null) {
            View emptyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view_tab, parent, false);
            return new ViewHolder(emptyView);
        } else {
            RecyclerViewItemMyTaskBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(this.context),
                            R.layout.recycler_view_item_my_task, parent,
                            false);
            binding.getRoot().setOnClickListener(this);
            ViewHolder viewHolder = new ViewHolder(binding.getRoot());
            binding.getRoot().setTag(viewHolder);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyTaskAdapter.ViewHolder holder, int position) {
        if (myTaskRecyclerViewList.size() == 0 || myTaskRecyclerViewList == null) {
        } else {
            RecyclerViewItemMyTaskBinding binding = DataBindingUtil.getBinding(holder.itemView);

            // item click
            if (mOnItemClickListener != null) {
                binding.rlBg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.onItemClick(view, position);
                    }
                });
            }

            // item long click
            binding.rlBg.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickListener.onItemLongClick(binding.rlBg, position);
                    return true;
                }
            });

            binding.setMyTaskRecyclerView(this.myTaskRecyclerViewList.get(position));
            binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return myTaskRecyclerViewList == null ? 0 : myTaskRecyclerViewList.size();
    }

    @Override
    public void onClick(View v) {

    }

//    public void togglePosition(int position) {
//        if (expandPosition != position) {//如果闪屏就用notifydatasetchange
//            notifyItemChanged(expandPosition);
//            expandPosition = position;
//        } else {
//            expandPosition = -1;
//        }
//        notifyItemChanged(position);
//
//    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View view = itemView.getRootView().findViewById(R.id.include_menu);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
