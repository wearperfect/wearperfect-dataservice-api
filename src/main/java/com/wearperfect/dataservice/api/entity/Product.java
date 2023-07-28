package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
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

	@Column(name = "product_category_size_chart_id")
	Integer productCategorySizeChartId;

	@Column(name = "color_id")
	Integer colorId;

	@Column(name = "available_for_sale")
	Boolean availableForSale;

	@Column(name = "price")
	Float price;

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

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
	@JoinColumn(name="manufacturer_id", referencedColumnName = "id", insertable = false, updatable = false)
	User manufacturer;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductCategory.class)
	@JoinColumn(name="product_category_id", referencedColumnName = "id", insertable = false, updatable = false)
	ProductCategory productCategory;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = GenderCategory.class)
	@JoinColumn(name="gender_category_id", referencedColumnName = "id", insertable = false, updatable = false)
	GenderCategory genderCategory;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Color.class)
	@JoinColumn(name="color_id", referencedColumnName = "id", insertable = false, updatable = false)
	Color color;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Currency.class)
	@JoinColumn(name="currency_id", referencedColumnName = "id", insertable = false, updatable = false)
	Currency currency;

//	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductCategorySizeChart.class)
//	@JoinColumns({
//			@JoinColumn(name="manufacturer_id", referencedColumnName="manufacturer_id", insertable = false, updatable = false, nullable = false),
//			@JoinColumn(name="product_category_id", referencedColumnName="product_category_id", insertable = false, updatable = false, nullable = false),
//			@JoinColumn(name="gender_category_id", referencedColumnName="gender_category_id", insertable = false, updatable = false, nullable = false)
//	})
//	ProductCategorySizeChart productCategorySizeChart;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductCategorySizeChart.class)
	@JoinColumn(name="product_category_size_chart_id", referencedColumnName = "id", insertable = false, updatable = false)
	ProductCategorySizeChart productCategorySizeChart;

	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, targetEntity = ProductMedia.class, cascade = CascadeType.ALL, orphanRemoval = true)
	List<ProductMedia> productMediaList;

	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, targetEntity = ProductStyle.class, cascade = CascadeType.ALL, orphanRemoval = true)
	List<ProductStyle> productStyles;

	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, targetEntity = ProductInventoryItem.class, cascade = CascadeType.ALL, orphanRemoval = true)
	List<ProductInventoryItem> productInventoryItems;

	@Fetch(FetchMode.JOIN)
	@OneToOne(mappedBy = "product", fetch = FetchType.LAZY, targetEntity = ProductDiscount.class)
	private ProductDiscount productDiscount;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, targetEntity = ProductDiscountCoupon.class, cascade = CascadeType.ALL, orphanRemoval = true)
	List<ProductDiscountCoupon> productDiscountCoupons;
}
