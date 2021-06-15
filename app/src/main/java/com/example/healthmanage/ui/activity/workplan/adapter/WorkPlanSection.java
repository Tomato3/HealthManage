package com.example.healthmanage.ui.activity.workplan.adapter;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.example.healthmanage.ui.activity.workplan.response.WorkPlanListResponse;

public class WorkPlanSection extends SectionEntity<WorkPlanListResponse.DataBean> {
    public WorkPlanSection(WorkPlanListResponse.DataBean dataBean) {
        super(dataBean);
    }

    public WorkPlanSection(boolean isHeader, String header) {
        super(isHeader, header);
    }
}
