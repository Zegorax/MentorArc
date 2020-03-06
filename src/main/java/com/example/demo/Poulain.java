package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Set;

import com.example.demo.HelpRequest;

@Entity
@Table(name = "poulain")
public class Poulain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;

	@Column
	private String firstName;

	@Column
	private String lastName;
	
	@OneToMany(mappedBy="poulain")
	private Set<HelpRequest> helpRequests;

	public Poulain() {

	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public Set<HelpRequest> getHelpRequests() {
		return helpRequests;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setHelpRequests(Set<HelpRequest> helpRequests) {
		this.helpRequests = helpRequests;
	}
}
