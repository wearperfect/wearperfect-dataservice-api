package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class PostDetailsDTO {
	
	Long id;
	
	String title;
	
	String description;
	
	Boolean active;
	
	UserBasicDetailsDTO createdBy;
	
	Long createdOn;
	
	UserBasicDetailsDTO lastUpdatedBy;
	
	Long lastUpdatedOn;
	
	Boolean following;
	
	Boolean isLiked;
	
	Boolean isSaved;
	
	Long totalLikes;
	
	Long totalComments;
	
	List<PostCommentDetailsDTO> comments;
	
	List<PostItemDetailsDTO> postItems;
}
