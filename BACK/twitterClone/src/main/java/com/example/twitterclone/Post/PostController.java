package com.example.twitterclone.Post;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final MicroPostService postService;

    public PostController(MicroPostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ModelPost createPost(@RequestParam Long userId, @RequestParam String content) {
        return postService.createPost(userId, content);
    }

    @GetMapping("/{userId}")
    public List<ModelPost> getUserPosts(@PathVariable Long userId) {
        return postService.getUserPosts(userId);
    }

}
