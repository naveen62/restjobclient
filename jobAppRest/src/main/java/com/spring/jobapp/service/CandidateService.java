package com.spring.jobapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jobapp.model.Candidate;
import com.spring.jobapp.model.Job;
import com.spring.jobapp.repo.ICandidateRepo;
import com.spring.jobapp.repo.IJobRepo;

@Service
public class CandidateService implements ICandidateService {
	
	@Autowired
	private ICandidateRepo candidateRepo;
	@Autowired
	private JobService jobService;
	
	@Override
	public Candidate saveCandidate(long jobId, Candidate candidate) {
		// TODO Auto-generated method stub
		Job job = jobService.getJob(jobId);
		candidate.setJob(job);
		Candidate savedCandidate =  candidateRepo.save(candidate);
		return savedCandidate;
	}

}
