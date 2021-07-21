package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class UserDetailsDTO {

	private Long id;
	private String username;
	private String fullname;
	private Long dob;
	private String email;
	private Boolean verifiedEmail;
	private String phone;
	private Boolean verifiedPhone;
	private String profilePicture;
	private String profileCoverPicture;
	private String bio;
	private String website;
	private Integer genderId;
	private GenderBasicDetailsDTO gender;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	private Integer roleId;
	private Boolean verified;
	private Long createdOn;
	private Long lastUpdatedOn;
	private Boolean active;
	
	private Boolean following;
	
	private RoleBasicDetailsDTO role;

	private CityBasicDetailsDTO city;
	private StateBasicDetailsDTO state;
	private CountryBasicDetailsDTO country;

	private Long homeAddressId;
	private Long currentAddressId;
	private Long deliveryAddressId;
	private Long businessAndSupportId;

	private AddressDetailsDTO homeAddress;
	private AddressDetailsDTO currentAddress;
	private AddressDetailsDTO deliveryAddress;
	private BusinessAndSupportDTO businessAndSupport;

	private Long totalFollowers;
	private Long totalFollowing;
	private Long totalPosts;
}
