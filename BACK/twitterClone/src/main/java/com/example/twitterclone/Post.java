package com.example.twitterclone;

public class Post {
    private Long id;
    private User user;
    private String content;

    public Post(Long id, User user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    //cambio
}
