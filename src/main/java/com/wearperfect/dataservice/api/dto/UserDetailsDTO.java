package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class UserDetailsDTO {

	private Long id;
	private String username;
	private String fullname;
	private String email;
	private String phone;
	private String profilePicture;
	private String bio;
	private String website;
	private GenderBasicDetailsDTO gender;
	private RoleBasicDetailsDTO role;
	private Long createdOn;
	private Long lastUpdatedOn;
	private Boolean active;
	
	private Long homeAddressId;
	private Long currentAddressId;
	private Long deliveryAddressId;
	private Long businessAddressId;
	
	private AddressDetailsDTO homeAddress;
	private AddressDetailsDTO currentAddress;
	private AddressDetailsDTO deliveryAddress;
	private AddressDetailsDTO businessAddress;
	
	private Long totalFollowers;
	private Long totalFollowing;
	private Long totalPosts;
}
