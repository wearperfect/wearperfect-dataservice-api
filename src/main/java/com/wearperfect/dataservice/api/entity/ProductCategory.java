package com.wearperfect.dataservice.api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "product_categories")
public class ProductCategory {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name = "name")
	String name;

	@Column(name = "desc")
	String desc;

	@Column(name = "thumbnail")
	String thumbnail;

	@Column(name = "source_link")
	String sourceLink;

	@Column(name = "category_id")
	Integer categoryId;
	
	@Column(name = "sequence")
	Integer sequence;

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

	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Category.class)
	@JoinColumn(name="category_id", referencedColumnName = "id", insertable = false, updatable = false)
	Category category;
}
