package com.example.demo.controller;

import java.security.Principal;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.service.UserServiceInterface;

@Controller
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, @RequestParam("roleWanted") String roleWanted, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();

        //userSignupValidator.validate(o, errors);
        
        if(bindingResult.hasErrors()) { 
            modelAndView.addObject("registerMessage", "Registration failed: correct the fields !");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else { // Saving the users
            userService.save(user, roleWanted);
            //securityService.autoLogin(user.getEmail(), user.getPassword());
            return new ModelAndView("redirect:" + "/");
        }

        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        
        return modelAndView;
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public ModelAndView login(HttpSession session, Principal principal) {
        
        if(principal != null){
            User user = userService.findByEmail(principal.getName());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("id", user.getId());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }

    @RequestMapping(value = {"/index", "", "/"}, method = RequestMethod.GET)
    public ModelAndView index(HttpSession session, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index"); // resources/template/index.html
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        if (user != null)
            session.setAttribute("username", user.getUsername());
        return modelAndView;
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin"); // resources/template/admin.html
        return modelAndView;
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public ModelAndView memberHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("member"); // resources/template/member.html
        return modelAndView;
    }

}
