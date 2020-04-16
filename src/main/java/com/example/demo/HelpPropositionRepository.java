package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.example.demo.HelpProposition;

public interface HelpPropositionRepository extends JpaRepository <HelpProposition, Long>  {
    List<HelpProposition> findByMentor(Mentor mentor);
    HelpProposition findById(Integer id);
}
