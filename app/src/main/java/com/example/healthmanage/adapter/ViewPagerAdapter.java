package com.example.healthmanage.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import static com.example.healthmanage.utils.Constants.HTAG;

public class ViewPagerAdapter extends PagerAdapter {

    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;

    public ViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
        this.fragmentManager = fragmentManager;
        this.fragmentList = fragmentList;
    }

    @Override
    public int getCount() {
        Log.d(HTAG, "getCount==========>: " + fragmentList.size());
        return fragmentList.size();
    }



    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = fragmentList.get(position);
        if (!fragment.isAdded()) { // 如果fragment还没有added
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(fragment, fragment.getClass().getSimpleName());
            ft.commit();
            /**
             * 在用FragmentTransaction.commit()方法提交FragmentTransaction对象后
             * 会在进程的主线程中，用异步的方式来执行。
             * 如果想要立即执行这个等待中的操作，就要调用这个方法（只能在主线程中调用）。
             * 要注意的是，所有的回调和相关的行为都会在这个调用中被执行完成，因此要仔细确认这个方法的调用位置。
             */
            fragmentManager.executePendingTransactions();
        }

//        if (fragment.getView() == null) {
//            container.addView(fragment.getView()); // 为viewpager增加布局
//        }

        return fragment.getView();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}