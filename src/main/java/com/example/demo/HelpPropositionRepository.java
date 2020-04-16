package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.example.demo.HelpProposition;
import com.example.demo.model.User;

public interface HelpPropositionRepository extends JpaRepository <HelpProposition, Long>  {
    List<HelpProposition> findByMentor(User mentor);
    HelpProposition findById(Integer id);
}
