package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.example.demo.model.HelpProposition;
import com.example.demo.model.User;

public interface HelpPropositionRepository extends JpaRepository <HelpProposition, Long>  {

    List<HelpProposition> findByMentor(User mentor);
    List<HelpProposition> findByPoulain(User poulain);

    HelpProposition findById(Integer id);
    
}
