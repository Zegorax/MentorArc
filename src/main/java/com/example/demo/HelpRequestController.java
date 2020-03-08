package com.example.demo;

import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		poulain.setId(0);
		helpRequest.setPoulain(poulain);

		helpRequestRepository.save(helpRequest);
		return "formHelpRequest";
	}

}
