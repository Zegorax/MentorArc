package com.example.demo;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.HelpRequestRepository;


@Controller
public class HelpRequestController {
	@Autowired 
	HelpRequestRepository helpRequestRepository;
	
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
		//TODO : recup le poulain loggé
		Poulain poulain= new Poulain();
		poulain.setId(32);
		helpRequest.setPoulain(poulain);

		helpRequestRepository.save(helpRequest);
		return "formHelpRequest";
	}
	@GetMapping("/allRequestByPoulain")
	public String getallPropositionByPoulain(Map<String, Object> model) {

		//TODO : recup le mentor loggé
		Poulain poulain= new Poulain();
		poulain.setId(32);
		model.put("helpRequests", helpRequestRepository.findByPoulain(poulain));
		return "ProfileHelpRequest";
	}
	
	@PostMapping("/acceptRequest")
	public String acceptRequest(@ModelAttribute HelpRequest helpRequest, Model model) {
		//TODO 
		Mentor mentor = new Mentor();
		mentor.setId(30);
		helpRequest.setMentor(mentor);
		helpRequestRepository.save(helpRequest);
		return "allHelpProposition";
	}

	@GetMapping("/editRequest/{id}")
	public String editRequest(@PathVariable("id") Integer helpId, Model model) {
		HelpRequest helpRequest = helpRequestRepository.findById(helpId);
			
		//TODO 
		Mentor mentor = new Mentor();
		mentor.setId(30);
		helpRequest.setMentor(mentor);

		helpRequestRepository.save(helpRequest);
		return "allHelpRequest";
	}

}
