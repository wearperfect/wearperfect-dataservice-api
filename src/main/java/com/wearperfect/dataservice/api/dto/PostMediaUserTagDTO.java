package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostMediaUserTagDTO {
	Long id;
	Long postMediaId;
	Long taggedUserId;
	Double tagLocationX;
	Double tagLocationY;
	Long createdOn;
}
