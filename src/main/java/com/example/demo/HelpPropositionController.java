package com.example.demo;

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

import com.example.demo.HelpPropositionRepository;
import com.example.demo.model.User;
import com.example.demo.service.UserServiceInterface;


@Controller
public class HelpPropositionController {
	@Autowired 
	HelpPropositionRepository helpPropositionRepository;
	@Autowired 
	private UserServiceInterface userService;
	
    @GetMapping("/allHelpProposition")
	public String getAll(Map<String, Object> model) {
		model.put("helpPropositions", helpPropositionRepository.findAll());
		return "allHelpProposition";
	}

	@GetMapping("/formHelpProposition")
	public String helpPropositionForm(Model model) {
		model.addAttribute("helpProposition", new HelpProposition());
		return "formHelpProposition";
	}
	
	@PostMapping("/insertHelpProposition")
	public String insertHelpProposition(@ModelAttribute HelpProposition helpProposition, Model model, HttpSession session, Principal principal) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(email);
		User user = userService.findByEmail(email);
		System.out.println(email);

		helpProposition.setMentor(user);

		helpPropositionRepository.save(helpProposition);
		return "formHelpProposition";
	}

	@GetMapping("/allPropositionByMentor")
	public String getallPropositionByMentor(Map<String, Object> model) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByEmail(email);
		
		model.put("helpPropositions", helpPropositionRepository.findByMentor(user));
		return "ProfileHelpProposition";
	}

	@GetMapping("/editProposition/{id}")
	public String editProposition(@PathVariable("id") Integer helpId, Model model) {
		HelpProposition helpProposition = helpPropositionRepository.findById(helpId);
		
	   	String email = SecurityContextHolder.getContext().getAuthentication().getName();
	  	User user = userService.findByEmail(email);
	   
		helpProposition.setPoulain(user);

		helpPropositionRepository.save(helpProposition);
		return "allHelpProposition";
	}
}
