package com.spring.jobapp.service;

import com.spring.jobapp.model.Candidate;
import com.spring.jobapp.model.Job;

public interface ICandidateService {
	public Candidate saveCandidate(long jobId, Candidate candidate);
}	
