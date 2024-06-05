package com.revature.revhire.dto;

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
public class ApplicationResponse {
	private long id;
	private long userId;
	private long jobId;
	private String appliedOn;
	private String resumeLink;

}
