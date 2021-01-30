package com.example.healthmanage.ui.activity.vipmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityVipTeamBinding;

public class VipTeamActivity extends BaseActivity<ActivityVipTeamBinding,VipTeamViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_team);
    }
}