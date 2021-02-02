package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class UserFollowUpDetailsDTO {
	
	private Long userId;
	
	private Long requestedUserId;
	
	private String username;
	
	private String fullname;
	
	private String profilePicture;
	
	private Boolean following;
	
	private Boolean followed;
	
	private Boolean active;
}
