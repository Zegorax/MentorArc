package com.example.demo.controller;

import java.security.Principal;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.repository.HelpPropositionRepository;
import com.example.demo.repository.HelpRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired 
    HelpPropositionRepository helpPropositionRepository;
    
    @Autowired 
    HelpRequestRepository helpRequestRepository;
    
    @Autowired 
    UserRepository userRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, @RequestParam(defaultValue = "false") boolean poulain, @RequestParam(defaultValue = "false") boolean mentor, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();

        //userSignupValidator.validate(o, errors);
        
        if(bindingResult.hasErrors()) { 
            modelAndView.addObject("registerMessage", "Registration failed: correct the fields !");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else { // Saving the users
            if (poulain) {
                userService.save(user, "poulain");
            }
            if(mentor){
                userService.save(user, "mentor");
            }
            //securityService.autoLogin(user.getEmail(), user.getPassword());
            return new ModelAndView("redirect:" + "/");
        }

        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        
        return modelAndView;
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public ModelAndView login() {
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }

    @RequestMapping(value = { "connected" }, method = RequestMethod.GET)
    public ModelAndView connected(HttpSession session, Principal principal) {

        if(principal != null){
            User user = userService.findByEmail(principal.getName());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("id", String.valueOf(user.getId()));
            session.setAttribute("isPoulain", String.valueOf(user.isPoulain()));
            session.setAttribute("isMentor", String.valueOf(user.isMentor()));
            session.setAttribute("isAdmin", String.valueOf(user.isAdmin()));
        }
        
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = {"/index", "", "/"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index"); // resources/template/index.html
        
        return modelAndView;
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("helpRequests",  helpRequestRepository.findAll());
        modelAndView.addObject("helpPropositions", helpPropositionRepository.findAll());
        modelAndView.addObject("users", userRepository.findAll());
        modelAndView.setViewName("admin"); // resources/template/admin.html
        return modelAndView;
    }
}
