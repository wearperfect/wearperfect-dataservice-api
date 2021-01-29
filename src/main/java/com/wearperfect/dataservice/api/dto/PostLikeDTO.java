package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostLikeDTO {
	
	Long id;
	
	Long postId;
		
	Long likedBy;
	
	Long likedOn;
	
	UserBasicDetailsDTO likedByUserDetails;
}
