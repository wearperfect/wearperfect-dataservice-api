package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class UserDTO {
	
	private Long id;
	private String username;
	private String password;
	private String roleId;
	private String fullname;
	private Long dob;
	private String email;
	private String phone;
	private String profilePicture;
	private String profileCoverPicture;
	private String bio;
	private String website;
	private Integer genderId;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	private Long homeAddressId;
	private Long currentAddressId;
	private Long deliveryAddressId;
	private Long businessAndSupportId;
	private Long createdOn;
	private Long lastUpdatedOn;
	private Boolean active;
}


