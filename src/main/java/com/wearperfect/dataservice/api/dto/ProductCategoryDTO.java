package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class ProductCategoryDTO {
	Integer id;
	String name;
	String desc;
	String thumbnail;
	String sourceLink;
	Integer categoryId;
	Integer sequence;
	Long createdBy;
	Long createdOn;
	Long lastUpdatedBy;
	Long lastUpdatedOn;
	Boolean active;
	CategoryDTO category;
}
