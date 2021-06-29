package com.wearperfect.dataservice.api.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Transient
	@Column(name = "password")
	private String password;

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "dob")
	private Date dob;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "profile_picture")
	private String profilePicture;
	
	@Column(name = "profile_cover_picture")
	private String profileCoverPicture;

	@Column(name = "bio")
	private String bio;
	
	@Column(name = "website")
	private String website;

	@Column(name = "gender_id")
	private Integer gender_id;
	
	@Column(name = "city_id")
	private Integer cityId;

	@Column(name = "state_id")
	private Integer stateId;
	
	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "home_address_id")
	private Long homeAddressId;
	
	@Column(name = "current_address_id")
	private Long currentAddressId;

	@Column(name = "delivery_address_id")
	private Long deliveryAddressId;

	@Column(name = "business_and_support_id")
	private Long businessAndSupportId;

	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "last_updated_on")
	private Date lastUpdatedOn;

	@Column(name = "active")
	private Boolean active;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class)
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Role roleDetails;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Gender.class)
	@JoinColumn(name = "gender_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Gender genderDetails;
	
	@OneToOne(fetch = FetchType.LAZY, targetEntity = City.class)
	@JoinColumn(name = "city_id", referencedColumnName = "id", insertable = false, updatable = false)
	private City city;
	
	@OneToOne(fetch = FetchType.LAZY, targetEntity = State.class)
	@JoinColumn(name = "state_id", referencedColumnName = "id", insertable = false, updatable = false)
	private State state;
	
	@OneToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
	@JoinColumn(name = "country_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Country country;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
	@JoinColumn(name = "home_address_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Address homeAddressDetails;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
	@JoinColumn(name = "current_address_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Address currentAddressDetails;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
	@JoinColumn(name = "delivery_address_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Address deliveryAddressDetails;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BusinessAndSupport.class)
	@JoinColumn(name = "business_and_support_id", referencedColumnName = "id", insertable = false, updatable = false)
	private BusinessAndSupport businessAndSupport;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = PreferenceFilterUser.class)
	List<PreferenceFilterUser> preferenceFilterUsers;
}
