package com.wearperfect.dataservice.api.entity;

import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "user_id")
	Long userId;
	
	@Column(name = "title")
	String title;

	@Column(name = "address_line_1")
	String addressLine1;
	
	@Column(name = "address_line_2")
	String addressLine2;
	
	@Column(name = "landmark")
	String landmark;

	@Column(name = "zip_code")
	String zipCode;
	
	@Column(name = "city_id")
	Integer cityId;

	@Column(name = "state_id")
	Integer stateId;
	
	@Column(name = "country_id")
	Integer countryId;
	
	@Column(name = "geo_location")
	String geoLocation;

	@Column(name = "phone")
	String phone;
	
	@Column(name = "created_by")
	Long createdBy;
	
	@Column(name = "created_on")
	Date createdOn;

	@Column(name = "last_updated_by")
	Long lastUpdatedBy;

	@Column(name = "last_updated_on")
	Date lastUpdatedOn;

	@Column(name = "last_used_on")
	Date lastUsedOn;

	@Column(name = "active")
	Boolean active;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = City.class)
	@JoinColumn(name="city_id", referencedColumnName = "id", updatable = false, insertable = false)
	City cityDetails;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = State.class)
	@JoinColumn(name="state_id", referencedColumnName = "id", updatable = false, insertable = false)
	State stateDetails;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Country.class)
	@JoinColumn(name="country_id", referencedColumnName = "id", updatable = false, insertable = false)
	Country countryDetails;
}
