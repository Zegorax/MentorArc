package com.example.demo.service;

import com.example.demo.model.User;

public interface IUserService {
    void save(User user, String role);
    User findByEmail(String email);
}
