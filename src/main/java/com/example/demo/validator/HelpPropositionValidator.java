package com.example.demo.validator;

import com.example.demo.model.HelpProposition;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class HelpPropositionValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return HelpProposition.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HelpProposition helpProposition = (HelpProposition) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branch", "branch.empty", "You must enter a branch!");
        if(helpProposition.getBranch().length() < 2 || helpProposition.getBranch().length() > 32){
            errors.rejectValue("branch", "branch.size", "The size must be between 2 and 32");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeBegin", "timeBegin.empty", "You must enter a time begin!");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeEnd", "timeEnd.empty", "You must enter a time end!");
        
        if(helpProposition.getDateBegin().after(helpProposition.getDateEnd())){
            System.out.println(helpProposition.getDateBegin());
            System.out.println(helpProposition.getDateEnd());
            errors.rejectValue("dateBegin", "dateBegin.size", "The begin date must be set before the end date!");
        }else{
            System.out.println("nop");
        }
    }

}