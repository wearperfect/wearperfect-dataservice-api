package com.wearperfect.dataservice.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="products")
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "title")
	String title;

	@Column(name = "description")
	String description;

	@Column(name = "features")
	String features;

	@Column(name = "user_id")
	Long userId;

	@Column(name = "product_category_id")
	Integer productCategoryId;

	@Column(name = "available_for_sale")
	Boolean availableForSale;

	@Column(name = "price")
	Float price;

	@Column(name = "discount")
	Integer discount;

	@Column(name = "discount_type_id")
	Integer discountTypeId;

	@Column(name = "currency_id")
	Integer currencyId;

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
}
