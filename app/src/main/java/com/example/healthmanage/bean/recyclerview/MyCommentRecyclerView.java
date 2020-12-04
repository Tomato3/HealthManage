package com.example.healthmanage.bean.recyclerview;

public class MyCommentRecyclerView {

    public String avatar, nickName, commentContent, commentTime;

    public MyCommentRecyclerView(String avatar, String nickName, String commentContent,
                                 String commentTime) {
        this.avatar = avatar;
        this.nickName = nickName;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
    }
}
