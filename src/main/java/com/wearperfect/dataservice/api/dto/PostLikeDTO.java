package com.wearperfect.dataservice.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostLikeDTO {
	
	Long id;
	
	Long postId;
		
	Long likedBy;
	
	Date likedOn;
}
