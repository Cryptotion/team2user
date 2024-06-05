package com.revature.revhire.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobRequest {
	
	private long creatorId;
	private String companyName;
	private String logoLink;
	private String createdOn;
	private String designation;
	private String location;
	private String description;
	private List<String> skills;

}
