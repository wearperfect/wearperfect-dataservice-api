package com.wearperfect.dataservice.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.CategoryDTO;
import com.wearperfect.dataservice.api.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	@GetMapping(value = "/categories")
	List<CategoryDTO> getCategories() {
		return categoryService.getAllCategories();
	}

	@GetMapping(value = "/categories/{regionId}")
	CategoryDTO getCategoryByCategoryId(@PathVariable(name = "categoryId") Integer categoryId) {
		return categoryService.getCategoryByCategoryId(categoryId);
	}

	@PostMapping(value = "/categories")
	CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
		return categoryService.createCategory(categoryDTO);
	}

	@PutMapping(value = "/categories/{categoryId}")
	CategoryDTO updateCategory(@PathVariable(name = "categoryId") Integer categoryId, @RequestBody CategoryDTO categoryDTO) {
		return categoryService.updateCategory(categoryId, categoryDTO);
	}

	@DeleteMapping(value = "/categories/{categoryId}")
	CategoryDTO deleteCategory(@PathVariable(name = "categoryId") Integer categoryId) {
		return categoryService.deleteCategory(categoryId);
	}
}
