package com.wearperfect.dataservice.api.entities;

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
@Table(name = "saved_filters")
public class SavedFilter {

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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "savedFilter", orphanRemoval = true, targetEntity = SavedFilterGenderCategoryPreference.class)
	List<SavedFilterGenderCategoryPreference> genderCategories;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "savedFilter", orphanRemoval = true, targetEntity = SavedFilterCategoryPreference.class)
	List<SavedFilterCategoryPreference> categories;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "savedFilter", orphanRemoval = true, targetEntity = SavedFilterRegionPreference.class)
	List<SavedFilterRegionPreference> regions;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "savedFilter", orphanRemoval = true, targetEntity = SavedFilterStylePreference.class)
	List<SavedFilterStylePreference> styles;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "savedFilter", orphanRemoval = true, targetEntity = SavedFilterColorPreference.class)
	List<SavedFilterColorPreference> colors;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "savedFilter", orphanRemoval = true, targetEntity = SavedFilterUserPreference.class)
	List<SavedFilterUserPreference> users;
	
	
}
