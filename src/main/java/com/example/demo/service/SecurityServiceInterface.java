package com.example.demo.service;

public interface SecurityServiceInterface {
    
    void autoLogin(String email, String password);
    
    String findLoggedInUsername();
}
