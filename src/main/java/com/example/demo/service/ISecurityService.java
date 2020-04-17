package com.example.demo.service;

public interface ISecurityService {
    void autoLogin(String email, String password);
    String findLoggedInUsername();
}
