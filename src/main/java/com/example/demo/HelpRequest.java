package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.Poulain;


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
	private String date;
	
	@Column
	private String comment;
	
	@ManyToOne
	private Poulain poulain;

	public HelpRequest() {

	}

	public Integer getId() {
		return id;
	}
	
	public String getComment() {
		return comment;
	}
	
	public String getMatiere() {
		return matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
