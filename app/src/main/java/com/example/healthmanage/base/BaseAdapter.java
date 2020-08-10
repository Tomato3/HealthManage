package com.example.healthmanage.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanage.R;

import java.util.List;

public class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    private Context context;
    private List<T> recyclerViewList;
    private int itemLayout, variableId;

    private OnItemClickListener onItemClickListener;

    public BaseAdapter(Context context, List<T> recyclerViewList, @LayoutRes int itemLayout, int variableId) {
        this.context = context;
        this.recyclerViewList = recyclerViewList;
        this.itemLayout = itemLayout;
        this.variableId = variableId;
    }

    @NonNull
    @Override
    public BaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (recyclerViewList.size() == 0 || recyclerViewList == null) {
            View emptyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view_tab,
                    parent,
                    false);
            return new ViewHolder(emptyView);
        } else {
            ViewDataBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                            itemLayout, parent,
                            false);
            ViewHolder viewHolder = new ViewHolder(binding.getRoot());
            viewHolder.setBinding(binding);
            binding.getRoot().setTag(viewHolder);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.ViewHolder holder, int position) {
        if (recyclerViewList.size() == 0 || recyclerViewList == null) {
        } else {
            // item click
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(view, position);
                    }
                });
            }

//            // item long click
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    onItemClickListener.onItemLongClick(view, position);
//                    return true;
//                }
//            });
            holder.binding.setVariable(variableId, recyclerViewList.get(position));
            holder.binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return recyclerViewList == null ? 0 : recyclerViewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

    public List<T> getRecyclerViewList() {
        return recyclerViewList;
    }

    public void setRecyclerViewList(List<T> recyclerViewList) {
        this.recyclerViewList = recyclerViewList;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

//        void onItemLongClick(View view, int position);

        void onItemClick(View view, int position);
    }
}
