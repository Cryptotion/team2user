package com.revature.revhire;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.revhire.controller.UserController;
import com.revature.revhire.dto.LoginCredentials;
import com.revature.revhire.dto.UserRequest;
import com.revature.revhire.dto.UserResponse;
import com.revature.revhire.model.Role;
import com.revature.revhire.service.UserService;
import com.revature.revhire.service.exception.UserServiceException;

@SpringBootTest
class UserTest {

    private UserController userController;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
     void testCreateUser() throws UserServiceException {
        UserRequest userRequest = new UserRequest("john@gmail.com", "Mr.", "John", "Cooper", "John@123", "1990-01-01",
        	    "123-456-7890", "Corenellia St, NYC", 'M', "Senoir Java Developer", Role.JOBSEEKER);
        UserResponse expectedResponse = new UserResponse();
        when(userService.createUser(userRequest)).thenReturn(expectedResponse);
        UserResponse response = userController.createUser(userRequest);
        assertEquals(expectedResponse, response);
    }

    @Test
     void testCreateUserError() throws UserServiceException {
        UserRequest userRequest = new UserRequest("john@gmail.com", "Mr.", "John", "Cooper", "John@123", "1990-01-01",
        	    "123-456-7890", "Corenellia St, NYC", 'M', "Senoir Java Developer", Role.JOBSEEKER);
        when(userService.createUser(userRequest)).thenThrow(new RuntimeException("Test exception"));
        assertThrows(HttpClientErrorException.class, () -> userController.createUser(userRequest));
    }

    @Test
     void testAuthenticateUser() throws UserServiceException {
        LoginCredentials loginCredentials = new LoginCredentials("john@gmail.com","John@123");
        UserResponse expectedResponse = new UserResponse();
        when(userService.authenticateUser(loginCredentials)).thenReturn(expectedResponse);
        UserResponse response = userController.authenticateUser(loginCredentials);
        assertEquals(expectedResponse, response);
    }

    @Test
     void testAuthenticateUserError() throws UserServiceException {
        LoginCredentials loginCredentials = new LoginCredentials("john@gmail.com","John@123");
        when(userService.authenticateUser(loginCredentials)).thenThrow(new RuntimeException("Test exception"));
        assertThrows(HttpClientErrorException.class, () -> userController.authenticateUser(loginCredentials));
    }
    
    @Test
     void testUpdateUser() throws UserServiceException {
        UserRequest userRequest = new UserRequest("joseph@gmail.com", "Mr.", "Joseph", "Cooper", "Josep@123", "1990-01-01",
        	    "123-456-7890", "Corenellia St, NYC", 'M', "Senoir Java Developer", Role.JOBSEEKER);
        long userId = 4;
        UserResponse expectedResponse = new UserResponse();
        when(userService.updateUser(userRequest, userId)).thenReturn(expectedResponse);
        UserResponse response = userController.updateateUser(userRequest, userId);
        assertEquals(expectedResponse, response);
    }

    @Test
     void testUpdateUserError() throws UserServiceException {
        UserRequest userRequest = new UserRequest();
        long userId = 4;
        when(userService.updateUser(userRequest, userId)).thenThrow(new RuntimeException("Test exception"));
        assertThrows(HttpClientErrorException.class, () -> userController.updateateUser(userRequest, userId));
    }

    @Test
     void testGetUser() throws UserServiceException {
        long userId = 4;
        UserResponse expectedResponse = new UserResponse();
        when(userService.getUser(userId)).thenReturn(expectedResponse);
        UserResponse response = userController.getUser(userId);

        assertEquals(expectedResponse, response);
    }

    @Test
     void testGetUserError() throws UserServiceException {
        long userId = 4;
        when(userService.getUser(userId)).thenThrow(new RuntimeException("Test exception"));
        assertThrows(HttpClientErrorException.class, () -> userController.getUser(userId));
    }

    @Test
     void testDeleteUserSuccess() throws UserServiceException {
        long userId = 4; 
        when(userService.deleteUser(userId)).thenReturn(true);
        String response = userController.deleteUser(userId);
        assertEquals("User Deleted Successfully", response);
    }

    @Test
     void testDeleteUserError() throws UserServiceException {
        long userId = 4;
        when(userService.deleteUser(userId)).thenReturn(false);
        assertThrows(HttpClientErrorException.class, () -> userController.deleteUser(userId));
    }

}
