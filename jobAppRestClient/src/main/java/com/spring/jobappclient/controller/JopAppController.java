package com.spring.jobappclient.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.jobappclient.model.Candidate;
import com.spring.jobappclient.model.Job;
import com.spring.jobappclient.model.JobList;
import com.spring.jobappclient.service.JobClientService;

@Controller
public class JopAppController {
	
	@Autowired
	private JobClientService service;
	
	@GetMapping("/")
	public String getAllJobs(@ModelAttribute("success") String msg,Model m) {
		List<Job> jobs = service.getAllJobs();
		Collections.reverse(jobs);
		m.addAttribute("jobs",jobs);
		m.addAttribute("success",msg);
		return "index.jsp";
	}
	@GetMapping("/addjob")
	public String addJobForm() {
		return "addUserForm.jsp";
	}
	@PostMapping("/addjob")
	public String addJob(@RequestParam("title") String title,
			@RequestParam("jobDesc") String jobDesc,
			@RequestParam("exp") int exp,
			@RequestParam("jobRole") String jobRole,
			RedirectAttributes redirectAttributes) {
		Job job = new Job(title, jobDesc, exp, jobRole);
		service.createJob(job);
		redirectAttributes.addFlashAttribute("success","Successfully Added");
		return "redirect:/";
	}
	@GetMapping("/{job-id}")
	public String viewJob(@PathVariable("job-id") long jobId, Model m) {
		Job job = service.getJob(jobId);
		m.addAttribute("job",job);
		return "showJob.jsp";
	}
	@GetMapping("/delete-job/{jobId}")
	public String deleteJob(@PathVariable("jobId") long jobId, RedirectAttributes ra) {
		service.deleteJob(jobId);
		ra.addFlashAttribute("success","Successfully Deleted");
		return "redirect:/";
	}
	@GetMapping("/{jobId}-update-job")
	public String updateJobForm(@PathVariable("jobId") long jobId, Model m) {
		Job job = service.getJob(jobId);
		m.addAttribute("job",job);
		return "updateJobForm.jsp";
	}
	@PostMapping("/updatejob")
	public String updateJob(@RequestParam("title") String title,
			@RequestParam("jobDesc") String jobDesc,
			@RequestParam("exp") int exp,
			@RequestParam("jobRole") String jobRole,
			@RequestParam("jodId") long jodId,
			RedirectAttributes redirectAttributes) {
		Job job = new Job(title, jobDesc, exp, jobRole);
		service.updateJob(jodId, job);
		redirectAttributes.addFlashAttribute("success", "successfully updated");
		return "redirect:/";
	}
	@PostMapping("/search-job")
	public String searchJob(@RequestParam("search") String search,
			@RequestParam("type") String type, Model m) {
		List<Job> jobs = service.searchJob(search, type);
		Job job = new Job();
		m.addAttribute("jobs",jobs);
		m.addAttribute("success","");
		return "index.jsp";
	}
	
	@GetMapping("add-candidate-{job-id}")
	public String addCandidateForm(@PathVariable("job-id") long jobId, Model m) {
		m.addAttribute("jobId",jobId);
		return "candidateForm.jsp";
	}
	@PostMapping("add-candidate-{job-id}")
	public String addCandidateForm(@PathVariable("job-id") long jobId,
			@ModelAttribute Candidate candidate) {
		System.out.println(jobId);
		System.out.println(candidate.getContactNo());
		service.createCandidate(jobId, candidate);
		return "redirect:/" + jobId;
	}
}
