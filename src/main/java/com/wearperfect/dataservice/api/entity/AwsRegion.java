package com.wearperfect.dataservice.api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "aws_regions")
public class AwsRegion {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name = "name")
	String name;

	@Column(name = "description")
	String description;

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
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity = AwsS3Bucket.class, mappedBy = "regionDetails", cascade = CascadeType.ALL, orphanRemoval = true)
	List<AwsS3Bucket> s3BucketsList;
}
