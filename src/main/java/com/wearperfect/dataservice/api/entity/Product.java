package com.wearperfect.dataservice.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="products")
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "name")
	String name;

	@Column(name = "description")
	String description;

	@Column(name = "features")
	String features;

	@Column(name = "manufacturer_id")
	Long manufacturerId;

	@Column(name = "product_category_id")
	Integer productCategoryId;

	@Column(name = "gender_category_id")
	Integer genderCategoryId;

	@Column(name = "color_id")
	Integer colorId;

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
	Instant createdOn;

	@Column(name = "last_updated_by")
	Long lastUpdatedBy;

	@Column(name = "last_updated_on")
	Instant lastUpdatedOn;

	@Column(name = "active")
	Boolean active;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
	@JoinColumn(name="manufacturer_id", referencedColumnName = "id", insertable = false, updatable = false)
	User manufacturer;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductCategory.class)
	@JoinColumn(name="product_category_id", referencedColumnName = "id", insertable = false, updatable = false)
	ProductCategory productCategory;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = GenderCategory.class)
	@JoinColumn(name="gender_category_id", referencedColumnName = "id", insertable = false, updatable = false)
	GenderCategory genderCategory;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Color.class)
	@JoinColumn(name="color_id", referencedColumnName = "id", insertable = false, updatable = false)
	Color color;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, targetEntity = ProductMedia.class, cascade = CascadeType.ALL, orphanRemoval = true)
	List<ProductMedia> productMediaList;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, targetEntity = ProductStyle.class, cascade = CascadeType.ALL, orphanRemoval = true)
	List<ProductStyle> productStyleList;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductCategorySizeChart.class)
	@JoinColumns({
			@JoinColumn(name="manufacturer_id", referencedColumnName="manufacturer_id", insertable = false, updatable = false, nullable = false),
			@JoinColumn(name="product_category_id", referencedColumnName="product_category_id", insertable = false, updatable = false, nullable = false),
			@JoinColumn(name="gender_category_id", referencedColumnName="gender_category_id", insertable = false, updatable = false, nullable = false)
	})
	ProductCategorySizeChart productCategorySizeChart;
}
