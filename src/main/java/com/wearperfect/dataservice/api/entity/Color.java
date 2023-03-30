package com.wearperfect.dataservice.api.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="colors")
public class Color {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "code")
	String code;
	
	@Column(name = "desc")
	String desc;
	
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
	
	@OneToMany(mappedBy = "color", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = PreferenceFilterColor.class)
	List<PreferenceFilterColor> preferenceFilterColors;
}
