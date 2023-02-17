package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class AddressDetailsDTO {
    Long id;
    Long userId;
    String title;
    String addressLine1;
    String addressLine2;
    String landmark;
    String zipCode;
    CityBasicDetailsDTO city;
    StateBasicDetailsDTO state;
    CountryBasicDetailsDTO country;
    String geoLocation;
    String phone;
    Long lastUsedOn;
    Boolean active;
}
