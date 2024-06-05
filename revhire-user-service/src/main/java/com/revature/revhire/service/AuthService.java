package com.revature.revhire.service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.revhire.dao.UserDao;
import com.revature.revhire.dto.LoginCredentials;
import com.revature.revhire.dto.UserResponse;
import com.revature.revhire.model.User;
import com.revature.revhire.service.exception.UserServiceException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@AllArgsConstructor
@Log4j2
public class AuthService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	private final UserDao userDao;

	
	private static final Map<String, UserResponse> sessionMap = new HashMap<String, UserResponse>();



	
	public UserResponse login(LoginCredentials loginCredenials) throws UserServiceException {
		
		try {
	    	  String encryptedPassword = encoder.encode(loginCredenials.getPassword());
	    	  String token = UUID.randomUUID().toString();
			  
	    	  
	    	  UserResponse userResponse = userDao.authenticateUser(loginCredenials);
	    	  sessionMap.put(token, userResponse );
	    	  
	    	  if(encoder.matches(userResponse.getPassword(),encryptedPassword)) {
	    		  return userResponse;
	    	  }
				
			}
			catch(Exception e) {
				log.error(e);
			
					throw new UserServiceException("Email or Password is Wrong");				
				
				
				
			}
		return null;
		
	}
	
	public void logout(String token) {
		sessionMap.remove(token);
	}
	
}
