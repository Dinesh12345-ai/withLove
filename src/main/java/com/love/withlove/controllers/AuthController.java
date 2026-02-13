package com.love.withlove.controllers;

import com.love.withlove.dtos.UserDTO;
import com.love.withlove.models.User;
import com.love.withlove.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @GetMapping("/api/user/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        else
            return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("api/user/login")
    public ResponseEntity<Object> loginUser(@RequestBody UserDTO user) {
        User existingUser = userService.loginUser(user);
        if(existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        return ResponseEntity.status(HttpStatus.OK).body(existingUser);
    }


}
