package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class UserDTO {
	
	private Long id;
	private String username;
	private String password;
	private String roleId;
	private String fullname;
	private String email;
	private String phone;
	private String profilePicture;
	private String bio;
	private Integer genderId;
	private Long homeAddressId;
	private Long currentAddressId;
	private Long deliveryAddressId;
	private Long businessAddressId;
	private Long createdOn;
	private Long lastUpdatedOn;
	private Boolean active;
}


