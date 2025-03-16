package com.example.twitterclone;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/")
public class TwitterCloneController {

    private static final int MAX_POST_LENGTH = 140;
    private static final Map<Long, User> users = new HashMap<>();
    private static final List<Post> stream = new ArrayList<>();
    private static final AtomicLong userIdGenerator = new AtomicLong(1);
    private static final AtomicLong postIdGenerator = new AtomicLong(1);

    @PostMapping("/users")
    public User createUser(@RequestParam String username) {
        long id = userIdGenerator.getAndIncrement();
        User user = new User(id, username);
        users.put(id, user);
        return user;
    }

    @GetMapping("/users")
    public Collection<User> getUsers() {
        return users.values();
    }

    @PostMapping("/posts")
    public Post createPost(@RequestParam Long userId, @RequestParam String content) {
        if (!users.containsKey(userId)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        if (content.length() > MAX_POST_LENGTH) {
            throw new IllegalArgumentException("El post excede los 140 caracteres");
        }
        Post post = new Post(postIdGenerator.getAndIncrement(), users.get(userId), content);
        stream.add(post);
        return post;
    }

    @GetMapping("/posts")
    public List<Post> getStream() {
        return stream;
    }

    @GetMapping("/posts/{userId}")
    public List<Post> getUserPosts(@PathVariable Long userId) {
        if (!users.containsKey(userId)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        List<Post> userPosts = new ArrayList<>();
        for (Post post : stream) {
            if (post.getUser().getId().equals(userId)) {
                userPosts.add(post);
            }
        }
        return userPosts;
    }
}
