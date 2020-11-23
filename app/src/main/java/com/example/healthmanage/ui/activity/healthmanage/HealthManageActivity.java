package com.example.healthmanage.ui.activity.healthmanage;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.base.BaseAdapter;
import com.example.healthmanage.databinding.ActivityHealthManageBinding;
import com.example.healthmanage.ui.activity.healthmanage.sport.SportActivity;
import com.example.healthmanage.view.HealthItemRecycleView;
import com.example.healthmanage.widget.TitleToolBar;

import java.util.ArrayList;
import java.util.List;

public class HealthManageActivity extends BaseActivity<ActivityHealthManageBinding, HealthManageViewModel> implements TitleToolBar.OnTitleIconClickCallBack {

    private TitleToolBar titleToolBar = new TitleToolBar();
    private BaseAdapter healthItemAdapter;
    private int[] healthItemDrawable = {R.drawable.activity_health_manage_sport, R.drawable.activity_health_manage_diet,
            R.drawable.activity_health_manage_sleep, R.drawable.activity_health_manage_evaluation,
            R.drawable.activity_health_manage_sign, R.drawable.activity_health_manage_physical_examination,
            R.drawable.activity_health_manage_bodyfat, R.drawable.activity_health_manage_medication,
            R.drawable.activity_health_manage_bodyfat, R.drawable.activity_health_manage_medication};

    private String[] healthItemTitle = {"运动", "饮食", "睡眠", "评估", "体征", "体检", "护理", "用药", "环境", "孕婴"};

    private List<HealthItemRecycleView> healthItemRecycleViewList = new ArrayList<>();

    private Bundle bundle;

    @Override
    protected void initData() {
        titleToolBar.setTitle(getIntent().getExtras().get("memberName") + "的健康管理");
        titleToolBar.setLeftIconVisible(true);
        viewModel.setTitleToolBar(titleToolBar);

        for (int i = 0; i < healthItemTitle.length; i++) {
            healthItemRecycleViewList.add(new HealthItemRecycleView(healthItemDrawable[i],
                    healthItemTitle[i]));
        }
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }


    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();

        healthItemAdapter = new BaseAdapter<>(this, healthItemRecycleViewList, R.layout.recycler_view_item_health_item,
                BR.HealthItemRecycleView);
        dataBinding.recyclerViewHealthItem.setLayoutManager(new GridLayoutManager(this, 5));
        dataBinding.recyclerViewHealthItem.setAdapter(healthItemAdapter);

        healthItemAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                bundle = new Bundle();
                bundle.putString("memberName", String.valueOf(getIntent().getExtras().get("memberName")));
                startActivity(SportActivity.class, bundle);
            }
        });

    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_health_manage;
    }
}
