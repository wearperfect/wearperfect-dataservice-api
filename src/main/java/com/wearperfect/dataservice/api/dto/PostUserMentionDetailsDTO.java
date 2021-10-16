package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostUserMentionDetailsDTO {

	Long id;

	Long postId;

	Long mentionedUserId;

	Long mentionedBy;

	Long createdOn;
	
	UserBasicDetailsDTO mentionedUserDetails;
	
	UserBasicDetailsDTO mentionedByUserDetails;
	
	PostDetailsDTO postDetails;
}
