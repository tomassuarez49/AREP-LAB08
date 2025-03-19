package com.example.twitterclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.twitterclone")
public class TwitterCloneApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwitterCloneApplication.class, args);
    }
}
