package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class PostMediaDetailsDTO {

	Long id;
	Long postId;
	Integer sequenceId;
	String sourceLink;
	String contentType;
	Integer height;
	Integer width;
	Float aspectRatio;
	Boolean active;
	Long createdOn;
	Long lastUpdatedOn;
	List<PostMediaUserTagDetailsDto> userTags;
}
