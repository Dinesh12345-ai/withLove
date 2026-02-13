package com.love.withlove.controllers;

import com.love.withlove.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/user")
    public String getUser() {
        return null;
    }


}
