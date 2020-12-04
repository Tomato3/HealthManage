package com.example.healthmanage.ui.activity.memberinfo;

import androidx.lifecycle.MutableLiveData;

import com.example.healthmanage.base.BaseApplication;
import com.example.healthmanage.base.BaseViewModel;
import com.example.healthmanage.bean.network.response.MemberInfoResponse;
import com.example.healthmanage.bean.UsersInterface;
import com.example.healthmanage.bean.UsersRemoteSource;
import com.example.healthmanage.data.network.exception.ExceptionHandle;
import com.example.healthmanage.utils.ToolUtil;
import com.example.healthmanage.bean.recyclerview.DataItem;

import java.util.ArrayList;
import java.util.List;

public class MemberInfoViewModel extends BaseViewModel {

    public MutableLiveData<String> memberName = new MutableLiveData<>("");
    public MutableLiveData<String> memberPhone = new MutableLiveData<>("");
    public MutableLiveData<String> memberAvatar = new MutableLiveData<>("");
    String[] name = {"姓名：", "性别：", "年龄：",
            "联系电话：", "出生年月：", "所在城市：",
            "上次登录时间", "其他：", "预留："};
    UsersRemoteSource usersRemoteSource = new UsersRemoteSource();

    public MutableLiveData<List<DataItem>> dataItemMutableLiveData =
            new MutableLiveData<>();

    private List<DataItem> dataItemList;
    private List<String> data;

    public void getMemberInfo(long memberId) {
        usersRemoteSource.getMemberInfo(memberId, BaseApplication.getToken(), new UsersInterface.GetMemberInfoCallback() {
            @Override
            public void getSucceed(MemberInfoResponse memberInfoResponse) {
                data = new ArrayList<>();
                dataItemList = new ArrayList<>();
                data.add(ToolUtil.isNull(memberInfoResponse.getData().getNickName()));
                data.add(ToolUtil.isNull(memberInfoResponse.getData().getSex() == 0 ? "男" : "女"));
                data.add(ToolUtil.isNull("20"));
                data.add(ToolUtil.isNull(memberInfoResponse.getData().getPhone()));
                data.add(ToolUtil.isNull(memberInfoResponse.getData().getBirthday()));
                data.add(ToolUtil.isNull(memberInfoResponse.getData().getCityName()));
                data.add(ToolUtil.isNull(memberInfoResponse.getData().getLastLoginTime()));
                data.add(ToolUtil.isNull("暂无其他"));
                data.add(ToolUtil.isNull("暂无预留"));
                dataItemList.add(new DataItem(name, data));

                dataItemMutableLiveData.setValue(dataItemList);


            }

            @Override
            public void getFailed(String msg) {

            }

            @Override
            public void error(ExceptionHandle.ResponseException e) {

            }
        });
    }

}
