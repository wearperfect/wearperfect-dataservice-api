package com.wearperfect.dataservice.api.entity;

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
@Table(name = "slaves")
@lombok.RequiredArgsConstructor
public class Slave {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "master_id")
	Master master;

	@Column(name = "name")
	String name;

	@Column(name = "active")
	Boolean active;
	
	@Column(name = "created_on")
	Date createdOn;
}
