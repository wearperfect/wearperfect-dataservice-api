package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostItemDTO {

	Long id;
	Long postId;
	Integer sequenceId;
	String sourceLink;
	String contentType;
	Float aspectRatio;
	Boolean active;
	Long createdOn;
	Long lastUpdatedOn;
}
