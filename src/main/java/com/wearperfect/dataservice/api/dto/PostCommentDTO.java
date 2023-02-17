package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostCommentDTO {
	Long id;
	Long postId;
	String description;
	Long commentedBy;
	Long commentedOn;
	Long lastUpdatedOn;
	Boolean active;
}
