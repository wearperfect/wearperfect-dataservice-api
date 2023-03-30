package com.wearperfect.dataservice.api.entity;

import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "hash_tags")
public class HashTag {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name="tag")
	String tag;
	
	@Column(name="created_on")
	Date createdOn;
	
	@Column(name="active")
	Boolean active;
}
