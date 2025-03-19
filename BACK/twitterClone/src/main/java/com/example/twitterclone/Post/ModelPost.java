package com.example.twitterclone.Post;

import com.example.twitterclone.User.ModelUser;

public class ModelPost {
    private Long id;
    private ModelUser user;
    private String content;

    public ModelPost(Long id, ModelUser user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public ModelUser getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }
}
