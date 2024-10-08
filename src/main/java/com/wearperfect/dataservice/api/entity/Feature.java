package com.wearperfect.dataservice.api.entity;

import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "features")
public class Feature {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "description")
	String desc;
	
	@Column(name = "sequence_id")
	Integer sequenceId;
	
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
}
