package com.wearperfect.dataservice.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostCommentDTO {

	Long id;
	
	Long postId;

	String comment;

	Long commentedBy;

	Date commentedOn;

	Date lastUpdatedOn;
	
	Boolean active;
}
