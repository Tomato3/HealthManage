package com.example.healthmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanage.R;
import com.example.healthmanage.databinding.RecyclerViewItemArticleBinding;
import com.example.healthmanage.view.ArticleRecyclerView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    Context context;
    private List<ArticleRecyclerView> articleRecyclerViewList;

    public ArticleAdapter(Context context, List<ArticleRecyclerView> articleRecyclerViewList) {
        this.context = context;
        this.articleRecyclerViewList = articleRecyclerViewList;
    }

    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (articleRecyclerViewList.size() == 0 || articleRecyclerViewList == null) {
            View emptyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view_tab, parent, false);
            return new ViewHolder(emptyView);
        } else {
            RecyclerViewItemArticleBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(this.context),
                            R.layout.recycler_view_item_article, parent,
                            false);
            return new ViewHolder(binding.getRoot());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        if (articleRecyclerViewList.size() == 0 || articleRecyclerViewList == null) {
        } else {
            RecyclerViewItemArticleBinding binding = DataBindingUtil.getBinding(holder.itemView);
            binding.setArticleRecyclerView(this.articleRecyclerViewList.get(position));
            binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return articleRecyclerViewList == null ? 0 : articleRecyclerViewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
