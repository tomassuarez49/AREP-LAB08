package com.example.twitterclone.Stream;

import org.springframework.stereotype.Service;
import com.example.twitterclone.Post.ModelPost;
import java.util.*;


@Service
public class MicroStreamService {

    private static final List<ModelPost> stream = new ArrayList<>();

    public List<ModelPost> getStream() {
        return stream;
    }

}
