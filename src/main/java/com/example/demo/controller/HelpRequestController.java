package com.example.demo.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.HelpRequest;
import com.example.demo.model.User;
import com.example.demo.repository.HelpRequestRepository;
import com.example.demo.service.IUserService;

@Controller
public class HelpRequestController {
    @Autowired 
    HelpRequestRepository helpRequestRepository;
    
    @Autowired 
    private IUserService userService;

    
    @GetMapping("/allHelpRequest")
    public String getAll(Map<String, Object> model) {
        model.put("helpRequests", helpRequestRepository.findAll());
        return "allHelpRequest";
    }
    
    @GetMapping("/formHelpRequest")
    public String helpRequestForm(Model model) {
        model.addAttribute("helpRequest", new HelpRequest());
        return "formHelpRequest";
    }
    
    @PostMapping("/insertHelpRequest")
    public String insertHelpRequest(@ModelAttribute HelpRequest helpRequest, Model model, HttpSession session, Principal principal) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        helpRequest.setPoulain(user);

        helpRequestRepository.save(helpRequest);
        return "formHelpRequest";
    }
    @GetMapping("/allRequestByPoulain")
    public String getallPropositionByPoulain(Map<String, Object> model, HttpSession session, Principal principal) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        model.put("helpRequests", helpRequestRepository.findByPoulain(user));
        return "ProfileHelpRequest";
    }
    @GetMapping("/allRequestByMentor")
    public String getallPropositionByMentor(Map<String, Object> model, HttpSession session, Principal principal) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        model.put("helpRequests", helpRequestRepository.findByMentor(user));
        return "ProfileHelpRequest";
    }
    
    @PostMapping("/acceptRequest")
    public String acceptRequest(@ModelAttribute HelpRequest helpRequest, Model model, HttpSession session, Principal principal) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        helpRequest.setMentor(user);
        helpRequestRepository.save(helpRequest);
        return "allHelpProposition";
    }

    @GetMapping("/editRequest/{id}")
    public String editRequest(@PathVariable("id") Integer helpId, Model model, HttpSession session, Principal principal) {
        HelpRequest helpRequest = helpRequestRepository.findById(helpId);
            
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        helpRequest.setMentor(user);

        helpRequestRepository.save(helpRequest);
        return "allHelpRequest";
    }
}
