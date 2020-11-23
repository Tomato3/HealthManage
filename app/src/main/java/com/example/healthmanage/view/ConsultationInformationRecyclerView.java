package com.example.healthmanage.view;

import com.example.healthmanage.R;

public class ConsultationInformationRecyclerView {

    public int statusImg, state;
    public String consultant, consultingContent, consultingTime;

    public ConsultationInformationRecyclerView(String consultant, String consultingContent,
                                               String consultingTime, int state) {
        this.consultant = consultant;
        this.consultingContent = consultingContent;
        this.consultingTime = consultingTime;
        this.state = state;
        switch (state) {
            case 0:
                statusImg = R.drawable.recycler_view_my_task_red;
                break;
            case 1:
                statusImg = R.drawable.recycler_view_my_task_blue;
                break;
            case 2:
                statusImg = R.drawable.recycler_view_my_task_green;
                break;
        }
    }


}
