package com.revature.revhire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.revhire.dao.UserDao;
import com.revature.revhire.dto.LoginCredentials;
import com.revature.revhire.dto.UserRequest;
import com.revature.revhire.dto.UserResponse;
import com.revature.revhire.service.exception.UserServiceException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Log4j2
public class UserService {
	
	
	@Autowired
	private PasswordEncoder encoder;

	private final UserDao userDao;

	public UserResponse createUser(UserRequest userRequest) throws UserServiceException {
		try {
			
			String encryptedPassword = encoder.encode(userRequest.getPassword());
			userRequest.setPassword(encryptedPassword);
			return userDao.createUser(userRequest);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new UserServiceException("Unable to register");
			
		}
		
	}

	public UserResponse authenticateUser(LoginCredentials loginCredenials) throws UserServiceException {
      try {
    	  
    	  UserResponse userResponse = userDao.authenticateUser(loginCredenials);
    	  System.out.println(userResponse.getPassword());
    	  if(encoder.matches(loginCredenials.getPassword(),userResponse.getPassword())) {
    		  
    		  return userResponse;
    	  }
    	  return null;
			
		}
		catch(Exception e) {
			log.error(e);
		
				throw new UserServiceException("Email or Password is Wrong");				
			
			
			
		}
	}

	public UserResponse updateUser(UserRequest userRequest, long id) throws UserServiceException {
        try {
			
			return userDao.updateUser(userRequest, id);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new UserServiceException("Unable to update user details");

			
			
		}
	}

	public UserResponse getUser(long id) throws UserServiceException {
        try {
			
			return userDao.getUser(id);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new UserServiceException("Unable to get user details");

			
			
		}
		
	}

	public boolean deleteUser(long id) throws UserServiceException {
        try {
			
			return userDao.deleteUser(id);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new UserServiceException("Unable to delete user");

			
			
		}

	}

}
