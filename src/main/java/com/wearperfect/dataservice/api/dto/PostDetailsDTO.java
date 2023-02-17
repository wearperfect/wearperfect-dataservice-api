package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class PostDetailsDTO {
	Long id;
	String title;
	String description;
	Boolean active;
	Long createdBy;
	Long createdOn;
	Long lastUpdatedBy;
	Long lastUpdatedOn;
	Boolean following;
	Boolean liked;
	Boolean saved;
	Long totalLikes;
	Long totalComments;
	UserBasicDetailsDTO createdByUserDetails;
	UserBasicDetailsDTO lastUpdatedByUserDetails;
	List<PostCommentDetailsDTO> comments;
	List<PostMediaDetailsDTO> postMediaList;
}
