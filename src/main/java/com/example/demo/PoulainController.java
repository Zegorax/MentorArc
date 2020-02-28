package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.*;

@Controller
public class PoulainController {

	@Autowired 
	PoulainRepository poulainRepository;
	
	@Autowired 
	
	@GetMapping("/allPoulain")
	public  String getAll(Map<String, Object> model) {
		
		model.put("poulains", poulainRepository.findAll());
		
		return "allPoulain";
	}
			
	@GetMapping("/formPoulain")
	public String personForm(Model model) {
		model.addAttribute("poulain", new Poulain());
		
		return "formPoulain";
	}
	
	@PostMapping("/insertPoulain")
	public String insertPerson(@ModelAttribute Poulain poulain, Model model) {
			
		poulainRepository.save(poulain);
		
		return "formPoulain";
		
	}
}