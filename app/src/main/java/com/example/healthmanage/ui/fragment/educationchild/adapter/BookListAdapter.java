package com.example.healthmanage.ui.fragment.educationchild.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.fragment.educationchild.response.BookListResponse;

import java.util.List;

/**
 * desc:
 * date:2021/7/14 11:51
 * author:bWang
 */
public class BookListAdapter extends BaseQuickAdapter<BookListResponse.DataBean, BaseViewHolder> {
    private Context mContext;
    public BookListAdapter(Context context,@Nullable List<BookListResponse.DataBean> data) {
        super(R.layout.item_magazines,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BookListResponse.DataBean item) {
        Glide.with(mContext)
                .load(item.getBookCover())
                .error(R.drawable.ic_book_pic)
                .into((ImageView) helper.getView(R.id.iv_book_pic));
        helper.setText(R.id.tv_book_name,item.getBookName());
        helper.setText(R.id.tv_book_time,item.getBookNumber());
        helper.setText(R.id.tv_subscribe,item.getStatus()==0 ?"订阅":"已订阅");
        helper.setTextColor(R.id.tv_subscribe,item.getStatus()==0 ?mContext.getResources().getColor(R.color.colorBlue):mContext.getResources().getColor(R.color.white));
        helper.getView(R.id.tv_subscribe).setBackgroundResource(item.getStatus()==0 ?R.drawable.bg_shape_subscribe:R.drawable.bg_shape_book_soild_blue);
        helper.addOnClickListener(R.id.tv_subscribe);
    }
}
