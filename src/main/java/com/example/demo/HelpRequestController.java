package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.HelpRequestRepository;
import com.example.demo.model.User;

import com.example.demo.service.UserServiceInterface;



@Controller
public class HelpRequestController {
    @Autowired 
    HelpRequestRepository helpRequestRepository;
	private UserServiceInterface userService;

	
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
	public String insertHelpRequest(@ModelAttribute HelpRequest helpRequest, Model model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
		helpRequest.setPoulain(user);

		helpRequestRepository.save(helpRequest);
		return "formHelpRequest";
	}
	@GetMapping("/allRequestByPoulain")
	public String getallPropositionByPoulain(Map<String, Object> model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
		model.put("helpRequests", helpRequestRepository.findByPoulain(user));
		return "ProfileHelpRequest";
	}
	
	@PostMapping("/acceptRequest")
	public String acceptRequest(@ModelAttribute HelpRequest helpRequest, Model model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
		helpRequest.setMentor(user);
		helpRequestRepository.save(helpRequest);
		return "allHelpProposition";
	}

	@GetMapping("/editRequest/{id}")
	public String editRequest(@PathVariable("id") Integer helpId, Model model) {
		HelpRequest helpRequest = helpRequestRepository.findById(helpId);
			
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);
		helpRequest.setMentor(user);

		helpRequestRepository.save(helpRequest);
		return "allHelpRequest";
	}

}
