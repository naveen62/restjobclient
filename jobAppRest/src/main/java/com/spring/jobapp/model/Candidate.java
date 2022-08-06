package com.spring.jobapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long candidateId;
	private String name;
	private String email;
	private long contactNo;
	private String education;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="jod_id")
	private Job job;
	
	public Candidate() {
		super();
	}
	public Candidate(long candidateId, String name, String email, long contactNo, String education,Job job) {
		super();
		this.candidateId = candidateId;
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.education = education;
		this.job = job;
	}
	public long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	
}
