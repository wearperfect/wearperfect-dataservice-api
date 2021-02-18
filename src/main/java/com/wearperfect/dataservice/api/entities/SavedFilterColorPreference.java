package com.wearperfect.dataservice.api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "saved_filter_color_preferences")
public class SavedFilterColorPreference {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "saved_filter_id")
	Long savedFilterId;
	
	@Column(name = "color_id")
	Integer colorId;

	@Column(name = "created_on")
	Date createdOn;

	@Column(name = "last_updated_on")
	Date lastUpdatedOn;

	@Column(name = "active")
	Boolean active;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = SavedFilter.class)
	@JoinColumn(name="saved_filter_id", referencedColumnName = "id", insertable = false, updatable = false)
	SavedFilter savedFilter;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Color.class)
	@JoinColumn(name="color_id", referencedColumnName = "id", insertable = false, updatable = false)
	Color color;
}
