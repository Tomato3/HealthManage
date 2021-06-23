package com.example.healthmanage.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseFragment;
import com.example.healthmanage.databinding.FragmentNewHomeBinding;
import com.example.healthmanage.ui.activity.invitemember.InviteNewMemberActivity;
import com.example.healthmanage.ui.activity.mytask.MyNewTaskActivity;
import com.example.healthmanage.ui.activity.notice.ui.NewsNoticeActivity;
import com.example.healthmanage.ui.activity.team.TeamActivity;
import com.example.healthmanage.ui.activity.team.TeamSignActivity;
import com.example.healthmanage.ui.activity.team.ui.BusinessDealActivity;
import com.example.healthmanage.ui.activity.team.ui.BusinessTeamActivity;
import com.example.healthmanage.ui.activity.temperature.ui.PrescriptionModelActivity;
import com.example.healthmanage.ui.activity.temperature.ui.SignPrescriptionActivity;
import com.example.healthmanage.ui.activity.temperature.ui.TemperatureActivity;
import com.example.healthmanage.ui.activity.vipmanager.MemberManageListActivity;
import com.example.healthmanage.ui.activity.vipmanager.response.MemberTeamListResponse;
import com.example.healthmanage.ui.activity.workplan.ui.WorkPlanActivity;
import com.example.healthmanage.utils.ToolUtil;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class NewHomeFragment extends BaseFragment<FragmentNewHomeBinding, NewHomeFragmentViewModel> {
    private View includeVVip;
    private RecyclerView mRecyclerView;
    //    private ImageView ava;
    private TextView vipType;
    private List<MemberTeamListResponse.DataBean> mDataBeanList;
    private HomeVipAdapter adapter;
    private static final String TAG = "NewHomeFragment";
    //type是为了从会员管理页面操作后返回到本页面，进行一次数据请求刷新
    private int type;
    private List<String> path = new ArrayList<>();
    private boolean isQualificationSuccess;
    public boolean isTrue;
    TextView tvTemperatureSize;
    TextView tvTaskSize;
    TextView tvWorkPlanSize;

    @Override
    public void onResume() {
        super.onResume();

        if (isTrue){
            if (type == 0) {
                viewModel.getMemberTeamList("0,1,2,3",1);

            } else {
                viewModel.getMemberTeamList(String.valueOf(type-1),0);
            }
            tvTemperatureSize = dataBinding.include.findViewById(R.id.textView5);
            tvTaskSize = dataBinding.include.findViewById(R.id.jixun_da);
            tvWorkPlanSize = dataBinding.include.findViewById(R.id.jihuazhi);
            viewModel.temperatureSize.observe(this, new Observer<String>() {
                @Override
                public void onChanged(String size) {
                    if (TextUtils.isEmpty(size)){
                        tvTemperatureSize.setText("0");
                    }else {
                        tvTemperatureSize.setText(size);
                    }
                }
            });
            viewModel.healthTaskSize.observe(this, new Observer<String>() {
                @Override
                public void onChanged(String size) {
                    if (TextUtils.isEmpty(size)){
                        tvTaskSize.setText("0");
                    }else {
                        tvTaskSize.setText(size);
                    }
                }
            });
            viewModel.workPlanSize.observe(this, new Observer<String>() {
                @Override
                public void onChanged(String size) {
                    if (TextUtils.isEmpty(size)){
                        tvWorkPlanSize.setText("0");
                    }else {
                        tvWorkPlanSize.setText(size);
                    }
                }
            });

        }else {
            return;
        }

    }

    @Override
    public void initDataBindingAndViewModel(Bundle savedInstanceState) {
        super.initDataBindingAndViewModel(savedInstanceState);
    }

    @Override
    protected void registerUIChangeLiveDataCallBack() {
        super.registerUIChangeLiveDataCallBack();
    }

    @Override
    protected void initListener() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    private void initRV() {
        if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==11){
            dataBinding.includeHomeChooseDoctor.setVisibility(View.VISIBLE);
            dataBinding.includeHomeChoose.setVisibility(View.GONE);
            dataBinding.includeHomeChooseDietitian.setVisibility(View.GONE);
        }else if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()==9){
            dataBinding.includeHomeChooseDoctor.setVisibility(View.GONE);
            dataBinding.includeHomeChoose.setVisibility(View.VISIBLE);
            dataBinding.includeHomeChooseDietitian.setVisibility(View.GONE);
        }else {
            dataBinding.includeHomeChooseDoctor.setVisibility(View.GONE);
            dataBinding.includeHomeChoose.setVisibility(View.GONE);
            dataBinding.includeHomeChooseDietitian.setVisibility(View.VISIBLE);
        }

        isTrue = getActivity().getIntent().getBooleanExtra("isSuccess",false);
        if (isTrue){
            dataBinding.tvNorenzheng.setVisibility(View.GONE);
            dataBinding.layoutUserInfo.setVisibility(View.VISIBLE);
            dataBinding.nameTv.setText(BaseApplication.getUserInfoBean().getAppDoctorInfo().getName());
            dataBinding.typeTv.setText(BaseApplication.getUserInfoBean().getAppDoctorInfo().getRank());
            dataBinding.layoutIncludeHomeQualificition.setVisibility(View.GONE);
            dataBinding.tablayout.setVisibility(View.VISIBLE);
            dataBinding.includeVvip.setVisibility(View.VISIBLE);
            includeVVip = (View) getActivity().findViewById(R.id.include_vvip);
            mRecyclerView = includeVVip.findViewById(R.id.recyler_view);
            vipType = dataBinding.includeVvip.findViewById(R.id.vip_type_tv);
            vipType.setText("重点关注会员");
            mDataBeanList = new ArrayList<>();
            adapter = new HomeVipAdapter(mDataBeanList, getContext(), R.layout.item_home_vvip);
            LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setAdapter(adapter);
            dataBinding.include.setOnClickListener(null);
            click();
        }else {
            dataBinding.tvNorenzheng.setVisibility(View.VISIBLE);
            dataBinding.layoutUserInfo.setVisibility(View.GONE);
            dataBinding.layoutIncludeHomeQualificition.setVisibility(View.VISIBLE);
            dataBinding.tablayout.setVisibility(View.GONE);
            dataBinding.includeVvip.setVisibility(View.GONE);
            dataBinding.include.setOnClickListener(null);
        }
        Glide.with(getContext())
                .load("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1629493508,3312904397&fm=26&gp=0.jpg")
                .apply(new RequestOptions().circleCrop())
                .into(dataBinding.avatarImg);
    }

    private void click() {
        dataBinding.includeVvip.findViewById(R.id.allofvip_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MemberManageListActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });
        dataBinding.includeHomeChoose.findViewById(R.id.huiyuanguanli).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MemberManageListActivity.class);
                intent.putExtra("type", 99);
                startActivity(intent);
            }
        });
        dataBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                type = tab.getPosition();
                if (mDataBeanList !=null) {
                    mDataBeanList.clear();
                }
                vipType = dataBinding.includeVvip.findViewById(R.id.vip_type_tv);
                vipType.setText(tab.getText());
                Log.e("tab.getposition", "onTabSelected: " + tab.getPosition());
                if (tab.getPosition() == 0) {//如果是0，查询我的关注接口数据
                    viewModel.getMemberTeamList("0,1,2,3",1);
                } else {//如果是其他的1 2 3   则查询不同等级的会员数据  0 1 2
                    viewModel.getMemberTeamList(String.valueOf(type-1),0);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        dataBinding.include.findViewById(R.id.constraintLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TemperatureActivity.class);
            }
        });
        dataBinding.include.findViewById(R.id.constraintLayout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyNewTaskActivity.class);
            }
        });
        dataBinding.include.findViewById(R.id.constraintLayout3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(WorkPlanActivity.class);
            }
        });
        dataBinding.include.findViewById(R.id.constraintLayout4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BusinessDealActivity.class);
            }
        });

        dataBinding.includeHomeChoose.findViewById(R.id.linearLayout3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(InviteNewMemberActivity.class);
//                startActivity(SignMemberActivity.class);
            }
        });
        dataBinding.includeHomeChoose.findViewById(R.id.linearLayout4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TeamSignActivity.class);
            }
        });
        dataBinding.includeHomeChoose.findViewById(R.id.linearLayout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TeamActivity.class);
            }
        });
        dataBinding.includeHomeChooseDoctor.findViewById(R.id.tv_patient_manage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MemberManageListActivity.class);
                intent.putExtra("type", 99);
                startActivity(intent);
            }
        });
        dataBinding.includeHomeChooseDoctor.findViewById(R.id.tv_business_team).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BusinessTeamActivity.class);
            }
        });
        dataBinding.includeHomeChooseDoctor.findViewById(R.id.tv_rp_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SignPrescriptionActivity.class);
            }
        });
        dataBinding.includeHomeChooseDoctor.findViewById(R.id.tv_sign_patient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(InviteNewMemberActivity.class);
//                startActivity(SignMemberActivity.class);
            }
        });
        dataBinding.includeHomeChooseDoctor.findViewById(R.id.tv_rp_template).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PrescriptionModelActivity.class);
            }
        });
        dataBinding.avatarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(HealthRecordActivity.class);
            }
        });
        dataBinding.messageImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(NewsNoticeActivity.class);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {
//        if (BaseApplication.getUserInfoBean().getAppDoctorInfo().getRoleId()!=9){
//            viewModel.getDoctorTeam();
//        }
        viewModel.getHealthConsultStatus(0);
        viewModel.getHealthTaskList(0);
        viewModel.getWorkPlanByTime(ToolUtil.getEndTime(),BaseApplication.getUserInfoBean().getAppDoctorInfo().getSystemUserId());
    }

    @Override
    protected void initAdapter() {
        initRV();
    }

    @Override
    protected void initView() {
        dataBinding.include.findViewById(R.id.imageView4).setAlpha(0.15f);
        dataBinding.include.findViewById(R.id.zixun).setAlpha(0.15f);
        dataBinding.include.findViewById(R.id.gongzuojihua).setAlpha(0.15f);
        dataBinding.include.findViewById(R.id.jihuawode).setAlpha(0.15f);
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void initObserver() {
        viewModel.mListMutableLiveData.observe(this, new Observer<List<MemberTeamListResponse.DataBean>>() {
            @Override
            public void onChanged(List<MemberTeamListResponse.DataBean> dataBeans) {
                if (dataBeans != null && dataBeans.size()>0) {
                    dataBinding.includeVvip.findViewById(R.id.nodata_tv).setVisibility(View.GONE);
                    dataBinding.includeVvip.findViewById(R.id.recyler_view).setVisibility(View.VISIBLE);
//                    LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) dataBinding.includeVvip.findViewById(R.id.recyler_view).getLayoutParams();
//                    linearParams.height = 300;
//                    dataBinding.includeVvip.findViewById(R.id.recyler_view).setLayoutParams(linearParams);
                } else {
                    dataBinding.includeVvip.findViewById(R.id.nodata_tv).setVisibility(View.VISIBLE);
                    dataBinding.includeVvip.findViewById(R.id.recyler_view).setVisibility(View.GONE);
                }
                NewHomeFragment.this.mDataBeanList = dataBeans;
                adapter.setList(dataBeans);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_new_home;
    }

    @Override
    protected int initVIewModelID() {
        return BR.ViewModel;
    }
}