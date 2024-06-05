package com.revature.revhire.controller;

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

import com.revature.revhire.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import com.revature.revhire.dto.LoginCredentials;
import com.revature.revhire.dto.UserRequest;
import com.revature.revhire.dto.UserResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/")
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {
	
	

	private final UserService userService;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	

	@PostMapping(path = "signup")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserResponse createUser(@RequestBody UserRequest userRequest) {

		try {
			
			return userService.createUser(userRequest);

		} catch (Exception e) {
			log.error(e.getMessage());

			throw new HttpClientErrorException(HttpStatusCode.valueOf(500), "Unable to create the user");
		}

	}

	@PostMapping(path = "login")
	@ResponseStatus(code = HttpStatus.OK)
	public UserResponse authenticateUser(@RequestBody LoginCredentials loginCredenials) {
		try {
			return userService.authenticateUser(loginCredenials);
		}
	 catch (Exception e) {
		 log.error(e.getMessage());
		throw new HttpClientErrorException(HttpStatusCode.valueOf(401), "Wrong email Id or password");
		 
	}
		
	}
	
	@PutMapping(path = "user/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public UserResponse updateateUser(@RequestBody UserRequest userRequest, @PathVariable long id) {
		try {
			return userService.updateUser(userRequest, id);
		}
	 catch (Exception e) {
		 log.error(e.getMessage());
		throw new HttpClientErrorException(HttpStatusCode.valueOf(500), "Unable to update profile");
		 
	}
		
	}
	
	@GetMapping(path = "user/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public UserResponse getUser(@PathVariable long id) {
		try {
			return userService.getUser(id);
		}
	 catch (Exception e) {
		 log.error(e.getMessage());
		throw new HttpClientErrorException(HttpStatusCode.valueOf(500), "Unable to get user information");
		 
	}
		
	}
	

	@DeleteMapping(path = "user/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public String deleteUser(@PathVariable long id) {
		try {
			if(userService.deleteUser(id)) {
				return "User Deleted Successfully";
			}
			else {
				
				throw new HttpClientErrorException(HttpStatusCode.valueOf(401), "Unable to delete profile");
				
			}
		}
	 catch (Exception e) {
		 log.error(e.getMessage());
		throw new HttpClientErrorException(HttpStatusCode.valueOf(401), "Unable to delete profile");
		 
	}
		
	}

}
