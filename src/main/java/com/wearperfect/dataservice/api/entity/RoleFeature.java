package com.wearperfect.dataservice.api.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "role_features")
public class RoleFeature {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "role_id")
	Integer roleId;
	
	@Column(name = "feature_id")
	Integer featureId;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class)
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
	Role role;
	
	@OneToOne(fetch = FetchType.LAZY, targetEntity = Feature.class, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "feature_id", referencedColumnName = "id", insertable = false, updatable = false)
	Feature feature;
}
