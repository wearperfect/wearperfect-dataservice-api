package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostMediaDetailsDTO {

	Long id;
	Long postId;
	Integer sequenceId;
	String sourceLink;
	//ContentTypeBasicDetailsDTO contentType;
	String contentType;
	Integer height;
	Integer width;
	Float aspectRatio;
	Boolean active;
	Long createdOn;
	Long lastUpdatedOn;
	
}
