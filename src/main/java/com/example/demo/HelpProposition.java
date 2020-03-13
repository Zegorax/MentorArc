package com.example.demo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.example.demo.Mentor;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "helpproposition")
public class HelpProposition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;

	@Column
	private String matiere;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@Column
	private String comment;
	
	@ManyToOne
	private Mentor mentor;

	public HelpProposition() {

	}

	public Integer getId() {
		return id;
	}

	public String getMatiere() {
		return matiere;
	}

	public Date getDate() {
		return date;
	}
	
	public String getComment() {
		return comment;
	}

	public Mentor getMentor(){
		return mentor;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}
}
