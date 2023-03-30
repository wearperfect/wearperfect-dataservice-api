package com.wearperfect.dataservice.api.entity;

import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "preference_filter_colors")
public class PreferenceFilterColor {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "preference_filter_id")
	Long preferenceFilterId;
	
	@Column(name = "color_id")
	Integer colorId;

	@Column(name = "created_on")
	Date createdOn;

	@Column(name = "last_updated_on")
	Date lastUpdatedOn;

	@Column(name = "active")
	Boolean active;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = PreferenceFilter.class)
	@JoinColumn(name="preference_filter_id", referencedColumnName = "id", insertable = false, updatable = false)
	PreferenceFilter preferenceFilter;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Color.class)
	@JoinColumn(name="color_id", referencedColumnName = "id", insertable = false, updatable = false)
	Color color;
}
