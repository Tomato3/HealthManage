package com.example.healthmanage.ui.activity.integral.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.example.healthmanage.R;
import com.example.healthmanage.base.BaseActivity;
import com.example.healthmanage.databinding.ActivityGoodsDetailsBinding;
import com.example.healthmanage.ui.activity.integral.IntegralViewModel;
import com.example.healthmanage.ui.activity.integral.response.GoodsDetailResponse;
import com.example.healthmanage.ui.fragment.education.NewEducationFragment;
import com.example.healthmanage.utils.MImageGetter;
import com.example.healthmanage.widget.TitleToolBar;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * desc:商品详情
 * date:2021/7/16 14:58
 * author:bWang
 */
public class GoodsDetailsActivity extends BaseActivity<ActivityGoodsDetailsBinding, IntegralViewModel> implements TitleToolBar.OnTitleIconClickCallBack {
    private Context mContext;
    private TitleToolBar mTitleToolBar = new TitleToolBar();
    private List<String> banners;
    private MImageGetter mImageGetter;
    private int goodsId;
    private GoodsDetailResponse.DataBean mDataBean;

    @Override
    protected void initData() {
        mContext = GoodsDetailsActivity.this;
        mTitleToolBar.setTitle("商品详情");
        mTitleToolBar.setLeftIconVisible(true);
        mTitleToolBar.setTitleColor(getResources().getColor(R.color.colorBlack));
        dataBinding.layoutShopDetailsTitle.toolbarTitle.setBackgroundColor(getResources().getColor(R.color.white));
        mTitleToolBar.setBackIconSrc(R.drawable.back_black);
        viewModel.setTitleToolBar(mTitleToolBar);
        banners = new ArrayList<>();
        goodsId = getIntent().getIntExtra("id",0);
        viewModel.getGoodsInfo(goodsId);
    }

    @Override
    protected void registerUIChangeEventObserver() {
        super.registerUIChangeEventObserver();
        viewModel.mGoodsDetailResponseMutableLiveData.observe(this, new Observer<GoodsDetailResponse>() {
            @Override
            public void onChanged(GoodsDetailResponse goodsDetailResponse) {
                if (goodsDetailResponse!=null){
                    mDataBean = goodsDetailResponse.getData();
                    //轮播图片
                    if (!TextUtils.isEmpty(goodsDetailResponse.getData().getDetailsPoster())){
                        banners = Stream.of(goodsDetailResponse.getData().getDetailsPoster().split(",")).collect(Collectors.toList());
                    }
                    dataBinding.goodsBanner.setImageLoader(new GlideImageLoader());
                    dataBinding.goodsBanner.setImages(banners);
                    dataBinding.goodsBanner.startAutoPlay();
                    dataBinding.goodsBanner.start();

                    if (!TextUtils.isEmpty(goodsDetailResponse.getData().getDetails())){
                        dataBinding.tvDetails.setText(Html.fromHtml(goodsDetailResponse.getData().getDetails(),new MImageGetter(mContext,dataBinding.tvDetails),null));
                    }
                }
            }
        });
        dataBinding.ivExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ExchangeGoodsActivity.class);
                intent.putExtra("goodsInfo",mDataBean);
                startActivity(intent);
            }
        });
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

        }
    }

    @Override
    public void initViewListener() {
        super.initViewListener();
        mTitleToolBar.setOnClickCallBack(this);
    }

    @Override
    protected int initVariableId() {
        return BR.ViewModel;
    }

    @Override
    protected int setContentViewSrc(Bundle savedInstanceState) {
        return R.layout.activity_goods_details;
    }

    @Override
    public void onRightIconClick() {

    }

    @Override
    public void onBackIconClick() {
        finish();
    }
}
