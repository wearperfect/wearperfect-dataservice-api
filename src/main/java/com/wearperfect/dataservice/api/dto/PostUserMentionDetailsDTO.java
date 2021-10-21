package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostUserMentionDetailsDTO {

	Long id;

	Long postId;

	Long userId;

	Long createdOn;
	
	UserBasicDetailsDTO userDetails;
		
	PostDetailsDTO postDetails;
}
