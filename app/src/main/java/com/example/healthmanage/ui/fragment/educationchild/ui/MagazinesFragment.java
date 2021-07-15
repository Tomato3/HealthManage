package com.example.healthmanage.ui.fragment.educationchild.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentMagazinesPeriodicalsBinding;
import com.example.healthmanage.ui.fragment.education.NewEducationViewModel;
import com.example.healthmanage.ui.fragment.educationchild.adapter.BookListAdapter;
import com.example.healthmanage.ui.fragment.educationchild.response.BookListResponse;
import com.example.healthmanage.ui.fragment.educationchild.response.YearResponse;
import com.example.healthmanage.view.GridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:保健医苑杂志
 * date:2021/7/14 11:06
 * author:bWang
 */
public class MagazinesFragment extends BaseFragment<FragmentMagazinesPeriodicalsBinding, NewEducationViewModel> {
    private YearResponse mYearResponse = new YearResponse();
    private String mYear;
    private List<BookListResponse.DataBean> mBookList;
    private BookListAdapter mBookListAdapter;
    private int mStatus;
    private int mPosition;

    @Override
    protected void initData() {
        mBookList = new ArrayList<>();

        mBookListAdapter = new BookListAdapter(getActivity(),mBookList);
        dataBinding.recyclerViewBook.setLayoutManager(new LinearLayoutManager(getActivity()));
        //最后一个不显示分割线且自定义分割线
        GridItemDecoration gridItemDecoration = new GridItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL);
        gridItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_divider));
        if (dataBinding.recyclerViewBook.getItemDecorationCount()==0){
            dataBinding.recyclerViewBook.addItemDecoration(gridItemDecoration);
        }
        dataBinding.recyclerViewBook.setAdapter(mBookListAdapter);
        viewModel.getYearList();
    }

    @Override
    protected void initListener() {
        viewModel.mYearResponseListMutableData.observe(this, new Observer<YearResponse>() {
            @Override
            public void onChanged(YearResponse yearResponse) {
                if (yearResponse.getData()!=null && yearResponse.getData().size()>0){
                    setRadioButton(yearResponse.getData());
                    viewModel.getBookListByYear(yearResponse.getData().get(0));
                    mYearResponse = yearResponse;
                }
            }
        });
        dataBinding.radioGroupYear.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = getActivity().findViewById(checkedId);
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < mYearResponse.getData().size(); i++) {
                            if (radioButton.getText().toString().equals(mYearResponse.getData().get(i))){
                                mYear = mYearResponse.getData().get(i);
                            }
                        }
                        viewModel.getBookListByYear(mYear);
                    }
                });
            }
        });
        viewModel.mBookListMutableLiveData.observe(this, new Observer<List<BookListResponse.DataBean>>() {
            @Override
            public void onChanged(List<BookListResponse.DataBean> dataBeans) {
                if (dataBeans!=null && dataBeans.size()>0){
                    dataBinding.recyclerViewBook.setVisibility(View.VISIBLE);
                    dataBinding.tvBookDataNull.setVisibility(View.GONE);
                    if (mBookList!=null && mBookList.size()>0){
                        mBookList.clear();
                    }
                    mBookList.addAll(dataBeans);
                    mBookListAdapter.notifyDataSetChanged();
                }else {
                    dataBinding.recyclerViewBook.setVisibility(View.GONE);
                    dataBinding.tvBookDataNull.setVisibility(View.VISIBLE);
                }
            }
        });

        mBookListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_subscribe:
//                        mStatus = mBookList.get(position).getStatus();
                        mPosition = position;
                        if (mBookList.get(position).getStatus() == 0){
                            //订阅
                            viewModel.subscribeBook(String.valueOf(mBookList.get(position).getId()));
                        }else {
                            //取消订阅
                            viewModel.cancelSubscribeBook(String.valueOf(mBookList.get(position).getId()));
                        }
                }
            }
        });

        mBookListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                intent.putExtra("bookId", String.valueOf(mBookList.get(position).getId()));
                intent.putExtra("coverImg", mBookList.get(position).getBookCover());
                startActivity(intent);
            }
        });

        viewModel.isSubscribeSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    mBookList.get(mPosition).setStatus(1);
                }else {
                    mBookList.get(mPosition).setStatus(0);
                }
                mBookListAdapter.notifyItemChanged(mPosition);
            }
        });

        viewModel.isCancelSubscribeSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    mBookList.get(mPosition).setStatus(0);
                }else {
                    mBookList.get(mPosition).setStatus(1);
                }
                mBookListAdapter.notifyItemChanged(mPosition);
            }
        });
    }

    /**
     * 动态设置radiobutton
     *
     * @param list
     */
    private void setRadioButton(List<String> list) {
        dataBinding.radioGroupYear.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(100, 60);
            int margin = 10;
            layoutParams.setMargins(margin, margin, margin, margin);

            RadioButton radioButton = new RadioButton(getActivity());
//            RadioButton radioButton = (RadioButton) getActivity().getLayoutInflater().inflate(R.layout.item_year,null);
            radioButton.setBackgroundResource(R.drawable.radio_button_selector_year);
            radioButton.setButtonDrawable(null);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setText(list.get(i));
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setTextColor(ContextCompat.getColorStateList(getActivity(),R.color.radio_button_selector_year_color));
            radioButton.setTextSize(14);
            dataBinding.radioGroupYear.addView(radioButton);
            if (i==0){
                radioButton.setChecked(true);
            }
        }
    }


    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void initObserver() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_magazines_periodicals;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}
