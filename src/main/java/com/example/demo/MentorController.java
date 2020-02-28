package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.*;


@Controller
public class MentorController {
    
    @Autowired 
    MentorRepository mentorRepository;
    
    @Autowired 
    
    @GetMapping("/allMentor")
    public  String getAll(Map<String, Object> model) {
        
        model.put("mentors", mentorRepository.findAll());
        
        return "allMentor";
    }
            
    @GetMapping("/formMentor")
    public String personForm(Model model) {
        model.addAttribute("mentor", new Mentor());
        
        return "formMentor";
    }
    
    @PostMapping("/insertMentor")
    public String insertPerson(@ModelAttribute Mentor mentor, Model model) {
        mentorRepository.save(mentor);
        return "formMentor";
        
    }
}



