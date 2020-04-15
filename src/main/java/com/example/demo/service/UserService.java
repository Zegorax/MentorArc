package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    public void save(User user, String role);
    
    public boolean isUserAlreadyPresent(User user);

    public User findByUsername(String username);
}
