package com.example.healthmanage.ui.activity.vipmanager;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityVipTeamBinding;
import com.example.healthmanage.ui.activity.vipmanager.adapter.VipAdapter;

/**
 * 会员管理
 */
public class MemberManageListActivity extends BaseActivity<ActivityVipTeamBinding,VipTeamViewModel> {
    private int type;
    private LinearLayoutManager manager = new LinearLayoutManager(this);
    private VipAdapter vipAdapter;
    private Context mContext;
    private int tabPosition;
    private int mPosition;

    @Override
    protected void initData() {

    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_vip_team;
    }
}
