package com.example.healthmanage.ui.activity.notice.adapter;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.example.healthmanage.ui.activity.notice.response.NoticeResponse;

public class NoticeSection extends SectionEntity<NoticeResponse.DataBean.ListBean> {
    public NoticeSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public NoticeSection(NoticeResponse.DataBean.ListBean dataBean) {
        super(dataBean);
    }
}
