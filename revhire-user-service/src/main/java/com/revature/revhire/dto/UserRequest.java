package com.revature.revhire.dto;

import com.revature.revhire.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	private String email;
	private String title;
	private String firstName;
	private String lastName;
	private String password;
	private String dob;
	private String contactNumber;
	private String address;
	private char gender;
	private String designation;
	private Role role;

}
