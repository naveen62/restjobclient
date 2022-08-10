package com.spring.jobapp.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jobapp.model.Candidate;
import com.spring.jobapp.model.Job;
import com.spring.jobapp.service.CandidateService;
import com.spring.jobapp.service.IJobService;
import com.spring.jobapp.service.JobService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "Job REST API", description = "API for Job Management System")
public class JobRestController {
	
	@Autowired
	JobService service;
	@Autowired
	CandidateService candidateService;
	
	@PostMapping("/jobs")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Creation of job")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201",description = "job created",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Job.class))})
	})
	public Job createJob(@RequestBody Job job) {
		return service.createJob(job);
	}
	@GetMapping("/jobs")
	@Operation(summary = "Get all job")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "getting all jobs from database",
					content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Job.class)))})
	})
	@ResponseStatus(code = HttpStatus.OK)
	public List<Job> getAllJobs() {
		return service.getAll();
	}
	@GetMapping("/jobs/{job-id}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Get job by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Getting Job by its own id",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Job.class))})
	})
	public Job getJob(@Parameter(description = "id of the job to be searched") @PathVariable("job-id") long jobId) {
		Job job = service.getJob(jobId);
		return job;
	}
	@PutMapping("/jobs/{job-id}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "update the job")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "job updated using id",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Job.class))})
	})
	public Job updateJob(@Parameter(description = "id of the job to be updated") @PathVariable("job-id") long jobId, @RequestBody Job job) {
		return service.updateJob(job, jobId);
	}
	@DeleteMapping("/jobs/{job-id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "delete the job")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204",description = "job deleted using id")
	})
	public boolean deleteJob(@Parameter(description = "id of the job to be deleted") @PathVariable("job-id") long jodId) {
		service.deleteJob(jodId);
		return true;
	}
	@PostMapping("/candidate/jobs/{job-id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "create candidate for a job")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "candidate created for job specified",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Candidate.class))})
	})
	public Candidate addCandidate(@Parameter(description = "id of job for which candidate needs to be added") @PathVariable("job-id") long jobId, @RequestBody Candidate candidate) {
		return candidateService.saveCandidate(jobId, candidate);
	}
	
	@GetMapping("/search/jobs/{search-query}/{search-type}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "search job")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "list of jobs searched",
					content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Job.class)))})
	})
	public List<Job> searchJob(@Parameter(description = "search query to search jobs") @PathVariable("search-query") String searchQuery,
			@Parameter(description = "type of search needs to be perform") @PathVariable("search-type") String searchType) {
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
