package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> getAllCategories();

	CategoryDTO getCategoryByCategoryId(Integer categoryId);

	CategoryDTO createCategory(CategoryDTO categoryDTO);

	CategoryDTO updateCategory(Integer categoryId, CategoryDTO categoryDTO);

	CategoryDTO deleteCategory(Integer categoryId);

}
