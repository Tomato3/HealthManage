package com.example.healthmanage.ui.fragment.qualification.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;

import java.util.List;

public class NewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public NewAdapter(@Nullable List<String> data) {
        super(R.layout.gv_filter_image,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
