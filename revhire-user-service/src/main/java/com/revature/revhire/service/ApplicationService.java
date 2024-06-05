package com.revature.revhire.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.revhire.dto.ApplicationRequest;
import com.revature.revhire.dto.ApplicationResponse;
import com.revature.revhire.dto.JobResponse;
import com.revature.revhire.service.exception.ApplicationServiceException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@AllArgsConstructor
public class ApplicationService {
	
	private final WebClient webClient;


	public String createApplication(ApplicationRequest applicationRequest) throws ApplicationServiceException {
        try {
			
        	Mono<String> applicationResponse = webClient.post()
	                  .uri("/apply")
	                  .bodyValue(applicationRequest)
	                  .retrieve()
	                  .bodyToMono(String.class);

            return applicationResponse.block();
			
		}
		catch(Exception e) {
			log.error(e);
			throw new ApplicationServiceException("Unable to Create a new application");
			
		}

	}

	public ApplicationResponse getApplication(long id) throws ApplicationServiceException {
        try {
			
        	Mono<ApplicationResponse> applicationResponse = webClient.get()
	                  .uri("/application/{id}", id)
	                  .retrieve()
	                  .bodyToMono(ApplicationResponse.class);

          return applicationResponse.block();
			
		}
		catch(Exception e) {
			log.error(e);
			throw new ApplicationServiceException("Unable to get application details");
			
		}
	
		
	}



	public List<ApplicationResponse> getAllapplicationsByUserId(long userId) throws ApplicationServiceException {
       try {
			
       	Mono<List<ApplicationResponse>> applicationResponses = webClient.get()
                .uri("/applications/user/{userId}", userId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ApplicationResponse>>() {});

        return applicationResponses.block();
			
		}
		catch(Exception e) {
			log.error(e);
			throw new ApplicationServiceException("Unable to get applications by User Id");
			
		}
	}

	public boolean deleteApplication(long id) throws ApplicationServiceException {
        try {
			
        	Mono<ApplicationResponse> applicationResponse = webClient.delete()
	                  .uri("/application/{id}", id)
	                  .retrieve()
	                  .bodyToMono(ApplicationResponse.class);

        if(applicationResponse!=null) {
        	return true;
        }
        else {
        	return false;
        }
			
		}
		catch(Exception e) {
			log.error(e);
			throw new ApplicationServiceException("Unable to Delete application");
			
		}

	}

	public List<ApplicationResponse> getAllapplicationsByJobId(long jobId) throws ApplicationServiceException {
        try {
			
        	Mono<List<ApplicationResponse>> applicationResponses = webClient.get()
                    .uri("/applications/job/{jobId}", jobId )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<ApplicationResponse>>() {});

            return applicationResponses.block();
			
		}
		catch(Exception e) {
			log.error(e);
			throw new ApplicationServiceException("Unable to get applications by Job Id");
			
		}
	}

}
