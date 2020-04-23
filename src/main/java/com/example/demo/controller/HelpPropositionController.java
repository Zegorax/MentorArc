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

import com.example.demo.model.HelpProposition;
import com.example.demo.model.User;
import com.example.demo.repository.HelpPropositionRepository;
import com.example.demo.service.IUserService;
import com.example.demo.validator.HelpPropositionValidator;

@Controller
public class HelpPropositionController {

    @Autowired 
    HelpPropositionRepository helpPropositionRepository;

    @Autowired 
    private IUserService userService;

    @Autowired
    private HelpPropositionValidator helpPropositionValidator;
    
    /**
     * Source : https://attacomsian.com/blog/spring-boot-thymeleaf-pagination 
     */
    @GetMapping("/allHelpProposition")
    public String getAll(HttpServletRequest request, Model model) {
     
        int page = 0; 
        int size = 3; 
        
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        
        model.addAttribute("helpPropositions", helpPropositionRepository.findAll(PageRequest.of(page, size)));
        return "allHelpProposition";  
    }

    @GetMapping("/formHelpProposition")
    public String helpPropositionForm(Model model) {
        model.addAttribute("helpProposition", new HelpProposition());
        return "formHelpProposition";
    }
    
    @PostMapping("/insertHelpProposition")
    public ModelAndView insertHelpProposition(@ModelAttribute HelpProposition helpProposition, BindingResult bindingResult, ModelMap modelMap, HttpSession session, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        helpPropositionValidator.validate(helpProposition, bindingResult);

        if(bindingResult.hasErrors()) { 
            modelAndView.addObject("registerMessage", "Registration failed: correct the fields !");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else { 
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByEmail(email);

            helpProposition.setMentor(user);

            helpPropositionRepository.save(helpProposition);

            return new ModelAndView("redirect:" + "/formHelpProposition");
        }

        modelAndView.addObject("helpProposition", new HelpProposition());
        modelAndView.setViewName("formHelpProposition");
        
        return modelAndView;
    }

    @GetMapping("/allPropositionByMentor")
    public String getallPropositionByMentor(Map<String, Object> model, HttpSession session, Principal principal) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        
        model.put("helpPropositions", helpPropositionRepository.findByMentor(user));
        return "ProfileHelpProposition";
    }
    
    @GetMapping("/allPropositionByPoulain")
    public String getallPropositionByPoulain(Map<String, Object> model, HttpSession session, Principal principal) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
        
        model.put("helpPropositions", helpPropositionRepository.findByPoulain(user));
        return "ProfileHelpProposition";
    }

    @GetMapping("/editProposition/{id}")
    public String editProposition(@PathVariable("id") Integer helpId, Model model, HttpSession session, Principal principal) {
        HelpProposition helpProposition = helpPropositionRepository.findById(helpId);
        
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
    
        helpProposition.setPoulain(user);

        helpPropositionRepository.save(helpProposition);
        
        return "redirect:/allHelpProposition";
    }
}
