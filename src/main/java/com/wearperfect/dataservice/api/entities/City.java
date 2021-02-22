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
@Table(name = "cities")
public class City {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "state_id")
	Long stateId;
	
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

	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = State.class)
	@JoinColumn(name = "state_id", referencedColumnName = "id", insertable = false, updatable = false)
	State state;
}
