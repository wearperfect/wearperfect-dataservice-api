package com.wearperfect.dataservice.api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "preference_filters")
public class PreferenceFilter {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	String title;
	
	@Column(name = "description")
	String description;
	
	@Column(name = "user_id")
	Long userId;
	
	@Column(name = "created_by")
	Long createdBy;

	@Column(name = "created_on")
	Date createdOn;
	
	@Column(name = "last_updated_by")
	Long lastUpdatedBy;

	@Column(name = "last_updated_on")
	Date lastUpdatedOn;

	@Column(name = "active")
	Boolean active;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "preferenceFilter", orphanRemoval = true, targetEntity = PreferenceFilterGenderCategory.class)
	List<PreferenceFilterGenderCategory> genderCategories;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "preferenceFilter", orphanRemoval = true, targetEntity = PreferenceFilterCategory.class)
	List<PreferenceFilterCategory> categories;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "preferenceFilter", orphanRemoval = true, targetEntity = PreferenceFilterRegion.class)
	List<PreferenceFilterRegion> regions;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "preferenceFilter", orphanRemoval = true, targetEntity = PreferenceFilterStyle.class)
	List<PreferenceFilterStyle> styles;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "preferenceFilter", orphanRemoval = true, targetEntity = PreferenceFilterColor.class)
	List<PreferenceFilterColor> colors;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "preferenceFilter", orphanRemoval = true, targetEntity = PreferenceFilterUser.class)
	List<PreferenceFilterUser> users;
	
	
}
