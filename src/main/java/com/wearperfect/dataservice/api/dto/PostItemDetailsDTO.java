package com.wearperfect.dataservice.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostItemDetailsDTO {

	Long id;
	Long postId;
	Integer sequenceId;
	String sourceLink;
	BasicContentTypeDetailsDTO contentType;
	Boolean active;
	Date createdOn;
	Date lastUpdatedOn;
	
}
