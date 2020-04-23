package com.example.demo.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.HelpRequest;
import com.example.demo.model.User;
import com.example.demo.repository.HelpRequestRepository;
import com.example.demo.service.IUserService;
import com.example.demo.validator.HelpRequestValidator;

@Controller
public class HelpRequestController {
    @Autowired
    HelpRequestRepository helpRequestRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private HelpRequestValidator helpRequestValidator;

    /**
     * Source : https://attacomsian.com/blog/spring-boot-thymeleaf-pagination 
     */

    @GetMapping("/allHelpRequest")
    public String getAll(HttpServletRequest request, Model model) {
        int page = 0; 
        int size = 3;
   
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        
        model.addAttribute("helpRequests", helpRequestRepository.findAll(PageRequest.of(page, size)));
        return "allHelpRequest";
    }

    @GetMapping("/formHelpRequest")
    public String helpRequestForm(Model model) {
        model.addAttribute("helpRequest", new HelpRequest());
        return "formHelpRequest";
    }

    @PostMapping("/insertHelpRequest")
    public ModelAndView insertHelpRequest(@ModelAttribute HelpRequest helpRequest, BindingResult bindingResult,
            ModelMap modelMap, HttpSession session, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        helpRequestValidator.validate(helpRequest, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("registerMessage", "Registration failed: correct the fields !");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByEmail(email);
            helpRequest.setPoulain(user);

            helpRequestRepository.save(helpRequest);

            return new ModelAndView("redirect:" + "/formHelpRequest");
        }

        modelAndView.addObject("helpRequest", new HelpRequest());
        modelAndView.setViewName("formHelpRequest");

        return modelAndView;
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
    public String acceptRequest(@ModelAttribute HelpRequest helpRequest, Model model, HttpSession session,
            Principal principal) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        helpRequest.setMentor(user);
        helpRequestRepository.save(helpRequest);
        return "allHelpProposition";
    }

    @GetMapping("/editRequest/{id}")
    public String editRequest(@PathVariable("id") Integer helpId, Model model, HttpSession session,
            Principal principal) {
        HelpRequest helpRequest = helpRequestRepository.findById(helpId);

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        helpRequest.setMentor(user);

        helpRequestRepository.save(helpRequest);
        return "allHelpRequest";
    }

}
