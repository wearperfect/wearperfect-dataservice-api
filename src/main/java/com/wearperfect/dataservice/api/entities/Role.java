package com.wearperfect.dataservice.api.entities;

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
@Table(name = "roles")
public class Role {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(name="name")
	String name;
	
	@Column(name="desc")
	String desc;
	
	@Column(name="active")
	boolean active;
	
	@Column(name="created_on")
	Date createdOn;
	
	@Column(name="last_updated_on")
	Date lastUpdatedOn;
}
