package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "masters")
public class Master {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "name")
	String name;

	@Column(name = "active")
	Boolean active;

	@Column(name = "created_on")
	Date createdOn;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "master")
	Set<Slave> slaves = new HashSet<>();
}