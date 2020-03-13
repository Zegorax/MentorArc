package com.example.demo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.example.demo.Poulain;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@Column
	private String comment;
	
	@ManyToOne
	private Poulain poulain;

	public HelpRequest() {

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

	public Poulain getPoulain(){
		return poulain;
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

	public void setPoulain(Poulain poulain) {
		this.poulain = poulain;
	}
}
