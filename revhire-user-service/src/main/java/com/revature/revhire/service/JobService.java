package com.revature.revhire.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.revature.revhire.dto.JobRequest;
import com.revature.revhire.dto.JobResponse;
import com.revature.revhire.service.exception.JobServiceException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Log4j2
public class JobService {
	
	private final WebClient webClient;

	public String createJob(JobRequest jobRequest) throws JobServiceException {
		
		try {
			
			Mono<String> jobResponse = webClient.post()
					                  .uri("/job/create")
					                  .bodyValue(jobRequest)
					                  .retrieve()
					                  .bodyToMono(String.class);
			
			return jobResponse.block();
			
		}
		catch(Exception e) {
			log.error(e);
			throw new JobServiceException("Unable to Create a new Job");
			
		}
		
	}

	public JobResponse updateJob(JobRequest jobRequest, long id) throws JobServiceException {
	    try {
		
		    Mono<JobResponse> jobResponse = webClient.patch()
                .uri("/job/{id}", id)
                .bodyValue(jobRequest)
                .retrieve()
                .bodyToMono(JobResponse.class);

            return jobResponse.block();
			
			
		}
		catch(Exception e) {
			log.error(e);
			throw new JobServiceException("Unable to update Job");
			
		}
	}

	public JobResponse getJob(long id) throws JobServiceException {
	try {
			
		    Mono<JobResponse> jobResponse = webClient.get()
                .uri("/job/{id}", id)
                .retrieve()
                .bodyToMono(JobResponse.class);

            return jobResponse.block();
			
		}
		catch(Exception e) {
			log.error(e);
			throw new JobServiceException("Unable to get Job Information");
			
		}
	}

	public boolean deleteJob(long id) throws JobServiceException {
	try {
		
		   Mono<String> jobResponse = webClient.delete()
                .uri("/job/{id}", id)
                .retrieve()
                .bodyToMono(String.class);
		   

		   if(jobResponse!=null) {
	        	return true;
	        }
	        else {
	        	return false;
	        }
			
		}
		catch(Exception e) {
			log.error(e);
			throw new JobServiceException("Unable to Delete the Job");
			
		}
	}

	public List<JobResponse> getAllJobsByUserId(long userId) throws JobServiceException {
	try {
			
		Mono<List<JobResponse>> jobResponse = webClient.get()
                .uri("/jobs/{userId}", userId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<JobResponse>>() {});

            return jobResponse.block();
			
		}
		catch(Exception e) {
			log.error(e);
			throw new JobServiceException("Unable to retreive all the Jobs by user Id");
			
		}
	}

	public List<JobResponse> getAllJobs() throws JobServiceException {
	try {
			
		    Mono<List<JobResponse>> jobResponse = webClient.get()
                .uri( "/jobs")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<JobResponse>>() {});

            return jobResponse.block();
			
			
		}
		catch(Exception e) {
			log.error(e);
			throw new JobServiceException("Unable to get all the Jobs");
			
		}
	}

}
