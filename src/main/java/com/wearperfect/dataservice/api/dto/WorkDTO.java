package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class WorkDTO {

	Long id;
	
	Long workedBy;

	Long workedAt;

	String workedAtAlt;

	Integer workedAs;

	String workedAsAlt;

	Long workedFrom;

	Long workedTo;

	Boolean workingActively;

	Long createdBy;

	Long createdOn;

	Long lastUpdatedBy;

	Long lastUpdatedOn;

	Boolean active;
}
