package com.example.twitterclone.Stream;

import org.springframework.web.bind.annotation.*;
import com.example.twitterclone.Post.ModelPost;

import java.util.List;

@RestController
@RequestMapping("/stream")
public class StreamController {

    private final MicroStreamService streamService;

    public StreamController(MicroStreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping("/posts")
    public List<ModelPost> getStream() {
        return streamService.getStream();
    }
}
