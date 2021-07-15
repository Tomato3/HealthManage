package com.example.healthmanage.ui.activity.team.ui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.healthmanage.BR;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityBusinessDetailBinding;
import com.example.healthmanage.databinding.ActivityGoFinishBusinessBinding;
import com.example.healthmanage.ui.activity.consultation.ConsultationPatientActivity;
import com.example.healthmanage.ui.activity.memberdetail.bean.CreateTaskBean;
import com.example.healthmanage.ui.activity.team.TeamViewModel;
import com.example.healthmanage.ui.activity.team.bean.BusinessDealBean;
import com.example.healthmanage.ui.activity.team.response.BusinessDetailResponse;
import com.example.healthmanage.ui.fragment.qualification.adapter.GridImageAdapter;
import com.example.healthmanage.utils.GlideEngine;
import com.example.healthmanage.utils.PhotoUtils;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.widget.TitleToolBar;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.ScreenUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * 去完成业务详情
 */
public class BusinessDetailToCompleteActivity extends BaseActivity<ActivityGoFinishBusinessBinding, TeamViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context context;
    private TitleToolBar titleToolBar = new TitleToolBar();
    private int id;
    private List<File> files = new ArrayList<>();
    private List<String> filePaths = new ArrayList<>();
    private List<BusinessDealBean.ListBean> listBeans = new ArrayList<>();
    private GridImageAdapter gridImageAdapter;
    private BusinessDealBean businessDealBean;
    private int status;

    @Override
    protected void initData() {
        businessDealBean = new BusinessDealBean();
        id = getIntent().getIntExtra("id",0);
        context = BusinessDetailToCompleteActivity.this;
        titleToolBar.setTitle("业务详情");
        titleToolBar.setLeftIconVisible(true);
        titleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        titleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(titleToolBar);

        viewModel.getBusineServiceInfo(id);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,5,GridLayoutManager.VERTICAL,false);
        dataBinding.recyclerViewGrid.setLayoutManager(gridLayoutManager);
        dataBinding.recyclerViewGrid.addItemDecoration(new GridSpacingItemDecoration(5, ScreenUtils.dip2px(context,8),false));
        gridImageAdapter = new GridImageAdapter(context,onAddPicClickListener);
        gridImageAdapter.setSelectMax(9);
        dataBinding.recyclerViewGrid.setAdapter(gridImageAdapter);
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(BusinessDetailToCompleteActivity.this)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                    .isWeChatStyle(true)// 是否开启微信图片选择风格
                    .maxSelectNum(9)// 最大图片选择数量 定一个size，如果size有值5-size  size == 0 ? 5 : 5-size
                    .imageSpanCount(5)// 每行显示个数
                    .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                    .isEnableCrop(false)// 是否裁剪
                    .isCompress(true)// 是否压缩
                    .compressQuality(80)// 图片压缩后输出质量 0~ 100
                    .synOrAsy(true)//同步false或异步true 压缩 默认同步
                    .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .selectionData(gridImageAdapter.getData())// 是否传入已选图片
                    //.isDragFrame(false)// 是否可拖动裁剪框(固定)
                    //.videoMaxSecond(15)
                    //.videoMinSecond(10)
                    .isPreviewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    //.cropCompressQuality(90)// 注：已废弃 改用cutOutQuality()
                    .cutOutQuality(90)// 裁剪输出质量 默认100
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.rotateEnabled(true) // 裁剪是否可旋转图片
                    //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                    //.videoQuality()// 视频录制质量 0 or 1
                    //.recordVideoSecond()//录制视频秒数 默认60s
                    //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径  注：已废弃
                    //.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                    .forResult(new MyResultCallback(gridImageAdapter));

        }

    };

    /**
     * 返回结果回调
     */
    public  class MyResultCallback implements OnResultCallbackListener<LocalMedia> {
        private WeakReference<GridImageAdapter> mAdapterWeakReference;
        private String path;

        public MyResultCallback(GridImageAdapter adapter) {
            super();
            this.mAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void onResult(List<LocalMedia> result) {
            if (files!=null && files.size()>0){
                files.clear();
            }
            for (LocalMedia media : result) {
                if (media.isCompressed()){
                    files.add(new File(media.getCompressPath()));
                }else {
                    path = media.getPath();
                    if (path.contains("content://")){
                        Uri uri = Uri.parse(path);
                        path = PhotoUtils.getFilePathByUri_BELOWAPI11(uri,BusinessDetailToCompleteActivity.this);
                    }
                    files.add(new File(path));
                }
            }
            if (mAdapterWeakReference.get() != null) {
                mAdapterWeakReference.get().setList(result);
                mAdapterWeakReference.get().notifyDataSetChanged();
            }
            viewModel.uploadPhotoForPatient(files);
        }

        @Override
        public void onCancel() {
            Log.i(TAG, "PictureSelector Cancel");
        }
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        dataBinding.radioGroupChooseCompleteType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbt_already_complete:
                        status = 2;
                        break;
                    case R.id.rbt_not_complete:
                        status = 1;
                        break;
                }
            }
        });
        viewModel.patientPhotos.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> files) {
                if (files==null){
                    return;
                }
                if (filePaths!=null && filePaths.size()>0){
                    filePaths.clear();
                }
                if (files!=null  && files.size()>0){
                    filePaths.addAll(files);
                }
            }
        });

        viewModel.businessInfoLiveData.observe(this, new Observer<BusinessDetailResponse.DataBean>() {
            @Override
            public void onChanged(BusinessDetailResponse.DataBean dataBean) {
                if (dataBean!=null){
                    switch (dataBean.getAssignStatus()){
                        case 0:
                            dataBinding.tvDealStatus.setText("团队指派-待完成");
                            dataBinding.rbtAlreadyComplete.setChecked(false);
                            dataBinding.rbtNotComplete.setChecked(false);
                            break;
                        case 1:
                            dataBinding.tvDealStatus.setText("平台指派-待完成");
                            dataBinding.rbtAlreadyComplete.setChecked(false);
                            dataBinding.rbtNotComplete.setChecked(false);
                            break;
                    }
                    if (dataBean.getAppUser()!=null){
                        switch (dataBean.getAppUser().getRank()){
                            case 0:
                                dataBinding.tvServiceObjectName.setText(Html.fromHtml("服务对象:\u3000<font color=\"#000000\">"+dataBean.getAppUser().getNickName()+"\u3000|\u3000"+"普通会员"+"</font>"));
                                break;
                            case 1:
                                dataBinding.tvServiceObjectName.setText(Html.fromHtml("服务对象:\u3000<font color=\"#000000\">"+dataBean.getAppUser().getNickName()+"\u3000|\u3000"+"高级会员"+"</font>"));
                                break;
                            case 2:
                                dataBinding.tvServiceObjectName.setText(Html.fromHtml("服务对象:\u3000<font color=\"#000000\">"+dataBean.getAppUser().getNickName()+"\u3000|\u3000"+"贵宾会员"+"</font>"));
                                break;
                            case 3:
                                dataBinding.tvServiceObjectName.setText(Html.fromHtml("服务对象:\u3000<font color=\"#000000\">"+dataBean.getAppUser().getNickName()+"\u3000|\u3000"+"至尊会员"+"</font>"));
                                break;
                        }
                        dataBinding.tvVipPhone.setText(Html.fromHtml("会员电话:\u3000<font color=\"#000000\">"+dataBean.getAppUser().getPhone()+"</font>"));
                    }
                    dataBinding.tvTaskDescribe.setText(Html.fromHtml("任务描述:\u3000<font color=\"#000000\">"+dataBean.getContent()+"</font>"));
                    dataBinding.tvTaskTime.setText(Html.fromHtml("任务时间:\u3000<font color=\"#000000\">"+dataBean.getDate()+"\u3000"+dataBean.getTime()+"</font>"));
                    dataBinding.tvTaskLocation.setText(Html.fromHtml("<font color=\"#000000\">"+dataBean.getAddr()+"</font>"));
                    dataBinding.tvDelegateTime.setText("委派时间:\u3000"+dataBean.getCreateTime());
                    dataBinding.tvDelegatePerson.setText("委派人:\u3000"+dataBean.getAppDoctorInfo().getName()+"\u3000"+dataBean.getAppDoctorInfo().getRank());
                }
            }
        });

        dataBinding.btnQueryPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status==0){
                    ToastUtil.showShort("请选择是否完成");
                }else {
                    businessDealBean.setStatus(status);
                    if (listBeans!=null && listBeans.size()>0){
                        listBeans.clear();
                    }
                    if (filePaths!=null && filePaths.size()>0){
                        for (String path:filePaths) {
                            listBeans.add(new BusinessDealBean.ListBean(path));
                        }
                    }else {
                        listBeans.add(new BusinessDealBean.ListBean(null));
                    }
                    businessDealBean.setId(id);
                    businessDealBean.setList(listBeans);
                    businessDealBean.setDetails(dataBinding.editDetails.getText().toString());
                    viewModel.editBusineService(businessDealBean);
                }
            }
        });
        viewModel.editSucceed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    ToastUtil.showShort("编辑业务成功");
                    finish();
                }
            }
        });
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        titleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_go_finish_business;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
