package com.wearperfect.dataservice.api.entity;

import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="aws_s3_buckets")
public class AwsS3Bucket {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "description")
	String description;
	
	@Column(name = "region")
	String region;

	@Column(name = "created_on")
	Date createdOn;
	
	@Column(name = "created_by")
	Long createdBy;
	
	@Column(name = "last_updated_on")
	Date lastUpdatedOn;
	
	@Column(name = "last_updated_by")
	Long lastUpdatedBy;
	
	@Column(name = "active")
	Boolean active;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = AwsRegion.class)
	@JoinColumn(name="region", referencedColumnName = "name", updatable = false, insertable = false)
	AwsRegion regionDetails;
}
