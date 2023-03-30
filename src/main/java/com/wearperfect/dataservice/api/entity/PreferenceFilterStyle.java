package com.wearperfect.dataservice.api.entity;

import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "preference_filter_styles")
public class PreferenceFilterStyle {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "preference_filter_id")
	Long preferenceFilterId;
	
	@Column(name = "style_id")
	Integer styleId;

	@Column(name = "created_on")
	Date createdOn;

	@Column(name = "last_updated_on")
	Date lastUpdatedOn;

	@Column(name = "active")
	Boolean active;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = PreferenceFilter.class)
	@JoinColumn(name="preference_filter_id", referencedColumnName = "id", insertable = false, updatable = false)
	PreferenceFilter preferenceFilter;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Style.class)
	@JoinColumn(name="style_id", referencedColumnName = "id", insertable = false, updatable = false)
	Style style;
}
