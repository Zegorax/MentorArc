package com.example.demo.validator;

import com.example.demo.model.HelpRequest;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class HelpRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return HelpRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HelpRequest helpRequest = (HelpRequest) target;

        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branch", "branch.empty", "You must enter a branch!");
        if(helpRequest.getBranch().length() < 2 || helpRequest.getBranch().length() > 32){
            errors.rejectValue("branch", "branch.size", "The size must be between 2 and 32");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeBegin", "timeBegin.empty", "You must enter a time begin!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeEnd", "timeEnd.empty", "You must enter a time end!");
    }

}