package com.revature.revhire.dao;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.revature.revhire.dao.exception.DuplicateEntryException;
import com.revature.revhire.dao.exception.UserDaoException;
import com.revature.revhire.dto.LoginCredentials;
import com.revature.revhire.dto.UserRequest;
import com.revature.revhire.dto.UserResponse;
import com.revature.revhire.model.User;
import com.revature.revhire.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@AllArgsConstructor
@Log4j2
public class UserDao {
	private final UserRepository userRepository;

	public UserResponse createUser(UserRequest userRequest) throws DuplicateEntryException, UserDaoException {
		
		try {
		
		User user = User.builder()
				.firstName(userRequest.getFirstName())
				.lastName(userRequest.getLastName())
				.address(userRequest.getAddress())
				.password(userRequest.getPassword())
				.email(userRequest.getEmail())
		        .address(userRequest.getAddress())
		        .dob(userRequest.getDob())
		        .contactNumber(userRequest.getContactNumber())
		        .gender(userRequest.getGender())
		        .designation(userRequest.getDesignation())
		        .role(userRequest.getRole())
		        .title(userRequest.getTitle())
		        .build();
		user = userRepository.save(user);
	    
		return UserResponse.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.address(user.getAddress())
				.password(user.getPassword())
				.title(user.getTitle())
				.email(user.getEmail())
		        .address(user.getAddress())
		        .dob(user.getDob())
		        .contactNumber(user.getContactNumber())
		        .gender(user.getGender())
		        .designation(user.getDesignation())
		        .role(user.getRole())
		        .build();
		}
		catch(DataIntegrityViolationException e) {
			log.error(e);
			throw new DuplicateEntryException("Date Integrity Vialation for Entity User");
			
			
		}
		catch(Exception e) {
			log.error(e);
            throw new UserDaoException("Unable to create a new user record");
			
		}
	}

	public UserResponse authenticateUser(LoginCredentials loginCredenials) throws UserDaoException {
		try {
			User user = userRepository.authenticateUser(loginCredenials.getEmail()).get();
			
			
			return UserResponse.builder()
					.id(user.getId())
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.address(user.getAddress())
					.password(user.getPassword())
					.title(user.getTitle())
					.email(user.getEmail())
			        .address(user.getAddress())
			        .dob(user.getDob())
			        .contactNumber(user.getContactNumber())
			        .gender(user.getGender())
			        .designation(user.getDesignation())
			        .role(user.getRole())
			        .build();
			
			
		}
		catch(Exception e) {
			log.error(e);
			throw new UserDaoException("Unable to retrieve record");
			
		}
	}

	public UserResponse updateUser(UserRequest userRequest, long id) throws UserDaoException {
		try {
			
			User user = User.builder()
					.id(id)
					.firstName(userRequest.getFirstName())
					.lastName(userRequest.getLastName())
					.address(userRequest.getAddress())
					.password(userRequest.getPassword())
					.email(userRequest.getEmail())
			        .address(userRequest.getAddress())
			        .dob(userRequest.getDob())
			        .contactNumber(userRequest.getContactNumber())
			        .gender(userRequest.getGender())
			        .designation(userRequest.getDesignation())
			        .role(userRequest.getRole())
			        .title(userRequest.getTitle())
			        .build();
			user = userRepository.save(user);
			return UserResponse.builder()
					.id(user.getId())
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.address(user.getAddress())
					.password(user.getPassword())
					.title(user.getTitle())
					.email(user.getEmail())
			        .address(user.getAddress())
			        .dob(user.getDob())
			        .contactNumber(user.getContactNumber())
			        .gender(user.getGender())
			        .designation(user.getDesignation())
			        .role(user.getRole())
			        .build();
			
		}
		catch(Exception e) {
			log.error(e);
			throw new UserDaoException("Unable to Update record");
			
		}
	}

	public UserResponse getUser(long id) throws UserDaoException {
		
		try {
			User user = userRepository.findById(id).get();
			return UserResponse.builder()
					.id(user.getId())
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.address(user.getAddress())
					.password(user.getPassword())
					.title(user.getTitle())
					.email(user.getEmail())
			        .address(user.getAddress())
			        .dob(user.getDob())
			        .contactNumber(user.getContactNumber())
			        .gender(user.getGender())
			        .designation(user.getDesignation())
			        .role(user.getRole())
			        .build();
			
		}
		catch(Exception e) {
			log.error(e);
			throw new UserDaoException("Unable to Retrieve record");
			
		}
		
	}

	public boolean deleteUser(long id) throws UserDaoException {
		
		try {
		userRepository.deleteById(id);
		return true;
		}
		catch(Exception e) {
			log.error(e);
			throw new UserDaoException("Unable to Delete record");
			
		}
		
	}
	
	

	

}
