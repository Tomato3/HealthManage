package com.example.healthmanage.ui.fragment.educationchild.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthmanage.R;
import com.example.healthmanage.ui.fragment.educationchild.response.BookMenuResponse;
import com.truizlop.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.util.List;

/**
 * desc:
 * date:2021/7/15 10:15
 * author:bWang
 */
public class BookCateAdapter extends SectionedRecyclerViewAdapter<BookCateHeadViewHolder,
        BookCateContentVH, FootViewHolder> {

    private Context mContext;
    // Activity传进来的原始数据集合
    private List<List<BookMenuResponse.DataBean.ListBean>> mDatas;

    // 标题的集合
    private List<String> mTitleList;

    public BookCateAdapter(Context context, List<String> mTitleList, List<List<BookMenuResponse.DataBean.ListBean>> content) {
        mContext = context;
        this.mTitleList = mTitleList;
        this.mDatas = content;
    }

    @Override
    protected int getSectionCount() {
        return mTitleList.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        return mDatas.get(section).size();
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected BookCateHeadViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_book_cate_head, parent, false);
        return new BookCateHeadViewHolder(itemView);
    }

    @Override
    protected FootViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected BookCateContentVH onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_book_cate_content, parent, false);
        return new BookCateContentVH(itemView);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(BookCateHeadViewHolder headerViewHolder, int i) {
        String title = mTitleList.get(i);
        headerViewHolder.render(title);
    }

    @Override
    protected void onBindSectionFooterViewHolder(FootViewHolder footerViewModel, int i) {

    }

    @Override
    protected void onBindItemViewHolder(BookCateContentVH contentVieModel, int i, int i1) {
        // 获取第section段中的第position个数据
        BookMenuResponse.DataBean.ListBean dataBean = mDatas.get(i).get(i1);
        contentVieModel.render(dataBean.getName());
        contentVieModel.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListenerDIY.onItemClick(i, i1);
            }
        });
    }

    public interface OnItemClickListenerDIY {
        void onItemClick(int section, int position);
    }

    private OnItemClickListenerDIY mOnItemClickListenerDIY;

    public OnItemClickListenerDIY getOnItemClickListenerDIY() {
        return mOnItemClickListenerDIY;
    }

    public void setOnItemClickListenerDIY(OnItemClickListenerDIY onItemClickListenerDIY) {
        mOnItemClickListenerDIY = onItemClickListenerDIY;
    }
}
