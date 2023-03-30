package com.wearperfect.dataservice.api.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name = "name")
	String name;

	@Column(name = "desc")
	String desc;

	@Column(name = "sequence")
	Integer sequence;

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

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = PreferenceFilterCategory.class)
	List<PreferenceFilterCategory> preferenceFilterCategories;
}
