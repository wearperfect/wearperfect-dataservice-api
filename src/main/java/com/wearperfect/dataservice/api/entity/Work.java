package com.wearperfect.dataservice.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "works")
public class Work {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "worked_by")
	Long workedBy;
	
	@Column(name = "worked_at")
	Long workedAt;
	
	@Column(name = "worked_at_alt")
	String workedAtAlt;
	
	@Column(name = "worked_as")
	Integer workedAs;
	
	@Column(name = "worked_as_alt")
	String workedAsAlt;
	
	@Column(name = "worked_from")
	Date workedFrom;
	
	@Column(name = "worked_to")
	Date workedTo;
	
	@Column(name = "working_actively")
	Boolean workingActively;
	
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
