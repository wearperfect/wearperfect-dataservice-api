package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostSaveDetailsDTO {
	Long id;
	Long postId;
	Long savedTo;
	Long savedBy;
	Long savedOn;
	PostDetailsDTO postDetails;
	UserBasicDetailsDTO savedByUserDetails;
}
