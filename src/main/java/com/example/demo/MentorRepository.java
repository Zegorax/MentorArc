package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Mentor;

public interface MentorRepository extends JpaRepository <Mentor, Long>  {

}

