package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class PostDTO {

	Long id;
	String title;
	String description;
	Boolean active;
	Long createdBy;
	Long createdOn;
	Long lastUpdatedBy;
	Long lastUpdatedOn;
	List<PostItemDTO> postItems;
}
