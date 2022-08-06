package com.spring.jobapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jobapp.model.Job;
import com.spring.jobapp.repo.IJobRepo;

@Service
public class JobService implements IJobService {
	
	@Autowired
	private IJobRepo repo;
	
	@Override
	public Job createJob(Job job) {
		// TODO Auto-generated method stub
		Job saveedJob = repo.save(job);
		System.out.println(saveedJob);
		return saveedJob;
	}

	@Override
	public List<Job> getAll() {
		// TODO Auto-generated method stub
		List<Job> allJobs = repo.findAll();
		return allJobs;
	}

	@Override
	public Job getJob(long jobId) {
		// TODO Auto-generated method stub
		Job job = repo.findById(jobId).get();
		return job;
	}

	@Override
	public Job updateJob(Job job, long jobId) {
		// TODO Auto-generated method stub
//		job.setJodId(jobId);
		repo.deleteById(jobId);
		Job updatedJob = repo.save(job);
		return updatedJob;
	}

	@Override
	public void deleteJob(long jobId) {
		// TODO Auto-generated method stub
		repo.deleteById(jobId);
	}

	@Override
	public List<Job> searchByTitle(String title) {
		// TODO Auto-generated method stub
		List<Job> searchedJobs = repo.searchByTitleJob(title);
		
		return searchedJobs;
	}

	@Override
	public List<Job> searchByjobRole(String jobRole) {
		// TODO Auto-generated method stub
		List<Job> searchedJobs = repo.searchByJobRole(jobRole);
		return searchedJobs;
	}
}
