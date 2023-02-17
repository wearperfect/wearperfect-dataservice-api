package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class HashTagSearchDTO {
	Long id;
	String tag;
	Long totalPostsTagged;
	Boolean active;
}
