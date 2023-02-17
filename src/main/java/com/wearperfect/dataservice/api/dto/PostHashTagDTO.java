package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostHashTagDTO {
	Long id;
	Long postId;
	Long hashTagId;
	Long createdOn;
	Boolean active;
}
