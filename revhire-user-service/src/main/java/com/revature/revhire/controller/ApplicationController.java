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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.revhire.dto.ApplicationRequest;
import com.revature.revhire.dto.ApplicationResponse;
import com.revature.revhire.service.ApplicationService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ApplicationController {
	private final ApplicationService applicationService;
	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

	
	@PostMapping(path = "application/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String createApplication(@RequestBody ApplicationRequest applicationRequest) {

		try {
			
			return applicationService.createApplication(applicationRequest);

		} catch (Exception e) {
			log.error(e.getMessage());

			throw new HttpClientErrorException(HttpStatusCode.valueOf(500), "Unable to create the application");
		}

	}
		
	@GetMapping(path = "application/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ApplicationResponse getApplication(@PathVariable long id) {
		try {
			return applicationService.getApplication(id);
		}
	 catch (Exception e) {
		 log.error(e.getMessage());
		throw new HttpClientErrorException(HttpStatusCode.valueOf(500), "Unable to get application information");
		 
	}
		
	}
	@GetMapping(path = "applications/job/{jobId}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ApplicationResponse> getAllApplicationsByJobId(@PathVariable long jobId) {
		try {
			return applicationService.getAllapplicationsByJobId(jobId);
		}
	 catch (Exception e) {
		 log.error(e.getMessage());
		throw new HttpClientErrorException(HttpStatusCode.valueOf(500), "Unable to get applications");
		 
	}
	
		
	}
	@GetMapping(path = "applications/user/{userId}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ApplicationResponse> getAllapplicationsByUserId(@PathVariable long userId) {
		try {
			return applicationService.getAllapplicationsByUserId(userId);
		}
	 catch (Exception e) {
		 log.error(e.getMessage());
		throw new HttpClientErrorException(HttpStatusCode.valueOf(500), "Unable to get applications");
		 
	}
	
		
	}
	

	@DeleteMapping(path = "application/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public String deleteApplication(@PathVariable long id) {
		try {
			if(applicationService.deleteApplication(id)) {
				return "application Deleted Successfully";
			}
			else {
				
				throw new HttpClientErrorException(HttpStatusCode.valueOf(401), "Unable to delete application");
				
			}
		}
	 catch (Exception e) {
		 log.error(e.getMessage());
		throw new HttpClientErrorException(HttpStatusCode.valueOf(401), "Unable to delete application");
		 
	}
		
	}


}
