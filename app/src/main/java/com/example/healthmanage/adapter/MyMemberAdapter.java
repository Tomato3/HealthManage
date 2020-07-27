package com.example.healthmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanage.R;
import com.example.healthmanage.databinding.RecyclerViewItemMyMemberBinding;
import com.example.healthmanage.view.MyMemberRecyclerView;

import java.util.List;

public class MyMemberAdapter extends RecyclerView.Adapter<MyMemberAdapter.ViewHolder> implements View.OnClickListener {

    Context context;

    public List<MyMemberRecyclerView> getMyMemberRecyclerViewList() {
        return myMemberRecyclerViewList;
    }

    public void setMyMemberRecyclerViewList(List<MyMemberRecyclerView> myMemberRecyclerViewList) {
        this.myMemberRecyclerViewList = myMemberRecyclerViewList;
    }

    private List<MyMemberRecyclerView> myMemberRecyclerViewList;
    ImageView textView;

    public MyMemberAdapter(Context context, List<MyMemberRecyclerView> myMemberRecyclerViewList) {
        this.context = context;
        this.myMemberRecyclerViewList = myMemberRecyclerViewList;
    }

    @NonNull
    @Override
    public MyMemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (myMemberRecyclerViewList.size() == 0 || myMemberRecyclerViewList == null) {
            View emptyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view_tab, parent, false);
            return new ViewHolder(emptyView);
        } else {
            RecyclerViewItemMyMemberBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.recycler_view_item_my_member, parent,
                            false);
            return new ViewHolder(binding.getRoot());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyMemberAdapter.ViewHolder holder, int position) {
        if (myMemberRecyclerViewList.size() == 0 || myMemberRecyclerViewList == null) {
        } else {
            RecyclerViewItemMyMemberBinding binding = DataBindingUtil.getBinding(holder.itemView);
            binding.setMyMemberRecyclerView(this.myMemberRecyclerViewList.get(position));
            binding.executePendingBindings();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return myMemberRecyclerViewList == null ? 0 : myMemberRecyclerViewList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.iv_focus:
//                for (int i = 0; i < myMemberRecyclerViewList.size(); i++) {
//                    if (myMemberRecyclerViewList.get(i).isFocusState()) {
//                        Log.d("TAG", "onClick: "+myMemberRecyclerViewList.get(i).isFocusState());
//                        textView.setImageResource(R.drawable.fragment_main_my_member_focus_selected);
//                    } else {
//                        textView.setImageResource(R.drawable.fragment_main_my_member_focus_selected);
//                    }
//
//                }
//                Log.d("TAG", "onClick: " +
//                        "=============================>");
//                break;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewItemMyMemberBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            textView = (ImageView) itemView.findViewById(R.id.iv_focus);
//            textView.setOnClickListener(MyMemberAdapter.this);
        }

    }
}
