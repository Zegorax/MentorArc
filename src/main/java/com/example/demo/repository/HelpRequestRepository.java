package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.example.demo.model.HelpRequest;
import com.example.demo.model.User;

public interface HelpRequestRepository extends JpaRepository <HelpRequest, Long>  {
    
    List<HelpRequest> findByPoulain(User poulain);
    
    HelpRequest findById(Integer id);

}
