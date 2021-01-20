package com.wearperfect.dataservice.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostItemDTO {

	Long id;
	Long postId;
	Integer sequenceId;
	String sourceLink;
	String contentType;
	Boolean active;
	Date createdOn;
	Date lastUpdatedOn;
}
