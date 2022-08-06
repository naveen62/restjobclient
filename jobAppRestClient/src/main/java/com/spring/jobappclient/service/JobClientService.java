package com.spring.jobappclient.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.jobappclient.model.Candidate;
import com.spring.jobappclient.model.Job;
import com.spring.jobappclient.model.JobList;

@Service
public class JobClientService {
	
	private String JobApiUrl = "http://localhost:8080/jobs";
	
	@Autowired
	RestTemplate rt;
	
	public Job createJob(Job job) {
		Job createdJob = rt.postForObject(JobApiUrl, job, Job.class);
		return createdJob;
	}
	public Candidate createCandidate(long jobId, Candidate candidate) {
		Candidate createdCandidate = rt.postForObject("http://localhost:8080/candidate/jobs/"+jobId, candidate, Candidate.class);
		return createdCandidate;
	}
	public List<Job> getAllJobs() {
		Job[] arrayJobs = rt.getForObject(JobApiUrl, Job[].class);
		List<Job> jobs = Arrays.asList(arrayJobs);
		return jobs;
	}
	public Job getJob(long jobId) {
		Job job = rt.getForObject(JobApiUrl+"/"+jobId, Job.class);
		return job;
	}
	public void deleteJob(long jobId) {
		rt.delete(JobApiUrl+"/"+jobId);
	}
	public Job updateJob(long jobId,Job job) {
		HttpEntity<Job> entity = new HttpEntity<Job>(job);
		Job updatedJob = rt.exchange(JobApiUrl+"/"+jobId, HttpMethod.PUT, entity, Job.class).getBody();
		return updatedJob;
	}
	public List<Job> searchJob(String searchQuery, String searchType) {
		String searchApi = "http://localhost:8080/search/jobs/" +searchQuery+ "/" + searchType;
		Job[] arrayJobs = rt.getForObject(searchApi, Job[].class);
		List<Job> jobs = Arrays.asList(arrayJobs);
		return jobs;
	}
	
}
