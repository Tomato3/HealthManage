package com.example.healthmanage.ui.fragment.education;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentEducationBinding;
import com.example.healthmanage.ui.fragment.home.HomeFragment;
import com.example.healthmanage.ui.fragment.my.MyFragment;
import com.example.healthmanage.bean.recyclerview.ArticleRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EducationFragment extends BaseFragment<FragmentEducationBinding, EducationViewModel> {

    BaseAdapter contentAdapter;
    private List<ArticleRecyclerView> articleRecyclerViewList;
    private List<Fragment> mFragments;

    @Override
    protected void initData() {
//        initFragment();
    }

    @Override
    protected void initListener() {

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
    protected void registerUIChangeLiveDataCallBack() {
        super.registerUIChangeLiveDataCallBack();
        articleRecyclerViewList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            articleRecyclerViewList.add(new ArticleRecyclerView("文章标题",
                    "文章内容文章内容文章内容",
                    "4",
                    "2020-7-31 11:11:11",
                    "99",
                    "99",
                    "http://b-ssl.duitang.com/uploads/item/201803/02/20180302222228_v3JdH.jpeg"));
        }
        contentAdapter = new BaseAdapter(getActivity(), articleRecyclerViewList, R.layout.recycler_view_item_article,
                BR.ArticleRecyclerView);
        dataBinding.recyclerViewEducationContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerViewEducationContent.setAdapter(contentAdapter);
//        dataBinding.rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.rb_recommend:
//                        Log.d(HTAG, "onCheckedChanged==========>: ");
//                        dataBinding.frameLayout.setCurrentItem(0);
////                        changeFragment(0);
//                        break;
//                    case R.id.rb_health_care:
//                        dataBinding.frameLayout.setCurrentItem(1);
////                        changeFragment(1);
//                        break;
//                }
//            }
//        });

        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new MyFragment());


//        dataBinding.frameLayout.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mFragments));
//        dataBinding.frameLayout.setOffscreenPageLimit(mFragments.size());
//        dataBinding.frameLayout.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
////                changeFragment(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    /**
     * 初始化fragment
     */
//    private void initFragment() {
//        mFragments = new ArrayList<>();
//        mFragments.add(new RecommendFragment());
//        mFragments.add(new HealthCareFragment());

//        changeFragment(0);
//    }

//    /**
//     * 切换fragment
//     *
//     * @param position
//     */
//    private void changeFragment(int position) {
//        hideAllFragment();
//        Log.d(HTAG, "changeFragment==========>: ");
//        FragmentTransaction transaction =
//                getChildFragmentManager().beginTransaction();
//        Fragment currentFragment =
//                getChildFragmentManager().findFragmentByTag(position + "");
//        if (currentFragment != null) {
//            transaction.show(currentFragment);
//        } else {
//            currentFragment = mFragments.get(position);
//            transaction.show(currentFragment);
//        }
//        transaction.commit();
//    }

//    /**
//     * 隐藏所有Fragment
//     */
//    private void hideAllFragment() {
//        FragmentTransaction transaction =
//                getChildFragmentManager().beginTransaction();
//        for (int i = 0; i < mFragments.size(); i++) {
//            Fragment currentFragment =
//                    getChildFragmentManager().findFragmentByTag(i + "");
//            if (currentFragment != null) {
//                transaction.hide(currentFragment);
//            }
//        }
//        transaction.commitAllowingStateLoss();
//    }
    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_education;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}
