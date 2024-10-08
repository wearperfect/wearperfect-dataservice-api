package com.wearperfect.dataservice.api.entity;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name="name")
	String name;
	
	@Column(name="desc")
	String desc;

	@Column(name="code")
	String code;
	
	@Column(name="switchable", nullable = false)
	Boolean switchable;
	
	@Column(name="active")
	Boolean active;
	
	@Column(name="created_on")
	Date createdOn;
	
	@Column(name="last_updated_on")
	Date lastUpdatedOn;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, targetEntity = RoleFeature.class, cascade = CascadeType.ALL, orphanRemoval = true)
	List<RoleFeature> roleFeatures;
}
