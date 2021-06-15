package com.example.healthmanage.ui.activity.healthrecord.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.healthrecord.response.MedicineBookResponse;
import com.example.healthmanage.utils.ToolUtil;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.tools.ScreenUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MedicineAdapter extends BaseQuickAdapter<MedicineBookResponse.DataBean, BaseViewHolder> {
    private Context mContext;
    private MedicinePicAdapter medicinePicAdapter;
    private RecyclerView gridView;
    private CheckBox checkBox;

    public MedicineAdapter(Context context,@Nullable List<MedicineBookResponse.DataBean> data) {
        super(R.layout.item_medicine_book,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MedicineBookResponse.DataBean item) {
        helper.setText(R.id.medicine_book_title,item.getName());
//        //时间戳转时间
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        long time = item.getCreateTime();
//        Date date = new Date(time);
        helper.setText(R.id.medicine_book_time, item.getCreateTime());
        checkBox = helper.getView(R.id.iv_selector_report);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    helper.getView(R.id.grid_recyclerview_medicine).setVisibility(View.GONE);
                }else {
                    helper.getView(R.id.grid_recyclerview_medicine).setVisibility(View.VISIBLE);
                }
            }
        });
        gridView = helper.getView(R.id.grid_recyclerview_medicine);
        gridView.setLayoutManager(new GridLayoutManager(mContext,3, LinearLayoutManager.VERTICAL,false));
        medicinePicAdapter = new MedicinePicAdapter(mContext,item.getList());
        gridView.setAdapter(medicinePicAdapter);
        gridView.addItemDecoration(new GridSpacingItemDecoration(3, ScreenUtils.dip2px(mContext,8),false));
        medicinePicAdapter.notifyDataSetChanged();
    }
}
