package com.wearperfect.dataservice.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostCommentDetailsDTO {

    Long id;
	
	Long postId;

	String description;

	BasicUserDetailsDTO commentedBy;

	Date commentedOn;

	Date lastUpdatedOn;
	
	Boolean active;
}
