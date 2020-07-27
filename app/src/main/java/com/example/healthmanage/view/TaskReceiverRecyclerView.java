package com.example.healthmanage.view;

public class TaskReceiverRecyclerView {

    public String avatarUrl;
    public String name;
    public String type;
    public String professionalTitle;
    public String sittingPlace;
    public long receiverId;

    public TaskReceiverRecyclerView(String avatarUrl, String name, String type,
                                    String professionalTitle, String sittingPlace, long receiverId) {
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.type = type;
        this.professionalTitle = professionalTitle;
        this.sittingPlace = sittingPlace;
        this.receiverId = receiverId;
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getSittingPlace() {
        return sittingPlace;
    }

    public void setSittingPlace(String sittingPlace) {
        this.sittingPlace = sittingPlace;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }
}
