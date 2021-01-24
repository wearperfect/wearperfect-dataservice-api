package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class PostDetailsDTO {
	
	Long id;
	
	String title;
	
	String description;
	
	Boolean active;
	
	BasicUserDetailsDTO createdBy;
	
	Long createdOn;
	
	BasicUserDetailsDTO lastUpdatedBy;
	
	Long lastUpdatedOn;
	
	Boolean isLiked;
	
	Boolean isSaved;
	
	Long totalLikes;
	
	Long totalComments;
	
	List<PostCommentDetailsDTO> comments;
	
	List<PostItemDetailsDTO> postItems;
}
