package com.example.twitterclone.User;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class MicroUserService {

    private static final Map<Long, ModelUser> users = new HashMap<>();
    private static final AtomicLong userIdGenerator = new AtomicLong(1);

    public ModelUser createUser(String username) {
        ModelUser user = new ModelUser(userIdGenerator.getAndIncrement(), username);
        users.put(user.getId(), user);
        return user;
    }

    public Collection<ModelUser> getUsers() {
        return users.values();
    }

}
