package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

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
