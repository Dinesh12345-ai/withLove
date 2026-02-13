package com.love.withlove.services;

import com.love.withlove.dtos.UserDTO;
import com.love.withlove.models.User;
import com.love.withlove.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User getUserById(Long id) {
     return userRepo.findById(id).orElse(null);
    }

    public void saveUser(User user) {
    if(userRepo.findByEmail(user.getEmail()) != null) {
        throw new RuntimeException("User with email already exists");
    }
        userRepo.save(user);
    }

    public User loginUser(UserDTO user) {
    User existingUser = userRepo.findByEmail(user.getEmail());
    if(existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
        throw new RuntimeException("Invalid email or password");
    }
    return existingUser;
    }
}
