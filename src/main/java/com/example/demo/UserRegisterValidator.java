package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRegisterValidator implements Validator{

    @Autowired
    private IUserService userService;

    private static final String REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    @Override
    public boolean supports(Class<?> arg0) {
        return User.class.equals(arg0);
    }

    @Override
    public void validate(Object oUser, Errors errors) {
        User user = (User) oUser;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty", "You must enter a username!");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 16) {
            errors.rejectValue("username", "username.size", "The username length must be between 4 and 16!");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password can't be empty!");
        if (user.getPassword().length() < 5 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "password.length", "The password length must be between 5 and 32!");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "You must enter an email!");
        if (!(user.getEmail()).matches(REGEX)) {
            errors.rejectValue("email", "email.structure", "This is not a valid email!");
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "email.dupplicate", "This email has already been used!");
        }
    
    }

}