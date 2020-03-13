package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.example.demo.HelpRequest;

public interface HelpRequestRepository extends JpaRepository <HelpRequest, Long>  {
    List<HelpRequest> findByPoulain(Poulain poulain);

}
