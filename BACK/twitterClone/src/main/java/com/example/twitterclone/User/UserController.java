package com.example.twitterclone.User;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    private final MicroUserService userService;

    public UserController(MicroUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ModelUser createUser(@RequestParam String username) {
        return userService.createUser(username);
    }

    @GetMapping
    public Collection<ModelUser> getUsers() {
        return userService.getUsers();
    }
}
