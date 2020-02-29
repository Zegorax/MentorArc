package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.HelpRequestRepository;


@Controller
public class HelpRequestController {
	@Autowired 
	HelpRequestRepository helpRequestRepository;
	
    @Autowired 
    
    @GetMapping("/allHelpRequest")
	public String getAll(Map<String, Object> model) {
		model.put("helpRequests", helpRequestRepository.findAll());
		return "allHelpRequest";
	}

}
