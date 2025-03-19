package com.example.twitterclone.User;

public class ModelUser {
    private Long id;
    private String username;

    public ModelUser(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
