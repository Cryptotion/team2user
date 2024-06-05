package com.revature.revhire.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.revhire.dto.JobRequest;
import com.revature.revhire.dto.JobResponse;
import com.revature.revhire.service.JobService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@RestController()
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class JobController {
	
	private final JobService jobService;
	private static final Logger log = LoggerFactory.getLogger(JobController.class);

	
	@PostMapping(path = "job/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String createJob(@RequestBody JobRequest jobRequest) {

		try {
			
			return jobService.createJob(jobRequest);

		} catch (Exception e) {
			log.error(e.getMessage());

			throw new HttpClientErrorException(HttpStatusCode.valueOf(500), "Unable to create the job");
		}

	}
	@PutMapping(path = "job/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public JobResponse updateateJob(@RequestBody JobRequest jobRequest, @PathVariable long id) {
		try {
			return jobService.updateJob(jobRequest, id);
		}
	 catch (Exception e) {
		 log.error(e.getMessage());
		throw new HttpClientErrorException(HttpStatusCode.valueOf(500), "Unable to update the Job");
		 
	}
		
	}
	
	@GetMapping(path = "job/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public JobResponse getJob(@PathVariable long id) {
		try {
			return jobService.getJob(id);
		}
	 catch (Exception e) {
		 log.error(e.getMessage());
		throw new HttpClientErrorException(HttpStatusCode.valueOf(500), "Unable to get job information");
		 
	}
		
	}
	@GetMapping(path = "jobs")
	@ResponseStatus(code = HttpStatus.OK)
	public List<JobResponse> getAllJobs() {
		try {
			return jobService.getAllJobs();
		}
	 catch (Exception e) {
		 log.error(e.getMessage());
		throw new HttpClientErrorException(HttpStatusCode.valueOf(500), "Unable to get jobs information");
		 
	}
	
		
	}
	@GetMapping(path = "jobs/{userId}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<JobResponse> getAllJobsByUserId(@PathVariable long userId) {
		try {
			return jobService.getAllJobsByUserId(userId);
		}
	 catch (Exception e) {
		 log.error(e.getMessage());
		throw new HttpClientErrorException(HttpStatusCode.valueOf(500), "Unable to get jobs information");
		 
	}
	
		
	}
	

	@DeleteMapping(path = "job/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public String deleteJob(@PathVariable long id) {
		try {
			if(jobService.deleteJob(id)) {
				return "job Deleted Successfully";
			}
			else {
				
				throw new HttpClientErrorException(HttpStatusCode.valueOf(401), "Unable to delete Job");
				
			}
		}
	 catch (Exception e) {
		 log.error(e.getMessage());
		throw new HttpClientErrorException(HttpStatusCode.valueOf(401), "Unable to delete Job");
		 
	}
		
	}

	

}
