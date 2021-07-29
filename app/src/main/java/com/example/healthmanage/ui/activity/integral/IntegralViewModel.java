package com.example.healthmanage.ui.activity.integral;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.ui.activity.integral.response.AddressResponse;
import com.example.healthmanage.ui.activity.integral.response.ExchangeGoodsBean;
import com.example.healthmanage.ui.activity.integral.response.ExchangeIntegralResponse;
import com.example.healthmanage.ui.activity.integral.response.GoodsDetailResponse;
import com.example.healthmanage.ui.activity.integral.response.GoodsListResponse;
import com.example.healthmanage.ui.activity.integral.response.IntegralDetailResponse;
import com.example.healthmanage.ui.activity.integral.response.IntegralResponse;
import com.example.healthmanage.ui.activity.integral.response.IntegralRuleResponse;
import com.example.healthmanage.ui.activity.integral.response.LogisticResponse;
import com.example.healthmanage.ui.activity.integral.response.OrderInfoResponse;
import com.example.healthmanage.ui.activity.integral.response.OrderListResponse;
import com.example.healthmanage.ui.activity.integral.ui.IntegralDetailActivity;

import java.util.List;

/**
 * desc:积分
 * date:2021/7/12 9:29
 * author:bWang
 */
public class IntegralViewModel extends BaseViewModel {
    private UsersRemoteSource mUsersRemoteSource;
    public MutableLiveData<List<IntegralRuleResponse.DataBean>> mRuleListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<IntegralResponse> mIntegralResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<GoodsListResponse.DataBean>> mGoodsListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> goodsName = new MutableLiveData<>();
    public MutableLiveData<String> goodsIntegral = new MutableLiveData<>();
    public MutableLiveData<String> goodsPrice = new MutableLiveData<>();
    public MutableLiveData<String> goodsExchangeNumber = new MutableLiveData<>();
    public MutableLiveData<String> logisticsType = new MutableLiveData<>();
    public MutableLiveData<GoodsDetailResponse> mGoodsDetailResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> mDateMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<IntegralDetailResponse.DataBean>> mIntegralDetailListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> exchangeNumber = new MutableLiveData<>("1");
    public MutableLiveData<String> exchangePoints = new MutableLiveData<>();
    public MutableLiveData<String> exchangeTitle = new MutableLiveData<>();
    public MutableLiveData<List<OrderListResponse.DataBean>> mOrderListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<AddressResponse.DataBean> mAddressDataMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isExchangeSucceed = new MutableLiveData<>();
    public MutableLiveData<String> address = new MutableLiveData<>();
    public MutableLiveData<String> goodsDetailName = new MutableLiveData<>();
    public MutableLiveData<String> goodsDetailIntegral = new MutableLiveData<>();
    public MutableLiveData<String> goodsDetailExchangeNum = new MutableLiveData<>();
    public MutableLiveData<String> goodIntegral = new MutableLiveData<>();
    public MutableLiveData<String> goodConsumeIntegral = new MutableLiveData<>();
    public MutableLiveData<String> orderNumberCode = new MutableLiveData<>();
    public MutableLiveData<String> exchangeTime = new MutableLiveData<>();
    public MutableLiveData<String> orderLogisticType = new MutableLiveData<>();
    public MutableLiveData<String> orderSendTime = new MutableLiveData<>();
    public MutableLiveData<OrderInfoResponse.DataBean> mOrderInfoMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<LogisticResponse.DataBean> mLogisticsMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> logisticsCode = new MutableLiveData<>();
    public MutableLiveData<String> logisticsPhone = new MutableLiveData<>();
    public MutableLiveData<String> logisticsName = new MutableLiveData<>();


    public IntegralViewModel() {
        mUsersRemoteSource = new UsersRemoteSource();
    }

    public void getIntegralRule(){
        mUsersRemoteSource.getIntegralRule(BaseApplication.getToken(), new UsersInterface.GetIntegralRuleCallback() {
            @Override
            public void getSucceed(IntegralRuleResponse integralRuleResponse) {
                if (integralRuleResponse.getData()!=null && integralRuleResponse.getData().size()>0){
                    mRuleListMutableLiveData.setValue(integralRuleResponse.getData());
                }else {
                    mRuleListMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                mRuleListMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mRuleListMutableLiveData.setValue(null);
            }
        });
    }

    public void getIntegral(){
        mUsersRemoteSource.getIntegral(BaseApplication.getToken(), new UsersInterface.GetIntegralCallback() {
            @Override
            public void getSucceed(IntegralResponse integralResponse) {
                if (integralResponse.getData()!=null){
                    mIntegralResponseMutableLiveData.setValue(integralResponse);
                }else {
                    mIntegralResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                mIntegralResponseMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mIntegralResponseMutableLiveData.setValue(null);
            }
        });
    }

    public void getGoodsList(String point){
        mUsersRemoteSource.getGoodsList(point, BaseApplication.getToken(), new UsersInterface.GetGoodsListCallback() {
            @Override
            public void getSucceed(GoodsListResponse goodsListResponse) {
                if (goodsListResponse.getData()!=null && goodsListResponse.getData().size()>0){
                    mGoodsListMutableLiveData.setValue(goodsListResponse.getData());
                }else {
                    mGoodsListMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                getUiChangeEvent().getToastTxt().setValue(msg);
                mGoodsListMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mGoodsListMutableLiveData.setValue(null);
            }
        });
    }

    public void getGoodsInfo(int id){
        mUsersRemoteSource.getGoodsInfo(id, BaseApplication.getToken(), new UsersInterface.GetGoodsInfoCallback() {
            @Override
            public void getSucceed(GoodsDetailResponse goodsDetailResponse) {
                if (goodsDetailResponse.getData()!=null){
                    goodsName.setValue(goodsDetailResponse.getData().getName());
                    goodsExchangeNumber.setValue("已兑:"+goodsDetailResponse.getData().getExchangeQuantity());
                    goodsIntegral.setValue(goodsDetailResponse.getData().getPoint()+"分");
                    goodsPrice.setValue("价值:￥"+goodsDetailResponse.getData().getMarketPrice());
                    if (goodsDetailResponse.getData().getLogisticsType()==0){
                        logisticsType.setValue("快递：包邮");
                    }else {
                        logisticsType.setValue("虚拟商品");
                    }
                    mGoodsDetailResponseMutableLiveData.setValue(goodsDetailResponse);
                }else {
                    mGoodsDetailResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                mGoodsDetailResponseMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mGoodsDetailResponseMutableLiveData.setValue(null);
            }
        });
    }

    public void getIntegralDetail(int type,String date){
        mUsersRemoteSource.getIntegralDetail(type, BaseApplication.getToken(), date, new UsersInterface.GetIntegralDetailCallback() {
            @Override
            public void getSucceed(IntegralDetailResponse integralDetailResponse) {
                if (integralDetailResponse.getData()!=null && integralDetailResponse.getData().size()>0){
                    mIntegralDetailListMutableLiveData.setValue(integralDetailResponse.getData());
                }else {
                    mIntegralDetailListMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                mIntegralDetailListMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mIntegralDetailListMutableLiveData.setValue(null);
            }
        });
    }

    public void getOrderList(String status){
        mUsersRemoteSource.getOrderList(status, BaseApplication.getToken(), new UsersInterface.GetOrderListCallback() {
            @Override
            public void getSucceed(OrderListResponse orderListResponse) {
                if (orderListResponse.getData()!=null && orderListResponse.getData().size()>0){
                    mOrderListMutableLiveData.setValue(orderListResponse.getData());
                }else {
                    mOrderListMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                mOrderListMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mOrderListMutableLiveData.setValue(null);
            }
        });
    }

    public void queryOneByUserId(){
        mUsersRemoteSource.queryOneByUserId(BaseApplication.getToken(), new UsersInterface.QueryOneByUserIdCallback() {
            @Override
            public void querySucceed(AddressResponse addressResponse) {
                if (addressResponse.getData()!=null){
                    mAddressDataMutableLiveData.setValue(addressResponse.getData());
                }else {
                    mAddressDataMutableLiveData.setValue(null);
                }
            }

            @Override
            public void queryFailed(String msg) {
                mAddressDataMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mAddressDataMutableLiveData.setValue(null);
            }
        });
    }

    /**
     * 兑换商品
     * @param exchangeGoodsBean
     */
    public void exchangeGoods(ExchangeGoodsBean exchangeGoodsBean){
        mUsersRemoteSource.exchangeGoods(exchangeGoodsBean, new UsersInterface.ExchangeGoodsCallback() {
            @Override
            public void exchangeSucceed(ExchangeIntegralResponse exchangeIntegralResponse) {
                isExchangeSucceed.setValue(true);
            }

            @Override
            public void exchangeFailed(String msg) {
                isExchangeSucceed.setValue(false);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                isExchangeSucceed.setValue(false);
            }
        });
    }

    public void getOrderInfo(int id){
        mUsersRemoteSource.getOrderInfo(id, BaseApplication.getToken(), new UsersInterface.GetOrderInfoCallback() {
            @Override
            public void getSucceed(OrderInfoResponse orderInfoResponse) {
                if (orderInfoResponse.getData()!=null){
                    mOrderInfoMutableLiveData.setValue(orderInfoResponse.getData());
                    address.setValue("地址:"+orderInfoResponse.getData().getAddress()+orderInfoResponse.getData().getDetailedAddress());
                    goodsDetailName.setValue(orderInfoResponse.getData().getAppGoods().getName());
                    goodsDetailIntegral.setValue(orderInfoResponse.getData().getAppGoods().getPoint()+"积分");
                    goodsDetailExchangeNum.setValue("x"+orderInfoResponse.getData().getExchangeQuantity());
                    goodIntegral.setValue(orderInfoResponse.getData().getPoint()+"积分");
                    goodConsumeIntegral.setValue(orderInfoResponse.getData().getPoint()*orderInfoResponse.getData().getExchangeQuantity()+"积分");
                    orderNumberCode.setValue("订单编号"+orderInfoResponse.getData().getOrderNumber());
                    exchangeTime.setValue("兑换时间"+orderInfoResponse.getData().getExchangeTime());
                    if (orderInfoResponse.getData().getLogisticsType()==0){
                        orderLogisticType.setValue("配送方式:包邮");
                    }else {
                        orderLogisticType.setValue("配送方式:虚拟商品");
                    }
                    orderSendTime.setValue("发货时间:"+orderInfoResponse.getData().getDeliverTime());
                }else {
                    mOrderInfoMutableLiveData.setValue(null);
                }
            }

            @Override
            public void getFailed(String msg) {
                mOrderInfoMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mOrderInfoMutableLiveData.setValue(null);
            }
        });
    }

    public void getLogistics(String courierNumber, String courierCompanyAbbr){
        mUsersRemoteSource.getLogistics(courierNumber, courierCompanyAbbr, BaseApplication.getToken(), new UsersInterface.GetLogisticsCallback() {
            @Override
            public void getSucceed(LogisticResponse logisticResponse) {
                if (logisticResponse!=null){
                    if (logisticResponse.getData()!=null){
                        logisticsCode.setValue(logisticResponse.getData().getLogisticCode());
                        logisticsName.setValue("发货快递："+logisticResponse.getData().getName());
                        logisticsPhone.setValue(logisticResponse.getData().getPhone());
                        mLogisticsMutableLiveData.setValue(logisticResponse.getData());
                    }else {
                        mLogisticsMutableLiveData.setValue(null);
                    }
                }
            }

            @Override
            public void getFailed(String msg) {
                mLogisticsMutableLiveData.setValue(null);
            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {
                mLogisticsMutableLiveData.setValue(null);
            }
        });
    }

}
