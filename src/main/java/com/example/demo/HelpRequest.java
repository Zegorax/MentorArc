package com.example.demo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.example.demo.model.User;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "helprequest")
public class HelpRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;

	@Column
	private String matiere;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date dateBegin;

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date dateEnd;
	
	@Column
	private String comment;
	
	@ManyToOne
	private User poulain;

	@ManyToOne
	private User mentor;


	public HelpRequest() {

	}

	public Integer getId() {
		return id;
	}

	public String getMatiere() {
		return matiere;
	}

	public Date getDateBegin() {
		return dateBegin;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	
	public String getComment() {
		return comment;
	}

	public User getPoulain(){
		return poulain;
	}

	public User getMentor(){
		return mentor;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}

	public void setDateBegin(Date date) {
		this.dateBegin = date;
	}
	
	public void setDateEnd(Date date) {
		this.dateEnd = date;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setPoulain(User poulain) {
		this.poulain = poulain;
	}

	public void setMentor(User mentor) {
		this.mentor = mentor;
	}
}
