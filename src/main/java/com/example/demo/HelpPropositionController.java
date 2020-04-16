package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.HelpPropositionRepository;


@Controller
public class HelpPropositionController {
	@Autowired 
	HelpPropositionRepository helpPropositionRepository;
	
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
	public String insertHelpProposition(@ModelAttribute HelpProposition helpProposition, Model model) {
		//TODO 
		Mentor mentor = new Mentor();
		mentor.setId(30);
		helpProposition.setMentor(mentor);

		helpPropositionRepository.save(helpProposition);
		return "formHelpProposition";
	}

	@GetMapping("/allPropositionByMentor")
	public String getallPropositionByMentor(Map<String, Object> model) {

		//TODO 
		Mentor mentor = new Mentor();
		mentor.setId(30);
		model.put("helpPropositions", helpPropositionRepository.findByMentor(mentor));
		return "ProfileHelpProposition";
	}

	@GetMapping("/editProposition/{id}")
	public String editProposition(@PathVariable("id") Integer helpId, Model model) {
		HelpProposition helpProposition = helpPropositionRepository.findById(helpId);
		
	   //TODO 
		Poulain poulain = new Poulain();
		poulain.setId(32);
		helpProposition.setPoulain(poulain);

		helpPropositionRepository.save(helpProposition);
		return "allHelpProposition";
	}
}
