package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class MessageDetailsDTO {

	Long id;

	Long sentBy;

	Long sentTo;

	String text;

	Long postId;

	String mediaUrl;
	
	String mediaThumbnailUrl;

	Integer mediaType;

	Long createdOn;

	Long receivedOn;

	Long seenOn;
	
	UserBasicDetailsDTO sentByUserDetails;

	UserBasicDetailsDTO sentToUserDetails;
	
	PostDetailsDTO postDetails;
	
	ContentTypeBasicDetailsDTO mediaTypeDetails;
}
