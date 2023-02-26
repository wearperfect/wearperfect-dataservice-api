package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
