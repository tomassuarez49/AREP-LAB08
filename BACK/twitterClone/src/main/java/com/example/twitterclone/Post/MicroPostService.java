package com.example.twitterclone.Post;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import com.example.twitterclone.User.MicroUserService;
import com.example.twitterclone.User.ModelUser;

@Service
public class MicroPostService {

    private static final int MAX_POST_LENGTH = 140;
    private final Map<Long, ModelPost> posts = new HashMap<>();
    private final AtomicLong postIdGenerator = new AtomicLong(1);
    private final MicroUserService userService; // Inyección del servicio de usuarios

    public MicroPostService(MicroUserService userService) {
        this.userService = userService;
    }

    public ModelPost createPost(Long userId, String content) {
        ModelUser user = userService.getUserById(userId); // Obtener usuario desde MicroUserService
        if (user == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        if (content.length() > MAX_POST_LENGTH) {
            throw new IllegalArgumentException("El post excede los 140 caracteres");
        }

        ModelPost post = new ModelPost(postIdGenerator.getAndIncrement(), user, content);
        posts.put(post.getId(), post);

        return post;
    }

    public List<ModelPost> getUserPosts(Long userId) {
        List<ModelPost> userPosts = new ArrayList<>();
        for (ModelPost post : posts.values()) {
            if (post.getUser().getId().equals(userId)) {
                userPosts.add(post);
            }
        }
        return userPosts;
    }
}
