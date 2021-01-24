package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostCommentDetailsDTO {

    Long id;
	
	Long postId;

	String description;

	BasicUserDetailsDTO commentedBy;

	Long commentedOn;

	Long lastUpdatedOn;
	
	Boolean active;
}
