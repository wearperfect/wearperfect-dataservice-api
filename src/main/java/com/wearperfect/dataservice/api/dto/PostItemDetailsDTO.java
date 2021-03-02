package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostItemDetailsDTO {

	Long id;
	Long postId;
	Integer sequenceId;
	String sourceLink;
	//ContentTypeBasicDetailsDTO contentType;
	Boolean active;
	Long createdOn;
	Long lastUpdatedOn;
	
}
