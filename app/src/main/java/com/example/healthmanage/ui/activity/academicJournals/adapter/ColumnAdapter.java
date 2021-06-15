package com.example.healthmanage.ui.activity.academicJournals.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.contrarywind.adapter.WheelAdapter;
import com.example.healthmanage.R;

import java.util.ArrayList;
import java.util.List;

public class ColumnAdapter extends BaseQuickAdapter<String, BaseViewHolder> implements WheelAdapter {
    private List<String> list = new ArrayList<>();
    public ColumnAdapter(@Nullable List<String> data) {
        super(R.layout.item_job,data);
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_content,item);
    }

    @Override
    public int getItemsCount() {
        return list.size();
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }
}
