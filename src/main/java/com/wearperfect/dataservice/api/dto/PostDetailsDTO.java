package com.wearperfect.dataservice.api.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PostDetailsDTO {

	Long id;
	String title;
	String desc;
	Boolean active;
	BasicUserDetailsDTO createdBy;
	Date createdOn;
	BasicUserDetailsDTO lastUpdatedBy;
	Date lastUpdatedOn;
	List<PostItemDetailsDTO> postItems;
}
