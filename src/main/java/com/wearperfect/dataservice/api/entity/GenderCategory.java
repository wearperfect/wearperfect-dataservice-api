package com.wearperfect.dataservice.api.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "gender_categories")
public class GenderCategory {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name="name")
	String name;
	
	@Column(name="short_name")
	String shortName;
	
	@Column(name="created_by")
	Long createdBy;
	
	@Column(name="created_on")
	Date createdOn;
	
	@Column(name="last_updated_by")
	Long lastUpdatedBy;
	
	@Column(name="last_updated_on")
	Date lastUpdatedOn;
	
	@Column(name="active")
	Boolean active;
	
	@OneToMany(mappedBy = "genderCategory", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = PreferenceFilterGenderCategory.class)
	List<PreferenceFilterGenderCategory> preferenceFilterGenderCategories;
}
