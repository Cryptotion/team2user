package com.revature.revhire.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequest {
	
	private long userId;
	private long jobId;
	private String appliedOn;
	private String resumeLink;

}
