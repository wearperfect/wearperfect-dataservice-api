package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class UserDetailsDTO {

	private Long id;
	private String username;
	private String fullname;
	private Long dob;
	private String email;
	private String phone;
	private String profilePicture;
	private String bio;
	private String website;
	private Integer genderId;
	private GenderBasicDetailsDTO gender;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	private Integer roleId;
	private RoleBasicDetailsDTO role;
	private Long createdOn;
	private Long lastUpdatedOn;
	private Boolean active;

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
	private BusinessAndSupportDetailsDTO businessAndSupport;

	private Long totalFollowers;
	private Long totalFollowing;
	private Long totalPosts;
}
