package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.model.HelpProposition;
import com.example.demo.model.HelpRequest;
import com.example.demo.repository.HelpPropositionRepository;
import com.example.demo.repository.HelpRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    HelpRequestRepository helpRequestRepository;

    @Autowired 
    HelpPropositionRepository helpPropositionRepository;

    @GetMapping("/search")
    public String search(Map<String, Object> model, @RequestParam(name = "branch") String branch, @RequestParam(name = "datebegin", required = false) String datebegin, @RequestParam(name = "dateend", required = false) String dateend, @RequestParam(name = "timebegin", required = false) String timebegin, @RequestParam(name = "timeend", required = false) String timeend, @RequestParam(name = "type") int type) throws ParseException {

        List<HelpRequest> allListHelpRequest = new ArrayList<>();
        List<HelpProposition> allListHelpProposition = new ArrayList<>();

        if (type == 1){ // HelpRequest
            allListHelpRequest = helpRequestRepository.findByBranchLike("%"+branch+"%");
        }
        else if (type == 2){ // HelpProposition 
            allListHelpProposition = helpPropositionRepository.findByBranchLike("%"+branch+"%");
        }
        else { // both
            allListHelpRequest = helpRequestRepository.findByBranchLike("%"+branch+"%");
            allListHelpProposition = helpPropositionRepository.findByBranchLike("%"+branch+"%");
        }

        // Date begin
        if (datebegin != null && datebegin.length() > 0){
            Date dateBegin = new SimpleDateFormat("yyyy-MM-dd").parse(datebegin);
            if (timebegin != null && timebegin.length() > 0){ // time begin
                dateBegin.setHours(Integer.parseInt(timebegin.substring(0, 2)));
                dateBegin.setMinutes(Integer.parseInt(timebegin.substring(3, 5)));
            }
            allListHelpRequest = allListHelpRequest.stream().filter(x -> x.getDateBegin().after(dateBegin)).collect(Collectors.toList());
            allListHelpProposition = allListHelpProposition.stream().filter(x -> x.getDateBegin().after(dateBegin)).collect(Collectors.toList());
            model.put("dateBegin", dateBegin);
        }

        // Date end
        if (dateend != null && dateend.length() > 0){
            Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(dateend);
            if (timeend != null && timeend.length() > 0){ // time end
                dateEnd.setHours(Integer.parseInt(timeend.substring(0, 2)));
                dateEnd.setMinutes(Integer.parseInt(timeend.substring(3, 5)));
            }
            allListHelpRequest = allListHelpRequest.stream().filter(x -> x.getDateBegin().before(dateEnd)).collect(Collectors.toList());
            allListHelpProposition = allListHelpProposition.stream().filter(x -> x.getDateEnd().after(dateEnd)).collect(Collectors.toList());
            model.put("dateEnd", dateEnd);
        }

        model.put("helpRequests", allListHelpRequest);
        model.put("helpPropositions", allListHelpProposition);
        model.put("helpRequestsSize", allListHelpRequest.size());
        model.put("helpPropositionsSize", allListHelpProposition.size());
        model.put("type", type);
        model.put("branch", branch);

        return "search";
    }
    
}