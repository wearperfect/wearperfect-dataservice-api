package com.wearperfect.dataservice.api.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PostDetailsDTO {
	
	Long id;
	
	String title;
	
	String description;
	
	Boolean active;
	
	BasicUserDetailsDTO createdBy;
	
	Date createdOn;
	
	BasicUserDetailsDTO lastUpdatedBy;
	
	Date lastUpdatedOn;
	
	Boolean isLiked;
	
	Boolean isSaved;
	
	Long totalLikes;
	
	Long totalComments;
	
	List<PostCommentDTO> comments;
	
	List<PostItemDetailsDTO> postItems;
}
