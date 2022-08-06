package com.spring.jobapp.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jobapp.model.Candidate;
import com.spring.jobapp.model.Job;
import com.spring.jobapp.service.CandidateService;
import com.spring.jobapp.service.IJobService;
import com.spring.jobapp.service.JobService;

@RestController
public class JobRestController {
	
	@Autowired
	JobService service;
	@Autowired
	CandidateService candidateService;
	
	@PostMapping("/jobs")
	public Job createJob(@RequestBody Job job) {
		return service.createJob(job);
	}
	@GetMapping("/jobs")
	public List<Job> getAllJobs() {
		return service.getAll();
	}
	@GetMapping("/jobs/{job-id}")
	public Job getJob(@PathVariable("job-id") long jobId) {
		Job job = service.getJob(jobId);
		return job;
	}
	@PutMapping("/jobs/{job-id}")
	public Job updateJob(@PathVariable("job-id") long jobId, @RequestBody Job job) {
		return service.updateJob(job, jobId);
	}
	@DeleteMapping("/jobs/{job-id}")
	public boolean deleteJob(@PathVariable("job-id") long jodId) {
		service.deleteJob(jodId);
		return true;
	}
	@PostMapping("/candidate/jobs/{job-id}")
	public Candidate addCandidate(@PathVariable("job-id") long jobId, @RequestBody Candidate candidate) {
		return candidateService.saveCandidate(jobId, candidate);
	}
	@GetMapping("/search/jobs/{search-query}/{search-type}")
	public List<Job> searchJob(@PathVariable("search-query") String searchQuery,
			@PathVariable("search-type") String searchType) {
		System.out.println(searchType.equals("title"));
		if(searchType.equals("title")) {
			List<Job> jobs = service.searchByTitle(searchQuery);
			return jobs;
		}
		if(searchType.equals("jobRole")) {
			List<Job> jobs = service.searchByjobRole(searchQuery);
			return jobs;
		}
		List<Job> noJobs = new ArrayList<Job>();
;		return noJobs;
	}
}
