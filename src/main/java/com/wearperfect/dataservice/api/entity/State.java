package com.wearperfect.dataservice.api.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "states")
public class State {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "name")
	String name;

	@Column(name = "short_name")
	String shortName;
	
	@Column(name = "country_id")
	Long countryId;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Country.class)
	@JoinColumn(name = "country_id", referencedColumnName = "id", insertable = false, updatable = false)
	Country country;
	
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY, targetEntity = City.class)
	List<City> cities;
}
