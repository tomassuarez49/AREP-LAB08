package com.example.twitterclone.Post;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import com.example.twitterclone.User.ModelUser;


@Service
public class MicroPostService{

    private static final int MAX_POST_LENGTH = 140;
    private final Map<Long, ModelPost> posts = new HashMap<>();
    private final AtomicLong postIdGenerator = new AtomicLong(1);
    private final Map<Long, ModelUser> users = new HashMap<>(); // Simulación de BD

    public ModelPost createPost(Long userId, String content) {
        if (!users.containsKey(userId)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        if (content.length() > MAX_POST_LENGTH) {
            throw new IllegalArgumentException("El post excede los 140 caracteres");
        }

        ModelUser user = users.get(userId); // Obtener el objeto User
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
