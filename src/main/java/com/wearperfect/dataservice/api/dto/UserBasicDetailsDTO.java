package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class UserBasicDetailsDTO {
	private Long id;
	private String username;
	private String fullname;
	private String profilePicture;
	private String profileCoverPicture;
	private Boolean active;
}
