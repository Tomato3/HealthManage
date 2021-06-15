package com.example.healthmanage.ui.activity.temperature.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.temperature.response.PrescriptionResponse;
import com.example.healthmanage.view.GridItemDecoration;

import java.util.List;

public class PrescriptionAdapter extends BaseQuickAdapter<PrescriptionResponse.DataBean, BaseViewHolder> {
    private Context context;
    private DrugsAdapter drugsAdapter;
    private RecyclerView recyclerView;
    public PrescriptionAdapter(Context context, @Nullable List<PrescriptionResponse.DataBean> data) {
        super(R.layout.item_prescription_model,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PrescriptionResponse.DataBean item) {
        helper.addOnClickListener(R.id.tv_watch_detail);
        helper.setText(R.id.tv_model_name,item.getModelName());
        helper.getView(R.id.rbt_choose_model).setVisibility(View.GONE);
        recyclerView = helper.getView(R.id.recycler_view_prescription);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(context, DividerItemDecoration.VERTICAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider_white));
        if (recyclerView.getItemDecorationCount()==0){
            recyclerView.addItemDecoration(gridItemDecoration);
        }
        drugsAdapter = new DrugsAdapter(item.getDrugList());
        recyclerView.setAdapter(drugsAdapter);
        drugsAdapter.notifyDataSetChanged();
    }
}
